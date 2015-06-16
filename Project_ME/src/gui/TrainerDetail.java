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

import object.Trainer;

public class TrainerDetail extends JDialog implements ActionListener {
	
	GuiProcess gui;
	Trainer trainer;
	
	JButton saveButton = new JButton("저장"); //저장버튼
	JButton cancelButton = new JButton("취소"); //취소버튼
	
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	
	JPanel[] pan = new JPanel[6];
			
	public TrainerDetail(Trainer trainer) {
		gui = GuiProcess.getInstance();
		this.trainer = trainer;
		
		saveButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		for(int i=0; i<6; i++) {
			pan[i] = new JPanel(new GridLayout(1,2,8,8));
		}
		
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		panel2.setLayout(new FlowLayout());
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
	
		pan[0].add(new JLabel("이름"));
		pan[0].add(new JTextField(trainer.getName()));
		pan[1].add(new JLabel("전화번호"));
		pan[1].add(new JTextField(trainer.getPhone()));
		pan[2].add(new JLabel("주소"));
		pan[2].add(new JTextField(trainer.getAddress()));
		pan[3].add(new JLabel("입사일"));
		pan[3].add(new JTextField(trainer.getRegistDate()));
		pan[4].add(new JLabel("월급"));
		pan[4].add(new JTextField(Integer.toString(trainer.getSalary())));
		pan[5].add(new JLabel("비고"));
		pan[5].add(new JTextField(trainer.getComment()));
		
		for(int i = 0; i < 6; i++) {
			panel1.add(pan[i]);
		}
		
		panel2.add(saveButton);
		panel2.add(cancelButton);
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

		//저장버튼 클릭시
		if(e.getSource() == saveButton) {
			JTextField nameField = (JTextField) pan[0].getComponent(1);
			JTextField phoneField = (JTextField) pan[1].getComponent(1);
			JTextField addressField = (JTextField) pan[2].getComponent(1);
			JTextField registDateField = (JTextField) pan[3].getComponent(1);
			JTextField salaryField = (JTextField) pan[4].getComponent(1);
			JTextField commentField = (JTextField) pan[5].getComponent(1);
			
			trainer.setName(nameField.getText());
			trainer.setPhone(phoneField.getText());
			trainer.setAddress(addressField.getText());
			trainer.setRegistDate(registDateField.getText());
			trainer.setSalary(Integer.parseInt(salaryField.getText()));
			trainer.setComment(commentField.getText());
			
			try {
				gui.update(trainer);
				dispose();
			} catch (ClassNotFoundException | SQLException e1) {
				JOptionPane.showMessageDialog(null, "저장실패");
			}				
		}
		//취소버튼 클릭시.
		else if(e.getSource() == cancelButton) {
			int result = JOptionPane.showConfirmDialog(null, "수정을 취소하시겠습니까?", null, JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
			if(result==0) {
				dispose();
			}
		}
	}
}
