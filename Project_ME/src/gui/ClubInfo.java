package gui;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import database.FileManager;

public class ClubInfo extends JPanel {
	
	public ClubInfo()
	{
		JPanel clientPanel = new JPanel();
		clientPanel.setLayout(new BoxLayout(clientPanel, BoxLayout.Y_AXIS));
		
		FileManager fm = FileManager.getInstance();
		String tableName = "client";
		
//		int clients_num = fm.selectCount(null, null, tableName);
//		int week_3 = fm.selectCount("week", new Integer(3), tableName);
//		int week_4 = fm.selectCount("week", new Integer(4), tableName);
//		int week_5 = fm.selectCount("week", new Integer(5), tableName);
//		int time_1 = fm.selectCount("time", new Integer(1), tableName);
//		int time_2 = fm.selectCount("time", new Integer(2), tableName);
		
//		clientPanel.add(new JLabel("��ü ȸ�� �� : " + clients_num));
//		clientPanel.add(new JLabel("---------------------------"));
//		clientPanel.add(new JLabel("�� 3�� ȸ�� �� : " + week_3));
//		clientPanel.add(new JLabel("�� 4�� ȸ�� �� : " + week_4));
//		clientPanel.add(new JLabel("�� 5�� ȸ�� �� : " + week_5));
//		clientPanel.add(new JLabel("---------------------------"));
//		clientPanel.add(new JLabel("�� 1�ð� ȸ�� �� : " + time_1));
//		clientPanel.add(new JLabel("�� 2�ð� ȸ�� �� : " + time_2));
//		
		add(clientPanel);
	}
}
