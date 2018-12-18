package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;

import admin.AdminJFrame_admin;
import tcpserver.TCPClient1;
import user.UserJFrame;

import javax.swing.JPasswordField;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class LoginJFrame extends JFrame implements ActionListener {

	JTextField idField;
	JPasswordField pwField;
	JButton btn1, btn2, btn3;
	private JButton btn4;

	// main 프레임 설정
	public LoginJFrame() {
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

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		// 로그인
		if (e.getSource() == btn1) {

			String id = idField.getText();
			String pw = pwField.getText();
			// 관리자 계정 로그인
			if (id.equals("admin") && pw.equals("1234")) {
				dispose();
				try {
					new AdminJFrame_admin();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// 일반 회원 로그인
			} else {
				String result = null;
				try {
					if (id.trim().equals("")) {
						JOptionPane.showMessageDialog(null, "아이디를 입력해 주세요", "아이디 입력", JOptionPane.INFORMATION_MESSAGE);
					} else {
						result = new TCPClient1().loginCheck(id + "\n" + pw);
						if (result.equals("NOID")) {
							JOptionPane.showMessageDialog(null, "아이디가 없습니다.");
						} else if (result.equals("PASS")) {
							dispose();
							new UserJFrame(id);
						} else {
							JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.");
						}
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}
		// 회원가입
		else if (e.getSource() == btn2) {
			// 회원가입 프레임
			new JoinJFrame();
		}
		// 아이디/비밀번호 찾기
		else if (e.getSource() == btn4) {
			new IdPwFindJFrame();
		} else { // 종료
			JOptionPane.showMessageDialog(null, "프로그램을 종료합니다.");
			dispose();
		}
	}
}
