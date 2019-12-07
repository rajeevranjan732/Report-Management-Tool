package Project1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

public class LoginPage extends JFrame implements ActionListener {
	
	JPanel p;
	JLabel l1,l2;
	JTextField t1;
	JPasswordField pwd;
	JButton b1,b2,b3;
	
	public LoginPage(){
		
		setSize(500,250);
		//setResizable(false);
		setTitle("Login or Register New Faculty");
		setLocation(400,250);
		setResizable(false);
		
		p=new JPanel();
		p.setLayout(null);
		
		l1=new JLabel("Enter User Name : ");
		l1.setBounds(40, 10, 250, 80);
		p.add(l1);
		
		t1=new JTextField();
		t1.setBounds(180,30,250,30);
		p.add(t1);
		
		l2=new JLabel("Enter Password : ");
		l2.setBounds(40,50,250,80);
		p.add(l2);
		
		pwd=new JPasswordField();
		pwd.setBounds(180,75,250,30);
		p.add(pwd);
		
		b1=new JButton("Login");
		b1.setBounds(100,140,100,25);
		b1.addActionListener(this);
		p.add(b1);
		
		b2=new JButton("Sign Up");
		b2.setBounds(210,140,100,25);
		b2.addActionListener(this);
		p.add(b2);
		
		b3=new JButton("Exit");
		b3.setBounds(320,140,100,25);
		b3.addActionListener(this);
		p.add(b3);
		
		add(p);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand().equals("Login")){
			if((t1.getText().trim()).equals("") || pwd.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Field cannot be left empty");
			else{
				
				try {
					Connection con=Provider.getConn();
					Statement stmt=con.createStatement();
					String str=t1.getText();
					ResultSet rs=stmt.executeQuery("select * from userinfo");
					int count=0;
					try{
						while(rs.next()){
							if(rs.getString("username").equals(t1.getText())){
								if(rs.getString("pwd1").equals(pwd.getText())){
									//set the values in faculty object
									
									Faculty fc=new Faculty();
									fc.setName(rs.getString("name"));
									fc.setUsername(rs.getString("username"));
									fc.setDesignation(rs.getString("designation"));
									fc.setPhone(rs.getString("phone"));
									fc.setEmail(rs.getString("email"));
									fc.setPwd1(rs.getString("pwd1"));
									
									Options ob=new Options(this,fc);
									this.setVisible(false);
									ob.setVisible(true);
									count=1;
									break;
								}
								else
								{
									JOptionPane.showMessageDialog(this, "Password does not match");
									pwd.setText("");
									count=1;
									break;
								}
							}
						}
						if(count==0){
							JOptionPane.showMessageDialog(this, "Access Denied");
							pwd.setText("");
							t1.setText("");
						
						}
							
					}
					catch(NullPointerException np){
					
						JOptionPane.showMessageDialog(this, "Access Denied");
						pwd.setText("");
						t1.setText("");
					}
					
					con.close();
					
				} 
				
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}
		else if(ae.getActionCommand().equals("Sign Up")){
			SignUp su=new SignUp(this);
			this.setVisible(false);
			su.setVisible(true);
		}
		else
		{
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {	
		new LoginPage();

	}
}
