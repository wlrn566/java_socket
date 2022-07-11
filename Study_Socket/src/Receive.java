import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receive implements Runnable {
//	메세지 수신 쓰레드
	Socket socket;
	DataInputStream in;
	String name;
	User user = new User();

	/*
	 * 전역변수들을 전부 초기화합니다.
	 * 또한 user 클래스에 AddClient를 호출하여 사용자를 등록합니다.
	 */
	
	public Receive(Socket socket, User user) throws IOException {
		this.user = user;
		this.socket = socket;
		in = new DataInputStream(socket.getInputStream());
		this.name = in.readUTF(); // UTF-8 로 인코딩 후 읽어옴
		user.AddClient(name, socket); // 사용자 등록
	}

	public void run() {
		try {
			while(true) {
				String msg = in.readUTF(); // in에 들어온 메세지를 UTF-8 로 인코딩 후 읽어옴
				user.sendMsg(msg, name); // name 이름을 가진 user가 msg라는 메세지를 보냄
			}
		}catch(Exception e) {
			user.RemoveClient(this.name); // 에러 발생시 name 유저를 client에서 삭제
		}
	}
}
