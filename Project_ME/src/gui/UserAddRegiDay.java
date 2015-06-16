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
	
	private JButton saveButton = new JButton("연장");
	private JButton cancelButton = new JButton("취소");
	private JComboBox<String> registPeriodComboBox = new JComboBox<String>();
	
	public UserAddRegiDay(Client client) {
		
		gui = GuiProcess.getInstance();
		this.client = client;

		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		
		registPeriodComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "1개월", "2개월", "3개월", "6개월", "12개월" }));
		
		panel1.add(new JLabel("연장기간을 선택해주세요."));
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
		//저장버튼 클릭시
		if(e.getSource() == saveButton) {
			String registPeriod = registPeriodComboBox.getSelectedItem().toString();
			
			int result = JOptionPane.showConfirmDialog(null, registPeriod + "을 연장합니다.", null, JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
			
			if(result==JOptionPane.YES_OPTION)
			{
				String terminateDate = calculateTerminateDate(registPeriod);
				
				client.setTerminateDate(terminateDate);
				try {
					gui.update(client);
					dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null, "연장실패");
				}		
			}
		}
		dispose();
	}
	
	public String calculateTerminateDate(String registPeriod) {
		String newTerminateDate = null;
		String terminateDate = client.getTerminateDate();

		String[] endDate = terminateDate.split("-");
		String[] period = registPeriod.split("개월");
		
		int endYear = Integer.parseInt(endDate[0]);
		int endMonth = Integer.parseInt(endDate[1]);
		int registperiod = Integer.parseInt(period[0]);
		int result = endMonth + registperiod;
		
		if (result > 12) {
			endYear += 1;
			result -= 12;
		}
		endDate[0] = Integer.toString(endYear);
		endDate[1] = Integer.toString(result);
		
		newTerminateDate = endDate[0] + "-" + endDate[1] + "-" + endDate[2];
		
		return newTerminateDate;
	}
}
