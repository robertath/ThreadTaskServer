package trainning.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.*;

public class ScheduleThreads {

	public static void main(String[] args) {

		System.out.println("---- Schedure thread ----");
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(4);

        try {
	        while(true) {
	    		Socket socket;
				socket = new Socket("localhost", 12345);
				
	        	System.out.println("Accepting new customer schedule on the port: " + socket.getPort());
	        	
//	        	TaskDistribuition taskDistribuition = new TaskDistribuition(socket);
//	            threadPool.scheduleAtFixedRate(taskDistribuition, 0, 2, TimeUnit.SECONDS);
	        }
        
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
	}

}
