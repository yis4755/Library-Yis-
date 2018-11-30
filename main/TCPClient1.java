package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient1 {
	Socket s;
	public TCPClient1() {
		try {
			s = new Socket("104.196.4.68", 9100);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("client start!!!");
	}
	
	//로그인 확인
	public String loginCheck(String info) {
		try {	
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			out.println(info);
			System.out.println("send succes");
			BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String result = input.readLine();
			s.close();
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "0";
	}
	
	//id 중복방지 확인
	public String idCheck2(String id) {
		try {	
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			out.println(id);
			System.out.println("send succes");
			BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String result = input.readLine();
			s.close();
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "0";
	}

	//회원가입 정보 전송
	public String join(String info) {
		try {	
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			out.println(info);
			System.out.println("send succes");
			BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String result = input.readLine();
			s.close();
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "0";
		
	}

		//아이디 찾기
	public String idFind(String info) {
		try {	
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			out.println(info);
			System.out.println("send succes");
			BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String result = input.readLine();
			s.close();
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "0";
		
	}

	public String pwFind(String info) {
		try {	
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			out.println(info);
			System.out.println("send succes");
			BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String result = input.readLine();
			s.close();
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "0";
	}
	
	//	비밀번호 변경
	public String pwChange(String info) {
		try {
			PrintWriter out  = new PrintWriter(s.getOutputStream(), true);
			out.println(info);
			System.out.println("send succes");
			BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String result = input.readLine();
			s.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "0";
	}
	
}






//		OutputStream out = s.getOutputStream();
//		ObjectOutputStream dos = new ObjectOutputStream(out);
//		
//		dos.writeObject(m);
//		dos.close();