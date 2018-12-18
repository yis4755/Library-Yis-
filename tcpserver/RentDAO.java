package tcpserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RentDAO {
	Connection con;

	public RentDAO() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/library2";
		String user = "root";
		String password = "1234";
		con = DriverManager.getConnection(url, user, password);
	}

	// 개인 대출 정보 가져오기
	public ArrayList<RentDTO> userRent(String id) throws Exception {
		String sql = "select * from rent where id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();

		ArrayList<RentDTO> info = new ArrayList<>();
		while (rs.next()) {
			id = rs.getString("id");
			String title = rs.getString("title");
			String rentDay = rs.getString("rentDay");
			String returnDay = rs.getString("returnDay");
			String extensionDay = rs.getString("extensionDay");
			info.add(new RentDTO(id, title, rentDay, returnDay, extensionDay));
		}
		return info;
	}

	// 대출 하기
	public String insertRent(String id, String title, String rentDay) throws Exception {
		String sql = "insert into rent values (?,?,?,?,?)";

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		Date date = df.parse(rentDay);

		// 날짜 더하기

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 7);
//		cal.add(Calendar.MONTH, 2);

		String returnDay = df.format(cal.getTime());

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, id);
		ps.setString(2, title);
		ps.setString(3, rentDay);
		ps.setString(4, returnDay);
		ps.setString(5, "0");

		int result = ps.executeUpdate();
		new ReservationDAO().resCancel(id, title);

		if (result == 1) {
			if (new BookDAO().rentBook(title) == 1) {
				return "성공";

			} else {
				return "실패";
			}
		} else {
			return "실패";
		}

	}

	public ArrayList<RentDTO> getRent() throws Exception {
		String sql = "select * from rent";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		ArrayList<RentDTO> info = new ArrayList<>();
		while (rs.next()) {
			String id = rs.getString("id");
			String title = rs.getString("title");
			String rentDay = rs.getString("rentday");
			String returnDay = rs.getString("returnday");
			String extensionDay = rs.getString("extensionDay");
			info.add(new RentDTO(id, title, rentDay, returnDay, extensionDay));
		}
		return info;
	}

	public void bookExtension(String title, String returnDay, String extensionDay) throws Exception {
		String sql = "update rent set returnday = ?,extensionday = ? where title = ?";
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, returnDay);
		ps.setString(2, extensionDay);
		ps.setString(3, title);
		ps.executeUpdate();
	}

	// 반납 확인으로 대출 목록에서 삭제
	public void returnCheck(String title) throws Exception {
		String sql = "delete from rent where title = ?";
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, title);
		ps.executeUpdate();

	}

}
