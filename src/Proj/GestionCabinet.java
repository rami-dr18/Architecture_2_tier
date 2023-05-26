package Proj;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestionCabinet {
    private static final String DOCTOR_USERNAME = "doctor";
    private static final String DOCTOR_PASSWORD = "doctor123";
    private static final String SECRETARY_USERNAME = "secretary";
    private static final String SECRETARY_PASSWORD = "secretary123";

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setSize(300, 200);
        frame.setLayout(null);
        ImageIcon icon = new ImageIcon(GestionCabinet.class.getResource("login.png"));
        frame.setIconImage(icon.getImage());
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        frame.add(userLabel);
        
        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        frame.add(userText);
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        frame.add(passwordLabel);
        
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        frame.add(passwordText);
        
        JCheckBox doctorCheckBox = new JCheckBox("Doctor");
        doctorCheckBox.setBounds(10, 80, 80, 25);
        frame.add(doctorCheckBox);
        
        JCheckBox secretaryCheckBox = new JCheckBox("Secretary");
        secretaryCheckBox.setBounds(100, 80, 80, 25);
        frame.add(secretaryCheckBox);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100, 110, 80, 25);
        frame.add(loginButton);
        
        JLabel statusLabel = new JLabel("");
        statusLabel.setBounds(10, 140, 300, 25);
        frame.add(statusLabel);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());
                boolean isDoctor = doctorCheckBox.isSelected();
                boolean isSecretary = secretaryCheckBox.isSelected();

                if (isDoctor && !isSecretary && username.equals(DOCTOR_USERNAME) && password.equals(DOCTOR_PASSWORD)) {
                	frame.setVisible(false);
            		JFrame jf = new JFrame("Gestion d'un cabinet Médicale");
            		ImageIcon img = new ImageIcon(GestionCabinet.class.getResource("clinic.png"));
            		jf.setIconImage(img.getImage());
            		jf.setBounds(200, 100, 800, 600);
            		jf.setSize(715, 400);
            		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            		jf.setResizable(false);
            		JPanel panel=new JPanel();
            		panel.setLayout(null);
            		jf.setContentPane(panel);
            		JMenuBar barreDeMenu = new JMenuBar();
            		barreDeMenu.setPreferredSize(new Dimension(200,200));
            		panel.add(barreDeMenu);
            		barreDeMenu.setBounds(0, 0, 700, 30);
            		JMenu menu1 = new JMenu("Gestion des patients");
            		menu1.setPreferredSize(new Dimension(200,200));
            		JMenu menu2 = new JMenu("Gestion des RDV");
            		menu2.setPreferredSize(new Dimension(200,200));
            		JMenu menu3 = new JMenu("Gestion des Ordonnances");
            		menu3.setPreferredSize(new Dimension(200,200));
            		JMenu menu4 = new JMenu("Quitter");
            		menu4.setPreferredSize(new Dimension(200,200));
            		JMenuItem item1 = new JMenuItem("Afficher Patient");
            		menu1.setIcon(new ImageIcon(GestionCabinet.class.getResource("gestpat.png")));
            		JMenuItem item2 = new JMenuItem("Ajouter Patient");
            		menu2.setIcon(new ImageIcon(GestionCabinet.class.getResource("gestrend.png")));
            		JMenuItem item3 = new JMenuItem("Modifier Patient");
            		menu3.setIcon(new ImageIcon(GestionCabinet.class.getResource("gestord.png")));
            		JMenuItem item4 = new JMenuItem("Supprimer Patient");
            		menu4.setIcon(new ImageIcon(GestionCabinet.class.getResource("exit.png")));
            		
            		JMenuItem item5 = new JMenuItem("Afficher RDV");
            		JMenuItem item6 = new JMenuItem("Ajouter RDV");
            		JMenuItem item7 = new JMenuItem("Modifier RDV");
            		JMenuItem item8 = new JMenuItem("Supprimer RDV");
            		
            		JMenuItem item9 = new JMenuItem("Afficher Ordonnances");
            		JMenuItem item10 = new JMenuItem("Ajouter Ordonnances");
            		JMenuItem item11 = new JMenuItem("Modifier Ordonnances");
            		JMenuItem item12 = new JMenuItem("Supprimer Ordonnances");
            		
            		JMenuItem item13 = new JMenuItem("Quitter gestion");
            		menu1.add(item1);
            		menu1.add(item2);
            		menu1.add(item3);
            		menu1.add(item4);
            		
            		menu2.add(item5);
            		menu2.add(item6);
            		menu2.add(item7);
            		menu2.add(item8);
            		
            		menu3.add(item9);
            		menu3.add(item10);
            		menu3.add(item11);
            		menu3.add(item12);
            		
            		menu4.add(item13);
            		barreDeMenu.add(menu1);
            		barreDeMenu.add(menu2);
            		barreDeMenu.add(menu3);
            		barreDeMenu.add(menu4);
            		item1.addActionListener(new ActionListener() {
            			public void actionPerformed(ActionEvent e) {
            			String columns[] = { "CIN", "Nom", "Prénom" };
            			int i = 0;
            			try
            			{
            			
            			Class.forName("oracle.jdbc.driver.OracleDriver");
            			
            			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            			
            			Statement stmt = con.createStatement();
            			
            			ResultSet res1 = stmt.executeQuery("SELECT COUNT(1) as NumberOfRows FROM patient");
            			res1.next();
            			int nbligne=res1.getInt("NumberOfRows");
            			String data[][] = new String[nbligne][3];
            			ResultSet res = stmt.executeQuery("select * from patient");
            			while(res.next())
            			{
            			int CIN=res.getInt(1);
            			String nom=res.getString(2);
            			String prénom=res.getString(3);
            			data[i][0] = CIN + "";
            			data[i][1] = nom;
            			data[i][2] = prénom;
            			i++;
            			}
            			DefaultTableModel model = new DefaultTableModel(data, columns);
            			JTable table = new JTable(model);
            			table.setShowGrid(true);
            			table.setShowVerticalLines(true);
            			JScrollPane pane = new JScrollPane(table);
            			JFrame f = new JFrame("Liste Des Patients");
            			JPanel panel = new JPanel();
            			ImageIcon img = new ImageIcon(GestionCabinet.class.getResource("list.png"));
            			f.setIconImage(img.getImage());
            			panel.add(pane);
            			f.add(panel);
            			f.setSize(500, 250);
            			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            			f.setVisible(true);
            			//étape 5: fermez l'objet de connexion
            			con.close();
            			}
            			catch(Exception e1){

            			System.out.println(e1);
            			}

            			}
            			});





            item2.addActionListener(new ActionListener() {
            								public void actionPerformed(ActionEvent e) {
            									JFrame f = new JFrame("Ajouter patient");
            									f.setSize(300, 400);
            									f.setVisible(true);
            									f.setResizable(false);
            									ImageIcon img = new ImageIcon(GestionCabinet.class.getResource("ajoutpat.png"));
            									f.setIconImage(img.getImage());
            									Dimension d = new Dimension(100, 35);
            									Font font = new Font("Serif", Font.PLAIN, 20);
            									Font font1 = new Font("Serif", Font.PLAIN, 16);
            									FlowLayout fl1 = new FlowLayout(FlowLayout.LEFT);

            									JButton button = new JButton("submit");
            									 button.setBackground(Color.LIGHT_GRAY);
            								     button.setForeground(Color.white);
            								     button.setFont(new Font("Arial", Font.BOLD, 20));
            								

            									JTextField cin = new JTextField(10);
            									cin.setFont(font1);
            									cin.setBackground(new Color (255, 240, 240) );
            									JTextField nom = new JTextField(10);
            									nom.setFont(font1);
            									nom.setBackground(new Color (255, 240, 240) );
            									JTextField prenom = new JTextField(10);
            									prenom.setFont(font1);
            									prenom.setBackground(new Color (255, 240, 240) );
            									JTextField age = new JTextField(10);
            									age.setFont(font1);
            									age.setBackground(new Color (255, 240, 240) );
            									JTextField adresse = new JTextField(10);
            									adresse.setFont(font1);
            									adresse.setBackground(new Color (255, 240, 240) );
            									JTextField tel = new JTextField(10);
            									tel.setFont(font1);
            									tel.setBackground(new Color (255, 240, 240) );

            									JLabel lblcin = new JLabel("CIN:");
            									lblcin.setFont(font);
            									lblcin.setLabelFor(cin);
            									lblcin.setPreferredSize(d);

            									JLabel lblnom = new JLabel("NOM:");
            									lblnom.setFont(font);
            									lblnom.setLabelFor(nom);
            									lblnom.setPreferredSize(d);

            									JLabel lblprenom = new JLabel("PRENOM:");
            									lblprenom.setFont(font);
            									lblprenom.setLabelFor(prenom);
            									lblprenom.setPreferredSize(d);

            									JLabel lblage = new JLabel("AGE");
            									lblage.setFont(font);
            									lblage.setLabelFor(age);
            									lblage.setPreferredSize(d);

            									JLabel lbladresse = new JLabel("ADRESSE:");
            									lbladresse.setFont(font);
            									lbladresse.setLabelFor(adresse);
            									lbladresse.setPreferredSize(d);

            									JLabel lbltel = new JLabel("TEL:");
            									lbltel.setFont(font);
            									lbltel.setLabelFor(tel);
            									lbltel.setPreferredSize(d);

            									JPanel p0 = new JPanel(fl1);

            									p0.add(lblcin);
            									p0.add(cin);
            									p0.setBackground(new Color(255, 204, 204));

            									JPanel p1 = new JPanel(fl1);

            									p1.add(lblnom);
            									p1.add(nom);
            									p1.setBackground(new Color(255, 204, 204));

            									JPanel p2 = new JPanel(fl1);

            									p2.add(lblprenom);
            									p2.add(prenom);
            									p2.setBackground(new Color(255, 204, 204));

            									JPanel p3 = new JPanel(fl1);

            									p3.add(lblage);
            									p3.add(age);
            									p3.setBackground(new Color(255, 204, 204));

            									JPanel p4 = new JPanel(fl1);

            									p4.add(lbladresse);
            									p4.add(adresse);
            									p4.setBackground(new Color(255, 204, 204));

            									JPanel p5 = new JPanel(fl1);

            									p5.add(lbltel);
            									p5.add(tel);
            									p5.setBackground(new Color(255, 204, 204));

            									JPanel p6 = new JPanel(fl1);

            									p6.add(button);
            									p6.setBackground(new Color(255, 204, 204));

            									JPanel p = new JPanel(fl1);

            									BoxLayout b = new BoxLayout(p, BoxLayout.Y_AXIS);

            									p.setLayout(b);

            									p.add(p0);
            									p.add(p1);
            									p.add(p2);
            									p.add(p3);
            									p.add(p4);
            									p.add(p5);
            									p.add(p6);

            									JPanel pf = new JPanel(fl1);
            									pf.add(p);

            									pf.setSize(300, 400);
            									pf.setBackground(new Color(255, 204, 204));
            									f.setContentPane(pf);
            									 button.addActionListener(new ActionListener() {
            										public void actionPerformed(ActionEvent e) {
            											String n1 = cin.getText();
            											String n2 = nom.getText();
            											String n3 = prenom.getText();
            											String n4 = age.getText();
            											String n5 = adresse.getText();
            											String n6 = tel.getText();
            											try {
            												Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            												Statement stmt = c.createStatement();
            												stmt.executeUpdate("insert into patient values (" + n1 + ",'" + n2 + "','" + n3 + "'," + n4
            														+ ",'" + n5 + "','" + n6 + "')");
            												f.setVisible(false);
            												JOptionPane.showMessageDialog(null, "patient  ajouté");
            												
            											} catch (Exception e1) {
            												System.out.println(e1);
            												f.setVisible(false);
            												JOptionPane.showMessageDialog(null, "patient non ajouté");
            											}
            										}
            									});

            								}
            							});

            item3.addActionListener(new ActionListener() {
            								public void actionPerformed(ActionEvent e) {
            									JFrame f = new JFrame("Modifier patient");
            									f.setSize(300, 400);
            									f.setVisible(true);
            									f.setResizable(false);
            									ImageIcon img = new ImageIcon(GestionCabinet.class.getResource("modifpat.png"));
            									f.setIconImage(img.getImage());
            									Dimension d = new Dimension(100, 35);
            									Font font = new Font("Serif", Font.PLAIN, 20);
            									Font font1 = new Font("Serif", Font.PLAIN, 16);
            									FlowLayout fl1 = new FlowLayout(FlowLayout.LEFT);
            									
            									JButton button = new JButton("submit");
            									 button.setBackground(Color.LIGHT_GRAY);
            								     button.setForeground(Color.white);
            								     button.setFont(new Font("Arial", Font.BOLD, 20));
            								     JComboBox<String> box = new JComboBox<String>();
            								     

            									
            									JTextField nom = new JTextField(10);
            									nom.setFont(font1);
            									nom.setBackground(new Color (255, 240, 240) );
            									JTextField prenom = new JTextField(10);
            									prenom.setFont(font1);
            									prenom.setBackground(new Color (255, 240, 240) );
            									JTextField age = new JTextField(10);
            									age.setFont(font1);
            									age.setBackground(new Color (255, 240, 240) );
            									JTextField adresse = new JTextField(10);
            									adresse.setFont(font1);
            									adresse.setBackground(new Color (255, 240, 240) );
            									JTextField tel = new JTextField(10);
            									tel.setFont(font1);
            									tel.setBackground(new Color (255, 240, 240) );
            									JLabel lblnom = new JLabel("NOM:");
            									lblnom.setFont(font);
            									lblnom.setLabelFor(nom);
            									lblnom.setPreferredSize(d);

            									JLabel lblprenom = new JLabel("PRENOM:");
            									lblprenom.setFont(font);
            									lblprenom.setLabelFor(prenom);
            									lblprenom.setPreferredSize(d);

            									JLabel lblage = new JLabel("AGE");
            									lblage.setFont(font);
            									lblage.setLabelFor(age);
            									lblage.setPreferredSize(d);

            									JLabel lbladresse = new JLabel("ADRESSE:");
            									lbladresse.setFont(font);
            									lbladresse.setLabelFor(adresse);
            									lbladresse.setPreferredSize(d);

            									JLabel lbltel = new JLabel("TEL:");
            									lbltel.setFont(font);
            									lbltel.setLabelFor(tel);
            									lbltel.setPreferredSize(d);

            									JPanel p0 = new JPanel(fl1);

            									p0.setBackground(new Color(255, 204, 204));

            									JPanel p1 = new JPanel(fl1);

            									p1.add(lblnom);
            									p1.add(nom);
            									p1.setBackground(new Color(255, 204, 204));

            									JPanel p2 = new JPanel(fl1);

            									p2.add(lblprenom);
            									p2.add(prenom);
            									p2.setBackground(new Color(255, 204, 204));

            									JPanel p3 = new JPanel(fl1);

            									p3.add(lblage);
            									p3.add(age);
            									p3.setBackground(new Color(255, 204, 204));

            									JPanel p4 = new JPanel(fl1);

            									p4.add(lbladresse);
            									p4.add(adresse);
            									p4.setBackground(new Color(255, 204, 204));

            									JPanel p5 = new JPanel(fl1);

            									p5.add(lbltel);
            									p5.add(tel);
            									p5.setBackground(new Color(255, 204, 204));

            									JPanel p6 = new JPanel(fl1);

            									p6.add(button);
            									p6.setBackground(new Color(255, 204, 204));

            									JPanel p = new JPanel(fl1);
            									Connection c;
            									try {
            										c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            										Statement stmt = c.createStatement();
            										String sql1="select CIN from PATIENT";
            										ResultSet rs= stmt.executeQuery(sql1);
            										
            										String sql2="select NOM, PRENOM ,AGE ,ADRESSE ,TEL from patient where CIN =? ";
            										while(rs.next()) {
            											String cin1=rs.getString("CIN");
            											box.addItem(cin1);
            											
            										}
            										  box.addItemListener(new ItemListener() {
            										        @Override
            										        public void itemStateChanged(ItemEvent e) {
            										            String tmp = (String)box.getSelectedItem();
            										            try {
            										                PreparedStatement pst = c.prepareStatement(sql2);
            										                pst.setString(1, tmp);
            										                ResultSet rs1 = pst.executeQuery();
            										                if(rs1.next()){
            										                    String nom1=rs1.getString("NOM");
            										                    nom.setText(nom1);
            										                    String prenom1=rs1.getString("PRENOM");
            										                    prenom.setText(prenom1);
            										                    String age1=rs1.getString("AGE");
            										                    age.setText(age1);
            										                    String adresse1=rs1.getString("ADRESSE");
            										                    adresse.setText(adresse1);
            										                    String tel1=rs1.getString("TEL");
            										                    tel.setText(tel1);
            										                }
            										            } catch (SQLException ex) {
            										                ex.printStackTrace();
            										            }
            										        }

            												
            										    });
            										
            									} catch (SQLException e2) {
            										e2.printStackTrace();
            									}
            									
            									BoxLayout b = new BoxLayout(p, BoxLayout.Y_AXIS);

            									p.setLayout(b);
            									p.add(box);
            									p.add(p0);
            									p.add(p1);
            									p.add(p2);
            									p.add(p3);
            									p.add(p4);
            									p.add(p5);
            									p.add(p6);

            									JPanel pf = new JPanel(fl1);
            									pf.add(p);
            									

            									pf.setSize(300, 400);
            									pf.setBackground(new Color(255, 204, 204));
            									f.setContentPane(pf);
            									 button.addActionListener(new ActionListener() {
            										public void actionPerformed(ActionEvent e) {
            											String n1 = (String) box.getSelectedItem();
            											String n2 = nom.getText();
            											String n3 = prenom.getText();
            											String n4 = age.getText();
            											String n5 = adresse.getText();
            											String n6 = tel.getText();
            											try {
            												Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            												
            												PreparedStatement stmt = c.prepareStatement("update patient set NOM = ?, PRENOM=? ,AGE = ?,ADRESSE =?,TEL= ? where CIN=?");
            												stmt.setString(1, n2);
            												stmt.setString(2, n3);
            												stmt.setString(3, n4);
            												stmt.setString(4, n5);
            												stmt.setString(5, n6);
            												stmt.setString(6, n1);
            												int nb = stmt.executeUpdate();
            												
            												f.setVisible(false);
            												JOptionPane.showMessageDialog(null, "patient  modifié");
            												
            											} catch (Exception e1) {
            												System.out.println(e1);
            												f.setVisible(false);
            												JOptionPane.showMessageDialog(null, "patient n\'est pas modifié");
            											}
            										}
            									});

            								}
            							});
            item4.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		JFrame f = new JFrame("Supprimer patient");
            		f.setSize(300, 400);
            		f.setVisible(true);
            		f.setResizable(false);
            		ImageIcon img = new ImageIcon(GestionCabinet.class.getResource("delete.png"));
            		f.setIconImage(img.getImage());
            		Dimension d = new Dimension(100, 35);
            		Font font = new Font("Serif", Font.PLAIN, 20);
            		Font font1 = new Font("Serif", Font.PLAIN, 16);
            		FlowLayout fl1 = new FlowLayout(FlowLayout.LEFT);
            		
            		JButton button = new JButton("submit");
            		 button.setBackground(Color.LIGHT_GRAY);
            	     button.setForeground(Color.white);
            	     button.setFont(new Font("Arial", Font.BOLD, 20));
            	     JComboBox<String> box = new JComboBox<String>();
            	     

            		
            		JTextField nom = new JTextField(10);
            		nom.setFont(font1);
            		nom.setBackground(new Color (255, 240, 240) );
            		nom.setEditable(false);
            		JTextField prenom = new JTextField(10);
            		prenom.setFont(font1);
            		prenom.setBackground(new Color (255, 240, 240) );
            		prenom.setEditable(false);
            		JTextField age = new JTextField(10);
            		age.setFont(font1);
            		age.setBackground(new Color (255, 240, 240) );
            		age.setEditable(false);
            		JTextField adresse = new JTextField(10);
            		adresse.setFont(font1);
            		adresse.setBackground(new Color (255, 240, 240) );
            		adresse.setEditable(false);
            		JTextField tel = new JTextField(10);
            		tel.setFont(font1);
            		tel.setBackground(new Color (255, 240, 240) );
            		tel.setEditable(false);
            		JLabel lblnom = new JLabel("NOM:");
            		lblnom.setFont(font);
            		lblnom.setLabelFor(nom);
            		lblnom.setPreferredSize(d);

            		JLabel lblprenom = new JLabel("PRENOM:");
            		lblprenom.setFont(font);
            		lblprenom.setLabelFor(prenom);
            		lblprenom.setPreferredSize(d);

            		JLabel lblage = new JLabel("AGE");
            		lblage.setFont(font);
            		lblage.setLabelFor(age);
            		lblage.setPreferredSize(d);

            		JLabel lbladresse = new JLabel("ADRESSE:");
            		lbladresse.setFont(font);
            		lbladresse.setLabelFor(adresse);
            		lbladresse.setPreferredSize(d);

            		JLabel lbltel = new JLabel("TEL:");
            		lbltel.setFont(font);
            		lbltel.setLabelFor(tel);
            		lbltel.setPreferredSize(d);

            		JPanel p0 = new JPanel(fl1);

            		p0.setBackground(new Color(255, 204, 204));

            		JPanel p1 = new JPanel(fl1);

            		p1.add(lblnom);
            		p1.add(nom);
            		p1.setBackground(new Color(255, 204, 204));

            		JPanel p2 = new JPanel(fl1);

            		p2.add(lblprenom);
            		p2.add(prenom);
            		p2.setBackground(new Color(255, 204, 204));

            		JPanel p3 = new JPanel(fl1);

            		p3.add(lblage);
            		p3.add(age);
            		p3.setBackground(new Color(255, 204, 204));

            		JPanel p4 = new JPanel(fl1);

            		p4.add(lbladresse);
            		p4.add(adresse);
            		p4.setBackground(new Color(255, 204, 204));

            		JPanel p5 = new JPanel(fl1);

            		p5.add(lbltel);
            		p5.add(tel);
            		p5.setBackground(new Color(255, 204, 204));

            		JPanel p6 = new JPanel(fl1);

            		p6.add(button);
            		p6.setBackground(new Color(255, 204, 204));

            		JPanel p = new JPanel(fl1);
            		Connection c;
            		try {
            			c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            			Statement stmt = c.createStatement();
            			String sql1="select CIN from PATIENT";
            			ResultSet rs= stmt.executeQuery(sql1);
            			
            			String sql2="select NOM, PRENOM ,AGE ,ADRESSE ,TEL from patient where CIN =? ";
            			while(rs.next()) {
            				String cin1=rs.getString("CIN");
            				box.addItem(cin1);
            			}
            			
            			  box.addItemListener(new ItemListener() {
            			        @Override
            			        public void itemStateChanged(ItemEvent e) {
            			        	
            			            String tmp = (String)box.getSelectedItem();
            			            try {
            			                PreparedStatement pst = c.prepareStatement(sql2);
            			                pst.setString(1, tmp);
            			                ResultSet rs1 = pst.executeQuery();
            			                if(rs1.next()){
            			                    String nom1=rs1.getString("NOM");
            			                    nom.setText(nom1);
            			                   
            			                    String prenom1=rs1.getString("PRENOM");
            			                    prenom.setText(prenom1);
            			                    
            			                    String age1=rs1.getString("AGE");
            			                    age.setText(age1);
            			                   
            			                    String adresse1=rs1.getString("ADRESSE");
            			                    adresse.setText(adresse1);
            			                    
            			                    String tel1=rs1.getString("TEL");
            			                    tel.setText(tel1);
            			                
            			                }
            			                
            			               
            			            } catch (SQLException ex) {
            			                ex.printStackTrace();
            			            }
            			        }


            					
            			    });
            			
            		} catch (SQLException e2) {
            			e2.printStackTrace();
            		}
            		
            		BoxLayout b = new BoxLayout(p, BoxLayout.Y_AXIS);

            		p.setLayout(b);
            		p.add(box);
            		p.add(p0);
            		p.add(p1);
            		p.add(p2);
            		p.add(p3);
            		p.add(p4);
            		p.add(p5);
            		p.add(p6);

            		JPanel pf = new JPanel(fl1);
            		pf.add(p);
            		

            		pf.setSize(300, 400);
            		pf.setBackground(new Color(255, 204, 204));
            		f.setContentPane(pf);
            		 button.addActionListener(new ActionListener() {
            			public void actionPerformed(ActionEvent e) {
            				String n1 = (String) box.getSelectedItem();
            				try {
            					Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            					
            					PreparedStatement stmt = c.prepareStatement("delete from patient where CIN=?");
            					stmt.setString(1, n1);
            					int nb = stmt.executeUpdate();
            					
            					f.setVisible(false);
            					JOptionPane.showMessageDialog(null, "patient  supprime");
            					
            				} catch (Exception e1) {
            					System.out.println(e1);
            					f.setVisible(false);
            					JOptionPane.showMessageDialog(null, "patient n\'est pas supprime");
            				}
            			}
            		});

            	}
            });
            item5.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            	String columns[] = { "ID", "DATERDV", "CINP" };
            	int i = 0;
            	try
            	{
            	//étape 1: charger la classe de driver
            	Class.forName("oracle.jdbc.driver.OracleDriver");
            	//étape 2: créer l'objet de connexion
            	Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            	//étape 3: créer l'objet statement
            	Statement stmt = con.createStatement();
            	//étape 4: exécuter la requête
            	ResultSet res1 = stmt.executeQuery("SELECT COUNT(1) as NumberOfRows FROM RDV");
            	res1.next();
            	int nbligne=res1.getInt("NumberOfRows");
            	String data[][] = new String[nbligne][3];
            	ResultSet res = stmt.executeQuery("select * from RDV");
            	while(res.next())
            	{
            	String ID=res.getString(1);
            	String DATERDV=res.getString(2);
            	String CINP=res.getString(3);
            	data[i][0] = ID + "";
            	data[i][1] = DATERDV;
            	data[i][2] = CINP;
            	i++;
            	}
            	DefaultTableModel model = new DefaultTableModel(data, columns);
            	JTable table = new JTable(model);
            	table.setShowGrid(true);
            	table.setShowVerticalLines(true);
            	JScrollPane pane = new JScrollPane(table);
            	JFrame f = new JFrame("Liste Des RDVS");
            	JPanel panel = new JPanel();
            	ImageIcon img = new ImageIcon(GestionCabinet.class.getResource("list.png"));
            	f.setIconImage(img.getImage());
            	panel.add(pane);
            	f.add(panel);
            	f.setSize(500, 250);
            	f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            	f.setVisible(true);
            	//étape 5: fermez l'objet de connexion
            	con.close();
            	}
            	catch(Exception e1){

            	System.out.println(e1);
            	}

            	}
            	});

            item6.addActionListener(new ActionListener() {
            								public void actionPerformed(ActionEvent e) {
            									JFrame f = new JFrame("Ajouter RDV");
            									f.setSize(300, 400);
            									f.setVisible(true);
            									f.setResizable(false);
            									ImageIcon img = new ImageIcon(GestionCabinet.class.getResource("ajoutdate.png"));
            									f.setIconImage(img.getImage());
            									Dimension d = new Dimension(100, 35);
            									Font font = new Font("Serif", Font.PLAIN, 20);
            									Font font1 = new Font("Serif", Font.PLAIN, 16);
            									FlowLayout fl1 = new FlowLayout(FlowLayout.LEFT);

            									JButton button = new JButton("submit");
            									 button.setBackground(Color.LIGHT_GRAY);
            								     button.setForeground(Color.white);
            								     button.setFont(new Font("Arial", Font.BOLD, 20));
            								

            									JTextField id = new JTextField(10);
            									id.setFont(font1);
            									id.setBackground(new Color (255, 240, 240) );
            									JTextField daterdv = new JTextField(10);
            									daterdv.setFont(font1);
            									daterdv.setBackground(new Color (255, 240, 240) );
            									JTextField cinp = new JTextField(10);
            									cinp.setFont(font1);
            									cinp.setBackground(new Color (255, 240, 240) );
            									
            									JLabel lblcin = new JLabel("ID:");
            									lblcin.setFont(font);
            									lblcin.setLabelFor(id);
            									lblcin.setPreferredSize(d);

            									JLabel lblnom = new JLabel("DATERDV:");
            									lblnom.setFont(font);
            									lblnom.setLabelFor(daterdv);
            									lblnom.setPreferredSize(d);

            									JLabel lblprenom = new JLabel("CINP:");
            									lblprenom.setFont(font);
            									lblprenom.setLabelFor(cinp);
            									lblprenom.setPreferredSize(d);


            									JPanel p0 = new JPanel(fl1);

            									p0.add(lblcin);
            									p0.add(id);
            									p0.setBackground(new Color(255, 204, 204));

            									JPanel p1 = new JPanel(fl1);

            									p1.add(lblnom);
            									p1.add(daterdv);
            									p1.setBackground(new Color(255, 204, 204));

            									JPanel p2 = new JPanel(fl1);

            									p2.add(lblprenom);
            									p2.add(cinp);
            									p2.setBackground(new Color(255, 204, 204));

            									JPanel p6 = new JPanel(fl1);

            									p6.add(button);
            									p6.setBackground(new Color(255, 204, 204));

            									JPanel p = new JPanel(fl1);

            									BoxLayout b = new BoxLayout(p, BoxLayout.Y_AXIS);

            									p.setLayout(b);

            									p.add(p0);
            									p.add(p1);
            									p.add(p2);
            									p.add(p6);

            									JPanel pf = new JPanel(fl1);
            									pf.add(p);

            									pf.setSize(300, 400);
            									pf.setBackground(new Color(255, 204, 204));
            									f.setContentPane(pf);
            									 button.addActionListener(new ActionListener() {
            										public void actionPerformed(ActionEvent e) {
            											String n1 = id.getText();
            											String n2 = daterdv.getText();
            											String n3 = cinp.getText();
            					
            											try {
            												Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            												Statement stmt = c.createStatement();
            												stmt.executeUpdate("insert into RDV values (" + n1 + ",'" + n2 + "','" + n3 + "')");
            												f.setVisible(false);
            												JOptionPane.showMessageDialog(null, "RDV  ajouté");
            												
            											} catch (Exception e1) {
            												System.out.println(e1);
            												f.setVisible(false);
            												JOptionPane.showMessageDialog(null, "RDV non ajouté");
            											}
            										}
            									});

            								}
            							});

            item7.addActionListener(new ActionListener() {
            								public void actionPerformed(ActionEvent e) {
            									JFrame f = new JFrame("Modifier RDV");
            									f.setSize(300, 400);
            									f.setVisible(true);
            									f.setResizable(false);
            									ImageIcon img = new ImageIcon(GestionCabinet.class.getResource("modifpat.png"));
            									f.setIconImage(img.getImage());
            									Dimension d = new Dimension(100, 35);
            									Font font = new Font("Serif", Font.PLAIN, 20);
            									Font font1 = new Font("Serif", Font.PLAIN, 16);
            									FlowLayout fl1 = new FlowLayout(FlowLayout.LEFT);
            									
            									JButton button = new JButton("submit");
            									 button.setBackground(Color.LIGHT_GRAY);
            								     button.setForeground(Color.white);
            								     button.setFont(new Font("Arial", Font.BOLD, 20));
            								     JComboBox<String> box = new JComboBox<String>();
            								     

            									
            									JTextField daterdv = new JTextField(10);
            									daterdv.setFont(font1);
            									daterdv.setBackground(new Color (255, 240, 240) );
            									JTextField cinp = new JTextField(10);
            									cinp.setFont(font1);
            									cinp.setBackground(new Color (255, 240, 240) );
            									
            									JLabel lblnom = new JLabel("DATERDV:");
            									lblnom.setFont(font);
            									lblnom.setLabelFor(daterdv);
            									lblnom.setPreferredSize(d);

            									JLabel lblprenom = new JLabel("CINP:");
            									lblprenom.setFont(font);
            									lblprenom.setLabelFor(cinp);
            									lblprenom.setPreferredSize(d);


            									JPanel p0 = new JPanel(fl1);

            									p0.setBackground(new Color(255, 204, 204));

            									JPanel p1 = new JPanel(fl1);

            									p1.add(lblnom);
            									p1.add(daterdv);
            									p1.setBackground(new Color(255, 204, 204));

            									JPanel p2 = new JPanel(fl1);

            									p2.add(lblprenom);
            									p2.add(cinp);
            									p2.setBackground(new Color(255, 204, 204));

            							
            									JPanel p6 = new JPanel(fl1);

            									p6.add(button);
            									p6.setBackground(new Color(255, 204, 204));

            									JPanel p = new JPanel(fl1);
            									Connection c;
            									try {
            										c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            										Statement stmt = c.createStatement();
            										String sql1="select ID from RDV";
            										ResultSet rs= stmt.executeQuery(sql1);
            										
            										String sql2="select DATERDV, CINP from RDV where ID =? ";
            										while(rs.next()) {
            											String cin1=rs.getString("ID");
            											box.addItem(cin1);
            											
            										}
            										  box.addItemListener(new ItemListener() {
            										        @Override
            										        public void itemStateChanged(ItemEvent e) {
            										            String tmp = (String)box.getSelectedItem();
            										            try {
            										                PreparedStatement pst = c.prepareStatement(sql2);
            										                pst.setString(1, tmp);
            										                ResultSet rs1 = pst.executeQuery();
            										                if(rs1.next()){
            										                    String nom1=rs1.getString("DATERDV");
            										                    daterdv.setText(nom1);
            										                    String prenom1=rs1.getString("CINP");
            										                    cinp.setText(prenom1);
            										                    
            										                }
            										            } catch (SQLException ex) {
            										                ex.printStackTrace();
            										            }
            										        }

            												
            										    });
            										
            									} catch (SQLException e2) {
            										e2.printStackTrace();
            									}
            									
            									BoxLayout b = new BoxLayout(p, BoxLayout.Y_AXIS);

            									p.setLayout(b);
            									p.add(box);
            									p.add(p0);
            									p.add(p1);
            									p.add(p2);
            									
            									p.add(p6);

            									JPanel pf = new JPanel(fl1);
            									pf.add(p);
            									

            									pf.setSize(300, 400);
            									pf.setBackground(new Color(255, 204, 204));
            									f.setContentPane(pf);
            									 button.addActionListener(new ActionListener() {
            										public void actionPerformed(ActionEvent e) {
            											String n1 = (String) box.getSelectedItem();
            											String n2 = daterdv.getText();
            											String n3 = cinp.getText();
            										
            											try {
            												Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            												
            												PreparedStatement stmt = c.prepareStatement("update RDV set DATERDV = ?, CINP = ? where ID=?");
            												stmt.setString(1, n2);
            												stmt.setString(2, n3);
            											
            												stmt.setString(3, n1);
            												int nb = stmt.executeUpdate();
            												
            												f.setVisible(false);
            												JOptionPane.showMessageDialog(null, "RDV  modifié");
            												
            											} catch (Exception e1) {
            												System.out.println(e1);
            												f.setVisible(false);
            												JOptionPane.showMessageDialog(null, "RDV n\'est pas modifié");
            											}
            										}
            									});

            								}
            							});
            item8.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		JFrame f = new JFrame("Supprimer RDV");
            		f.setSize(300, 400);
            		f.setVisible(true);
            		f.setResizable(false);
            		ImageIcon img = new ImageIcon(GestionCabinet.class.getResource("delete.png"));
            		f.setIconImage(img.getImage());
            		Dimension d = new Dimension(100, 35);
            		Font font = new Font("Serif", Font.PLAIN, 20);
            		Font font1 = new Font("Serif", Font.PLAIN, 16);
            		FlowLayout fl1 = new FlowLayout(FlowLayout.LEFT);
            		
            		JButton button = new JButton("submit");
            		 button.setBackground(Color.LIGHT_GRAY);
            	     button.setForeground(Color.white);
            	     button.setFont(new Font("Arial", Font.BOLD, 20));
            	     JComboBox<String> box = new JComboBox<String>();
            	     

            		
            		JTextField daterdv = new JTextField(10);
            		daterdv.setFont(font1);
            		daterdv.setBackground(new Color (255, 240, 240) );
            		daterdv.setEditable(false);
            		JTextField cinp = new JTextField(10);
            		cinp.setFont(font1);
            		cinp.setBackground(new Color (255, 240, 240) );
            		cinp.setEditable(false);
            		
            		JLabel lblnom = new JLabel("DATERDV:");
            		lblnom.setFont(font);
            		lblnom.setLabelFor(daterdv);
            		lblnom.setPreferredSize(d);

            		JLabel lblprenom = new JLabel("CINP:");
            		lblprenom.setFont(font);
            		lblprenom.setLabelFor(cinp);
            		lblprenom.setPreferredSize(d);


            		JPanel p0 = new JPanel(fl1);

            		p0.setBackground(new Color(255, 204, 204));

            		JPanel p1 = new JPanel(fl1);

            		p1.add(lblnom);
            		p1.add(daterdv);
            		p1.setBackground(new Color(255, 204, 204));

            		JPanel p2 = new JPanel(fl1);

            		p2.add(lblprenom);
            		p2.add(cinp);
            		p2.setBackground(new Color(255, 204, 204));


            		JPanel p6 = new JPanel(fl1);

            		p6.add(button);
            		p6.setBackground(new Color(255, 204, 204));

            		JPanel p = new JPanel(fl1);
            		Connection c;
            		try {
            			c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            			Statement stmt = c.createStatement();
            			String sql1="select ID from RDV";
            			ResultSet rs= stmt.executeQuery(sql1);
            			
            			String sql2="select DATERDV, CINP from RDV where ID =? ";
            			while(rs.next()) {
            				String cin1=rs.getString("ID");
            				box.addItem(cin1);
            				
            			}
            			  box.addItemListener(new ItemListener() {
            			        @Override
            			        public void itemStateChanged(ItemEvent e) {
            			            String tmp = (String)box.getSelectedItem();
            			            try {
            			                PreparedStatement pst = c.prepareStatement(sql2);
            			                pst.setString(1, tmp);
            			                ResultSet rs1 = pst.executeQuery();
            			                if(rs1.next()){
            			                    String nom1=rs1.getString("DATERDV");
            			                    daterdv.setText(nom1);
            			                    String prenom1=rs1.getString("CINP");
            			                    cinp.setText(prenom1);
            			                    
            			                }
            			            } catch (SQLException ex) {
            			                ex.printStackTrace();
            			            }
            			        }

            					
            			    });
            			
            		} catch (SQLException e2) {
            			e2.printStackTrace();
            		}
            		
            		BoxLayout b = new BoxLayout(p, BoxLayout.Y_AXIS);

            		p.setLayout(b);
            		p.add(box);
            		p.add(p0);
            		p.add(p1);
            		p.add(p2);
            		
            		p.add(p6);

            		JPanel pf = new JPanel(fl1);
            		pf.add(p);
            		

            		pf.setSize(300, 400);
            		pf.setBackground(new Color(255, 204, 204));
            		f.setContentPane(pf);
            		 button.addActionListener(new ActionListener() {
            			public void actionPerformed(ActionEvent e) {
            				String n1 = (String) box.getSelectedItem();
            				
            			
            				try {
            					Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            					
            					PreparedStatement stmt = c.prepareStatement("delete from RDV where ID=?");
            					
            				
            					stmt.setString(1, n1);
            					int nb = stmt.executeUpdate();
            					
            					f.setVisible(false);
            					JOptionPane.showMessageDialog(null, "RDV  supprime");
            					
            				} catch (Exception e1) {
            					System.out.println(e1);
            					f.setVisible(false);
            					JOptionPane.showMessageDialog(null, "RDV n\'est pas supprime");
            				}
            			}
            		});

            	}
            });
            item9.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            	String columns[] = { "ID", "NOMP", "PRENOMP" };
            	int i = 0;
            	try
            	{
            	//étape 1: charger la classe de driver
            	Class.forName("oracle.jdbc.driver.OracleDriver");
            	//étape 2: créer l'objet de connexion
            	Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            	//étape 3: créer l'objet statement
            	Statement stmt = con.createStatement();
            	//étape 4: exécuter la requête
            	ResultSet res1 = stmt.executeQuery("SELECT COUNT(1) as NumberOfRows FROM ORDONNACE");
            	res1.next();
            	int nbligne=res1.getInt("NumberOfRows");
            	String data[][] = new String[nbligne][3];
            	ResultSet res = stmt.executeQuery("select * from ORDONNACE");
            	while(res.next())
            	{
            	String ID=res.getString(1);
            	String nomp=res.getString(2);
            	String prenomp=res.getString(3);
            	data[i][0] = ID + "";
            	data[i][1] = nomp;
            	data[i][2] = prenomp;
            	i++;
            	}
            	DefaultTableModel model = new DefaultTableModel(data, columns);
            	JTable table = new JTable(model);
            	table.setShowGrid(true);
            	table.setShowVerticalLines(true);
            	JScrollPane pane = new JScrollPane(table);
            	JFrame f = new JFrame("Liste Des Ordonnances");
            	JPanel panel = new JPanel();
            	ImageIcon img = new ImageIcon(GestionCabinet.class.getResource("list.png"));
            	f.setIconImage(img.getImage());
            	panel.add(pane);
            	f.add(panel);
            	f.setSize(500, 250);
            	f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            	f.setVisible(true);
            	//étape 5: fermez l'objet de connexion
            	con.close();
            	}
            	catch(Exception e1){

            	System.out.println(e1);
            	}

            	}
            	});

            item10.addActionListener(new ActionListener() {
            								public void actionPerformed(ActionEvent e) {
            									JFrame f = new JFrame("Ajouter Ordonnace");
            									f.setSize(300, 400);
            									f.setVisible(true);
            									f.setResizable(false);
            									ImageIcon img = new ImageIcon(GestionCabinet.class.getResource("ajoutpat.png"));
            									f.setIconImage(img.getImage());
            									Dimension d = new Dimension(100, 35);
            									Font font = new Font("Serif", Font.PLAIN, 20);
            									Font font1 = new Font("Serif", Font.PLAIN, 16);
            									FlowLayout fl1 = new FlowLayout(FlowLayout.LEFT);

            									JButton button = new JButton("submit");
            									 button.setBackground(Color.LIGHT_GRAY);
            								     button.setForeground(Color.white);
            								     button.setFont(new Font("Arial", Font.BOLD, 20));
            								

            									JTextField id = new JTextField(10);
            									id.setFont(font1);
            									id.setBackground(new Color (255, 240, 240) );
            									JTextField nomp = new JTextField(10);
            									nomp.setFont(font1);
            									nomp.setBackground(new Color (255, 240, 240) );
            									JTextField prenomp = new JTextField(10);
            									prenomp.setFont(font1);
            									prenomp.setBackground(new Color (255, 240, 240) );
            									JTextField nommedicament = new JTextField(10);
            									nommedicament.setFont(font1);
            									nommedicament.setBackground(new Color (255, 240, 240) );
            									JTextField dosagemed = new JTextField(10);
            									dosagemed.setFont(font1);
            									dosagemed.setBackground(new Color (255, 240, 240) );
            									JTextField cinp = new JTextField(10);
            									cinp.setFont(font1);
            									cinp.setBackground(new Color (255, 240, 240) );

            									JLabel lblcin = new JLabel("ID:");
            									lblcin.setFont(font);
            									lblcin.setLabelFor(id);
            									lblcin.setPreferredSize(d);

            									JLabel lblnom = new JLabel("NOMP:");
            									lblnom.setFont(font);
            									lblnom.setLabelFor(nomp);
            									lblnom.setPreferredSize(d);

            									JLabel lblprenom = new JLabel("PRENOMP:");
            									lblprenom.setFont(font);
            									lblprenom.setLabelFor(prenomp);
            									lblprenom.setPreferredSize(d);

            									JLabel lblage = new JLabel("NOMMED");
            									lblage.setFont(font);
            									lblage.setLabelFor(nommedicament);
            									lblage.setPreferredSize(d);

            									JLabel lbladresse = new JLabel("DOSAGE:");
            									lbladresse.setFont(font);
            									lbladresse.setLabelFor(dosagemed);
            									lbladresse.setPreferredSize(d);

            									JLabel lbltel = new JLabel("CINP:");
            									lbltel.setFont(font);
            									lbltel.setLabelFor(cinp);
            									lbltel.setPreferredSize(d);

            									JPanel p0 = new JPanel(fl1);

            									p0.add(lblcin);
            									p0.add(id);
            									p0.setBackground(new Color(255, 204, 204));

            									JPanel p1 = new JPanel(fl1);

            									p1.add(lblnom);
            									p1.add(nomp);
            									p1.setBackground(new Color(255, 204, 204));

            									JPanel p2 = new JPanel(fl1);

            									p2.add(lblprenom);
            									p2.add(prenomp);
            									p2.setBackground(new Color(255, 204, 204));

            									JPanel p3 = new JPanel(fl1);

            									p3.add(lblage);
            									p3.add(nommedicament);
            									p3.setBackground(new Color(255, 204, 204));

            									JPanel p4 = new JPanel(fl1);

            									p4.add(lbladresse);
            									p4.add(dosagemed);
            									p4.setBackground(new Color(255, 204, 204));

            									JPanel p5 = new JPanel(fl1);

            									p5.add(lbltel);
            									p5.add(cinp);
            									p5.setBackground(new Color(255, 204, 204));

            									JPanel p6 = new JPanel(fl1);

            									p6.add(button);
            									p6.setBackground(new Color(255, 204, 204));

            									JPanel p = new JPanel(fl1);

            									BoxLayout b = new BoxLayout(p, BoxLayout.Y_AXIS);

            									p.setLayout(b);

            									p.add(p0);
            									p.add(p1);
            									p.add(p2);
            									p.add(p3);
            									p.add(p4);
            									p.add(p5);
            									p.add(p6);

            									JPanel pf = new JPanel(fl1);
            									pf.add(p);

            									pf.setSize(300, 400);
            									pf.setBackground(new Color(255, 204, 204));
            									f.setContentPane(pf);
            									 button.addActionListener(new ActionListener() {
            										public void actionPerformed(ActionEvent e) {
            											String n1 = id.getText();
            											String n2 = nomp.getText();
            											String n3 = prenomp.getText();
            											String n4 = nommedicament.getText();
            											String n5 = dosagemed.getText();
            											String n6 = cinp.getText();
            											try {
            												Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            												Statement stmt = c.createStatement();
            												stmt.executeUpdate("insert into ORDONNACE values (" + n1 + ",'" + n2 + "','" + n3 + "','" + n4
            														+ "','" + n5 + "','" + n6 + "')");
            												f.setVisible(false);
            												JOptionPane.showMessageDialog(null, "ordonnance   ajouté");
            												
            											} catch (Exception e1) {
            												System.out.println(e1);
            												f.setVisible(false);
            												JOptionPane.showMessageDialog(null, "ordonnance non ajouté");
            											}
            										}
            									});

            								}
            							});

            item11.addActionListener(new ActionListener() {
            								public void actionPerformed(ActionEvent e) {
            									JFrame f = new JFrame("Modifier patient");
            									f.setSize(300, 400);
            									f.setVisible(true);
            									f.setResizable(false);
            									ImageIcon img = new ImageIcon(GestionCabinet.class.getResource("modifpat.png"));
            									f.setIconImage(img.getImage());
            									Dimension d = new Dimension(100, 35);
            									Font font = new Font("Serif", Font.PLAIN, 20);
            									Font font1 = new Font("Serif", Font.PLAIN, 16);
            									FlowLayout fl1 = new FlowLayout(FlowLayout.LEFT);
            									
            									JButton button = new JButton("submit");
            									 button.setBackground(Color.LIGHT_GRAY);
            								     button.setForeground(Color.white);
            								     button.setFont(new Font("Arial", Font.BOLD, 20));
            								     JComboBox<String> box = new JComboBox<String>();
            								     

            									
            									JTextField nomp = new JTextField(10);
            									nomp.setFont(font1);
            									nomp.setBackground(new Color (255, 240, 240) );
            									JTextField prenomp = new JTextField(10);
            									prenomp.setFont(font1);
            									prenomp.setBackground(new Color (255, 240, 240) );
            									JTextField nommed = new JTextField(10);
            									nommed.setFont(font1);
            									nommed.setBackground(new Color (255, 240, 240) );
            									JTextField dosagemed = new JTextField(10);
            									dosagemed.setFont(font1);
            									dosagemed.setBackground(new Color (255, 240, 240) );
            									JTextField cinp = new JTextField(10);
            									cinp.setFont(font1);
            									cinp.setBackground(new Color (255, 240, 240) );
            									JLabel lblnom = new JLabel("NOMP:");
            									lblnom.setFont(font);
            									lblnom.setLabelFor(nomp);
            									lblnom.setPreferredSize(d);

            									JLabel lblprenom = new JLabel("PRENOMP:");
            									lblprenom.setFont(font);
            									lblprenom.setLabelFor(prenomp);
            									lblprenom.setPreferredSize(d);

            									JLabel lblage = new JLabel("NOMMED");
            									lblage.setFont(font);
            									lblage.setLabelFor(nommed);
            									lblage.setPreferredSize(d);

            									JLabel lbladresse = new JLabel("DOSAGE:");
            									lbladresse.setFont(font);
            									lbladresse.setLabelFor(dosagemed);
            									lbladresse.setPreferredSize(d);

            									JLabel lbltel = new JLabel("CINP:");
            									lbltel.setFont(font);
            									lbltel.setLabelFor(cinp);
            									lbltel.setPreferredSize(d);

            									JPanel p0 = new JPanel(fl1);

            									p0.setBackground(new Color(255, 204, 204));

            									JPanel p1 = new JPanel(fl1);

            									p1.add(lblnom);
            									p1.add(nomp);
            									p1.setBackground(new Color(255, 204, 204));

            									JPanel p2 = new JPanel(fl1);

            									p2.add(lblprenom);
            									p2.add(prenomp);
            									p2.setBackground(new Color(255, 204, 204));

            									JPanel p3 = new JPanel(fl1);

            									p3.add(lblage);
            									p3.add(nommed);
            									p3.setBackground(new Color(255, 204, 204));

            									JPanel p4 = new JPanel(fl1);

            									p4.add(lbladresse);
            									p4.add(dosagemed);
            									p4.setBackground(new Color(255, 204, 204));

            									JPanel p5 = new JPanel(fl1);

            									p5.add(lbltel);
            									p5.add(cinp);
            									p5.setBackground(new Color(255, 204, 204));

            									JPanel p6 = new JPanel(fl1);

            									p6.add(button);
            									p6.setBackground(new Color(255, 204, 204));

            									JPanel p = new JPanel(fl1);
            									Connection c;
            									try {
            										c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            										Statement stmt = c.createStatement();
            										String sql1="select ID from ORDONNACE";
            										ResultSet rs= stmt.executeQuery(sql1);
            										
            										String sql2="select NOMP, PRENOMP ,NOMMEDICAMENT ,DOSAGEMEDICAMENT ,CINP from ORDONNACE where ID =? ";
            										while(rs.next()) {
            											String cin1=rs.getString("ID");
            											box.addItem(cin1);
            											
            										}
            										  box.addItemListener(new ItemListener() {
            										        @Override
            										        public void itemStateChanged(ItemEvent e) {
            										            String tmp = (String)box.getSelectedItem();
            										            try {
            										                PreparedStatement pst = c.prepareStatement(sql2);
            										                pst.setString(1, tmp);
            										                ResultSet rs1 = pst.executeQuery();
            										                if(rs1.next()){
            										                    String nom1=rs1.getString("NOMP");
            										                    nomp.setText(nom1);
            										                    String prenom1=rs1.getString("PRENOMP");
            										                    prenomp.setText(prenom1);
            										                    String age1=rs1.getString("NOMMEDICAMENT");
            										                    nommed.setText(age1);
            										                    String adresse1=rs1.getString("DOSAGEMEDICAMENT");
            										                    dosagemed.setText(adresse1);
            										                    String tel1=rs1.getString("CINP");
            										                    cinp.setText(tel1);
            										                }
            										            } catch (SQLException ex) {
            										                ex.printStackTrace();
            										            }
            										        }

            												
            										    });
            										
            									} catch (SQLException e2) {
            										e2.printStackTrace();
            									}
            									
            									BoxLayout b = new BoxLayout(p, BoxLayout.Y_AXIS);

            									p.setLayout(b);
            									p.add(box);
            									p.add(p0);
            									p.add(p1);
            									p.add(p2);
            									p.add(p3);
            									p.add(p4);
            									p.add(p5);
            									p.add(p6);

            									JPanel pf = new JPanel(fl1);
            									pf.add(p);
            									

            									pf.setSize(300, 400);
            									pf.setBackground(new Color(255, 204, 204));
            									f.setContentPane(pf);
            									 button.addActionListener(new ActionListener() {
            										public void actionPerformed(ActionEvent e) {
            											String n1 = (String) box.getSelectedItem();
            											String n2 = nomp.getText();
            											String n3 = prenomp.getText();
            											String n4 = nommed.getText();
            											String n5 = dosagemed.getText();
            											String n6 = cinp.getText();
            											try {
            												Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            												
            												PreparedStatement stmt = c.prepareStatement("update ORDONNACE set NOMP = ?, PRENOMP=? ,NOMMEDICAMENT = ?,DOSAGEMEDICAMENT =?,CINP= ? where ID=?");
            												stmt.setString(1, n2);
            												stmt.setString(2, n3);
            												stmt.setString(3, n4);
            												stmt.setString(4, n5);
            												stmt.setString(5, n6);
            												stmt.setString(6, n1);
            												int nb = stmt.executeUpdate();
            												
            												f.setVisible(false);
            												JOptionPane.showMessageDialog(null, "ordonnance  modifié");
            												
            											} catch (Exception e1) {
            												System.out.println(e1);
            												f.setVisible(false);
            												JOptionPane.showMessageDialog(null, "ordonnance n\'est pas modifié");
            											}
            										}
            									});

            								}
            							});
            item12.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		JFrame f = new JFrame("Supprimer ordonnace");
            		f.setSize(300, 400);
            		f.setVisible(true);
            		f.setResizable(false);
            		ImageIcon img = new ImageIcon(GestionCabinet.class.getResource("delete.png"));
            		f.setIconImage(img.getImage());
            		Dimension d = new Dimension(100, 35);
            		Font font = new Font("Serif", Font.PLAIN, 20);
            		Font font1 = new Font("Serif", Font.PLAIN, 16);
            		FlowLayout fl1 = new FlowLayout(FlowLayout.LEFT);
            		
            		JButton button = new JButton("submit");
            		 button.setBackground(Color.LIGHT_GRAY);
            	     button.setForeground(Color.white);
            	     button.setFont(new Font("Arial", Font.BOLD, 20));
            	     JComboBox<String> box = new JComboBox<String>();
            	     

            		
            		JTextField nomp = new JTextField(10);
            		nomp.setFont(font1);
            		nomp.setBackground(new Color (255, 240, 240) );
            		nomp.setEditable(false);
            		JTextField prenomp = new JTextField(10);
            		prenomp.setFont(font1);
            		prenomp.setBackground(new Color (255, 240, 240) );
            		prenomp.setEditable(false);
            		JTextField nommed = new JTextField(10);
            		nommed.setFont(font1);
            		nommed.setBackground(new Color (255, 240, 240) );
            		nommed.setEditable(false);
            		JTextField dosagemed = new JTextField(10);
            		dosagemed.setFont(font1);
            		dosagemed.setBackground(new Color (255, 240, 240) );
            		dosagemed.setEditable(false);
            		JTextField cinp = new JTextField(10);
            		cinp.setFont(font1);
            		cinp.setBackground(new Color (255, 240, 240) );
            		cinp.setEditable(false);
            		JLabel lblnom = new JLabel("NOMP:");
            		lblnom.setFont(font);
            		lblnom.setLabelFor(nomp);
            		lblnom.setPreferredSize(d);

            		JLabel lblprenom = new JLabel("PRENOMP:");
            		lblprenom.setFont(font);
            		lblprenom.setLabelFor(prenomp);
            		lblprenom.setPreferredSize(d);

            		JLabel lblage = new JLabel("NOMMED");
            		lblage.setFont(font);
            		lblage.setLabelFor(nommed);
            		lblage.setPreferredSize(d);

            		JLabel lbladresse = new JLabel("DOSAGE:");
            		lbladresse.setFont(font);
            		lbladresse.setLabelFor(dosagemed);
            		lbladresse.setPreferredSize(d);

            		JLabel lbltel = new JLabel("CINP:");
            		lbltel.setFont(font);
            		lbltel.setLabelFor(cinp);
            		lbltel.setPreferredSize(d);

            		JPanel p0 = new JPanel(fl1);

            		p0.setBackground(new Color(255, 204, 204));

            		JPanel p1 = new JPanel(fl1);

            		p1.add(lblnom);
            		p1.add(nomp);
            		p1.setBackground(new Color(255, 204, 204));

            		JPanel p2 = new JPanel(fl1);

            		p2.add(lblprenom);
            		p2.add(prenomp);
            		p2.setBackground(new Color(255, 204, 204));

            		JPanel p3 = new JPanel(fl1);

            		p3.add(lblage);
            		p3.add(nommed);
            		p3.setBackground(new Color(255, 204, 204));

            		JPanel p4 = new JPanel(fl1);

            		p4.add(lbladresse);
            		p4.add(dosagemed);
            		p4.setBackground(new Color(255, 204, 204));

            		JPanel p5 = new JPanel(fl1);

            		p5.add(lbltel);
            		p5.add(cinp);
            		p5.setBackground(new Color(255, 204, 204));

            		JPanel p6 = new JPanel(fl1);

            		p6.add(button);
            		p6.setBackground(new Color(255, 204, 204));

            		JPanel p = new JPanel(fl1);
            		Connection c;
            		try {
            			c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            			Statement stmt = c.createStatement();
            			String sql1="select ID from ORDONNACE";
            			ResultSet rs= stmt.executeQuery(sql1);
            			
            			String sql2="select NOMP, PRENOMP ,NOMMEDICAMENT ,DOSAGEMEDICAMENT ,CINP from ORDONNACE where ID =? ";
            			while(rs.next()) {
            				String cin1=rs.getString("ID");
            				box.addItem(cin1);
            			}
            			
            			  box.addItemListener(new ItemListener() {
            			        @Override
            			        public void itemStateChanged(ItemEvent e) {
            			        	
            			            String tmp = (String)box.getSelectedItem();
            			            try {
            			                PreparedStatement pst = c.prepareStatement(sql2);
            			                pst.setString(1, tmp);
            			                ResultSet rs1 = pst.executeQuery();
            			                if(rs1.next()){
            			                    String nom1=rs1.getString("NOMP");
            			                    nomp.setText(nom1);
            			                   
            			                    String prenom1=rs1.getString("PRENOMP");
            			                    prenomp.setText(prenom1);
            			                    
            			                    String age1=rs1.getString("NOMMEDICAMENT");
            			                    nommed.setText(age1);
            			                   
            			                    String adresse1=rs1.getString("DOSAGEMEDICAMENT");
            			                    dosagemed.setText(adresse1);
            			                    
            			                    String tel1=rs1.getString("CINP");
            			                    cinp.setText(tel1);
            			                
            			                }
            			                
            			               
            			            } catch (SQLException ex) {
            			                ex.printStackTrace();
            			            }
            			        }


            					
            			    });
            			
            		} catch (SQLException e2) {
            			e2.printStackTrace();
            		}
            		
            		BoxLayout b = new BoxLayout(p, BoxLayout.Y_AXIS);

            		p.setLayout(b);
            		p.add(box);
            		p.add(p0);
            		p.add(p1);
            		p.add(p2);
            		p.add(p3);
            		p.add(p4);
            		p.add(p5);
            		p.add(p6);

            		JPanel pf = new JPanel(fl1);
            		pf.add(p);
            		

            		pf.setSize(300, 400);
            		pf.setBackground(new Color(255, 204, 204));
            		f.setContentPane(pf);
            		 button.addActionListener(new ActionListener() {
            			public void actionPerformed(ActionEvent e) {
            				String n1 = (String) box.getSelectedItem();
            				try {
            					Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            					
            					PreparedStatement stmt = c.prepareStatement("delete from ORDONNACE where ID=?");
            					stmt.setString(1, n1);
            					int nb = stmt.executeUpdate();
            					
            					f.setVisible(false);
            					JOptionPane.showMessageDialog(null, "ordonnace  supprime");
            					
            				} catch (Exception e1) {
            					System.out.println(e1);
            					f.setVisible(false);
            					JOptionPane.showMessageDialog(null, "ordonnace n\'est pas supprime");
            				}
            			}
            		});

            	}
            });
            			item13.addActionListener(new ActionListener() {
            			public void actionPerformed(ActionEvent e) {
            			System.exit(0);
            			}});		
            			JLabel imageLabel = new JLabel(new ImageIcon("D:\\Code\\Java\\Projet_arch2tier\\src\\Proj\\1.png"));
            			panel.add(imageLabel);
            			imageLabel.setBounds(0, 30, 700, 300);
            			jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            		
            		menu2.setVisible(false);
            		jf.setVisible(true);
                } else if (isSecretary && !isDoctor && username.equals(SECRETARY_USERNAME) && password.equals(SECRETARY_PASSWORD)) {
                	frame.setVisible(false);
            		JFrame jf = new JFrame("Gestion d'un cabinet Médicale");
            		ImageIcon img = new ImageIcon(GestionCabinet.class.getResource("clinic.png"));
            		jf.setIconImage(img.getImage());
            		jf.setBounds(200, 100, 800, 600);
            		jf.setSize(715, 400);
            		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            		jf.setResizable(false);
            		JPanel panel=new JPanel();
            		panel.setLayout(null);
            		jf.setContentPane(panel);
            		JMenuBar barreDeMenu = new JMenuBar();
            		barreDeMenu.setPreferredSize(new Dimension(200,200));
            		panel.add(barreDeMenu);
            		barreDeMenu.setBounds(0, 0, 700, 30);
            		JMenu menu1 = new JMenu("Gestion des patients");
            		menu1.setPreferredSize(new Dimension(200,200));
            		JMenu menu2 = new JMenu("Gestion des RDV");
            		menu2.setPreferredSize(new Dimension(200,200));
            		JMenu menu3 = new JMenu("Gestion des Ordonnances");
            		menu3.setPreferredSize(new Dimension(200,200));
            		JMenu menu4 = new JMenu("Quitter");
            		menu4.setPreferredSize(new Dimension(200,200));
            		JMenuItem item1 = new JMenuItem("Afficher Patient");
            		menu1.setIcon(new ImageIcon(GestionCabinet.class.getResource("gestpat.png")));
            		JMenuItem item2 = new JMenuItem("Ajouter Patient");
            		menu2.setIcon(new ImageIcon(GestionCabinet.class.getResource("gestrend.png")));
            		JMenuItem item3 = new JMenuItem("Modifier Patient");
            		menu3.setIcon(new ImageIcon(GestionCabinet.class.getResource("gestord.png")));
            		JMenuItem item4 = new JMenuItem("Supprimer Patient");
            		menu4.setIcon(new ImageIcon(GestionCabinet.class.getResource("exit.png")));
            		
            		JMenuItem item5 = new JMenuItem("Afficher RDV");
            		JMenuItem item6 = new JMenuItem("Ajouter RDV");
            		JMenuItem item7 = new JMenuItem("Modifier RDV");
            		JMenuItem item8 = new JMenuItem("Supprimer RDV");
            		
            		JMenuItem item9 = new JMenuItem("Afficher Ordonnances");
            		JMenuItem item10 = new JMenuItem("Ajouter Ordonnances");
            		JMenuItem item11 = new JMenuItem("Modifier Ordonnances");
            		JMenuItem item12 = new JMenuItem("Supprimer Ordonnances");
            		
            		JMenuItem item13 = new JMenuItem("Quitter gestion");
            		menu1.add(item1);
            		menu1.add(item2);
            		menu1.add(item3);
            		menu1.add(item4);
            		
            		menu2.add(item5);
            		menu2.add(item6);
            		menu2.add(item7);
            		menu2.add(item8);
            		
            		menu3.add(item9);
            		menu3.add(item10);
            		menu3.add(item11);
            		menu3.add(item12);
            		
            		menu4.add(item13);
            		barreDeMenu.add(menu1);
            		barreDeMenu.add(menu2);
            		barreDeMenu.add(menu3);
            		barreDeMenu.add(menu4);
            		item1.addActionListener(new ActionListener() {
            			public void actionPerformed(ActionEvent e) {
            			String columns[] = { "CIN", "Nom", "Prénom" };
            			int i = 0;
            			try
            			{
            			
            			Class.forName("oracle.jdbc.driver.OracleDriver");
            			
            			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            			
            			Statement stmt = con.createStatement();
            			
            			ResultSet res1 = stmt.executeQuery("SELECT COUNT(1) as NumberOfRows FROM patient");
            			res1.next();
            			int nbligne=res1.getInt("NumberOfRows");
            			String data[][] = new String[nbligne][3];
            			ResultSet res = stmt.executeQuery("select * from patient");
            			while(res.next())
            			{
            			int CIN=res.getInt(1);
            			String nom=res.getString(2);
            			String prénom=res.getString(3);
            			data[i][0] = CIN + "";
            			data[i][1] = nom;
            			data[i][2] = prénom;
            			i++;
            			}
            			DefaultTableModel model = new DefaultTableModel(data, columns);
            			JTable table = new JTable(model);
            			table.setShowGrid(true);
            			table.setShowVerticalLines(true);
            			JScrollPane pane = new JScrollPane(table);
            			JFrame f = new JFrame("Liste Des Patients");
            			JPanel panel = new JPanel();
            			ImageIcon img = new ImageIcon(GestionCabinet.class.getResource("list.png"));
            			f.setIconImage(img.getImage());
            			panel.add(pane);
            			f.add(panel);
            			f.setSize(500, 250);
            			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            			f.setVisible(true);
            			//étape 5: fermez l'objet de connexion
            			con.close();
            			}
            			catch(Exception e1){

            			System.out.println(e1);
            			}

            			}
            			});





            item2.addActionListener(new ActionListener() {
            								public void actionPerformed(ActionEvent e) {
            									JFrame f = new JFrame("Ajouter patient");
            									f.setSize(300, 400);
            									f.setVisible(true);
            									f.setResizable(false);
            									ImageIcon img = new ImageIcon(GestionCabinet.class.getResource("ajoutpat.png"));
            									f.setIconImage(img.getImage());
            									Dimension d = new Dimension(100, 35);
            									Font font = new Font("Serif", Font.PLAIN, 20);
            									Font font1 = new Font("Serif", Font.PLAIN, 16);
            									FlowLayout fl1 = new FlowLayout(FlowLayout.LEFT);

            									JButton button = new JButton("submit");
            									 button.setBackground(Color.LIGHT_GRAY);
            								     button.setForeground(Color.white);
            								     button.setFont(new Font("Arial", Font.BOLD, 20));
            								

            									JTextField cin = new JTextField(10);
            									cin.setFont(font1);
            									cin.setBackground(new Color (255, 240, 240) );
            									JTextField nom = new JTextField(10);
            									nom.setFont(font1);
            									nom.setBackground(new Color (255, 240, 240) );
            									JTextField prenom = new JTextField(10);
            									prenom.setFont(font1);
            									prenom.setBackground(new Color (255, 240, 240) );
            									JTextField age = new JTextField(10);
            									age.setFont(font1);
            									age.setBackground(new Color (255, 240, 240) );
            									JTextField adresse = new JTextField(10);
            									adresse.setFont(font1);
            									adresse.setBackground(new Color (255, 240, 240) );
            									JTextField tel = new JTextField(10);
            									tel.setFont(font1);
            									tel.setBackground(new Color (255, 240, 240) );

            									JLabel lblcin = new JLabel("CIN:");
            									lblcin.setFont(font);
            									lblcin.setLabelFor(cin);
            									lblcin.setPreferredSize(d);

            									JLabel lblnom = new JLabel("NOM:");
            									lblnom.setFont(font);
            									lblnom.setLabelFor(nom);
            									lblnom.setPreferredSize(d);

            									JLabel lblprenom = new JLabel("PRENOM:");
            									lblprenom.setFont(font);
            									lblprenom.setLabelFor(prenom);
            									lblprenom.setPreferredSize(d);

            									JLabel lblage = new JLabel("AGE");
            									lblage.setFont(font);
            									lblage.setLabelFor(age);
            									lblage.setPreferredSize(d);

            									JLabel lbladresse = new JLabel("ADRESSE:");
            									lbladresse.setFont(font);
            									lbladresse.setLabelFor(adresse);
            									lbladresse.setPreferredSize(d);

            									JLabel lbltel = new JLabel("TEL:");
            									lbltel.setFont(font);
            									lbltel.setLabelFor(tel);
            									lbltel.setPreferredSize(d);

            									JPanel p0 = new JPanel(fl1);

            									p0.add(lblcin);
            									p0.add(cin);
            									p0.setBackground(new Color(255, 204, 204));

            									JPanel p1 = new JPanel(fl1);

            									p1.add(lblnom);
            									p1.add(nom);
            									p1.setBackground(new Color(255, 204, 204));

            									JPanel p2 = new JPanel(fl1);

            									p2.add(lblprenom);
            									p2.add(prenom);
            									p2.setBackground(new Color(255, 204, 204));

            									JPanel p3 = new JPanel(fl1);

            									p3.add(lblage);
            									p3.add(age);
            									p3.setBackground(new Color(255, 204, 204));

            									JPanel p4 = new JPanel(fl1);

            									p4.add(lbladresse);
            									p4.add(adresse);
            									p4.setBackground(new Color(255, 204, 204));

            									JPanel p5 = new JPanel(fl1);

            									p5.add(lbltel);
            									p5.add(tel);
            									p5.setBackground(new Color(255, 204, 204));

            									JPanel p6 = new JPanel(fl1);

            									p6.add(button);
            									p6.setBackground(new Color(255, 204, 204));

            									JPanel p = new JPanel(fl1);

            									BoxLayout b = new BoxLayout(p, BoxLayout.Y_AXIS);

            									p.setLayout(b);

            									p.add(p0);
            									p.add(p1);
            									p.add(p2);
            									p.add(p3);
            									p.add(p4);
            									p.add(p5);
            									p.add(p6);

            									JPanel pf = new JPanel(fl1);
            									pf.add(p);

            									pf.setSize(300, 400);
            									pf.setBackground(new Color(255, 204, 204));
            									f.setContentPane(pf);
            									 button.addActionListener(new ActionListener() {
            										public void actionPerformed(ActionEvent e) {
            											String n1 = cin.getText();
            											String n2 = nom.getText();
            											String n3 = prenom.getText();
            											String n4 = age.getText();
            											String n5 = adresse.getText();
            											String n6 = tel.getText();
            											try {
            												Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            												Statement stmt = c.createStatement();
            												stmt.executeUpdate("insert into patient values (" + n1 + ",'" + n2 + "','" + n3 + "'," + n4
            														+ ",'" + n5 + "','" + n6 + "')");
            												f.setVisible(false);
            												JOptionPane.showMessageDialog(null, "patient  ajouté");
            												
            											} catch (Exception e1) {
            												System.out.println(e1);
            												f.setVisible(false);
            												JOptionPane.showMessageDialog(null, "patient non ajouté");
            											}
            										}
            									});

            								}
            							});

            item3.addActionListener(new ActionListener() {
            								public void actionPerformed(ActionEvent e) {
            									JFrame f = new JFrame("Modifier patient");
            									f.setSize(300, 400);
            									f.setVisible(true);
            									f.setResizable(false);
            									ImageIcon img = new ImageIcon(GestionCabinet.class.getResource("modifpat.png"));
            									f.setIconImage(img.getImage());
            									Dimension d = new Dimension(100, 35);
            									Font font = new Font("Serif", Font.PLAIN, 20);
            									Font font1 = new Font("Serif", Font.PLAIN, 16);
            									FlowLayout fl1 = new FlowLayout(FlowLayout.LEFT);
            									
            									JButton button = new JButton("submit");
            									 button.setBackground(Color.LIGHT_GRAY);
            								     button.setForeground(Color.white);
            								     button.setFont(new Font("Arial", Font.BOLD, 20));
            								     JComboBox<String> box = new JComboBox<String>();
            								     

            									
            									JTextField nom = new JTextField(10);
            									nom.setFont(font1);
            									nom.setBackground(new Color (255, 240, 240) );
            									JTextField prenom = new JTextField(10);
            									prenom.setFont(font1);
            									prenom.setBackground(new Color (255, 240, 240) );
            									JTextField age = new JTextField(10);
            									age.setFont(font1);
            									age.setBackground(new Color (255, 240, 240) );
            									JTextField adresse = new JTextField(10);
            									adresse.setFont(font1);
            									adresse.setBackground(new Color (255, 240, 240) );
            									JTextField tel = new JTextField(10);
            									tel.setFont(font1);
            									tel.setBackground(new Color (255, 240, 240) );
            									JLabel lblnom = new JLabel("NOM:");
            									lblnom.setFont(font);
            									lblnom.setLabelFor(nom);
            									lblnom.setPreferredSize(d);

            									JLabel lblprenom = new JLabel("PRENOM:");
            									lblprenom.setFont(font);
            									lblprenom.setLabelFor(prenom);
            									lblprenom.setPreferredSize(d);

            									JLabel lblage = new JLabel("AGE");
            									lblage.setFont(font);
            									lblage.setLabelFor(age);
            									lblage.setPreferredSize(d);

            									JLabel lbladresse = new JLabel("ADRESSE:");
            									lbladresse.setFont(font);
            									lbladresse.setLabelFor(adresse);
            									lbladresse.setPreferredSize(d);

            									JLabel lbltel = new JLabel("TEL:");
            									lbltel.setFont(font);
            									lbltel.setLabelFor(tel);
            									lbltel.setPreferredSize(d);

            									JPanel p0 = new JPanel(fl1);

            									p0.setBackground(new Color(255, 204, 204));

            									JPanel p1 = new JPanel(fl1);

            									p1.add(lblnom);
            									p1.add(nom);
            									p1.setBackground(new Color(255, 204, 204));

            									JPanel p2 = new JPanel(fl1);

            									p2.add(lblprenom);
            									p2.add(prenom);
            									p2.setBackground(new Color(255, 204, 204));

            									JPanel p3 = new JPanel(fl1);

            									p3.add(lblage);
            									p3.add(age);
            									p3.setBackground(new Color(255, 204, 204));

            									JPanel p4 = new JPanel(fl1);

            									p4.add(lbladresse);
            									p4.add(adresse);
            									p4.setBackground(new Color(255, 204, 204));

            									JPanel p5 = new JPanel(fl1);

            									p5.add(lbltel);
            									p5.add(tel);
            									p5.setBackground(new Color(255, 204, 204));

            									JPanel p6 = new JPanel(fl1);

            									p6.add(button);
            									p6.setBackground(new Color(255, 204, 204));

            									JPanel p = new JPanel(fl1);
            									Connection c;
            									try {
            										c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            										Statement stmt = c.createStatement();
            										String sql1="select CIN from PATIENT";
            										ResultSet rs= stmt.executeQuery(sql1);
            										
            										String sql2="select NOM, PRENOM ,AGE ,ADRESSE ,TEL from patient where CIN =? ";
            										while(rs.next()) {
            											String cin1=rs.getString("CIN");
            											box.addItem(cin1);
            											
            										}
            										  box.addItemListener(new ItemListener() {
            										        @Override
            										        public void itemStateChanged(ItemEvent e) {
            										            String tmp = (String)box.getSelectedItem();
            										            try {
            										                PreparedStatement pst = c.prepareStatement(sql2);
            										                pst.setString(1, tmp);
            										                ResultSet rs1 = pst.executeQuery();
            										                if(rs1.next()){
            										                    String nom1=rs1.getString("NOM");
            										                    nom.setText(nom1);
            										                    String prenom1=rs1.getString("PRENOM");
            										                    prenom.setText(prenom1);
            										                    String age1=rs1.getString("AGE");
            										                    age.setText(age1);
            										                    String adresse1=rs1.getString("ADRESSE");
            										                    adresse.setText(adresse1);
            										                    String tel1=rs1.getString("TEL");
            										                    tel.setText(tel1);
            										                }
            										            } catch (SQLException ex) {
            										                ex.printStackTrace();
            										            }
            										        }

            												
            										    });
            										
            									} catch (SQLException e2) {
            										e2.printStackTrace();
            									}
            									
            									BoxLayout b = new BoxLayout(p, BoxLayout.Y_AXIS);

            									p.setLayout(b);
            									p.add(box);
            									p.add(p0);
            									p.add(p1);
            									p.add(p2);
            									p.add(p3);
            									p.add(p4);
            									p.add(p5);
            									p.add(p6);

            									JPanel pf = new JPanel(fl1);
            									pf.add(p);
            									

            									pf.setSize(300, 400);
            									pf.setBackground(new Color(255, 204, 204));
            									f.setContentPane(pf);
            									 button.addActionListener(new ActionListener() {
            										public void actionPerformed(ActionEvent e) {
            											String n1 = (String) box.getSelectedItem();
            											String n2 = nom.getText();
            											String n3 = prenom.getText();
            											String n4 = age.getText();
            											String n5 = adresse.getText();
            											String n6 = tel.getText();
            											try {
            												Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            												
            												PreparedStatement stmt = c.prepareStatement("update patient set NOM = ?, PRENOM=? ,AGE = ?,ADRESSE =?,TEL= ? where CIN=?");
            												stmt.setString(1, n2);
            												stmt.setString(2, n3);
            												stmt.setString(3, n4);
            												stmt.setString(4, n5);
            												stmt.setString(5, n6);
            												stmt.setString(6, n1);
            												int nb = stmt.executeUpdate();
            												
            												f.setVisible(false);
            												JOptionPane.showMessageDialog(null, "patient  modifié");
            												
            											} catch (Exception e1) {
            												System.out.println(e1);
            												f.setVisible(false);
            												JOptionPane.showMessageDialog(null, "patient n\'est pas modifié");
            											}
            										}
            									});

            								}
            							});
            item4.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		JFrame f = new JFrame("Supprimer patient");
            		f.setSize(300, 400);
            		f.setVisible(true);
            		f.setResizable(false);
            		ImageIcon img = new ImageIcon(GestionCabinet.class.getResource("delete.png"));
            		f.setIconImage(img.getImage());
            		Dimension d = new Dimension(100, 35);
            		Font font = new Font("Serif", Font.PLAIN, 20);
            		Font font1 = new Font("Serif", Font.PLAIN, 16);
            		FlowLayout fl1 = new FlowLayout(FlowLayout.LEFT);
            		
            		JButton button = new JButton("submit");
            		 button.setBackground(Color.LIGHT_GRAY);
            	     button.setForeground(Color.white);
            	     button.setFont(new Font("Arial", Font.BOLD, 20));
            	     JComboBox<String> box = new JComboBox<String>();
            	     

            		
            		JTextField nom = new JTextField(10);
            		nom.setFont(font1);
            		nom.setBackground(new Color (255, 240, 240) );
            		nom.setEditable(false);
            		JTextField prenom = new JTextField(10);
            		prenom.setFont(font1);
            		prenom.setBackground(new Color (255, 240, 240) );
            		prenom.setEditable(false);
            		JTextField age = new JTextField(10);
            		age.setFont(font1);
            		age.setBackground(new Color (255, 240, 240) );
            		age.setEditable(false);
            		JTextField adresse = new JTextField(10);
            		adresse.setFont(font1);
            		adresse.setBackground(new Color (255, 240, 240) );
            		adresse.setEditable(false);
            		JTextField tel = new JTextField(10);
            		tel.setFont(font1);
            		tel.setBackground(new Color (255, 240, 240) );
            		tel.setEditable(false);
            		JLabel lblnom = new JLabel("NOM:");
            		lblnom.setFont(font);
            		lblnom.setLabelFor(nom);
            		lblnom.setPreferredSize(d);

            		JLabel lblprenom = new JLabel("PRENOM:");
            		lblprenom.setFont(font);
            		lblprenom.setLabelFor(prenom);
            		lblprenom.setPreferredSize(d);

            		JLabel lblage = new JLabel("AGE");
            		lblage.setFont(font);
            		lblage.setLabelFor(age);
            		lblage.setPreferredSize(d);

            		JLabel lbladresse = new JLabel("ADRESSE:");
            		lbladresse.setFont(font);
            		lbladresse.setLabelFor(adresse);
            		lbladresse.setPreferredSize(d);

            		JLabel lbltel = new JLabel("TEL:");
            		lbltel.setFont(font);
            		lbltel.setLabelFor(tel);
            		lbltel.setPreferredSize(d);

            		JPanel p0 = new JPanel(fl1);

            		p0.setBackground(new Color(255, 204, 204));

            		JPanel p1 = new JPanel(fl1);

            		p1.add(lblnom);
            		p1.add(nom);
            		p1.setBackground(new Color(255, 204, 204));

            		JPanel p2 = new JPanel(fl1);

            		p2.add(lblprenom);
            		p2.add(prenom);
            		p2.setBackground(new Color(255, 204, 204));

            		JPanel p3 = new JPanel(fl1);

            		p3.add(lblage);
            		p3.add(age);
            		p3.setBackground(new Color(255, 204, 204));

            		JPanel p4 = new JPanel(fl1);

            		p4.add(lbladresse);
            		p4.add(adresse);
            		p4.setBackground(new Color(255, 204, 204));

            		JPanel p5 = new JPanel(fl1);

            		p5.add(lbltel);
            		p5.add(tel);
            		p5.setBackground(new Color(255, 204, 204));

            		JPanel p6 = new JPanel(fl1);

            		p6.add(button);
            		p6.setBackground(new Color(255, 204, 204));

            		JPanel p = new JPanel(fl1);
            		Connection c;
            		try {
            			c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            			Statement stmt = c.createStatement();
            			String sql1="select CIN from PATIENT";
            			ResultSet rs= stmt.executeQuery(sql1);
            			
            			String sql2="select NOM, PRENOM ,AGE ,ADRESSE ,TEL from patient where CIN =? ";
            			while(rs.next()) {
            				String cin1=rs.getString("CIN");
            				box.addItem(cin1);
            			}
            			
            			  box.addItemListener(new ItemListener() {
            			        @Override
            			        public void itemStateChanged(ItemEvent e) {
            			        	
            			            String tmp = (String)box.getSelectedItem();
            			            try {
            			                PreparedStatement pst = c.prepareStatement(sql2);
            			                pst.setString(1, tmp);
            			                ResultSet rs1 = pst.executeQuery();
            			                if(rs1.next()){
            			                    String nom1=rs1.getString("NOM");
            			                    nom.setText(nom1);
            			                   
            			                    String prenom1=rs1.getString("PRENOM");
            			                    prenom.setText(prenom1);
            			                    
            			                    String age1=rs1.getString("AGE");
            			                    age.setText(age1);
            			                   
            			                    String adresse1=rs1.getString("ADRESSE");
            			                    adresse.setText(adresse1);
            			                    
            			                    String tel1=rs1.getString("TEL");
            			                    tel.setText(tel1);
            			                
            			                }
            			                
            			               
            			            } catch (SQLException ex) {
            			                ex.printStackTrace();
            			            }
            			        }


            					
            			    });
            			
            		} catch (SQLException e2) {
            			e2.printStackTrace();
            		}
            		
            		BoxLayout b = new BoxLayout(p, BoxLayout.Y_AXIS);

            		p.setLayout(b);
            		p.add(box);
            		p.add(p0);
            		p.add(p1);
            		p.add(p2);
            		p.add(p3);
            		p.add(p4);
            		p.add(p5);
            		p.add(p6);

            		JPanel pf = new JPanel(fl1);
            		pf.add(p);
            		

            		pf.setSize(300, 400);
            		pf.setBackground(new Color(255, 204, 204));
            		f.setContentPane(pf);
            		 button.addActionListener(new ActionListener() {
            			public void actionPerformed(ActionEvent e) {
            				String n1 = (String) box.getSelectedItem();
            				try {
            					Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            					
            					PreparedStatement stmt = c.prepareStatement("delete from patient where CIN=?");
            					stmt.setString(1, n1);
            					int nb = stmt.executeUpdate();
            					
            					f.setVisible(false);
            					JOptionPane.showMessageDialog(null, "patient  supprime");
            					
            				} catch (Exception e1) {
            					System.out.println(e1);
            					f.setVisible(false);
            					JOptionPane.showMessageDialog(null, "patient n\'est pas supprime");
            				}
            			}
            		});

            	}
            });
            item5.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            	String columns[] = { "ID", "DATERDV", "CINP" };
            	int i = 0;
            	try
            	{
            	//étape 1: charger la classe de driver
            	Class.forName("oracle.jdbc.driver.OracleDriver");
            	//étape 2: créer l'objet de connexion
            	Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            	//étape 3: créer l'objet statement
            	Statement stmt = con.createStatement();
            	//étape 4: exécuter la requête
            	ResultSet res1 = stmt.executeQuery("SELECT COUNT(1) as NumberOfRows FROM RDV");
            	res1.next();
            	int nbligne=res1.getInt("NumberOfRows");
            	String data[][] = new String[nbligne][3];
            	ResultSet res = stmt.executeQuery("select * from RDV");
            	while(res.next())
            	{
            	String ID=res.getString(1);
            	String DATERDV=res.getString(2);
            	String CINP=res.getString(3);
            	data[i][0] = ID + "";
            	data[i][1] = DATERDV;
            	data[i][2] = CINP;
            	i++;
            	}
            	DefaultTableModel model = new DefaultTableModel(data, columns);
            	JTable table = new JTable(model);
            	table.setShowGrid(true);
            	table.setShowVerticalLines(true);
            	JScrollPane pane = new JScrollPane(table);
            	JFrame f = new JFrame("Liste Des RDVS");
            	JPanel panel = new JPanel();
            	ImageIcon img = new ImageIcon(GestionCabinet.class.getResource("list.png"));
            	f.setIconImage(img.getImage());
            	panel.add(pane);
            	f.add(panel);
            	f.setSize(500, 250);
            	f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            	f.setVisible(true);
            	//étape 5: fermez l'objet de connexion
            	con.close();
            	}
            	catch(Exception e1){

            	System.out.println(e1);
            	}

            	}
            	});

            item6.addActionListener(new ActionListener() {
            								public void actionPerformed(ActionEvent e) {
            									JFrame f = new JFrame("Ajouter RDV");
            									f.setSize(300, 400);
            									f.setVisible(true);
            									f.setResizable(false);
            									ImageIcon img = new ImageIcon(GestionCabinet.class.getResource("ajoutdate.png"));
            									f.setIconImage(img.getImage());
            									Dimension d = new Dimension(100, 35);
            									Font font = new Font("Serif", Font.PLAIN, 20);
            									Font font1 = new Font("Serif", Font.PLAIN, 16);
            									FlowLayout fl1 = new FlowLayout(FlowLayout.LEFT);

            									JButton button = new JButton("submit");
            									 button.setBackground(Color.LIGHT_GRAY);
            								     button.setForeground(Color.white);
            								     button.setFont(new Font("Arial", Font.BOLD, 20));
            								

            									JTextField id = new JTextField(10);
            									id.setFont(font1);
            									id.setBackground(new Color (255, 240, 240) );
            									JTextField daterdv = new JTextField(10);
            									daterdv.setFont(font1);
            									daterdv.setBackground(new Color (255, 240, 240) );
            									JTextField cinp = new JTextField(10);
            									cinp.setFont(font1);
            									cinp.setBackground(new Color (255, 240, 240) );
            									
            									JLabel lblcin = new JLabel("ID:");
            									lblcin.setFont(font);
            									lblcin.setLabelFor(id);
            									lblcin.setPreferredSize(d);

            									JLabel lblnom = new JLabel("DATERDV:");
            									lblnom.setFont(font);
            									lblnom.setLabelFor(daterdv);
            									lblnom.setPreferredSize(d);

            									JLabel lblprenom = new JLabel("CINP:");
            									lblprenom.setFont(font);
            									lblprenom.setLabelFor(cinp);
            									lblprenom.setPreferredSize(d);


            									JPanel p0 = new JPanel(fl1);

            									p0.add(lblcin);
            									p0.add(id);
            									p0.setBackground(new Color(255, 204, 204));

            									JPanel p1 = new JPanel(fl1);

            									p1.add(lblnom);
            									p1.add(daterdv);
            									p1.setBackground(new Color(255, 204, 204));

            									JPanel p2 = new JPanel(fl1);

            									p2.add(lblprenom);
            									p2.add(cinp);
            									p2.setBackground(new Color(255, 204, 204));

            									JPanel p6 = new JPanel(fl1);

            									p6.add(button);
            									p6.setBackground(new Color(255, 204, 204));

            									JPanel p = new JPanel(fl1);

            									BoxLayout b = new BoxLayout(p, BoxLayout.Y_AXIS);

            									p.setLayout(b);

            									p.add(p0);
            									p.add(p1);
            									p.add(p2);
            									p.add(p6);

            									JPanel pf = new JPanel(fl1);
            									pf.add(p);

            									pf.setSize(300, 400);
            									pf.setBackground(new Color(255, 204, 204));
            									f.setContentPane(pf);
            									 button.addActionListener(new ActionListener() {
            										public void actionPerformed(ActionEvent e) {
            											String n1 = id.getText();
            											String n2 = daterdv.getText();
            											String n3 = cinp.getText();
            					
            											try {
            												Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            												Statement stmt = c.createStatement();
            												stmt.executeUpdate("insert into RDV values (" + n1 + ",'" + n2 + "','" + n3 + "')");
            												f.setVisible(false);
            												JOptionPane.showMessageDialog(null, "RDV  ajouté");
            												
            											} catch (Exception e1) {
            												System.out.println(e1);
            												f.setVisible(false);
            												JOptionPane.showMessageDialog(null, "RDV non ajouté");
            											}
            										}
            									});

            								}
            							});

            item7.addActionListener(new ActionListener() {
            								public void actionPerformed(ActionEvent e) {
            									JFrame f = new JFrame("Modifier RDV");
            									f.setSize(300, 400);
            									f.setVisible(true);
            									f.setResizable(false);
            									ImageIcon img = new ImageIcon(GestionCabinet.class.getResource("modifpat.png"));
            									f.setIconImage(img.getImage());
            									Dimension d = new Dimension(100, 35);
            									Font font = new Font("Serif", Font.PLAIN, 20);
            									Font font1 = new Font("Serif", Font.PLAIN, 16);
            									FlowLayout fl1 = new FlowLayout(FlowLayout.LEFT);
            									
            									JButton button = new JButton("submit");
            									 button.setBackground(Color.LIGHT_GRAY);
            								     button.setForeground(Color.white);
            								     button.setFont(new Font("Arial", Font.BOLD, 20));
            								     JComboBox<String> box = new JComboBox<String>();
            								     

            									
            									JTextField daterdv = new JTextField(10);
            									daterdv.setFont(font1);
            									daterdv.setBackground(new Color (255, 240, 240) );
            									JTextField cinp = new JTextField(10);
            									cinp.setFont(font1);
            									cinp.setBackground(new Color (255, 240, 240) );
            									
            									JLabel lblnom = new JLabel("DATERDV:");
            									lblnom.setFont(font);
            									lblnom.setLabelFor(daterdv);
            									lblnom.setPreferredSize(d);

            									JLabel lblprenom = new JLabel("CINP:");
            									lblprenom.setFont(font);
            									lblprenom.setLabelFor(cinp);
            									lblprenom.setPreferredSize(d);


            									JPanel p0 = new JPanel(fl1);

            									p0.setBackground(new Color(255, 204, 204));

            									JPanel p1 = new JPanel(fl1);

            									p1.add(lblnom);
            									p1.add(daterdv);
            									p1.setBackground(new Color(255, 204, 204));

            									JPanel p2 = new JPanel(fl1);

            									p2.add(lblprenom);
            									p2.add(cinp);
            									p2.setBackground(new Color(255, 204, 204));

            							
            									JPanel p6 = new JPanel(fl1);

            									p6.add(button);
            									p6.setBackground(new Color(255, 204, 204));

            									JPanel p = new JPanel(fl1);
            									Connection c;
            									try {
            										c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            										Statement stmt = c.createStatement();
            										String sql1="select ID from RDV";
            										ResultSet rs= stmt.executeQuery(sql1);
            										
            										String sql2="select DATERDV, CINP from RDV where ID =? ";
            										while(rs.next()) {
            											String cin1=rs.getString("ID");
            											box.addItem(cin1);
            											
            										}
            										  box.addItemListener(new ItemListener() {
            										        @Override
            										        public void itemStateChanged(ItemEvent e) {
            										            String tmp = (String)box.getSelectedItem();
            										            try {
            										                PreparedStatement pst = c.prepareStatement(sql2);
            										                pst.setString(1, tmp);
            										                ResultSet rs1 = pst.executeQuery();
            										                if(rs1.next()){
            										                    String nom1=rs1.getString("DATERDV");
            										                    daterdv.setText(nom1);
            										                    String prenom1=rs1.getString("CINP");
            										                    cinp.setText(prenom1);
            										                    
            										                }
            										            } catch (SQLException ex) {
            										                ex.printStackTrace();
            										            }
            										        }

            												
            										    });
            										
            									} catch (SQLException e2) {
            										e2.printStackTrace();
            									}
            									
            									BoxLayout b = new BoxLayout(p, BoxLayout.Y_AXIS);

            									p.setLayout(b);
            									p.add(box);
            									p.add(p0);
            									p.add(p1);
            									p.add(p2);
            									
            									p.add(p6);

            									JPanel pf = new JPanel(fl1);
            									pf.add(p);
            									

            									pf.setSize(300, 400);
            									pf.setBackground(new Color(255, 204, 204));
            									f.setContentPane(pf);
            									 button.addActionListener(new ActionListener() {
            										public void actionPerformed(ActionEvent e) {
            											String n1 = (String) box.getSelectedItem();
            											String n2 = daterdv.getText();
            											String n3 = cinp.getText();
            										
            											try {
            												Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            												
            												PreparedStatement stmt = c.prepareStatement("update RDV set DATERDV = ?, CINP = ? where ID=?");
            												stmt.setString(1, n2);
            												stmt.setString(2, n3);
            											
            												stmt.setString(3, n1);
            												int nb = stmt.executeUpdate();
            												
            												f.setVisible(false);
            												JOptionPane.showMessageDialog(null, "RDV  modifié");
            												
            											} catch (Exception e1) {
            												System.out.println(e1);
            												f.setVisible(false);
            												JOptionPane.showMessageDialog(null, "RDV n\'est pas modifié");
            											}
            										}
            									});

            								}
            							});
            item8.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		JFrame f = new JFrame("Supprimer RDV");
            		f.setSize(300, 400);
            		f.setVisible(true);
            		f.setResizable(false);
            		ImageIcon img = new ImageIcon(GestionCabinet.class.getResource("delete.png"));
            		f.setIconImage(img.getImage());
            		Dimension d = new Dimension(100, 35);
            		Font font = new Font("Serif", Font.PLAIN, 20);
            		Font font1 = new Font("Serif", Font.PLAIN, 16);
            		FlowLayout fl1 = new FlowLayout(FlowLayout.LEFT);
            		
            		JButton button = new JButton("submit");
            		 button.setBackground(Color.LIGHT_GRAY);
            	     button.setForeground(Color.white);
            	     button.setFont(new Font("Arial", Font.BOLD, 20));
            	     JComboBox<String> box = new JComboBox<String>();
            	     

            		
            		JTextField daterdv = new JTextField(10);
            		daterdv.setFont(font1);
            		daterdv.setBackground(new Color (255, 240, 240) );
            		daterdv.setEditable(false);
            		JTextField cinp = new JTextField(10);
            		cinp.setFont(font1);
            		cinp.setBackground(new Color (255, 240, 240) );
            		cinp.setEditable(false);
            		
            		JLabel lblnom = new JLabel("DATERDV:");
            		lblnom.setFont(font);
            		lblnom.setLabelFor(daterdv);
            		lblnom.setPreferredSize(d);

            		JLabel lblprenom = new JLabel("CINP:");
            		lblprenom.setFont(font);
            		lblprenom.setLabelFor(cinp);
            		lblprenom.setPreferredSize(d);


            		JPanel p0 = new JPanel(fl1);

            		p0.setBackground(new Color(255, 204, 204));

            		JPanel p1 = new JPanel(fl1);

            		p1.add(lblnom);
            		p1.add(daterdv);
            		p1.setBackground(new Color(255, 204, 204));

            		JPanel p2 = new JPanel(fl1);

            		p2.add(lblprenom);
            		p2.add(cinp);
            		p2.setBackground(new Color(255, 204, 204));


            		JPanel p6 = new JPanel(fl1);

            		p6.add(button);
            		p6.setBackground(new Color(255, 204, 204));

            		JPanel p = new JPanel(fl1);
            		Connection c;
            		try {
            			c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            			Statement stmt = c.createStatement();
            			String sql1="select ID from RDV";
            			ResultSet rs= stmt.executeQuery(sql1);
            			
            			String sql2="select DATERDV, CINP from RDV where ID =? ";
            			while(rs.next()) {
            				String cin1=rs.getString("ID");
            				box.addItem(cin1);
            				
            			}
            			  box.addItemListener(new ItemListener() {
            			        @Override
            			        public void itemStateChanged(ItemEvent e) {
            			            String tmp = (String)box.getSelectedItem();
            			            try {
            			                PreparedStatement pst = c.prepareStatement(sql2);
            			                pst.setString(1, tmp);
            			                ResultSet rs1 = pst.executeQuery();
            			                if(rs1.next()){
            			                    String nom1=rs1.getString("DATERDV");
            			                    daterdv.setText(nom1);
            			                    String prenom1=rs1.getString("CINP");
            			                    cinp.setText(prenom1);
            			                    
            			                }
            			            } catch (SQLException ex) {
            			                ex.printStackTrace();
            			            }
            			        }

            					
            			    });
            			
            		} catch (SQLException e2) {
            			e2.printStackTrace();
            		}
            		
            		BoxLayout b = new BoxLayout(p, BoxLayout.Y_AXIS);

            		p.setLayout(b);
            		p.add(box);
            		p.add(p0);
            		p.add(p1);
            		p.add(p2);
            		
            		p.add(p6);

            		JPanel pf = new JPanel(fl1);
            		pf.add(p);
            		

            		pf.setSize(300, 400);
            		pf.setBackground(new Color(255, 204, 204));
            		f.setContentPane(pf);
            		 button.addActionListener(new ActionListener() {
            			public void actionPerformed(ActionEvent e) {
            				String n1 = (String) box.getSelectedItem();
            				
            			
            				try {
            					Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            					
            					PreparedStatement stmt = c.prepareStatement("delete from RDV where ID=?");
            					
            				
            					stmt.setString(1, n1);
            					int nb = stmt.executeUpdate();
            					
            					f.setVisible(false);
            					JOptionPane.showMessageDialog(null, "RDV  supprime");
            					
            				} catch (Exception e1) {
            					System.out.println(e1);
            					f.setVisible(false);
            					JOptionPane.showMessageDialog(null, "RDV n\'est pas supprime");
            				}
            			}
            		});

            	}
            });
            item9.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            	String columns[] = { "ID", "NOMP", "PRENOMP" };
            	int i = 0;
            	try
            	{
            	//étape 1: charger la classe de driver
            	Class.forName("oracle.jdbc.driver.OracleDriver");
            	//étape 2: créer l'objet de connexion
            	Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            	//étape 3: créer l'objet statement
            	Statement stmt = con.createStatement();
            	//étape 4: exécuter la requête
            	ResultSet res1 = stmt.executeQuery("SELECT COUNT(1) as NumberOfRows FROM ORDONNACE");
            	res1.next();
            	int nbligne=res1.getInt("NumberOfRows");
            	String data[][] = new String[nbligne][3];
            	ResultSet res = stmt.executeQuery("select * from ORDONNACE");
            	while(res.next())
            	{
            	String ID=res.getString(1);
            	String nomp=res.getString(2);
            	String prenomp=res.getString(3);
            	data[i][0] = ID + "";
            	data[i][1] = nomp;
            	data[i][2] = prenomp;
            	i++;
            	}
            	DefaultTableModel model = new DefaultTableModel(data, columns);
            	JTable table = new JTable(model);
            	table.setShowGrid(true);
            	table.setShowVerticalLines(true);
            	JScrollPane pane = new JScrollPane(table);
            	JFrame f = new JFrame("Liste Des Ordonnances");
            	JPanel panel = new JPanel();
            	ImageIcon img = new ImageIcon(GestionCabinet.class.getResource("list.png"));
            	f.setIconImage(img.getImage());
            	panel.add(pane);
            	f.add(panel);
            	f.setSize(500, 250);
            	f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            	f.setVisible(true);
            	//étape 5: fermez l'objet de connexion
            	con.close();
            	}
            	catch(Exception e1){

            	System.out.println(e1);
            	}

            	}
            	});

            item10.addActionListener(new ActionListener() {
            								public void actionPerformed(ActionEvent e) {
            									JFrame f = new JFrame("Ajouter Ordonnace");
            									f.setSize(300, 400);
            									f.setVisible(true);
            									f.setResizable(false);
            									ImageIcon img = new ImageIcon(GestionCabinet.class.getResource("ajoutpat.png"));
            									f.setIconImage(img.getImage());
            									Dimension d = new Dimension(100, 35);
            									Font font = new Font("Serif", Font.PLAIN, 20);
            									Font font1 = new Font("Serif", Font.PLAIN, 16);
            									FlowLayout fl1 = new FlowLayout(FlowLayout.LEFT);

            									JButton button = new JButton("submit");
            									 button.setBackground(Color.LIGHT_GRAY);
            								     button.setForeground(Color.white);
            								     button.setFont(new Font("Arial", Font.BOLD, 20));
            								

            									JTextField id = new JTextField(10);
            									id.setFont(font1);
            									id.setBackground(new Color (255, 240, 240) );
            									JTextField nomp = new JTextField(10);
            									nomp.setFont(font1);
            									nomp.setBackground(new Color (255, 240, 240) );
            									JTextField prenomp = new JTextField(10);
            									prenomp.setFont(font1);
            									prenomp.setBackground(new Color (255, 240, 240) );
            									JTextField nommedicament = new JTextField(10);
            									nommedicament.setFont(font1);
            									nommedicament.setBackground(new Color (255, 240, 240) );
            									JTextField dosagemed = new JTextField(10);
            									dosagemed.setFont(font1);
            									dosagemed.setBackground(new Color (255, 240, 240) );
            									JTextField cinp = new JTextField(10);
            									cinp.setFont(font1);
            									cinp.setBackground(new Color (255, 240, 240) );

            									JLabel lblcin = new JLabel("ID:");
            									lblcin.setFont(font);
            									lblcin.setLabelFor(id);
            									lblcin.setPreferredSize(d);

            									JLabel lblnom = new JLabel("NOMP:");
            									lblnom.setFont(font);
            									lblnom.setLabelFor(nomp);
            									lblnom.setPreferredSize(d);

            									JLabel lblprenom = new JLabel("PRENOMP:");
            									lblprenom.setFont(font);
            									lblprenom.setLabelFor(prenomp);
            									lblprenom.setPreferredSize(d);

            									JLabel lblage = new JLabel("NOMMED");
            									lblage.setFont(font);
            									lblage.setLabelFor(nommedicament);
            									lblage.setPreferredSize(d);

            									JLabel lbladresse = new JLabel("DOSAGE:");
            									lbladresse.setFont(font);
            									lbladresse.setLabelFor(dosagemed);
            									lbladresse.setPreferredSize(d);

            									JLabel lbltel = new JLabel("CINP:");
            									lbltel.setFont(font);
            									lbltel.setLabelFor(cinp);
            									lbltel.setPreferredSize(d);

            									JPanel p0 = new JPanel(fl1);

            									p0.add(lblcin);
            									p0.add(id);
            									p0.setBackground(new Color(255, 204, 204));

            									JPanel p1 = new JPanel(fl1);

            									p1.add(lblnom);
            									p1.add(nomp);
            									p1.setBackground(new Color(255, 204, 204));

            									JPanel p2 = new JPanel(fl1);

            									p2.add(lblprenom);
            									p2.add(prenomp);
            									p2.setBackground(new Color(255, 204, 204));

            									JPanel p3 = new JPanel(fl1);

            									p3.add(lblage);
            									p3.add(nommedicament);
            									p3.setBackground(new Color(255, 204, 204));

            									JPanel p4 = new JPanel(fl1);

            									p4.add(lbladresse);
            									p4.add(dosagemed);
            									p4.setBackground(new Color(255, 204, 204));

            									JPanel p5 = new JPanel(fl1);

            									p5.add(lbltel);
            									p5.add(cinp);
            									p5.setBackground(new Color(255, 204, 204));

            									JPanel p6 = new JPanel(fl1);

            									p6.add(button);
            									p6.setBackground(new Color(255, 204, 204));

            									JPanel p = new JPanel(fl1);

            									BoxLayout b = new BoxLayout(p, BoxLayout.Y_AXIS);

            									p.setLayout(b);

            									p.add(p0);
            									p.add(p1);
            									p.add(p2);
            									p.add(p3);
            									p.add(p4);
            									p.add(p5);
            									p.add(p6);

            									JPanel pf = new JPanel(fl1);
            									pf.add(p);

            									pf.setSize(300, 400);
            									pf.setBackground(new Color(255, 204, 204));
            									f.setContentPane(pf);
            									 button.addActionListener(new ActionListener() {
            										public void actionPerformed(ActionEvent e) {
            											String n1 = id.getText();
            											String n2 = nomp.getText();
            											String n3 = prenomp.getText();
            											String n4 = nommedicament.getText();
            											String n5 = dosagemed.getText();
            											String n6 = cinp.getText();
            											try {
            												Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            												Statement stmt = c.createStatement();
            												stmt.executeUpdate("insert into ORDONNACE values (" + n1 + ",'" + n2 + "','" + n3 + "','" + n4
            														+ "','" + n5 + "','" + n6 + "')");
            												f.setVisible(false);
            												JOptionPane.showMessageDialog(null, "ordonnance   ajouté");
            												
            											} catch (Exception e1) {
            												System.out.println(e1);
            												f.setVisible(false);
            												JOptionPane.showMessageDialog(null, "ordonnance non ajouté");
            											}
            										}
            									});

            								}
            							});

            item11.addActionListener(new ActionListener() {
            								public void actionPerformed(ActionEvent e) {
            									JFrame f = new JFrame("Modifier patient");
            									f.setSize(300, 400);
            									f.setVisible(true);
            									f.setResizable(false);
            									ImageIcon img = new ImageIcon(GestionCabinet.class.getResource("modifpat.png"));
            									f.setIconImage(img.getImage());
            									Dimension d = new Dimension(100, 35);
            									Font font = new Font("Serif", Font.PLAIN, 20);
            									Font font1 = new Font("Serif", Font.PLAIN, 16);
            									FlowLayout fl1 = new FlowLayout(FlowLayout.LEFT);
            									
            									JButton button = new JButton("submit");
            									 button.setBackground(Color.LIGHT_GRAY);
            								     button.setForeground(Color.white);
            								     button.setFont(new Font("Arial", Font.BOLD, 20));
            								     JComboBox<String> box = new JComboBox<String>();
            								     

            									
            									JTextField nomp = new JTextField(10);
            									nomp.setFont(font1);
            									nomp.setBackground(new Color (255, 240, 240) );
            									JTextField prenomp = new JTextField(10);
            									prenomp.setFont(font1);
            									prenomp.setBackground(new Color (255, 240, 240) );
            									JTextField nommed = new JTextField(10);
            									nommed.setFont(font1);
            									nommed.setBackground(new Color (255, 240, 240) );
            									JTextField dosagemed = new JTextField(10);
            									dosagemed.setFont(font1);
            									dosagemed.setBackground(new Color (255, 240, 240) );
            									JTextField cinp = new JTextField(10);
            									cinp.setFont(font1);
            									cinp.setBackground(new Color (255, 240, 240) );
            									JLabel lblnom = new JLabel("NOMP:");
            									lblnom.setFont(font);
            									lblnom.setLabelFor(nomp);
            									lblnom.setPreferredSize(d);

            									JLabel lblprenom = new JLabel("PRENOMP:");
            									lblprenom.setFont(font);
            									lblprenom.setLabelFor(prenomp);
            									lblprenom.setPreferredSize(d);

            									JLabel lblage = new JLabel("NOMMED");
            									lblage.setFont(font);
            									lblage.setLabelFor(nommed);
            									lblage.setPreferredSize(d);

            									JLabel lbladresse = new JLabel("DOSAGE:");
            									lbladresse.setFont(font);
            									lbladresse.setLabelFor(dosagemed);
            									lbladresse.setPreferredSize(d);

            									JLabel lbltel = new JLabel("CINP:");
            									lbltel.setFont(font);
            									lbltel.setLabelFor(cinp);
            									lbltel.setPreferredSize(d);

            									JPanel p0 = new JPanel(fl1);

            									p0.setBackground(new Color(255, 204, 204));

            									JPanel p1 = new JPanel(fl1);

            									p1.add(lblnom);
            									p1.add(nomp);
            									p1.setBackground(new Color(255, 204, 204));

            									JPanel p2 = new JPanel(fl1);

            									p2.add(lblprenom);
            									p2.add(prenomp);
            									p2.setBackground(new Color(255, 204, 204));

            									JPanel p3 = new JPanel(fl1);

            									p3.add(lblage);
            									p3.add(nommed);
            									p3.setBackground(new Color(255, 204, 204));

            									JPanel p4 = new JPanel(fl1);

            									p4.add(lbladresse);
            									p4.add(dosagemed);
            									p4.setBackground(new Color(255, 204, 204));

            									JPanel p5 = new JPanel(fl1);

            									p5.add(lbltel);
            									p5.add(cinp);
            									p5.setBackground(new Color(255, 204, 204));

            									JPanel p6 = new JPanel(fl1);

            									p6.add(button);
            									p6.setBackground(new Color(255, 204, 204));

            									JPanel p = new JPanel(fl1);
            									Connection c;
            									try {
            										c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            										Statement stmt = c.createStatement();
            										String sql1="select ID from ORDONNACE";
            										ResultSet rs= stmt.executeQuery(sql1);
            										
            										String sql2="select NOMP, PRENOMP ,NOMMEDICAMENT ,DOSAGEMEDICAMENT ,CINP from ORDONNACE where ID =? ";
            										while(rs.next()) {
            											String cin1=rs.getString("ID");
            											box.addItem(cin1);
            											
            										}
            										  box.addItemListener(new ItemListener() {
            										        @Override
            										        public void itemStateChanged(ItemEvent e) {
            										            String tmp = (String)box.getSelectedItem();
            										            try {
            										                PreparedStatement pst = c.prepareStatement(sql2);
            										                pst.setString(1, tmp);
            										                ResultSet rs1 = pst.executeQuery();
            										                if(rs1.next()){
            										                    String nom1=rs1.getString("NOMP");
            										                    nomp.setText(nom1);
            										                    String prenom1=rs1.getString("PRENOMP");
            										                    prenomp.setText(prenom1);
            										                    String age1=rs1.getString("NOMMEDICAMENT");
            										                    nommed.setText(age1);
            										                    String adresse1=rs1.getString("DOSAGEMEDICAMENT");
            										                    dosagemed.setText(adresse1);
            										                    String tel1=rs1.getString("CINP");
            										                    cinp.setText(tel1);
            										                }
            										            } catch (SQLException ex) {
            										                ex.printStackTrace();
            										            }
            										        }

            												
            										    });
            										
            									} catch (SQLException e2) {
            										e2.printStackTrace();
            									}
            									
            									BoxLayout b = new BoxLayout(p, BoxLayout.Y_AXIS);

            									p.setLayout(b);
            									p.add(box);
            									p.add(p0);
            									p.add(p1);
            									p.add(p2);
            									p.add(p3);
            									p.add(p4);
            									p.add(p5);
            									p.add(p6);

            									JPanel pf = new JPanel(fl1);
            									pf.add(p);
            									

            									pf.setSize(300, 400);
            									pf.setBackground(new Color(255, 204, 204));
            									f.setContentPane(pf);
            									 button.addActionListener(new ActionListener() {
            										public void actionPerformed(ActionEvent e) {
            											String n1 = (String) box.getSelectedItem();
            											String n2 = nomp.getText();
            											String n3 = prenomp.getText();
            											String n4 = nommed.getText();
            											String n5 = dosagemed.getText();
            											String n6 = cinp.getText();
            											try {
            												Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            												
            												PreparedStatement stmt = c.prepareStatement("update ORDONNACE set NOMP = ?, PRENOMP=? ,NOMMEDICAMENT = ?,DOSAGEMEDICAMENT =?,CINP= ? where ID=?");
            												stmt.setString(1, n2);
            												stmt.setString(2, n3);
            												stmt.setString(3, n4);
            												stmt.setString(4, n5);
            												stmt.setString(5, n6);
            												stmt.setString(6, n1);
            												int nb = stmt.executeUpdate();
            												
            												f.setVisible(false);
            												JOptionPane.showMessageDialog(null, "ordonnance  modifié");
            												
            											} catch (Exception e1) {
            												System.out.println(e1);
            												f.setVisible(false);
            												JOptionPane.showMessageDialog(null, "ordonnance n\'est pas modifié");
            											}
            										}
            									});

            								}
            							});
            item12.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		JFrame f = new JFrame("Supprimer ordonnace");
            		f.setSize(300, 400);
            		f.setVisible(true);
            		f.setResizable(false);
            		ImageIcon img = new ImageIcon(GestionCabinet.class.getResource("delete.png"));
            		f.setIconImage(img.getImage());
            		Dimension d = new Dimension(100, 35);
            		Font font = new Font("Serif", Font.PLAIN, 20);
            		Font font1 = new Font("Serif", Font.PLAIN, 16);
            		FlowLayout fl1 = new FlowLayout(FlowLayout.LEFT);
            		
            		JButton button = new JButton("submit");
            		 button.setBackground(Color.LIGHT_GRAY);
            	     button.setForeground(Color.white);
            	     button.setFont(new Font("Arial", Font.BOLD, 20));
            	     JComboBox<String> box = new JComboBox<String>();
            	     

            		
            		JTextField nomp = new JTextField(10);
            		nomp.setFont(font1);
            		nomp.setBackground(new Color (255, 240, 240) );
            		nomp.setEditable(false);
            		JTextField prenomp = new JTextField(10);
            		prenomp.setFont(font1);
            		prenomp.setBackground(new Color (255, 240, 240) );
            		prenomp.setEditable(false);
            		JTextField nommed = new JTextField(10);
            		nommed.setFont(font1);
            		nommed.setBackground(new Color (255, 240, 240) );
            		nommed.setEditable(false);
            		JTextField dosagemed = new JTextField(10);
            		dosagemed.setFont(font1);
            		dosagemed.setBackground(new Color (255, 240, 240) );
            		dosagemed.setEditable(false);
            		JTextField cinp = new JTextField(10);
            		cinp.setFont(font1);
            		cinp.setBackground(new Color (255, 240, 240) );
            		cinp.setEditable(false);
            		JLabel lblnom = new JLabel("NOMP:");
            		lblnom.setFont(font);
            		lblnom.setLabelFor(nomp);
            		lblnom.setPreferredSize(d);

            		JLabel lblprenom = new JLabel("PRENOMP:");
            		lblprenom.setFont(font);
            		lblprenom.setLabelFor(prenomp);
            		lblprenom.setPreferredSize(d);

            		JLabel lblage = new JLabel("NOMMED");
            		lblage.setFont(font);
            		lblage.setLabelFor(nommed);
            		lblage.setPreferredSize(d);

            		JLabel lbladresse = new JLabel("DOSAGE:");
            		lbladresse.setFont(font);
            		lbladresse.setLabelFor(dosagemed);
            		lbladresse.setPreferredSize(d);

            		JLabel lbltel = new JLabel("CINP:");
            		lbltel.setFont(font);
            		lbltel.setLabelFor(cinp);
            		lbltel.setPreferredSize(d);

            		JPanel p0 = new JPanel(fl1);

            		p0.setBackground(new Color(255, 204, 204));

            		JPanel p1 = new JPanel(fl1);

            		p1.add(lblnom);
            		p1.add(nomp);
            		p1.setBackground(new Color(255, 204, 204));

            		JPanel p2 = new JPanel(fl1);

            		p2.add(lblprenom);
            		p2.add(prenomp);
            		p2.setBackground(new Color(255, 204, 204));

            		JPanel p3 = new JPanel(fl1);

            		p3.add(lblage);
            		p3.add(nommed);
            		p3.setBackground(new Color(255, 204, 204));

            		JPanel p4 = new JPanel(fl1);

            		p4.add(lbladresse);
            		p4.add(dosagemed);
            		p4.setBackground(new Color(255, 204, 204));

            		JPanel p5 = new JPanel(fl1);

            		p5.add(lbltel);
            		p5.add(cinp);
            		p5.setBackground(new Color(255, 204, 204));

            		JPanel p6 = new JPanel(fl1);

            		p6.add(button);
            		p6.setBackground(new Color(255, 204, 204));

            		JPanel p = new JPanel(fl1);
            		Connection c;
            		try {
            			c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            			Statement stmt = c.createStatement();
            			String sql1="select ID from ORDONNACE";
            			ResultSet rs= stmt.executeQuery(sql1);
            			
            			String sql2="select NOMP, PRENOMP ,NOMMEDICAMENT ,DOSAGEMEDICAMENT ,CINP from ORDONNACE where ID =? ";
            			while(rs.next()) {
            				String cin1=rs.getString("ID");
            				box.addItem(cin1);
            			}
            			
            			  box.addItemListener(new ItemListener() {
            			        @Override
            			        public void itemStateChanged(ItemEvent e) {
            			        	
            			            String tmp = (String)box.getSelectedItem();
            			            try {
            			                PreparedStatement pst = c.prepareStatement(sql2);
            			                pst.setString(1, tmp);
            			                ResultSet rs1 = pst.executeQuery();
            			                if(rs1.next()){
            			                    String nom1=rs1.getString("NOMP");
            			                    nomp.setText(nom1);
            			                   
            			                    String prenom1=rs1.getString("PRENOMP");
            			                    prenomp.setText(prenom1);
            			                    
            			                    String age1=rs1.getString("NOMMEDICAMENT");
            			                    nommed.setText(age1);
            			                   
            			                    String adresse1=rs1.getString("DOSAGEMEDICAMENT");
            			                    dosagemed.setText(adresse1);
            			                    
            			                    String tel1=rs1.getString("CINP");
            			                    cinp.setText(tel1);
            			                
            			                }
            			                
            			               
            			            } catch (SQLException ex) {
            			                ex.printStackTrace();
            			            }
            			        }


            					
            			    });
            			
            		} catch (SQLException e2) {
            			e2.printStackTrace();
            		}
            		
            		BoxLayout b = new BoxLayout(p, BoxLayout.Y_AXIS);

            		p.setLayout(b);
            		p.add(box);
            		p.add(p0);
            		p.add(p1);
            		p.add(p2);
            		p.add(p3);
            		p.add(p4);
            		p.add(p5);
            		p.add(p6);

            		JPanel pf = new JPanel(fl1);
            		pf.add(p);
            		

            		pf.setSize(300, 400);
            		pf.setBackground(new Color(255, 204, 204));
            		f.setContentPane(pf);
            		 button.addActionListener(new ActionListener() {
            			public void actionPerformed(ActionEvent e) {
            				String n1 = (String) box.getSelectedItem();
            				try {
            					Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            					
            					PreparedStatement stmt = c.prepareStatement("delete from ORDONNACE where ID=?");
            					stmt.setString(1, n1);
            					int nb = stmt.executeUpdate();
            					
            					f.setVisible(false);
            					JOptionPane.showMessageDialog(null, "ordonnace  supprime");
            					
            				} catch (Exception e1) {
            					System.out.println(e1);
            					f.setVisible(false);
            					JOptionPane.showMessageDialog(null, "ordonnace n\'est pas supprime");
            				}
            			}
            		});

            	}
            });
            			item13.addActionListener(new ActionListener() {
            			public void actionPerformed(ActionEvent e) {
            			System.exit(0);
            			}});		
            			JLabel imageLabel = new JLabel(new ImageIcon("D:\\Code\\Java\\Projet_arch2tier\\src\\Proj\\1.png"));
            			panel.add(imageLabel);
            			imageLabel.setBounds(0, 30, 700, 300);
            			jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            		
            		menu3.setVisible(false);
            		jf.setVisible(true);
                } else {
                    statusLabel.setText("Invalid username or password.");
                }
            }
        });
        
        frame.setVisible(true);
    }
}