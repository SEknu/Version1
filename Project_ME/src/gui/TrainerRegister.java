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
	JButton okButton = new JButton("등록");
	JButton cancelButton = new JButton("취소");
		
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
		
		add(new JLabel("이름"));
		add(nameTextfield);

		add(new JLabel("입사일"));
		panel1.add(yearTextfield);
		panel1.add(monthTextfield);
		panel1.add(dateTextfield);
		add(panel1);
		
		add(new JLabel("전화번호"));
		panel2.add(phone1Textfield);
		panel2.add(new JLabel("-"));
		panel2.add(phone2Textfield);
		panel2.add(new JLabel("-"));
		panel2.add(phone3Textfield);
		add(panel2);
		
		add(new JLabel("주소"));
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
		
			//공란 or 포맷에 어긋난 입력 예외처리
			if(name.length()==0)
				JOptionPane.showMessageDialog(null, "이름을 입력하십시오.", "", JOptionPane.ERROR_MESSAGE);
			else if(id.length()==0)
				JOptionPane.showMessageDialog(null, "id를 입력하십시오.", "", JOptionPane.ERROR_MESSAGE);
			else if(this.yearTextfield.getText().length()==0 || this.monthTextfield.getText().length()==0 || this.dateTextfield.getText().length()==0)
				JOptionPane.showMessageDialog(null, "입사일을 입력하십시오.", "", JOptionPane.ERROR_MESSAGE);
			else if(this.phone1Textfield.getText().length()==0 || this.phone2Textfield.getText().length()==0 || this.phone3Textfield.getText().length()==0)
				JOptionPane.showMessageDialog(null, "전화번호를 입력하십시오.", "", JOptionPane.ERROR_MESSAGE);
			else if(addr.length()==0)
				JOptionPane.showMessageDialog(null, "주소를 입력하십시오.", "", JOptionPane.ERROR_MESSAGE);
			else if(name.length()>10)
				JOptionPane.showMessageDialog(null, "이름의 길이가 너무 깁니다.", "", JOptionPane.ERROR_MESSAGE);
			else if(id.length()>11)
				JOptionPane.showMessageDialog(null, "id의 길이가  너무 깁니다.", "", JOptionPane.ERROR_MESSAGE);
			else if(Integer.parseInt(this.yearTextfield.getText())>9999 || Integer.parseInt(this.monthTextfield.getText())>12 || Integer.parseInt(this.dateTextfield.getText())>31)
				JOptionPane.showMessageDialog(null, "입사일을 바르게 입력하십시오.", "", JOptionPane.ERROR_MESSAGE);
			else if(this.phone1Textfield.getText().length()>4 || this.phone2Textfield.getText().length()>4 || this.phone3Textfield.getText().length()>4)
				JOptionPane.showMessageDialog(null, "전화번호를 바르게 입력하십시오.", "", JOptionPane.ERROR_MESSAGE);
			else if(addr.length()>50)
				JOptionPane.showMessageDialog(null, "주소의 길이가  너무 깁니다.", "", JOptionPane.ERROR_MESSAGE);
			
			else{
				Trainer trainer = new Trainer(id, loginId, pwd, name, rDate, phone, addr, salary);
				Member member = new Member(id, loginId, pwd, position);
				
				try {
					filemanager.addTrainer(trainer);
					filemanager.addMember(member);
					JOptionPane.showMessageDialog(null, "등록되었습니다.");
					dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null, "DB문제발생");
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
