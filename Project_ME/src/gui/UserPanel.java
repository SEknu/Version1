package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import object.Client;

public class UserPanel extends JPanel implements ActionListener{

	private String name;
	private String phone;
	private int height;
	private int weight;
	private int bodyFat;
	private int bodyMuscle;
	private int regiDateYear;
	private int regiDateMonth;
	private int regiDateDate;
	private int experDateYear;
	private int experDateMonth;
	private int experDateDate;
	private String currentStatus;
		
	public UserPanel(Client loginClient) throws ClassNotFoundException, SQLException {
		
		name = loginClient.getName();
		phone = loginClient.getPhone();
		height = loginClient.getHeight();
		weight = loginClient.getWeight();
		bodyFat = loginClient.getBodyFat();
		bodyMuscle = loginClient.getBodyMuscle();
		if(loginClient.isCurrentStatus()==0)
			currentStatus = "��� ���� �ƴմϴ�.";
		else
			currentStatus = "��� ���Դϴ�.";
		
		if (loginClient.getRegistDate() != null) {
			String date = loginClient.getRegistDate();
			this.regiDateYear = Integer.valueOf(date.substring(0, 4));
			this.regiDateMonth = Integer.valueOf(date.substring(5, 7));
			this.regiDateDate = Integer.valueOf(date.substring(8, 10));
		}
		if (loginClient.getTerminateDate() != null) {
			String date = loginClient.getTerminateDate();
			this.experDateYear = Integer.valueOf(date.substring(0, 4));
			this.experDateMonth = Integer.valueOf(date.substring(5, 7));
			this.experDateDate = Integer.valueOf(date.substring(8, 10));
		}
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panel5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panel6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panel7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panel8 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panel9 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		panel1.add(new JLabel(name + "���� ����"));
		panel2.add(new JLabel("�޴�����ȣ"));
		panel2.add(new JLabel(phone));
		
		panel3.add(new JLabel("Ű"));
		panel3.add(new JLabel(height + "cm"));
		
		panel4.add(new JLabel("������"));
		panel4.add(new JLabel(weight + "kg"));
		
		panel5.add(new JLabel("������"));
		panel5.add(new JLabel(bodyMuscle + "kg"));
		
		panel6.add(new JLabel("ü����"));
		panel6.add(new JLabel(bodyFat + "%"));
		
		panel7.add(new JLabel("�����"));
		panel7.add(new JLabel(regiDateYear + "�� " + regiDateMonth + "�� " + regiDateDate + "��"));
		
		panel8.add(new JLabel("������"));
		panel8.add(new JLabel(experDateYear + "�� " + experDateMonth + "�� " + experDateDate + "��"));
		
		panel9.add(new JLabel(currentStatus));
		
		
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		panel.add(panel4);
		panel.add(panel5);
		panel.add(panel6);
		panel.add(panel7);
		panel.add(panel8);
		panel.add(panel9);
		
		add(panel);
		//pack();
		//setResizable(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean logout = false;
		
		/*if (e.getSource() == Logout) {
			logout = FileManager.LogOut(lg.getText());
			
			if (logout == true)
				dispose();
		}*/
	}
}