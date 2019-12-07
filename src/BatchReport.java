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

public class BatchReport extends JFrame implements ActionListener {
	
	JPanel p;
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9;
	JTextField t1,t2,t3,t4,t5,t6,t7;
	//JPasswordField t6,t7;
	JButton b1,b2,b3,b4;
	Options op;
	Faculty fc;
	int n1;
	
	public BatchReport(Options op,Faculty fc){
		
		this.op=op;
		setLocation(420,100);
		setSize(500,520);
		setTitle("BATCH REPORT!!");
		
		try {
			Connection con=Project1.Provider.getConn();
			PreparedStatement ps1=con.prepareStatement("Select MAX(BID) as MAX from batch");
		    ResultSet rs=ps1.executeQuery();
		    rs.next();
		    n1=rs.getInt("MAX");
				n1=n1+1;
				//System.out.println(""+n1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		p=new JPanel();
		p.setLayout(null);
		
		l9=new JLabel("UserName: ");
		l9.setBounds(5,5,150,20);
		p.add(l9);
		
		l8=new JLabel(""+fc.getUsername());
		l8.setBounds(100,5,150,20);
		p.add(l8);
		
		l1=new JLabel("BID :");
		l1.setBounds(40, 10, 250, 80);
		p.add(l1);
		
		t1=new JTextField();
		t1.setText(""+n1);
		t1.setEditable(false);
		t1.setBounds(180,30,250,30);
		p.add(t1);
		
		l2=new JLabel("Batch Name :");
		l2.setBounds(40,50,250,80);
		p.add(l2);
		
		t2=new JTextField();
		t2.setBounds(180,75,250,30);
		p.add(t2);
		
		l3=new JLabel("Start Date :");
		l3.setBounds(40,95,250,80);
		p.add(l3);
		
		b3=new JButton("...");
		b3.setBounds(430, 120 ,30, 30);
		b3.addActionListener(this);
		p.add(b3);
		
		t3=new JTextField();
		t3.setBounds(180,120,250,30);
		t3.setEditable(false);
		p.add(t3);
		
		l4=new JLabel("End Date :");
		l4.setBounds(40,138,250,80);
		p.add(l4);
		
		b4=new JButton("..");
		b4.setBounds(430, 165 ,30, 30);
		b4.addActionListener(this);
		p.add(b4);
		
		t4=new JTextField();
		t4.setBounds(180,165,250,30);
		t4.setEditable(false);
		p.add(t4);
		
		l5=new JLabel("Course Duration :");
		l5.setBounds(40,184,250,80);
		p.add(l5);
		
		t5=new JTextField();
		t5.setBounds(180,210,250,30);
		p.add(t5);
		
		l6=new JLabel("Time :");
		l6.setBounds(40,230,250,80);
		p.add(l6);
		
		t6=new JTextField();
		t6.setBounds(180,255,250,30);
		p.add(t6);
		
		l7=new JLabel("Venue :");
		l7.setBounds(40,270,250,80);
		p.add(l7);
		
		t7=new JTextField();
		t7.setBounds(180,300,250,30);
		p.add(t7);
		
		b1=new JButton("ADD BATCH");
		b1.addActionListener(this);
		b1.setBounds(180,360,100,25);
		p.add(b1);
		
		b2=new JButton("Cancel");
		b2.addActionListener(this);
		b2.setBounds(320,360,100,25);
		p.add(b2);
		
		
		getContentPane().add(p);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getActionCommand().equals("ADD BATCH")){
			
			if((t1.getText().trim()).equals("") || (t6.getText().trim()).equals("") ||
					(t2.getText().trim()).equals("") || (t3.getText().trim()).equals("") ||
					(t4.getText().trim()).equals("") || (t5.getText().trim()).equals("") || (t7.getText().trim()).equals("") )
			{
				JOptionPane.showMessageDialog(this, "Field cannot be left empty");
			}
			else{
				try {
					Connection con=Project1.Provider.getConn();
					PreparedStatement ps1=con.prepareStatement("Select BID from batch where BID=?");
					ps1.setString(1,t1.getText());
						int n1=ps1.executeUpdate();
					
					if(n1>0){
						JOptionPane.showMessageDialog(this, "Same BID Exists In Database");
						t1.setText("");
						
					}
					
					else{
						
						PreparedStatement ps=con.prepareStatement("insert into batch(BID,BName,Startdate,Enddate,Days,Time,Venue,Username)values(?,?,?,?,?,?,?,?)");
						
						ps.setInt(1,Integer.parseInt(t1.getText()));
						ps.setString(2, t2.getText());
						ps.setString(3,t3.getText());
						ps.setString(4,t4.getText());
						ps.setString(5,t5.getText());
						ps.setString(6,t6.getText());
						ps.setString(7,t7.getText());
						ps.setString(8,l8.getText());
						int n=ps.executeUpdate();
					
						if(n>0){
							JOptionPane.showMessageDialog(this,"New Batch "+t1.getText()+" Added !!!");
						}
						this.dispose();
						op.setVisible(true);
					}
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
			
		}else if(ae.getActionCommand().equals("...")){
			final JFrame f = new JFrame();
			//set text which is collected by date picker i.e. set date 
			t3.setText(new DatePicker(f).setPickedDate());
		}
		else if(ae.getActionCommand().equals("..")){
			final JFrame f = new JFrame();
			//set text which is collected by date picker i.e. set date 
			t4.setText(new DatePicker(f).setPickedDate());
		}
		else{
			this.dispose();
			op.setVisible(true);
		}
		
	}
}