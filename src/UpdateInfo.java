package Project1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UpdateInfo extends JFrame implements ActionListener {
	
	JPanel p;
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9;
	JTextField t1,t2,t3,t4;
	JPasswordField pwd1,pwd2,pwd3;
	JButton b1,b2;
	Options op;
	Faculty fc;
	JOptionPane jop;
	long n11;
	
	public UpdateInfo(Options op,Faculty fc){
		
		this.op=op;
		this.fc=fc;;
		setLocation(420,100);
		setSize(500,520);
		setResizable(false);
		setTitle("Update Information");
		
		p=new JPanel();
		p.setLayout(null);
		
		l8=new JLabel("Hello "+fc.getUsername()+" !");
		l8.setBounds(5,5, 150, 50);
		p.add(l8);;
		
		l1=new JLabel("Update your record here");
		l1.setBounds(180,40, 150,20);
		p.add(l1);
		
		l2=new JLabel("Name : ");
		l2.setBounds(40, 60, 250, 80);
		p.add(l2);
		
		t1=new JTextField();
		t1.setBounds(180,80,250,30);
		t1.setText(fc.getName());
		p.add(t1);
		
		l3=new JLabel("Designation :");
		l3.setBounds(40,100,250,80);
		p.add(l3);
		
		t2=new JTextField();
		t2.setBounds(180,125,250,30);
		t2.setText(fc.getDesignation());
		p.add(t2);
		
		l4=new JLabel("Contact Number : ");
		l4.setBounds(40,145,250,80);
		p.add(l4);
		
		t3=new JTextField();
		t3.setBounds(180,170,250,30);
		t3.setText(fc.getPhone());
		p.add(t3);
		
		l5=new JLabel("Email ID : ");
		l5.setBounds(40,188,250,80);
		p.add(l5);
		
		t4=new JTextField();
		t4.setBounds(180,215,250,30);
		t4.setText(fc.getEmail());
		p.add(t4);
		
		l9=new JLabel("Enter Old Password : ");
		l9.setBounds(40,234,250,80);
		p.add(l9);
		
		pwd3=new JPasswordField();
		pwd3.setBounds(180,260,250,30);
		pwd3.setText("");
		p.add(pwd3);
		
		l6=new JLabel("New Password : ");
		l6.setBounds(40,280,250,80);
		p.add(l6);
		
		pwd1=new JPasswordField();
		pwd1.setBounds(180,305,250,30);
		//pwd1.setText(fc.getPwd1());
		pwd1.setText("");
		p.add(pwd1);
		
		l7=new JLabel("Confirm Password : ");
		l7.setBounds(40,325,250,80);
		p.add(l7);
		
		pwd2=new JPasswordField();
		pwd2.setBounds(180,350,250,30);
		pwd2.setText("");
		p.add(pwd2);
		
		
		b1=new JButton("Update");
		b1.addActionListener(this);
		b1.setBounds(180,410,100,25);
		p.add(b1);
		
		b2=new JButton("Cancel");
		b2.addActionListener(this);
		b2.setBounds(320,410,100,25);
		p.add(b2);
		
		
		add(p);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		String st=t4.getText();
		int a=st.indexOf("@");
		int b=st.lastIndexOf(".");
		int len=st.length();
		if(e.getActionCommand().equals("Update")){	//if we click on register button
			
			try
			{
			n11=Long.parseLong(t3.getText());
			}
			catch(NumberFormatException ae){
				jop.showMessageDialog(null,"Enter Integer Contact no");
				t3.setText("0");
			}
			//n11=Integer.parseInt(t4.getText());
			n11=Long.parseLong(t3.getText());
		
			if((t1.getText().trim()).equals("") || pwd1.getText().equals("") ||
					(t2.getText().trim()).equals("") || (t3.getText().trim()).equals("") ||
					(t4.getText().trim()).equals("") || pwd2.getText().equals("") )
			{
				JOptionPane.showMessageDialog(this, "Field cannot be left empty");
			}
			
			else if(!(pwd3.getText().equals(fc.getPwd1()))){
				
				JOptionPane.showMessageDialog(this, "Please check your old password and reenter");
				pwd3.setText("");
				pwd1.setText("");
				pwd2.setText("");
				
			}
			else if(!(pwd1.getText().equals(pwd2.getText()))){
				
				JOptionPane.showMessageDialog(this, "Passwords don't match");
				pwd3.setText("");
				pwd1.setText("");
				pwd2.setText("");
			}
			else if(pwd1.getText().length()<8)
			{
				JOptionPane.showMessageDialog(this, "Password Must be Atleast 8 Characters Long");
				pwd3.setText("");
				pwd1.setText("");
				pwd2.setText("");
			}
			
			else if(b-a<=1||b==len-1){       // check format of email ID
				JOptionPane.showMessageDialog(this, "'Enter Email ID in the format abc@xyz.com'");
				t4.setText("");
			}
	 
			else if(n11!=0){
				try {
					Connection con=Project1.Provider.getConn();
					PreparedStatement ps=con.prepareStatement("update userinfo set name=?,designation=?,phone=?,email=?,pwd1=? where username=?");
						
					ps.setString(1, t1.getText());
					//ps.setString(2, fc.getUsername());
					ps.setString(2,t2.getText());
					ps.setString(3,t3.getText());
					ps.setString(4,t4.getText());
					ps.setString(5,pwd1.getText());
					ps.setString(6, fc.getUsername());
					
					// update faculty data
					fc.setDesignation(t2.getText());
					fc.setEmail(t4.getText());
					fc.setName(t1.getText());
					fc.setPhone(t3.getText());
					fc.setPwd1(pwd1.getText());
					
						
					int n=ps.executeUpdate();
				
					if(n>0){
						JOptionPane.showMessageDialog(this,fc.getUsername()+" Record updated !!!");
					}
					this.dispose();
					op.setVisible(true);
					/*
					Statement stmt=con.createStatement();
					ResultSet rs=stmt.executeQuery("select * from student");
					while(rs.next()){
						System.out.println(rs.getString("name")+" "+rs.getString("password")+" "+rs.getString("address")+rs.getInt("phone"));
					}*/
					con.close();
				} catch (SQLException ae) {
					// TODO Auto-generated catch block
					ae.printStackTrace();
				}
			
			}
			
		}
		else{     // if we click on cancel
			this.dispose();
			op.setVisible(true);
		}
	}
}
