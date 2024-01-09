import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class IdentifyPatientInfo extends JFrame implements ActionListener
{
	private JPanel jpDep = new JPanel();
	private JLabel lbNo, lbName, lbDate, lbMob,lbAdd,lbReport;
	private JButton btnMark, btnCancel,btnDown;
	private JTextField txtNo, txtName, txtMob,txtAdd,txtDate;
	private JFileChooser  destfile= new JFileChooser("./");
	boolean found = false;
	private String spath,dpath;

	private int recCount = 0;
	private int rows = 0;
	private	int total = 0;

	private String records[][] = new String [500][8];

	private FileInputStream fis;
	private DataInputStream dis;
	String acno;

	IdentifyPatientInfo (String rs)
	{
		super ("Patient Identifier");
		
		System.out.println("Patient No:"+rs);
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((d.width-335)/2,(d.height-280)/2);

		acno=rs;
		setSize (335, 280);

		jpDep.setLayout (null);

		lbNo = new JLabel ("Adhar No:");
		lbNo.setForeground (Color.black);
		lbNo.setBounds (15, 20, 80, 25);

		lbName = new JLabel ("Patient Name:");
		lbName.setForeground (Color.black);
	    lbName.setBounds (15, 55, 85, 25);

		lbDate = new JLabel ("Date of Birth:");
		lbDate.setForeground (Color.black);
		lbDate.setBounds (15, 90, 80, 25);

		lbMob = new JLabel ("Mobile No:");
		lbMob.setForeground (Color.black);
		lbMob.setBounds (15, 125, 80, 25);

		lbAdd = new JLabel ("Address.");
		lbAdd.setForeground (Color.black);
		lbAdd.setBounds (15, 160, 80, 25);

		lbReport = new JLabel ("Med. Report");
		lbReport.setForeground (Color.black);
		lbReport.setBounds (15, 195, 80, 25);

		txtNo = new JTextField ();
		txtNo.setEnabled (false);
		txtNo.setBounds (105, 20, 205, 25);

		txtName = new JTextField ();
		txtName.setEnabled (false);
		txtName.setBounds (105, 55, 205, 25);

		txtDate = new JTextField ();
		txtDate.setEnabled (false);
		txtDate.setBounds (105, 90, 205, 25);

		txtMob = new JTextField ();
		txtMob.setEnabled (false);
		txtMob.setBounds (105, 125, 205, 25);

		txtAdd = new JTextField ();
		txtAdd.setEnabled (false);
		txtAdd.setBounds (105, 160, 205, 25);

		btnDown = new JButton ("Download Report");
		btnDown.setBounds (105, 195, 205, 25);
		btnDown.addActionListener (this);

		jpDep.add (lbNo);
		jpDep.add (txtNo);
		jpDep.add (lbName);
		jpDep.add (txtName);
		jpDep.add (lbDate);
		jpDep.add (txtDate);
		jpDep.add (lbMob);
		jpDep.add (txtMob);
		jpDep.add (lbAdd);
		jpDep.add (txtAdd);
		jpDep.add (lbReport);
		jpDep.add (btnDown);

		getContentPane().add (jpDep);
	
		populateArray ();	//Load All Existing Records in Memory.

		String detectRecord=acno;
		for (int x = 0; x < total; x++)
		{
			if (records[x][0].equals(acno.replace(".txt","")))
			{
				found = true;
				showRec(x);
				//new File(Login.FingerDBpath+detectRecord).delete();	
				System.out.println(Login.FingerDBpath+detectRecord);
				break;
			}
		}
	}

	public void actionPerformed (ActionEvent ae)
	{
		Object obj = ae.getSource();

		if (obj == btnDown)
		{
			destfile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

	         if(destfile.showOpenDialog(null)== destfile.APPROVE_OPTION)
			{
			  try
			  {
	          dpath= destfile.getSelectedFile().getPath();
			  
	          String extension = "";
	          int i = spath.lastIndexOf('.');
	          if (i > 0) 
	          {
	              extension = spath.substring(i+1);
	          }
	          
  		      FileInputStream fin=new FileInputStream(spath);

			  FileOutputStream fout=new FileOutputStream(dpath+"."+extension);

			  int  ch=fin.read();//read file char

			  while(ch!=-1)//end of file
			  {
			    fout.write((char)ch);
			     ch=fin.read();
			  }
  			  JOptionPane.showMessageDialog (null, "Report Downloaded Successfully.","Patient System", JOptionPane.PLAIN_MESSAGE);

			  fin.close();
			  fout.close();
			  System.exit(0);
			  }
			  catch(Exception e){}
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
			if (total == 0)
			{
				JOptionPane.showMessageDialog (null, "Records File is Empty.\nEnter Records First to Display.","Patient System - EmptyFile", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}

	public void showRec (int intRec)
	{
		txtNo.setText (records[intRec][0]);
		txtName.setText (records[intRec][1]);
		txtDate.setText (records[intRec][2]+" - "+records[intRec][3]+" , "+records[intRec][4]);
		txtMob.setText(records[intRec][5]);
		txtAdd.setText(records[intRec][6]);
		spath=records[intRec][7];
	}
}