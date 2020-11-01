package trainning.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;

import trainning.customer.ConsumerTask;

public class TaskServer {

	
	private ServerSocket server;
	private ExecutorService threadPool;
	private AtomicBoolean isRunning;
	private BlockingQueue<String> queueCommands;
	
	public TaskServer() throws IOException {
        System.out.println("---- Starting Server ----");
		this.server = new ServerSocket(12345);
		ThreadFactory defaultFactory = Executors.defaultThreadFactory();
		this.threadPool = Executors.newFixedThreadPool(4, defaultFactory);
//		this.threadPool = Executors.newCachedThreadPool();
		
		this.isRunning = new AtomicBoolean(true);
		this.queueCommands = new ArrayBlockingQueue<String>(2);
		startConsumers();
	}


	public static void main(String[] args) throws Exception  {
		TaskServer server = new TaskServer();
		server.callStart();
	}

	public void callStart() throws IOException {

		while(this.isRunning.get()) {
			
			try {
				Socket socket = this.server.accept();
				System.out.println("Accepting new customer on the port: " + socket.getPort());
				
				FrontController taskDistribuition = new FrontController(threadPool, socket, this, queueCommands);
				threadPool.execute(taskDistribuition);
				
			} catch (SocketException e) {
				System.out.println("Socket exception, is running: " + this.isRunning);
			}
		}
	}
	
	public void callStop() throws IOException {
		this.isRunning.set(false);
		this.threadPool.shutdown();
		this.server.close();
	}
	
	private void startConsumers() {
		int amount = 2;
		for(int i = 0; i < amount; i++) {
			ConsumerTask task = new ConsumerTask(queueCommands);
			this.threadPool.execute(task);
		}
	}
}
