package SMain;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SMain {

	public static void main(String[] args) throws Exception {
		ServerSocket serverS = null;
		Socket withClient = null;
		serverS = new ServerSocket();
		serverS.bind(new InetSocketAddress("10.0.0.117", 9999));

		ArrayList<Socket> cList = new ArrayList<>();
		ServerMenu sc = new ServerMenu();

		while (true) {
			System.out.println("서버 대기중");
			withClient = serverS.accept();
			cList.add(withClient);
			System.out.println(cList);
			System.out.println(withClient.getInetAddress()+"클라이언트 입장");
			ServerChat s = new ServerChat(withClient,sc);
			sc.addServerChat(s);
			s.start();
		}

	}

}
