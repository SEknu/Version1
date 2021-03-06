package gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import object.Commodity;

////★수정부분 새로운 클래스(운동기구정보 상세보기용)
public class CommodityDetail extends JDialog implements ActionListener {

	Commodity commodity;
	String year, month, day;
	GuiProcess gui;

	JButton saveButton = new JButton("저장"); // 저장버튼
	JButton cancelButton = new JButton("취소"); // 취소버튼
	JCheckBox checkbox = new JCheckBox(); // 체크박스 - 파손유무체크용
	JPanel panel3 = new JPanel();
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel[] pan = new JPanel[6];

	public CommodityDetail(Commodity commodity) {
		this.commodity = commodity;
		gui = GuiProcess.getInstance();

		// 버튼추가
		cancelButton.addActionListener(this);
		saveButton.addActionListener(this);
		// 날짜를 년,월,일로 나눔
		String date[] = commodity.getBuyDate().split("-");
		year = date[0];
		month = date[1];
		day = date[2];
		// 패널 레이아웃설정
		for (int i = 0; i < 6; i++) {
			pan[i] = new JPanel(new GridLayout(1, 2, 8, 8));
		}
		// 가장 바깥 레이아웃 설정
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		panel2.setLayout(new FlowLayout());
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
		// DB에서 맞는정보 불러와서 보여주기 ★수정
		pan[0].add(new JLabel("이름 : "));
		pan[1].add(new JLabel("구입날짜 : "));
		pan[2].add(new JLabel("재고량 : "));
		pan[3].add(new JLabel("가격 : "));
		pan[4].add(new JLabel("파손유무  "));
		pan[5].add(new JLabel("비고: "));

		// 수정 필요 -> text필드 크기가 안 커져..
		pan[0].add(new JTextField(commodity.getName(), 15));
		pan[1].add(new JTextField(year, 5));
		pan[1].add(new JTextField(month, 5));
		pan[1].add(new JTextField(day, 5));
		pan[2].add(new JTextField(Integer.toString(commodity.getInventory()),
				15));
		pan[3].add(new JTextField(Integer.toString(commodity.getPrice()), 15));
		pan[4].add(checkbox);
		pan[5].add(new JTextField(commodity.getComment(), 15));
		// 패널1에 팬을 넣고
		for (int i = 0; i < 6; i++) {
			panel1.add(pan[i]);
		}
		// 패널에 버튼을 추가 후 가장 상위 패널에 추가
		panel2.add(saveButton);
		panel2.add(cancelButton);
		panel3.add(panel1);
		panel3.add(panel2);
		add(panel3);
		// 체크박스 DB에서 정보 읽어서 설정
		if (commodity.getState() == 1) {
			checkbox.setSelected(true);
		}
		pack();
		setModal(true);
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 저장버튼 클릭시
		if (e.getSource() == saveButton) {
			JTextField namefield = (JTextField) pan[0].getComponent(1);
			JTextField yearfield = (JTextField) pan[1].getComponent(1);
			JTextField monthfield = (JTextField) pan[1].getComponent(2);
			JTextField dayfield = (JTextField) pan[1].getComponent(3);
			JTextField Inventoryfield = (JTextField) pan[2].getComponent(1);
			JTextField pricefield = (JTextField) pan[3].getComponent(1);
			JTextField commentfield = (JTextField) pan[5].getComponent(1);
			// commodity정보 수정
			commodity.setName(namefield.getText());
			commodity.setBuyDate(yearfield.getText() + "-"
					+ monthfield.getText() + "-" + dayfield.getText());
			if (!isDate(yearfield.getText(), monthfield.getText(),
					dayfield.getText())) {
				return;
			}
			commodity.setInventory(Integer.parseInt(Inventoryfield.getText()));
			commodity.setPrice(Integer.parseInt(pricefield.getText()));
			commodity.setComment(commentfield.getText());
			if (checkbox.isSelected()) {
				commodity.setState(1);
			} else {
				commodity.setState(0);
			}
			// DB에 저장.
			try {
				gui.update(commodity);
				JOptionPane.showMessageDialog(null, "저장했습니다.");
			} catch (ClassNotFoundException | SQLException e1) {
				JOptionPane.showMessageDialog(null, "Database 저장실패");
			}
		}
		// 취소버튼 클릭시.
		else if (e.getSource() == cancelButton) {
			int result = JOptionPane.showConfirmDialog(null, "수정을 취소하시겠습니까?",
					null, JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.WARNING_MESSAGE, null);
			if (result == 0) {
				dispose();
			}
		}
	}

	// ★수정부분 - 날짜 형식이 잘못되었을 경우(추가된 메소드)
	public boolean isDate(String syear, String smonth, String sday) {
		int year, month, day;
		try {
			year = Integer.parseInt(syear);
			month = Integer.parseInt(smonth);
			day = Integer.parseInt(sday);

			if (year <= 0 || month <= 0 || day <= 0) {
				JOptionPane.showMessageDialog(null, "날짜를 정확하게 입력하세요.");
				return false;
			}
			if (month > 12 || day > 31) {
				JOptionPane.showMessageDialog(null, "날짜를 정확하게 입력하세요.");
				return false;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "구입날짜는 숫자를 입력하셔야합니다.");
			return false;
		}
		return true;
	}
}