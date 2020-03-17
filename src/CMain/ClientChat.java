package CMain;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientChat {
	private Socket withServer = null;
	private InputStream reMsg = null;
	private OutputStream sendMsg = null;
	private String id = null;
	private Scanner input = new Scanner(System.in);

	ClientChat(Socket c) {
		this.withServer = c;
		streamSet();
		receive();
		send();
	}

	private void send() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					System.out.println("상대방에게 보내기");
					Scanner in = new Scanner(System.in);
					while(true) {
						
						reMsg = withServer.getInputStream();
						byte[] reBuffer = new byte[100];
						
						reMsg.read(reBuffer);
						String msg = new String(reBuffer);
						msg = msg.trim();
						System.out.println(msg);
					}
				} catch (Exception e) {
					System.out.println("보내기 끝");
					return;
				}
			}
		}).start();

	}

	private void receive() {
		new Thread(new Runnable() {

			@Override
			public void run() {
			try {
				System.out.println("받기 시작");
				while (true) {
					reMsg = withServer.getInputStream();
					byte[] reBuffer = new byte[100];
					reMsg.read(reBuffer);
					String msg = new String(reBuffer);
					msg = msg.trim();
					System.out.println(msg);
				}
			}catch (Exception e) {
				System.out.println("클라이언트 받기 끝");
				return;
			}	
				
			}
			
		}).start();

	}

	public void menu () {
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("라면");
		arrayList.add("만두");
		arrayList.add("밥");
		arrayList.add("김밥");
		arrayList.add("사랑해");
	}
	
	private void streamSet() {
		try {
			
			System.out.println("ID를 입력해주세요");
			id = input.nextLine();
			menu();
			
			sendMsg = withServer.getOutputStream();
			sendMsg.write(id.getBytes());
			reMsg = withServer.getInputStream();
			byte[] reBuffer = new byte[100];
			reMsg.read(reBuffer);
			String msg = new String(reBuffer);
			msg = msg.trim();
			System.out.println(msg);
		} catch (Exception e) {
		}
	}

}
