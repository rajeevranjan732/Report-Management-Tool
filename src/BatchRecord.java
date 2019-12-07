package Project1;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;

public class BatchRecord extends JFrame implements ActionListener{
	
	JLabel l1,l2,l3,l8;
	JTextField t1;
	JTextArea ar;
	JButton b1,b2,b3;
	JPanel p;
	EnterBatch1 eb1;
	
	public BatchRecord(EnterBatch1 eb1,String BID)
	{
		
	    this.eb1=eb1;	
		setSize(500,250);
		//setResizable(false);
		setTitle("Batch Record!!!");
		setLocation(400,250);
		setResizable(false);
	
		p=new JPanel();
		p.setLayout(null);
		
		l8=new JLabel("Batch Id:");
		l8.setBounds(5, 5, 200, 20);
		p.add(l8);
		
		l3=new JLabel(""+BID);
		l3.setBounds(85,5, 200, 20);
		p.add(l3);
		
		l1=new JLabel("DATE : ");
		l1.setBounds(40, 10, 250, 80);
		p.add(l1);
		
		t1=new JTextField();
		t1.setBounds(180,30,250,30);
		p.add(t1);
		t1.setEditable(false);
		t1.setColumns(10);
		
		//create button and there object
		b3 = new JButton("...");
		b3.addActionListener(this);
		b3.setBounds(440, 30, 30, 30);
		p.add(b3);
		
		l2=new JLabel("Topics Covered : ");
		l2.setBounds(40,50,250,80);
		p.add(l2);
		
		ar=new JTextArea();
		ar.setBounds(180,75,250,30);
		p.add(ar);
		
		
		b1=new JButton("Record Details");
		b1.setBounds(180,150,150,25);
		b1.addActionListener(this);
		p.add(b1);
		
		b2=new JButton("Cancel");
		b2.setBounds(350,150,100,25);
		b2.addActionListener(this);
		p.add(b2);
		
		add(p);
		
		//getContentPane().add(p);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Record Details")){
			
			if((ar.getText().trim()).equals("") ||
					(t1.getText().trim()).equals("") )
			{
				JOptionPane.showMessageDialog(this, "Field cannot be left empty");
			}
			else
			{
				
				try {
					Connection con=Project1.Provider.getConn();
					PreparedStatement ps=con.prepareStatement("insert into batchrec(bid,date1,topicscovered) values(?,?,?)");
					ps.setString(1,l3.getText());
					ps.setString(2,t1.getText());
					ps.setString(3,ar.getText());
						
						//JOptionPane.showMessageDialog(this, "Field cannot be left empty 1");
						int n=ps.executeUpdate();
					
						if(n>0){
							
							
							JOptionPane.showMessageDialog(this," Batch Record Of Batch "+l3.getText()+" Updated !!!");
						}
						this.dispose();
						eb1.setVisible(true);
					
					/*
					Statement stmt=con.createStatement();
					ResultSet rs=stmt.executeQuery("select * from student");
					while(rs.next()){
						System.out.println(rs.getString("name")+" "+rs.getString("password")+" "+rs.getString("address")+rs.getInt("phone"));
					}*/
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		
		}
		else if(e.getActionCommand().equals("...")){
			
			final JFrame f = new JFrame();
			//set text which is collected by date picker i.e. set date 
			t1.setText(new DatePicker(f).setPickedDate());
			
		}
		else 
		{
			
			this.dispose();
			eb1.setVisible(true);
			eb1.t1.setText("");
		
		}
	}
}
