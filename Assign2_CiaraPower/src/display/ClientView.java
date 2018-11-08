/**
 * CLASS DETAILS
 * 
 * This class is the GUI for the client program. It acts as a view.
 * 
 *
 * Fields:
 *  - frmStudentClientProgram
 *	- student_number
 *	- hostname
 *	- ip_address
 *	- id
 *	- name
 *	- btn_submit
 *	- client
 *	- text_display
 *	- btnExit
 *  
 *  
 * There are both public and private functions within this class.
 * 
 * Public Functions:
 * 	- All Getters and Setters
 *  - setText
 *  - setStudID
 *  - setGUIName
 *  - setStudentNumberTextEdit
 *  - setHostDetails
 * 
 * Private Functions:
 *  - initialize
 *  - createTabbedPane
 *  - createLoginTab
 *  - createDetailsTab
 * 
 * 
 * Private Classes:
 *  - Listener
 *  - WindowListen
 *  
 * 
 * FEATURE DETAILS
 * 
 *  - Tab Layout
 *  - Text Field and Button disabling/enabling
 *  - Validation for input (cannot be blank)
 *  - Messages tab: shows conversation with server
 *  - Details tab: shows server connection details and authenticated student details
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controllers.ClientA2;

public class ClientView {

	// **************************************************
	// Fields
	// **************************************************

	private JFrame frmStudentClientProgram;
	private JTextField student_number;
	private JTextField hostname;
	private JTextField ip_address;
	private JTextField id;
	private JTextField name;
	private JButton btn_submit;
	private ClientA2 client;
	private JTextArea text_display;
	private JButton btnExit;

	// **************************************************
	// Constructors
	// **************************************************

	/**
	 * This is the constructor for the Client View GUI.
	 * 
	 * @param client representing a ClientA2 controller object.
	 * 
	 * @return ClientView GUI object
	 * 
	 */
	public ClientView(ClientA2 client) {
		this.client = client;
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
		frmStudentClientProgram = new JFrame();
		frmStudentClientProgram.setBackground(new Color(245, 255, 250));
		frmStudentClientProgram.setTitle("Student Client Program");
		frmStudentClientProgram.setBounds(100, 100, 447, 459);
		frmStudentClientProgram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStudentClientProgram.getContentPane().setLayout(null);
		frmStudentClientProgram.addWindowListener(new WindowListen());

		createTabbedPane();

		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 255, 250));
		panel.setBounds(0, 349, 428, 63);
		frmStudentClientProgram.getContentPane().add(panel);
		panel.setLayout(null);

		btnExit = new JButton("Exit");
		btnExit.setBounds(12, 13, 404, 37);
		panel.add(btnExit);
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.setBackground(new Color(0, 139, 139));
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnExit.addActionListener(new Listener());
	}

	/**
	 * This function creates the tabbed pane element of the GUI.
	 * 
	 * This function takes no parameters.
	 * 
	 * @return void
	 * 
	 */
	private void createTabbedPane() {
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tabbedPane.setBackground(new Color(245, 255, 250));
		tabbedPane.setBounds(0, 0, 428, 353);
		frmStudentClientProgram.getContentPane().add(tabbedPane);

		createLoginTab(tabbedPane);
		createDetailsTab(tabbedPane);

	}

	/**
	 * This function creates the details tab of the GUI.
	 * 
	 * @param tabbedPane
	 * 
	 * @return void
	 * 
	 */
	private void createDetailsTab(JTabbedPane tabbedPane) {
		JPanel details_tab = new JPanel();
		details_tab.setBackground(new Color(245, 255, 250));
		tabbedPane.addTab("Details", null, details_tab, null);
		details_tab.setLayout(null);

		JPanel server_details = new JPanel();
		server_details.setBackground(new Color(176, 224, 230));
		server_details.setLayout(null);
		server_details.setFont(new Font("Tahoma", Font.PLAIN, 16));
		server_details.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Server Details", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 17), new Color(0, 0, 0)));
		server_details.setBounds(12, 13, 401, 152);
		details_tab.add(server_details);

		JLabel label_hostname = new JLabel("Hostname:");
		label_hostname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_hostname.setBounds(68, 43, 80, 29);
		server_details.add(label_hostname);

		JLabel label_hostAddress = new JLabel("IP Address:");
		label_hostAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_hostAddress.setBounds(68, 98, 91, 29);
		server_details.add(label_hostAddress);

		hostname = new JTextField();
		hostname.setEditable(false);
		hostname.setColumns(10);
		hostname.setBounds(160, 47, 172, 22);
		server_details.add(hostname);

		ip_address = new JTextField();
		ip_address.setEditable(false);
		ip_address.setColumns(10);
		ip_address.setBounds(160, 102, 172, 22);
		server_details.add(ip_address);

		JPanel stud_details = new JPanel();
		stud_details.setFont(new Font("Tahoma", Font.PLAIN, 17));
		stud_details.setBackground(new Color(176, 224, 230));
		stud_details.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Student Details", TitledBorder.CENTER, TitledBorder.TOP,new Font("Tahoma", Font.PLAIN, 17), new Color(0, 0, 0)));
		stud_details.setBounds(12, 178, 399, 134);
		details_tab.add(stud_details);
		stud_details.setLayout(null);

		name = new JTextField();
		name.setEditable(false);
		name.setBounds(162, 87, 172, 22);
		stud_details.add(name);
		name.setColumns(10);

		id = new JTextField();
		id.setEditable(false);
		id.setBounds(162, 41, 172, 22);
		stud_details.add(id);
		id.setColumns(10);

		JLabel lblStud_id = new JLabel("ID:");
		lblStud_id.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblStud_id.setBounds(120, 42, 30, 19);
		stud_details.add(lblStud_id);

		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblName.setBounds(94, 89, 56, 16);
		stud_details.add(lblName);
	}

	/**
	 * This function creates the login tab of the GUI.
	 * 
	 * @param tabbedPane
	 * 
	 * @return void
	 * 
	 */
	private void createLoginTab(JTabbedPane tabbedPane) {
		JPanel login_panel = new JPanel();
		login_panel.setBackground(new Color(245, 255, 250));
		tabbedPane.addTab("Login", null, login_panel, null);
		login_panel.setLayout(null);

		JPanel login = new JPanel();
		login.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		login.setBackground(new Color(176, 224, 230));
		login.setBounds(10, 8, 401, 104);
		login_panel.add(login);
		login.setLayout(null);

		JLabel label = new JLabel("Student Number:");
		label.setBounds(24, 32, 122, 20);
		login.add(label);
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));

		student_number = new JTextField();
		student_number.setBounds(171, 30, 218, 25);
		login.add(student_number);
		student_number.setFont(new Font("Tahoma", Font.PLAIN, 15));
		student_number.setColumns(10);

		btn_submit = new JButton("Submit");
		btn_submit.setForeground(new Color(255, 255, 255));
		btn_submit.setBackground(new Color(0, 139, 139));
		btn_submit.setBounds(127, 68, 158, 25);
		login.add(btn_submit);
		btn_submit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_submit.addActionListener(new Listener());

		JPanel conversation_panel = new JPanel();
		conversation_panel.setBackground(new Color(176, 224, 230));
		conversation_panel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		conversation_panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		conversation_panel.setBounds(10, 125, 401, 190);
		login_panel.add(conversation_panel);
		conversation_panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(12, 30, 377, 147);
		conversation_panel.add(scrollPane);

		text_display = new JTextArea();
		text_display.setEditable(false);
		scrollPane.setViewportView(text_display);

		JLabel lblMessages = new JLabel("Messages");
		lblMessages.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMessages.setBounds(161, 0, 78, 29);
		conversation_panel.add(lblMessages);

	}

	// **************************************************
	// Public methods
	// **************************************************

	/** 
	 * This function returns the frmStudentClientProgram field.
	 * 
	 * @return frmStudentClientProgram
	 */
	public JFrame getFrmStudentClientProgram() {
		return frmStudentClientProgram;
	}

	/** 
	 * This function sets the frmStudentClientProgram field.
	 * 
	 * @param frmStudentClientProgram
	 * 
	 * @return void
	 */
	public void setFrmStudentClientProgram(JFrame frmStudentClientProgram) {
		this.frmStudentClientProgram = frmStudentClientProgram;
	}

	/** 
	 * This function returns the student_number field.
	 * 
	 * @return student_number
	 */
	public JTextField getStudent_number() {
		return student_number;
	}

	/** 
	 * This function sets the student_number field.
	 * 
	 * @param student_number
	 * 
	 * @return void
	 * 
	 */
	public void setStudent_number(JTextField student_number) {
		this.student_number = student_number;
	}

	/** 
	 * This function returns the hostname field.
	 * 
	 * @return hostname
	 */
	public JTextField getHostname() {
		return hostname;
	}

	/** 
	 * This function sets the hostname field.
	 * 
	 * @param hostname
	 * 
	 * @return void
	 * 
	 */
	public void setHostname(JTextField hostname) {
		this.hostname = hostname;
	}

	/** 
	 * This function returns the ip_address field.
	 * 
	 * @return ip_address
	 */
	public JTextField getIp_address() {
		return ip_address;
	}

	/** 
	 * This function sets the ip_address field.
	 * 
	 * @param ip_address
	 * 
	 * @return void
	 * 
	 */
	public void setIp_address(JTextField ip_address) {
		this.ip_address = ip_address;
	}

	/** 
	 * This function returns the id field.
	 * 
	 * @return id
	 */
	public JTextField getId() {
		return id;
	}

	/** 
	 * This function sets the id field.
	 * 
	 * @param id
	 * 
	 * @return void
	 * 
	 */
	public void setId(JTextField id) {
		this.id = id;
	}

	/** 
	 * This function returns the name field.
	 * 
	 * @return name
	 */
	public JTextField getName() {
		return name;
	}

	/** 
	 * This function sets the name field.
	 * 
	 * @param name
	 * 
	 * @return void
	 * 
	 */
	public void setName(JTextField name) {
		this.name = name;
	}

	/** 
	 * This function returns the btn_submit field.
	 * 
	 * @return btn_submit
	 */
	public JButton getBtn_submit() {
		return btn_submit;
	}

	/** 
	 * This function sets the btn_submit field.
	 * 
	 * @param btn_submit
	 * 
	 * @return void
	 * 
	 */
	public void setBtn_submit(JButton btn_submit) {
		this.btn_submit = btn_submit;
	}

	/** 
	 * This function returns the client field.
	 * 
	 * @return client
	 */
	public ClientA2 getClient() {
		return client;
	}

	/** 
	 * This function sets the client field.
	 * 
	 * @param client
	 * 
	 * @return void
	 * 
	 */
	public void setClient(ClientA2 client) {
		this.client = client;
	}

	/** 
	 * This function returns the text_display field.
	 * 
	 * @return text_display
	 */
	public JTextArea getText_display() {
		return text_display;
	}

	/** 
	 * This function sets the text_display field.
	 * 
	 * @param text_display
	 * 
	 * @return void
	 * 
	 */
	public void setText_display(JTextArea text_display) {
		this.text_display = text_display;
	}

	/** 
	 * This function returns the btnExit field.
	 * 
	 * @return btnExit
	 */
	public JButton getBtnExit() {
		return btnExit;
	}

	/** 
	 * This function sets the btnExit field.
	 * 
	 * @param btnExit
	 * 
	 * @return void
	 * 
	 */
	public void setBtnExit(JButton btnExit) {
		this.btnExit = btnExit;
	}

	/**
	 * This function appends text to the display.
	 * 
	 * @param text
	 * 
	 * @return void
	 * 
	 */
	public void setText(String text) {
		text_display.append("\n"+text);
	}

	/**
	 * This function sets the id textbox value.
	 * 
	 * @param stud_id
	 * 
	 * @return void
	 * 
	 */
	public void setStudID(String stud_id) {
		id.setText(stud_id);
	}

	/**
	 * This function sets the name textbox value.
	 * 
	 * @param name
	 * 
	 * @return void
	 * 
	 */
	public void setGUIName(String name) {
		this.name.setText(name);
	}

	/**
	 * This function sets the student number input textbox and submit button to be editable/enabled or not.
	 * 
	 * @param b
	 * 
	 * @return void
	 * 
	 */
	public void setStudentNumberTextEdit(boolean b) {
		student_number.setEditable(false);
		btn_submit.setEnabled(false);
	}

	/**
	 * This function sets the hostname and address textbox values.
	 * 
	 * @param hostName
	 * @param hostAddress
	 * 
	 * @return void
	 * 
	 */
	public void setHostDetails(String hostName, String hostAddress) {
		hostname.setText(hostName); 
		ip_address.setText(hostAddress);
	}

	// **************************************************
	// Private Classes
	// **************************************************
	
	/**
	 * This class represents the Listener model.
	 * 
	 * The ActionListener interface is implemented by this class.
	 * 
	 * This class handles actions such as buttons pressed on the GUI.
	 *  
	 * Public Functions:
	 *  - actionPerformed
	 *
	 * @author Ciara Power - 20072488
	 * 
	 * @version 1.0
	 * 
	 * @since 07/11/18
	 *
	 */
	private class Listener implements ActionListener {
		
		/**
		 * This function deals with an action event such as a button pressed.
		 * 
		 * @param e
		 * 
		 * @return void
		 * 
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btn_submit) {
				// Get the id from the text field
				String stud_id = student_number.getText().trim();
				if (stud_id.isEmpty()) {
					JOptionPane.showMessageDialog(frmStudentClientProgram, "Student ID cannot be empty!", "Error!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				client.clientIDInputHandler(stud_id); // call relevant handler in controller
				setStudentNumberTextEdit(false);
			}
			else if (e.getSource()==btnExit) {
				client.cleanup();
				System.exit(0);
			}
		}
	}

	
	/**
	 * This class represents the WindowListen model.
	 * 
	 * The WindowListener interface is implemented by this class.
	 * 
	 * This class handles window actions.
	 *  
	 * Public Functions:
	 *  - windowActivated
	 *  - windowClosed
	 *  - windowClosing
	 *  - windowDeactivated
	 *  - windowDeiconified
	 *  - windowIconified
	 *  - windowOpened
	 *
	 * @author Ciara Power - 20072488
	 * 
	 * @version 1.0
	 * 
	 * @since 07/11/18
	 *
	 */
	private class WindowListen implements WindowListener{

		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		/**
		 * This function calls the controller cleanup function to close sockets.
		 * 
		 * @return void
		 * 
		 */
		@Override
		public void windowClosing(WindowEvent arg0) {
			client.cleanup();
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

	}
}
