package tcpserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;

import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import java.net.Socket;
import java.util.ArrayList;

import java.util.TreeMap;

@SuppressWarnings("unchecked")
public class TCPClient1 {
	Socket s;

	public TCPClient1() {
		try {

//			s = new Socket("35.243.191.233", 9400);
			s = new Socket("localhost", 9400);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 회원가입 id 중복방지 확인
	public String idCheck(String id) {
		try {
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			out.println("idCheck\n" + id);

			BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String result = input.readLine();
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "false";
	}

	// 회원가입 정보 전송
	public String join(MemberDTO dto) {
		try {
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			out.println("join");
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(dto);
			oos.flush();

			BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String result = input.readLine();
			return result;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	// 로그인 확인
	public String loginCheck(String info) {
		try {
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			out.println("loginCheck\n" + info);

			BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String result = input.readLine();
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	// 회원 개인정보 가져오기
	public MemberDTO personalInfo(String id) throws Exception {
		PrintWriter out;
		try {
			out = new PrintWriter(s.getOutputStream(), true);
			out.println("personalInfo\n" + id);
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			MemberDTO dto = (MemberDTO) ois.readObject();
			return dto;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	// 아이디 찾기
	public String idFind(String info) {
		try {
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			out.println("idFind\n" + info);
			BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String result = input.readLine();
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	// 비밀번호 찾기
	public String pwFind(String info) {
		try {
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			out.println("pwFind\n" + info);
			System.out.println("send succes");
			BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String result = input.readLine();
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	// 책 등록하기
	public void bookRegist(String info) throws Exception {
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		out.println("bookRegist\n" + info);
	}

	// 전체회원 정보 얻어오기
	public ArrayList<MemberDTO> allMemberInfo() throws Exception {

		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		out.println("allMemberInfo");
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		ArrayList<MemberDTO> a = (ArrayList<MemberDTO>) ois.readObject();
		return a;

	}

	// 도서 정보 얻어오기
	public ArrayList<BookDTO> getBookInfo() throws Exception {

		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		out.println("bookInfo");
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		ArrayList<BookDTO> a = (ArrayList<BookDTO>) ois.readObject();

		return a;
	}

	// 책 종류 갖고오기
	public TreeMap<String, Integer> bookKind2() throws Exception {
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		out.println("bookKind2");
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

		TreeMap<String, Integer> a = (TreeMap<String, Integer>) ois.readObject();

		return a;

	}

	// 유저 정보 변경
	public String userInfoChange(String info) throws Exception {
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		out.println("userInfoChange\n" + info);
		BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String result = input.readLine();
		return result;
	}

	// 유저 이미지 올리기
	public String userImage(String id, String path) {
		try {
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			out.println("userImage\n" + id);
			byte buffer[] = new byte[2048];
			File imgfile = new File(path);
			String flen = String.valueOf(imgfile.length());
			String header = "0000000000".substring(0, 10 - flen.length()) + flen;
			System.out.println(2);

			FileInputStream fis = new FileInputStream(imgfile);
			OutputStream os = s.getOutputStream();
			os.write(header.getBytes());
			while (fis.available() > 0) {
				int readsz = fis.read(buffer);
				os.write(buffer, 0, readsz);
				System.out.println(3);
			}
			os.close();
			fis.close();
			System.out.println(4);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;

	}

	public ArrayList<String> bookSearch(String info) {
		// TODO Auto-generated method stub
		return null;
	}

	// 개인정보 수정 비밀번호 변경
	public String pwChange(String info) throws Exception {
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		out.println("pwChange\n" + info);
		BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String result = input.readLine();
		return result;

	}

	// 회원탈퇴
	public String memberDelete(String info) throws Exception {
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		out.println("memberDelete\n" + info);
		BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String result = input.readLine();
		return result;

	}

	// 유저 정보 변경
	public String userInfoChange_admin(String info) throws Exception {
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		out.println("userInfoChange_admin\n" + info);
		BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String result = input.readLine();
		return result;
	}

	public void test() throws Exception {
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		out.println("2");
		byte buffer[] = new byte[2048];

		File imgfile = new File("3.jpg");

		String flen = String.valueOf(imgfile.length());

		// change "1234" to "0000001234", to make sure 10 size.

		String header = "0000000000".substring(0, 10 - flen.length()) + flen;
		System.out.println(2);

		FileInputStream fis = new FileInputStream(imgfile);

		OutputStream os = s.getOutputStream();

		// send header

		os.write(header.getBytes());

		// send body

		while (fis.available() > 0) {

			int readsz = fis.read(buffer);

			os.write(buffer, 0, readsz);
			System.out.println(3);
		}

		os.close();

		fis.close();
		System.out.println(4);

		System.out.println("전송 끝");

	}

	// 개인 예약 정보 가져오기
	public ArrayList<ReservationDTO> userReservation(String id) throws Exception {
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		out.println("userReservation\n" + id);
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		ArrayList<ReservationDTO> a = (ArrayList<ReservationDTO>) ois.readObject();

		return a;

	}

	// 예약 정보 가져오기
	public ArrayList<ReservationDTO> getReservation() throws Exception {
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		out.println("reservation");
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		ArrayList<ReservationDTO> a = (ArrayList<ReservationDTO>) ois.readObject();

		return a;

	}

	// 개인 대출 정보 가져오기
	public ArrayList<RentDTO> userRentInfo(String id) throws Exception {
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		out.println("userRentInfo\n" + id);
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		ArrayList<RentDTO> a = (ArrayList<RentDTO>) ois.readObject();

		return a;
	}

	// 대출 하기
	public String insertRent(String id, String title, String date) throws Exception {
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		out.println("insertRent\n" + id + "\n" + title + "\n" + date);
		BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String result = input.readLine();
		return result;
	}

	// 예약하기
	public String insertReservation(String id, String title, String resDate) throws Exception {
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		out.println("insertReservation\n" + id + "\n" + title + "\n" + resDate);
		BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String result = input.readLine();
		return result;
	}

	// 대출 정보 가져오기
	public ArrayList<RentDTO> getRent() throws Exception {
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		out.println("rentInfo\n");
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		ArrayList<RentDTO> a = (ArrayList<RentDTO>) ois.readObject();

		return a;
	}

	// 예약 취소
	public void resCancel(String info) throws Exception {
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		out.println("resCancel\n" + info);

	}

	// 반납 정보 가져오기
	public ArrayList<ReturnDTO> getReturn() throws Exception {
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		out.println("getReturn\n");
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		ArrayList<ReturnDTO> a = (ArrayList<ReturnDTO>) ois.readObject();

		return a;
	}

	// 반납 확인
	public void returnCheck(String[] bookReturn) throws Exception {
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		out.println("returnCheck\n");
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(bookReturn);
		oos.flush();
		oos.close();
	}

	public void bookExtension(String title, String returnDay, int extensionDay) throws Exception {
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		out.println("bookExtension\n" + title + "\n" + returnDay + "\n" + extensionDay);

	}

	public void bookReturn(String id, String title) throws Exception {
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		out.println("bookReturn\n" + id + "\n" + title);

	}

}
