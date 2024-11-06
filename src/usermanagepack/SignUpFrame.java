package usermanagepack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
public class SignUpFrame extends JDialog
{
    private JTextField txtUid;
    private JPasswordField txtPwd,txtCnfPwd;
    private JButton btnSubmit,btnReset,btnReturn;
    private JComboBox cmbRole;
    private String[] role = {"Admin"};
    private Connection con;
    
    
    private JLabel makeLabel(String s,int x,int y,int w,int h,int mode)
    {
        JLabel temp = new JLabel(s);
        temp.setBounds( x, y, w, h);
	if(mode == 1)
	{
	    Border br1 = BorderFactory.createLineBorder(Color.RED, 2);
	    Border br2 = BorderFactory.createLineBorder(Color.WHITE, 2);
	    Border br3 = BorderFactory.createCompoundBorder(br1, br2);
	    temp.setOpaque( true);
	    temp.setBackground(Color.BLUE);
	    temp.setForeground(Color.WHITE);
	    temp.setBorder(br3);
	    temp.setFont(new Font("Verdana",1,30));
	    temp.setHorizontalAlignment(JLabel.CENTER);
	}
	else if(mode == 2)
	    temp.setFont(new Font("Courier New",1,18));
        add(temp);
        return(temp);
    }
    private JComponent makeTextField(int x,int y,int w,int h,int mode)
    {
        JComponent temp = null;
        if(mode == 1)
            temp = new JTextField();
        else if(mode == 2)
            temp = new JPasswordField();
        temp.setBounds( x, y, w, h);
	temp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        temp.setFont(new Font("Courier New",1,18));
        add(temp);
        return(temp);
    }
    private JComboBox makeComboBox(String s[],int x,int y,int w,int h)
    {
        JComboBox temp = new JComboBox(s);
        temp.setBounds(x, y, w, h);
        temp.setFont(new Font("Courier New",1,18));
        temp.setForeground(Color.BLACK);
        temp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        add(temp);
        return temp;
    }
    private JButton makeButton (String s,int x,int y,int w,int h)
    {
        JButton temp = new JButton(s);
        temp.setBounds( x, y, w, h);
        temp.setOpaque(true);
        temp.setFont(new Font("Courier New",1,16));
	temp.setMargin(new Insets(0,0,0,0));
	temp.addActionListener(new ActionListener()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
                try{
		Object ob = e.getSource();
                if(ob==btnSubmit)
                {
                    String qry = "SELECT USERID FROM USER WHERE USERID = ?";
                    PreparedStatement pst1 = con.prepareStatement(qry);
                    pst1.setString(1,txtUid.getText());
                    ResultSet rst = pst1.executeQuery();
                    if(rst.next())
                    {
                        JOptionPane.showMessageDialog(null,"USER ID ALREADY PRESENT");
                    }
                    
                    else
                    {
                        qry = "INSERT INTO USER VALUES(?,?,?)";
                        PreparedStatement pst2 = con.prepareStatement(qry);
                        pst2.setString(1,txtUid.getText());
                        pst2.setString(2,txtPwd.getText());
                        String role = txtCnfPwd.getSelectedText();
                        if(role==null)role="Admin";
                        pst2.setString(3,role);
                        pst2.executeUpdate();
                        JOptionPane.showMessageDialog(null,"USER REGISTERED");
                    }
                    
                }
                else if(ob==btnReset)
                {
                    
                }
                else if(ob==btnReturn)
                {
                    dispose();
                }
                }
                catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,ex);
            }
           }
	});
        add(temp);
        return(temp);
    }
    public SignUpFrame()
    {
        makeLabel("NEW USER REGISTRATION", 10,10,470,40,1);
        makeLabel("ENTER USER ID", 10,70,250,30,2);
        txtUid = (JTextField)makeTextField(250,70,230,30,1);
        txtUid.setHorizontalAlignment(JTextField.CENTER);
        makeLabel("ENTER PASSWORD", 10,110,250,30,2);
        txtPwd = (JPasswordField)makeTextField(250,110,230,30,2);
        txtPwd.setHorizontalAlignment(JTextField.CENTER);
        txtPwd.setEchoChar('*');
        makeLabel("CONFIRM PASSWORD", 10,150,250,30,2);
        txtCnfPwd = (JPasswordField)makeTextField(250,150,230,30,2);
        txtCnfPwd.setHorizontalAlignment(JTextField.CENTER);
        txtCnfPwd.setEchoChar('*');
        makeLabel("SELECT ROLE/PRIVILEGE", 10,190,250,30,2);
        cmbRole = makeComboBox(role,250,190,230,30);
        btnSubmit = makeButton("Submit",50,230,100,30);
        btnReset = makeButton("Reset",200,230,100,30);
        btnReturn = makeButton("Return",350,230,100,30);
        
        try
        {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project2024","root","150800");
        
        
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        
    }
}
