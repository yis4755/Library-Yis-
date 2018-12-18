package tcpserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.util.TreeMap;

public class BookDAO {
	Connection con;

	public BookDAO() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/library2";
		String user = "root";
		String password = "1234";
		con = DriverManager.getConnection(url, user, password);
	}

//책 정보 가져오기
	public ArrayList<BookDTO> bookInfo() throws Exception {
		String sql = "select * from book";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		ArrayList<BookDTO> info = new ArrayList<BookDTO>();
		while (rs.next()) {
			String number = rs.getString(1);
			String title = rs.getString(2);
			String author = rs.getString(3);
			String publisher = rs.getString(4);
			String year = rs.getString(5);
			String bill = rs.getString(6);
			String rent = rs.getString(7);
			info.add(new BookDTO(number, title, author, publisher, year, bill, rent));
		}
		return info;
	}

	// 책 종류 구분
	public TreeMap<String, Integer> bookKind2() throws Exception {
		String sql = "select bill from book";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		int[] count = new int[12];
		while (rs.next()) {
			count[11]++;

			switch (rs.getString(1).charAt(1)) { // 코드 줄일수 잇을것같음
			case '0':
				count[0]++;
				break;
			case '1':
				count[1]++;
				break;
			case '2':
				count[2]++;
				break;
			case '3':
				count[3]++;
				break;
			case '4':
				count[4]++;
				break;
			case '5':
				count[5]++;
				break;
			case '6':
				count[6]++;
				break;
			case '7':
				count[7]++;
				break;
			case '8':
				count[8]++;
				break;
			case '9':
				count[9]++;
				break;

			default:
				count[10]++;
				break;
			}

		}
		TreeMap<String, Integer> map = new TreeMap<>();
		map.put("총류", count[0]);
		map.put("철학", count[1]);
		map.put("종교", count[2]);
		map.put("사회과학", count[3]);
		map.put("자연과학", count[4]);
		map.put("기술과학", count[5]);
		map.put("예술", count[6]);
		map.put("언어", count[7]);
		map.put("문학", count[8]);
		map.put("역사", count[9]);
		map.put("기타", count[10]);

		return map;

	}

	// 책 예약
	public int rentBook(String title) throws Exception {

		String sql = "update book set rent = ? where title = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, "N");
		ps.setString(2, title);
		return ps.executeUpdate();

	}

	// 책 예약 취소
	public void resCancel(String title) throws Exception {
		String sql = "update book set rent = ? where title = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, "Y");
		ps.setString(2, title);
		ps.executeUpdate();

	}

	// 책 등록하기
	public void bookRegist(String number, String title, String author, String publisher, String year, String bill)
			throws Exception {
		String sql = "insert into bookinfo values (?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, number);
		ps.setString(2, title);
		ps.setString(3, author);
		ps.setString(4, publisher);
		ps.setString(5, year);
		ps.setString(6, bill);
		ps.setString(7, "Y");
		ps.executeUpdate();

	}

}
