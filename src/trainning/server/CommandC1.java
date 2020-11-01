package trainning.server;

import java.io.PrintStream;

public class CommandC1 implements Runnable {

	private PrintStream out;

	public CommandC1(PrintStream out) {
		this.out = out;
	}

	@Override
	public void run() {

		System.out.println("Command C1 is running...");

		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		out.println("Command C1 executed!");
	}

}
