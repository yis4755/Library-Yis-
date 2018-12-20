package test;

import java.io.File;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.util.Random;

import jxl.Sheet;

import jxl.Workbook;


public class InsertMember {

	public static void main(String[] args) throws Exception {

		// 1. 드라이버(connector) 셋팅
		Class.forName("com.mysql.jdbc.Driver");

		// 2. DB연결(->my서버설정+db명+id+pw)
		String url = "jdbc:mysql://localhost:3306/library";
		String user = "root";
		String password = "1234";

		Connection con = DriverManager.getConnection(url, user, password);

		System.out.println(new InsertMember().id());
		System.out.println(new InsertMember().name());
		System.out.println(new InsertMember().address());
		System.out.println(new InsertMember().phone());
		System.out.println(new InsertMember().rrn());
		
		
			
		
		// 3. SQL문 객체화
		String sql = "insert into members values (?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		
		
		for (int i = 0; i < 100; i++) {
			ps.setString(1, new InsertMember().id());
			ps.setString(2, new InsertMember().id());
			ps.setString(3, new InsertMember().name());
			ps.setString(4, new InsertMember().phone());
			ps.setString(5, new InsertMember().address());
			ps.setString(6, "0");
			ps.setString(7, "0");
			ps.setString(8, "0");
			ps.setString(9, (new InsertMember().rrn()));
			ps.executeUpdate();
		}
//		 4. SQL문 실행 요청


	}

	public String id() {
		Random r = new Random();
		String id = "";
		for (int i = 0; i < 5; i++) {
			char a = (char) (r.nextInt(26) + 97);
			id += a;
		}
		int b = r.nextInt(20) + 1;
		id += b;
		return id;
	}

	public String name() throws Exception {
		// 파일 객체 생성 - 엑셀파일 경로
		Random r = new Random();
		File file = new File("C:\\566.xls");

		// 엑셀파일 워크북 객체 생성

		Workbook workbook = Workbook.getWorkbook(file);

		// 시트 지정

		Sheet sheet = workbook.getSheet(0);
		int rowCount = sheet.getRows();
		int colCount = sheet.getColumns();

		String name = "";
		name += sheet.getCell(2, r.nextInt(88)).getContents(); // B 0
		name += sheet.getCell(r.nextInt(2), r.nextInt(400)).getContents(); // B 0
		return name;
	}

	public String address() throws Exception {
		// 파일 객체 생성 - 엑셀파일 경로
		Random r = new Random();
		File file = new File("C:\\566.xls");

		// 엑셀파일 워크북 객체 생성

		Workbook workbook = Workbook.getWorkbook(file);

		// 시트 지정

		Sheet sheet = workbook.getSheet(0);
		int rowCount = sheet.getRows();
		int colCount = sheet.getColumns();

		String address = "서울시 ";
		address += sheet.getCell(3, r.nextInt(467)).getContents(); // B 0

		return address;
	}

	public String phone() {
		Random r = new Random();
		String phone = "010";
		phone += r.nextInt(8999) + 1000;
		phone += r.nextInt(8999) + 1000;
		return phone;
	}

	public String rrn() throws Exception {
		Random r = new Random();
		String rrn = "";
		rrn += r.nextInt(59) + 40;
		rrn += r.nextInt(2);
		// 주민번호 월
		if (rrn.charAt(2) == '0') {
			rrn += r.nextInt(9) + 1;
		} else {
			rrn += r.nextInt(2) + 1;
		}

		// 일
		// 2월달 예외 두기

		if (rrn.charAt(2) == '0' && rrn.charAt(3) == '2') {
			rrn += r.nextInt(3); // 앞자리 0,1,2
			rrn += r.nextInt(8) + 1; // 뒷자리 8까지만
		} else {

			rrn += r.nextInt(4); // 앞자리 0,1,2,3
			if (rrn.charAt(4) == '3') {
				if (((int) (rrn.charAt(3) % 2) == 1)) {
					rrn += 0;
				} else {
					rrn += 1;
				}
			} else {

				rrn += r.nextInt(9) + 1;
			}
		}
		
		//앞자리 생성 완료
		rrn += r.nextInt(2)+1;
		rrn += r.nextInt(9);
		rrn += r.nextInt(9);
		rrn += r.nextInt(9);
		rrn += r.nextInt(9);
		rrn += r.nextInt(9);
		rrn += r.nextInt(9);
		return rrn;
	}
}
