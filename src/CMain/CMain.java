package CMain;

import java.net.Socket;

public class CMain {

	public static void main(String[] args) throws Exception {
		Socket withServer = null;
		withServer = new Socket ("10.0.0.117",9999);
		new ClientChat(withServer);

	}

}
