/**
 * CLASS DETAILS
 * 
 * This class is the main entry for the server program. It acts as a controller.
 * It handles the connection between the Server model and the ServerView GUI.
 * 
 * 
 * There is only one field in this class.
 *
 * Fields:
 *  - server
 *  
 *  
 * There are both public and private functions within this class.
 * 
 * Public Functions:
 * 	- MultiThreadedServerA2 (constructor)
 *  - main
 * 
 * Private Functions:
 *  - getConnection
 *  - run
 *  - listenForClients
 * 
 * 
 * Private Class:
 *  - ClientThread
 * 
 * 
 * DATABASE LOGIN
 * 
 * user = root
 * password = ""
 * 
 * 
 * @author Ciara Power
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
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import display.ServerView;
import models.Server;

public class MultiThreadedServerA2 {

	// **************************************************
	// Fields
	// **************************************************

	Server server;

	// **************************************************
	// Constructors
	// **************************************************

	/**
	 * This is the constructor for the MultiThreadedServerA2 class.
	 * 
	 * It has no parameters.
	 * 
	 * @return MultiThreadedServerA2
	 *
	 */
	public MultiThreadedServerA2() {
		this.server = new Server();
	}

	// **************************************************
	// Private methods
	// **************************************************

	/**
	 * This function constantly listens for client connections on the server socket.
	 * When a connection is accepted, a new thread is started for that client socket.
	 * 
	 * This function takes no parameters.
	 * 
	 * @return void
	 * 
	 */
	private void listenForClients() {
		try {
			// Create a server socket
			server.setServerSocket(new ServerSocket(8000));

			while (true) {
				// Listen for a connection request
				Socket socket = server.getServerSocket().accept();

				// Create a new thread for the new client socket accepted, and start thread running.
				ClientThread client = new ClientThread(socket);
				client.start();
			}
		}
		catch(IOException ex) {
			server.getGui().appendMessage("Error: "+ex);
		}
	}
	
	/**
	 * Get a new database connection using server model field values.
	 * 
	 * This function takes no parameters.
	 * 
	 * @return Connection 
	 * 
	 * @throws SQLException
	 * 
	 */
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", server.getUserName());
		connectionProps.put("password", server.getPassword());

		conn = DriverManager.getConnection("jdbc:mysql://"
				+ server.getServerName() + ":" +server.getPortNumber() + "/" + server.getDbName() + "?useSSL=false",
				connectionProps);

		return conn;
	}


	/**
	 * This function handles most functionality of the program, it is called from the main function above.
	 * Connect to MySQL and get a ResultSet object containing all database data.
	 * The function to listen for client connections is then called.
	 * 
	 * This function takes no parameters.
	 * 
	 * @return void
	 * 
	 */
	private void run() {
		try {
			server.setConn(this.getConnection());
			server.getGui().appendMessage("Connected to database\n");
		} catch (SQLException e) {
			server.getGui().appendMessage("Error: Could not connect to the database:\n "+e.getMessage());
			return;
		}

		// Create a ResultSet
		try {
			server.setS(server.getConn().createStatement (ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE));
			server.getS().executeQuery ("SELECT * FROM " + server.getTableName());
			server.setRs(server.getS().getResultSet ());
		} catch (SQLException e) {
			server.getGui().appendMessage("Error: Could not create the resultSet");
			return;
		}
		listenForClients();
	}

	// **************************************************
	// Public methods
	// **************************************************

	/**
	 * Run application.
	 * This is the main point of entry for the server controller program.
	 * The controller MultiThreadedServerA2 object is created, and a new ServerView GUI is set and displayed.
	 * 
	 * The run function is called which will handle database connectivity and client connections.
	 * 
	 * @return void
	 * 
	 */
	public static void main(String[] args) {
		MultiThreadedServerA2 controller = new MultiThreadedServerA2();
		controller.server.setGui(new ServerView());
		controller.server.getGui().getFrmServer().setVisible(true);
		controller.run();
	}

	// **************************************************
	// Private Class
	// **************************************************
	
	/**
	 * This class represents the model for a ClientThread.
	 * 
	 * The Thread interface is used in this class.
	 * 
	 * This class validates the student ID sent through the socket, using the database data.
	 * 
	 * Fields:
	 *  - inputFromClient
	 *  - outputToClient
	 *  - socket
	 *  - hostName
	 *  - hostAddress
	 *  
	 *  
	 * Public Functions:
	 *  - ClientThread (constructor)
	 *  - run
	 *  
	 * Private Functions:
	 *  - validateClient
	 *  - textIdReceived
	 *  - textClientConnectionRequest
	 * 
	 * @author Ciara Power - 20072488
	 * 
	 * @version 1.0
	 * 
	 * @since 07/11/18
	 *
	 */
	private class ClientThread extends Thread{

		// **************************************************
		// Fields
		// **************************************************

		private DataInputStream inputFromClient;
		private DataOutputStream outputToClient;
		private Socket socket;
		private String hostName;
		private String hostAddress;

		// **************************************************
		// Constructors
		// **************************************************
		
		/**
		 * This is the constructor for the ClientThread class.
		 * It creates initial data stream connections, and gets the inetAddress for the socket.
		 * 
		 * @param socket representing the client socket connection
		 * 
		 * @return ClientThread
		 * 
		 * @throws IOException
		 * 
		 */
		public ClientThread(Socket socket) throws IOException {

			this.socket = socket;

			// Create data input and output streams
			inputFromClient = new DataInputStream(socket.getInputStream());
			outputToClient = new DataOutputStream(socket.getOutputStream());

			// Get the inetAddress for the client socket.
			InetAddress inetAddress = socket.getInetAddress();
			hostName = inetAddress.getHostName();
			hostAddress = inetAddress.getHostAddress();

			// Append notification to screen about connection
			textClientConnectionRequest();
		}

		// **************************************************
		// Private methods
		// **************************************************
		
		/**
		 * This function validates the ID sent by the client over the socket.
		 * It iterates through the Resultset from the database, and checks if an entry with the corresponding student ID exists.
		 * Notifications are appended to the server screen, and response is sent to the client.
		 * 
		 * @param stud_id respresenting the ID received from the client
		 * 
		 * @return void
		 * 
		 */
		private void validateClient(String stud_id) {
			try {
				ResultSet rs = server.getRs();
				rs.beforeFirst(); // move cursor back to the start of the data
				while(rs.next()) {
					String valid_id = rs.getString("stud_id");
					if (valid_id.equals(stud_id)) { // Successful validation
						String fname = rs.getString("fname");
						String sname = rs.getString("sname");
						server.getGui().appendMessage("Client authenticated, "+fname+" "+sname+" is connected!\n" );
						outputToClient.writeUTF("Welcome "+fname+" "+sname+" \n\tYou are now connected to the Server!");
						return;
					}
				}
				// Unsuccessful validation
				server.getGui().appendMessage("Client failed authentication, closing connection!\n");
				outputToClient.writeUTF("Sorry "+stud_id+", You are not a registered student.\nGoodbye!");
				socket.close(); // close the connection if the client could not be authenticated
			} catch (SQLException | IOException e) {
				server.getGui().appendMessage("Error: "+e.getMessage());
			}
		}

		/**
		 * This function calls a function in the GUI to append text to the screen, to indicate an ID message was received.
		 * The client inet details are shown for the message.
		 * 
		 * @param stud_id representing the data received from the client
		 * 
		 * @return void 
		 * 
		 */
		private void textIdReceived(String stud_id) {
			server.getGui().appendMessage("\n\n------------------------------------ ID Received! ------------------------------------\nClient Details:\n\tHostname: "
					+hostName+"\n\tHost Address: "+hostAddress+"\n\nID:\n\t"+stud_id+"\n\nResults:\n\tProcessing....\n\t");
		}

		/**
		 * This function calls a function in the GUI to append text to the screen, to indicate a new client connected.
		 * The client inet details are shown for the message.
		 * 
		 * There are no parameters for this function.
		 * 
		 * @return void 
		 * 
		 */
		private void textClientConnectionRequest() {
			server.getGui().appendMessage("\n\n-------------------------------------------------------------------------------\n"
					+"--------------- Client connection request received! ---------------\nClient Details:\n\tHostname: "
					+hostName+"\n\tHost Address: "+hostAddress+"\n-------------------------------------------------------------------------------\n"
					+"-------------------------------------------------------------------------------\n");
		}
		
		// **************************************************
		// Public methods
		// **************************************************
		
		/**
		 * This function runs when the ClientThread instance is started. 
		 * It takes input from the client over the socket, and calls the relevant handlers.
		 * 
		 * There are no parameters for this function.
		 * 
		 * @return void
		 * 
		 */
		public void run() {

			String stud_id = "";
			try {
				stud_id = inputFromClient.readUTF();
				textIdReceived(stud_id);
				outputToClient.writeUTF("Processing....");
			} catch (IOException e) { // If server cannot read from socket, when client closes connection before sending data
				server.getGui().appendMessage("Warning: Client socket closed before input received\n");
			}
			validateClient(stud_id);
			server.getGui().appendMessage("-------------------------------------------------------------------------------------------");
		}

	}
}
