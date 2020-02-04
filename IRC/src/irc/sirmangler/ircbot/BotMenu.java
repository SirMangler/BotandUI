package irc.sirmangler.ircbot;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.TextArea;
import javax.swing.JScrollBar;
import javax.swing.border.LineBorder;
import javax.swing.JLayeredPane;
import javax.swing.border.CompoundBorder;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BotMenu extends JDialog {
	private JLabel lblOnline;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BotMenu dialog = new BotMenu();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BotMenu() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			getContentPane().add(tabbedPane, BorderLayout.CENTER);
			{
				JPanel panel = new JPanel();
				tabbedPane.addTab("Console", null, panel, null);
				panel.setLayout(new BorderLayout(0, 0));
				{
					JTextArea textArea = new JTextArea();
					textArea.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
						}
					});
					textArea.addKeyListener(new KeyAdapter() {
						@Override
						public void keyReleased(KeyEvent arg0) {
						}
					});
					textArea.setForeground(Color.GREEN);
					textArea.setBackground(Color.BLACK);
					panel.add(textArea);
				}
				{
					JPanel panel_1 = new JPanel();
					panel.add(panel_1, BorderLayout.SOUTH);
					{
						lblOnline = new JLabel("Online");
						lblOnline.setAlignmentX(Component.CENTER_ALIGNMENT);
					}
					
					textField = new JTextField();
					textField.setColumns(10);
					GroupLayout gl_panel_1 = new GroupLayout(panel_1);
					gl_panel_1.setHorizontalGroup(
						gl_panel_1.createParallelGroup(Alignment.LEADING)
							.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
								.addGap(2)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(lblOnline, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
					);
					gl_panel_1.setVerticalGroup(
						gl_panel_1.createParallelGroup(Alignment.LEADING)
							.addGroup(Alignment.TRAILING, gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblOnline, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
					);
					panel_1.setLayout(gl_panel_1);
				}
			}
			{
				JPanel panel = new JPanel();
				tabbedPane.addTab("Options", null, panel, null);
				panel.setLayout(new BorderLayout(0, 0));
				{
					JPanel buttonPane = new JPanel();
					panel.add(buttonPane, BorderLayout.SOUTH);
					buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
					{
						JButton okButton = new JButton("OK");
						okButton.setActionCommand("OK");
						buttonPane.add(okButton);
						getRootPane().setDefaultButton(okButton);
					}
					{
						JButton cancelButton = new JButton("Cancel");
						cancelButton.setActionCommand("Cancel");
						buttonPane.add(cancelButton);
					}
				}
				{
					JPanel panel_1 = new JPanel();
					panel.add(panel_1, BorderLayout.CENTER);
					
					JButton btnStart = new JButton("Start");
					JButton btnStop = new JButton("Stop");
					
					JCheckBox chckbxDontSendMessages = new JCheckBox("Don't send messages to channel");
					
					JLabel lblIgnoreUsers = new JLabel("Ignore Users");
					
					JLayeredPane layeredPane = new JLayeredPane();
					GroupLayout gl_panel_1 = new GroupLayout(panel_1);
					gl_panel_1.setHorizontalGroup(
						gl_panel_1.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_1.createSequentialGroup()
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel_1.createSequentialGroup()
										.addContainerGap()
										.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_panel_1.createSequentialGroup()
												.addComponent(btnStart)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(btnStop))
											.addGroup(gl_panel_1.createSequentialGroup()
												.addComponent(chckbxDontSendMessages)
												.addGap(122)
												.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))))
									.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
										.addGap(325)
										.addComponent(lblIgnoreUsers, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addContainerGap())
					);
					gl_panel_1.setVerticalGroup(
						gl_panel_1.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panel_1.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblIgnoreUsers)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
									.addComponent(chckbxDontSendMessages, Alignment.TRAILING)
									.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnStart)
									.addComponent(btnStop))
								.addContainerGap())
					);
					layeredPane.setLayout(new BorderLayout(0, 0));
					
					JTextArea textArea = new JTextArea();
					textArea.setBorder(new LineBorder(new Color(0, 0, 0), 2));
					layeredPane.add(textArea, BorderLayout.CENTER);
					
					JScrollBar scrollBar = new JScrollBar();
					scrollBar.setSize(2, 50);
					layeredPane.add(scrollBar, BorderLayout.EAST);
					panel_1.setLayout(gl_panel_1);
				}
			}
		}
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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
}
