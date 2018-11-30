package admin;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BookRegister_admin extends JFrame implements ActionListener {
	private JTextField numberField;
	private JTextField nameField;
	private JTextField textField_3;
	private JTextField textField_4;
	private JLabel jlb = new JLabel(" ");
	JComboBox combo;
	JButton fileButton;
	JLabel picture;
	private JFileChooser jfc = new JFileChooser();
	JButton button;

	public BookRegister_admin() {

		// 도서 일련번호 입력
		JLabel bookNumber = new JLabel("도서 일련번호");
		bookNumber.setBounds(12, 33, 84, 15);

		numberField = new JTextField();
		numberField.setBounds(108, 30, 116, 21);
		getContentPane().add(numberField);
		numberField.setColumns(10);

		// 도서 이름 입력
		JLabel bookName = new JLabel("도서 이름");
		bookName.setBounds(12, 58, 84, 15);

		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(108, 55, 116, 21);
		getContentPane().add(nameField);

		// 도서 분류
		JLabel bookClassification = new JLabel("도서 분류");
		bookClassification.setBounds(12, 83, 84, 15);
		String[] classification = { "총류", "철학", "종교", "사회학", "언어", "자연과학", "기술과학", "예술", "문학", "역사" };
		combo = new JComboBox(classification);
		combo.setBounds(108, 80, 116, 21);

		// 도서 저자
		JLabel bookAuthor = new JLabel("도서 저자");
		bookAuthor.setBounds(12, 108, 84, 15);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(108, 105, 116, 21);
		getContentPane().add(textField_3);

		// 도서 출판년도
		JLabel bookYear = new JLabel("도서 출판 년도");
		bookYear.setBounds(12, 133, 84, 15);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(108, 130, 116, 21);

		// 도서 사진 및 파일첨부
		JLabel bookPicture = new JLabel("도서 사진");
		bookPicture.setBounds(12, 158, 84, 15);
		jlb.setBounds(108, 184, 209, 51);
		getContentPane().add(jlb);

		fileButton = new JButton("파일 첨부");
		fileButton.setBounds(108, 154, 97, 23);
		getContentPane().add(fileButton);
		fileButton.addActionListener(this);
		jfc.setFileFilter(new FileNameExtensionFilter("jpg", "jpg")); // 필터
		jfc.setMultiSelectionEnabled(false);// 다중 선택 불가
		picture = new JLabel("사진");
		picture.setBounds(22, 187, 97, 153);
		getContentPane().add(picture);

		// JFrame에 추가 및 설정
		getContentPane().setLayout(null);
		getContentPane().add(bookNumber);
		getContentPane().add(bookName);
		getContentPane().add(bookClassification);
		getContentPane().add(bookAuthor);
		getContentPane().add(bookYear);
		getContentPane().add(bookPicture);
		getContentPane().add(combo);

		getContentPane().add(textField_4);

		button = new JButton("도서 등록하기");
		button.setBounds(108, 350, 121, 23);
		button.addActionListener(this);
		getContentPane().add(button);

		setTitle("도서 등록(관리자)");
		setResizable(true);
		setBounds(700, 300, 333, 422);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == fileButton) {
			if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				// showopendialog 열기 창을 열고 확인 버튼을 눌렀는지 확인
				ImageIcon icon = new ImageIcon(jfc.getSelectedFile().toString());
				Image image = icon.getImage(); // ImageIcon을 Image로 변환.
				image = image.getScaledInstance(97, 153, java.awt.Image.SCALE_SMOOTH);
				icon = new ImageIcon(image); // Image로 ImageIcon 생성

				jlb.setText("열기 경로 : " + jfc.getSelectedFile().toString());
				picture.setIcon(icon);
			}
		}

		if (e.getSource() == button) {
			numberField.getText();
			nameField.getText();
			combo.getSelectedItem();
			textField_3.getText();
			textField_4.getText();
			String test = "123412341234";
		
		}

	}

	public static void main(String[] args) {
		new BookRegister_admin();
	}
}
