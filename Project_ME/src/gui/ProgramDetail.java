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
import object.Program;

public class ProgramDetail extends JDialog implements ActionListener {
	JButton Button_Save = new JButton("����"); //�����ư
	JButton Button_Cancel = new JButton("���"); //��ҹ�ư
	
	JPanel panel1 = new JPanel(), panel2 = new JPanel(), panel3 = new JPanel();
	
	JPanel[] pan = new JPanel[4];
	
	String programId;
			
	public ProgramDetail(Program program) {
		programId = program.getID();
		
		Button_Save.addActionListener(this);
		Button_Cancel.addActionListener(this);
		
		for(int i = 0; i<4; i++) {
			pan[i] = new JPanel(new GridLayout(1,2,8,8));
		}
		
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		panel2.setLayout(new FlowLayout());
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
	
		pan[0].add(new JLabel("�̸�"));
		pan[0].add(new JTextField(program.getName()));
		pan[1].add(new JLabel("���̵�"));
		pan[1].add(new JTextField(program.getDifficulty()));
		pan[2].add(new JLabel("�����"));
		pan[2].add(new JTextField(program.getPartOfBody()));
		pan[3].add(new JLabel("���"));
		pan[3].add(new JTextField(program.getComment()));
		
		for(int i = 0; i < 4; i++) {
			panel1.add(pan[i]);
		}
		
		panel2.add(Button_Save);
		panel2.add(Button_Cancel);
		panel3.add(panel1);
		panel3.add(panel2);
		
		add(panel3);

		pack();
		setModal(true);
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		//�����ư Ŭ����
		if(e.getSource() == Button_Save) {
			Program program = new Program();
			program.setID(programId);
			program.setName(((JTextField)pan[0].getComponent(1)).getText().toString());
			program.setDifficulty(((JTextField)pan[1].getComponent(1)).getText().toString());
			program.setPartOfBody(((JTextField)pan[2].getComponent(1)).getText().toString());
			program.setComment(((JTextField)pan[3].getComponent(1)).getText().toString());
			
			try {
				FileManager.getInstance().updateProgram(program);
				JOptionPane.showMessageDialog(null, "�����߽��ϴ�.");
				dispose();
			} catch (ClassNotFoundException | SQLException e1) {
				JOptionPane.showMessageDialog(null, "�������");
			}				
		}
		//��ҹ�ư Ŭ����.
		else if(e.getSource() == Button_Cancel) {
			int result = JOptionPane.showConfirmDialog(null, "������ ����Ͻðڽ��ϱ�?", null, JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
			if(result==0) {
				dispose();
			}
		}
	}
}
