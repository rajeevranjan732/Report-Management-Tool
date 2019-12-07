package Project1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UpdateBatch extends JFrame implements ActionListener {
	
	JPanel p;
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10;
	JTextField t1,t2,t3,t4,t5,t6,t7;
	//JPasswordField t6,t7;
	JButton b1,b2,b3,b4;
	EnterBatch eb;
	String BID,username;
	public UpdateBatch(EnterBatch eb,String BID,String username)
	{
	this.eb=eb;
		setLocation(420,100);
		setSize(500,520);
		setTitle("BATCH REPORT UPDATE!!");
		
		p=new JPanel();
		p.setLayout(null);
		
		l8=new JLabel("Hello!! ");
		l8.setBounds(5, 5, 100, 20);
		p.add(l8);
		
		l10=new JLabel(username);
		l10.setBounds(10, 20, 100, 30);
		
		l1=new JLabel("BID");
		l1.setBounds(40, 10, 250, 80);
		p.add(l1);
		
		l9=new JLabel(BID);
		l9.setBounds(180,10,250,80);
		p.add(l9);
		
		try {
			Connection con=Project1.Provider.getConn();
			PreparedStatement ps1=con.prepareStatement("Select * from batch where BID=?");
			ps1.setString(1,BID);
			ResultSet rs=ps1.executeQuery();
		
		while(rs.next())
		{
		l2=new JLabel("Batch Name :");
		l2.setBounds(40,50,250,80);
		p.add(l2);
		
		t2=new JTextField();
		t2.setBounds(180,75,250,30);
		t2.setText(rs.getString("BName"));
		p.add(t2);
		
		l3=new JLabel("Start Date :");
		l3.setBounds(40,95,250,80);
		p.add(l3);
		
		t3=new JTextField();
		t3.setBounds(180,120,250,30);
		t3.setText(rs.getString("Startdate"));
		t3.setEditable(false);
		p.add(t3);
		
		b3=new JButton("...");
		b3.setBounds(430, 120, 30, 30);
		b3.addActionListener(this);
		p.add(b3);
		
		l4=new JLabel("End Date :");
		l4.setBounds(40,138,250,80);
		p.add(l4);
		
		t4=new JTextField();
		t4.setBounds(180,165,250,30);
		t4.setText(rs.getString("Enddate"));
		t4.setEditable(false);
		p.add(t4);
		
		b4=new JButton("..");
		b4.setBounds(430, 165, 30, 30);
		b4.addActionListener(this);
		p.add(b4);
		
		l5=new JLabel("Course Duration :");
		l5.setBounds(40,184,250,80);
		p.add(l5);
		
		t5=new JTextField();
		t5.setBounds(180,210,250,30);
		t5.setText(rs.getString("Days"));
		p.add(t5);
		
		l6=new JLabel("Time :");
		l6.setBounds(40,230,250,80);
		p.add(l6);
		
		t6=new JTextField();
		t6.setBounds(180,255,250,30);
		t6.setText(rs.getString("Time"));
		p.add(t6);
		
		l7=new JLabel("Venue :");
		l7.setBounds(40,270,250,80);
		p.add(l7);
		
		t7=new JTextField();
		t7.setBounds(180,300,250,30);
		t7.setText(rs.getString("Venue"));
		p.add(t7);
		
		}
		
		b1=new JButton("UPDATE BATCH");
		b1.addActionListener(this);
		b1.setBounds(100,360,130,25);
		p.add(b1);
		
		b2=new JButton("Cancel");
		b2.addActionListener(this);
		b2.setBounds(270,360,100,25);
		p.add(b2);
	
		
		getContentPane().add(p);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getActionCommand().equals("UPDATE BATCH")){
			
			if((t6.getText().trim()).equals("") ||
					(t2.getText().trim()).equals("") || (t3.getText().trim()).equals("") ||
					(t4.getText().trim()).equals("") || (t5.getText().trim()).equals("") || (t7.getText().trim()).equals("") )
			{
				JOptionPane.showMessageDialog(this, "Field cannot be left empty");
			}
			else
			{
				
				try {
					Connection con=Project1.Provider.getConn();
					
			
						
						PreparedStatement ps=con.prepareStatement("update batch set BID=?,BName=?,Startdate=?,Enddate=?,Days=?,Time=?,Venue=?,Username=? where BID=?");
						
						ps.setString(1,l9.getText());
						ps.setString(2,t2.getText());
						ps.setString(3,t3.getText());
						ps.setString(4,t4.getText());
						ps.setString(5,t5.getText());
						ps.setString(6,t6.getText());
						ps.setString(7,t7.getText());
						ps.setString(8,l10.getText());
						ps.setString(9,l9.getText());
						//JOptionPane.showMessageDialog(this, "Field cannot be left empty 1");
						int n=ps.executeUpdate();
					
						if(n>0){
							
							
							JOptionPane.showMessageDialog(this," Batch "+l9.getText()+" Updated !!!");
						}
						else{
							System.out.println("sorry");
						}
						this.dispose();
						eb.setVisible(true);
						eb.t1.setText("");
					
					/*
					Statement stmt=con.createStatement();
					ResultSet rs=stmt.executeQuery("select * from student");
					while(rs.next()){
						System.out.println(rs.getString("name")+" "+rs.getString("password")+" "+rs.getString("address")+rs.getInt("phone"));
					}*/
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		else if(ae.getActionCommand().equals("...")){
			
			final JFrame f = new JFrame();
			//set text which is collected by date picker i.e. set date 
			t3.setText(new DatePicker(f).setPickedDate());
			
		}
		else if(ae.getActionCommand().equals("..")){
			
			final JFrame f = new JFrame();
			//set text which is collected by date picker i.e. set date 
			t4.setText(new DatePicker(f).setPickedDate());
			
		}
		else
		{
			
				this.dispose();
				eb.setVisible(true);
				eb.t1.setText("");
		
		}
	}
}
