import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class NewCheck
  extends JFrame
{
  private JPanel contentPane;
  JRadioButton lux;
  JRadioButton vip;
  JRadioButton gen;
  private JTextField tname;
  private JTextField tmobile;
  private static JTextField tpack;
  private static JTextField troom;
  private JTextField val;
  static Connection dbc = null;
  private static JTextField tcost;
  private JLabel lblTotalCost;
  int validity;
  
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          NewCheck frame = new NewCheck();
          frame.setVisible(true);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    });
  }
  
  public NewCheck()
  {
   // setType(Window.Type.POPUP);
    setTitle("New Checkin");
    URL iconURL = getClass().getResource("/icon2.png");
    
    ImageIcon icon = new ImageIcon(iconURL);
    setIconImage(icon.getImage());
    setResizable(false);
    setDefaultCloseOperation(2);
    setBounds(520, 100, 373, 461);
    this.contentPane = new JPanel();
    this.contentPane.setBackground(Color.WHITE);
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(this.contentPane);
    this.contentPane.setLayout(null);
    
    JLabel lblNewLabel = new JLabel("Please Select a package");
    lblNewLabel.setFont(new Font("Tahoma", 1, 14));
    lblNewLabel.setBounds(91, 11, 171, 24);
    this.contentPane.add(lblNewLabel);
    
    this.lux = new JRadioButton("Luxary");
    this.lux.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        if (NewCheck.this.lux.isSelected())
        {
          NewCheck.this.vip.setSelected(false);
          NewCheck.this.gen.setSelected(false);
        }
        NewCheck.lfree();
        int c = Integer.parseInt(NewCheck.this.val.getText());
        NewCheck.tcost.setText(Integer.toString(c * 4000 + 2500));
      }
    });
    this.lux.setFont(new Font("Tahoma", 1, 12));
    this.lux.setForeground(Color.WHITE);
    this.lux.setBackground(Color.BLACK);
    this.lux.setBounds(29, 50, 74, 23);
    this.contentPane.add(this.lux);
    
    this.vip = new JRadioButton("Vip");
    this.vip.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        if (NewCheck.this.vip.isSelected())
        {
          NewCheck.this.lux.setSelected(false);
          NewCheck.this.gen.setSelected(false);
          NewCheck.vfree();
          int c = Integer.parseInt(NewCheck.this.val.getText());
          NewCheck.tcost.setText(Integer.toString(c * 3000 + 2500));
        }
      }
    });
    this.vip.setBackground(new Color(128, 0, 0));
    this.vip.setForeground(Color.WHITE);
    this.vip.setFont(new Font("Tahoma", 1, 12));
    this.vip.setBounds(142, 50, 74, 23);
    this.contentPane.add(this.vip);
    
    this.gen = new JRadioButton("General");
    this.gen.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        if (NewCheck.this.gen.isSelected())
        {
          NewCheck.this.vip.setSelected(false);
          NewCheck.this.lux.setSelected(false);
          NewCheck.gfree();
          int c = Integer.parseInt(NewCheck.this.val.getText());
          NewCheck.tcost.setText(Integer.toString(c * 2000 + 2500));
        }
      }
    });
    this.gen.setFont(new Font("Tahoma", 1, 12));
    this.gen.setForeground(new Color(255, 255, 255));
    this.gen.setBackground(new Color(0, 100, 0));
    this.gen.setBounds(256, 50, 74, 23);
    this.contentPane.add(this.gen);
    
    this.tname = new JTextField();
    this.tname.setBounds(135, 96, 171, 24);
    this.contentPane.add(this.tname);
    this.tname.setColumns(10);
    
    JLabel cname = new JLabel("Name :");
    cname.setFont(new Font("Tahoma", 1, 12));
    cname.setBounds(68, 96, 46, 22);
    this.contentPane.add(cname);
    
    this.tmobile = new JTextField();
    this.tmobile.setColumns(10);
    this.tmobile.setBounds(135, 142, 171, 24);
    this.contentPane.add(this.tmobile);
    
    JLabel cmobile = new JLabel("Mobile :");
    cmobile.setFont(new Font("Tahoma", 1, 12));
    cmobile.setBounds(68, 142, 58, 22);
    this.contentPane.add(cmobile);
    
    tpack = new JTextField();
    tpack.setBackground(new Color(255, 255, 255));
    tpack.setEditable(false);
    tpack.setColumns(10);
    tpack.setBounds(135, 185, 74, 24);
    this.contentPane.add(tpack);
    
    JLabel cpack = new JLabel("Pack :");
    cpack.setFont(new Font("Tahoma", 1, 12));
    cpack.setBounds(66, 185, 46, 22);
    this.contentPane.add(cpack);
    
    troom = new JTextField();
    troom.setBackground(new Color(255, 255, 255));
    troom.setEditable(false);
    troom.setColumns(10);
    troom.setBounds(135, 235, 74, 24);
    this.contentPane.add(troom);
    
    JLabel croom = new JLabel("Room :");
    croom.setFont(new Font("Tahoma", 1, 12));
    croom.setBounds(68, 235, 46, 22);
    this.contentPane.add(croom);
    
    JButton btnNewButton = new JButton("Checkin");
    btnNewButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if ((NewCheck.this.tname.getText().length() > 1) && (NewCheck.this.tmobile.getText().length() > 9) && (NewCheck.tpack.getText().length() > 0)) {
          try
          {
            String cd = Code.getcode();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
            Date date = new Date();
            int d4 = Integer.parseInt(NewCheck.this.val.getText());
            String query = "Update Room set Status='1' , Client='" + NewCheck.this.tname.getText() + "' , Checkin='" + dateFormat.format(date) + "',CheckOut='" + NewCheck.dt(d4) + "',Code='" + cd + "',Credit='500',Mobile='" + NewCheck.this.tmobile.getText() + "',Paid='" + NewCheck.tcost.getText() + "'  where Room='" + NewCheck.troom.getText() + "'  ";
            PreparedStatement pst = NewCheck.dbc.prepareStatement(query);
            JOptionPane.showMessageDialog(null, "Checkin Successfull \nThe PinCode Of Your CreditCard is\n " + cd);
            pst.execute();
            pst.close();
            NewCheck.this.dispose();
          }
          catch (SQLException e)
          {
            JOptionPane.showMessageDialog(null, e);
          }
        } else {
          JOptionPane.showMessageDialog(null, "Please fillup all the field ");
        }
      }
    });
    btnNewButton.setFont(new Font("Tahoma", 1, 11));
    btnNewButton.setBackground(new Color(255, 255, 255));
    Image check = new ImageIcon(getClass().getResource("/check.png")).getImage();
    btnNewButton.setIcon(new ImageIcon(check));
    btnNewButton.setBounds(125, 348, 113, 38);
    this.contentPane.add(btnNewButton);
    
    JLabel valuuuu = new JLabel("Validity :");
    valuuuu.setFont(new Font("Tahoma", 1, 12));
    valuuuu.setBounds(68, 284, 58, 22);
    this.contentPane.add(valuuuu);
    
    this.val = new JTextField();
    this.val.setText("1");
    this.val.setEditable(false);
    this.val.setColumns(10);
    this.val.setBackground(Color.WHITE);
    this.val.setBounds(135, 284, 74, 24);
    this.contentPane.add(this.val);
    
    JLabel lblNewLabel_1 = new JLabel("");
    lblNewLabel_1.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        if ((NewCheck.this.vip.isSelected()) || (NewCheck.this.lux.isSelected()) || (NewCheck.this.gen.isSelected()))
        {
          int i = Integer.parseInt(NewCheck.this.val.getText());
          if (i < 30)
          {
            i++;
            NewCheck.this.val.setText(Integer.toString(i));
            if (NewCheck.this.lux.isSelected())
            {
              int c = Integer.parseInt(NewCheck.this.val.getText());
              NewCheck.tcost.setText(Integer.toString(c * 4000 + 2500));
            }
            if (NewCheck.this.vip.isSelected())
            {
              int c = Integer.parseInt(NewCheck.this.val.getText());
              NewCheck.tcost.setText(Integer.toString(c * 3000 + 2500));
            }
            if (NewCheck.this.gen.isSelected())
            {
              int c = Integer.parseInt(NewCheck.this.val.getText());
              NewCheck.tcost.setText(Integer.toString(c * 2000 + 2500));
            }
          }
        }
      }
    });
    Image up = new ImageIcon(getClass().getResource("/up.png")).getImage();
    lblNewLabel_1.setIcon(new ImageIcon(up));
    lblNewLabel_1.setBounds(235, 268, 27, 24);
    this.contentPane.add(lblNewLabel_1);
    
    JLabel label = new JLabel("");
    label.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        if ((NewCheck.this.vip.isSelected()) || (NewCheck.this.lux.isSelected()) || (NewCheck.this.gen.isSelected()))
        {
          int i = Integer.parseInt(NewCheck.this.val.getText());
          if (i > 1)
          {
            i--;
            NewCheck.this.val.setText(Integer.toString(i));
            if (NewCheck.this.lux.isSelected())
            {
              int c = Integer.parseInt(NewCheck.this.val.getText());
              NewCheck.tcost.setText(Integer.toString(c * 4000 + 2500));
            }
            if (NewCheck.this.vip.isSelected())
            {
              int c = Integer.parseInt(NewCheck.this.val.getText());
              NewCheck.tcost.setText(Integer.toString(c * 3000 + 2500));
            }
            if (NewCheck.this.gen.isSelected())
            {
              int c = Integer.parseInt(NewCheck.this.val.getText());
              NewCheck.tcost.setText(Integer.toString(c * 2000 + 2500));
            }
          }
        }
      }
    });
    Image down = new ImageIcon(getClass().getResource("/down.png")).getImage();
    label.setIcon(new ImageIcon(down));
    label.setBounds(235, 300, 27, 24);
    this.contentPane.add(label);
    
    tcost = new JTextField();
    tcost.setBounds(256, 218, 86, 41);
    this.contentPane.add(tcost);
    tcost.setColumns(10);
    
    this.lblTotalCost = new JLabel("Total Cost");
    this.lblTotalCost.setFont(new Font("Tahoma", 1, 12));
    this.lblTotalCost.setBounds(267, 185, 63, 22);
    this.contentPane.add(this.lblTotalCost);
    
    JLabel lblNewLabel_2 = new JLabel("");
    lblNewLabel_2.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        NewCheck.this.dispose();
      }
    });
    Image home = new ImageIcon(getClass().getResource("/home.png")).getImage();
    lblNewLabel_2.setIcon(new ImageIcon(home));
    lblNewLabel_2.setBounds(10, 10, 37, 33);
    this.contentPane.add(lblNewLabel_2);
  }
  
  static void vfree()
  {
    dbc = db.dbConnector();
    try
    {
      String query = "select Room from Room Where Status='0' and Pack='2' ";
      PreparedStatement pst = dbc.prepareStatement(query);
      ResultSet rs = pst.executeQuery();
      if (rs.next())
      {
        troom.setText(rs.getString("Room"));
        tpack.setText("2");
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
      if (rs.next())
      {
        troom.setText(rs.getString("Room"));
        tpack.setText("1");
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
      if (rs.next())
      {
        troom.setText(rs.getString("Room"));
        tpack.setText("3");
      }
      pst.close();
      rs.close();
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(null, "Failed To load Try Again " + e);
    }
  }
  
  static String dt(int d)
  {
    DateFormat da = new SimpleDateFormat("MM");
    DateFormat day = new SimpleDateFormat("dd");
    DateFormat yr = new SimpleDateFormat("yy");
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
    
    Date date = new Date();
    
    int d1 = Integer.parseInt(day.format(date));
    int m = Integer.parseInt(da.format(date)) - 1;
    int y = Integer.parseInt(yr.format(date));
    Calendar calendar = new GregorianCalendar(y, m, d1);
    calendar.add(5, d);
    String date1 = sdf.format(calendar.getTime());
    return date1;
  }
}
