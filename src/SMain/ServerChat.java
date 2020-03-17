package SMain;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class ServerChat extends Thread {
	private Socket withClient = null;
	private InputStream reMsg = null;
	private OutputStream sendMsg = null;
	private String id = null;
	
	private ArrayList<Thread> tList = new ArrayList<Thread>();
	
	
	
	
	public String getMyid() {
		return id;
	}

	
	private ServerMenu sc = null;

	
	
	

	ServerChat(Socket c, ServerMenu s) {
		this.withClient = c;
		this.sc = s;
	}

	@Override
	public void run() {
		streamSet();
		receive();
	}

	public void send(String reMsg) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		try {
			sendMsg = withClient.getOutputStream();
			sendMsg.write(reMsg.getBytes());

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("send End");
			return;
		}
	}

	private void receive() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					System.out.println("받기 시작");
					while (true) {
						reMsg = withClient.getInputStream();
						byte[] reBuffer = new byte[100];
						reMsg.read(reBuffer);
						String menu = new String (reBuffer);
						String msg = new String(reBuffer);
						msg = msg.trim();
						sc.reMsg(msg, id);
					}

				} catch (Exception e) {
					System.out.println("받기 끝");
					return;
				}

			}

		}).start();

	}

	private void streamSet() {
		try {
			reMsg = withClient.getInputStream();
			byte[] reBuffer = new byte[100];
			reMsg.read(reBuffer);
			id = new String(reBuffer);
			id = id.trim();

			InetAddress c_ip = withClient.getInetAddress();
			String ip = c_ip.getHostAddress();

			System.out.println(id + "님 로그인 (" + ip + ")");

			String reMsg = "정상접속 되었습니다.";
			sendMsg = withClient.getOutputStream();
			sendMsg.write(reMsg.getBytes());

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
