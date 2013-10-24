
package com.sample;
 
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;  
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextField; 
import javax.swing.SwingConstants;
import java.awt.Color; 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JProgressBar;
import javax.swing.border.LineBorder; 
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.UIManager;

import models.Item;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
public class MainMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7172111005132986964L;
	private JPanel contentPane;
	public JLabel lblOwner;
	public JLabel lblMaterial;
	public JLabel lblType;
	public JLabel lblWeight;
	public JLabel lblWashCount;
	public JLabel lblColor;
	
	public JLabel lblOwnerR;
	public JLabel lblMaterialR;
	public JLabel lblTypeR;
	public JLabel lblWeightR;
	public JLabel lblWashCountR;
	public JLabel lblColorR;
	
	public JTextPane textPane;
	
	public static DefaultListModel coloredBinModel = new DefaultListModel();
	public static DefaultListModel whiteBinModel = new DefaultListModel();
	public static DefaultListModel hospitalBinModel = new DefaultListModel();
	public static DefaultListModel discardBinModel = new DefaultListModel();
	 
	public static Knowledgebase kbGeneral = new Knowledgebase("generalFlow.rf");

	public static LaundryFactory laundryFactory = new LaundryFactory();
	public static JTextField tfLaundryId;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
			    	kbGeneral.AddFlow("generalWash.rf"); 
			    	kbGeneral.AddFlow("generalDry.rf");  
			    	kbGeneral.AddFlow("generalIron.rf");  
			    	kbGeneral.AddFlow("generalPackage.rf"); 
			    	kbGeneral.AddFlow("sortForWash.rf"); 
			    	kbGeneral.AddFlow("sortForDry.rf"); 
			    	kbGeneral.AddFlow("sortForIron.rf"); 
			    	kbGeneral.AddFlow("sortForPackage.rf"); 
			    	
					kbGeneral.ksession.getWorkItemManager().
					registerWorkItemHandler("WebService", new SortingWebServiceHandler());
					RFIDsim rfidReader = new RFIDsim();
					rfidReader.start();
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
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 941, 451);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRegisterLaundry = new JButton("Register Laundry");
		btnRegisterLaundry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FrontDesk frame = new FrontDesk();
					frame.setVisible(true);
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
		});
		btnRegisterLaundry.setBounds(10, 379, 138, 23);
		contentPane.add(btnRegisterLaundry);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 758, 357);
//		tabbedPane.addChangeListener(new ChangeListener() {
//			public void stateChanged(ChangeEvent changeEvent) {
//				lblMaterialR.setText("");
//				lblTypeR.setText("");
//				lblWeightR.setText("");
//				lblWashCountR.setText("");
//				lblColorR.setText("");
//			}
//		});
		contentPane.add(tabbedPane);
		
		JPanel fdPanel = new JPanel();
		tabbedPane.addTab("Front Desk", null, fdPanel, null);
		fdPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBounds(10, 11, 199, 185);
		fdPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblPleaseReadThe = new JLabel("Please Read The RFID tag");
		lblPleaseReadThe.setBounds(10, 32, 179, 14);
		panel.add(lblPleaseReadThe);
		
		tfLaundryId = new JTextField();
		tfLaundryId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfLaundryId.setEnabled(false);
		tfLaundryId.setBounds(10, 78, 179, 41);
		panel.add(tfLaundryId);
		tfLaundryId.setColumns(10);
		tfLaundryId.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void removeUpdate(DocumentEvent e) { 
			  }
			  public void insertUpdate(DocumentEvent e) {
			    warn();
			  }

			  public void warn() 
			  { 
				  String s = tfLaundryId.getText();
				  System.out.println("text -> \"" + s + "\"");
				  if(s != "")
				  {
					  Item item = GetInformationOfLaundry(tfLaundryId.getText());
					  StartProcess(item);   
				  }
			  }
			});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_1.setBounds(219, 11, 331, 185);
//		fdPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblOwnerName = new JLabel("Owner Name:");
		lblOwnerName.setBounds(10, 14, 119, 14);
		panel_1.add(lblOwnerName);
		
		JLabel lbl_Material = new JLabel("Material:");
		lbl_Material.setBounds(10, 42, 119, 14);
		panel_1.add(lbl_Material);
		
		JLabel lbl_Type = new JLabel("Type:");
		lbl_Type.setBounds(10, 70, 119, 14);
		panel_1.add(lbl_Type);
		
		JLabel lbl_Weight = new JLabel("Weight:");
		lbl_Weight.setBounds(10, 98, 119, 14);
		panel_1.add(lbl_Weight);
		
		JLabel lbl_WashCount = new JLabel("Wash Count:");
		lbl_WashCount.setBounds(10, 126, 119, 14);
		panel_1.add(lbl_WashCount);
		
		lblOwner = new JLabel("");
		lblOwner.setBounds(139, 14, 182, 14);
		panel_1.add(lblOwner);
		
		lblMaterial = new JLabel("");
		lblMaterial.setBounds(139, 42, 182, 14);
		panel_1.add(lblMaterial);
		
		lblType = new JLabel("");
		lblType.setBounds(139, 70, 182, 14);
		panel_1.add(lblType);
		
		lblWeight = new JLabel("");
		lblWeight.setBounds(139, 98, 182, 14);
		panel_1.add(lblWeight);
		
		lblWashCount = new JLabel("");
		lblWashCount.setBounds(139, 126, 182, 14);
		panel_1.add(lblWashCount);
		
		JLabel lbl_color = new JLabel("Color:");
		lbl_color.setBounds(10, 154, 119, 14);
		panel_1.add(lbl_color);
		
		lblColor = new JLabel("");
		lblColor.setBounds(139, 154, 182, 14);
		panel_1.add(lblColor);
		
		JPanel waPanel = new JPanel();
		tabbedPane.addTab("Washing Area", null, waPanel, null);
		waPanel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Colored Bin", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, Color.DARK_GRAY));
		panel_2.setBounds(17, 11, 166, 307);
		waPanel.add(panel_2);
		panel_2.setLayout(null);
		
		JProgressBar pbColored = laundryFactory.ColoredBin.getPbar(); 
		pbColored.setOrientation(SwingConstants.VERTICAL);
		pbColored.setBounds(10, 27, 34, 269);
		panel_2.add(pbColored);
		
		JList coloredBinList = new JList(laundryFactory.ColoredBin.getModel());
		coloredBinList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		coloredBinList.setBounds(54, 27, 102, 269);
		coloredBinList.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            GetInformationOfLaundry(list.getSelectedValue().toString());
		        } 
		    }
		});
		panel_2.add(coloredBinList);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "White Bin", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		panel_3.setLayout(null);
		panel_3.setBounds(200, 11, 166, 307);
		waPanel.add(panel_3);
		
		JProgressBar pbWhite = laundryFactory.WhiteBin.getPbar();
		pbWhite.setOrientation(SwingConstants.VERTICAL); 
		pbWhite.setBounds(10, 27, 34, 269);
		panel_3.add(pbWhite);
		
		JList whiteBinList = new JList(laundryFactory.WhiteBin.getModel());
		whiteBinList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		whiteBinList.setBounds(54, 27, 102, 269);
		whiteBinList.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            GetInformationOfLaundry(list.getSelectedValue().toString());
		        } 
		    }
		});
		panel_3.add(whiteBinList);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Hospital Bin", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		panel_4.setLayout(null);
		panel_4.setBounds(383, 11, 166, 307);
		waPanel.add(panel_4);
		
		JProgressBar pbHospital = laundryFactory.HospitalBin.getPbar();
		pbHospital.setOrientation(SwingConstants.VERTICAL); 
		pbHospital.setBounds(10, 27, 34, 269);
		panel_4.add(pbHospital);
		
		JList hospitalBinList = new JList(laundryFactory.HospitalBin.getModel());
		hospitalBinList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		hospitalBinList.setBounds(54, 27, 102, 269);
		hospitalBinList.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            GetInformationOfLaundry(list.getSelectedValue().toString());
		        } 
		    }
		});
		panel_4.add(hospitalBinList);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Discard Bin", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		panel_5.setLayout(null);
		panel_5.setBounds(566, 11, 166, 307);
		waPanel.add(panel_5);
		
		JProgressBar pbDiscard = laundryFactory.DiscardBin.getPbar();
		pbDiscard.setOrientation(SwingConstants.VERTICAL); 
		pbDiscard.setBounds(10, 27, 34, 269);
		panel_5.add(pbDiscard);
		
		JList discardBinList = new JList(laundryFactory.DiscardBin.getModel());
		discardBinList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		discardBinList.setBounds(54, 27, 102, 269);
		discardBinList.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            GetInformationOfLaundry(list.getSelectedValue().toString());
		        } 
		    }
		});
		panel_5.add(discardBinList);
		
		JPanel daPanel = new JPanel();
		tabbedPane.addTab("Drying Area", null, daPanel, null);
		daPanel.setLayout(null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "Silk Bin", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		panel_6.setLayout(null);
		panel_6.setBounds(63, 11, 166, 307);
		daPanel.add(panel_6);
		
		JList silkBinList = new JList(laundryFactory.SilkBin.getModel());
		silkBinList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		silkBinList.setBounds(54, 27, 102, 269);
		silkBinList.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            GetInformationOfLaundry(list.getSelectedValue().toString());
		        } 
		    }
		});
		panel_6.add(silkBinList);
		
		JProgressBar pbSilk = laundryFactory.SilkBin.getPbar();
		pbSilk.setOrientation(SwingConstants.VERTICAL);
		pbSilk.setBounds(10, 27, 34, 269);
		panel_6.add(pbSilk);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "Cotton Bin", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		panel_7.setLayout(null);
		panel_7.setBounds(292, 11, 166, 307);
		daPanel.add(panel_7);
		
		JList cottonBinList = new JList(laundryFactory.CottonBin.getModel());
		cottonBinList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		cottonBinList.setBounds(54, 27, 102, 269);
		cottonBinList.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            GetInformationOfLaundry(list.getSelectedValue().toString());
		        } 
		    }
		});
		panel_7.add(cottonBinList);
		
		JProgressBar pbCotton = laundryFactory.CottonBin.getPbar();
		pbCotton.setOrientation(SwingConstants.VERTICAL);
		pbCotton.setBounds(10, 27, 34, 269);
		panel_7.add(pbCotton);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(null, "Open Air Bin", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		panel_8.setLayout(null);
		panel_8.setBounds(521, 11, 166, 307);
		daPanel.add(panel_8);
		
		JList openAirBinList = new JList(laundryFactory.OpenAirBin.getModel());
		openAirBinList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		openAirBinList.setBounds(54, 27, 102, 269);
		openAirBinList.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            GetInformationOfLaundry(list.getSelectedValue().toString());
		        } 
		    }
		});
		panel_8.add(openAirBinList);
		
		JProgressBar pbOpenAir = laundryFactory.OpenAirBin.getPbar();
		pbOpenAir.setOrientation(SwingConstants.VERTICAL);
		pbOpenAir.setBounds(10, 27, 34, 269);
		panel_8.add(pbOpenAir);
		
		JPanel iaPanel = new JPanel();
		tabbedPane.addTab("Ironing Area", null, iaPanel, null);
		iaPanel.setLayout(null);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(null, "Normal Iron Bin", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		panel_9.setLayout(null);
		panel_9.setBounds(63, 11, 166, 307);
		iaPanel.add(panel_9);
		
		JList normalIronBinList = new JList(laundryFactory.NormalIronBin.getModel());
		normalIronBinList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		normalIronBinList.setBounds(54, 27, 102, 269);
		normalIronBinList.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            GetInformationOfLaundry(list.getSelectedValue().toString());
		        } 
		    }
		});
		panel_9.add(normalIronBinList);
		
		JProgressBar pbNormalIronBin = laundryFactory.NormalIronBin.getPbar();
		pbNormalIronBin.setOrientation(SwingConstants.VERTICAL);
		pbNormalIronBin.setBounds(10, 27, 34, 269);
		panel_9.add(pbNormalIronBin);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Do Not Iron Bin", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		panel_10.setLayout(null);
		panel_10.setBounds(292, 11, 166, 307);
		iaPanel.add(panel_10);
		
		JList doNotIronBinList = new JList(laundryFactory.DoNotIronBin.getModel());
		doNotIronBinList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		doNotIronBinList.setBounds(54, 27, 102, 269);
		doNotIronBinList.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            GetInformationOfLaundry(list.getSelectedValue().toString());
		        } 
		    }
		});
		panel_10.add(doNotIronBinList);
		
		JProgressBar pbDoNotIronBin = laundryFactory.DoNotIronBin.getPbar();
		pbDoNotIronBin.setOrientation(SwingConstants.VERTICAL);
		pbDoNotIronBin.setBounds(10, 27, 34, 269);
		panel_10.add(pbDoNotIronBin);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new TitledBorder(null, "Iron With Machine Bin", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		panel_11.setLayout(null);
		panel_11.setBounds(521, 11, 166, 307);
		iaPanel.add(panel_11);
		
		JList ironWithMachineBinList = new JList(laundryFactory.IronWithMachineBin.getModel());
		ironWithMachineBinList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		ironWithMachineBinList.setBounds(54, 27, 102, 269);
		ironWithMachineBinList.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            GetInformationOfLaundry(list.getSelectedValue().toString());
		        } 
		    }
		});
		panel_11.add(ironWithMachineBinList);
		
		JProgressBar pbIronWithMachine = laundryFactory.IronWithMachineBin.getPbar();
		pbIronWithMachine.setOrientation(SwingConstants.VERTICAL);
		pbIronWithMachine.setBounds(10, 27, 34, 269);
		panel_11.add(pbIronWithMachine);
		
		JPanel paPanel = new JPanel();
		tabbedPane.addTab("Packaging Area", null, paPanel, null);
		paPanel.setLayout(null);
		
		JPanel panel_16 = new JPanel();
		panel_16.setBorder(new TitledBorder(null, "North", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		panel_16.setLayout(null);
		panel_16.setBounds(10, 11, 166, 307);
		paPanel.add(panel_16);
		
		JList northBinList = new JList(laundryFactory.NorthBin.getModel());
		northBinList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		northBinList.setBounds(54, 27, 102, 269);
		northBinList.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            GetInformationOfLaundry(list.getSelectedValue().toString());
		        } 
		    }
		});
		panel_16.add(northBinList);
		
		JProgressBar pbNorthBin = laundryFactory.NorthBin.getPbar();
		pbNorthBin.setOrientation(SwingConstants.VERTICAL);
		pbNorthBin.setBounds(10, 27, 34, 269);
		panel_16.add(pbNorthBin);
		
		JPanel panel_17 = new JPanel();
		panel_17.setBorder(new TitledBorder(null, "West", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		panel_17.setLayout(null);
		panel_17.setBounds(193, 11, 166, 307);
		paPanel.add(panel_17);
		
		JList westBinList = new JList(laundryFactory.WestBin.getModel());
		westBinList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		westBinList.setBounds(54, 27, 102, 269);
		westBinList.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            GetInformationOfLaundry(list.getSelectedValue().toString());
		        } 
		    }
		});
		panel_17.add(westBinList);
		
		JProgressBar pbWestBin = laundryFactory.WestBin.getPbar();
		pbWestBin.setOrientation(SwingConstants.VERTICAL);
		pbWestBin.setBounds(10, 27, 34, 269);
		panel_17.add(pbWestBin);
		
		JPanel panel_18 = new JPanel();
		panel_18.setBorder(new TitledBorder(null, "South", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		panel_18.setLayout(null);
		panel_18.setBounds(376, 11, 166, 307);
		paPanel.add(panel_18);
		
		JList southBinList = new JList(laundryFactory.SouthBin.getModel());
		southBinList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		southBinList.setBounds(54, 27, 102, 269);
		southBinList.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            GetInformationOfLaundry(list.getSelectedValue().toString());
		        } 
		    }
		});
		panel_18.add(southBinList);
		
		JProgressBar pbSouthBin = laundryFactory.SouthBin.getPbar();
		pbSouthBin.setOrientation(SwingConstants.VERTICAL);
		pbSouthBin.setBounds(10, 27, 34, 269);
		panel_18.add(pbSouthBin);
		
		JPanel panel_19 = new JPanel();
		panel_19.setBorder(new TitledBorder(null, "East", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		panel_19.setLayout(null);
		panel_19.setBounds(559, 11, 166, 307);
		paPanel.add(panel_19);
		
		JList eastBinList = new JList(laundryFactory.EastBin.getModel());
		eastBinList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		eastBinList.setBounds(54, 27, 102, 269);
		eastBinList.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            GetInformationOfLaundry(list.getSelectedValue().toString());
		        } 
		    }
		});
		panel_19.add(eastBinList);
		
		JProgressBar pbEastBin = laundryFactory.EastBin.getPbar();
		pbEastBin.setOrientation(SwingConstants.VERTICAL);
		pbEastBin.setBounds(10, 27, 34, 269);
		panel_19.add(pbEastBin);
		
		JPanel panel_23 = new JPanel();
		tabbedPane.addTab("Change Rules", null, panel_23, null);
		panel_23.setLayout(null);
		
		final JComboBox comboBox = new JComboBox(GetRuleFiles());
		comboBox.setBounds(10, 28, 183, 20);
		comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	if (comboBox.getSelectedItem() != null) {
                    System.out.println(comboBox.getSelectedItem().toString());
                    try { 
						textPane.setText(GetRuleText(comboBox.getSelectedItem().toString()));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("hATA");
						e.printStackTrace();
					}
                }
            }
        });
		panel_23.add(comboBox);
		
		JLabel lblPickTheRule = new JLabel("Pick the rule file you want to modify");
		lblPickTheRule.setBounds(10, 11, 238, 14);
		panel_23.add(lblPickTheRule);
		
		JButton btnModify = new JButton("Save");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveRuleText(comboBox.getSelectedItem().toString(), textPane.getText());
			}
		});
		btnModify.setBounds(654, 27, 89, 23);
		panel_23.add(btnModify);
		
		textPane = new JTextPane();
		JScrollPane scrollPane = new JScrollPane(textPane);
		scrollPane.setBounds(10, 59, 733, 259);
		textPane.setBounds(10, 59, 733, 259);
		panel_23.add(scrollPane); 
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_12.setLayout(null);
		panel_12.setBounds(789, 32, 129, 370);
		contentPane.add(panel_12);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new TitledBorder(null, "Owner", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_13.setBounds(10, 6, 103, 54);
		panel_12.add(panel_13);
		
		lblOwnerR = new JLabel("");
		panel_13.add(lblOwnerR);
		
		JPanel panel_14 = new JPanel();
		panel_14.setBorder(new TitledBorder(null, "Material", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_14.setBounds(10, 66, 103, 54);
		panel_12.add(panel_14);
		
		lblMaterialR = new JLabel("");
		panel_14.add(lblMaterialR);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBorder(new TitledBorder(null, "Type", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_15.setBounds(10, 126, 103, 54);
		panel_12.add(panel_15);
		
		lblTypeR = new JLabel("");
		panel_15.add(lblTypeR);
		
		JPanel panel_20 = new JPanel();
		panel_20.setBorder(new TitledBorder(null, "Weight", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_20.setBounds(10, 186, 103, 54);
		panel_12.add(panel_20);
		
		lblWeightR = new JLabel("");
		panel_20.add(lblWeightR);
		
		JPanel panel_21 = new JPanel();
		panel_21.setBorder(new TitledBorder(null, "Wash Count", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_21.setBounds(10, 246, 103, 54);
		panel_12.add(panel_21);
		
		lblWashCountR = new JLabel("");
		panel_21.add(lblWashCountR);
		
		JPanel panel_22 = new JPanel();
		panel_22.setBorder(new TitledBorder(null, "Color", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_22.setBounds(10, 306, 103, 54);
		panel_12.add(panel_22);
		
		lblColorR = new JLabel("");
		panel_22.add(lblColorR);
	}

	public Item GetInformationOfLaundry(String id)
	{
		String _url = "http://localhost:8080/LmsService/LMS/getLaundry?id=" + id; 
		try {
			URL url = new URL(_url);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			StringBuffer text = new StringBuffer();
			connection.connect();
			InputStreamReader in = new InputStreamReader((InputStream) connection.getContent());
		    BufferedReader buff = new BufferedReader(in);
		    String line;
		    do {
		      line = buff.readLine();
		      text.append(line + "\n");
		    } while (line != null);
			//System.out.println(text);
			ObjectMapper mapper = new ObjectMapper();
			Item l = mapper.readValue(text.toString(),Item.class);  
			lblMaterial.setText(l.getMaterial() + "");
			lblType.setText(l.getType().toString());
			lblWeight.setText(l.getWeight() + " lbs");
			lblWashCount.setText(l.getWashedCount() + "");
			lblColor.setText(l.getColor() ? "White" : "Colored"); 
			
			lblOwnerR.setText("Ali Gül");
			lblMaterialR.setText(l.getMaterial() + "");
			lblTypeR.setText(l.getType().toString());
			lblWeightR.setText(l.getWeight() + " lbs");
			lblWashCountR.setText(l.getWashedCount() + "");
			lblColorR.setText(l.getColor() ? "White" : "Colored");
			lblOwnerR.setText(GetOwnerName(l));
			return l;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String GetOwnerName(Item l)
	{
		if(l.getId() == 1 || l.getId() == 2 || l.getId() == 3)
			return "Gökhan Girgin";
		if(l.getId() == 4 || l.getId() == 5 || l.getId() == 6)
			return "Tolga Evcimen";
		if(l.getId() == 7 || l.getId() == 8 || l.getId() == 9)
			return "Salih Çiftçi";
		if(l.getId() == 10 || l.getId() == 11 || l.getId() == 12)
			return "Doğan Evci";
		if(l.getId() == 13 || l.getId() == 14 || l.getId() == 15)
			return "Efdal Ustaoğlu";
		return null;
	}
	
	public String GetRuleText(String fileName) throws IOException
	{
		File f = new File("C:\\Users\\penishead\\Documents\\WorkSpace\\LmsService\\rules\\" + fileName); 
	    String str = "";
	    BufferedReader br = new BufferedReader(new FileReader(f));
	    while (true) { 
	    	String s;
	      s = br.readLine();
	      if (s == null)
	        break; 
	      str += s + "\n";
	    }
	    br.close();  
		return str;
	}
	
	public void SaveRuleText(String fileName, String ruleText)
	{
		try
		{
			// Create file
			FileWriter fstream = new FileWriter("C:\\Users\\penishead\\Documents\\WorkSpace\\LmsService\\rules\\" + fileName, false);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(ruleText);
			//Close the output stream
			out.close();
			JOptionPane.showMessageDialog(null, "Rule is succesfully saved, but not deployed", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
		}
		catch (Exception e)
		{
			//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	public String[] GetRuleFiles()
	{
		List<String> results = new ArrayList<String>();
		File[] files = new File("C:\\Users\\penishead\\Documents\\WorkSpace\\LmsService\\rules").listFiles();

		for (File file : files) {
		    if (file.isFile()) {
		        results.add(file.getName());
		        System.out.println(file.getName());
		    }
		}
		return results.toArray(new String[results.size()]);
	}
	
	public void StartProcess(Item l)
	{
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("Item", l); 
		parameterMap.put("LaundryFactory", MainMenu.laundryFactory);  

		MainMenu.kbGeneral.ksession.startProcess("com.sample.generalFlow", parameterMap);
	}
}
