package Project1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class ViewBatch extends JFrame implements ActionListener{
    JPanel p;
    JTextArea ar,ar1;
    JButton b1;
	EnterBatch1 eb1;
	String BID;
	JLabel l1;
	JTable table;
	String[] columnNames;
	String[][] dataValues;
	public ViewBatch(EnterBatch1 eb1,String BID)
	{
		this.eb1=eb1;
		this.BID=BID;
		p=new JPanel();
		p.setLayout(new BorderLayout());
		setLocation(400,250);
		
		setSize(600,250);
		//setResizable(false);
		setTitle("View ");
		
try {
			
			Connection con=Provider.getConn();
			PreparedStatement ps=con.prepareStatement("select * from batchrec where bid=?", ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE );
					//("select * from batch where username=?",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE));
			ps.setString(1,BID);
			ResultSet rs=ps.executeQuery();
			
			CreateColumns();
			CreateData(rs);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		table = new JTable( dataValues, columnNames );

		table.setRowSelectionAllowed( true );
		table.setColumnSelectionAllowed( true );

		// Change the selection colour
		table.setSelectionForeground( Color.white );
		table.setSelectionBackground( Color.red );

		// Add the table to a scrolling pane
		JScrollPane scrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		p.add( scrollPane, BorderLayout.CENTER );
		
		
		add(p);
		//setDefaultCloseOperation();
		setVisible(true);
		
	}
		
		public void CreateColumns()
		{
			// Create column string labels
			columnNames = new String[3];
			
			columnNames[0]="BID";
			columnNames[1]="DATE";
			columnNames[2]="Topics Covered";

		}

		public void CreateData(ResultSet rs)
		{
			// Create data for each element
			int y=0;
			if (rs == null) {
			    y=0;
			}
			try {
		      rs.last();
			     y= rs.getRow();
			 } catch (SQLException exp) {
				 exp.printStackTrace();
			   } finally {
			       try {
			          rs.beforeFirst();
			       } catch (SQLException exp) {
			          exp.printStackTrace();
			       }
			   }
			dataValues = new String[y][7];
			
			try {
				rs.next();
				
				for( int iY = 0; iY < y; iY++ )
				{
					dataValues[iY][0]=rs.getString("bid");
					dataValues[iY][1]=rs.getString("date1");
					dataValues[iY][2]=rs.getString("topicscovered");

					rs.next();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		this.dispose();
		eb1.setVisible(true);
		//eb1.t1.setText("");
	}


}
