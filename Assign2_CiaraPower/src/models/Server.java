/**
 * CLASS DETAILS
 * 
 * This class is the server model for the server program.
 * 
 *
 * Fields:
 *  - userName
 *  - password
 *  - serverName
 *  - portNumber
 *  - dbName
 *  - tableName
 *  - conn
 *  - serverSocket
 *  - rs
 *  - s
 *  - gui
 *  
 * Public Functions:
 * 	- All Getters and Setters
 * 
 * Private Functions:
 *  - initialize
 *  
 * 
 * @author Ciara Power - 20072488
 * 
 * @version 1.0
 * 
 * @since 07/11/2018
 * 
 */
package models;

import java.net.ServerSocket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import display.ServerView;

public class Server {

	// **************************************************
	// Fields
	// **************************************************

	private final String userName = "root";
	private final String password = "";
	private final String serverName = "localhost";
	private final int portNumber = 3306;
	private final String dbName = "assign2";
	private final String tableName = "mystudents";
	private Connection conn = null;
	private ServerSocket serverSocket;
	private ResultSet rs;
	private Statement s;
	private ServerView gui;

	// **************************************************
	// Public methods
	// **************************************************
	
	/** 
	 * This function returns the serverSocket field.
	 * 
	 * @return serverSocket
	 */
	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	/** 
	 * This function sets the serverSocket field.
	 * 
	 * @param serverSocket
	 * 
	 * @return void
	 * 
	 */
	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	/** 
	 * This function returns the conn field.
	 * 
	 * @return conn
	 */
	public Connection getConn() {
		return conn;
	}

	/** 
	 * This function sets the conn field.
	 * 
	 * @param conn
	 * 
	 * @return void
	 * 
	 */
	public void setConn(Connection conn) {
		this.conn = conn;
	}

	/** 
	 * This function returns the rs field.
	 * 
	 * @return rs
	 */
	public ResultSet getRs() {
		return rs;
	}

	/** 
	 * This function sets the rs field.
	 * 
	 * @param rs
	 * 
	 * @return void
	 * 
	 */
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	/** 
	 * This function returns the s field.
	 * 
	 * @return s
	 */
	public Statement getS() {
		return s;
	}

	/** 
	 * This function sets the s field.
	 * 
	 * @param s
	 * 
	 * @return void
	 * 
	 */
	public void setS(Statement s) {
		this.s = s;
	}

	/** 
	 * This function returns the gui field.
	 * 
	 * @return gui
	 */
	public ServerView getGui() {
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
	public void setGui(ServerView gui) {
		this.gui = gui;
	}

	/** 
	 * This function returns the userName field.
	 * 
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/** 
	 * This function returns the password field.
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/** 
	 * This function returns the serverName field.
	 * 
	 * @return serverName
	 */
	public String getServerName() {
		return serverName;
	}

	/** 
	 * This function returns the portNumber field.
	 * 
	 * @return portNumber
	 */
	public int getPortNumber() {
		return portNumber;
	}

	/** 
	 * This function returns the dbName field.
	 * 
	 * @return dbName
	 */
	public String getDbName() {
		return dbName;
	}

	/** 
	 * This function returns the tableName field.
	 * 
	 * @return tableName
	 */
	public String getTableName() {
		return tableName;
	}

}
