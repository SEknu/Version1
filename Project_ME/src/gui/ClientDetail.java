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
	int regi_date_year, regi_date_month, regi_date_date;		//��ϳ�¥
	int exper_date_year, exper_date_month, exper_date_date;		//���ᳯ¥
	
	JButton Button_Cancel = new JButton("�ݱ�");
	
	public ClientDetail(Client clt) {
		JPanel panel = new JPanel();
		JPanel[] pan = new JPanel[13];
		Button_Cancel.addActionListener(this);
		for(int i=0; i<13; i++)
			pan[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		//DB���� �´����� �ҷ��ͼ� �����ֱ� �ڼ���
		pan[0].add(new JLabel("�̸� : " + clt.getName()));
		pan[1].add(new JLabel("���� : " + clt.getAge()));
		pan[2].add(new JLabel("�ּ� : " + clt.getAddress()));
		pan[3].add(new JLabel("��ȭ��ȣ : " + clt.getPhone()));
		pan[4].add(new JLabel("Ű : " + clt.getHeight()));
		pan[5].add(new JLabel("������ : " + clt.getWeight()));
		pan[6].add(new JLabel("������ : " + clt.getBodyMuscle()));
		pan[7].add(new JLabel("��ϳ�¥ : " + clt.getRegistDate()));
		pan[8].add(new JLabel("���ᳯ¥ : " + clt.getTerminateDate()));
		pan[9].add(new JLabel("������� : " + clt.getCurrentStatus()));
		pan[10].add(new JLabel("�⼮�ϼ� : " + clt.getAttendance()));
		pan[11].add(new JLabel("ȸ����� : " + clt.getGrade()));
		pan[12].add(new JLabel("Ư�̻��� : " + clt.getNote()));
		
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
