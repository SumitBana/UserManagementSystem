package usermanagepack;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;

public class LoginMain
{
    public static void main(String[] args) 
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project2024","root","150800");
            DatabaseMetaData metadata = con.getMetaData();
            ResultSet result = metadata.getTables("project2024","root","USER",new String[]{"TABLE"});
            Statement sst = con.createStatement();
            if(!result.next())
            {
                String sql = "CREATE TABLE USER(USERID VARCHAR(20) PRIMARY KEY,PASSWORD VARCHAR(20),ROLE VARCHAR(9))";
                sst.executeUpdate(sql);
                sql = "INSERT INTO USER VALUES('admin','admin','Admin')";
                sst.executeUpdate(sql);
                sql = "CREATE TABLE STUDENT_MASTER(STUDENT_ID VARCHAR(15) PRIMARY KEY, NAME VARCHAR(20),FATHER_NAME VARCHAR(20),GENDER VARCHAR(6),ADDRESS VARCHAR(50),DOB DATE,PHONE VARCHAR(12),EMAIL VARCHAR(30),COURSE VARCHAR(5),SEMESTERS CHAR(1))";
                sst.executeUpdate(sql);
                sql = "CREATE TABLE PROFESSOR_MASTER(PROFESSOR_ID VARCHAR(13) PRIMARY KEY,NAME VARCHAR(20),ADDRESS VARCHAR(50),GENDER VARCHAR(6),PHONE VARCHAR(12),EMAIL VARCHAR(30),DOB DATE,DOJ DATE)";
                sst.executeUpdate(sql);
                sql = "CREATE TABLE PROFESSOR_DEGREE(PROFESSOR_ID VARCHAR(13),DEGREE VARCHAR(10),PRIMARY KEY (PROFESSOR_ID,DEGREE), FOREIGN KEY(PROFESSOR_ID) REFERENCES PROFESSOR_MASTER(PROFESSOR_ID)ON DELETE CASCADE)";
                sst.executeUpdate(sql);
                sql = "CREATE TABLE STUDENT_GRADE(STUDENT_ID VARCHAR(15),SEMESTER CHAR(1), GRADE CHAR(1), PRIMARY KEY(STUDENT_ID,SEMESTER),FOREIGN KEY(STUDENT_ID) REFERENCES STUDENT_MASTER(STUDENT_ID) ON DELETE CASCADE)";
                sst.executeUpdate(sql);
                
            }
            con.close();
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
                        JOptionPane.showMessageDialog(null, ex);

        }
        
    }
}
