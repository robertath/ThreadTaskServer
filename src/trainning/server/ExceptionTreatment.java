package trainning.server;

import java.lang.Thread.UncaughtExceptionHandler;

public class ExceptionTreatment implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {

		System.out.println("Deu exceção na thread " + t.getName() + ", "
                + e.getMessage());
	}

}
