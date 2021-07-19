package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class RMIServer extends UnicastRemoteObject implements RMIInterface {

	private static final long serialVersionUID = 1L; // Safeguard ensuring the classes used to start, end the program
	private TreeMap<String, String> clientTreeMap = new TreeMap<String, String>();

	public RMIServer() throws RemoteException {
		super();
	}

	public static void main(String[] args) {

		// Create instances of the registry on its port (1099) and server
		try {
			Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT); // REGISTRY_PORT is the default
																						// for the registry
			RMIServer server = new RMIServer();
			registry.rebind("rmi://localhost/RMIserver", server); // Bind RMIService on the IP localhost to the registry
			System.out.println("Server ready for logins..."); // Print out server is ready
		} catch (RemoteException e) { // Catch exceptions crash caused e.g. if the client port number is wrong
			System.err.println(e.getMessage());
		}
	}

	public String login(String username, String password) throws RemoteException { // Implementation of the interfaces
																					// method (getter)

		init();

		System.out.println("Password pre decryption: " + password); // Showing password received is encrypted

		try {
			StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor(); // Jasypt encription API
			decryptor.setPassword("rob"); // Identical key as in the client class, these cannot differ (similar to
											// salting) - ideally more complex
			password = decryptor.decrypt(password); // Decrypting the password for checking
		} catch (Exception e) { // Exception handling as major event occurs if the encryption keys are different
			System.out.println(e.getMessage());
		}

		System.out.println("Password post decryption: " + password);

		String response = search(username, password);

		return response; // information sent as a result of calling this method
	}

	public String arbitraryMessage(String message) throws RemoteException { // Getter for interface method
		return "Your message was: " + message;
	}

	private String search(String username, String password) throws RemoteException {
		String response = "";

		Set<Entry<String, String>> set = clientTreeMap.entrySet(); // Sets the TreeMap so as no two sets(String, String)
																	// of entries can be the same
		Iterator<Entry<String, String>> iterator = set.iterator(); // An object that loops through collections
		boolean flag = false; // Flag to create scenarios based on true/false

		while (iterator.hasNext()) { // Iterator called to loop the set in the TreeMap - hasNext = true if the
										// scanner has a token to collect
			response = ""; // Reset response to "";
			Entry<String, String> entry = iterator.next(); // Accesses the TreeMap looking for a key and associated
															// values
			String user = entry.getKey().toString(); // Entry key
			String pass = entry.getValue().toString(); // Entry value

			if (username.equals(user)) { // Checks if the username returned from the interface matches the TreeMap key
				flag = true; // Flagging username as registered
				if (password.equals(pass)) { // Checks if the password returned from the interface matches the TreeMap
												// key's value
					response = "Login Successful."; // If the interfaces values match the key and value
				} else {
					response = "Password/username combination incorrect, please try again."; // Password wrong
				}
				break; // Breaks out of the loop
			}
		}
		if (!flag) {
			response = "User not recognised"; // If the username is wrong, irrespective of the password
		}
		return response; // Returns the respective response based on the above criteria
	}

	private void init() {
		clientTreeMap.put("user1", "1234"); // Adding entries to TreeMap - key and value respectively
		clientTreeMap.put("user2", "1234");
	}
}
