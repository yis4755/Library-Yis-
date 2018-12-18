package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JComboBox;

import javax.swing.JPanel;
import javax.swing.JTextField;

import tcpserver.BookDTO;
import tcpserver.TCPClient1;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;

import javax.swing.table.DefaultTableModel;

/*
 * 도서 검색
 */

@SuppressWarnings("serial")
public class BookSearch_admin implements ActionListener {
	private JPanel mainPanel;
	private JButton searchButton;
	private JComboBox<?> combo;
	private JTextField searchText;
	private JScrollPane scrollPane;
	private JPanel tableMainPanel;
	private JTable infoTable;

	public BookSearch_admin() throws Exception {
		// 패널만들기
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(12, 10, 582, 506);
		mainPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		// 검색 메뉴 만들기 combo
		String[] searchMenu = { "책 제목", "저자" };
		combo = new JComboBox<>(searchMenu);
		combo.setBounds(12, 6, 82, 21);
		mainPanel.add(combo);

		// 입력 받는 부분 만들기
		searchText = new JTextField(15);
		searchText.setBounds(106, 6, 225, 21);
		mainPanel.add(searchText);

		// 검색 버튼 만들기
		searchButton = new JButton("검색");
		searchButton.setBounds(343, 5, 71, 23);
		mainPanel.add(searchButton);

		// DB에서 책정보 가져오기
		ArrayList<?> bookInfo = new TCPClient1().getBookInfo();

		// 표 만들기 jtable
		String[] column = { "일련번호", "제목", "저자명", "발행처", "발행년도", "청구기호" };
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
		}

		searchButton.addActionListener(this);

		// 표를 넣을 패널 생성
		tableMainPanel = new JPanel();
		tableMainPanel.setLayout(null);
		tableMainPanel.setBounds(12, 37, 558, 456);
		mainPanel.add(tableMainPanel);

		// 테이블에 데이터 입력
		DefaultTableModel dtm = new DefaultTableModel(row, column) { // 셀 수정 못하게 하는 부분
			public boolean isCellEditable(int a, int column) {
				return false;
			}
		};
		infoTable = new JTable(dtm);

		// 스크롤 pane에 테이블 추가
		scrollPane = new JScrollPane(infoTable);
		scrollPane.setBounds(0, 0, 558, 456);
		tableMainPanel.add(scrollPane);
		infoTable.getTableHeader().setReorderingAllowed(false); // 이동 불가

	} // default constructor end

	// 메인 패널 반환
	public JPanel getPanel() {
		return mainPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// 도서 검색 버튼을 눌렀을 경우
		if (e.getSource() == searchButton) {
			ArrayList<?> bookInfo = null;
			try {
				bookInfo = new TCPClient1().getBookInfo();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			String select = combo.getSelectedItem().toString();
			tableMainPanel.removeAll();
			tableMainPanel.setBounds(12, 37, 525, 456);

			// 책 제목으로 검색했을 경우
			if (select.equals("책 제목")) {
				ArrayList<BookDTO> list = new ArrayList<>();
				for (int i = 0; i < bookInfo.size(); i++) {
					BookDTO dto = (BookDTO) bookInfo.get(i);
					if (dto.getTitle().indexOf(searchText.getText()) != -1) {

						list.add(dto);
					}

				} // for

				String[] column = { "일련번호", "제목", "저자명", "발행처", "발행년도", "청구기호" };

				// list에 있는 dto를 스트링 배열에 저장
				String[][] row2 = new String[list.size()][column.length];
				for (int i = 0; i < list.size(); i++) {
					BookDTO dto = (BookDTO) list.get(i);
					row2[i][0] = dto.getNumber();
					row2[i][1] = dto.getTitle();
					row2[i][2] = dto.getAuthor();
					row2[i][3] = dto.getPublisher();
					row2[i][4] = dto.getYear();
					row2[i][5] = dto.getBill();
				}
				DefaultTableModel dtm = new DefaultTableModel(row2, column) { // 셀 수정 못하게 하는 부분
					public boolean isCellEditable(int a, int column) {
						return false;
					}
				};
				infoTable = new JTable(dtm);

				scrollPane = new JScrollPane(infoTable);
				scrollPane.setBounds(0, 0, 525, 456);
				tableMainPanel.add(scrollPane);
				infoTable.setDragEnabled(false);
			} // if end

			// 저자 명으로 검색 했을 경우
			else if (select.equals("저자")) {
				ArrayList<BookDTO> list = new ArrayList<>();
				for (int i = 0; i < bookInfo.size(); i++) {
					BookDTO dto = (BookDTO) bookInfo.get(i);
					if (dto.getAuthor().indexOf(searchText.getText()) != -1) {

						list.add(dto);
					}

				} // for

				String[] column = { "일련번호", "제목", "저자명", "발행처", "발행년도", "청구기호" };

				// list에 있는 dto를 스트링 배열에 저장
				String[][] row2 = new String[list.size()][column.length];
				for (int i = 0; i < list.size(); i++) {
					BookDTO dto = (BookDTO) list.get(i);
					row2[i][0] = dto.getNumber();
					row2[i][1] = dto.getTitle();
					row2[i][2] = dto.getAuthor();
					row2[i][3] = dto.getPublisher();
					row2[i][4] = dto.getYear();
					row2[i][5] = dto.getBill();
				}
				DefaultTableModel dtm = new DefaultTableModel(row2, column) { // 셀 수정 못하게 하는 부분
					public boolean isCellEditable(int a, int column) {
						return false;
					}
				};
				infoTable = new JTable(dtm);
				scrollPane = new JScrollPane(infoTable);

				scrollPane.setBounds(0, 0, 525, 456);
				tableMainPanel.add(scrollPane);
				infoTable.setDragEnabled(false);
			} // else if (select.equals("저자") end

		} // if(e.getsource==searchButton) end

	} // actionPerformed end

} // class end
