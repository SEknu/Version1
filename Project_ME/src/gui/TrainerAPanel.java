package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import object.Trainer;
import database.FileManager;

public class TrainerAPanel extends TrainerTPanel implements ActionListener {
	JButton Button_Add = new JButton("등록");
	JButton Button_Delete = new JButton("삭제");
	
	JPanel panel = new JPanel();
	
	FileManager filemanager = FileManager.getInstance();
	
	public TrainerAPanel() throws ClassNotFoundException, SQLException {
		Button_Add.addActionListener(this);
		Button_Delete.addActionListener(this);
		panel.add(Button_Add);
		panel.add(Button_Delete);
		super.add(panel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Button_Add) {
			new TrainerRegister();
			
			try {
				Patch(getRow());
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if(e.getSource() == Button_Delete) {
			int index = jtable.getSelectedRow();
			
			if (index != -1){
				int delete = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?", "", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if(delete==JOptionPane.YES_OPTION)
					try {
						filemanager.delete(this.allTrainer.get(index).getID(), "trainer");
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
			try {
				Patch(getRow());
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
