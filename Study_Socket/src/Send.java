import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

public class Send implements Runnable {
// 메세지 보내는 쓰레드
	DataOutputStream out;

	// 채팅 수신
	BufferedReader in2 = new BufferedReader(new InputStreamReader(System.in));

	public Send(DataOutputStream out) {
		this.out = out;
	}

	public void run() {
		while (true) {
			try {
				String msg = in2.readLine(); // 메세지를 받아오면
				out.writeUTF(msg); // 메세지 출력
			} catch (Exception e) {

			}
		}
	}
}
