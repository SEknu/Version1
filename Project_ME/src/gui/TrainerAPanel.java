package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import object.Program;
import object.Trainer;
import database.FileManager;

public class TrainerAPanel extends TrainerTPanel implements ActionListener {
	
	
	JButton addButton = new JButton("���");
	JButton deleteButton = new JButton("����");
	JButton detailButton = new JButton("�󼼺���");
	JButton searchButton = new JButton("�˻�");
	JTextField searchTextField = new JTextField(10);
	
	JPanel panel = new JPanel();
	
	FileManager filemanager = FileManager.getInstance();
	
	public TrainerAPanel() throws ClassNotFoundException, SQLException {
		
		addButton.addActionListener(this);
		deleteButton.addActionListener(this);
		detailButton.addActionListener(this);
		searchButton.addActionListener(this);
		
		panel.add(searchTextField);
		panel.add(searchButton);
		panel.add(addButton);
		panel.add(deleteButton);
		panel.add(detailButton);
		super.add(panel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addButton) { //���
			new TrainerRegister();
			try {
				vectorTrainer = gui.getTrainer();
			} catch (ClassNotFoundException | SQLException e1) {
				JOptionPane.showMessageDialog(null, "�����ͺ��̽� ����");
			}
		} else if(e.getSource() == deleteButton) { //����
			int index = jtable.getSelectedRow();
			
			if (index != -1){
				int delete = JOptionPane.showConfirmDialog(null, "���� �����Ͻðڽ��ϱ�?", "", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if(delete==JOptionPane.YES_OPTION)
					try {
						filemanager.delete(this.vectorTrainer.get(index).getID(), "trainer");
						filemanager.delete(this.vectorTrainer.get(index).getID(), "Member");
					} catch (ClassNotFoundException | SQLException e1) {
						JOptionPane.showMessageDialog(null, "���� ����");
					}
			}
			try {
				vectorTrainer = gui.getTrainer();
			} catch (ClassNotFoundException | SQLException e1) {
				JOptionPane.showMessageDialog(null, "�����ͺ��̽� ����");
			}
			
		} else if(e.getSource() == detailButton) { //�󼼺���
			int index = jtable.getSelectedRow();
			if (index != -1)
				new TrainerDetail(this.vectorTrainer.get(index));
			
		}  else if (e.getSource() == searchButton) { //�˻�
			
			String col = "name";
			String str = this.searchTextField.getText();
					
			if (str != null && str.length() > 0) {
				try {
					vectorTrainer = filemanager.selectTrainer(col, str);
					if(vectorTrainer==null){
						JOptionPane.showMessageDialog(null, "�ش� Ʈ���̳ʰ� �������� �ʽ��ϴ�.");
						vectorTrainer = filemanager.getTrainer("all");
					}
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null, "�����ͺ��̽� ���ٽ���");
				}
			//} else if(str == null || str.length() <= 0){
			//	JOptionPane.showMessageDialog(null, "Ʈ���̳� �̸��� �Է��ϼ���.", "", JOptionPane.INFORMATION_MESSAGE);
			} else {
				try {
					vectorTrainer = filemanager.getTrainer("all");
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null, "�����ͺ��̽� ���ٽ���");
				}
			}
		}

		try {
			Patch(gui.getRowTrainer(vectorTrainer));
		} catch (ClassNotFoundException | SQLException e1) {
			JOptionPane.showMessageDialog(null, "�ҷ����� ����");
		}
	}
}
