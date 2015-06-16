package gui;

import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import object.Trainer;

public class TrainerTPanel extends JPanel {
	JTable jtable;
	JScrollPane scroll;
	Vector<Trainer> vectorTrainer;
	Vector<Vector<String>> row = new Vector<Vector<String>>();
	Vector<String> col = new Vector<String>();
	GuiProcess gui;
	
	String[] colArray = {"이름","전화번호","주소","입사일"};
	
	public TrainerTPanel() throws ClassNotFoundException, SQLException {
		gui = GuiProcess.getInstance();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		
		for(int i=0; i<colArray.length; i++)
			col.add(colArray[i]);
		
		try {
			vectorTrainer = gui.getTrainer();
		} catch (ClassNotFoundException | SQLException e1) {
			JOptionPane.showMessageDialog(null, "데이터베이스 오류");
			e1.printStackTrace();
		}

		row = gui.getRowTrainer(vectorTrainer);
		jtable = new JTable(row, col);
		scroll = new JScrollPane(jtable);
		
		add(scroll);
		add(panel);
		
		setSize(500, 550);
		setVisible(true);
	}	
	
	public void Patch(Vector<Vector<String>> New) {
		row.removeAllElements();
		row.addAll(New);
		jtable.updateUI();
	}
}