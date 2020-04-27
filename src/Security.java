import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;

public class Security
  extends JDialog
{
  private final JPanel contentPanel = new JPanel();
  
  public static void main(String[] args)
  {
    try
    {
      Security dialog = new Security();
      dialog.setDefaultCloseOperation(2);
      dialog.setVisible(true);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public Security()
  {
    setTitle("Security");
    setResizable(false);
    URL iconURL = getClass().getResource("/icon2.png");
    
    ImageIcon icon = new ImageIcon(iconURL);
    setIconImage(icon.getImage());
    setBounds(490, 100, 437, 417);
    getContentPane().setLayout(new BorderLayout());
    this.contentPanel.setBackground(Color.WHITE);
    this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(this.contentPanel, "Center");
    this.contentPanel.setLayout(null);
    
    JLabel lblNewLabel = new JLabel("New label");
    Image home = new ImageIcon(getClass().getResource("/sec.jpg")).getImage();
    lblNewLabel.setIcon(new ImageIcon(home));
    lblNewLabel.setBounds(0, 0, 421, 355);
    this.contentPanel.add(lblNewLabel);
    
    JPanel buttonPane = new JPanel();
    buttonPane.setBackground(Color.LIGHT_GRAY);
    buttonPane.setLayout(new FlowLayout(2));
    getContentPane().add(buttonPane, "South");
    
    JButton okButton = new JButton("OK");
    okButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Security.this.dispose();
      }
    });
    okButton.setBackground(Color.WHITE);
    okButton.setActionCommand("OK");
    buttonPane.add(okButton);
    getRootPane().setDefaultButton(okButton);
  }
}
