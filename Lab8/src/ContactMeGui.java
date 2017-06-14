import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ContactMeGui extends JFrame
{
	private static final int WIDTH = 400;
	private static final int HEIGHT = 300;

	private JLabel emailL, passwordL,reEnterPassL, messageL, nameL;
	private JTextField emailTF, messageTF, nameTF;
	private JPasswordField passwordTF,reEnterPassTF;
	private JButton sendB, exitB, clearB;
	private JRadioButton genderMaleB, genderFemaleB;
	private ButtonGroup gender;

	private SendButtonHandler sbHandler;
	private ExitButtonHandler ebHandler;
	private ClearButtonHandler cbHandler;

	private JMenuBar  menuBar;
	private JMenu make;
	private JMenuItem byEmail;
	private JMenuItem byFax;
	private JMenuItem byCellPhone;
	private JMenuItem byRegularMail;

	public ContactMeGui()
	{
		nameL = new JLabel("Full name: ", SwingConstants.CENTER);
		emailL = new JLabel("Email: ", SwingConstants.CENTER);
		passwordL = new JLabel("Password: ", SwingConstants.CENTER);
		reEnterPassL= new JLabel("Re-enter Password: ", SwingConstants.CENTER);
		messageL = new JLabel("Addtional Info: ", SwingConstants.CENTER);
		
		nameTF = new JTextField();
		emailTF = new JTextField();
		passwordTF = new JPasswordField();
		reEnterPassTF = new JPasswordField();
		messageTF = new JTextField();

		//SPecify handlers for each button and add (register) ActionListeners to each button.
		sendB = new JButton("Send");
		sbHandler = new SendButtonHandler();
		sendB.addActionListener(sbHandler);

		exitB = new JButton("Exit");
		ebHandler = new ExitButtonHandler();
		exitB.addActionListener(ebHandler);

		clearB = new JButton("Clear");
		cbHandler = new ClearButtonHandler();
		clearB.addActionListener(cbHandler);
		
		genderMaleB = new JRadioButton("Male");
		genderMaleB.setActionCommand("Male");
		
		genderFemaleB = new JRadioButton("Female");
		genderFemaleB.setActionCommand("Female");
		
		gender = new ButtonGroup();
		gender.add(genderMaleB);
		gender.add(genderFemaleB);

		menuBar = new JMenuBar();
		make = new JMenu("            Contact Me                 ");
		byEmail = new JMenuItem("by Email");
		byFax = new JMenuItem("by Fax");
		byCellPhone = new JMenuItem("by Cell Phone");
		byRegularMail = new JMenuItem("by Regular Mail");
		menuBar.add(make);
		make.add(byEmail);
		make.add(byFax);
		make.add(byCellPhone);
		make.add(byRegularMail);

		setTitle("Registration Form");
		Container pane = getContentPane();
		pane.setLayout(new GridLayout(8, 2));

		//Add things to the pane in the order you want them to appear (left to right, top to bottom)
		pane.add(nameL);
		pane.add(nameTF);
 		pane.add(emailL);
		pane.add(emailTF);
		pane.add(passwordL);
		pane.add(passwordTF);
		pane.add(reEnterPassL);
		pane.add(reEnterPassTF);
		pane.add(messageL);
		pane.add(messageTF);
		pane.add(genderMaleB);
		pane.add(genderFemaleB);
		pane.add(menuBar);
		
		pane.add(clearB);
		pane.add(sendB);
		pane.add(exitB);

		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private class SendButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//We use the getText & setText methods to manipulate the data entered into those fields.
			String inputEmail= emailTF.getText();
			String inputPassword= passwordTF.getText();
			String reinputPassword= reEnterPassTF.getText();
			String inputMessage= messageTF.getText();

			if(inputEmail.equals("") || inputPassword.equals("") || reinputPassword.equals("")){

				//Display error message
				JOptionPane.showMessageDialog(null,
						"Wrong input. Rememeber to fill all the spaces",
						"Something is missing!!!",
						JOptionPane.WARNING_MESSAGE);
			}
			//the password is invalid
			else if(!inputEmail.contains("@")) {
				JOptionPane.showMessageDialog(null, "Please enter a valid email adress", "Invalid email adress", JOptionPane.WARNING_MESSAGE);
			}
			//the password is too long
			else if(inputPassword.length() > 11){
				JOptionPane.showMessageDialog(null,"Wrong password. Rememeber it is 10 characters long",
						"Wrong Password Input!!!", JOptionPane.WARNING_MESSAGE);
			}
			//password is not the same
			else if(!inputPassword.equals(reinputPassword)) {
				JOptionPane.showMessageDialog(null, "Please enter the same password.", "Wrong password!", JOptionPane.WARNING_MESSAGE);
			}
			//the message is too long
			else if(inputMessage.length() > 81){
				JOptionPane.showMessageDialog(null,"Wrong Message. Rememeber it is 80 characters long",
						"Wrong Message Input!!!", JOptionPane.WARNING_MESSAGE);
			}
			
			//all is correct
			else
			{
				JOptionPane.showMessageDialog(null,
						"Your message has been sent.");
			}

			//massageTF.setText("" + area);
		}
	}

	public class ExitButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}
	
	public class ClearButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			emailTF.setText("");
			messageTF.setText("");
			nameTF.setText("");
			passwordTF.setText("");
			reEnterPassTF.setText("");
			gender.clearSelection();
		}
	}

	public static void main(String[] args)
	{
		ContactMeGui contactMe = new ContactMeGui();
	}
}