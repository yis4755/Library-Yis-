package tcpserver;

import java.awt.image.BufferedImage;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.io.PrintWriter;

import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import java.util.TreeMap;

import javax.imageio.ImageIO;

public class Server {

	Socket socket;
	BufferedReader input;

	@SuppressWarnings("resource")
	public Server() throws Exception {
		ServerSocket serverSocket = new ServerSocket(9400);
		while (true) {
			socket = serverSocket.accept();
			checkRequest();
			socket.close();
		}
	}

	// 어떤 요청인지 판별하기
	public void checkRequest() throws Exception {
		input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String info = input.readLine();

		// 책 등록하기
		if (info.equals("bookRegist")) {
			bookRegist();

			// 전체 회원정보 가져오기
		} else if (info.equals("allMemberInfo")) {
			allMemberInfo();
			// 회원 개인정보 가져오기
		} else if (info.equals("personalInfo")) {
			personalInfo();
			// 책정보 가져오기
		} else if (info.equals("bookInfo")) {
			bookInfo();
			// 책종류 가져오기
		} else if (info.equals("bookKind2")) {
			bookKind2();
			// 회원가입하기
		} else if (info.equals("join")) {
			memberJoin();
			// 회원가입 ID중복 확인
		} else if (info.equals("idCheck")) {
			idCheck();
			// 로그인 확인
		} else if (info.equals("loginCheck")) {
			loginCheck();
			// 개인정보 수정
		} else if (info.equals("userInfoChange")) {
			userInfoChange();
			// 개인정보 비밀번호 변경
		} else if (info.equals("pwChange")) {
			pwChange();
			// 회원 탈퇴
		} else if (info.equals("memberDelete")) {
			memberDelete();
			// 아이디 찾기
		} else if (info.equals("idFind")) {
			idFind();
			// 비밀번호 찾기
		} else if (info.equals("pwFind")) {
			pwFind();
			// 관리자 유저 정보 변경
		} else if (info.equals("userInfoChange_admin")) {
			userInfoChange_admin();
			// 유저 이미지 변경
		} else if (info.equals("2")) {
			userImage2();
			// 예약 정보
		} else if (info.equals("reservation")) {
			getReservation();
			// 개인 대출 정보
		} else if (info.equals("userRentInfo")) {
			userRent();
			// 대출 하기
		} else if (info.equals("insertRent")) {
			insertRent();
			// 예약 하기
		} else if (info.equals("insertReservation")) {
			insertReservation();
			// 대출 정보 가져오기
		} else if (info.equals("rentInfo")) {
			rentInfo();
			// 개인 예약 정보 가져오기
		} else if (info.equals("userReservation")) {
			userReservation();
			// 예약 취소하기
		} else if (info.equals("resCancel")) {
			resCancel();
			// 반납 정보가져오기
		} else if (info.equals("getReturn")) {
			getReturn();
			// 반납 확인
		} else if (info.equals("returnCheck")) {
			returnCheck();
			// 연장 하기
		} else if (info.equals("bookExtension")) {
			bookExtension();
			// 책 반납 신청하기
		} else if (info.equals("bookReturn")) {
			bookReturn();
		}
	}

	public void bookReturn() throws Exception {
		String id = input.readLine();
		String title = input.readLine();
		new ReturnDAO().bookReturn(id,title);
		
	}

	//연장 하기
	public void bookExtension() throws Exception {
		String title = input.readLine();
		String returnDay = input.readLine();
		String extensionDay = input.readLine();
		new RentDAO().bookExtension(title, returnDay, extensionDay);
	}

	// 반납 확인
	public void returnCheck() throws Exception {
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		String[] returnCheck = (String[]) ois.readObject();
		new ReturnDAO().returnCheck(returnCheck);
	}

	// 반납 정보 가져오기
	public void getReturn() throws Exception {
		ArrayList<ReturnDTO> d = new ReturnDAO().getReturn();
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

		oos.writeObject(d);
		oos.flush();
		oos.close();

	}

	// 예약 취소하기
	public void resCancel() throws Exception {
		String id = input.readLine();
		String title = input.readLine();
		new ReservationDAO().resCancel(id, title);

	}

	// 개인 예약정보
	public void userReservation() throws Exception {
		String id = input.readLine();
		ArrayList<ReservationDTO> list = new ReservationDAO().userReservation(id);
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

		oos.writeObject(list);
		oos.flush();
		oos.close();

	}

	// 대출 정보 가져오기
	public void rentInfo() throws Exception {
		ArrayList<RentDTO> d = new RentDAO().getRent();
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

		oos.writeObject(d);
		oos.flush();
		oos.close();

	}

	// 예약하기
	public void insertReservation() throws Exception {
		String id = input.readLine();
		String title = input.readLine();
		String resDate = input.readLine();
		String result = new ReservationDAO().insertReservation(id, title, resDate);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println(result);
		out.close();
	}

	// 대출하기
	public void insertRent() throws Exception {
		String id = input.readLine();
		String title = input.readLine();
		String date = input.readLine();
		String result = new RentDAO().insertRent(id, title, date);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println(result);
		out.close();
	}

	// 개인 대출 정보
	public void userRent() throws Exception {
		String id = input.readLine();
		ArrayList<RentDTO> d = new RentDAO().userRent(id);
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

		oos.writeObject(d);
		oos.flush();
		oos.close();

	}

	// 예약정보 가져오기
	public void getReservation() throws Exception {
		ArrayList<ReservationDTO> d = new ReservationDAO().getReserInfo();
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

		oos.writeObject(d);
		oos.flush();
		oos.close();

	}

	// 유저 이미지 변경
	public void userImage() throws Exception {
		String id = input.readLine();
		InputStream inputStream = socket.getInputStream();
		byte[] sizeAr = new byte[4];
		inputStream.read(sizeAr);
		int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
		byte[] imageAr = new byte[size];
		inputStream.read(imageAr);
		BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));
		String IMAGE_PATH = "/var/www/html/" + id + ".jpg"; // 파일 포맷 일치 필요
		ImageIO.write(image, "jpg", new File(IMAGE_PATH)); // 파일 포맷 일치 필요

	}

//	//유저 이미지 변경 22
	public void userImage2() throws Exception {
		FileOutputStream fos = new FileOutputStream("37.jpg");

		InputStream is = socket.getInputStream();

		byte buffer[] = new byte[2048];

		// read header(10 bytes)

		is.read(buffer, 0, 10);

		String header = new String(buffer, 0, 10);

		int bodysize = Integer.parseInt(header);

		int readsize = 0;

		// read body

		while (readsize < bodysize) {

			int rsize = is.read(buffer);

			fos.write(buffer, 0, rsize);

			readsize += rsize;

		}

		is.close();

		fos.close();
		System.out.println("받기끝");
	}

	// 관리자 유저 정보 변경
	public void userInfoChange_admin() throws Exception {
		String id = input.readLine();
		String pw = input.readLine();
		String name = input.readLine();
		String tel = input.readLine();
		String address = input.readLine();
		String result = new MemberDAO().userInfoChange_admin(id, pw, name, tel, address);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println(result);
		out.close();

	}

	// 비밀번호 찾기
	public void pwFind() throws Exception {
		String id = input.readLine();
		String name = input.readLine();
		String result = new MemberDAO().pwFind(id, name);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println(result);
		out.close();

	}

	// 아이디 찾기
	public void idFind() throws Exception {
		String name = input.readLine();
		String tel = input.readLine();
		String result = new MemberDAO().idFind(name, tel);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println(result);
		out.close();
	}

	// 회원 탈퇴
	public void memberDelete() throws Exception {
		String id = input.readLine();
		String result = new MemberDAO().memberDelete(id);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println(result);
		out.close();

	}

	// 개인정보 비밀번호 변경하기
	public void pwChange() throws Exception {
		String id = input.readLine();
		String pw = input.readLine();
		String result = new MemberDAO().pwChange(id, pw);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println(result);
		out.close();
	}

	// 개인정보 수정하기
	public void userInfoChange() throws Exception {
		String id = input.readLine();
		String name = input.readLine();
		String tel = input.readLine();
		String address = input.readLine();
		String result = new MemberDAO().userInfoChange(id, name, tel, address);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println(result);
		out.close();
	}

	// 개인정보 가져오기
	public void personalInfo() throws Exception {
		String id = input.readLine();
		MemberDTO dto = new MemberDAO().personalInfo(id);
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(dto);
		oos.flush();
		oos.close();
	}

	// 회원가입 id 중복 확인하기
	public void idCheck() throws Exception {
		String id = input.readLine();
		String result = new MemberDAO().idCheck(id);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println(result);
		out.close();
	}

	// 회원가입
	public void memberJoin() throws Exception {
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		MemberDTO dto = (MemberDTO) ois.readObject();
		String result = new MemberDAO().join(dto);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println(result);
		ois.close();
		out.close();
	}

	// 로그인 확인
	public void loginCheck() throws Exception {
		String id = input.readLine();
		String pw = input.readLine();
		String result = new MemberDAO().loginCheck(id, pw);

		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println(result);
		out.close();

	}

	// 책 종류 가져오기
	public void bookKind2() throws Exception {
		// TODO Auto-generated method stub
		TreeMap<String, Integer> bookKind = new BookDAO().bookKind2();
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(bookKind);
		oos.flush();
		oos.close();
	}

	// 책 정보 가져오기
	public void bookInfo() throws Exception {
		ArrayList<BookDTO> d = new BookDAO().bookInfo();
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

		oos.writeObject(d);
		oos.flush();
		oos.close();

	}

	// 회원정보 가져오기
	public void allMemberInfo() throws Exception {
		ArrayList<MemberDTO> d = new MemberDAO().allMemberInfo();
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

		oos.writeObject(d);
		oos.flush();
		oos.close();
	}

	// 책 등록하기
	public void bookRegist() throws Exception {
		String number = input.readLine();
		String title = input.readLine();
		String author = input.readLine();
		String publisher = input.readLine();
		String year = input.readLine();
		String bill = input.readLine();
		
		new BookDAO().bookRegist(number, title, author, publisher, year, bill);
	
	}

	public static void main(String[] args) throws Exception {
		new Server();
	}
}
