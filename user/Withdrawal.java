package user;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Withdrawal extends  JFrame implements ActionListener {
	public Withdrawal() {
		
		//비밀번호
		JLabel PasswordLabel = new JLabel("비밀번호");
		PasswordLabel.setBounds(12, 22, 57, 15);

		JPasswordField PasswordField = new JPasswordField(10);
		PasswordField.setBounds(110,19,136,21);
		
		//비밀번호 확인
		JLabel PasswordCheckLabel = new JLabel("비밀번호 확인");
		PasswordCheckLabel.setBounds(12, 50, 99, 15);
				
		JPasswordField PasswordCheckField = new JPasswordField(10);
		PasswordCheckField.setBounds(110,47,136,21);
		
		//회원탈퇴 버튼
		JButton btnNewButton = new JButton("회원탈퇴");
		btnNewButton.setBounds(79, 78, 97, 23);
				

		//JFrame에 추가 및 설정
		add(PasswordLabel);
		add(PasswordField);
		add(PasswordCheckLabel);
		add(PasswordCheckField);
		add(btnNewButton);
		setTitle("회원 탈퇴");
		setLayout(null);
		setResizable(false);
		setBounds(300, 700, 281, 138);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Withdrawal();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
