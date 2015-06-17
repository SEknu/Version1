package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import object.Client;

public class UserAddRegiDay extends JDialog implements ActionListener {

	private Client client;
	private GuiProcess gui;

	private JButton saveButton = new JButton("����");
	private JButton cancelButton = new JButton("���");
	private JComboBox<String> registPeriodComboBox = new JComboBox<String>();

	public UserAddRegiDay(Client client) {

		gui = GuiProcess.getInstance();
		this.client = client;

		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();

		registPeriodComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "1����", "2����", "3����", "6����", "12����" }));

		panel1.add(new JLabel("����Ⱓ�� �������ּ���."));
		panel2.add(registPeriodComboBox);
		panel3.add(saveButton);
		panel3.add(cancelButton);

		saveButton.addActionListener(this);
		cancelButton.addActionListener(this);

		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		add(panel);

		pack();
		setModal(true);
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		// �����ư Ŭ����
		if (e.getSource() == saveButton) {
			String registPeriod = registPeriodComboBox.getSelectedItem()
					.toString();
			String regiDate = client.getRegistDate();
			int result = JOptionPane.showConfirmDialog(null, registPeriod
					+ "�� �����մϴ�.", null, JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.WARNING_MESSAGE, null);

			if (result == JOptionPane.YES_OPTION) {
				String terminateDate = calculateTerminateDate(registPeriod);
				client.setRegistperiod(calculatePeriodDate(terminateDate,regiDate));
				client.setTerminateDate(terminateDate);
				try {
					gui.update(client);
					dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null, "�������");
				}
			}
		}
		dispose();
	}

	public String calculateTerminateDate(String registPeriod) {
		String newTerminateDate = null;
		String terminateDate = client.getTerminateDate();

		String[] endDate = terminateDate.split("-");
		String[] period = registPeriod.split("����");

		int endYear = Integer.parseInt(endDate[0]);
		int endMonth = Integer.parseInt(endDate[1]);
		int registperiod = Integer.parseInt(period[0]);
		int result = endMonth + registperiod;
		while(result > 12)
		{
			if (result > 12) {
				endYear += 1;
				result -= 12;
			}
		}
		endDate[0] = Integer.toString(endYear);
		if(result < 10)
			endDate[1] = "0"+Integer.toString(result);
		else
			endDate[1] = Integer.toString(result);

		newTerminateDate = endDate[0] + "-" + endDate[1] + "-" + endDate[2];

		return newTerminateDate;
	}
	public String calculatePeriodDate(String TermindateDate, String registDate) {
		String newPeriodDate = null;
		String[] tDate = TermindateDate.split("-");
		String[] rDate = registDate.split("-");
		int tYear = Integer.parseInt(tDate[0]);
		int rYear = Integer.parseInt(rDate[0]); 
		int tMonth = Integer.parseInt(tDate[1]);
		int rMonth = Integer.parseInt(rDate[1]);
		int resultYear = tYear - rYear;
		int resultMonth = Math.abs((tMonth - rMonth));
		int newPeriod;
		if(resultYear > 0)
			newPeriod = resultMonth + (resultYear*12);
		else
			newPeriod = resultMonth;
		
		newPeriodDate = ""+newPeriod+"����";
		return newPeriodDate;
	}
}
