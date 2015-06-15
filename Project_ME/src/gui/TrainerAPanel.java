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
	
	
	JButton addButton = new JButton("등록");
	JButton deleteButton = new JButton("삭제");
	JButton detailButton = new JButton("상세보기");
	JButton searchButton = new JButton("검색");
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
		if(e.getSource() == addButton) { //등록
			new TrainerRegister();
			try {
				vectorTrainer = gui.getTrainer();
			} catch (ClassNotFoundException | SQLException e1) {
				JOptionPane.showMessageDialog(null, "데이터베이스 오류");
			}
		} else if(e.getSource() == deleteButton) { //삭제
			int index = jtable.getSelectedRow();
			
			if (index != -1){
				int delete = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?", "", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if(delete==JOptionPane.YES_OPTION)
					try {
						filemanager.delete(this.vectorTrainer.get(index).getID(), "trainer");
						filemanager.delete(this.vectorTrainer.get(index).getID(), "Member");
					} catch (ClassNotFoundException | SQLException e1) {
						JOptionPane.showMessageDialog(null, "삭제 실패");
					}
			}
			try {
				vectorTrainer = gui.getTrainer();
			} catch (ClassNotFoundException | SQLException e1) {
				JOptionPane.showMessageDialog(null, "데이터베이스 오류");
			}
			
		} else if(e.getSource() == detailButton) { //상세보기
			int index = jtable.getSelectedRow();
			if (index != -1)
				new TrainerDetail(this.vectorTrainer.get(index));
			
		}  else if (e.getSource() == searchButton) { //검색
			
			String col = "name";
			String str = this.searchTextField.getText();
					
			if (str != null && str.length() > 0) {
				try {
					vectorTrainer = filemanager.selectTrainer(col, str);
					if(vectorTrainer==null){
						JOptionPane.showMessageDialog(null, "해당 트레이너가 존재하지 않습니다.");
						vectorTrainer = filemanager.getTrainer("all");
					}
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null, "데이터베이스 접근실패");
				}
			//} else if(str == null || str.length() <= 0){
			//	JOptionPane.showMessageDialog(null, "트레이너 이름을 입력하세요.", "", JOptionPane.INFORMATION_MESSAGE);
			} else {
				try {
					vectorTrainer = filemanager.getTrainer("all");
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
