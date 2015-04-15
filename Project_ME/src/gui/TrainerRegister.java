package gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import object.Trainer;
import database.FileManager;

public class TrainerRegister extends JDialog implements ActionListener{
	JButton Button_OK = new JButton("���");
	JButton button_Cancel = new JButton("���");
		
	JTextField tra_name = new JTextField(5);
	JTextField tra_ID = new JTextField(5);
	
	JTextField start_Year = new JTextField(5);
	JTextField start_Month = new JTextField(5);
	JTextField start_Date = new JTextField(5);

	//JTextField tra_age = new JTextField(2);
	JTextField tra_phone1 = new JTextField(4);
	JTextField tra_phone2 = new JTextField(4);
	JTextField tra_phone3 = new JTextField(4);
	JTextField tra_addr = new JTextField(10);
	
	FileManager filemanager = FileManager.getInstance();
		
	public TrainerRegister() {
		Button_OK.addActionListener(this);
		button_Cancel.addActionListener(this);
		
		setLayout(new GridLayout(6, 2));
		JPanel panel1 = new JPanel(new FlowLayout());
		JPanel panel2 = new JPanel(new FlowLayout());
		
		add(new JLabel("�̸�"));
		add(tra_name);

		add(new JLabel("ID"));
		add(tra_ID);

		add(new JLabel("�Ի���"));
		panel1.add(start_Year);
		panel1.add(start_Month);
		panel1.add(start_Date);
		add(panel1);
		
		//add(new JLabel("����"));
		//add(tra_age);
		
		add(new JLabel("��ȭ��ȣ"));
		panel2.add(tra_phone1);
		panel2.add(new JLabel("-"));
		panel2.add(tra_phone2);
		panel2.add(new JLabel("-"));
		panel2.add(tra_phone3);
		add(panel2);
		
		add(new JLabel("�ּ�"));
		add(tra_addr);
				
		add(Button_OK);
		add(button_Cancel);
		
		pack();
		setModal(true);
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Button_OK) {
			
			String name = this.tra_name.getText();
			String id = this.tra_ID.getText();
			String rDate = this.start_Year.getText() + "-" + this.start_Month.getText() + "-" + this.start_Date.getText();
			//int age = Integer.valueOf(this.tra_age.getText());
			String phone = this.tra_phone1.getText() + this.tra_phone2.getText() + this.tra_phone3.getText();
			String addr = this.tra_addr.getText();
		
			//���� or ���˿� ��߳� �Է� ����ó��
			if(name.length()==0)
				JOptionPane.showMessageDialog(null, "�̸��� �Է��Ͻʽÿ�.", "", JOptionPane.ERROR_MESSAGE);
			else if(id.length()==0)
				JOptionPane.showMessageDialog(null, "id�� �Է��Ͻʽÿ�.", "", JOptionPane.ERROR_MESSAGE);
			else if(this.start_Year.getText().length()==0 || this.start_Month.getText().length()==0 || this.start_Date.getText().length()==0)
				JOptionPane.showMessageDialog(null, "�Ի����� �Է��Ͻʽÿ�.", "", JOptionPane.ERROR_MESSAGE);
			else if(this.tra_phone1.getText().length()==0 || this.tra_phone2.getText().length()==0 || this.tra_phone3.getText().length()==0)
				JOptionPane.showMessageDialog(null, "��ȭ��ȣ�� �Է��Ͻʽÿ�.", "", JOptionPane.ERROR_MESSAGE);
			else if(addr.length()==0)
				JOptionPane.showMessageDialog(null, "�ּҸ� �Է��Ͻʽÿ�.", "", JOptionPane.ERROR_MESSAGE);
			else if(name.length()>10)
				JOptionPane.showMessageDialog(null, "�̸��� ���̰� �ʹ� ��ϴ�.", "", JOptionPane.ERROR_MESSAGE);
			else if(id.length()>11)
				JOptionPane.showMessageDialog(null, "id�� ���̰�  �ʹ� ��ϴ�.", "", JOptionPane.ERROR_MESSAGE);
			else if(Integer.parseInt(this.start_Year.getText())>9999 || Integer.parseInt(this.start_Month.getText())>12 || Integer.parseInt(this.start_Date.getText())>31)
				JOptionPane.showMessageDialog(null, "�Ի����� �ٸ��� �Է��Ͻʽÿ�.", "", JOptionPane.ERROR_MESSAGE);
			else if(this.tra_phone1.getText().length()>4 || this.tra_phone2.getText().length()>4 || this.tra_phone3.getText().length()>4)
				JOptionPane.showMessageDialog(null, "��ȭ��ȣ�� �ٸ��� �Է��Ͻʽÿ�.", "", JOptionPane.ERROR_MESSAGE);
			else if(addr.length()>50)
				JOptionPane.showMessageDialog(null, "�ּ��� ���̰�  �ʹ� ��ϴ�.", "", JOptionPane.ERROR_MESSAGE);
			
			else{
				Trainer trainer = new Trainer(id, rDate, name, addr, phone);
				
				try {
					filemanager.addTrainer(trainer);
					JOptionPane.showMessageDialog(null, "��ϵǾ����ϴ�.");
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null, "���� id�� �����մϴ�.");
					dispose();
					e1.printStackTrace();
				}
				
			}
			
		}
	}
	
}