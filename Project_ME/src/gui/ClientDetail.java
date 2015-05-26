package gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.FileManager;
import object.Client;
import object.Program;
import object.Trainer;

public class ClientDetail extends JDialog implements ActionListener {

	FileManager filemanager;
	Client clt;
	String name, address, phone, state;
	int age, height, weight, muscle, attend, etc, grade;
	int regi_date_year, regi_date_month, regi_date_date;		//등록날짜
	int exper_date_year, exper_date_month, exper_date_date;		//만료날짜
	
	JButton Button_Cancel = new JButton("취소");
	JButton Button_Save = new JButton("저장");
	JButton Button_Modify = new JButton("수정");
	JButton Button_addTrainer = new JButton("트레이너배정");
	JButton Button_addProgram = new JButton("프로그램배정");
	JPanel backgroundPanel = new JPanel();
	JPanel contentPanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	JPanel phonePanel = new JPanel();
	JPanel birthdayPanel = new JPanel();
	JPanel regiDatePanel = new JPanel();
	JPanel termiDatePanel = new JPanel();
	
	public ClientDetail(Client clt) {
		filemanager = FileManager.getInstance();
		this.clt = clt;
		
		backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS));
		contentPanel.setLayout(new GridLayout(16,2));
		buttonPanel.setLayout(new FlowLayout());
		birthdayPanel.setLayout(new FlowLayout());
		regiDatePanel.setLayout(new FlowLayout());
		termiDatePanel.setLayout(new FlowLayout());
		phonePanel.setLayout(new FlowLayout());
		
		buttonPanel.add(Button_Modify);
		buttonPanel.add(Button_addTrainer);
		buttonPanel.add(Button_addProgram);
		buttonPanel.add(Button_Save);
		buttonPanel.add(Button_Cancel);
		
		Button_Modify.addActionListener(this);
		Button_addTrainer.addActionListener(this);
		Button_addProgram.addActionListener(this);
		Button_Save.addActionListener(this);
		Button_Cancel.addActionListener(this);
		
		contentPanel.add(new JLabel("이름:",JLabel.CENTER));
		contentPanel.add(new JTextField(clt.getName()));
		
		contentPanel.add(new JLabel("생년월일:",JLabel.CENTER));
		String[] birthday = clt.getBirthday().split("-");
		birthdayPanel.add(new JTextField(birthday[0],6));
		birthdayPanel.add(new JLabel("-"));
		birthdayPanel.add(new JTextField(birthday[1],6));
		birthdayPanel.add(new JLabel("-"));
		birthdayPanel.add(new JTextField(birthday[2],6));
		contentPanel.add(birthdayPanel);
		
		contentPanel.add(new JLabel("나이:",JLabel.CENTER));
		contentPanel.add(new JTextField(""+clt.getAge()));
		
		contentPanel.add(new JLabel("휴대폰 번호:",JLabel.CENTER));
		String[] phoneNumber = clt.getPhone().split("-");
		phonePanel.add(new JTextField(phoneNumber[0],6));
		phonePanel.add(new JLabel("-"));
		phonePanel.add(new JTextField(phoneNumber[1],6));
		phonePanel.add(new JLabel("-"));
		phonePanel.add(new JTextField(phoneNumber[2],6));
		contentPanel.add(phonePanel);
		
		contentPanel.add(new JLabel("주소:",JLabel.CENTER));
		contentPanel.add(new JTextField(clt.getAddress()));
		
		contentPanel.add(new JLabel("키:",JLabel.CENTER));
		contentPanel.add(new JTextField(""+clt.getHeight()));
		
		contentPanel.add(new JLabel("몸무게:",JLabel.CENTER));
		contentPanel.add(new JTextField(""+clt.getWeight()));
		
		contentPanel.add(new JLabel("체지방량:",JLabel.CENTER));
		contentPanel.add(new JTextField(""+clt.getBodyFat()));
		
		contentPanel.add(new JLabel("근육량:",JLabel.CENTER));
		contentPanel.add(new JTextField(""+clt.getBodyMuscle()));
		
		contentPanel.add(new JLabel("배정트레이너:",JLabel.CENTER));
		contentPanel.add(new JTextField(clt.getTrainer()));
		
		contentPanel.add(new JLabel("배정프로그램:",JLabel.CENTER));
		contentPanel.add(new JTextField(clt.getProgram()));
		
		contentPanel.add(new JLabel("등록일자:",JLabel.CENTER));
		String[] regiDate = clt.getRegistDate().split("-");
		regiDatePanel.add(new JTextField(regiDate[0],6));
		regiDatePanel.add(new JLabel("-"));
		regiDatePanel.add(new JTextField(regiDate[1],6));
		regiDatePanel.add(new JLabel("-"));
		regiDatePanel.add(new JTextField(regiDate[2],6));
		contentPanel.add(regiDatePanel);

		contentPanel.add(new JLabel("만료일자:",JLabel.CENTER));
		String[] termiDate = clt.getTerminateDate().split("-");
		termiDatePanel.add(new JTextField(termiDate[0],6));
		termiDatePanel.add(new JLabel("-"));
		termiDatePanel.add(new JTextField(termiDate[1],6));
		termiDatePanel.add(new JLabel("-"));
		termiDatePanel.add(new JTextField(termiDate[2],6));
		contentPanel.add(termiDatePanel);
		
		contentPanel.add(new JLabel("등록기간:",JLabel.CENTER));
		contentPanel.add(new JTextField(clt.getRegistperiod()));
		
		contentPanel.add(new JLabel("특이사항:",JLabel.CENTER));
		contentPanel.add(new JTextField(clt.getComment()));
		
		contentPanel.add(new JLabel("현재등록여부:",JLabel.CENTER));
		if(clt.isCurrentStatus() == 1)
			contentPanel.add(new JTextField("등록"));
		else
			contentPanel.add(new JTextField("미등록"));
		
		backgroundPanel.add(contentPanel);
		backgroundPanel.add(buttonPanel);
		add(backgroundPanel);
		
		// 수정 못하도록 비활성화
		for(int i = 0; i < contentPanel.getComponentCount(); i++){
			if(i%2 != 0)
				contentPanel.getComponent(i).setEnabled(false);
		}
		for(int i = 0; i < 5; i++){
			phonePanel.getComponent(i).setEnabled(false);
			birthdayPanel.getComponent(i).setEnabled(false);
			regiDatePanel.getComponent(i).setEnabled(false);
			termiDatePanel.getComponent(i).setEnabled(false);
		}
		Button_addProgram.setEnabled(false);
		Button_addTrainer.setEnabled(false);
		Button_Save.setEnabled(false);
		
		pack();
		setModal(true);
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Button_Modify){
			for(int i = 0; i < contentPanel.getComponentCount(); i++){
				if(i%2 != 0)
					contentPanel.getComponent(i).setEnabled(true);
			}
			for(int i = 0; i < 5; i++){
				phonePanel.getComponent(i).setEnabled(true);
				birthdayPanel.getComponent(i).setEnabled(true);
				regiDatePanel.getComponent(i).setEnabled(true);
				//termiDatePanel.getComponent(i).setEnabled(true);
			}
			Button_addProgram.setEnabled(true);
			Button_addTrainer.setEnabled(true);
			Button_Save.setEnabled(true);
			
		}
		else if(e.getSource() == Button_Save) {
			JTextField nameTextField = (JTextField)contentPanel.getComponent(1);
			JTextField birthdayTextField1 = (JTextField)birthdayPanel.getComponent(0);
			JTextField birthdayTextField2 = (JTextField)birthdayPanel.getComponent(2);
			JTextField birthdayTextField3 = (JTextField)birthdayPanel.getComponent(4);
			JTextField ageTextField = (JTextField)contentPanel.getComponent(5);
			JTextField phoneTextField1 = (JTextField)phonePanel.getComponent(0);
			JTextField phoneTextField2 = (JTextField)phonePanel.getComponent(2);
			JTextField phoneTextField3 = (JTextField)phonePanel.getComponent(4);
			JTextField addrTextField = (JTextField)contentPanel.getComponent(9);
			JTextField heightTextField = (JTextField)contentPanel.getComponent(11);
			JTextField weightTextField = (JTextField)contentPanel.getComponent(13);
			JTextField bodyFatTextField = (JTextField)contentPanel.getComponent(15);
			JTextField bodyMuscleTextField = (JTextField)contentPanel.getComponent(17);
			JTextField trainerTextField = (JTextField)contentPanel.getComponent(19);
			JTextField programTextField = (JTextField)contentPanel.getComponent(21);
			JTextField regiDateTextField1 = (JTextField)regiDatePanel.getComponent(0);
			JTextField regiDateTextField2 = (JTextField)regiDatePanel.getComponent(2);
			JTextField regiDateTextField3 = (JTextField)regiDatePanel.getComponent(4);
			String registDate = regiDateTextField1.getText()+"-"+regiDateTextField2.getText()+"-"+regiDateTextField3.getText();
			JTextField regiPerTextField = (JTextField)contentPanel.getComponent(27);
			String terminateDate = calculateTerminateDate(registDate,regiPerTextField.getText());
			JTextField commentTextField = (JTextField)contentPanel.getComponent(29);
			JTextField registTextField = (JTextField)contentPanel.getComponent(31);
			
			clt.setName(nameTextField.getText());
			clt.setBirthday(birthdayTextField1.getText()+"-"+birthdayTextField2.getText()+"-"+birthdayTextField3.getText());
			clt.setAge(Integer.parseInt(ageTextField.getText()));
			clt.setPhone(phoneTextField1.getText()+"-"+phoneTextField2.getText()+"-"+phoneTextField3.getText());
			clt.setAddress(addrTextField.getText());
			clt.setHeight(Integer.parseInt(heightTextField.getText()));
			clt.setWeight(Integer.parseInt(weightTextField.getText()));
			clt.setBodyFat(Integer.parseInt(bodyFatTextField.getText()));
			clt.setBodyMuscle(Integer.parseInt(bodyMuscleTextField.getText()));
			clt.setTrainer(trainerTextField.getText());
			clt.setProgram(programTextField.getText());
			clt.setRegistDate(regiDateTextField1.getText());
			clt.setTerminateDate(terminateDate);
			clt.setRegistperiod(regiPerTextField.getText());
			clt.setComment(commentTextField.getText());
			if(registTextField.getText().equals("등록"))
				clt.setCurrentStatus(1);
			else
				clt.setCurrentStatus(0);
			// DB에 저장.
			try {
				filemanager.updateClient(clt);
				JOptionPane.showMessageDialog(null, "저장했습니다.");
				for(int i = 0; i < contentPanel.getComponentCount(); i++){
					if(i%2 != 0)
						contentPanel.getComponent(i).setEnabled(false);
				}
				for(int i = 0; i < 5; i++){
					phonePanel.getComponent(i).setEnabled(false);
					birthdayPanel.getComponent(i).setEnabled(false);
					regiDatePanel.getComponent(i).setEnabled(false);
				}
				Button_addProgram.setEnabled(false);
				Button_addTrainer.setEnabled(false);
				Button_Save.setEnabled(false);
			} catch (ClassNotFoundException | SQLException e1) {
				JOptionPane.showMessageDialog(null, "Database 저장실패");
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == Button_Cancel) {
			int result = JOptionPane.showConfirmDialog(null, "수정을 취소하시겠습니까?",
					null, JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.WARNING_MESSAGE, null);
			if (result == 0) {
				dispose();
			}
		}
		else if(e.getSource() == Button_addTrainer) {
			try {
				new ClientTrainer<Trainer>(filemanager.getTrainer("all"), clt);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == Button_addProgram) {
			try {
				new CleintProgram<Program>(filemanager.getProgram("all"), clt);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
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
	
	public void refresh()
	{
		
	}
}
