package gui;

import gui.CommodityRegister;

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

import object.Commodity;

public class CommodityPanel extends JPanel implements ActionListener {
	GuiProcess gui;

	JTable jtable;
	JScrollPane scroll;

	Vector<Commodity> vectorCommodity;
	Vector<Vector<String>> row = new Vector<Vector<String>>();
	Vector<String> col = new Vector<String>();
	// �ڼ����κ�
	String[] colArray = { "�̸�", "���Գ�¥", "���", "���" };
	String[] selectionArray = { "�̸�", "���Գ�¥" };
	JButton addButton = new JButton("���");
	JButton deleteButton = new JButton("����");
	JButton searchButton = new JButton("�˻�");
	JButton detailButton = new JButton("�󼼺���");
	JComboBox selectionList = new JComboBox(selectionArray);
	JTextField searchTextField = new JTextField(10);

	// �ڼ����κ�
	public CommodityPanel() throws ClassNotFoundException, SQLException {
		gui = GuiProcess.getInstance();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();

		for (int i = 0; i < colArray.length; i++) {
			col.add(colArray[i]);
		}
		try {
			vectorCommodity = gui.getCommodity();
		} catch (ClassNotFoundException | SQLException e1) {
			JOptionPane.showMessageDialog(null, "�����ͺ��̽� ����");
		}
		row = gui.getCommodityRow(vectorCommodity);

		jtable = new JTable(row, col);
		scroll = new JScrollPane(jtable);

		addButton.addActionListener(this);
		deleteButton.addActionListener(this);
		detailButton.addActionListener(this);
		searchButton.addActionListener(this);

		add(scroll);
		panel.add(selectionList);
		panel.add(searchTextField);
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

	// �ڼ����κ�


	@Override
	public void actionPerformed(ActionEvent e) {
		String key = null;
		if (e.getSource() == addButton) {
			new CommodityRegister();
			try {
				vectorCommodity = gui.getCommodity();
			} catch (ClassNotFoundException | SQLException e1) {
				JOptionPane.showMessageDialog(null, "�����ͺ��̽� ����");
			}
		} else if (e.getSource() == searchButton) {
			if (selectionList.getSelectedItem().equals(selectionArray[0])) {
				key = "name";
			} else if (selectionList.getSelectedItem()
					.equals(selectionArray[1])) {
				key = "buy_date";
			}

			String str = this.searchTextField.getText();

			if (str != null && str.length() > 0) {
				try {
					vectorCommodity = gui.selectCommodity(key, str);
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null, "�����ͺ��̽� ���ٽ���");
				}
			} else {
				try {
					vectorCommodity = gui.getCommodity();
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null, "�����ͺ��̽� ���ٽ���");
				}
			}
		} else if (e.getSource() == deleteButton) {
			int index = jtable.getSelectedRow();
			try {
				gui.delete(gui.getCommodity().get(index)
						.getID(), "commodity");
				vectorCommodity = gui.getCommodity();
			} catch (ClassNotFoundException | SQLException e1) {
				JOptionPane.showMessageDialog(null, "�����ͺ��̽� ����");
			} catch (ArrayIndexOutOfBoundsException e2) {
				JOptionPane.showMessageDialog(null, "������ ��ⱸ�� �����ϴ�.");
			}
		} else if (e.getSource() == detailButton) {
			int index = jtable.getSelectedRow();
			if (index != -1) {
				new CommodityDetail(this.vectorCommodity.get(index));
			}
		}
		try {
			patch(gui.getCommodityRow(vectorCommodity));
		} catch (ClassNotFoundException | SQLException e1) {
			JOptionPane.showMessageDialog(null, "�ҷ����� ����");
		}
	}
}