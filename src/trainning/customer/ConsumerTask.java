package trainning.customer;

import java.util.concurrent.BlockingQueue;

public class ConsumerTask implements Runnable{

	private BlockingQueue<String> queueCommands;
	
	public ConsumerTask(BlockingQueue<String> queueCommands) {
		this.queueCommands = queueCommands;
	}

	@Override
	public void run() {

		try {
			String command = null;
			
			while ((command = queueCommands.take()) != null) {
	            System.out.println("Consuming the command: " + command + ", "
	                    + Thread.currentThread().getName());
	            Thread.sleep(10000);
	        }
			
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
	}

}
