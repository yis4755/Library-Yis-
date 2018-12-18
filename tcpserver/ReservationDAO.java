package tcpserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

public class ReservationDAO {
	Connection con;

	public ReservationDAO() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/library2";
		String user = "root";
		String password = "1234";
		con = DriverManager.getConnection(url, user, password);
	}

	// 예약 정보 가져오기
	public ArrayList<ReservationDTO> getReserInfo() throws Exception {
		String sql = "select * from reservation";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		ArrayList<ReservationDTO> info = new ArrayList<>();
		while (rs.next()) {
			String id = rs.getString("id");
			String title = rs.getString("title");
			String date = rs.getString("date");
			info.add(new ReservationDTO(id, title, date));
		}
		return info;
	}

	public String insertReservation(String id, String title, String resDate) throws Exception {

		String sql = "insert into reservation values (?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, id);
		ps.setString(2, title);
		ps.setString(3, resDate);

		int result = ps.executeUpdate();
		new BookDAO().rentBook(title);

		if (result == 1) {
			return "성공";
		} else {
			return "실패";
		}
	}

	// 유저 예약정보 가져오기
	public ArrayList<ReservationDTO> userReservation(String id) throws Exception {
		String sql = "select * from reservation where id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();

		ArrayList<ReservationDTO> list = new ArrayList<>();
		while (rs.next()) {
			id = rs.getString("id");
			String title = rs.getString("title");
			String date = rs.getString("date");
			list.add(new ReservationDTO(id, title, date));
		}
		return list;

	}

	// 예약 취소하기
	public void resCancel(String id, String title) throws Exception {
		String sql = "delete from reservation where id =? and title =?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, id);
		ps.setString(2, title);
		ps.executeUpdate();
		new BookDAO().resCancel(title);

	}

}
