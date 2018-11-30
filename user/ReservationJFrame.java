package user;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class ReservationJFrame extends JFrame {
	private JTextField resField;

	public ReservationJFrame() {
		setTitle("도서 예약");
		setSize(400, 350);
		getContentPane().setLayout(null);
		
		resField = new JTextField();
		resField.setBounds(44, 124, 239, 21);
		getContentPane().add(resField);
		resField.setColumns(10);
		
		String[] selectRes = {"도서 이름", "저자"};
		JComboBox selectCom = new JComboBox(selectRes);
		selectCom.setBounds(44, 81, 138, 21);
		getContentPane().add(selectCom);
		
		JButton btnRes = new JButton("예약");
		btnRes.setBounds(117, 207, 149, 36);
		getContentPane().add(btnRes);
		
		JButton btnSear = new JButton("검색");
		btnSear.setBounds(294, 123, 78, 21);
		getContentPane().add(btnSear);
		
		setVisible(true);
	}
}
