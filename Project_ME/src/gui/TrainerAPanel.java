package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TrainerAPanel extends TrainerTPanel implements ActionListener {
	
	GuiProcess gui;
	
	String[] selectionArray = {"�̸�", "����ó"};
	JButton addButton = new JButton("���");
	JButton deleteButton = new JButton("����");
	JButton detailButton = new JButton("�󼼺���");
	JButton searchButton = new JButton("�˻�");
	JComboBox selectionList = new JComboBox(selectionArray);
	JTextField searchTextField = new JTextField(10);
	
	public TrainerAPanel() throws ClassNotFoundException, SQLException {
		gui = GuiProcess.getInstance();
		
		JPanel buttonPanel = new JPanel();
		addButton.addActionListener(this);
		deleteButton.addActionListener(this);
		detailButton.addActionListener(this);
		searchButton.addActionListener(this);
		
		buttonPanel.add(selectionList);
		buttonPanel.add(searchTextField);
		buttonPanel.add(searchButton);
		buttonPanel.add(addButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(detailButton);
		super.add(buttonPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String key = null;
		
		////���
		if(e.getSource() == addButton) {
			new TrainerRegister();
			try {
				vectorTrainer = gui.getTrainer();
			} catch (ClassNotFoundException | SQLException e1) {
				JOptionPane.showMessageDialog(null, "�����ͺ��̽� ����");
			}
		}
		////����
		else if(e.getSource() == deleteButton) {
			int index = jtable.getSelectedRow();
			
			if (index != -1){
				String id;
				int delete = JOptionPane.showConfirmDialog(null, "���� �����Ͻðڽ��ϱ�?", "", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if(delete==JOptionPane.YES_OPTION)
					try {
						id = this.vectorTrainer.get(index).getID();
						gui.delete(id, "trainer");
						gui.delete(id, "Member");
					} catch (ClassNotFoundException | SQLException e1) {
						JOptionPane.showMessageDialog(null, "���� ����");
					}
			}
			try {
				vectorTrainer = gui.getTrainer();
			} catch (ClassNotFoundException | SQLException e1) {
				JOptionPane.showMessageDialog(null, "�����ͺ��̽� ����");
			}
			
		}
		////�󼼺���
		else if(e.getSource() == detailButton) {
			int index = jtable.getSelectedRow();
			if (index != -1)
				new TrainerDetail(this.vectorTrainer.get(index));
		}
		////�˻�
		else if (e.getSource() == searchButton) {
			if(selectionList.getSelectedItem().equals(selectionArray[0])) {
				key = "name";
			} else if(selectionList.getSelectedItem().equals(selectionArray[1])) {
				key = "phone";
			}
			String str = this.searchTextField.getText();
					
			if (str != null && str.length() > 0) {
				try {
					vectorTrainer = gui.selectTrainer(key, str);
					if(vectorTrainer==null){
						JOptionPane.showMessageDialog(null, "�ش� Ʈ���̳ʰ� �������� �ʽ��ϴ�.");
						vectorTrainer = gui.getTrainer();
					}
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null, "�����ͺ��̽� ���ٽ���");
				}
			} else {
				try {
					vectorTrainer = gui.getTrainer();
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
