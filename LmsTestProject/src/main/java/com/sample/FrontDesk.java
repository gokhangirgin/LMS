package com.sample;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL; 

import javax.swing.JRadioButton; 
 
public class FrontDesk extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_2;
	public JRadioButton rdbtnColored;
	public JRadioButton rdbtnBedding;
	public JRadioButton rdbtnSilk;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrontDesk frame = new FrontDesk();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrontDesk() {
		setTitle("Registry");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 286, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Color");
		lblNewLabel.setBounds(10, 16, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(10, 46, 46, 14);
		contentPane.add(lblType);
		
		JLabel lblMaterial = new JLabel("Material");
		lblMaterial.setBounds(10, 76, 46, 14);
		contentPane.add(lblMaterial);
		
		JLabel lblWeight = new JLabel("Weight");
		lblWeight.setBounds(10, 106, 46, 14);
		contentPane.add(lblWeight);
		
		textField_2 = new JTextField();
		textField_2.setBounds(60, 101, 195, 25);
		contentPane.add(textField_2);
		
		rdbtnColored = new JRadioButton("Colored");
		rdbtnColored.setBounds(62, 12, 78, 23);
		
		JRadioButton rdbtnWhite = new JRadioButton("White");
		rdbtnWhite.setBounds(142, 12, 63, 23);

		ButtonGroup bg3 = new ButtonGroup();
		bg3.add(rdbtnColored);
		bg3.add(rdbtnWhite);
		
		contentPane.add(rdbtnColored);
		contentPane.add(rdbtnWhite);
		
		rdbtnBedding = new JRadioButton("Bedding");
		rdbtnBedding.setBounds(62, 42, 78, 23);
		
		JRadioButton rdbtnCloth = new JRadioButton("Cloth");
		rdbtnCloth.setBounds(142, 42, 109, 23);

		ButtonGroup bg2 = new ButtonGroup();
		bg2.add(rdbtnBedding);
		bg2.add(rdbtnCloth);
		
		contentPane.add(rdbtnBedding);
		contentPane.add(rdbtnCloth);
		
		rdbtnSilk = new JRadioButton("Silk");
		rdbtnSilk.setBounds(62, 72, 78, 23);
		
		JRadioButton rdbtnNormalFabric = new JRadioButton("Normal Fabric");
		rdbtnNormalFabric.setBounds(142, 72, 109, 23);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnSilk);
		bg.add(rdbtnNormalFabric);
		
		contentPane.add(rdbtnSilk);
		contentPane.add(rdbtnNormalFabric);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				int count = 0;
				try {
					URL url = new URL("http://localhost:8080/restEasy/LMS/getLaundryCount");
					HttpURLConnection connection = (HttpURLConnection)url.openConnection(); 
					connection.connect();
					InputStreamReader in = new InputStreamReader((InputStream) connection.getContent());
				    BufferedReader buff = new BufferedReader(in); 
				    String line; 
				    line = buff.readLine();  
					count = Integer.parseInt(line);
				} catch (MalformedURLException ee) {
					// TODO Auto-generated catch block
					ee.printStackTrace();
				} catch (IOException ee) {
					// TODO Auto-generated catch block
					ee.printStackTrace();
				}
				Laundry l = new Laundry(
						count + 1,
						rdbtnColored.isSelected() == true ? 1 : 0,
						rdbtnBedding.isSelected() == true ? 1 : 0,
						rdbtnSilk.isSelected() == true ? 1 : 0,
						0,
						Float.parseFloat(textField_2.getText()));

				System.out.println("id" + l.getId());
				try {
					URL url = new URL("http://localhost:8080/restEasy/LMS/putLaundry?id="+ l.getId() +"&material=" + l.getMaterial() + "&color=" + l.getColor() + "&weight=" + l.getWeight() + "&bedding=" + (l.IsBedding() == true ? 0 : 1));
					HttpURLConnection connection = (HttpURLConnection)url.openConnection(); 
					connection.connect();
					connection.getContent();

					System.out.println("done");
				} catch (MalformedURLException ee) {
					// TODO Auto-generated catch block
					ee.printStackTrace();
				} catch (IOException ee) {
					// TODO Auto-generated catch block
					ee.printStackTrace();
				}  
				l.Start();
			}
		});
		btnNewButton.setBounds(10, 131, 245, 23);
		contentPane.add(btnNewButton);
		
	}
}
