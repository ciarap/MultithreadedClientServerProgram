/**
 * CLASS DETAILS
 * 
 * This class is the client model for the client program.
 * 
 *
 * Fields:
 *  - gui
 *  - hostName
 *  - hostAddress
 *  - socket
 *  - toServer
 *  - fromServer
 *  
 * Public Functions:
 * 	- All Getters and Setters
 * 
 * Private Functions:
 *  - initialize
 * 
 * @author Ciara Power - 20072488
 * 
 * @version 1.0
 * 
 * @since 07/11/2018
 * 
 */
package models;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import display.ClientView;

public class Client {

	// **************************************************
	// Fields
	// **************************************************

	private ClientView gui;
	private String hostName;
	private String hostAddress;
	private Socket socket;

	// IO streams
	private DataOutputStream toServer;
	private DataInputStream fromServer;

	// **************************************************
	// Public methods
	// **************************************************

	/** 
	 * This function returns the gui field.
	 * 
	 * @return gui
	 */
	public ClientView getGui() {
		return gui;
	}
	
	/** 
	 * This function sets the gui field.
	 * 
	 * @param gui
	 * 
	 * @return void
	 * 
	 */
	public void setGui(ClientView gui) {
		this.gui = gui;
	}
	
	/** 
	 * This function returns the hostName field.
	 * 
	 * @return hostName
	 */
	public String getHostName() {
		return hostName;
	}
	
	/** 
	 * This function sets the hostName field.
	 * 
	 * @param hostName
	 * 
	 * @return void
	 * 
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	
	/** 
	 * This function returns the hostAddress field.
	 * 
	 * @return hostAddress
	 */
	public String getHostAddress() {
		return hostAddress;
	}
	
	/** 
	 * This function sets the hostAddress field.
	 * 
	 * @param hostAddress
	 * 
	 * @return void
	 * 
	 */
	public void setHostAddress(String hostAddress) {
		this.hostAddress = hostAddress;
	}
	
	/** 
	 * This function returns the socket field.
	 * 
	 * @return socket
	 */
	public Socket getSocket() {
		return socket;
	}
	
	/** 
	 * This function sets the socket field.
	 * 
	 * @param socket
	 * 
	 * @return void
	 * 
	 */
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	/** 
	 * This function returns the toServer field.
	 * 
	 * @return toServer
	 */
	public DataOutputStream getToServer() {
		return toServer;
	}
	
	/** 
	 * This function sets the toServer field.
	 * 
	 * @param toServer
	 * 
	 * @return void
	 * 
	 */
	public void setToServer(DataOutputStream toServer) {
		this.toServer = toServer;
	}
	
	/** 
	 * This function returns the fromServer field.
	 * 
	 * @return fromServer
	 */
	public DataInputStream getFromServer() {
		return fromServer;
	}
	
	/** 
	 * This function sets the fromServer field.
	 * 
	 * @param fromServer
	 * 
	 * @return void
	 * 
	 */
	public void setFromServer(DataInputStream fromServer) {
		this.fromServer = fromServer;
	}
}
