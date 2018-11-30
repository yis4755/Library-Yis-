package admin;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JButton;

public class MemberManagement_admin extends  JFrame implements ActionListener  {
	public MemberManagement_admin() {

		// 표 만들기
		String[] column = { "아이디", "이름", "전화번호", "주소" };
		Object row[][] = new Object[1][4];

		JTable infoTable = new JTable(row, column);
		JScrollPane js = new JScrollPane(infoTable);

		JButton MemberInfo = new JButton("회원 정보");
		JButton MemberRemove = new JButton("회원 탈퇴");

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(MemberInfo);
		buttonPanel.add(MemberRemove);

		// JFrame에 추가 및 설정
		add(js, BorderLayout.NORTH);
		add(buttonPanel);
		setTitle("회원 정보(관리자)");
		setResizable(true);
		setBounds(700, 300, 483, 505);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new MemberManagement_admin();
	}

}
