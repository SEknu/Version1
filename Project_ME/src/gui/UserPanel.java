package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import object.Client;

public class UserPanel extends JPanel implements ActionListener {

	Vector<Client> vectorClient;
	GuiProcess gui;
	Client client;

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
	private JButton addButton = new JButton("연장하기");

	public UserPanel(Client loginClient) throws ClassNotFoundException,
			SQLException {

		client = loginClient;
		name = loginClient.getName();
		phone = loginClient.getPhone();
		height = loginClient.getHeight();
		weight = loginClient.getWeight();
		bodyFat = loginClient.getBodyFat();
		bodyMuscle = loginClient.getBodyMuscle();
		if (loginClient.isCurrentStatus() == 0)
			currentStatus = "현재 미등록 상태입니다.";
		else
			currentStatus = "현재 등록 상태입니다.";

		if (loginClient.getRegistDate() != null) {
			String date = loginClient.getRegistDate();
			this.regiDateYear = Integer.valueOf(date.substring(0, 4));
			this.regiDateMonth = Integer.valueOf(date.substring(5, 7));
			this.regiDateDate = Integer.valueOf(date.substring(8, 10));
		}
		if (loginClient.getTerminateDate() != null) {
			String date = loginClient.getTerminateDate();
			System.out.println(date);
			this.experDateYear = Integer.valueOf(date.substring(0, 4));
			System.out.println(experDateYear);
			this.experDateMonth = Integer.valueOf(date.substring(5, 7));
			System.out.println(experDateMonth);
			this.experDateDate = Integer.valueOf(date.substring(8, 10));
			System.out.println(experDateDate);
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
		JPanel space = new JPanel(new FlowLayout(FlowLayout.CENTER));

		panel1.add(new JLabel(name + "님의 정보"));
		panel2.add(new JLabel("휴대폰  "));
		panel2.add(new JLabel(phone));
		panel3.add(new JLabel("신    장 　　"));
		panel3.add(new JLabel(height + "cm"));
		panel4.add(new JLabel("체    중  　　"));
		panel4.add(new JLabel(weight + "kg"));
		panel5.add(new JLabel("근육량 　　"));
		panel5.add(new JLabel(bodyMuscle + "kg"));
		panel6.add(new JLabel("체지방 　　"));
		panel6.add(new JLabel(bodyFat + "%"));
		panel7.add(new JLabel("등록일 "));
		panel7.add(new JLabel(regiDateYear + "년 " + regiDateMonth + "월 "
				+ regiDateDate + "일"));
		panel8.add(new JLabel("만료일 "));
		panel8.add(new JLabel(experDateYear + "년 " + experDateMonth + "월 "
				+ experDateDate + "일"));
		panel9.add(new JLabel(currentStatus));

		addButton.addActionListener(this);

		panel.add(panel1);
		panel.add(space);
		panel.add(panel2);
		panel.add(panel3);
		panel.add(panel4);
		panel.add(panel5);
		panel.add(panel6);
		panel.add(panel7);
		panel.add(panel8);
		panel.add(space);
		panel.add(panel9);
		panel.add(addButton);

		add(panel);
		// pack();
		// setResizable(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == addButton) {
			new UserAddRegiDay(client);
			// try {
			// / vectorClient = gui.getClient();
			// } catch (ClassNotFoundException | SQLException e1) {
			// JOptionPane.showMessageDialog(null, "데이터베이스 오류");
			// }
			// ///////////★바로 패치
		}
	}
}