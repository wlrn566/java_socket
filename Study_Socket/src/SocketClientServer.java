import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClientServer {

	public static void main(String[] args) {
		// 클라이언트와 소켓 서버 연결
		try {
			Socket socket = new Socket("192.168.0.9", 1234);  // (로컬ip, 소켓포트) -> 소켓 서버에 접속
			System.out.println("소켓 서버 접속 성공!");
			
			// 클라이언트 -> 서버 (보낼 거)
			OutputStream outputStream = socket.getOutputStream(); 
			PrintWriter writer = new PrintWriter(outputStream, true);
			writer.println("Client to server");
			
			// 서버 -> 클라이언트 (받은 거)
			InputStream inputStream = socket.getInputStream(); 
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			System.out.println("서버에서의 메세지 : " + reader.readLine());
			System.out.println("Clinet close");
			socket.close(); // 종료
			
		}catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
