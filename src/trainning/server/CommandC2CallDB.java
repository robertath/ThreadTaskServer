package trainning.server;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

public class CommandC2CallDB implements Callable<String> {

	private PrintStream out;
	
	public CommandC2CallDB(PrintStream out) {
		this.out = out;
	}

	@Override 
	public String call() throws Exception {
		
		System.out.println("Server received the command C2 - DB");
		
		out.println("C2 command in progress");
		Thread.sleep(15000);
		
		int num = new Random().nextInt(100) + 1;
		System.out.println("Server closed the command C2 - DB");
		
		return Integer.toString(num);
	}
		
}
