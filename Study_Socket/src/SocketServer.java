import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	public static void main(String[] args) {
		// 소켓 서버
		try {
			// 포트 번호
			int port = 1234;
			// 소켓 생성
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println(port + " 서버 생성 성공!");
			Socket socketUser = null; // 클라이언트용 소켓

			// 서버는 계속 돌아가야하므로 while문 처리
			while (true) {
				socketUser = serverSocket.accept(); // 소켓 서버 접속 시 socket으로 보내줌
				System.out.println("접속 : " + socketUser.getLocalAddress()); // 서버 접속자의 로컬주소

				// 클라이언트 -> 서버 (받은 거)
				InputStream inputStream = socketUser.getInputStream(); // 정보를 넣음
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)); // BufferReader에
																								// inputStream을 담음
				System.out.println("클라이언트에서의 메세지 : " + reader.readLine()); // 메세지 읽어주기

				// 서버 -> 클라이언트 (보낼 거)
				OutputStream outputStream = socketUser.getOutputStream(); // 정보 넣음
				PrintWriter writer = new PrintWriter(outputStream, true);
				writer.println("SERVER to CLIENT");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

}
