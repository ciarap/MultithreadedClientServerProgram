/**
 * CLASS DETAILS
 * 
 * This class is the main entry for the client program. It acts as a controller.
 * It handles the connection between the Client model and the ClientView GUI.
 * 
 * 
 * There is only one field in this class.
 *
 * Fields:
 *  - client
 *  
 *  
 * There are both public and private functions within this class.
 * 
 * Public:
 * 	- ClientA2 (constructor)
 *  - main
 *  - clientIDInputHandler
 *  - serverMessageRead
 *  - cleanup
 * 
 * Private:
 *  - run
 *  - getNameFromMessage
 *  - setServerMessage
 *  - setClientMessage
 * 
 * 
 * FEATURE DETAILS
 * 
 * Server connection details (HostName, Host Address) are shown for each message received, and on the details tab.
 * 
 * Once a client successfully connects as a registered student, the student details are filled in the details tab.
 * These fields stay blank if the connection is not successful.
 * 
 * When a client sends an ID to the server, whether successful or unsuccessful, the ID text input box
 * and the submit button are disabled.
 * 
 * All messages to and from the server will be appended to the text area in the messages tab.
 * 
 * @author Ciara Power - 20072488
 * 
 * @version 1.0
 * 
 * @since 07/11/2018
 * 
 */

package controllers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JOptionPane;

import display.ClientView;
import models.Client;

public class ClientA2 {

	// **************************************************
	// Fields
	// **************************************************

	Client client;

	// **************************************************
	// Constructors
	// **************************************************

	/**
	 * This is the constructor for the ClientA2 class.
	 * 
	 * It has no parameters.
	 * 
	 * @return ClientA2
	 *
	 */
	public ClientA2() {
		this.client = new Client();
	}

	// **************************************************
	// Private methods
	// **************************************************

	/**
	 * The run function is called from main when the ClientA2 program begins.
	 * This function deals with the inital connection to the socket, and setting up the data streams.
	 * 
	 * The inetAddress for the connection is obtained and field values in the Client Model are set.
	 * 
	 * There are no parameters for this function.
	 *  
	 * @return void
	 * 
	 */
	private void run() {
		try {
			// Create a socket to connect to the server
			client.setSocket(new Socket("localhost", 8000));

			// Create an input stream to receive data from the server
			client.setFromServer(new DataInputStream(client.getSocket().getInputStream()));

			// Create an output stream to send data to the server
			client.setToServer(new DataOutputStream(client.getSocket().getOutputStream()));

			// Get the inetAddress details for the socket, and set fields in model and GUI
			InetAddress inetAddress = client.getSocket().getInetAddress();
			client.setHostName(inetAddress.getHostName());
			client.setHostAddress(inetAddress.getHostAddress());
			client.getGui().setHostDetails(client.getHostName(), client.getHostAddress());
		}
		catch (IOException ex) {
			JOptionPane.showMessageDialog(client.getGui().getFrmStudentClientProgram(),  ex.toString() + '\n', "Error!", JOptionPane.ERROR_MESSAGE);
			client.getGui().setStudentNumberTextEdit(false);
		}
	}

	/**
	 * This function gets the name portion of the message received from the server upon successful connection and authentication.
	 * 
	 * @param serverInput - text received from the server in response to ID sent
	 * 
	 * @return String value representing the student's name
	 * 
	 */
	private String getNameFromMessage(String serverInput) {
		String[] split_string = serverInput.split(" ");
		String name="";
		int i=1; // Begin counter value at 1, as this is the first token of the student's name portion
		while(!split_string[i].equals("\n\tYou")) { // This "\n\tYou" substring will occur after the student's full name in the input
			name=name+split_string[i]+" "; // Add each string token to the name until "\n\tYou" token is reached
			i++;
		}
		return name;
	}

	/**
	 * This function passes a string to the GUI which will append the string to the messages text area.
	 * This template takes the server input message as a parameter to add to the text area.
	 * The Host Address and HostName are accessed to pass to the GUI also.
	 * 
	 * @param serverInput representing the text input received from the server
	 * 
	 * @return void
	 * 
	 */
	private void setServerMessage(String serverInput) {
		client.getGui().setText("---------------------------------- SERVER ---------------------------------\nConnection Details:\n\tHostname: "+client.getHostName()+
				"\n\tHost Address: "+client.getHostAddress()+"\n\nData Received:\n\t"+serverInput+
				"\n--------------------------------------------------------------------------------\n\n");
	}

	/**
	 * This function passes a string to the GUI which will append the string to the messages text area.
	 * This template takes the student ID sent to the server as a parameter to add to the text area.
	 * 
	 * @param stud_id representing the ID that was sent to the server
	 * 
	 * @return void
	 * 
	 */
	private void setClientMessage(String stud_id) {
		client.getGui().setText("---------------------------------- CLIENT ---------------------------------\nData Sent:\n\t"+stud_id+
				"\n--------------------------------------------------------------------------------\n\n");
	}


	// **************************************************
	// Public methods
	// **************************************************

	/**
	 * Launch the application.
	 * This is the main entry point of the client program.
	 * The controller ClientA2 is created, and a new ClientView GUI is set and displayed.
	 * 
	 * The run function is called which will handle initial connections etc.
	 * 
	 * @return void
	 * 
	 */
	public static void main(String[] args) {
		ClientA2 controller = new ClientA2();
		controller.client.setGui(new ClientView(controller));
		controller.client.getGui().getFrmStudentClientProgram().setVisible(true);
		controller.run();
	}

	/**
	 * This function handles the ID input in the GUI to send to the server.
	 * 
	 * @param stud_id representing the ID input from the GUI text input field.
	 * 
	 * @return void
	 * 
	 */
	public void clientIDInputHandler(String stud_id) {
		// Send the id to the server and call function to read response
		try {
			setClientMessage(stud_id);
			client.getToServer().writeUTF(stud_id);
			client.getToServer().flush();
			serverMessageRead(stud_id);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(client.getGui().getFrmStudentClientProgram(),  "Could not write with server:\n "+e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * This function reads the server response and handles the message received.
	 * 
	 * @param stud_id representing the ID of the student sent to the server.
	 * 
	 * @return void
	 */
	public void serverMessageRead(String stud_id) {
		try {
			// Receive text from the server and append to GUI
			String text = client.getFromServer().readUTF();
			setServerMessage(text);

			// Receive text from the server and append to GUI
			text = client.getFromServer().readUTF();
			if (text.contains("Welcome")) { // Successful Authentication Response
				client.getGui().setStudID(stud_id); // Set fields in details tab with student information
				String name = getNameFromMessage(text);
				client.getGui().setGUIName(name);
			}
			setServerMessage(text); // Appends message received to messages screen
		} catch (IOException e) {
			JOptionPane.showMessageDialog(client.getGui().getFrmStudentClientProgram(),  "Could not read from server:\n "+e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * This function closes the socket when a client program closes.
	 * 
	 * This function takes no parameters.
	 * 
	 * @return void
	 * 
	 */
	public void cleanup() {
		try {
			if(client.getSocket()!=null) { // Only close the socket if one exists
				client.getSocket().close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
