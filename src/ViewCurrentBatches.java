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

public class ViewCurrentBatches extends JFrame implements ActionListener {
	
	Options op;
	Faculty fc;
	JLabel l1;
	JButton b1;
	JPanel p;
	JTable table;
	String[] columnNames;
	String[][] dataValues;
	
	public ViewCurrentBatches(Options op,Faculty fc){
		this.op=op;
		this.fc=fc;
		p=new JPanel();
		//p.setLayout(null);
		setLocation(200,75);
		
		setSize(1000,250);
		//setResizable(false);
		setTitle("View ");
		p.setLayout(new BorderLayout());
		
		try {
			
			Connection con=Provider.getConn();
			PreparedStatement ps=con.prepareStatement("select * from batch where username=? order by bid", ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE );
					//("select * from batch where username=?",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE));
			ps.setString(1, fc.getUsername());
			ResultSet rs=ps.executeQuery();
			
			CreateColumns();
			CreateData(rs);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		table = new JTable( dataValues, columnNames );

		// Configure some of JTable's paramters
		//table.setShowHorizontalLines( false );
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
		columnNames = new String[7];
		
		columnNames[0]="BID";
		columnNames[1]="Batch Name";
		columnNames[2]="Start Date";
		columnNames[3]="End Date";
		columnNames[4]="Course Duration";
		columnNames[5]="Time";
		columnNames[6]="Venue";
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
				dataValues[iY][1]=rs.getString("bname");
				dataValues[iY][2]=rs.getString("startdate");
				dataValues[iY][3]=rs.getString("enddate");
				dataValues[iY][4]=rs.getString("days");
				dataValues[iY][5]=rs.getString("time");
				dataValues[iY][6]=rs.getString("venue");
				rs.next();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		this.dispose();
		op.setVisible(true);
	}
}
