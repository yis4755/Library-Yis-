package admin;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import tcpserver.RentDTO;

import tcpserver.TCPClient1;

/*
 *  책 대출 현황 표
 */

@SuppressWarnings("serial")
public class BookRent_admin {
	private JPanel mainPanel;
	private JScrollPane scrollPane;
	private JPanel tableMainPanel;
	public JTable infoTable;
	public DefaultTableModel dtm;

	public BookRent_admin() throws Exception {
		// 메인 패널 설정
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 582, 506);
		mainPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		// DB에서 대출예약정보 가져오기
		ArrayList<RentDTO> rentInfo = new TCPClient1().getRent();

		// 표 만들기
		String[] column = { "Id", "Title", "rentDay", "returnDay" };
		String[][] row = new String[rentInfo.size()][column.length];

		// row값에 데이터 입력
		for (int i = 0; i < row.length; i++) {
			RentDTO dto = (RentDTO) rentInfo.get(i);
			row[i][0] = dto.getId();
			row[i][1] = dto.getTitle();
			row[i][2] = dto.getRentDay();
			row[i][3] = dto.getReturnDay();
		}

		// 테이블에 데이터 입력
		dtm = new DefaultTableModel(row, column) { // 셀 수정 못하게 하는 부분
			public boolean isCellEditable(int a, int column) {
				return false;
			}
		};
		infoTable = new JTable(dtm);

		// 표를 넣을 패널 설정
		tableMainPanel = new JPanel();
		tableMainPanel.setBounds(12, 37, 461, 456);
		tableMainPanel.setLayout(null);
		mainPanel.add(tableMainPanel);
		
		// 스크롤 pane에 테이블 추가
		scrollPane = new JScrollPane(infoTable);
		scrollPane.setBounds(0, 0, 461, 456);
		tableMainPanel.add(scrollPane);
		infoTable.getTableHeader().setReorderingAllowed(false); // 이동 불가

	} // default constructor end

	public JPanel getPanel() {

		return mainPanel;
	}

} // class end
