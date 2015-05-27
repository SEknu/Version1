package gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import object.Member;
import object.Trainer;
import database.FileManager;

public class TrainerRegister extends JDialog implements ActionListener{
	JButton okButton = new JButton("���");
	JButton cancelButton = new JButton("���");
		
	JTextField nameTextfield = new JTextField(5);
	JTextField idTextfield = new JTextField(5);
	
	JTextField yearTextfield = new JTextField(5);
	JTextField monthTextfield = new JTextField(5);
	JTextField dateTextfield = new JTextField(5);
	
	JTextField phone1Textfield = new JTextField(4);
	JTextField phone2Textfield = new JTextField(4);
	JTextField phone3Textfield = new JTextField(4);
	JTextField addressTextfield = new JTextField(10);
	
	FileManager filemanager = FileManager.getInstance();
		
	public TrainerRegister() {
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		setLayout(new GridLayout(5, 2));
		JPanel panel1 = new JPanel(new FlowLayout());
		JPanel panel2 = new JPanel(new FlowLayout());
		
		add(new JLabel("�̸�"));
		add(nameTextfield);

		add(new JLabel("�Ի���"));
		panel1.add(yearTextfield);
		panel1.add(monthTextfield);
		panel1.add(dateTextfield);
		add(panel1);
		
		add(new JLabel("��ȭ��ȣ"));
		panel2.add(phone1Textfield);
		panel2.add(new JLabel("-"));
		panel2.add(phone2Textfield);
		panel2.add(new JLabel("-"));
		panel2.add(phone3Textfield);
		add(panel2);
		
		add(new JLabel("�ּ�"));
		add(addressTextfield);
				
		add(okButton);
		add(cancelButton);
		
		pack();
		setModal(true);
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == okButton) {
			
			String id = createId();
			String name = this.nameTextfield.getText();
			String rDate = this.yearTextfield.getText() + "-" + this.monthTextfield.getText() + "-" + this.dateTextfield.getText();
			String phone = this.phone1Textfield.getText() + "-" + this.phone2Textfield.getText() + "-" + this.phone3Textfield.getText();
			String addr = this.addressTextfield.getText();
			String loginId = createLoginId(phone);
			String pwd = createPwd();
			int salary = createSalary();
			String position = "trainer";
		
			//���� or ���˿� ��߳� �Է� ����ó��
			if(name.length()==0)
				JOptionPane.showMessageDialog(null, "�̸��� �Է��Ͻʽÿ�.", "", JOptionPane.ERROR_MESSAGE);
			else if(id.length()==0)
				JOptionPane.showMessageDialog(null, "id�� �Է��Ͻʽÿ�.", "", JOptionPane.ERROR_MESSAGE);
			else if(this.yearTextfield.getText().length()==0 || this.monthTextfield.getText().length()==0 || this.dateTextfield.getText().length()==0)
				JOptionPane.showMessageDialog(null, "�Ի����� �Է��Ͻʽÿ�.", "", JOptionPane.ERROR_MESSAGE);
			else if(this.phone1Textfield.getText().length()==0 || this.phone2Textfield.getText().length()==0 || this.phone3Textfield.getText().length()==0)
				JOptionPane.showMessageDialog(null, "��ȭ��ȣ�� �Է��Ͻʽÿ�.", "", JOptionPane.ERROR_MESSAGE);
			else if(addr.length()==0)
				JOptionPane.showMessageDialog(null, "�ּҸ� �Է��Ͻʽÿ�.", "", JOptionPane.ERROR_MESSAGE);
			else if(name.length()>10)
				JOptionPane.showMessageDialog(null, "�̸��� ���̰� �ʹ� ��ϴ�.", "", JOptionPane.ERROR_MESSAGE);
			else if(id.length()>11)
				JOptionPane.showMessageDialog(null, "id�� ���̰�  �ʹ� ��ϴ�.", "", JOptionPane.ERROR_MESSAGE);
			else if(Integer.parseInt(this.yearTextfield.getText())>9999 || Integer.parseInt(this.monthTextfield.getText())>12 || Integer.parseInt(this.dateTextfield.getText())>31)
				JOptionPane.showMessageDialog(null, "�Ի����� �ٸ��� �Է��Ͻʽÿ�.", "", JOptionPane.ERROR_MESSAGE);
			else if(this.phone1Textfield.getText().length()>4 || this.phone2Textfield.getText().length()>4 || this.phone3Textfield.getText().length()>4)
				JOptionPane.showMessageDialog(null, "��ȭ��ȣ�� �ٸ��� �Է��Ͻʽÿ�.", "", JOptionPane.ERROR_MESSAGE);
			else if(addr.length()>50)
				JOptionPane.showMessageDialog(null, "�ּ��� ���̰�  �ʹ� ��ϴ�.", "", JOptionPane.ERROR_MESSAGE);
			
			else{
				Trainer trainer = new Trainer(id, loginId, pwd, name, rDate, phone, addr, salary);
				Member member = new Member(id, loginId, pwd, position);
				
				try {
					filemanager.addTrainer(trainer);
					filemanager.addMember(member);
					JOptionPane.showMessageDialog(null, "��ϵǾ����ϴ�.");
					dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null, "DB�����߻�");
					dispose();
					e1.printStackTrace();
				}
			}
		}
	}
	

	// DB ID create
	public String createId() {
		String id = null;
		Calendar cal = Calendar.getInstance();

		id = "" + cal.get(Calendar.HOUR_OF_DAY) + cal.get(Calendar.MINUTE)
				+ cal.get(Calendar.SECOND);

		return id;
	}

	// loginID create
	public String createLoginId(String phone) {
		String loginId = null;
		String[] id = phone.split("-");

		loginId = id[0] + id[1] + id[2];

		return loginId;
	}

	// password create
	public String createPwd() {
		String pwd = null;
		
		pwd = "1234";
		
		return pwd;
	}
	
	// salary create
	public int createSalary() {
		int salary;
		
		salary = 100;
		
		return salary;
	}
}
