package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;

import admin.AdminJFrame;
import member.MemberJFrame;
import user.UserJFrame;

import javax.swing.JPasswordField;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class MainJFrame extends JFrame implements ActionListener {

	JTextField idField;
	JPasswordField pwField;
	JButton btn1, btn2, btn3;
	private JButton btn4;

	// main 프레임 설정
	public MainJFrame() {
		getContentPane().setBackground(Color.WHITE);

		// main 프레임 설정
		setTitle("도서관 관리 프로그램");
		setSize(500, 450);
		getContentPane().setLayout(null);

		// image 라벨
		JLabel imgLab = new JLabel("");
		imgLab.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\도서관.jpeg"));
		imgLab.setBackground(Color.DARK_GRAY);
		imgLab.setBounds(12, 21, 460, 230);
		getContentPane().add(imgLab);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(12, 276, 460, 125);
		panel.setLayout(null);
		getContentPane().add(panel);

		// 버튼(로그인)
		btn1 = new JButton("로그인");
		btn1.setBounds(291, 66, 97, 23);
		panel.add(btn1);

		// 버튼(회원가입)
		btn2 = new JButton("회원가입");
		btn2.setBounds(40, 10, 97, 23);
		panel.add(btn2);

		// 버튼(종료)
		btn3 = new JButton("종료");
		btn3.setBounds(332, 10, 97, 23);
		panel.add(btn3);

		// 버튼(아이디/비밀번호 찾기)
		btn4 = new JButton("아이디/비밀번호 찾기");
		btn4.setBounds(149, 10, 162, 23);
		panel.add(btn4);

		// id 텍스트 필드
		idField = new JTextField();
		idField.setBounds(144, 49, 115, 20);
		panel.add(idField);
		idField.setToolTipText("8글자 이내 입력");
		idField.setColumns(10);

		// id 라벨
		JLabel idLab = new JLabel("ID");
		idLab.setBounds(108, 52, 24, 15);
		panel.add(idLab);

		// pw 라벨
		JLabel pwLab = new JLabel("PW");
		pwLab.setBounds(108, 86, 24, 15);
		panel.add(pwLab);

		// pw 텍스트 필드
		pwField = new JPasswordField();
		pwField.setBounds(144, 84, 115, 20);
		panel.add(pwField);
		pwField.setToolTipText("8글자 이내 입력");

		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 로그인
		if (e.getSource() == btn1) {
			String id = idField.getText();
			String pw = pwField.getText();
			// 관리자 계정 로그인
			if (id.equals("admin") && pw.equals("1234")) {
				setVisible(false);
				new AdminJFrame();
			} else { // 일반 회원 로그인
				String result = new TCPClient1().loginCheck(id + "\n" + pw);
				System.out.println(result);
				if (result.equals("1")) { // 비밀번호 오류
					System.out.println("비밀 번호 오류");

				} else if (result.equals("0")) { // 아이디 오류
					System.out.println("아이디 오류");
				} else {
					UserJFrame user = new UserJFrame(result);
					setVisible(false);
				}

			}
		}
		// 회원가입
		else if (e.getSource() == btn2) {
			// 회원가입 프레임
			MemberJFrame mem = new MemberJFrame();
		}
		// 아이디/비밀번호 찾기
		else if (e.getSource() == btn4) {
			Id_PwFindJFrame find = new Id_PwFindJFrame();
		} else { // 종료
			JOptionPane.showMessageDialog(null, "프로그램을 종료합니다.");
			dispose();
		}
	}
}
