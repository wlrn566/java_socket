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

		Socket socket = null; // Client와 통신하기 위한 Socket 생성
		User user = new User(); // 채팅방에 접속해 있는 Client 관리 객체
		ServerSocket serverSocket = null; // Client 접속을 받기 위한 ServerSocket

		int count = 0; // 쓰레드 할당을 위한 정수
		Thread thread[] = new Thread[10]; // 10개까지 쓰레드 할당, 즉 채팅방에 10명이 접속 가능

		try {
			// 포트 번호
			int port = 1234;
			// 소켓 생성
			serverSocket = new ServerSocket(port);
			System.out.println(port + " 서버 생성 성공!");

			// 서버는 계속 돌아가야하므로 while문 처리
			while (true) {
				socket = serverSocket.accept(); // 소켓 서버 접속 시 socket으로 보내줌
				System.out.println("접속자 : " + socket.getLocalAddress()); // 서버 접속자의 로컬주소

				thread[count] = new Thread(new Receive(socket, user));
				thread[count].start();
				count++;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

}
