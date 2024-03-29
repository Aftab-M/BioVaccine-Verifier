import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;

import javax.swing.border.*;

class IndentifyPatient extends JFrame implements ActionListener
{
	private JButton btnStartCam,btnDetect,btnCompare,btnCancel;
	//JPicsPanel pp;
	
	boolean running=false;
	static String result;
	String command;
	JFrame desktop;

	IndentifyPatient(String cmd,JFrame pan)
	{
		command=cmd;
		desktop=pan;
		setSize (2000, 1170);
		setLocationRelativeTo(null);
		setLayout(null);
		setTitle("Verification");
		
		btnStartCam = new JButton("Fingerprint");
		btnStartCam.addActionListener(this);
		btnStartCam.setBounds(106,10,120,35);
		
		btnDetect = new JButton("Verify");
		btnDetect.addActionListener(this);
		btnDetect.setBounds(106,70,120,35);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(106,120,120,35);

		JPanel btnPan = new JPanel();

		add(btnStartCam);
		add(btnDetect);
		add(btnCancel);

		JPanel panel = new JPanel ();
        JLabel label = new JLabel();
        panel.setBounds(0,0,2000,1170);
        label.setIcon(new ImageIcon("Images/Screenshot_5.png"));// your image here
        panel.add(label);

		//jpInfo.add (cboUser);
		add (panel);


		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				if(running)
				{
					//pp.closeDown();

					dispose();
				}
				else
					dispose();
			}
		});
	}
	public void actionPerformed(ActionEvent e)
	{
		Object obj=e.getSource();
		if(obj == btnStartCam)
		{
				
				 FingerPrintVerify frame1 = new FingerPrintVerify();
				 frame1.setVisible(true);
				 running=true;
		}
		else
		if(obj == btnDetect)
		{
			if(running)
			{
				System.out.println("detect Press");
		
				
					boolean bCanceled = false;
						dispose();
						System.out.println("Exit From Try");

						//result=result.replace(".jpg","");

				System.out.println(result + "****");
				running=false;
				if(result != null)
				{
					
					{
						System.out.println("Result Length:="+result.length());
						if(result.equals(""))
						{
						  System.out.println("No Result Found...");
						 JOptionPane.showMessageDialog (this, "Not a Registered User !!! ","Wrong User", JOptionPane.PLAIN_MESSAGE);
					   }
					   else
						  {
							 System.out.println(Login.FingerDBpath+result);
							  File f1 = new File(Login.FingerDBpath+result);
								if(f1.exists()) { 
									 System.out.println("Record Detected:..");
									JOptionPane.showMessageDialog (this, "Door Opening","Door Opening", JOptionPane.PLAIN_MESSAGE);
									
								}else{
									JOptionPane.showMessageDialog (this, "Door Not Opening","Door Opening", JOptionPane.PLAIN_MESSAGE);
											
								}
							

				}
			}
		}
				else
				{
					JOptionPane.showMessageDialog(null,"FingerPrint Not Matching \n\t\t Please Try Again","Login Face",JOptionPane.WARNING_MESSAGE);
				}
			}
			
			else
				JOptionPane.showMessageDialog(null,"Finger Print Not Started \n \t Start Webcam" );


		}
		else
		if(obj == btnCancel)
		{
			if(running)
			{
	//			pp.closeDown();
				dispose();
			}
			else
			{
				dispose();
			}
		}

	}
	class JCameraPanel extends JPanel
	{
		JCameraPanel()
		{
			setSize(250,230);
		}
		public void paint(Graphics g)
		{
			EtchedBorder border=new EtchedBorder(EtchedBorder.LOWERED,Color.gray ,Color.lightGray );
			//cm.setBorder(border);

			Font f=new Font("Times New Roman",Font.BOLD,30);
			g.setFont(f);
			border.paintBorder(this, g, 0, 0,getWidth(),getHeight());
			g.drawString("WebCam",70,110);
		}
	}
	public String getResult()
	{
		return result;
	}
}