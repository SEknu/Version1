package gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import object.Client;
import object.Member;

public class ClientRegister extends JDialog implements ActionListener {
	GuiProcess gui;
	
	private JButton registerButon = new JButton("등록");
	private JButton cancelButton = new JButton("취소");

	private JTextField nameTextField = new JTextField(10);
	private JTextField registerYearTextField = new JTextField(4);
	private JTextField registerMonthTextField = new JTextField(2);
	private JTextField registerDayTextField = new JTextField(2);
	private JTextField birthdayYearTextField = new JTextField(4);
	private JTextField birthdayMonthTextField = new JTextField(2);
	private JTextField birthdayDayTextField = new JTextField(2);
	private JTextField weightTextField = new JTextField(5);
	private JTextField heightTextField = new JTextField(5);
	private JTextField bodyFatTextField = new JTextField(5);
	private JTextField BodyMuscleTextField = new JTextField(5);
	private JTextField phone1TextField = new JTextField(3);
	private JTextField phone2TextField = new JTextField(4);
	private JTextField phone3TextField = new JTextField(4);
	private JTextField addressTextField = new JTextField(20);
	private JTextField commentTextField = new JTextField(20);
	private JComboBox<String> registPeriodComboBox = new JComboBox<String>();
	String birthday;
	
	public ClientRegister() {
		gui = GuiProcess.getInstance();
		
		registerButon.addActionListener(this);
		cancelButton.addActionListener(this);
		
		GregorianCalendar cal = new GregorianCalendar();
        String year = Integer.toString(cal.get(Calendar.YEAR));
        String month = Integer.toString(cal.get(Calendar.MONTH)+1);
        String day = Integer.toString(cal.get(Calendar.DATE));
        
        registerYearTextField = new JTextField(year,4);
    	registerMonthTextField = new JTextField(month,2);
    	registerDayTextField = new JTextField(day,2);
        
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
		registDataPanel.add(registerYearTextField);
		registDataPanel.add(new JLabel("년"));
		registDataPanel.add(registerMonthTextField);
		registDataPanel.add(new JLabel("월"));
		registDataPanel.add(registerDayTextField);
		registDataPanel.add(new JLabel("일"));
		mainPanel.add(registDataPanel);

		mainPanel.add(new JLabel("등록기간"));
		mainPanel.add(registPeriodComboBox);

		mainPanel.add(new JLabel("이름"));
		mainPanel.add(nameTextField);

		mainPanel.add(new JLabel("생일"));
		birthdayPanel.add(birthdayYearTextField);
		birthdayPanel.add(new JLabel("년"));
		birthdayPanel.add(birthdayMonthTextField);
		birthdayPanel.add(new JLabel("월"));
		birthdayPanel.add(birthdayDayTextField);
		birthdayPanel.add(new JLabel("일"));
		mainPanel.add(birthdayPanel);

		mainPanel.add(new JLabel("휴대폰번호"));
		phoneNumPanel.add(phone1TextField);
		phoneNumPanel.add(new JLabel("-"));
		phoneNumPanel.add(phone2TextField);
		phoneNumPanel.add(new JLabel("-"));
		phoneNumPanel.add(phone3TextField);
		mainPanel.add(phoneNumPanel);

		mainPanel.add(new JLabel("주소"));
		mainPanel.add(addressTextField);

		mainPanel.add(new JLabel("키"));
		mainPanel.add(heightTextField);

		mainPanel.add(new JLabel("몸무게"));
		mainPanel.add(weightTextField);

		mainPanel.add(new JLabel("체지방량"));
		mainPanel.add(bodyFatTextField);

		mainPanel.add(new JLabel("근육량"));
		mainPanel.add(BodyMuscleTextField);

		mainPanel.add(new JLabel("비고"));
		mainPanel.add(commentTextField);
		
		buttonPanel.add(registerButon);
		buttonPanel.add(cancelButton);

		homePanel.add(mainPanel);
		homePanel.add(buttonPanel);

		getContentPane().add(homePanel);
		
		pack();
		setModal(true);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == registerButon) {
			// Calendar calendar = Calendar.getInstance();

			String id = createDBId();
			String registDate = registerYearTextField.getText() + "-"
					+ registerMonthTextField.getText() + "-" + registerDayTextField.getText();
			String registPeriod = registPeriodComboBox.getSelectedItem()
					.toString();
			String terminateDate = calculateTerminateDate(registDate,
					registPeriod);
			int height = Integer.parseInt(heightTextField.getText());
			int weight = Integer.parseInt(weightTextField.getText());
			int bodyFat = Integer.parseInt(bodyFatTextField.getText());
			int bodyMuscle = Integer.parseInt(BodyMuscleTextField.getText());
			String note = commentTextField.getText();
			String name = nameTextField.getText();
			int age = calculateAge(Integer.parseInt(birthdayYearTextField.getText()));
			birthday = birthdayYearTextField.getText() + "-"
					+ birthdayMonthTextField.getText() + "-" + birthdayDayTextField.getText();
			String address = addressTextField.getText();
			String phone = phone1TextField.getText() + "-" + phone2TextField.getText() + "-"
					+ phone3TextField.getText();
			String loginId = createClientId(phone);
			String pwd = createClientPwd();
			String position = "client";

			Client client = new Client(id, loginId, pwd, registDate,
					registPeriod, terminateDate, name, age, birthday, address,
					phone, height, weight, bodyFat, bodyMuscle, note);
			Member member = new Member(id, loginId, pwd, position);
			
			try {
				gui.add(client);
				gui.add(member);
			} catch (ClassNotFoundException e1) {
				JOptionPane.showMessageDialog(null, "client, member 찾기 실패");
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "데이터베이스 저장 실패");
			}

			dispose();
		} else if (e.getSource() == cancelButton) {
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
		clientPwd = birthday;
		return clientPwd;
	}

	// 나이 계산
	public int calculateAge(int birthdayYear) {
		int age = 0;

		Calendar cal = Calendar.getInstance();
		age = cal.get(Calendar.YEAR) - birthdayYear;
		age += 1;
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
