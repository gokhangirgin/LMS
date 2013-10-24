package com.sample;

 
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
 
public class RFIDsim extends Thread {
	@Override
	public void run() {
		{
			try {
				while (true) {
					System.out.println("Receiver Start");

					SocketChannel sChannel = SocketChannel.open();
					sChannel.configureBlocking(true);
					
					if (sChannel.connect(new InetSocketAddress("192.168.146.82",12345))) 
					{
						ObjectInputStream ois = new ObjectInputStream(sChannel.socket().getInputStream());
						String s = (String) ois.readObject();
						System.out.println("String is: '" + s + "'");
						MainMenu.tfLaundryId.setText(s);
					}

					System.out.println("End Receiver");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
