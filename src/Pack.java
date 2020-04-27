import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;

public class Pack
  extends JDialog
{
  private final JPanel contentPanel = new JPanel();
  
  public static void main(String[] args)
  {
    try
    {
      Pack dialog = new Pack();
      dialog.setDefaultCloseOperation(2);
      dialog.setVisible(true);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public Pack()
  {
    setTitle("Package");
    setResizable(false);
    setBounds(500, 100, 403, 359);
    getContentPane().setLayout(new BorderLayout());
    this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(this.contentPanel, "Center");
    this.contentPanel.setLayout(null);
    
    JLabel lblNewLabel = new JLabel("New label");
    Image home = new ImageIcon(getClass().getResource("/package.jpg")).getImage();
    lblNewLabel.setIcon(new ImageIcon(home));
    lblNewLabel.setBounds(0, 0, 400, 297);
    this.contentPanel.add(lblNewLabel);
    
    JPanel buttonPane = new JPanel();
    buttonPane.setBackground(Color.BLACK);
    buttonPane.setLayout(new FlowLayout(2));
    getContentPane().add(buttonPane, "South");
    
    JButton okButton = new JButton("OK");
    
    okButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Pack.this.dispose();
      }
    });
    okButton.setForeground(Color.WHITE);
    okButton.setBackground(Color.BLACK);
    okButton.setActionCommand("OK");
    buttonPane.add(okButton);
    getRootPane().setDefaultButton(okButton);
  }
}
