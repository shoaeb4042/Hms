import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;

public class About
  extends JDialog
{
  private final JPanel contentPanel = new JPanel();
  
  public static void main(String[] args)
  {
    try
    {
      About dialog = new About();
      dialog.setDefaultCloseOperation(2);
      dialog.setVisible(true);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public About()
  {
    setTitle("About");
    setResizable(false);
    URL iconURL = getClass().getResource("/icon2.png");
    
    ImageIcon icon = new ImageIcon(iconURL);
    setIconImage(icon.getImage());
    setBounds(490, 100, 450, 372);
    getContentPane().setLayout(new BorderLayout());
    this.contentPanel.setBackground(Color.WHITE);
    this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(this.contentPanel, "Center");
    this.contentPanel.setLayout(null);
    
    JLabel lblNewLabel = new JLabel("HOTEL MANAGEMENT SYSTEM");
    lblNewLabel.setFont(new Font("Tahoma", 1, 15));
    lblNewLabel.setBounds(108, 104, 241, 42);
    this.contentPanel.add(lblNewLabel);
    
    JLabel lblByShoaeb = new JLabel("By Shoaeb");
    lblByShoaeb.setFont(new Font("Tahoma", 1, 15));
    lblByShoaeb.setBounds(184, 136, 100, 42);
    this.contentPanel.add(lblByShoaeb);
    
    JLabel lblNewLabel_1 = new JLabel("System Recuirement: Windows XP/7/8/8.1/10");
    lblNewLabel_1.setFont(new Font("Tahoma", 1, 12));
    lblNewLabel_1.setBounds(95, 180, 298, 33);
    this.contentPanel.add(lblNewLabel_1);
    
    JLabel lblRammb = new JLabel("Ram 128MB,1.5 GHz Processor,JRE/JDK");
    lblRammb.setFont(new Font("Tahoma", 1, 12));
    lblRammb.setBounds(95, 210, 266, 42);
    this.contentPanel.add(lblRammb);
    
    JLabel label = new JLabel("");
    Image hotel = new ImageIcon(getClass().getResource("/resize.png")).getImage();
    label.setIcon(new ImageIcon(hotel));
    label.setFont(new Font("Tahoma", 1, 15));
    label.setBounds(164, 0, 106, 111);
    this.contentPanel.add(label);
    
    JLabel lblForMoreAbout = new JLabel("Know More About Me");
    lblForMoreAbout.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        try
        {
          Desktop.getDesktop().browse(URI.create("https://www.facebook.com/salehin6"));
        }
        catch (IOException localIOException) {}
      }
    });
    Image home = new ImageIcon(getClass().getResource("/fb.png")).getImage();
    lblForMoreAbout.setIcon(new ImageIcon(home));
    lblForMoreAbout.setFont(new Font("Tahoma", 1, 12));
    lblForMoreAbout.setBounds(127, 263, 186, 42);
    this.contentPanel.add(lblForMoreAbout);
    
    JPanel buttonPane = new JPanel();
    buttonPane.setBackground(Color.LIGHT_GRAY);
    buttonPane.setLayout(new FlowLayout(2));
    getContentPane().add(buttonPane, "South");
    
    JButton okButton = new JButton("OK");
    okButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        About.this.dispose();
      }
    });
    okButton.setBackground(Color.WHITE);
    okButton.setActionCommand("OK");
    buttonPane.add(okButton);
    getRootPane().setDefaultButton(okButton);
  }
}
