package admin;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;

public class BookUpdateSearch_admin extends JFrame implements ActionListener{
	JComboBox combo;
	JButton searchButton;
	public BookUpdateSearch_admin() {
		setLayout(new FlowLayout());
		
		//검색 메뉴 만들기
		String[] searchMenu = {"책 제목","저자"};
		combo = new JComboBox(searchMenu);
		add(combo);
		JTextField searchText = new JTextField(15);		
		add(searchText);
		
		//검색 버튼 만들기
		searchButton = new JButton("검색");
		searchButton.addActionListener(this);
		add(searchButton);
		
		
		setTitle("도서 검색(관리자)");
		setBounds(400,300,364,66);
		setResizable(false); //창 크기변경 불가
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String search = combo.getSelectedItem().toString(); //책 제목과 저자를 갖고 오는거
	}
	public static void main(String[] args) {
		new BookUpdateSearch_admin();
	}


}
