package usermanagepack;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JRootPane;

public class LoginMain
{
    public static void main(String[] args) 
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project2024","root","150800");
                    
                    
            Toolkit tk = Toolkit.getDefaultToolkit();
            int screenWidth = (int)(tk.getScreenSize().width)/3;
            int screenHeight = (int)tk.getScreenSize().height/3;
            LoginFrame logFrame = new LoginFrame();
            logFrame.setTitle("SIGN IN PANEL");
            logFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            logFrame.setResizable(false);
            logFrame.setSize(screenWidth, screenHeight);
            logFrame.setLocationRelativeTo(null);
            logFrame.getContentPane().setBackground(new Color(250,250,200));
            logFrame.setLayout(new BorderLayout());
            logFrame.setUndecorated(true);
            logFrame.getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG);
            logFrame.setVisible(true);
        } catch (Exception ex) {
            
        }
        
    }
}
