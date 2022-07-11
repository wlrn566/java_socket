import java.io.DataOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

public class User {
//	User 권한 클래스
	
	HashMap<String, DataOutputStream> clientmap = new HashMap<String, DataOutputStream>();
	// clientmap은 String형의 key와 DataOutputStream 형의 Value(값)을 받음

	public synchronized void AddClient(String name, Socket socket) {
		try {
			sendMsg(name + " 님이 입장하셨습니다.", "Server"); // server에 입장 메세지 전달
			clientmap.put(name, new DataOutputStream(socket.getOutputStream()));
			// HashMap의 put(key, value)함수는 key와 value를 받는다 
			
			System.out.println("채팅 참여 인원 : " + clientmap.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void RemoveClient(String name) {
		try {
			clientmap.remove(name); // name value를 가지는 client 제거
			sendMsg(name + " 님이 퇴장하였습니다.", "Server");
			System.out.println("채팅 참여 인원 : " + clientmap.size());
		}catch(Exception e) {
			
		}
	}

	public synchronized void sendMsg(String msg, String name) throws Exception {
		Iterator iterator = clientmap.keySet().iterator(); // clientmap의 key 값들을 읽어옵니다.
		while (iterator.hasNext()) { // key 값의 next가 존재한다면 계속 루프
			String clientname = (String) iterator.next();  // 채팅을 입력한 유저 이름을 받아와서
			clientmap.get(clientname).writeUTF(name + " : " + msg); // 양식대로 채팅을 출력합니다.
		}
	}
}
