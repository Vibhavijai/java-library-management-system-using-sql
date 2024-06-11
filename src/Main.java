import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JPanel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Main {
    JLabel l0,l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11 ;
    JTextField tf1,tf2,tf3,tf4,tf5,tf6,tf7,tf8,tf9;
    JButton b1;
    JTextArea ta;
    JButton btn1;
    String copies;
    public JLabel nameLabel,nameField,emailLabel,emailField,phoneLabel;
    JTextField phoneField;
    JButton save;
    Connection connection;

    Main(String usr)
    {

        JFrame f1=new JFrame("LIBRARY");
        JButton b1=new JButton("Add_Book");
        b1.setBounds(250,150,100,30);
        f1.add(b1);
        JButton b2=new JButton("Take_Book");
        b2.setBounds(250,300,100,30);
        f1.add(b2);
        f1.getContentPane().setBackground(Color.CYAN);
        f1.setSize(500,500);
        ImageIcon background_ima = new ImageIcon("C://Users//vibha//Take.jpg");
        Image img1 = background_ima.getImage();
        Image temp_img = img1.getScaledInstance(1920,1080,Image.SCALE_SMOOTH);
        background_ima = new ImageIcon(temp_img);
        JLabel background1 = new JLabel("",background_ima,JLabel.CENTER);
        background1.setBounds(0,0,1920,1080);
        f1.add(background1);
        f1.setLayout(null);
        f1.setVisible(true);

        JFrame f2=new JFrame("ADDING BOOKS");

        JFrame f3=new JFrame("TAKING BOOK");

        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent a)
            {
//f1.setVisible(false);

                l1=new JLabel("BOOK NAME");
                l1.setBounds(50,100,120,50);
                f2.add(l1);

                tf1=new JTextField();
                tf1.setBounds(190,110,100,20);
                f2.add(tf1);

                l2=new JLabel("AUTHOR NAME");
                l2.setBounds(50,140,120,50);
                f2.add(l2);


                tf2=new JTextField();
                tf2.setBounds(190,150,100,20);
                f2.add(tf2);

                l3=new JLabel("PRICE");
                l3.setBounds(50,180,120,50);
                f2.add(l3);


                tf3=new JTextField();
                tf3.setBounds(190,190,100,20);
                f2.add(tf3);

                l4=new JLabel("COPIES");
                l4.setBounds(50,220,120,50);
                f2.add(l4);


                tf4=new JTextField();
                tf4.setBounds(190,230,100,20);
                f2.add(tf4);

                l5=new JLabel("PAGESs");
                l5.setBounds(50,260,120,50);
                f2.add(l5);


                tf5=new JTextField();
                tf5.setBounds(190,270,100,20);
                f2.add(tf5);

                l6=new JLabel("JOURNAL");
                l6.setBounds(50,300,120,50);
                f2.add(l6);
                String j[]={"FICTION","NON FICTION","NOVEL","SCIENCE","MYSTERY","THRILLER"};
                JComboBox<String>  c=new JComboBox<String>(j);
                c.setBounds(190,300,120,30);
                f2.add(c);

                JTextField  tf6=new JTextField();
                tf6.setBounds(50,300,120,50);
                f2.add(tf6);


                f2.setVisible(true);
                l0=new JLabel("LIBRARY MANAGEMENT SYSTEM");
                l0.setBounds(350,50,250,50);
                f2.add(l0);


                btn1=new JButton("ADD BOOK");
                btn1.setBounds(450,450,190,20);
                f2.add(btn1);

                btn1.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        String bname=tf1.getText();
                        String aname=tf2.getText();
                        String journal=tf2.getText();
                        String price=tf3.getText();
                        copies=tf4.getText();
                        String pages=tf5.getText();

                            String databaseURL ="jdbc:ucanaccess://C://Users//vibha//Desktop//Library1.accdb";

                            try
                            {

                                Connection connection = DriverManager.getConnection(databaseURL);
                                String sql = "INSERT INTO Table1 (BName,Author,Price,Copies,Pages,Journal) VALUES (?,?,?,?,?,?)";
                                PreparedStatement ps = connection.prepareStatement(sql);


                                ps.setString(1,bname );
                                ps.setString(2,aname );
                                ps.setString(3,price );
                                ps.setString(4,copies );
                                ps.setString(5,pages);
                                ps.setString(6,journal);
                                ps.executeUpdate();
                                ps.close();

                                String sql1 = "INSERT INTO Book (Bookname) VALUES (?)";
                                PreparedStatement ps1 = connection.prepareStatement(sql1);

                                ps1.setString(1,bname );
                                ps1.executeUpdate();
                                ps1.close();

                                String sql2 = "INSERT INTO BookDetails (auname,price,copies,pages) VALUES (?,?,?,?)";
                                PreparedStatement ps2 = connection.prepareStatement(sql2);

                                ps2.setString(1,aname );
                                ps2.setString(2,price );
                                ps2.setString(3,copies );
                                ps2.setString(4,pages);
                                ps2.executeUpdate();
                                ps2.close();

                                String sql3= "create index bn on Book(Bookname)";
                                PreparedStatement ps3 = connection.prepareStatement(sql3);
                                ps3.executeUpdate();

                            }


                            catch(SQLException ex)
                        {
                            ex.printStackTrace();
                        }
                        JOptionPane.showMessageDialog(f2, "Book added successfully");

                    }
                });

                f2.getContentPane().setBackground(Color.CYAN);
                f2.setSize(900,900);
                f2.setLayout(null);
                f2.setVisible(true);
            }
        });
        b2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae){
//f1.setVisible(false);
                JFrame f3=new JFrame("TAKING BOOK");
                JLabel ll1=new JLabel("BOOK NAME");
                JTextField  t1=new JTextField();
                t1.setBounds(200,100,120,50);
                f3.add(t1);
                JButton bb1=new JButton("Take Book");
                bb1.setBounds(100,160,120,50);
                f3.add(bb1);
                ll1.setBounds(50,100,120,50);
                f3.add(ll1);
                bb1.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent ee) {
                        try {
                            String book = t1.getText();
                            Connection connection=DriverManager.getConnection("jdbc:ucanaccess://C://Users//vibha//Desktop//Library1.accdb");
                            PreparedStatement p = connection.prepareStatement("SELECT * from Table1 where BName=?");
                            p.setString(1,book);
                            ResultSet rs = p.executeQuery();
                            rs.next();
                            String Bnam=rs.getString(1);
                            String copy=rs.getString(4);
                            p.close();
                            if (Bnam.equalsIgnoreCase(book))
                            {
                                int temp=Integer.parseInt(copy);
                                temp=temp-1;
                                String temp2=new String(String.valueOf(temp));
                                PreparedStatement p1= connection.prepareStatement("UPDATE Table1 SET Copies=? WHERE BName=?");
                                p1.setString(1,temp2);
                                p1.setString(2,Bnam);
                                p1.executeUpdate();
                                p1.close();
                                JOptionPane.showMessageDialog(f3, "You can take the book and return within 15 days...");
                                takebook(usr,Bnam);
                            }
                        } catch (SQLException ex)
                        {
                            JOptionPane.showMessageDialog(f3, "Sorry :(( book is not available");
                            ex.printStackTrace();
                        }
                    }
                });

                f3.setSize(500,500);
                ImageIcon background_imag = new ImageIcon("C://Users//vibha//add.jpg");
                Image img1 = background_imag.getImage();
                Image temp_img = img1.getScaledInstance(1920,1080,Image.SCALE_SMOOTH);
                background_imag = new ImageIcon(temp_img);
                JLabel background2 = new JLabel("",background_imag,JLabel.CENTER);
                background2.setBounds(0,0,1920,1080);
                f3.add(background2);
                f3.setLayout(null);
                f3.setVisible(true);
            }
        });
        f1.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });

        f2.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        f3.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
    }
    static void takebook(String usr,String bname) {
        JFrame f5=new JFrame("User INFORMATION");
        JLabel b = new JLabel("Book Name: ");
        JTextField bb = new JTextField(20);
        JLabel nl = new JLabel("Name:");
        JTextField nf = new JTextField(20);
        JLabel eL = new JLabel("Email:");
        JTextField ef = new JTextField(20);
        JLabel deptl = new JLabel("Dept:");
        JTextField deptf = new JTextField(20);
        JLabel yearl = new JLabel("Year:");
        JTextField yearf=new JTextField(20);
        b.setBounds(100,60,120,50);
        bb.setBounds(230,70,100,20);
        nl.setBounds(50,100,120,50);
        nf.setBounds(190,110,100,20);

        eL.setBounds(50,140,120,50);
        ef.setBounds(190,150,100,20);

        yearl.setBounds(50,180,120,50);
        yearf.setBounds(190,190,100,20);

        deptl.setBounds(50,220,120,50);
        deptf.setBounds(190,230,100,20);

        f5.add(b);
        f5.add(bb);
        f5.add(nl);
        f5.add(nf);
        f5.add(eL);
        f5.add(ef);
        f5.add(deptl);
        f5.add(deptf);
        f5.add(yearl);
        f5.add(yearf);

        f5.getContentPane().setBackground(Color.PINK);
        f5.setSize(500,500);
        f5.setLayout(null);
        f5.setVisible(true);
        String databaseURL ="jdbc:ucanaccess://C://Users//vibha//Desktop//Library1.accdb";
        try
        {

            Connection connection = DriverManager.getConnection(databaseURL);
            String sql = "INSERT INTO Take_book (Name,Bname) VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,usr);
            ps.setString(2,bname);
            ps.executeUpdate();
            ps.close();
            try
            {
                String sql1 = "Select * from UserDetails join Take_book on Take_book.Name=UserDetails.Name  where Take_book.BName=?";
                PreparedStatement ps1 = connection.prepareStatement(sql1);
                ps1.setString(1,bname);
                ResultSet rs = ps1.executeQuery();
                while (rs.next()) {
                    String a = rs.getString("Name");
                    String f = rs.getString("email");
                    String c = rs.getString("dept");
                    String d = rs.getString("year");
                    bb.setText(bname);
                    nf.setText(a);
                    ef.setText(f);
                    deptf.setText(c);
                    yearf.setText(d);
                    bb.setEditable(false);
                    nf.setEditable(false);
                    ef.setEditable(false);
                    deptf.setEditable(false);
                    yearf.setEditable(false);
                }
                ps1.close();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }

    }
  static void view(JFrame f4) {
        String databaseURL = "jdbc:ucanaccess://C://Users//vibha//Desktop//Library1.accdb";

        try {

            Connection connection = DriverManager.getConnection(databaseURL);
            String sql = "create view My_view as select * from Table1";
            PreparedStatement ps = connection.prepareStatement(sql);
            JOptionPane.showMessageDialog(f4,"View created successfully");
            ps.executeUpdate();

            ps.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    static void signup()
    {


        JFrame f4=new JFrame("User Details");
        JLabel nl = new JLabel("Name:");
        JTextField nf = new JTextField(20);
        JLabel eL = new JLabel("Email:");
        JTextField ef = new JTextField(20);
        JLabel pl = new JLabel("Phone:");
        JTextField pf = new JTextField(20);
        JLabel deptl = new JLabel("Dept:");
        JTextField deptf = new JTextField(20);
        JLabel yearl = new JLabel("Year:");
        JTextField yearf=new JTextField(20);
        nl.setBounds(50,100,120,50);
        nf.setBounds(190,110,100,20);

        eL.setBounds(50,140,120,50);
        ef.setBounds(190,150,100,20);

        pl.setBounds(50,180,120,50);
        pf.setBounds(190,190,100,20);

        deptl.setBounds(50,220,120,50);
        deptf.setBounds(190,230,100,20);
        JButton save=new JButton("Save");

        nl.setBounds(50,100,120,50);
        nf.setBounds(190,110,100,20);

        eL.setBounds(50,140,120,50);
        ef.setBounds(190,150,100,20);

        pl.setBounds(50,180,120,50);
        pf.setBounds(190,190,100,20);

        deptl.setBounds(50,220,120,50);
        deptf.setBounds(190,230,100,20);

        yearl.setBounds(50,260,120,50);
        yearf.setBounds(190,270,100,20);

        save.setBounds(250,400,100,30);
        f4.add(nl);
        f4.add(nf);
        f4.add(eL);
        f4.add(ef);
        f4.add(pf);
        f4.add(pl);
        f4.add(deptl);
        f4.add(deptf);
        f4.add(yearl);
        f4.add(yearf);
        f4.add(save);
        f4.setSize(500,500);
        ImageIcon background_ima = new ImageIcon("C://Users//vibha//login.jpg");
        Image img1 = background_ima.getImage();
        Image temp_img = img1.getScaledInstance(1920,1080,Image.SCALE_SMOOTH);
        background_ima = new ImageIcon(temp_img);
        JLabel background1 = new JLabel("",background_ima,JLabel.CENTER);
        background1.setBounds(0,0,1920,1080);
        f4.add(background1);
        f4.setLayout(null);

        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String name=nf.getText();
                String mail=ef.getText();
                String phone=pf.getText();
                String dept=deptf.getText();
                String year=yearf.getText();

                String databaseURL ="jdbc:ucanaccess://C://Users//vibha//Desktop//Library1.accdb";

                try
                {

                    Connection connection = DriverManager.getConnection(databaseURL);
                    String sql = "INSERT INTO UserDetails (Name,email,dept,year) VALUES (?,?,?,?)";
                    String sql1= "INSERT INTO ContactDetails (Name,phone) VALUES (?,?)";
                    PreparedStatement ps = connection.prepareStatement(sql);
                    PreparedStatement ps1 = connection.prepareStatement(sql1);
                    ps.setString(1,name);
                    ps.setString(2,mail);
                    ps.setString(3,dept);
                    ps.setString(4,year);
                    ps1.setString(1,name);
                    ps1.setString(2,phone);
                    ps.executeUpdate();
                    ps.close();
                    ps1.executeUpdate();
                    ps1.close();
                /*    try
                    {
                        String sql2= "Select count(*) as c from Table2";
                        PreparedStatement ps2 = connection.prepareStatement(sql);
                        ResultSet rs = ps2.executeQuery(sql2);
                        rs.next();
                        int count = rs.getInt("c");
                        System.out.println("Number of users: " + count);
                        ps2.close();

                    }
                    catch(SQLException ex)
                    {
                        ex.printStackTrace();
                    }*/

                   view(f4);

                }

                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }

            }
        });
        f4.setVisible(true);


    }

    public static void main(String[] args) {

        //  private static final long serialVersionUID = 1L;
        final String[] usr = new String[1];
        final String[] pass = new String[1];
        JButton btnNewButton;
        JFrame fr=new JFrame("LOGIN PAGE");
        JLabel lb= new JLabel("LOGIN");
        lb.setBounds(100,50,100,30);
        fr.add(lb);

        JLabel lb1= new JLabel("UserName");
        lb1.setBounds(50,150,100,30);
        fr.add(lb1);

        JTextField tt1=new JTextField();
        tt1.setBounds(170,150,100,30);
        fr.add(tt1);

        JLabel lb2= new JLabel("Password");
        lb2.setBounds(50,300,100,30);
        fr.add(lb2);

        JPasswordField tt2=new JPasswordField();
        tt2.setBounds(170,300,100,30);
        fr.add(tt2);

        btnNewButton = new JButton("Login");
        btnNewButton.setBounds(100, 350, 100, 35);
        fr.add(btnNewButton);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userName = tt1.getText();
                String password = String.valueOf(tt2.getPassword());
                try {
                    Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C://Users//vibha//Desktop//Library1.accdb");
                    PreparedStatement st = connection.prepareStatement("SELECT * FROM Table2 WHERE Username=? OR Password=?");
                    String sql = "select count(*) as c from Table2";
                   Statement ps = connection.createStatement();
                    ResultSet rs2 = ps.executeQuery(sql);
                    rs2.next();
                    int count=rs2.getInt("c")+1;
                    st.setString(1, userName);
                    st.setString(2, password);
                    ResultSet rs1 = st.executeQuery();
                    rs1.next();
                    usr[0] = rs1.getString(1);
                    pass[0] = rs1.getString(2);
                    if (pass[0].equalsIgnoreCase(password) && usr[0].equalsIgnoreCase(userName)) {
                        JOptionPane.showMessageDialog(btnNewButton, "You are one of the "+count+" user to  log in");
                        new Main(userName );
                    }
                    else if (usr[0].equalsIgnoreCase(userName) && pass[0].equalsIgnoreCase(password)==false) {
                        JOptionPane.showMessageDialog(btnNewButton, "Password is Incorrect");
                    }

                }
                catch (SQLException sqlException)
                {
                    String databaseURL = "jdbc:ucanaccess://C://Users//vibha//Desktop//Library1.accdb";

                    try
                    {
                        Connection con = DriverManager.getConnection(databaseURL);
                        String sql = "INSERT INTO Table2 (Username,Password) VALUES (?,?)";
                        String sql1 = "INSERT INTO UserName (UserName) VALUES (?)";
                        String sql2 = "INSERT INTO Password (Password) VALUES (?)";
                        PreparedStatement ps1 = con.prepareStatement(sql);
                        PreparedStatement ps2 = con.prepareStatement(sql1);
                        PreparedStatement ps3 = con.prepareStatement(sql2);
                        ps1.setString(1, userName);
                        ps1.setString(2, password);
                        ps1.executeUpdate();
                        ps1.close();
                        ps2.setString(1, userName);
                        ps2.executeUpdate();
                        ps2.close();
                        ps3.setString(1, password);
                        ps3.executeUpdate();
                        ps3.close();
                        JOptionPane.showMessageDialog(btnNewButton,"New Username & Password added");
                        signup();
                       // view();
 }
                    catch (SQLException x)
                    {
                        x.printStackTrace();
                    }
                    sqlException.printStackTrace();
                }
            }
        });

        fr.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });

        fr.setSize(500,500);
        ImageIcon background_image = new ImageIcon("C://Users//vibha//login.jpg");
        Image img1 = background_image.getImage();
        Image temp_img = img1.getScaledInstance(1920,1080,Image.SCALE_SMOOTH);
        background_image = new ImageIcon(temp_img);
        JLabel background1 = new JLabel("",background_image,JLabel.CENTER);
        background1.setBounds(0,0,1920,1080);
        fr.add(background1);
        fr.setLayout(null);
        fr.setVisible(true);
    }
}