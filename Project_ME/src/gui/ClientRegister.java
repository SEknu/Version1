package gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import object.Client;
import database.FileManager;

public class ClientRegister extends JDialog implements ActionListener {

	private JButton register = new JButton("등록");
	private JButton cancel = new JButton("취소");

	private JTextField JTName = new JTextField(10);
	private JTextField JTRegisterYear = new JTextField(4);
	private JTextField JTRegisterMonth = new JTextField(2);
	private JTextField JTRegisterDay = new JTextField(2);
	private JTextField JTBirthdayYear = new JTextField(4);
	private JTextField JTBirthdayMonth = new JTextField(2);
	private JTextField JTBirthdayDay = new JTextField(2);
	private JTextField JTWeight = new JTextField(5);
	private JTextField JTHeight = new JTextField(5);
	private JTextField JTBodyFat = new JTextField(5);
	private JTextField JTBodyMuscle = new JTextField(5);
	private JTextField JTPhone1 = new JTextField(3);
	private JTextField JTPhone2 = new JTextField(4);
	private JTextField JTPhone3 = new JTextField(4);
	private JTextField JTAddress1 = new JTextField(20);
	// private JTextField JTAddress2 = new JTextField(10);
	private JTextField JTNote = new JTextField(20);
	private JComboBox<String> registPeriodComboBox = new JComboBox<String>();

	public ClientRegister() {
		register.addActionListener(this);
		cancel.addActionListener(this);

		JPanel homePanel = new JPanel();
		homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.Y_AXIS));
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(11, 2));
		JPanel registDataPanel = new JPanel();
		registDataPanel.setLayout(new FlowLayout());
		JPanel birthdayPanel = new JPanel();
		birthdayPanel.setLayout(new FlowLayout());
		JPanel phoneNumPanel = new JPanel();
		phoneNumPanel.setLayout(new FlowLayout());
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		registPeriodComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "1개월", "2개월", "3개월", "6개월", "12개월" }));

		mainPanel.add(new JLabel("등록일"));
		registDataPanel.add(JTRegisterYear);
		registDataPanel.add(new JLabel("년"));
		registDataPanel.add(JTRegisterMonth);
		registDataPanel.add(new JLabel("월"));
		registDataPanel.add(JTRegisterDay);
		registDataPanel.add(new JLabel("일"));
		mainPanel.add(registDataPanel);

		mainPanel.add(new JLabel("등록기간"));
		mainPanel.add(registPeriodComboBox);

		mainPanel.add(new JLabel("이름"));
		mainPanel.add(JTName);

		mainPanel.add(new JLabel("생일"));
		birthdayPanel.add(JTBirthdayYear);
		birthdayPanel.add(new JLabel("년"));
		birthdayPanel.add(JTBirthdayMonth);
		birthdayPanel.add(new JLabel("월"));
		birthdayPanel.add(JTBirthdayDay);
		birthdayPanel.add(new JLabel("일"));
		mainPanel.add(birthdayPanel);

		mainPanel.add(new JLabel("휴대폰번호"));
		phoneNumPanel.add(JTPhone1);
		phoneNumPanel.add(new JLabel("-"));
		phoneNumPanel.add(JTPhone2);
		phoneNumPanel.add(new JLabel("-"));
		phoneNumPanel.add(JTPhone3);
		mainPanel.add(phoneNumPanel);

		mainPanel.add(new JLabel("주소"));
		mainPanel.add(JTAddress1);

		mainPanel.add(new JLabel("키"));
		mainPanel.add(JTHeight);

		mainPanel.add(new JLabel("몸무게"));
		mainPanel.add(JTWeight);

		mainPanel.add(new JLabel("체지방량"));
		mainPanel.add(JTBodyFat);

		mainPanel.add(new JLabel("근육량"));
		mainPanel.add(JTBodyMuscle);

		mainPanel.add(new JLabel("비고"));
		mainPanel.add(JTNote);
		
		buttonPanel.add(register);
		buttonPanel.add(cancel);

		homePanel.add(mainPanel);
		homePanel.add(buttonPanel);

		getContentPane().add(homePanel);
		setResizable(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == register) {
			// Calendar calendar = Calendar.getInstance();

			String id = createDBId();
			String registDate = JTRegisterYear.getText() + "-"
					+ JTRegisterMonth.getText() + "-" + JTRegisterDay.getText();
			String registPeriod = registPeriodComboBox.getSelectedItem()
					.toString();
			String terminateDate = calculateTerminateDate(registDate,
					registPeriod);
			int height = Integer.parseInt(JTHeight.getText());
			int weight = Integer.parseInt(JTWeight.getText());
			int bodyFat = Integer.parseInt(JTBodyFat.getText());
			int bodyMuscle = Integer.parseInt(JTBodyMuscle.getText());
			String note = JTNote.getText();
			String name = JTName.getText();
			int age = calculateAge(Integer.parseInt(JTBirthdayYear.getText()));
			String birthday = JTBirthdayYear.getText() + "-"
					+ JTBirthdayMonth.getText() + "-" + JTBirthdayDay.getText();
			String address = JTAddress1.getText();
			String phone = JTPhone1.getText() + "-" + JTPhone2.getText() + "-"
					+ JTPhone3.getText();
			String loginId = createClientId(phone);
			String pwd = createClientPwd();

			Client client = new Client(id, loginId, pwd, registDate,
					registPeriod, terminateDate, name, age, birthday, address,
					phone, height, weight, bodyFat, bodyMuscle, note);

			try {
				database.FileManager.getInstance().addClient(client);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			dispose();
		} else if (e.getSource() == cancel) {
			dispose();
		}
	}

	// DB ID create
	public String createDBId() {
		String id = null;
		Calendar cal = Calendar.getInstance();

		id = "" + cal.get(Calendar.HOUR_OF_DAY) + cal.get(Calendar.MINUTE)
				+ cal.get(Calendar.SECOND);

		return id;

	}

	// Client ID create
	public String createClientId(String phone) {
		String clientId = null;
		String[] id = phone.split("-");

		clientId = id[0] + id[1] + id[2];

		return clientId;

	}

	// Client password create
	public String createClientPwd() {
		String clientPwd = null;
		clientPwd = "1234";
		return clientPwd;
	}

	// 나이 계산
	public int calculateAge(int birthdayYear) {
		int age = 0;

		Calendar cal = Calendar.getInstance();
		age = cal.get(Calendar.YEAR) - birthdayYear;

		return age;
	}

	// 만료일자 계산 - 등록기간은 개월단위
	public String calculateTerminateDate(String registDate, String registPeriod) {
		String terminateDate = null;

		String[] registdate = registDate.split("-");
		String[] period = registPeriod.split("개월");
		int registYear = Integer.parseInt(registdate[0]);
		int registMonth = Integer.parseInt(registdate[1]);
		int registperiod = Integer.parseInt(period[0]);
		int result = registMonth + registperiod;
		if (result > 12) {
			registYear += 1;
			result -= 12;
		}
		registdate[0] = Integer.toString(registYear);
		registdate[1] = Integer.toString(result);

		terminateDate = registdate[0] + "-" + registdate[1] + "-"
				+ registdate[2];
		return terminateDate;
	}
}
