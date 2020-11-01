package trainning.server;

import java.io.IOException;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class GetRunThreads {

	
	public static void main(String[] args) {
		
		System.out.println("---- Get threads----");
		Set<Thread> all = Thread.getAllStackTraces().keySet();
		
	    try {
	    	for(Thread thread : all){
	    		System.out.println(thread.getName());
	    	}
	    	
	    	
	    	Runtime runtime = Runtime.getRuntime();
	    	int qtdProcessadores = runtime.availableProcessors();
	    	System.out.println("Qtd de processadores: " + qtdProcessadores);
	    	
	    
	    } catch (Exception e) {
			e.printStackTrace();
		}
	}
}
