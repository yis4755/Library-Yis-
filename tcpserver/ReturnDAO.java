package tcpserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ReturnDAO {
	Connection con;

	public ReturnDAO() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/library2";
		String user = "root";
		String password = "1234";
		con = DriverManager.getConnection(url, user, password);
	}

	// 반납 정보 가져오기
	public ArrayList<ReturnDTO> getReturn() throws Exception {
		String sql = "select * from bookreturn";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		ArrayList<ReturnDTO> info = new ArrayList<>();
		while (rs.next()) {
			String id = rs.getString("id");
			String title = rs.getString("title");
			String applyDay = rs.getString("applyday");

			info.add(new ReturnDTO(id, title, applyDay));
		}
		return info;
	}

	// 반납 확인
	public void returnCheck(String[] returnCheck) throws Exception {
		String sql = "delete from bookreturn where title = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		for (int i = 0; i < returnCheck.length; i++) {
			ps.setString(1, returnCheck[i]);
			ps.executeUpdate();
			new BookDAO().resCancel(returnCheck[i]);
			new RentDAO().returnCheck(returnCheck[i]);
		}

	}

	//
	public void bookReturn(String id, String title) throws Exception {
		String sql = "insert into bookreturn values(?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		// 날짜 더하기

		Calendar cal = Calendar.getInstance();

		String applyDay = df.format(cal.getTime());
		ps.setString(1, id);
		ps.setString(2, title);
		ps.setString(3, applyDay);
		ps.executeUpdate();
	}

}
