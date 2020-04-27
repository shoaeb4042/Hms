
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Login
  extends JFrame
{
  JLabel wait;
  private JPanel contentPane;
  private JPasswordField pass;
  private JTextField tarikh;
  
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          Login frame = new Login();
          frame.setVisible(true);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    });
  }
  
  public Login()
  {
    URL iconURL = getClass().getResource("/icon2.png");
    
    ImageIcon icon = new ImageIcon(iconURL);
    setIconImage(icon.getImage());
    setTitle("Admin Login");
    setResizable(false);
    setDefaultCloseOperation(3);
    setBounds(350, 100, 648, 466);
    this.contentPane = new JPanel();
    this.contentPane.setBackground(new Color(0, 0, 51));
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(this.contentPane);
    this.contentPane.setLayout(null);
    
    JLabel label_1 = new JLabel("");
    Image logo1 = new ImageIcon(getClass().getResource("/i3.png")).getImage();
    label_1.setIcon(new ImageIcon(logo1));
    label_1.setBounds(88, 81, 180, 187);
    this.contentPane.add(label_1);
    
    JPanel panel = new JPanel();
    panel.setBounds(277, 164, 180, 25);
    this.contentPane.add(panel);
    panel.setLayout(null);
    
    this.pass = new JPasswordField();
    this.pass.setBounds(0, 0, 180, 25);
    panel.add(this.pass);
    this.pass.setDropMode(DropMode.INSERT);
    
    JLabel lblLocked = new JLabel("Locked");
    lblLocked.setForeground(new Color(255, 255, 204));
    lblLocked.setFont(new Font("Tahoma", 1, 15));
    lblLocked.setBounds(277, 131, 65, 25);
    this.contentPane.add(lblLocked);
    Image img2 = new ImageIcon(getClass().getResource("/Ad.png")).getImage();
    
    JLabel lblNewLabel = new JLabel("Exit");
    lblNewLabel.setForeground(new Color(255, 250, 250));
    lblNewLabel.setFont(new Font("Tahoma", 1, 15));
    lblNewLabel.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        System.exit(0);
      }
    });
    Image img = new ImageIcon(getClass().getResource("/Power.png")).getImage();
    lblNewLabel.setIcon(new ImageIcon(img));
    lblNewLabel.setBounds(531, 355, 89, 57);
    this.contentPane.add(lblNewLabel);
    
    JLabel lblNewLabel_1 = new JLabel("Clear");
    lblNewLabel_1.setForeground(new Color(255, 255, 255));
    lblNewLabel_1.setFont(new Font("Tahoma", 1, 14));
    lblNewLabel_1.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e)
      {
        Login.this.pass.setText(null);
      }
    });
    Image re = new ImageIcon(getClass().getResource("/Reload.png")).getImage();
    lblNewLabel_1.setIcon(new ImageIcon(re));
    lblNewLabel_1.setBounds(312, 200, 113, 64);
    this.contentPane.add(lblNewLabel_1);
    
    JLabel lblNewLabel_2 = new JLabel("Login");
    lblNewLabel_2.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e)
      {
        Login.this.wait.setText("Please Wait........");
        if ((Login.this.pass.getText().equals("Shoaeb")) || (Login.this.pass.getText().equals("shoaeb")) || (Login.this.pass.getText().equals("admin")))
        {
          new Menu().setVisible(true);
          Login.this.dispose();
        }
        else
        {
          Login.this.wait.setText(null);
          JOptionPane.showMessageDialog(null, "Password is incorrect ");
        }
      }
    });
    lblNewLabel_2.setForeground(new Color(255, 255, 255));
    lblNewLabel_2.setFont(new Font("Tahoma", 1, 15));
    Image img2w = new ImageIcon(getClass().getResource("/log.png")).getImage();
    lblNewLabel_2.setIcon(new ImageIcon(img2w));
    lblNewLabel_2.setBounds(479, 153, 98, 48);
    this.contentPane.add(lblNewLabel_2);
    
    this.tarikh = new JTextField();
    this.tarikh.setForeground(new Color(255, 255, 255));
    this.tarikh.setFont(new Font("Tahoma", 1, 12));
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    
    this.tarikh.setText("  " + dateFormat.format(date));
    this.tarikh.setBackground(new Color(0, 100, 0));
    this.tarikh.setEditable(false);
    this.tarikh.setBounds(432, 11, 158, 25);
    this.contentPane.add(this.tarikh);
    this.tarikh.setColumns(10);
    
    JLabel lblCode = new JLabel("� 2015 Hotel Management System By Shoaeb");
    lblCode.setForeground(Color.BLACK);
    lblCode.setFont(new Font("Tahoma", 1, 12));
    lblCode.setBounds(171, 401, 286, 25);
    this.contentPane.add(lblCode);
    
    JLabel bgd = new JLabel("New label");
    Image img24 = new ImageIcon(getClass().getResource("/bg1.jpg")).getImage();
    
    this.wait = new JLabel("");
    this.wait.setForeground(new Color(255, 255, 204));
    this.wait.setFont(new Font("Tahoma", 1, 30));
    this.wait.setBounds(230, 288, 270, 72);
    this.contentPane.add(this.wait);
    bgd.setIcon(new ImageIcon(img24));
    bgd.setBounds(0, 0, 642, 437);
    this.contentPane.add(bgd);
  }
}
