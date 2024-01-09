import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;

import javax.swing.border.*;
import javax.swing.event.*;
import java.applet.*;

public class RegisterPatient extends JFrame implements ActionListener,Runnable
{
	private JPanel jpInfo = new JPanel();
	private JLabel lbNo, lbName, lbDate, lbWordName,lbMobNo,lbWordNo,lbCrimeAdd;
	private JTextField txtNo, txtName, txtMobNo,txtAdd;//txtWordNo,txtCrimeAdd;
	private JComboBox cboMonth, cboDay, cboYear,txtPath,txtPath1,txtPath2;
	private JButton btnSave, btnCancel;
	private JButton btnStartCam;
	private JFileChooser  sourcefile= new JFileChooser("./");
	private int count = 0;
	private int rows = 0;
	private	int total = 0;

	private String records[][] = new String [500][8];

	private String saves[][] = new String [500][8];

	private String spath;
	
	private FileInputStream fis;
	private DataInputStream dis;
	boolean running=false,detectFace=false,save=false;
	RegisterPatient(int i){}
	public void run(){}
	public static void main(String a[])
	{

		RegisterPatient newAcc = new RegisterPatient ();
		newAcc.setVisible(true);
	}
	RegisterPatient ()
	{
		super ("Patient Information System");
		setSize (1366, 1170);

//		setResizable(false);
//		setLocationRelativeTo(null);
		jpInfo.setBounds (0, 0, 500, 215);
		jpInfo.setLayout (null);
		lbNo = new JLabel ("Adhar_No:");
//		lbNo.setFont(new Font("Serif", Font.BOLD, 20));
		lbNo.setForeground (Color.black);
		lbNo.setBounds (265, 190, 490, 25);

        lbName = new JLabel ("Patient Name:");
		lbName.setForeground (Color.black);
        lbName.setBounds (265, 240, 90, 25);

		lbDate = new JLabel ("Date of Birth:");
		lbDate.setForeground (Color.black);
		lbDate.setBounds (265, 300, 90, 25);

		lbMobNo = new JLabel ("Mobile No: ");
		lbMobNo.setForeground (Color.black);
		lbMobNo.setBounds (265, 340, 90, 25);

		lbWordNo = new JLabel ("Address");
		lbWordNo.setForeground (Color.black);
		lbWordNo.setBounds (265, 380, 90, 25);

		lbWordName = new JLabel ("Vaccine Dose");
		lbWordName.setForeground (Color.black);
		lbWordName.setBounds (265, 420, 90, 25);

		txtNo = new JTextField ();
		txtNo.setHorizontalAlignment (JTextField.RIGHT);
		txtNo.setBounds (355, 190, 205, 25);

		txtName = new JTextField ();
		txtName.setHorizontalAlignment (JTextField.RIGHT);
		txtName.setBounds (355, 240, 205, 25);

		txtMobNo = new JTextField ();
		txtMobNo.setHorizontalAlignment (JTextField.RIGHT);
		txtMobNo.setBounds (355, 340, 205, 25);
		
		
		

		txtAdd = new JTextField ();
		txtAdd.setHorizontalAlignment (JTextField.RIGHT);
		txtAdd.setBounds (355, 380, 205, 25);
		String dose[] = {"Covid Shield Dose 1","Covaxin Dose 1"};
		//cboMonth = new JComboBox (Months);
		String dose2[] = {"Covid Shield Dose 2"};
		String dose3[] = {"Covaxin Dose 2"};
		txtPath = new JComboBox (dose);
		//txtPath.setEnabled (false);
		//txtPath.setHorizontalAlignment (JTextField.RIGHT);
		txtPath.setBounds (355, 420, 205, 25);
		txtPath1 = new JComboBox (dose2);
		//txtPath.setEnabled (false);
		//txtPath.setHorizontalAlignment (JTextField.RIGHT);
		txtPath1.setBounds (355, 420, 205, 25);
		txtPath2 = new JComboBox (dose3);
		//txtPath.setEnabled (false);
		//txtPath.setHorizontalAlignment (JTextField.RIGHT);
		txtPath2.setBounds (355, 420, 205, 25);

		
		
		
		txtPath2.setVisible(false);
	
		txtPath1.setVisible(false);
		/*btnBrowse = new JButton ("Browse");
		btnBrowse.setBounds (470, 220, 90, 25);
		btnBrowse.addActionListener (this);
*/
		
		txtNo.addKeyListener (new KeyAdapter()
		{
			public void keyTyped (KeyEvent ke)
			{
				char c = ke.getKeyChar ();
				if (!((Character.isDigit (c) || (c == KeyEvent.VK_BACK_SPACE))))
				{
					getToolkit().beep ();
					ke.consume ();
      			}
    		}
  		}
		);

		txtName.addKeyListener (new KeyAdapter()
		{
			public void keyTyped (KeyEvent ke)
			{
				char c = ke.getKeyChar ();
				if (Character.isDigit (c))// || (c == KeyEvent.VK_BACK_SPACE))))
				{
					getToolkit().beep ();
					ke.consume ();
      			}
    		}
  		}
		);

		txtMobNo.addKeyListener (new KeyAdapter()
		{
			public void keyTyped (KeyEvent ke)
			{
				char c = ke.getKeyChar ();
				if (!((Character.isDigit (c) || (c == KeyEvent.VK_BACK_SPACE))))
				{
					getToolkit().beep ();
					ke.consume ();
      		    }
    	  }
  		}
		);

		String Months[] = {"January", "February", "March", "April", "May", "June","July", "August", "September", "October", "November", "December"};
		cboMonth = new JComboBox (Months);
		cboDay = new JComboBox ();
		cboYear = new JComboBox ();
		for (int i = 1; i <= 31; i++)
		{
			String days = "" + i;
			cboDay.addItem (days);
		}
		for (int i = 1960; i <= 2018; i++)
		{
			String years = "" + i;
			cboYear.addItem (years);
		}

		//Aligning The Date Option Controls.
		cboMonth.setBounds (355, 300, 92, 25);
		cboDay.setBounds (452, 300, 43, 25);
		cboYear.setBounds (500, 300, 60, 25);

		//Aligning The Buttons.
		btnStartCam = new JButton("Start Finger Device");
		btnStartCam.setBounds(285,500,280,50);
		btnStartCam.addActionListener(this);
		
		btnSave = new JButton ("Save");
        btnSave.setEnabled(false);
 		btnSave.setBounds (285, 560, 120, 35);
		btnSave.addActionListener (this);

		btnCancel = new JButton ("Cancel");
		btnCancel.setBounds (425, 560, 120, 35);
		btnCancel.addActionListener (this);


		JPanel panel = new JPanel (null);
        JLabel label = new JLabel();
        panel.setBounds(0,0,2000,1170);
        //label.setIcon(new ImageIcon("Images/reg.jpg"));// your image here
        label.setIcon(new ImageIcon("images/Screenshot_6.png"));// your image here
		label.setBounds(0,0,1700,750);
//		panel.add(label);
		panel.setBounds(0,0,1700,600);

		//Adding the All the Controls to Panel.
		jpInfo.add (lbNo);
		jpInfo.add (txtNo);
		jpInfo.add (lbName);
		jpInfo.add (txtName);
		jpInfo.add (lbDate);
		jpInfo.add (cboMonth);
		jpInfo.add (cboDay);
		jpInfo.add (cboYear);
		jpInfo.add (lbMobNo);
		jpInfo.add (txtMobNo);
		jpInfo.add(lbWordNo);
		jpInfo.add(txtAdd);
		jpInfo.add(txtPath);
		jpInfo.add(txtPath1);
		jpInfo.add(txtPath2);
		jpInfo.add (btnStartCam);
		//jpInfo.add (btnBrowse);
		jpInfo.add(lbWordName);
		jpInfo.add (btnSave);
		jpInfo.add (btnCancel);
//		jpInfo.add (panel);
		jpInfo.add(label);			

		
		getContentPane().add (jpInfo);

		//In the End Showing the New Account Window.
		setVisible (true);
		addWindowListener (new WindowAdapter ()
		{
			public void windowClosing (WindowEvent we)
			{
				quitApp ();
			}
		}
		);
}
	private void quitApp ()
	{
		try
		{
		    	int reply = JOptionPane.showConfirmDialog (this,"Are you really want to exit\nFrom  System?","Patient Information System - Exit", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (reply == JOptionPane.YES_OPTION)
				{
				setVisible (false);	//Hide the Frame.

				AudioClip ac = Applet.newAudioClip(new File("Images/Good_Bye.wav").toURL());
				ac.play();

				RegisterPatient b=new RegisterPatient(0);
				Thread t=new Thread(b);
				t.sleep(1000);
				System.exit (0);        //Close the Application.
				}
				else if (reply == JOptionPane.NO_OPTION)
				{
				setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			   }
		 }
   	    catch (Exception e) {}
     }


	public void actionPerformed (ActionEvent ae)
	{
		Object obj = ae.getSource();

		if (obj == btnSave)
		{
	
			 if (txtName.getText().equals("")) {
				JOptionPane.showMessageDialog (this, "Please! Provide Name of Patient.","Patient Information System - EmptyField", JOptionPane.PLAIN_MESSAGE);
				txtName.requestFocus ();
			}
			else if (txtMobNo.getText().equals(""))
			{
				JOptionPane.showMessageDialog (this, "Please! Provide Mobile Number.","Patient Information System - EmptyField", JOptionPane.PLAIN_MESSAGE);
				txtMobNo.requestFocus ();
			}
			else if ((txtMobNo.getText().length())!=10)
			{
				JOptionPane.showMessageDialog (this, "Please! Provide Valid Mobile Number.","Patient Information System - EmptyField", JOptionPane.PLAIN_MESSAGE);
				txtMobNo.requestFocus ();
			}
			else if (txtAdd.getText().equals(""))
			{
				JOptionPane.showMessageDialog (this, "Please! Provide Patient Address.","Patient Information System - EmptyField", JOptionPane.PLAIN_MESSAGE);
				txtNo.requestFocus();
			}
					
			else{
			populateArray ();	//Load All Existing Records in Memory.					
			findRec ();		//Finding if Criminal No. Already Exist or Not.
			}}
		
		if (obj == btnCancel)
		{
			txtClear ();
			setVisible (false);
			dispose();
		}

		
		if(obj == btnStartCam)
		{
			if (txtNo.getText().equals(""))
			{
				JOptionPane.showMessageDialog (this, "Please! Provide Patient number.","Patient Information System - EmptyField", JOptionPane.PLAIN_MESSAGE);
				txtNo.requestFocus();
			} else if ((txtNo.getText().length())!=12)
			{
				JOptionPane.showMessageDialog (this, "Please! Provide Valid Adhar Number.","Patient Information System - EmptyField", JOptionPane.PLAIN_MESSAGE);
				txtMobNo.requestFocus ();
			}
			else
			{			
			btnSave.setEnabled(true);
		    btnStartCam.setEnabled(false);				

			File f1 = new File(Login.FingerDBpath+txtNo.getText()+"_2"+".txt");
			if(f1.exists()) { 
				JOptionPane.showMessageDialog (this, "All Dose are taken","All Dose are taken", JOptionPane.PLAIN_MESSAGE);
				
			}else{
			boolean found=false;
				populateArray ();
				for (int x = 0; x < total; x++) 
				{
				if (records[x][0].equals (txtNo.getText()))
				{
				
					txtName.setText(""+records[x][1]);
					cboMonth.setSelectedItem(""+records[x][2]);

					cboDay.setSelectedItem(""+records[x][3]);

					cboYear.setSelectedItem(""+records[x][4]);
					txtMobNo.setText(""+records[x][5]);
					txtAdd.setText(""+records[x][6]);
					txtPath.setVisible(false);
					if(records[x][7].contains("Covid"))
					{
					String dose[] = {"Covid Shield Dose 2"};
					//cboMonth = new JComboBox (Months);
					//txtPath = new JComboBox (dose);
					txtPath1.setVisible(true);
						
					
					}
					else if(records[x][7].contains("2"))
					{
						JOptionPane.showMessageDialog (this, "All Dose Taken.","Patient Information System - EmptyField", JOptionPane.PLAIN_MESSAGE);
						found = true;
						break;
					}
					else 
					{
						txtPath2.setVisible(true);
						
						String dose[] = {"Covaxin Dose 2"};
						//cboMonth = new JComboBox (Months);
						//txtPath = new JComboBox (dose);
						
							
					}
						System.out.println("reci"+records[x][0]);
					System.out.println("reci"+records[x][1]);
					System.out.println("reci"+records[x][2]);
					System.out.println("reci"+records[x][3]);
					System.out.println("reci"+records[x][4]);
					System.out.println("reci"+records[x][5]);
					System.out.println("reci"+records[x][6]);
					System.out.println("reci"+records[x][7]);
					
					
					
					//txtClear ();
					break;
					}
				}
				if(!found)
				{
					if(!running)
					{
						detectFace=true;
						FingerPrintScan.file1 = txtNo.getText();
									FingerPrintScan frame1 = new FingerPrintScan();
									 frame1.setVisible(true);	
									
				System.out.println("Reader Start");
				}
				}
			}
		  	  }
		  }
	}

	//Function use to load all Records from File when Application Execute.
	void populateArray ()
	{
		try
		{
			fis = new FileInputStream ("Patient.dat");
			dis = new DataInputStream (fis);
			while (true)
			{
				for (int i = 0; i < 8; i++)
				{
					records[rows][i] = dis.readUTF ();
				}
				rows++;
			}
		}
		catch (Exception ex)
		{
			total = rows;
			if (total == 0) { }
			else {
				try {
					dis.close();
					fis.close();
				}
				catch (Exception exp) { }
			}
		}

	}

	//Function use to Find Record by Matching the Contents of Records Array with ID TextBox.
	void findRec () {

		boolean found = false;
		for (int x = 0; x < total; x++) {
			if (records[x][0].equals (txtNo.getText())) {
				found = false;
				JOptionPane.showMessageDialog (this, "Patient No. " + txtNo.getText () + " is Already Exist.Applying Second Dose.",
							"Applying Second Dose", JOptionPane.PLAIN_MESSAGE);
				//txtClear ();
				System.out.println(Login.FingerDBpath + "//"+txtNo.getText()+".txt");
				File file
	            = new File(Login.FingerDBpath + "//"+txtNo.getText()+".txt");
				File file1
	            = new File(Login.FingerDBpath + "//"+txtNo.getText()+"_2.txt");
				
				file.renameTo(file1);
	        if (file.delete()) {
	            System.out.println("File deleted successfully");
	        }
	        else {
	            System.out.println("Failed to delete the file");
	        }
				break;
			}
		}
		if (found == false) {
			saveArray ();
			save=true;
		}

	}

	//Function use to add new Element to Array.
	void saveArray ()
	{
		saves[count][0] = txtNo.getText ();
		saves[count][1] = txtName.getText ();
		saves[count][2] = "" + cboMonth.getSelectedItem ();
		saves[count][3] = "" + cboDay.getSelectedItem ();
		saves[count][4] = "" + cboYear.getSelectedItem ();
		saves[count][5] = txtMobNo.getText ();
		saves[count][6] = txtAdd.getText();
		saves[count][7] = "" + txtPath.getSelectedItem();
		saveFile ();	//Save This Array to File.
		count++;
	}

	void saveFile ()
	{
		try
		{
			FileOutputStream fos = new FileOutputStream ("Patient.dat", true);
			DataOutputStream dos = new DataOutputStream (fos);
			dos.writeUTF (saves[count][0]);
			dos.writeUTF (saves[count][1]);
			dos.writeUTF (saves[count][2]);
			dos.writeUTF (saves[count][3]);
			dos.writeUTF (saves[count][4]);
			dos.writeUTF (saves[count][5]);
			dos.writeUTF (saves[count][6]);
			dos.writeUTF (saves[count][7]);
			JOptionPane.showMessageDialog (this, "The Record has been Saved Successfully",
						"Patient Information System - Record Saved", JOptionPane.PLAIN_MESSAGE);
			setVisible (false);
			Project.method();
		}
		catch (IOException ioe)
		{
			JOptionPane.showMessageDialog (this, "There are Some Problem with File",
						"Patient Information System - Problem", JOptionPane.PLAIN_MESSAGE);
		}

	}

	//Function use to Clear all TextFields of Window.
	void txtClear ()
	{
		txtNo.setText ("");
		txtName.setText ("");
		txtMobNo.setText ("");
		txtNo.requestFocus ();
	}
}