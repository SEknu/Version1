package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import object.Client;
import object.Program;

public class CleintProgram<T> extends JDialog implements ActionListener {
	GuiProcess gui;

	Vector<Program> list;
	JComboBox<String> combo = new JComboBox<String>();
	JButton button = new JButton("배정");

	private Client clt;

	public CleintProgram(Vector<Program> list, Client c) {
		gui = GuiProcess.getInstance();

		clt = c;
		this.list = list;

		setLayout(new FlowLayout());

		button.addActionListener(this);
		combo.addItem(" ");
		//DB로부터 파일을 읽어들임.
		for (Program p : list) {
			combo.addItem(p.getName());
		}

		add(combo);
		add(button);
		setModal(true);
		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//배정버튼 클릭시
		if (e.getSource() == button) {
			int index = combo.getSelectedIndex();
	
			if (index != -1) {
				clt.setProgram(combo.getItemAt(index));
				try {
					gui.update(clt);
					JOptionPane.showMessageDialog(null, "저장했습니다.");
					dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null, "저장실패");
				}
			}
		}
	}
}
