package user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tcpserver.BookDTO;
import tcpserver.TCPClient1;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;

import javax.swing.table.DefaultTableModel;

/*
 *  일반 사용자 도서 검색
 */
@SuppressWarnings("serial")
public class UserBookSearch implements ActionListener, MouseListener {
	
	private JPanel jp;
	private JButton searchButton;
	private JComboBox<?> combo;
	private JTextField searchText;
	public ArrayList<BookDTO> bookInfo;
	private JScrollPane scrollPane;
	private JPanel tableMainPanel;
	public JTable infoTable;
	private JCheckBox chckbxNewCheckBox;

	public UserBookSearch() throws Exception {
		// 패널만들기
		jp = new JPanel();
		jp.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jp.setLayout(null);

		// 검색 메뉴 만들기 combo
		String[] searchMenu = { "책 제목", "저자" };
		combo = new JComboBox<>(searchMenu);
		combo.setBounds(12, 6, 82, 21);
		jp.add(combo);

		// 입력 받는 부분 만들기 jtext
		searchText = new JTextField(15);
		searchText.setBounds(106, 6, 225, 21);
		jp.add(searchText);

		// 검색 버튼 만들기
		searchButton = new JButton("검색");
		searchButton.setBounds(343, 5, 71, 23);
		jp.add(searchButton);

		// 대출 가능한 책만 보이게 하는 체크박스 만들기
		chckbxNewCheckBox = new JCheckBox("대출 가능한 책만");
		chckbxNewCheckBox.setBounds(422, 5, 139, 23);
		jp.add(chckbxNewCheckBox);

		// DB에서 책정보 가져오기
		bookInfo = new TCPClient1().getBookInfo();

		// 표 만들기 jtable
		String[] column = { "일련번호", "제목", "저자명", "발행처", "발행년도", "청구기호", "대출 여부" };
		String[][] row = new String[bookInfo.size()][column.length];

		// row값에 데이터 입력
		for (int i = 0; i < row.length; i++) {
			BookDTO dto = (BookDTO) bookInfo.get(i);
			row[i][0] = dto.getNumber();
			row[i][1] = dto.getTitle();
			row[i][2] = dto.getAuthor();
			row[i][3] = dto.getPublisher();
			row[i][4] = dto.getYear();
			row[i][5] = dto.getBill();
			row[i][6] = dto.getRent();
		}
		jp.setBounds(12, 10, 600, 670);
		searchButton.addActionListener(this);

		tableMainPanel = new JPanel();
		tableMainPanel.setBounds(12, 37, 580, 620);
		jp.add(tableMainPanel);
		tableMainPanel.setLayout(null);

		// 테이블에 데이터 입력
		DefaultTableModel dtm = new DefaultTableModel(row, column) { // 셀 수정 못하게 하는 부분
			public boolean isCellEditable(int a, int column) {
				return false;
			}
		};

		infoTable = new JTable(dtm);
		infoTable.addMouseListener(this);
		
		// 스크롤 pane에 테이블 추가
		scrollPane = new JScrollPane(infoTable);
		scrollPane.setBounds(0, 0, 580, 620);
		tableMainPanel.add(scrollPane);
		chckbxNewCheckBox.addActionListener(this);
		infoTable.getTableHeader().setReorderingAllowed(false); // 이동 불가
//		infoTable.getTableHeader().setResizingAllowed(false); //크기 조절 불가

	} // default constructor end

	// 메인 패널 반환
	public JPanel panel() {
		return jp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// 도서 검색 이벤트 처리
		tableMainPanel.removeAll();
		tableMainPanel.setBounds(12, 37, 580, 620);
		String[] column = { "일련번호", "제목", "저자명", "발행처", "발행년도", "청구기호", "대출 여부" };
		String[][] row = null;
		String select = combo.getSelectedItem().toString();

		// 체크 박스를 선택 했을 경우
		if (chckbxNewCheckBox.isSelected()) {
			ArrayList<BookDTO> list = new ArrayList<>();

			for (int i = 0; i < bookInfo.size(); i++) {
				BookDTO dto = (BookDTO) bookInfo.get(i);
				// 책 제목으로 검색 했을 경우
				if (select.equals("책 제목")) {
					// 대출 가능한 책만 데이타 출력
					if (dto.getRent().equals("Y")) {
						if (dto.getTitle().indexOf(searchText.getText()) != -1) {
							list.add(dto);
						}
					}
					
				// 저자 명으로 검색 했을 경우
				} else if (select.equals("저자")) {
					// 대출 가능한 책만 데이타 출력
					if (dto.getRent().equals("Y")) {
						if (dto.getAuthor().indexOf(searchText.getText()) != -1) {
							list.add(dto);
						}
					}
				}
			} // for

			// list에 있는 dto를 스트링 배열에 저장
			row = new String[list.size()][column.length];
			for (int i = 0; i < list.size(); i++) {
				BookDTO dto = (BookDTO) list.get(i);
				if (dto.getRent().equals("Y")) {
					row[i][0] = dto.getNumber();
					row[i][1] = dto.getTitle();
					row[i][2] = dto.getAuthor();
					row[i][3] = dto.getPublisher();
					row[i][4] = dto.getYear();
					row[i][5] = dto.getBill();
					row[i][6] = dto.getRent();
				}
			}
		} // if end
		
		// 체크 박스를 선택하지 않았을 경우
		else {
			ArrayList<BookDTO> list = new ArrayList<>();

			for (int i = 0; i < bookInfo.size(); i++) {
				BookDTO dto = (BookDTO) bookInfo.get(i);
				// 책 제목으로 검색 했을 경우
				if (select.equals("책 제목")) {
					if (dto.getTitle().indexOf(searchText.getText()) != -1) {
						list.add(dto);
					}
				// 저자 명으로 검색 했을 경우
				} else if (select.equals("저자")) {
					if (dto.getAuthor().indexOf(searchText.getText()) != -1) {
						list.add(dto);
					}
				}
			} // for
			
			// list에 있는 dto를 스트링 배열에 저장
			row = new String[list.size()][column.length];
			for (int i = 0; i < list.size(); i++) {
				BookDTO dto = (BookDTO) list.get(i);
				row[i][0] = dto.getNumber();
				row[i][1] = dto.getTitle();
				row[i][2] = dto.getAuthor();
				row[i][3] = dto.getPublisher();
				row[i][4] = dto.getYear();
				row[i][5] = dto.getBill();
				row[i][6] = dto.getRent();
			}
		} // else end

		DefaultTableModel dtm = new DefaultTableModel(row, column) { // 셀 수정 불가

			public boolean isCellEditable(int a, int column) {
				return false;
			}
		};
		infoTable = new JTable(dtm);
		infoTable.addMouseListener(this);
		scrollPane = new JScrollPane(infoTable);

		scrollPane.setBounds(0, 0, 580, 670);
		tableMainPanel.add(scrollPane);
		infoTable.setDragEnabled(false);
	} // actionPerformed end

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public String id;

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getClickCount() == 2) {
			int row = infoTable.getSelectedRow();

			// 제목 가져오기
			String title = (String) infoTable.getValueAt(row, 1);
			for (int i = 0; i < bookInfo.size(); i++) {
				BookDTO dto = (BookDTO) bookInfo.get(i);
				if (dto.getTitle().equals(title)) {
					if (dto.getRent().equals("Y")) {

						try {
							new UserReservationJFrame(id, title);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "이미 대출 중인 도서 입니다.", "예약 불가", JOptionPane.WARNING_MESSAGE);
					}

				}
				
			} // for
			
		} // if end
		
	} // mouseReleased end

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

} //class end
