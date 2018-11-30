package user;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.JTabbedPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.JTextField;

import main.MainJFrame;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class UserJFrame extends JFrame implements ActionListener {
	private JTable table_1;
	private JTable searchTab;
	private JTextField searchField;
	JButton btn1, btn2, btn3, btnUpdate, btnDelete, btnLogout;
	String[] user;
	
	// User 로그인 프레임 설정
	public UserJFrame(String userIn) {
		setTitle("사용자 이름 도서 이용 정보");
		setSize(740, 600);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(12, 10, 700, 541);
		panel.setLayout(null);
		getContentPane().add(panel);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("굴림", Font.BOLD, 14));
		tabbedPane.setBounds(12, 10, 676, 520);

		// 회원정보, 도서 검색, 예약 판넬 객체 생성
		JPanel mem = new JPanel();
		JPanel search = new JPanel();
		mem.setLayout(null);
		search.setLayout(null);

		// 회원정보 가져오기
		user = userIn.split("/n");

		// 회원정보 탭
		tabbedPane.add("회원정보", mem);
		String[] columnNames = { "번호", "도서 이름", "대출일", "반납 예정일" };
		String[][] rowData = new String[10][4];
		table_1 = new JTable(rowData, columnNames);
		table_1.setBounds(12, 303, 647, 176);
		mem.add(table_1);

		JLabel idLab = new JLabel("ID");
		idLab.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 15));
		idLab.setBounds(216, 25, 39, 15);
		mem.add(idLab);
		JLabel idPrint = new JLabel(user[0]);
		idPrint.setFont(new Font("굴림", Font.PLAIN, 15));
		idPrint.setBounds(216, 50, 226, 15);
		mem.add(idPrint);

		JLabel nameLab = new JLabel("NAME");
		nameLab.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 15));
		nameLab.setBounds(216, 86, 60, 15);
		mem.add(nameLab);
		JLabel namePrint = new JLabel(user[2]);
		namePrint.setFont(new Font("굴림", Font.PLAIN, 15));
		namePrint.setBounds(216, 111, 226, 15);
		mem.add(namePrint);

		JLabel addLab = new JLabel("ADDERSS");
		addLab.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 15));
		addLab.setBounds(216, 145, 97, 15);
		mem.add(addLab);
		JLabel addPrint = new JLabel(user[4]);
		addPrint.setFont(new Font("굴림", Font.PLAIN, 15));
		addPrint.setBounds(216, 170, 226, 15);
		mem.add(addPrint);

		JLabel gradeLab = new JLabel("GRADE");
		gradeLab.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 15));
		gradeLab.setBounds(216, 205, 77, 15);
		mem.add(gradeLab);
		JLabel gradePrint = new JLabel("일반 회원");
		gradePrint.setFont(new Font("굴림", Font.PLAIN, 15));
		gradePrint.setBounds(216, 230, 226, 15);
		mem.add(gradePrint);

		btn1 = new JButton("Image");
		btn1.setToolTipText("사진을 등록합니다.");
		btn1.setBounds(22, 24, 161, 221);
		mem.add(btn1);

		btnUpdate = new JButton("회원 정보 수정");
		btnUpdate.setBounds(468, 25, 191, 50);
		mem.add(btnUpdate);

		btnDelete = new JButton("회원 탈퇴");
		btnDelete.setBounds(468, 111, 191, 50);
		mem.add(btnDelete);

		btnLogout = new JButton("로그아웃");
		btnLogout.setBounds(468, 201, 191, 50);
		mem.add(btnLogout);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(30);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(180);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(100);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(100);

		// 도서 검색 탭
		tabbedPane.add("소장 도서 검색", search);
		searchField = new JTextField();
		searchField.setBounds(107, 26, 317, 21);
		search.add(searchField);
		searchField.setColumns(10);
		btn2 = new JButton("검색");
		btn2.setBounds(447, 25, 115, 23);
		search.add(btn2);
		// 콤보 박스
		String[] selectS = { "도서 이름", "저자 이름" };
		JComboBox select = new JComboBox(selectS);
		select.setBounds(12, 26, 83, 21);
		search.add(select);

		String[] searchhead = { "일련번호", "도서 이름", "저자", "분류", "대출 여부" };
		String[][] searchCon = new String[20][5];
		searchTab = new JTable(searchCon, searchhead);
		searchTab.setBounds(12, 137, 647, 342);
		search.add(searchTab);

		// 도서 예약 버튼
		btn3 = new JButton("도서 예약");
		btn3.setToolTipText("도서 예약은 대출 중이지 않은 도서만 가능합니다.");
		btn3.setBounds(447, 77, 115, 23);
		search.add(btn3);
		panel.add(tabbedPane);

		// 버튼 액션
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
		btnLogout.addActionListener(this);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn1) {

		} else if (e.getSource() == btn2) {

		} else if (e.getSource() == btn3) {						//	도서 예약
			ReservationJFrame res = new ReservationJFrame();
		} else if (e.getSource() == btnUpdate) {				//	회원 정보 수정
			UserUpdateJFrame userUpdate = new UserUpdateJFrame(user);
		} else if (e.getSource() == btnDelete) { 				//	회원 탈퇴
			Withdrawal withdrawal = new Withdrawal();
		} else {												//	로그 아웃
			setVisible(false);
			MainJFrame main = new MainJFrame();
		}

	}
}
