import javax.swing.*;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

//===================================================================================================================================================================

class Project extends JFrame implements ActionListener
{
	private JDesktopPane desktop = new JDesktopPane ();
	static JButton register=new JButton(new ImageIcon("images/registerimage.png"));
	static JButton verify=new JButton(new ImageIcon("images/verifyimage.png"));
	int width=750,height=305;
	JPanel panel=new JPanel(null);
	static Project frame=null;
	public void actionPerformed(ActionEvent e){}

	
	public static void main(String a[])
	{
		
		IndentifyPatient f=new IndentifyPatient("Patient Identify",new JFrame());
		f.setVisible(true);
	}
	Project(int i){}

	Project()
	{
		
		setSize(width,height);
		setTitle("BioMedical System");
		setResizable(true);
		setSize (1300, 760);
		panel.setBackground(new Color(100,182,99));
		
		//Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		//setLocation((d.width-width)/2,(d.height-height)/2);
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/stop1.jpg"));
		register.setOpaque(true);
		register.setToolTipText("Click here for Registration");
		verify.setToolTipText("Click here for Verification");

//		panel.add(register,"East");
//		panel.add(verify,"West");
//		
		register.setBounds(150,100,500,500);
		verify.setBounds(750,100,500,500);
		
		panel.add(register);
		panel.add(verify);
		
		add(panel);
		setVisible(true);
//		setAlwaysOnTop(true);
		setDefaultCloseOperation(3);

	}

//===================================================================================================================================================================
//public static void main(String args[])
public void start()
{
	try
	{
			AudioClip ac = Applet.newAudioClip(new File("Sounds\\Welcome.wav").toURL());
			ac.play();
			method();
   }
   catch(Exception e){}
}

//===================================================================================================================================================================

	public static void method()
	{

		frame = new Project();

		register.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
				
				RegisterPatient newAcc = new RegisterPatient ();
				newAcc.setVisible(true);
			}
		});


		verify.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
				
				IndentifyPatient f=new IndentifyPatient("Patient Identify",new JFrame());
			f.setVisible(true);
			}
		});

	}
}

//==================================================================================================================================================================
