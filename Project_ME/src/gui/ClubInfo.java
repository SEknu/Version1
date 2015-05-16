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
		
//		clientPanel.add(new JLabel("전체 회원 수 : " + clients_num));
//		clientPanel.add(new JLabel("---------------------------"));
//		clientPanel.add(new JLabel("주 3일 회원 수 : " + week_3));
//		clientPanel.add(new JLabel("주 4일 회원 수 : " + week_4));
//		clientPanel.add(new JLabel("주 5일 회원 수 : " + week_5));
//		clientPanel.add(new JLabel("---------------------------"));
//		clientPanel.add(new JLabel("일 1시간 회원 수 : " + time_1));
//		clientPanel.add(new JLabel("일 2시간 회원 수 : " + time_2));
//		
		add(clientPanel);
	}
}
