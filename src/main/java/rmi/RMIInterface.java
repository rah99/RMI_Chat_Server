package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends Remote {
	public String login(String username, String password) throws RemoteException;
	public String arbitraryMessage(String message) throws RemoteException;
}
