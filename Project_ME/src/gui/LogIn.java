package gui;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import object.Client;
import object.Member;
import object.Trainer;

public class LogIn extends JDialog implements ActionListener, KeyListener {

	GuiProcess gui;
	JTextField idTextfield = new JTextField(15);
	JPasswordField pwTextfield = new JPasswordField(15);
	JButton logInButton = new JButton("Login");
		
	public LogIn()
	{
		gui = GuiProcess.getInstance();
		pwTextfield.addKeyListener(this);
		logInButton.addActionListener(this);
		
		setLayout(new GridLayout(4, 2));
		JPanel panel0 = new JPanel();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		
		panel0.add(new JLabel(" �ｺ�� �Դϴ� :)"));
		panel1.add(new JLabel(" ID  "));
		panel1.add(idTextfield);
		panel2.add(new JLabel("PW "));
		panel2.add(pwTextfield);
		panel3.add(logInButton);
		
		add(panel0);
		add(panel1);
		add(panel2);
		add(panel3);
		
		pack();
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		try {
			if (e.getSource() == logInButton) {
				String id = this.idTextfield.getText();
				String pw = new String(this.pwTextfield.getPassword());
				
				Member member = gui.selectMember("loginId", id);
				
				if(member == null){
					JOptionPane.showMessageDialog(null, "�ش� id�� �������� �ʽ��ϴ�.");
					
				} else if (member.getPwd().equals(pw)) {
					// ������ �α���
					if(member.getPosition().equals("1")) {
						dispose();
						new AdminAMode();
					}
					
					//Ʈ���̳� �α���
					else if(member.getPosition().equals("trainer")){ //trainer �α���
						dispose();
						Vector<Trainer> trainer = gui.selectTrainer("loginId", id);
						String trainerName = trainer.get(0).getName();
						new AdminTMode(trainerName);
						
					} else if(member.getPosition().equals("client")){ //client �α���
						Client client = gui.selectClient("loginId", id).firstElement();
						dispose();
						new UserMode(client);
					}
				} else
					JOptionPane.showMessageDialog(null, "�н����尡 Ʋ�Ƚ��ϴ�.");
			}
			
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		String pw = new String(this.pwTextfield.getPassword());
		if (e.getSource() == pwTextfield && pw.length() > 15)
		{
			pwTextfield.setText(pw.substring(0, 15));
		}
	}
	
}