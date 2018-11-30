package member;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JTextField;

import main.TCPClient1;

import javax.swing.JPasswordField;
import javax.swing.JButton;

public class MemberJFrame extends JFrame implements ActionListener {

	Random ran = new Random();

	JTextField idField;
	JTextField telField;
	JTextField addField;
	JPasswordField pwField;
	JButton btn1;
	JButton btn2;
	JButton btn3;

	int a, b;
	String checkId; // 아이디 중복버튼을 눌렀는지 확인
	String random = "";
	private JTextField nameField;
	private JTextField textField;

	// 회원가입 프레임
	public MemberJFrame() {
		setTitle("회원가입");
		setSize(450, 550);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(12, 10, 410, 490);
		panel.setLayout(null);
		getContentPane().add(panel);

		// id 입력 설정
		JLabel idLab = new JLabel("ID");
		idLab.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 14));
		idLab.setBounds(32, 43, 34, 15);
		panel.add(idLab);
		idField = new JTextField();
		idField.setBounds(119, 41, 170, 21);
		panel.add(idField);
		idField.setColumns(10);

		// pw 입력 설정
		JLabel pwLab = new JLabel("PW");
		pwLab.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 14));
		pwLab.setBounds(32, 97, 34, 15);
		panel.add(pwLab);
		pwField = new JPasswordField();
		pwField.setBounds(119, 95, 170, 21);
		panel.add(pwField);

		// 이름 입력 설정
		JLabel nameLab = new JLabel("NAME");
		nameLab.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 14));
		nameLab.setBounds(32, 152, 52, 15);
		panel.add(nameLab);
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(119, 150, 170, 21);
		panel.add(nameField);

		// 전화번호 입력 설정
		JLabel telLab = new JLabel("TEL");
		telLab.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 14));
		telLab.setBounds(32, 207, 34, 15);
		panel.add(telLab);
		telField = new JTextField();
		telField.setColumns(10);
		telField.setBounds(119, 205, 170, 21);
		panel.add(telField);

		// 주소 입력 설정
		JLabel addLab = new JLabel("ADDRESS");
		addLab.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 14));
		addLab.setBounds(32, 265, 81, 15);
		panel.add(addLab);
		addField = new JTextField();
		addField.setColumns(10);
		addField.setBounds(119, 263, 170, 21);
		panel.add(addField);

		// 회원가입 진행
		btn1 = new JButton("회원가입");
		btn1.setBounds(88, 375, 97, 23);
		btn1.addActionListener(this);
		panel.add(btn1);

		// 회원가입 취소
		btn2 = new JButton("취소");
		btn2.setBounds(221, 375, 97, 23);
		btn2.addActionListener(this);
		panel.add(btn2);

		// id 중복확인
		btn3 = new JButton("중복확인");
		btn3.setBackground(new Color(153, 204, 255));
		btn3.setBounds(301, 40, 97, 23);
		btn3.addActionListener(this);
		panel.add(btn3);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(32, 308, 153, 44);
		panel_1.setLayout(null);
		panel.add(panel_1);

		// 자동 가입 방지
		JLabel textLab = new JLabel();
		for (int i = 0; i < 3; i++) {
			a = ran.nextInt(25) + 65;
			random += String.valueOf((char) a);
			b = ran.nextInt(9);
			random += String.valueOf(b);

		}
		System.out.println(random);
		// String str = str.valueOf(obj)
//		textLab.setBounds(12, 10, 129, 24);
		textLab.setText(random);
		panel_1.setLayout(new GridLayout(0, 1));
		panel_1.add(textLab);

		// 자동가입 방지
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(197, 319, 170, 21);
		panel.add(textField);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String id = idField.getText();
		String pw = pwField.getText();
		String name = nameField.getText();
		String tel = telField.getText();
		String address = addField.getText();
		
			//ID중복방지 버튼
		if (e.getSource() == btn3) {
			String result = new TCPClient1().idCheck2(id);
			if (result.equals("1")) {
				checkId = idField.getText();
				JOptionPane.showMessageDialog(null, "사용가능 합니다.");
			} else {
				JOptionPane.showMessageDialog(null, "아이디가 존재합니다.");
			}
			
			// 취소 버튼
		} else if (e.getSource() == btn2) {
			setVisible(false);

			// 회원가입 버튼
		} else if (e.getSource() == btn1) {
			if (textField.getText().equals(random)) {
				new TCPClient1().join(id+"\n"+pw+"\n"+name+"\n"+tel+"\n"+address);
			} else {
				JOptionPane.showMessageDialog(null, "자동가입방지 문자가 잘못되었습니다. 다시 입력해주세요");
			}
		}

	}
}
