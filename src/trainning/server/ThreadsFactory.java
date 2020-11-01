package trainning.server;

import java.util.concurrent.ThreadFactory;

public class ThreadsFactory implements ThreadFactory {

	private static int number = 1;
	private ThreadFactory defaultFactory;
	
	
	public ThreadsFactory(ThreadFactory defaultFactory) {
		super();
		this.defaultFactory = defaultFactory;
	}


	@Override
	public Thread newThread(Runnable task) {

		Thread thread = defaultFactory.newThread(task);

		thread.setUncaughtExceptionHandler(new ExceptionTreatment());
		thread.setDaemon(true);
		return thread;
	}

}
