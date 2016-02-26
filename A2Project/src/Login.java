import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener
{
    JLabel l1, l2, l3;
    JTextField tf1;
    JButton btn1;
    JPasswordField p1;
    
    Login()
    {
        setTitle("Login Page");
        setSize(600, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        l1 = new JLabel("Exton Exotic Plants");
        l1.setForeground(Color.blue);
        l1.setFont(new Font("Serif", Font.BOLD, 20));

        l2 = new JLabel("Username:");
        l3 = new JLabel("Password:");
        tf1 = new JTextField();
        p1 = new JPasswordField();
        btn1 = new JButton("Login");

        l1.setBounds(100, 30, 400, 30);
        l2.setBounds(100, 70, 120, 30);
        l3.setBounds(100, 110, 120, 30);
        tf1.setBounds(200, 70, 200, 30);
        p1.setBounds(200, 110, 200, 30);
        btn1.setBounds(150, 160, 100, 30);

        add(l1);
        add(l2);
        add(tf1);
        add(l3);
        add(p1);
        add(btn1);
        setVisible(true);
        btn1.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        showData();
    }

    public void showData()
    {
        Boolean connectError = false;   // Error flag
        Connection DBConn = null;       // MySQL connection handle   
        Boolean executeError = false;   // Error flag
        String errString = null;        // String for displaying errors    
        Boolean fieldError = false;     // Error flag
        String msgString = null;        // String for displaying non-error messages
        ResultSet res = null;           // SQL query result set pointer       
        java.sql.Statement s, st = null;    // SQL statement pointer
        String SQLstatement = null;     // String for building SQL queries
        
        
        try
            {
                msgString = ">> Establishing Driver...";                
                Class.forName( "com.mysql.jdbc.Driver" );
                msgString = ">> Setting up URL...";               
                String sourceURL = "jdbc:mysql://localhost:3306/leaftech";
                msgString = ">> Establishing connection with: " + sourceURL + "...";                
                DBConn = DriverManager.getConnection(sourceURL,"remote","remote_pass");

            } catch (Exception e) {

                errString =  "\nProblem connecting to database:: " + e;                
                connectError = true;

            }
         if (!connectError && !fieldError )
        {
            try
            {
                s = DBConn.createStatement();
                String str1 = tf1.getText();
                char[] p = p1.getPassword();
                String str2 = new String(p);
                res = s.executeQuery("Select userid from users.users where username='"+str1+"' and password='"+str2+"'");
                if (res.next())                
                {
                    st = DBConn.createStatement();
                    st.executeUpdate("insert into users.loginactivity (userid, timein) values ("+res.getInt(1)+",CURRENT_TIMESTAMP)");                   
                    this.setVisible(false);
                    new Home(res.getInt(1));
                } else
                {
                    JOptionPane.showMessageDialog(null,
                       "Incorrect username or password..Try Again with correct detail");
                    tf1.setText("");
                    p1.setText("");
                }
            }catch (Exception e) {

                errString =  "\nProblem with insert:: " + e;
               // jTextArea1.append(errString);
                executeError = true;

            }
         }
       
                   
            
        
    }

    public static void main(String arr[])
    {
        new Login();
    }
}