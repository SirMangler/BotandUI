package irc.sirmangler.ircbot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;
import java.awt.event.ActionListener;

public class Dialog extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2585848142539896326L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void dialog() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dialog frame = new Dialog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	static JTextArea area;
	private JTextField txtTextf;
	private boolean clicked = false;
	static JScrollPane scrollPane;
	private JMenuBar menuBar;
	private JMenu mnIrc;
	private JMenu mnMode;
	private final Action action = new SwingAction();
	private JCheckBoxMenuItem chckbxmntmChat;
	private JMenuItem mntmNewConnection;
	static boolean chatmode = false;
	private final Action action_1 = new SwingAction_1();
	private JMenu mnBot;
	private JMenuItem mntmBoterious;
	public Dialog() {
		setTitle("IRC Terminal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		
		contentPane.setLayout(new BorderLayout(2, 1));
		
		txtTextf = new JTextField();
		txtTextf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(txtTextf.getText()=="" || txtTextf.getText()==" ") {
					clicked = false;
					txtTextf.setForeground(Color.LIGHT_GRAY);
					txtTextf.setFont(new Font("Segoe UI", Font.BOLD, 11));
					txtTextf.setText("Enter Message Here");
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				if (clicked == false) {
					txtTextf.setForeground(Color.BLACK);
					txtTextf.setFont(new Font("Segoe UI", Font.PLAIN, 11));
					txtTextf.setText("");
					clicked = true;
				}
			}
		});
		txtTextf.setForeground(Color.LIGHT_GRAY);
		txtTextf.setFont(new Font("Segoe UI", Font.BOLD, 11));
		txtTextf.setBackground(Color.WHITE);
		txtTextf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER) {
					if(chatmode == false) {
						ClientSide.writeline(txtTextf.getText());
					} else { ClientSide.writechat(txtTextf.getText()); }
					txtTextf.setText("");
				}
			}
		});
		txtTextf.setText("Enter Message Here");
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnIrc = new JMenu("IRC");
		menuBar.add(mnIrc);
		
		mntmNewConnection = new JMenuItem("New Connection");
		mntmNewConnection.setAction(action_1);
		mnIrc.add(mntmNewConnection);
		
		mnMode = new JMenu("Mode");
		mnIrc.add(mnMode);
		
		chckbxmntmChat = new JCheckBoxMenuItem("Chat");
		chckbxmntmChat.setAction(action);
		mnMode.add(chckbxmntmChat);
		
		mnBot = new JMenu("Bot");
		menuBar.add(mnBot);
		
		mntmBoterious = new JMenuItem("Boterious");
		mntmBoterious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClientSide.bot = new BotMenu();
			}
		});
		mnBot.add(mntmBoterious);
		contentPane.add(txtTextf, BorderLayout.SOUTH);
		txtTextf.setColumns(10);
		
		setContentPane(contentPane);
		area = new JTextArea();
		area.setForeground(Color.WHITE);
		area.setBackground(Color.BLACK);
		area.setLineWrap(true);
		area.setEditable(false);
		JTextArea textArea = new JTextArea();
		DefaultCaret caret = (DefaultCaret)textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		scrollPane = new JScrollPane(area);
		scrollPane.setAutoscrolls(true);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane, BorderLayout.CENTER);


	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Chat");
			putValue(SHORT_DESCRIPTION, "Toggle Chat Mode");
		}
		public void actionPerformed(ActionEvent e) {
			chatmode=!chatmode;
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "New connection");
			putValue(SHORT_DESCRIPTION, "Connect to a new IRC and/or channel.");
		}
		public void actionPerformed(ActionEvent e)
		{
				NewCon.makeDialog();
		}
	}
}
