package rmi;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class RMIClient {

	private static final long serialVersionUID = 1L; // Safeguard ensuring the classes used to start, end the program

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException { // Handling
																												// exceptions
		String response, password, username = "";

		try {
			// Creating instance of RMI registry & a Server instance using the interface

			Registry registry = LocateRegistry.getRegistry("localhost", 1099); // 1099 is the registry port
			RMIInterface server = (RMIInterface) registry.lookup("rmi://localhost/RMIserver");// Interface looks for a
																								// connection called
																								// RMIService

			StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor(); // Instance of Jasypt encryption
																						// .jar
			encryptor.setPassword("rob"); // Encryption key - without this key the encrypted obj will not be decrypted

			Scanner scanner = new Scanner(System.in); // Scanner instance

			System.out.println("Client starting...");
			do {
				System.out.print("Enter username: ");
				username = scanner.nextLine(); // Reading user input
				System.out.print("Enter password: ");
				password = scanner.nextLine(); // Reading user input

//				System.out.println("Password pre encryption: " + password); // Showing user input before encryption

				password = encryptor.encrypt(password); // Encrypting the user input

				System.out.println("Password post encryption: " + password); // Showing the encrypted paswword

				response = server.login(username, password); // Interfacing with login in RMIInterface to post and
																// receive response from the server for the username and
																// password
				System.out.println("[Server] " + response); // "[Server] to define where the message is from - this case
															// its the response from the server

				if (response.equalsIgnoreCase("Login Successful.")) { // Workaround for simulating if one did not login,
					System.out.print("Type message to send: "); // ...as in do not send request if failed login
					String arbitraryMessage = scanner.nextLine();
					String reply = server.arbitraryMessage(arbitraryMessage); // Interfacing with arbitraryMessage in
																				// RMIInterface to post and receive
																				// message from the server
					System.out.println("[Server] " + reply); // Print reply from server
				}
			} while (response.equalsIgnoreCase("Password/username combination incorrect, please try again.")
					|| response.equalsIgnoreCase("User not recognised"));

			scanner.close(); // Close the scanner to free up resources

			System.out.println("Closing client.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public static long getSerialversionuid() { // This was recommended by the IDE, which is logical so as the program
												// can verify
		return serialVersionUID; // ..that the same classes used to establish the connection are the ones to
									// terminate it
	}
}