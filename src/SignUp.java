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

public class SignUp extends JFrame implements ActionListener {
	
	JPanel p;
	JLabel l1,l2,l3,l4,l5,l6,l7,l8;
	JTextField t1,t2,t3,t4,t5;
	JPasswordField pwd1,pwd2;
	JButton b1,b2;
	LoginPage lp;
	JOptionPane jop;
	
	public SignUp(LoginPage lp){
		
		this.lp=lp;
		setLocation(420,100);
		setSize(500,520);
		setResizable(false);
		setTitle("Sign Up New User");
		
		p=new JPanel();
		p.setLayout(null);
		
		l8=new JLabel("All fields are mandatory");
		l8.setBounds(180,40, 150,20);
		p.add(l8);
		
		l1=new JLabel("Name : ");
		l1.setBounds(40, 60, 250, 80);
		p.add(l1);
		
		t1=new JTextField();
		t1.setBounds(180,80,250,30);
		p.add(t1);
		
		l2=new JLabel("User Name : ");
		l2.setBounds(40,100,250,80);
		p.add(l2);
		
		t2=new JTextField();
		t2.setBounds(180,125,250,30);
		p.add(t2);
		
		l3=new JLabel("Designation : ");
		l3.setBounds(40,145,250,80);
		p.add(l3);
		
		t3=new JTextField();
		t3.setBounds(180,170,250,30);
		p.add(t3);
		
		l4=new JLabel("Contact Number : ");
		l4.setBounds(40,188,250,80);
		p.add(l4);
		
		t4=new JTextField();
		t4.setBounds(180,215,250,30);
		p.add(t4);
		
		l5=new JLabel("Email ID : ");
		l5.setBounds(40,234,250,80);
		p.add(l5);
		
		t5=new JTextField();
		t5.setBounds(180,260,250,30);
		p.add(t5);
		
		l6=new JLabel("Password : ");
		l6.setBounds(40,280,250,80);
		p.add(l6);
		
		pwd1=new JPasswordField();
		pwd1.setBounds(180,305,250,30);
		p.add(pwd1);
		
		l7=new JLabel("Confirm Password : ");
		l7.setBounds(40,320,250,80);
		p.add(l7);
		
		pwd2=new JPasswordField();
		pwd2.setBounds(180,350,250,30);
		p.add(pwd2);
		
		b1=new JButton("Register");
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
		String st=t5.getText();
		int a=st.indexOf("@");
		int b=st.lastIndexOf(".");
		int len=st.length();
		long n11;
	
		
		if(e.getActionCommand().equals("Register")){	//if we click on register button
			
			try
			{
			n11=Long.parseLong(t4.getText());
			}
			catch(NumberFormatException ae){
				jop.showMessageDialog(null,"Enter Integer Contact no");
				t4.setText("0");
			}
			//n11=Integer.parseInt(t4.getText());
			n11=Long.parseLong(t4.getText());
			if((t1.getText().trim()).equals("") || pwd1.getText().equals("") ||
					(t2.getText().trim()).equals("") || (t3.getText().trim()).equals("") ||
					(t4.getText().trim()).equals("") || (t5.getText().trim()).equals("") || pwd2.getText().equals("") )
			{
				JOptionPane.showMessageDialog(this, "Field cannot be left empty");
			}
			else if(!(pwd1.getText().equals(pwd2.getText()))){
				
				JOptionPane.showMessageDialog(this, "Passwords don't match");
				pwd1.setText("");
				pwd2.setText("");
			}
			else if(pwd1.getText().length()<8)
			{
				JOptionPane.showMessageDialog(this, "Password Must be Atleast 8 Characters Long");
				pwd1.setText("");
				pwd2.setText("");
			}
			
			else if(b-a<=1||b==len-1){       // check format of email ID
				JOptionPane.showMessageDialog(this, "'Enter Email ID in the format abc@xyz.com'");
				t5.setText("");
			}
		
	
			else if(n11!=0){
				try {
					Connection con=Project1.Provider.getConn();
					PreparedStatement ps1=con.prepareStatement("Select name from userinfo where username=?");
					ps1.setString(1,t2.getText());
						int n1=ps1.executeUpdate();
					
					if(n1>0){
						JOptionPane.showMessageDialog(this, "Same Username Exists In Database");
						t2.setText("");
						pwd1.setText("");
						pwd2.setText("");
					}
					
					
					else{
						PreparedStatement ps=con.prepareStatement("insert into userinfo(name,username,designation,phone,email,pwd1)values(?,?,?,?,?,?)");
						
						ps.setString(1, t1.getText());
						ps.setString(2, t2.getText());
						ps.setString(3,t3.getText());
						ps.setString(4,t4.getText());
						ps.setString(5,t5.getText());
						ps.setString(6,pwd1.getText());
						
						int n=ps.executeUpdate();
					
						if(n>0){
							JOptionPane.showMessageDialog(this,"New Faculty "+t2.getText()+" registered !!!");
						}
						this.dispose();
						lp.setVisible(true);
					}
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
			lp.setVisible(true);
		}
	}
}
