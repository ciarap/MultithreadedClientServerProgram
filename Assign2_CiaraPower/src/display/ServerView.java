/**
 * CLASS DETAILS
 * 
 * This class is the GUI for the server program. It acts as a view.
 * 
 *
 * Fields:
 *  - frmServer
 *  - text_messages
 *  
 *  
 * There are both public and private functions within this class.
 * 
 * Public Functions:
 * 	- All Getters and Setters
 *  - ServerView (constructor)
 *  - appendMessage
 * 
 * Private Functions:
 *  - initialize
 *  
 * 
 * FEATURE DETAILS

 *  - Messages pane: shows conversation with clients
 * 
 * @author Ciara Power - 20072488
 * 
 * @version 1.0
 * 
 * @since 07/11/2018
 * 
 */
package display;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ServerView {

	// **************************************************
	// Fields
	// **************************************************

	private JFrame frmServer;
	private JTextArea text_messages;

	// **************************************************
	// Constructors
	// **************************************************

	/**
	 * This is the constructor for the Server View GUI.
	 * 
	 * @return ServerView GUI object
	 * 
	 */
	public ServerView() {
		initialize();
	}

	// **************************************************
	// Private methods
	// **************************************************

	/**
	 * Initialize the contents of the frame.
	 * 
	 * This function takes no parameters.
	 * 
	 * @return void
	 * 
	 */
	private void initialize() {
		frmServer = new JFrame();
		frmServer.setTitle("Server");
		frmServer.setBounds(100, 100, 592, 427);
		frmServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServer.getContentPane().setLayout(null);

		JPanel messages = new JPanel();
		messages.setBackground(new Color(0, 139, 139));
		messages.setBounds(0, 0, 582, 380);
		frmServer.getContentPane().add(messages);
		messages.setLayout(null);

		JScrollPane scrollPane_messages = new JScrollPane();
		scrollPane_messages.setBounds(12, 52, 547, 315);
		messages.add(scrollPane_messages);

		text_messages = new JTextArea();
		text_messages.setEditable(false);
		scrollPane_messages.setViewportView(text_messages);

		JLabel lblMessages = new JLabel("Messages");
		lblMessages.setForeground(new Color(245, 255, 250));
		lblMessages.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblMessages.setBounds(224, 13, 126, 33);
		messages.add(lblMessages);
	}

	/** 
	 * This function returns the frmServer field.
	 * 
	 * @return frmServer
	 */
	public JFrame getFrmServer() {
		return frmServer;
	}

	/** 
	 * This function sets the frmServer field.
	 * 
	 * @param frmServer
	 * 
	 * @return void
	 * 
	 */
	public void setFrmServer(JFrame frmServer) {
		this.frmServer = frmServer;
	}

	/** 
	 * This function returns the text_messages field.
	 * 
	 * @return text_messages
	 */
	public JTextArea getText_messages() {
		return text_messages;
	}

	/** 
	 * This function sets the text_messages field.
	 * 
	 * @param text_messages
	 * 
	 * @return void
	 * 
	 */
	public void setText_messages(JTextArea text_messages) {
		this.text_messages = text_messages;
	}

	/**
	 * This function appends a message to the display.
	 * 
	 * @param message
	 * 
	 * @return void 
	 * 
	 */
	public void appendMessage(String message) {
		text_messages.append(message);
	}
}
