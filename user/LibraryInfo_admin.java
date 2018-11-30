package user;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class LibraryInfo_admin extends JFrame implements ActionListener {

	public LibraryInfo_admin() {

		//통계표 만들기
		String[] column = {"대출 횟수", "반납 횟수", "회원 수", "도서 수량"};
		Object row[][] = new Object[1][4];
		
		JTable infoTable = new JTable(row, column);
		JScrollPane js = new JScrollPane(infoTable);
		
		
		// JFrame에 추가 및 설정
		add(js);
		setLayout(new FlowLayout());
		setTitle("도서 통계");
		setResizable(false);
		setBounds(300, 700, 461, 83);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new LibraryInfo_admin();
	}

}
