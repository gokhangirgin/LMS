package com.sample;

 
import javax.swing.JTextField;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
 
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class Source extends JFrame {
 
	private JTextField textField;
	ServerSocketChannel ssChannel ;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Source frame = new Source();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public Source() throws IOException {
		System.out.println("start");
		setTitle("RFID reader");
		
   	 	ssChannel = ServerSocketChannel.open();
   	 	ssChannel.configureBlocking(true);
   	 	int port = 12345;
   	 	ssChannel.socket().bind(new InetSocketAddress(port));		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 332, 109);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSend = new JButton("Read");
		btnSend.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				
				String obj = textField.getText().toString();
		    		SocketChannel sChannel = null; 
		    		ObjectOutputStream oos = null;
					try {
						sChannel = ssChannel.accept();
						oos = new ObjectOutputStream(sChannel.socket().getOutputStream());
						oos.writeObject(obj);
						oos.close(); 
			    		System.out.println("sent"); 
						sChannel.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
			}
		});
		btnSend.setBounds(175, 11, 130, 51);
		contentPane.add(btnSend);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField.setBounds(10, 11, 155, 51);
		contentPane.add(textField);
		textField.setColumns(10);
	}

}
