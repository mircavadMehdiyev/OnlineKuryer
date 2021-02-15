package kuryer;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
//import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Ekran extends JFrame implements ActionListener { 
	JButton tesdiqle = new JButton();	
	JTextField ad ;
	
	JTextField sifaris; 
	JTextField kart;
	JTextField cvv;
	
	String path = "jdbc:ucanaccess://VB.accdb";
	Ekran(){
		ad =  new JTextField();
		sifaris =  new JTextField();
		kart =  new JTextField();
		cvv =  new JTextField();
		
		tesdiqle.setText("Sifarişi Təsdiqlə");
		tesdiqle.setBounds(150,500,300,40);
		tesdiqle.addActionListener(this);
		
		JLabel adYazi = new JLabel();
		adYazi.setText("Tam Adınızı Qeyd Edin: ");
		adYazi.setBounds(5,0,300,100);
		adYazi.setFont(new Font("Arial",Font.BOLD,15));
		adYazi.setForeground(Color.darkGray);
		
		
		ad.setBounds(200,35,300,30);
		
		sifaris.setBounds(200,90,300,30);
		
		JLabel sifarisYazi = new JLabel();
		sifarisYazi.setText("Sifarişinizi Qeyd Edin: ");
		sifarisYazi.setBounds(5,60,300,100);
		sifarisYazi.setFont(new Font("Arial",Font.BOLD,15));
		sifarisYazi.setForeground(Color.darkGray);
		
		
		kart.setBounds(200,150,300,30);
		
		JLabel kartYazi = new JLabel();
		kartYazi.setText("Pan Kodu Qeyd Edin: ");
		kartYazi.setBounds(5,120,300,100);
		kartYazi.setFont(new Font("Arial",Font.BOLD,15));
		kartYazi.setForeground(Color.darkGray);
		
		cvv.setBounds(200,210,300,30);
		
		JLabel cvvYazi = new JLabel();
		cvvYazi.setText("CVV Qeyd Edin: ");
		cvvYazi.setBounds(5,170,300,100);
		cvvYazi.setFont(new Font("Arial",Font.BOLD,15));
		cvvYazi.setForeground(Color.darkGray);
		
		
		
		this.setVisible(true);
		this.setSize(600,600);
		this.setResizable(false);
		this.setLayout(null);
		this.add(ad);
		this.add(adYazi);
		this.add(sifaris);
		this.add(sifarisYazi);
		this.add(kart);
		this.add(kartYazi);
		this.add(cvv);
		this.add(cvvYazi);
		this.add(tesdiqle);
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==tesdiqle) {
			System.out.println(ad.getText() + sifaris.getText() + kart.getText() + cvv.getText()+"ds");
			
			try {
				Connection conn = DriverManager.getConnection(path);

				String sql = "INSERT INTO kuryer(tam_ad,sifaris,pan,cvv) VALUES(?,?,?,?)";
				String re = "SELECT * FROM kuryer";
				PreparedStatement state = conn.prepareStatement(sql);
				Statement st = conn.createStatement();
				state.setString(1,ad.getText());
				state.setString(2, sifaris.getText());
				state.setString(3,kart.getText());
				state.setString(4, cvv.getText());
				//DbUtils
				ResultSet rs = st.executeQuery(re);
				int rows = state.executeUpdate();
				System.out.println("Connected");
				if(rows > 0) {
					while(rs.next()) {
						String name = rs.getString("tam_ad");
						String order = rs.getString("sifaris");
						String card = rs.getString("pan");
						String cv = rs.getString("cvv");
						
						System.out.println("Inserted");
						
					}
				}
				state.close();					
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
}
