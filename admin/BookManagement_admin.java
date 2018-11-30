package admin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;

public class BookManagement_admin extends JFrame implements ActionListener  {
	JButton bookRegister;
	JButton bookRemove;
	JButton bookUpdate;
	JButton memberMangement;
	public BookManagement_admin() {

		//버튼 만들기
		bookRegister = new JButton("도서 등록");
		bookRegister.setBounds(45, 60, 97, 23);
		bookRegister.addActionListener(this);

		bookRemove = new JButton("도서 삭제");
		bookRemove.setBounds(45, 106, 97, 23);
		bookRemove.addActionListener(this);
		
		bookUpdate = new JButton("도서 수정");
		bookUpdate.setBounds(45, 154, 97, 23);
		bookUpdate.addActionListener(this);
		
		memberMangement = new JButton("회원 관리");
		memberMangement.setBounds(45, 209, 97, 23);
		memberMangement.addActionListener(this);
		
		// JFrame에 추가 및 설정
		add(bookRegister);
		add(bookRemove);
		add(bookUpdate);
		add(memberMangement);
		setTitle("도서 관리 창(관리자)");
		setResizable(true);
		setBounds(700, 300, 296, 338);
		setLayout(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bookRegister) {
			new BookRegister_admin();
		}else if(e.getSource()==bookRemove) {
			
		}else if(e.getSource()==bookUpdate) {
			new BookUpdateSearch_admin();
		}else if(e.getSource()==memberMangement) {
			
		}
		
	}
	
	public static void main(String[] args) {
		new BookManagement_admin();
	}

}
