package trainning.queue;

import java.util.concurrent.*;

public class TestQueue {

	
	public static void main(String[] args) throws InterruptedException {
		
		BlockingQueue<Command> queue = new PriorityBlockingQueue<>();
		queue.put(new Command("ADD", 5, "curso=threads2&dataCriacao=12/06/2016&nivel=avancada"));
		queue.put(new Command("UPDATE", 3, "curso=threads2&dataCriacao=13/06/2016")); 
		queue.put(new Command("REMOVE", 1, "id=3"));
		queue.put(new Command("GET", 2, "id=4"));
		
		Command command = null;
		while((command = queue.take()) != null) {
			System.out.println(command.getType() + " - " + command.getPriority());	
		}
			
		
		System.out.println(queue.size());
	}
}
