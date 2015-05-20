package gui;

import gui.CommodityRegister;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import object.Commodity;
import database.FileManager;

public class CommodityPanel extends JPanel implements ActionListener {
	FileManager filemanager = FileManager.getInstance();

	JTable jtable;
	JScrollPane scroll;

	Vector<Commodity> allCommo = new Vector<Commodity>();
	Vector<Vector<String>> row = new Vector<Vector<String>>();
	Vector<String> col = new Vector<String>();
	// ★수정부분
	String[] colArray = { "이름", "구입날짜", "재고량", "비고" };
	JButton addButton = new JButton("추가");
	JButton deleteButton = new JButton("삭제");
	JButton searchButton = new JButton("검색");
	JButton detailButton = new JButton("상세보기");
	JTextField tf_Name = new JTextField(10);

	// ★수정부분
	public CommodityPanel() throws ClassNotFoundException, SQLException {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();

		for (int i = 0; i < colArray.length; i++) {
			col.add(colArray[i]);
		}

		row = getRow();

		jtable = new JTable(row, col);
		scroll = new JScrollPane(jtable);

		addButton.addActionListener(this);
		deleteButton.addActionListener(this);
		detailButton.addActionListener(this);
		searchButton.addActionListener(this);

		add(scroll);
		panel.add(tf_Name);
		panel.add(searchButton);
		panel.add(addButton);
		panel.add(deleteButton);
		panel.add(detailButton);
		add(panel);

		setSize(500, 550);
		setVisible(true);
	}

	public void patch(Vector<Vector<String>> New) {
		row.removeAllElements();
		row.addAll(New);
		jtable.updateUI();
	}

	// ★수정부분
	public Vector<Vector<String>> getRow() throws ClassNotFoundException,
			SQLException {
		Vector<Vector<String>> result = new Vector<Vector<String>>();
		allCommo = filemanager.getCommodity("all");
		for (Commodity c : this.allCommo) {
			Vector<String> commodity = new Vector<String>();
			commodity.add(c.getName());
			commodity.add(c.getBuyDate());
			commodity.add(String.valueOf(c.getInventory()));
			commodity.add(c.getComment());
			result.add(commodity);
		}
		return result;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton) {
			new CommodityRegister();
		} else if (e.getSource() == searchButton) {
			Vector<Vector<String>> result = new Vector<Vector<String>>();

			String name = this.tf_Name.getText();

			if (name != null && name.length() > 0) {
				for (Commodity c : this.allCommo) {
					Vector<String> commodity = new Vector<String>();

					if (c.getName().equals(name)) {
						commodity.add(c.getName());
						commodity.add(c.getBuyDate());
						commodity.add(String.valueOf(c.getInventory()));
						commodity.add(c.getComment());
						result.add(commodity);
					}
				}
			}
			patch(result);
		} else if (e.getSource() == deleteButton) {
			int index = jtable.getSelectedRow();
			if (index != -1) {
				try {
					filemanager.delete(this.allCommo.get(index).getID(),
							"commodity");
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				index = -1;
			}
		} else if (e.getSource() == detailButton) {
			int index = jtable.getSelectedRow();
			if (index != -1) {
				new CommodityDetail(this.allCommo.get(index));
			}
		}
		try {
			patch(getRow());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
