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

import object.Client;

public abstract class ClientAbstractPanel extends JPanel implements
		ActionListener {

	JTable jtable;
	JScrollPane scroll;
	Vector<Client> vectorClient;
	Vector<Vector<String>> row = new Vector<Vector<String>>();
	Vector<String> col = new Vector<String>();
	GuiProcess gui;

	String[] colArray = { "�̸�", "����ó", "���Ʈ���̳�", "�������α׷�" };
	String[] selectionArray = { "�̸�", "����ó", "Ʈ���̳�", "���α׷�" };
	JButton addButton = new JButton("���");
	JButton deleteButton = new JButton("����");
	JButton searchButton = new JButton("�˻�");
	JButton detailButton = new JButton("�󼼺���");
	JComboBox selectionList = new JComboBox(selectionArray);
	JTextField searchTextField = new JTextField(10);

	protected String name;

	public ClientAbstractPanel() throws ClassNotFoundException, SQLException {
		gui = GuiProcess.getInstance();
		doGui();
	}

	public ClientAbstractPanel(String name) throws ClassNotFoundException,
			SQLException {
		gui = GuiProcess.getInstance();
		this.name = name;
		doGui();
	}

	protected void doGui() throws ClassNotFoundException, SQLException {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		addButton.addActionListener(this);
		deleteButton.addActionListener(this);
		searchButton.addActionListener(this);
		detailButton.addActionListener(this);

		for (int i = 0; i < colArray.length; i++) {
			col.add(colArray[i]);
		}
		try {
			getClient();
		} catch (ClassNotFoundException | SQLException e1) {
			JOptionPane.showMessageDialog(null, "�����ͺ��̽� ����");
		}

		row = gui.getClientRow(vectorClient);
		jtable = new JTable(row, col);
		scroll = new JScrollPane(jtable);

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

	public abstract void getClient() throws ClassNotFoundException,
			SQLException;

	public void patch(Vector<Vector<String>> newInfo) {
		row.removeAllElements();
		row.addAll(newInfo);
		jtable.updateUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String key = null;

		// //���
		if (e.getSource() == addButton) {
			new ClientRegister();
			try {
				getClient();
				patch(gui.getClientRow(vectorClient));
			} catch (ClassNotFoundException | SQLException e1) {
				JOptionPane.showMessageDialog(null, "�����ͺ��̽� ����");
			}
		}
		// //�˻�
		else if (e.getSource() == searchButton) {
			if (selectionList.getSelectedItem().equals(selectionArray[0])) {
				key = "name";
			} else if (selectionList.getSelectedItem()
					.equals(selectionArray[1])) {
				key = "phone";
			} else if (selectionList.getSelectedItem()
					.equals(selectionArray[2])) {
				key = "trainer";
			} else if (selectionList.getSelectedItem()
					.equals(selectionArray[3])) {
				key = "program";
			}
			String str = this.searchTextField.getText();

			if (str != null && str.length() > 0) {
				try {
					vectorClient = gui.selectClient(key, str);
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null, "�����ͺ��̽� ���ٽ���");
				}
			} else {
				try {
					getClient();
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null, "�����ͺ��̽� ���ٽ���");
				}
			}
		}
		// //����
		else if (e.getSource() == deleteButton) {
			int index = jtable.getSelectedRow();
			String id;
			try {
				id = vectorClient.get(index).getId();
				gui.delete(id, "client");
				gui.delete(id, "member");
				vectorClient = gui.getClient();
			} catch (ClassNotFoundException | SQLException e1) {
				JOptionPane.showMessageDialog(null, "�����ͺ��̽� ����");
			} catch (ArrayIndexOutOfBoundsException e2) {
				JOptionPane.showMessageDialog(null, "������ ȸ���� �����ϴ�.");
			}
		}
		// //�󼼺���
		else if (e.getSource() == detailButton) {
			int index = jtable.getSelectedRow();
			if (index != -1) {
				// DB���� �´� ���� �ҷ��ͼ� �����ֱ�.�ڼ���
				new ClientDetail(this.vectorClient.get(index));
			}
		}

		try {
			getClient();
			patch(gui.getClientRow(vectorClient));
		} catch (ClassNotFoundException | SQLException e1) {
			JOptionPane.showMessageDialog(null, "�ҷ����� ����");
		}
	}
}
