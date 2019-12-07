package Project1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;


public class EnterBatch extends JFrame implements ActionListener {

	JPanel p;
	JLabel l1,l2,l3;
	JTextField t1;
	JButton b1,b2,b3;
	Options op;
	Faculty fc;
	public EnterBatch(Options op,Faculty fc){
		this.op=op;
		p=new JPanel();
		p.setLayout(null);
		setLocation(400,250);
		
		setSize(500,250);
		setResizable(false);
		setTitle("Enter Batch");
		l3=new JLabel("UserName: ");
		l3.setBounds(5,5,150,20);
		p.add(l3);
		
		l2=new JLabel(""+fc.getUsername());
		l2.setBounds(100,5,150,20);
		p.add(l2);
		
		l1=new JLabel("Enter your Batch ID : ");
		l1.setBounds(30,30,250,80);
		p.add(l1);
		
		t1=new JTextField();
		t1.setBounds(180,55,250,30);
		p.add(t1);
		
		b1=new JButton("Update");
		b1.addActionListener(this);
		b1.setBounds(190,130,100,30);
		p.add(b1);
		
	    /*b3=new JButton("View");
		b3.setBounds(240,130,100,30);
		b3.addActionListener(this);
		p.add(b3);*/
		
		b2=new JButton("Cancel");
		b2.setBounds(320,130,100,30);
		b2.addActionListener(this);
		p.add(b2);
		
		add(p);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand().equals("Cancel")){
			this.dispose();
			op.setVisible(true);
		}
		/*else if(ae.getActionCommand().equals("View"))
		{
			
			try {
				Connection con=Project1.Provider.getConn();
				PreparedStatement ps1=con.prepareStatement("Select username,BID from batch where BID=? AND username=?");
				ps1.setString(1,t1.getText());
				ps1.setString(2,l2.getText());
					int n1=ps1.executeUpdate();
				
				if(n1<=0){
					JOptionPane.showMessageDialog(this, "Faculty Not Authorised to View");
					t1.setText("");
					
				}
				
				else
				{
				 
					//ViewBatch ob1=new ViewBatch(this,t1.getText());
					//this.setVisible(false);
					//ob1.setVisible(true);
				 
				 
				
				}
			}
			
				catch (SQLException e) {
					
					e.printStackTrace();
				}	
		
		}*/
		else
		{
			try {
				Connection con=Project1.Provider.getConn();
				PreparedStatement ps1=con.prepareStatement("Select username,BID from batch where BID=? AND username=?");
				ps1.setString(1,t1.getText());
				ps1.setString(2,l2.getText());
					int n1=ps1.executeUpdate();
				
				if(n1<=0){
					JOptionPane.showMessageDialog(this, "Faculty Not Authorised to Update");
					t1.setText("");
					
				}
				
				else
				{
					UpdateBatch ob=new UpdateBatch(this,t1.getText(),l2.getText());
					this.setVisible(false);
					ob.setVisible(true);
				}
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
	

}
