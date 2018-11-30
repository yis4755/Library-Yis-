package main;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Id_PwFindJFrame extends JFrame implements ActionListener {
	private JTextField nameField;
	private JTextField telField;
	private JTextField idField;
	private JTextField nameField1;
	JButton btnFind;
	JButton btnFind1;

	public Id_PwFindJFrame() {
		setTitle("아이디/비밀번호 찾기");
		setSize(400, 550);
		getContentPane().setLayout(null);

		JTabbedPane find = new JTabbedPane(JTabbedPane.TOP);
		find.setBounds(12, 10, 360, 491);
		// 아이디, 비밀번호 찾기 탭 추가
		JPanel idPan = new JPanel();
		idPan.setLayout(null);
		idPan.setBounds(10, 10, 380, 530);

		JPanel pwPan = new JPanel();
		pwPan.setLayout(null);
		pwPan.setBounds(10, 10, 380, 530);
		find.add("아이디 찿기", idPan);
		find.add("비밀번호 찿기", pwPan);

		JLabel idLab = new JLabel("아이디");
		idLab.setFont(new Font("굴림", Font.PLAIN, 14));
		idLab.setBounds(32, 122, 56, 15);
		pwPan.add(idLab);

		idField = new JTextField();
		idField.setColumns(10);
		idField.setBounds(106, 120, 237, 21);
		pwPan.add(idField);

		JLabel nameLab1 = new JLabel("이름");
		nameLab1.setFont(new Font("굴림", Font.PLAIN, 14));
		nameLab1.setBounds(32, 183, 56, 15);
		pwPan.add(nameLab1);

		nameField1 = new JTextField();
		nameField1.setColumns(10);
		nameField1.setBounds(106, 181, 237, 21);
		pwPan.add(nameField1);

		btnFind1 = new JButton("찾기");
		btnFind1.setBounds(106, 315, 125, 37);
		btnFind1.addActionListener(this);
		pwPan.add(btnFind1);
		getContentPane().add(find);

		JLabel nameLab = new JLabel("이름");
		nameLab.setFont(new Font("굴림", Font.PLAIN, 14));
		nameLab.setBounds(32, 122, 41, 15);
		idPan.add(nameLab);

		nameField = new JTextField();
		nameField.setBounds(106, 120, 237, 21);
		idPan.add(nameField);
		nameField.setColumns(10);

		JLabel telLab = new JLabel("전화번호");
		telLab.setFont(new Font("굴림", Font.PLAIN, 14));
		telLab.setBounds(32, 183, 56, 15);
		idPan.add(telLab);

		telField = new JTextField();
		telField.setColumns(10);
		telField.setBounds(106, 181, 237, 21);
		idPan.add(telField);

		btnFind = new JButton("찾기");
		btnFind.setBounds(106, 301, 125, 37);
		btnFind.addActionListener(this);
		idPan.add(btnFind);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnFind) {
			String name = nameField.getText();
			String tel = telField.getText();
			String result = new TCPClient1().idFind(name + "\n" + tel);
			if (result == "결과값") {
				
			} else {

			}
		} else if (e.getSource() == btnFind1) {
			String id = idField.getText();
			String name = nameField1.getText();
			String result = new TCPClient1().pwFind(id + "\n" + name);
		}

	}
}
