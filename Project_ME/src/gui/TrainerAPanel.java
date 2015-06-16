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
	
	String[] selectionArray = {"이름", "연락처"};
	JButton addButton = new JButton("등록");
	JButton deleteButton = new JButton("삭제");
	JButton detailButton = new JButton("상세보기");
	JButton searchButton = new JButton("검색");
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
		
		////등록
		if(e.getSource() == addButton) {
			new TrainerRegister();
			try {
				vectorTrainer = gui.getTrainer();
			} catch (ClassNotFoundException | SQLException e1) {
				JOptionPane.showMessageDialog(null, "데이터베이스 오류");
			}
		}
		////삭제
		else if(e.getSource() == deleteButton) {
			int index = jtable.getSelectedRow();
			
			if (index != -1){
				String id;
				int delete = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?", "", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if(delete==JOptionPane.YES_OPTION)
					try {
						id = this.vectorTrainer.get(index).getID();
						gui.delete(id, "trainer");
						gui.delete(id, "Member");
					} catch (ClassNotFoundException | SQLException e1) {
						JOptionPane.showMessageDialog(null, "삭제 실패");
					}
			}
			try {
				vectorTrainer = gui.getTrainer();
			} catch (ClassNotFoundException | SQLException e1) {
				JOptionPane.showMessageDialog(null, "데이터베이스 오류");
			}
			
		}
		////상세보기
		else if(e.getSource() == detailButton) {
			int index = jtable.getSelectedRow();
			if (index != -1)
				new TrainerDetail(this.vectorTrainer.get(index));
		}
		////검색
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
						JOptionPane.showMessageDialog(null, "해당 트레이너가 존재하지 않습니다.");
						vectorTrainer = gui.getTrainer();
					}
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null, "데이터베이스 접근실패");
				}
			} else {
				try {
					vectorTrainer = gui.getTrainer();
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null, "데이터베이스 접근실패");
				}
			}
		}

		try {
			Patch(gui.getRowTrainer(vectorTrainer));
		} catch (ClassNotFoundException | SQLException e1) {
			JOptionPane.showMessageDialog(null, "불러오기 실패");
		}
	}
}
