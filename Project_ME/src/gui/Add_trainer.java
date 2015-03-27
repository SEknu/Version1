package gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import object.Trainer;

public class Add_trainer extends JDialog implements ActionListener{
	JButton Button_OK = new JButton("���");
	JButton button_Cancel = new JButton("���");
		
	JTextField tra_name = new JTextField(5);
	JTextField tra_ID = new JTextField(5);
	
	JTextField start_Year = new JTextField(5);
	JTextField start_Month = new JTextField(5);
	JTextField start_Date = new JTextField(5);

	JTextField tra_age = new JTextField(2);
	JTextField tra_phone1 = new JTextField(4);
	JTextField tra_phone2 = new JTextField(4);
	JTextField tra_phone3 = new JTextField(4);
	JTextField tra_addr = new JTextField(10);
		
	public Add_trainer() {
		Button_OK.addActionListener(this);
		button_Cancel.addActionListener(this);
		
		setLayout(new GridLayout(7, 2));
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
		
		add(new JLabel("����"));
		add(tra_age);
		
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
			int age = Integer.valueOf(this.tra_age.getText());
			String phone = this.tra_phone1.getText() + this.tra_phone2.getText() + this.tra_phone3.getText();
			String addr = this.tra_addr.getText();
			
			Trainer obj = new Trainer(id, rDate, name, age, addr, phone);
			
			int result = database.FileManager.getInstance().insert(obj, "trainer");
			
			if (result != -1)
				JOptionPane.showMessageDialog(null, "����");
			else
				JOptionPane.showMessageDialog(null, "����");
		}
		
		dispose();
	}
	
}
