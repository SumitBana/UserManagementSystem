package usermanagepack;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class LoginFrame extends JFrame
{
    private JComboBox cmbRole;
    private JTextField txtUid;
    private JPasswordField txtPwd;
    private JButton btnSignup,btnSignin,btnReset,btnExit;
    private String[] role = {"Select Your Role","Admin","Professor","Student"};
    public JLabel makeLabel(String s,int x,int y,int w,int h,int mode)
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
	{
	    temp.setFont(new Font("Courier New",1,18));
	}
        add(temp);
        return(temp);
    }
    public JComponent makeTextField(int x,int y,int w,int h,int mode)
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
    public JButton makeButton (String s,int x,int y,int w,int h)
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
		Object ob = e.getSource();
                if(ob == btnSignup)
                {
                    SignUpFrame signFrame = new SignUpFrame();
                    signFrame.setTitle("SIGN UP PANEL...");
                    signFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    signFrame.setResizable(false);
                    signFrame.setModal(true);
                    signFrame.setSize(500, 300);
                    signFrame.setLocationRelativeTo(null);
                    signFrame.getContentPane().setBackground(new Color(250,250,200));
                    signFrame.setLayout(new BorderLayout());
                    signFrame.setUndecorated(true);
                    signFrame.getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG);
                    signFrame.setVisible(true);
                }
                else if(ob == btnExit)
                {
                    System.exit(0);
                }
                
	    }
	});
        add(temp);
        return(temp);
    }
    public LoginFrame()
    {
           makeLabel("ON BOARD LOGIN",10,10,610,60,1 );
           makeLabel("SELECT ROLE/PRIVILEDGE",30,80,250,40,2 );
           cmbRole = makeComboBox(role,310, 80, 280,40);
           makeLabel("ENTER USER ID",30,135,250,40,2 );
           txtUid = (JTextField)makeTextField(310,135,280,40,1);
           txtUid.setHorizontalAlignment(JTextField.CENTER);
           makeLabel("ENTER PASSWORD", 30,190,250,40,2);
           txtPwd = (JPasswordField)makeTextField(310,190,280,40,2);
           txtPwd.setHorizontalAlignment(JTextField.CENTER);
           txtPwd.setEchoChar('*');
           
           btnSignup = makeButton("Sign Up", 30,250,120,40);
           btnSignin = makeButton("Sign In",175,250,120,40);
           btnReset  = makeButton("Reset",  325,250,120,40);
           btnExit   = makeButton("Exit",   470,250,120,40);
    }
}
