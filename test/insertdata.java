package test;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import jxl.Sheet;

import jxl.Workbook;

public class insertdata {

	public static void main(String[] args) throws Exception {
		// 파일 객체 생성 - 엑셀파일 경로

		File file = new File("C:\\3.xls");

		// 엑셀파일 워크북 객체 생성

		Workbook workbook = Workbook.getWorkbook(file);

		// 시트 지정

		Sheet sheet = workbook.getSheet(0);

		int rowCount = sheet.getRows();
		int colCount = sheet.getColumns();
		
		// 1. 드라이버(connector) 셋팅
		Class.forName("com.mysql.jdbc.Driver");

		// 2. DB연결(->my서버설정+db명+id+pw)
		String url = "jdbc:mysql://localhost:3306/library";
		String user = "root";
		String password = "1234";

		Connection con = DriverManager.getConnection(url, user, password);

		// 3. SQL문 객체화
		String sql = "insert into book values (?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		System.out.println(sheet.getCell(1,0).getContents());
//		ps.setString(1, sheet.getCell(0,0).getContents());
//		ps.setString(2, "ㄱ");
//		ps.setString(3, "123123");
//		ps.setString(4, "123123");
//		ps.setString(5, "123123");
//		ps.setString(6, "123123");
		System.out.println(sheet.getCell(0, 0).getContents());
		for (int i = 0; i < 3193; i++) {
			for (int j = 0; j < colCount; j++) {
				
				ps.setString(j+1, sheet.getCell(j, i).getContents());
			}
			ps.executeUpdate();
		}
		// 4. SQL문 실행 요청

//		ps.executeUpdate();
		System.out.println(11);
	}

}
