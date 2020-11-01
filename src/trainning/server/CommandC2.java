package trainning.server;

import java.io.PrintStream;

public class CommandC2 implements Runnable {

	private PrintStream out;
	
	public CommandC2(PrintStream out) {
		
		this.out = out;
	}
	
	@Override
	public void run() {
		
		System.out.println("Command C2 is running...");
		
		try {
			Thread.sleep(20000);
			
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
		out.println("Command C2 executed!");
        throw new RuntimeException("Exception no comando c2");
	}

}
