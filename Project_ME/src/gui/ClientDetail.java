package gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import object.Client;

public class ClientDetail extends JDialog implements ActionListener {

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
				termiDatePanel.getComponent(i).setEnabled(true);
			}
			Button_addProgram.setEnabled(true);
			Button_addTrainer.setEnabled(true);
			Button_Save.setEnabled(true);
			
		}
		else if(e.getSource() == Button_Save) {
			JTextField nameTextField = (JTextField)contentPanel.getComponent(1);
			System.out.println(nameTextField.getText());
			/*
			clt.setName(name);
			clt.setBirthday(birthday);
			clt.setAge(age);
			clt.setPhone(phone);
			clt.setAddress(address);
			clt.setHeight(height);
			clt.setWeight(weight);
			clt.setBodyFat(bodyFat);
			clt.setBodyMuscle(bodyMuscle);
			clt.setTrainer(trainer);
			clt.setProgram(program);
			clt.setRegistDate(registDate);
			clt.setTerminateDate(terminateDate);
			clt.setRegistperiod(registperiod);
			clt.setNote(note);
			if()
			clt.setCurrentStatus(currentStatus);
			*/
		}
		else if(e.getSource() == Button_Cancel) {
			
		}
		else if(e.getSource() == Button_addTrainer) {
			
		}
		else if(e.getSource() == Button_addProgram) {
			
		}
	}
	

}
