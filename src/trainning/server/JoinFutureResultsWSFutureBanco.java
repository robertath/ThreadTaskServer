package trainning.server;

import java.io.PrintStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class JoinFutureResultsWSFutureBanco implements Callable<Void> {

	
	private Future<String> futureWS;
    private Future<String> futureBank;
    private PrintStream checkoutCustomer;

    public JoinFutureResultsWSFutureBanco(Future<String> futureWS, Future<String> futureBanco, PrintStream saidaCliente) {
        this.futureWS = futureWS;
        this.futureBank = futureBank;
        this.checkoutCustomer = checkoutCustomer;
    }

    //não queremos devolver nada, então usamos um tipo que representa nada: Void
    public Void call() { 

        System.out.println("Waiting result from future WS and Bank");

        try {
            String numeroMagico = this.futureWS.get(20, TimeUnit.SECONDS);
            String numeroMagico2 = this.futureBank.get(20, TimeUnit.SECONDS);

            this.checkoutCustomer.println("Command result c2: " + numeroMagico + ", " + numeroMagico2);

        } catch (InterruptedException | ExecutionException | TimeoutException e) {

            System.out.println("Timeout: Canceling the execution of the command c2");

            this.checkoutCustomer.println("Timeout during execution command c2");
            this.futureWS.cancel(true);
            this.futureBank.cancel(true);
        }

        System.out.println("Finished JoinFutureResultsWSFutureBanco");

        return null; //This Callable don't have any return type and because it, is null
    }
}
