package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import object.Client;

public class ClientDetail extends JDialog implements ActionListener {

	String name, address, phone, state;
	int age, height, weight, muscle, attend, etc, grade;
	int regi_date_year, regi_date_month, regi_date_date;		//등록날짜
	int exper_date_year, exper_date_month, exper_date_date;		//만료날짜
	
	JButton Button_Cancel = new JButton("닫기");
	
	public ClientDetail(Client clt) {
		JPanel panel = new JPanel();
		JPanel[] pan = new JPanel[13];
		Button_Cancel.addActionListener(this);
		for(int i=0; i<13; i++)
			pan[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		//DB에서 맞는정보 불러와서 보여주기 ★수정
		pan[0].add(new JLabel("이름 : " + clt.getName()));
		pan[1].add(new JLabel("나이 : " + clt.getAge()));
		pan[2].add(new JLabel("주소 : " + clt.getAddress()));
		pan[3].add(new JLabel("전화번호 : " + clt.getPhone()));
		pan[4].add(new JLabel("키 : " + clt.getHeight()));
		pan[5].add(new JLabel("몸무게 : " + clt.getWeight()));
		pan[6].add(new JLabel("근육량 : " + clt.getBodyMuscle()));
		pan[7].add(new JLabel("등록날짜 : " + clt.getRegistDate()));
		pan[8].add(new JLabel("만료날짜 : " + clt.getTerminateDate()));
		pan[9].add(new JLabel("현재상태 : " + clt.getCurrentStatus()));
		pan[10].add(new JLabel("출석일수 : " + clt.getAttendance()));
		pan[11].add(new JLabel("회원등급 : " + clt.getGrade()));
		pan[12].add(new JLabel("특이사항 : " + clt.getNote()));
		
		for(int i=0; i<13; i++)
			panel.add(pan[i]);
		
		panel.add(Button_Cancel);
	//	setSize(550, 600);
		add(panel);
		pack();
		setModal(true);
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
	}

}
