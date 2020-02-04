package irc.sirmangler.ircbot;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

public class NewCon extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtIrcexamplenet;
	private JTextField txtSirbotmangler;
	private JTextField txtPassword;
	private JTextField txtchannel;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	boolean LgInWNick = false;
	String ip = "";
	String user = "";
	String channel = "";
	String password = "";
	private JTextField textField;
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();
	
	public static void makeDialog() {
		try {
			NewCon dialog = new NewCon();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewCon() {
		setTitle("New Connection");
		setBounds(100, 100, 285, 200);
		getContentPane().setLayout(new BorderLayout(0, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JLabel lblServerip = new JLabel("IP     ");
			lblServerip.setBounds(10, 14, 25, 14);
			contentPanel.add(lblServerip);
		}
		{
			txtIrcexamplenet = new JTextField();
			txtIrcexamplenet.setBounds(62, 11, 86, 20);
			txtIrcexamplenet.setText("irc.example.net");
			contentPanel.add(txtIrcexamplenet);
			txtIrcexamplenet.setColumns(10);
		}
		{
			JLabel lblChannel = new JLabel("Channel #");
			lblChannel.setBounds(10, 103, 50, 14);
			contentPanel.add(lblChannel);
		}
		{
			txtchannel = new JTextField();
			txtchannel.setBounds(62, 100, 86, 20);
			contentPanel.add(txtchannel);
			txtchannel.setText("sirmangler");
			txtchannel.setColumns(10);
		}
		{
			txtSirbotmangler = new JTextField();
			txtSirbotmangler.setBounds(62, 36, 86, 20);
			contentPanel.add(txtSirbotmangler);
			txtSirbotmangler.setText("SirBotMangler");
			txtSirbotmangler.setColumns(10);
		}
		{
			JLabel lblUsernick = new JLabel("USER");
			lblUsernick.setBounds(10, 39, 26, 14);
			contentPanel.add(lblUsernick);
		}
		{
			txtPassword = new JTextField();
			txtPassword.setBounds(62, 61, 86, 20);
			contentPanel.add(txtPassword);
			txtPassword.setText("Password");
			txtPassword.setColumns(10);
		}
		{
			JLabel lblPass = new JLabel("PASS");
			lblPass.setBounds(10, 64, 25, 14);
			contentPanel.add(lblPass);
		}
		
		textField = new JTextField();
		textField.setText("6667");
		textField.setBounds(204, 11, 42, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblPort = new JLabel("PORT");
		lblPort.setBounds(165, 14, 29, 14);
		contentPanel.add(lblPort);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setBounds(162, 100, 84, 20);
		contentPanel.add(comboBox);
		
		JLabel lblProtocol = new JLabel("Protocol");
		lblProtocol.setBounds(180, 75, 46, 14);
		contentPanel.add(lblProtocol);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JCheckBox chckbxNewCheckBox = new JCheckBox("Log in with NICK");
				chckbxNewCheckBox.setAction(action);
				buttonPane.add(chckbxNewCheckBox);
			}
			{
				JButton okButton = new JButton("CONNECT");
				okButton.setAction(action_1);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setAction(action_2);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Log in with NICK");
			putValue(SHORT_DESCRIPTION, "Logs in using the NICK command instead of USER (TWITCH REQUIRES THIS)");
		}
		public void actionPerformed(ActionEvent e) {
			LgInWNick=!LgInWNick;
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Connect");
			putValue(SHORT_DESCRIPTION, "Connect using the set arguments");
		}
		public void actionPerformed(ActionEvent e) {
			ClientSide.running = false;
			ClientSide.ip=txtIrcexamplenet.getText();
			ClientSide.port=Integer.parseInt(textField.getText());
			ClientSide.user=txtSirbotmangler.getText();
			ClientSide.password=txtPassword.getText();
			ClientSide.channel="#"+txtchannel.getText();
			ClientSide.loginwitnck=LgInWNick;
			Dialog.area.setText("");
			ClientSide.startSock();
			setVisible(false);
		}
	}
	
	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "Cancel");
			putValue(SHORT_DESCRIPTION, "Cancel");
		}
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	}
}
