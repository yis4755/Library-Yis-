package admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import main.MainJFrame;

//	관리자 화면

public class AdminJFrame extends JFrame implements ActionListener {
	private JTextField idField;
	private JTextField numField;
	private JTextField idField1;
	private JTextField numField1;
	JButton btnRen, btnRet, btnLogout, bookRegister, bookRemove, bookUpdate, memberMangement;

	public AdminJFrame() {
		setTitle("도서 관리 프로그램");
		setSize(1000, 600);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(272, 10, 700, 541);
		panel.setLayout(null);
		getContentPane().add(panel);

		// 탭 추가
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 10, 676, 520);
		panel.add(tabbedPane);

		// 대출 탭 설정
		JPanel rental = new JPanel();
		rental.setLayout(null);
		rental.setBounds(10, 10, 380, 530);
		// 반납 탭 설정
		JPanel returnB = new JPanel();
		returnB.setLayout(null);
		returnB.setBounds(10, 10, 380, 530);

		// tabbedPane에 대출 탭 설정
		tabbedPane.add("대출", rental);

		// 대출 날짜별 대출 목록 출력
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DATE);

		JLabel listLab = new JLabel(year + "." + month + "." + day + "." + "대출 목록");
		listLab.setBounds(12, 194, 179, 15);
		rental.add(listLab);

		// 대출 목록 table 설정
		String[] head = { "일련 번호", "대출자", "도서 이름", "저자", "대출일", "반납 예정일" };
		String[][] content = new String[15][6];
		JTable rentalTab = new JTable(content, head);
		rentalTab.setBounds(12, 219, 647, 250);
		rental.add(rentalTab);

		// 회원 아이디 입력
		JLabel idLab = new JLabel("회원 아이디");
		idLab.setFont(new Font("굴림", Font.PLAIN, 15));
		idLab.setBounds(30, 47, 75, 15);
		rental.add(idLab);

		idField = new JTextField();
		idField.setBounds(164, 44, 245, 21);
		rental.add(idField);
		idField.setColumns(10);

		// 도서 일련번호 입력
		JLabel numberLab = new JLabel("도서 일련번호");
		numberLab.setFont(new Font("굴림", Font.PLAIN, 15));
		numberLab.setBounds(30, 103, 98, 15);
		rental.add(numberLab);

		numField = new JTextField();
		numField.setColumns(10);
		numField.setBounds(164, 100, 245, 21);
		rental.add(numField);

		// 대출 버튼
		btnRen = new JButton("대출");
		btnRen.setBounds(447, 46, 124, 75);
		rental.add(btnRen);

		// 반납 탭 설정
		tabbedPane.add("반납", returnB);

		// 반납 목록 table 설정
		String[] head1 = { "일련 번호", "대출자", "도서 이름", "저자", "반납일" };
		String[][] content1 = new String[15][5];
		JTable returnTab = new JTable(content1, head1);
		returnTab.setBounds(12, 219, 647, 250);
		returnB.add(returnTab);

		// 회원 아이디 입력
		JLabel idLab1 = new JLabel("회원 아이디");
		idLab1.setFont(new Font("굴림", Font.PLAIN, 15));
		idLab1.setBounds(30, 47, 75, 15);
		returnB.add(idLab1);

		idField1 = new JTextField();
		idField1.setColumns(10);
		idField1.setBounds(164, 44, 245, 21);
		returnB.add(idField1);

		// 도서 일련번호 입력
		JLabel numberLab1 = new JLabel("도서 일련번호");
		numberLab1.setFont(new Font("굴림", Font.PLAIN, 15));
		numberLab1.setBounds(30, 103, 98, 15);
		returnB.add(numberLab1);

		numField1 = new JTextField();
		numField1.setColumns(10);
		numField1.setBounds(164, 100, 245, 21);
		returnB.add(numField1);

		// 반납 버튼
		btnRet = new JButton("반납");
		btnRet.setBounds(447, 46, 124, 75);
		returnB.add(btnRet);

		// 로그아웃 버튼
		btnLogout = new JButton("로그아웃");
		btnLogout.setBounds(12, 507, 248, 44);
		getContentPane().add(btnLogout);

		// 도서 등록 버튼
		bookRegister = new JButton("도서 등록");
		bookRegister.setBounds(12, 10, 248, 44);
		getContentPane().add(bookRegister);

		// 도서 삭제 버튼
		bookRemove = new JButton("도서 삭제");
		bookRemove.setBounds(12, 79, 248, 44);
		getContentPane().add(bookRemove);

		// 도서 수정 버튼
		bookUpdate = new JButton("도서 수정");
		bookUpdate.setBounds(12, 151, 248, 44);
		getContentPane().add(bookUpdate);

		// 회원 관리 버튼
		memberMangement = new JButton("회원 관리");
		memberMangement.setBounds(12, 304, 248, 44);
		getContentPane().add(memberMangement);

		// 버튼 액션 추가
		btnRen.addActionListener(this);
		btnRet.addActionListener(this);
		btnLogout.addActionListener(this);
		bookRegister.addActionListener(this);
		bookRemove.addActionListener(this);
		bookUpdate.addActionListener(this);
		memberMangement.addActionListener(this);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRen) {								//	대출 완료
			JOptionPane.showMessageDialog(null, "대출 작업 완료");
		} else if (e.getSource() == btnRet) {						//	반납 완료
			JOptionPane.showMessageDialog(null, "반납 작업 완료");
		} else if (e.getSource() == btnLogout) {					//	로그아웃
			setVisible(false);
			new MainJFrame();
		} else if (e.getSource() == bookRegister) {					//	도서 등록
			BookRegister_admin bookRegister = new BookRegister_admin();
		} else if (e.getSource() == bookRemove) {					//	도서 삭제
			
		} else if (e.getSource() == bookUpdate) {					//	도서 수정
			BookUpdateSearch_admin bookUpdateSearch = new BookUpdateSearch_admin();
		} else if (e.getSource() == memberMangement) {				//	회원 관리
			MemberManagement_admin memberManagement = new MemberManagement_admin();
		}
	}
	public static void main(String[] args) {
		new AdminJFrame();
	}
}
