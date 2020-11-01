package trainning.server;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

public class CommandC2CallWS implements Callable<String> {

	private PrintStream out;
	
	public CommandC2CallWS(PrintStream out) {
		this.out = out;
	}

	@Override 
	public String call() throws Exception {
		
		System.out.println("Server received the command C2 - WS");
		
		out.println("C2 command in progress");
		Thread.sleep(15000);
		
		int num = new Random().nextInt(100) + 1;
		System.out.println("Server closed the command C2 - WS");
		
		return Integer.toString(num);
	}
		
}
