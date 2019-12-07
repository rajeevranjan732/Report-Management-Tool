package Project1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Options extends JFrame implements ActionListener {
	
	JPanel p;
	JButton b1,b2,b3,b4,b5,b6;
	LoginPage lp;
	Faculty fc;
	JLabel l1;
	//private EnterBatch eb;	
	public Options(LoginPage lp,Faculty fc){
		this.lp=lp;
		this.fc=fc;
		
		p=new JPanel();
		setSize(350,550);
		setResizable(false);
		setLocation(500,100);
		setTitle("Options");
		p.setLayout(null);
		
		l1=new JLabel("Hello "+fc.getUsername()+" !");
		l1.setBounds(5, 5, 150, 10);
		p.add(l1);
		
		b1=new JButton("NEW BATCH REPORT");
		b1.addActionListener(this);
		p.add(b1);
		b1.setBounds(50,50,250,30);
		
		b2=new JButton("UPDATE USER INFORMATION");
		b2.addActionListener(this);
		p.add(b2);
		b2.setBounds(50,125,250,30);
		
		b6=new JButton("VIEW BATCHES");
		b6.addActionListener(this);
		p.add(b6);
		b6.setBounds(50,200,250,30);
		
		b3=new JButton("UPDATE BATCH REPORT");
		b3.addActionListener(this);
		p.add(b3);
		b3.setBounds(50,275,250,30);
		
		b5=new JButton("TOPICS RECORD");
		b5.addActionListener(this);
		p.add(b5);
		b5.setBounds(50,350,250,30);
	
		
		b4=new JButton("LOGOUT");
		p.add(b4);
		b4.addActionListener(this);
		b4.setBounds(50,425,250,30);
		
		add(p);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand().equals("LOGOUT")){
			this.dispose();
			fc=null;
			lp.setVisible(true);
			lp.pwd.setText("");
			lp.t1.setText("");
		}
		
		else if(ae.getActionCommand().equals("VIEW BATCHES")){
			ViewCurrentBatches vcb=new ViewCurrentBatches(this,fc);
			this.setVisible(true);
			vcb.setVisible(true);
		}
		
		
		else if(ae.getActionCommand().equals("UPDATE BATCH REPORT")){
			EnterBatch eb=new EnterBatch(this,fc);
			this.setVisible(false);
			eb.setVisible(true);
		}
		else if(ae.getActionCommand().equals("NEW BATCH REPORT")){
			BatchReport br=new BatchReport(this,fc);
			this.setVisible(false);
			br.setVisible(true);
		}
		else if(ae.getActionCommand().equals("UPDATE USER INFORMATION")){
			UpdateInfo ui=new UpdateInfo(this,fc);
			this.setVisible(false);
			ui.setVisible(true);
		}
		
		else if(ae.getActionCommand().equals("TOPICS RECORD")){
			
			EnterBatch1 eb=new EnterBatch1(this,fc);
			this.setVisible(false);
			eb.setVisible(true);
		}
	}
	
}
