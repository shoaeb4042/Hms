import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Map
  extends JDialog
{
  private final JPanel contentPanel = new JPanel();
  
  public static void main(String[] args)
  {
    try
    {
      Map dialog = new Map();
      dialog.setDefaultCloseOperation(2);
      dialog.setVisible(true);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public Map()
  {
    setTitle("Location");
    setResizable(false);
    setBounds(490, 100, 456, 429);
    getContentPane().setLayout(new BorderLayout());
    this.contentPanel.setBackground(Color.WHITE);
    this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(this.contentPanel, "Center");
    this.contentPanel.setLayout(null);
    
    JLabel lblNewLabel = new JLabel("New label");
    Image home = new ImageIcon(getClass().getResource("/img.png")).getImage();
    
    JLabel lblNewLabel_1 = new JLabel("Ok");
    lblNewLabel_1.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        Map.this.dispose();
      }
    });
    Image ok = new ImageIcon(getClass().getResource("/down.png")).getImage();
    lblNewLabel_1.setIcon(new ImageIcon(ok));
    lblNewLabel_1.setFont(new Font("Tahoma", 1, 15));
    lblNewLabel_1.setForeground(Color.WHITE);
    lblNewLabel_1.setBounds(10, 353, 58, 47);
    this.contentPanel.add(lblNewLabel_1);
    
    JLabel mapp = new JLabel("");
    mapp.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        String d = "https://www.google.com.bd/maps/place/Toyenbee+Circular+Rd,+Dhaka/@23.7318588,90.4208051,17z/data=!3m1!4b1!4m2!3m1!1s0x3755b85b11ce8f6f:0x92f78b3e2bcbd1f7";
        try
        {
          Desktop.getDesktop().browse(URI.create(d));
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }
    });
    mapp.setBounds(334, 283, 116, 117);
    this.contentPanel.add(mapp);
    lblNewLabel.setIcon(new ImageIcon(home));
    lblNewLabel.setBounds(0, 0, 450, 400);
    this.contentPanel.add(lblNewLabel);
  }
}
