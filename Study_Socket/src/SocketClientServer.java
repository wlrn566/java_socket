import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class SocketClientServer {

	public static void main(String[] args) {
		// 클라이언트와 소켓 서버 연결
		/*
		  1. 소캣 서버를 연다
		  2. 클라이언트 서버를 열어서 닉네임을 받음(in2)
		  3. in2를 nick에 넣어 출력해줌 + 쓰레드 실행
		  4. while문으로 채팅 계속 돌려줌
		  5. 서버에서 받은 클라이언트 정보를 Receive에 넘겨줌 + 쓰레드 실행
		  6. Receive에서 User클래스의 addClient(사용자등록) 실행
		  7. 클라이언트에서 메세지 입력 받음(in)
		  8. 서버에서 받은 메세지를 Receive로 넘겨줌 
		  9. Receive에서 User클래스의 sendMsg를 실행 -> iterator로 닉네임이 존재하면 출력
		   https://freloha.tistory.com/44
		 */
		
		Socket socket = null;
		DataInputStream in = null; // 입력하는 채팅 부분
		BufferedReader in2 = null; // 사용자의 닉네임
		DataOutputStream out = null; // 입력한 데이터를 출력

		try {
			InetAddress ipAddr = null; // 로컬 IP 가져오기
			ipAddr = InetAddress.getLocalHost(); // 로컬 IP 할당
			socket = new Socket(ipAddr, 1234); // 소켓 서버에 접속
			System.out.println("소켓 서버 접속 성공!");

			in = new DataInputStream(socket.getInputStream());
			in2 = new BufferedReader(new InputStreamReader(System.in));
			out = new DataOutputStream(socket.getOutputStream());

			System.out.println("닉네임 입력 : ");
			String nick = in2.readLine(); // 닉네임

			out.writeUTF(nick); // 인코딩 후 출력스트림에 넣기
			Thread th = new Thread(new Send(out));
			th.start();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		try {
			// 서버가 끊기기 전까지 채팅을 받을 수 있도록 while문 생성
			while (true) {
				String str = in.readUTF();
				System.out.println(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
