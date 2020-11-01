package trainning.server;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;


public class FrontController implements Runnable {
	
	private Socket socket;
	private TaskServer server;
	private ExecutorService threadPool;
	private BlockingQueue<String> queueCommands;
	
	public FrontController(ExecutorService threadPool, Socket socket, TaskServer server, BlockingQueue<String> queueCmd) {
		this.socket = socket;
		this.server = server;
		this.threadPool = threadPool;
		this.queueCommands = queueCmd;
	}


	public void run() {
		
		System.out.println("Distribuiting task to the costumer");
		
		try {
			Scanner checkinCustomer = new Scanner(socket.getInputStream());
			PrintStream checkoutCustomer = new PrintStream(socket.getOutputStream());
			
			while(checkinCustomer.hasNextLine()) {
				String command = checkinCustomer.nextLine();
				System.out.println("Received command: " + command);
				
					switch (command) {
	                case "c1": {
	                	checkoutCustomer.println("Confirmation of the command c1");
	                	CommandC1 c1 = new CommandC1(checkoutCustomer);
	                	this.threadPool.execute(c1);
	                    break;
	                }
	                case "c2": {
	                	checkoutCustomer.println("Confirmation of the command c2");
	                	CommandC2CallWS c2WS = new CommandC2CallWS(checkoutCustomer);
	                	CommandC2CallDB c2DB = new CommandC2CallDB(checkoutCustomer);
	                	
	                	Future<String> futureWS = this.threadPool.submit(c2WS);
	                	Future<String> futureDB = this.threadPool.submit(c2DB);
	                	
	                	Callable<Void> joinResults = new JoinFutureResultsWSFutureBanco(futureWS, futureDB, checkoutCustomer);
	                	this.threadPool.submit(joinResults);
	                	
	                    break;
	                }
	                case "c3": {
	                	this.queueCommands.put(command);
	                	checkoutCustomer.println("C3 command was added on the queue.");
	                	break;
	                }
	                case "fim": {
	                	checkoutCustomer.println("Server Shutdown");
	                	server.callStop();
	                	return;
	                }
	                default: {
	                	checkoutCustomer.println("Command not found");
	                }
	            }
				
				System.out.println(command);
			}
			
			checkoutCustomer.close();
			checkinCustomer.close();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		};
	}
} 
