import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class Menu extends JFrame
{
  private JPanel contentPane;
  private static JTable table;
  int load = 1;
  JTextArea dispaly;
  static int tf = 0;
  static int vf = 0;
  static int lf = 0;
  static int gf = 0;
  
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          Menu frame = new Menu();
          frame.setVisible(true);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    });
  }
  
  static Connection dbc = null;
  private static JTextField room1;
  private static JTextField name1;
  private static JTextField pack1;
  private JTextField textDate;
  private JTextField gogle;
  
  public Menu()
  {
    dbc = db.dbConnector();
    
    URL iconURL = getClass().getResource("/icon2.png");
    
    ImageIcon icon = new ImageIcon(iconURL);
    setIconImage(icon.getImage());
    setTitle("Control Panel");
    setResizable(false);
    setDefaultCloseOperation(3);
    setBounds(350, 100, 720, 469);
    
    JMenuBar menuBar = new JMenuBar();
    menuBar.setBackground(Color.WHITE);
    setJMenuBar(menuBar);
    
    JMenu mnNewMenu = new JMenu("File");
    mnNewMenu.setBackground(Color.WHITE);
    mnNewMenu.setForeground(Color.BLACK);
    mnNewMenu.setFont(new Font("Segoe UI", 1, 12));
    Image f = new ImageIcon(getClass().getResource("/file.png")).getImage();
    mnNewMenu.setIcon(new ImageIcon(f));
    menuBar.add(mnNewMenu);
    
    JMenu help = new JMenu("Help");
    help.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        new Help().setVisible(true);
      }
    });
    help.setBackground(Color.WHITE);
    Image h = new ImageIcon(getClass().getResource("/help.png")).getImage();
    help.setIcon(new ImageIcon(h));
    help.setFont(new Font("Segoe UI", 1, 12));
    help.setForeground(Color.BLACK);
    mnNewMenu.add(help);
    
    JSeparator separator = new JSeparator();
    mnNewMenu.add(separator);
    
    JMenu mnAbout = new JMenu("About");
    mnAbout.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        new About().setVisible(true);
      }
    });
    mnAbout.setBackground(Color.WHITE);
    Image about = new ImageIcon(getClass().getResource("/about.png")).getImage();
    mnAbout.setIcon(new ImageIcon(about));
    mnAbout.setFont(new Font("Segoe UI", 1, 12));
    mnNewMenu.add(mnAbout);
    
    JSeparator separator_1 = new JSeparator();
    mnNewMenu.add(separator_1);
    
    JMenu mnExit = new JMenu("Exit");
    mnExit.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        System.exit(3);
      }
    });
    mnExit.setBackground(Color.WHITE);
    Image exit = new ImageIcon(getClass().getResource("/exit.png")).getImage();
    mnExit.setIcon(new ImageIcon(exit));
    mnExit.setFont(new Font("Segoe UI", 1, 12));
    mnNewMenu.add(mnExit);
    
    JMenu mnChieckIn = new JMenu("Checkin");
    Image check = new ImageIcon(getClass().getResource("/check.png")).getImage();
    mnChieckIn.setIcon(new ImageIcon(check));
    mnChieckIn.setForeground(Color.BLACK);
    mnChieckIn.setFont(new Font("Segoe UI", 1, 12));
    menuBar.add(mnChieckIn);
    
    JMenu mnConfirmCheckin = new JMenu("Confirm");
    mnConfirmCheckin.setIcon(new ImageIcon(check));
    mnConfirmCheckin.setFont(new Font("Segoe UI", 1, 12));
    mnChieckIn.add(mnConfirmCheckin);
    
    JSeparator separator_3 = new JSeparator();
    mnChieckIn.add(separator_3);
    
    JMenu mnNewMenu_4 = new JMenu("New");
    mnNewMenu_4.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        new NewCheck().setVisible(true);
      }
    });
    mnNewMenu_4.setIcon(new ImageIcon(check));
    mnNewMenu_4.setFont(new Font("Segoe UI", 1, 12));
    mnChieckIn.add(mnNewMenu_4);
    
    JMenu mnNewMenu_1 = new JMenu("CheckOut");
    mnNewMenu_1.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        Menu.dbc = db.dbConnector();
        try
        {
          DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
          Date date = new Date();
          int x = 0;
          Stack st = new Stack();
          String xx = dateFormat.format(date);
          String query = "select Room from Room Where CheckOut='" + xx + "' ";
          PreparedStatement pst = Menu.dbc.prepareStatement(query);
          ResultSet rs = pst.executeQuery();
          while (rs.next())
          {
            st.push(rs.getString("Room") + " \n");
            x++;
          }
          JOptionPane.showMessageDialog(null, x + " Client Are available for Checkout. Room  " + st);
          
          pst.close();
          rs.close();
        }
        catch (Exception e)
        {
          JOptionPane.showMessageDialog(null, "Failed To load Try Again " + e);
        }
      }
    });
    Image cout = new ImageIcon(getClass().getResource("/checkout.png")).getImage();
    mnNewMenu_1.setIcon(new ImageIcon(cout));
    mnNewMenu_1.setFont(new Font("Segoe UI", 1, 12));
    mnNewMenu_1.setForeground(Color.BLACK);
    menuBar.add(mnNewMenu_1);
    
    JMenu mnDatabase = new JMenu("Database");
    mnDatabase.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        new DataBase().setVisible(true);
      }
    });
    Image db = new ImageIcon(getClass().getResource("/database.png")).getImage();
    mnDatabase.setIcon(new ImageIcon(db));
    mnDatabase.setFont(new Font("Segoe UI", 1, 12));
    mnDatabase.setForeground(Color.BLACK);
    menuBar.add(mnDatabase);
    
    JMenu mnMap = new JMenu("Location");
    mnMap.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        new Map().setVisible(true);
      }
    });
    Image map = new ImageIcon(getClass().getResource("/map.png")).getImage();
    mnMap.setIcon(new ImageIcon(map));
    mnMap.setFont(new Font("Segoe UI", 1, 12));
    mnMap.setForeground(Color.BLACK);
    menuBar.add(mnMap);
    
    JMenu mnSecurity = new JMenu("Security");
    mnSecurity.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        new Security().setVisible(true);
      }
    });
    Image se = new ImageIcon(getClass().getResource("/sc.png")).getImage();
    mnSecurity.setIcon(new ImageIcon(se));
    mnSecurity.setFont(new Font("Segoe UI", 1, 12));
    mnSecurity.setForeground(Color.BLACK);
    menuBar.add(mnSecurity);
    
    JMenu mnNewMenu_3 = new JMenu("Employee");
    mnNewMenu_3.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        new Employee().setVisible(true);
      }
    });
    Image em = new ImageIcon(getClass().getResource("/User.png")).getImage();
    mnNewMenu_3.setIcon(new ImageIcon(em));
    mnNewMenu_3.setFont(new Font("Segoe UI", 1, 12));
    mnNewMenu_3.setForeground(Color.BLACK);
    menuBar.add(mnNewMenu_3);
    JMenu mnNewMenu_2 = new JMenu("Package");
    mnNewMenu_2.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        new Pack().setVisible(true);
      }
    });
    Image pack = new ImageIcon(getClass().getResource("/pack.png")).getImage();
    mnNewMenu_2.setIcon(new ImageIcon(pack));
    mnNewMenu_2.setFont(new Font("Segoe UI", 1, 12));
    mnNewMenu_2.setForeground(Color.BLACK);
    menuBar.add(mnNewMenu_2);
    
    this.contentPane = new JPanel();
    this.contentPane.setBackground(new Color(255, 255, 255));
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(this.contentPane);
    this.contentPane.setLayout(null);
    
    pack1 = new JTextField();
    pack1.setFont(new Font("Tahoma", 1, 12));
    pack1.setEditable(false);
    pack1.setColumns(10);
    pack1.setBounds(71, 132, 108, 25);
    this.contentPane.add(pack1);
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(211, 39, 343, 330);
    this.contentPane.add(scrollPane);
    
    table = new JTable();
    table.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0) {}
    });
    scrollPane.setViewportView(table);
    Image bg = new ImageIcon(getClass().getResource("/bgr.jpg")).getImage();
    
    this.dispaly = new JTextArea();
    this.dispaly.setForeground(Color.BLACK);
    this.dispaly.setBackground(SystemColor.control);
    this.dispaly.setEditable(false);
    this.dispaly.setFont(new Font("Monospaced", 1, 13));
    this.dispaly.setBounds(566, 39, 138, 141);
    try
    {
      loadt();
      tfree();
      lfree();
      vfree();
      gfree();
      
      this.dispaly.setText(" Overall Status\n\n Total Room: 30\n Available: " + tf + "\n Luxary: " + lf + "\n Vip: " + vf + "\n Genarel: " + gf);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    this.contentPane.add(this.dispaly);
    
    JLabel lblDatabase = new JLabel("Current Status");
    lblDatabase.setFont(new Font("Tahoma", 1, 18));
    lblDatabase.setForeground(Color.BLACK);
    lblDatabase.setBounds(307, 0, 138, 31);
    this.contentPane.add(lblDatabase);
    
    room1 = new JTextField();
    room1.setFont(new Font("Tahoma", 1, 12));
    room1.setEditable(false);
    
    room1.setBounds(71, 39, 108, 25);
    this.contentPane.add(room1);
    room1.setColumns(10);
    
    name1 = new JTextField();
    name1.setFont(new Font("Tahoma", 1, 12));
    name1.setEditable(false);
    name1.setColumns(10);
    name1.setBounds(71, 86, 108, 25);
    this.contentPane.add(name1);
    
    JLabel lblHotel = new JLabel("@ 2015 Hotel Management System By Shoaeb");
    lblHotel.setForeground(Color.BLACK);
    lblHotel.setFont(new Font("Tahoma", 1, 12));
    lblHotel.setBounds(232, 381, 286, 25);
    this.contentPane.add(lblHotel);
    
    JLabel lblNewLabel = new JLabel("Name");
    lblNewLabel.setFont(new Font("Tahoma", 1, 15));
    lblNewLabel.setForeground(Color.BLACK);
    lblNewLabel.setBounds(15, 84, 46, 25);
    this.contentPane.add(lblNewLabel);
    
    JLabel lblRoom = new JLabel("Room");
    lblRoom.setForeground(Color.BLACK);
    lblRoom.setFont(new Font("Tahoma", 1, 15));
    lblRoom.setBounds(15, 39, 46, 25);
    this.contentPane.add(lblRoom);
    
    JLabel lblPackage = new JLabel("Pack");
    lblPackage.setForeground(Color.BLACK);
    lblPackage.setFont(new Font("Tahoma", 1, 15));
    lblPackage.setBounds(15, 130, 46, 25);
    this.contentPane.add(lblPackage);
    
    JLabel cout1 = new JLabel("CheckOut");
    cout1.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        if (Menu.room1.getText().length() == 3) {
          try
          {
            String query = "Update Room set Status='0' , Client=null , Checkin=null,CheckOut=null,Code=null,Credit=null,Mobile=null  where Room='" + Menu.room1.getText() + "'  ";
            PreparedStatement pst = Menu.dbc.prepareStatement(query);
            pst.execute();
            pst.close();
            Menu.room1.setText(null);
            Menu.name1.setText(null);
            Menu.pack1.setText(null);
            Menu.loadt();
            Menu.tf = Menu.lf = Menu.vf = 0;
            Menu.tfree();
            Menu.lfree();
            Menu.vfree();
            Menu.gfree();
            
            Menu.this.dispaly.setText(" Overall Status\n\n Total Room: 30\n Available: " + Menu.tf + "\n Luxary: " + Menu.lf + "\n Vip: " + Menu.vf + "\n Genarel: " + Menu.gf);
            JOptionPane.showMessageDialog(null, "Success");
          }
          catch (Exception e00)
          {
            JOptionPane.showMessageDialog(null, "Failed To CheckOut " + e00);
          }
        } else {
          JOptionPane.showMessageDialog(null, "Select a room 1st");
        }
      }
    });
    cout1.setFont(new Font("Tahoma", 1, 15));
    cout1.setForeground(Color.BLACK);
    
    cout1.setIcon(new ImageIcon(cout));
    
    cout1.setBounds(71, 168, 108, 40);
    this.contentPane.add(cout1);
    
    JLabel logo = new JLabel("");
    Image logo1 = new ImageIcon(getClass().getResource("/i3.png")).getImage();
    logo.setIcon(new ImageIcon(logo1));
    logo.setBounds(15, 208, 180, 187);
    this.contentPane.add(logo);
    
    this.textDate = new JTextField();
    this.textDate.setFont(new Font("Tahoma", 1, 13));
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
    Date date = new Date();
    
    this.textDate.setText("  " + dateFormat.format(date));
    this.textDate.setBackground(SystemColor.textHighlightText);
    this.textDate.setEditable(false);
    this.textDate.setBounds(555, 0, 149, 31);
    this.contentPane.add(this.textDate);
    this.textDate.setColumns(10);
    
    this.gogle = new JTextField();
    this.gogle.setHorizontalAlignment(0);
    this.gogle.setText(" Code71.org");
    this.gogle.setToolTipText("Search");
    this.gogle.setFont(new Font("Tahoma", 1, 12));
    this.gogle.setColumns(10);
    this.gogle.setBounds(564, 237, 140, 25);
    this.contentPane.add(this.gogle);
    
    JLabel lblNewLabel_1 = new JLabel("Google Search");
    lblNewLabel_1.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        try
        {
          String a = "https://www.google.com.bd/search?q=";
          String b = Menu.this.gogle.getText();
          String c = b.replace(" ", "+");
          String d = a + c;
          
          Desktop.getDesktop().browse(URI.create(d));
          Menu.this.gogle.setText("Code71.org");
        }
        catch (Exception e)
        {
          Menu.this.gogle.setText("No connection");
        }
      }
    });
    lblNewLabel_1.setForeground(new Color(139, 0, 0));
    lblNewLabel_1.setFont(new Font("Tahoma", 1, 12));
    lblNewLabel_1.setBounds(589, 273, 93, 25);
    this.contentPane.add(lblNewLabel_1);
  }
  
  static void loadt()
  {
    dbc = db.dbConnector();
    try
    {
      String query = "select Room,Client,CheckOut,Pack,Code from Room Where Status='1' ";
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
  
  static void tfree()
  {
    dbc = db.dbConnector();
    try
    {
      String query = "select Room from Room Where Status='0' ";
      PreparedStatement pst = dbc.prepareStatement(query);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        tf += 1;
      }
      pst.close();
      rs.close();
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(null, "Failed To load Try Again " + e);
    }
  }
  
  static void vfree()
  {
    dbc = db.dbConnector();
    try
    {
      String query = "select Room from Room Where Status='0' and Pack='2' ";
      PreparedStatement pst = dbc.prepareStatement(query);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        vf += 1;
      }
      pst.close();
      rs.close();
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(null, "Failed To load Try Again " + e);
    }
  }
  
  static void gfree()
  {
    dbc = db.dbConnector();
    try
    {
      String query = "select Room from Room Where Status='0' and Pack='1' ";
      PreparedStatement pst = dbc.prepareStatement(query);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        gf += 1;
      }
      pst.close();
      rs.close();
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(null, "Failed To load Try Again " + e);
    }
  }
  
  static void lfree()
  {
    dbc = db.dbConnector();
    try
    {
      String query = "select Room from Room Where Status='0' and Pack='3' ";
      PreparedStatement pst = dbc.prepareStatement(query);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        lf += 1;
      }
      pst.close();
      rs.close();
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(null, "Failed To load Try Again " + e);
    }
  }
  
  static void getvalue()
  {
    dbc = db.dbConnector();
    int row = table.getSelectedRow();
    String R1 = table.getModel().getValueAt(row, 0).toString();
    try
    {
      String query = "select Room,Client,Pack from Room Where Room='" + R1 + "' ";
      PreparedStatement pst = dbc.prepareStatement(query);
      ResultSet rs = pst.executeQuery();
      while (rs.next())
      {
        room1.setText(rs.getString("Room"));
        name1.setText(rs.getString("Client"));
        pack1.setText(rs.getString("Pack"));
      }
      pst.close();
      rs.close();
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(null, "Failed To load Try Again " + e);
    }
  }
}
