package user;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

//	회원정보 수정 화면

public class UserUpdateJFrame extends JFrame implements ActionListener {
	private JTextField idField;
	private JTextField nameField;
	private JTextField telField;
	private JTextField addrField;
	private JPasswordField pwField;
	JButton btnPwUpdate, btnUpdateCom, btnCancle;
	
	String userId, userPw;	//  로그인한 유저 아이디와 비밀번호 정보를 저장할 변수 선언

	public UserUpdateJFrame(String[] user) {
		setTitle("회원 정보 수정");
		setSize(450, 500);
		getContentPane().setLayout(null);

		// 아이디 라벨 및 입력 필드
		JLabel idLab = new JLabel("ID");
		idLab.setFont(new Font("굴림", Font.PLAIN, 15));
		idLab.setBounds(41, 61, 57, 15);
		getContentPane().add(idLab);

		idField = new JTextField();
		idField.setText(user[0]);
		userId = user[0]; //  userId에 로그인한 유저 아이디를 저장.
		idField.setEditable(false); // 아이디를 수정 못하도록 설정
		idField.setBounds(161, 58, 142, 21);
		getContentPane().add(idField);
		idField.setColumns(10);

		// 비밀번호 라벨 및 입력 필드
		JLabel pwLab = new JLabel("PW");
		pwLab.setFont(new Font("굴림", Font.PLAIN, 15));
		pwLab.setBounds(41, 120, 57, 15);
		getContentPane().add(pwLab);

		pwField = new JPasswordField();
		pwField.setText(user[1]);
		userPw = user[1];	//	userPw에 로그인한 유저 비밀번호를 저장.
		pwField.setEditable(false);
		pwField.setBounds(161, 117, 142, 21);
		getContentPane().add(pwField);

		// 이름 라벨 및 입력 필드
		JLabel nameLab = new JLabel("NAME");
		nameLab.setFont(new Font("굴림", Font.PLAIN, 15));
		nameLab.setBounds(41, 175, 57, 15);
		getContentPane().add(nameLab);

		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setText(user[2]);
		nameField.setBounds(161, 172, 142, 21);
		getContentPane().add(nameField);

		// 전화번호 라벨 및 입력 필드
		JLabel telLab = new JLabel("TEL");
		telLab.setFont(new Font("굴림", Font.PLAIN, 15));
		telLab.setBounds(41, 238, 57, 15);
		getContentPane().add(telLab);

		telField = new JTextField();
		telField.setColumns(10);
		telField.setText(user[3]);
		telField.setBounds(161, 235, 142, 21);
		getContentPane().add(telField);

		// 주소 라벨 및 입력 필드
		JLabel addrLab = new JLabel("ADDRESS");
		addrLab.setFont(new Font("굴림", Font.PLAIN, 15));
		addrLab.setBounds(41, 306, 76, 15);
		getContentPane().add(addrLab);

		addrField = new JTextField();
		addrField.setColumns(10);
		addrField.setText(user[4]);
		addrField.setBounds(161, 303, 142, 21);
		getContentPane().add(addrField);

		// 비밀번호 변경(버튼)
		btnPwUpdate = new JButton("비밀번호 변경");
		btnPwUpdate.setFont(new Font("굴림", Font.PLAIN, 11));
		btnPwUpdate.setBounds(315, 116, 107, 23);
		getContentPane().add(btnPwUpdate);

		// 수정 완료(버튼)
		btnUpdateCom = new JButton("수정 완료");
		btnUpdateCom.setBounds(98, 373, 107, 40);
		getContentPane().add(btnUpdateCom);

		// 취소(버튼)
		btnCancle = new JButton("취소");
		btnCancle.setBounds(240, 373, 107, 40);
		getContentPane().add(btnCancle);

		// 버튼 이벤트
		btnPwUpdate.addActionListener(this);
		btnUpdateCom.addActionListener(this);
		btnCancle.addActionListener(this);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnPwUpdate) { // 비밀번호 변경
			ChangePassword changePw = new ChangePassword(userId, userPw);
		} else if (e.getSource() == btnUpdateCom) {// 회원정보 수정

		} else {

		}
	}
}
