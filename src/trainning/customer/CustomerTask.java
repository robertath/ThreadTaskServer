package trainning.customer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class CustomerTask {

	public static void main(String[] args) throws Exception {

		Socket socket = new Socket("localhost", 12345);
		System.out.println("Connection started");

		Thread threadSendCommand = new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					System.out.println("You can send a command now.. ");
					PrintStream out = new PrintStream(socket.getOutputStream());

					// wait enter
					Scanner keyboard = new Scanner(System.in);
					while (keyboard.hasNextLine()) {
						String line = keyboard.nextLine();

						if (line.trim().equals("")) {
							System.out.println("Close thread connection");
							break;
						}

						out.println(line);
					}

					out.close();
					keyboard.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		});

		Thread threadReceiveCommand = new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					System.out.println("Catching server message");
					Scanner answerServer = new Scanner(socket.getInputStream());

					while (answerServer.hasNextLine()) {
						String line = answerServer.nextLine();
						System.out.println(line);
					}
					
					answerServer.close();

				} catch (IOException e) {
					throw new RuntimeException();
				}
			}
		});

		threadReceiveCommand.start();
		threadSendCommand.start();
		
		threadSendCommand.join();
		System.out.println("Socket closed");
		socket.close();
		System.exit(0);

	}

}
