import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window.Type;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;

public class DataBase
  extends JFrame
{
  static Connection dbc = null;
  private JPanel contentPane;
  private static JTable table;
  
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          DataBase frame = new DataBase();
          frame.setVisible(true);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    });
  }
  
  public DataBase()
  {
    //setType(Window.Type.POPUP);
    setTitle("DataBase Status");
    URL iconURL = getClass().getResource("/icon2.png");
    
    ImageIcon icon = new ImageIcon(iconURL);
    setIconImage(icon.getImage());
    setResizable(false);
    setDefaultCloseOperation(2);
    setBounds(500, 100, 432, 462);
    this.contentPane = new JPanel();
    this.contentPane.setBackground(Color.WHITE);
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(this.contentPane);
    this.contentPane.setLayout(null);
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(21, 46, 383, 376);
    this.contentPane.add(scrollPane);
    
    table = new JTable();
    scrollPane.setViewportView(table);
    
    JLabel lblNewLabel = new JLabel("Database Status");
    lblNewLabel.setForeground(new Color(128, 0, 0));
    lblNewLabel.setFont(new Font("Tahoma", 1, 14));
    lblNewLabel.setBounds(148, 11, 123, 22);
    this.contentPane.add(lblNewLabel);
    
    JLabel lblNewLabel_2 = new JLabel("");
    lblNewLabel_2.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e)
      {
        DataBase.this.dispose();
      }
    });
    Image home = new ImageIcon(getClass().getResource("/home.png")).getImage();
    lblNewLabel_2.setIcon(new ImageIcon(home));
    lblNewLabel_2.setBounds(10, 2, 37, 33);
    this.contentPane.add(lblNewLabel_2);
    loadt();
  }
  
  static void loadt()
  {
    dbc = db.dbConnector();
    try
    {
      String query = "select Room,Client,CheckOut,Mobile from Room ";
      PreparedStatement pst = dbc.prepareStatement(query);
      ResultSet rs = pst.executeQuery();
      table.setModel(DbUtils.resultSetToTableModel(rs));
      pst.close();
      rs.close();
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(null, "Failed To load Try Again " + e);
    }
  }
}
