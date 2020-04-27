import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class Employee
  extends JDialog
{
  private final JPanel contentPanel = new JPanel();
  private static JTable table;
  static Connection dbc = null;
  private static JTextField name;
  private static JTextField id;
  private static JTextField mob;
  private static JTextField post;
  private JLabel lblNewLabel;
  private JLabel lblEid;
  private JLabel lblMobile;
  private JLabel lblPost;
  private JLabel lblEmployeeInfo;
  JCheckBox r;
  JCheckBox a;
  JCheckBox u;
  private JTextField txtOk;
  
  public static void main(String[] args)
  {
    try
    {
      Employee dialog = new Employee();
      dialog.setDefaultCloseOperation(2);
      dialog.setVisible(true);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public Employee()
  {
    setTitle("Employee");
    setResizable(false);
    setBounds(420, 100, 624, 381);
    getContentPane().setLayout(new BorderLayout());
    this.contentPanel.setBackground(Color.WHITE);
    this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(this.contentPanel, "Center");
    this.contentPanel.setLayout(null);
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(271, 47, 337, 246);
    this.contentPanel.add(scrollPane);
    
    table = new JTable();
    table.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0) {}
    });
    table.setBackground(new Color(255, 255, 255));
    table.setFont(new Font("Tahoma", 0, 12));
    scrollPane.setViewportView(table);
    
    JLabel lblEmployeeDatabase = new JLabel("Employee DataBase");
    lblEmployeeDatabase.setForeground(new Color(128, 0, 0));
    lblEmployeeDatabase.setFont(new Font("Tahoma", 1, 15));
    lblEmployeeDatabase.setBounds(367, 0, 168, 36);
    this.contentPanel.add(lblEmployeeDatabase);
    
    name = new JTextField();
    name.setEditable(false);
    name.setBounds(99, 65, 136, 25);
    this.contentPanel.add(name);
    name.setColumns(10);
    
    id = new JTextField();
    id.setEditable(false);
    id.setColumns(10);
    id.setBounds(99, 101, 136, 25);
    this.contentPanel.add(id);
    
    mob = new JTextField();
    mob.setEditable(false);
    mob.setColumns(10);
    mob.setBounds(99, 137, 136, 25);
    this.contentPanel.add(mob);
    
    post = new JTextField();
    post.setEditable(false);
    post.setColumns(10);
    post.setBounds(99, 179, 136, 25);
    this.contentPanel.add(post);
    
    this.lblNewLabel = new JLabel("Name");
    this.lblNewLabel.setFont(new Font("Tahoma", 1, 13));
    this.lblNewLabel.setBounds(31, 64, 58, 25);
    this.contentPanel.add(this.lblNewLabel);
    
    this.lblEid = new JLabel("Eid");
    this.lblEid.setFont(new Font("Tahoma", 1, 13));
    this.lblEid.setBounds(31, 101, 58, 25);
    this.contentPanel.add(this.lblEid);
    
    this.lblMobile = new JLabel("Mobile");
    this.lblMobile.setFont(new Font("Tahoma", 1, 13));
    this.lblMobile.setBounds(31, 137, 58, 25);
    this.contentPanel.add(this.lblMobile);
    
    this.lblPost = new JLabel("Post");
    this.lblPost.setFont(new Font("Tahoma", 1, 13));
    this.lblPost.setBounds(31, 179, 58, 25);
    this.contentPanel.add(this.lblPost);
    
    this.r = new JCheckBox("Remove");
    this.r.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        if (Employee.this.r.isSelected())
        {
          Employee.this.a.setSelected(false);
          Employee.this.u.setSelected(false);
        }
      }
    });
    this.r.setForeground(new Color(255, 0, 0));
    this.r.setBounds(31, 229, 72, 23);
    this.contentPanel.add(this.r);
    
    this.a = new JCheckBox("Add");
    this.a.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        if (Employee.this.a.isSelected())
        {
          Employee.this.r.setSelected(false);
          Employee.this.u.setSelected(false);
          Employee.name.setText(null);
          Employee.id.setText(Code.geid());
          Employee.post.setText(null);
          Employee.mob.setText(null);
          Employee.name.setEditable(true);
          Employee.post.setEditable(true);
          Employee.mob.setEditable(true);
        }
      }
    });
    this.a.setForeground(new Color(128, 0, 0));
    this.a.setBounds(105, 229, 72, 23);
    this.contentPanel.add(this.a);
    
    this.u = new JCheckBox("Update");
    this.u.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        if (Employee.this.u.isSelected())
        {
          Employee.this.a.setSelected(false);
          Employee.this.r.setSelected(false);
          
          Employee.name.setEditable(true);
          
          Employee.mob.setEditable(true);
        }
      }
    });
    this.u.setForeground(new Color(0, 128, 0));
    this.u.setBounds(179, 229, 72, 23);
    this.contentPanel.add(this.u);
    
    JLabel lblNewLabel_1 = new JLabel("Submit");
    lblNewLabel_1.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        if (Employee.this.r.isSelected()) {
          Employee.delete();
        } else if (Employee.this.a.isSelected()) {
          Employee.addd();
        } else if (Employee.this.u.isSelected()) {
          Employee.update();
        }
        Employee.loadt();
      }
    });
    lblNewLabel_1.setForeground(new Color(128, 0, 0));
    lblNewLabel_1.setFont(new Font("Tahoma", 1, 13));
    Image home = new ImageIcon(getClass().getResource("/login.png")).getImage();
    lblNewLabel_1.setIcon(new ImageIcon(home));
    lblNewLabel_1.setBounds(114, 282, 104, 43);
    this.contentPanel.add(lblNewLabel_1);
    
    this.lblEmployeeInfo = new JLabel("Employee Info");
    this.lblEmployeeInfo.setForeground(new Color(128, 0, 0));
    this.lblEmployeeInfo.setFont(new Font("Tahoma", 1, 15));
    this.lblEmployeeInfo.setBounds(99, 11, 122, 36);
    this.contentPanel.add(this.lblEmployeeInfo);
    
    this.txtOk = new JTextField();
    this.txtOk.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        Employee.this.dispose();
      }
    });
    this.txtOk.setBackground(new Color(255, 255, 255));
    this.txtOk.setFont(new Font("Tahoma", 1, 14));
    this.txtOk.setText(" Ok");
    this.txtOk.setEditable(false);
    this.txtOk.setColumns(10);
    this.txtOk.setBounds(465, 316, 38, 25);
    this.contentPanel.add(this.txtOk);
    loadt();
  }
  
  static void loadt()
  {
    dbc = db.dbConnector();
    try
    {
      String query = "select Id,Name,Mobile,Post from Emp ";
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
  
  static void getvalue()
  {
    dbc = db.dbConnector();
    int row = table.getSelectedRow();
    String R1 = table.getModel().getValueAt(row, 0).toString();
    try
    {
      String query = "select Name,Id,Post,Mobile from Emp Where Id='" + R1 + "' ";
      PreparedStatement pst = dbc.prepareStatement(query);
      ResultSet rs = pst.executeQuery();
      while (rs.next())
      {
        name.setText(rs.getString("Name"));
        id.setText(rs.getString("Id"));
        post.setText(rs.getString("Post"));
        mob.setText(rs.getString("Mobile"));
      }
      pst.close();
      rs.close();
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(null, "Failed To load Try Again " + e);
    }
  }
  
  static void update()
  {
    try
    {
      String query = "Update Emp set Name='" + name.getText() + "',Post='" + post.getText() + "',Mobile='" + mob.getText() + "'  where Id='" + id.getText() + "'  ";
      PreparedStatement pst = dbc.prepareStatement(query);
      pst.execute();
      pst.close();
      name.setText(null);
      id.setText(null);
      post.setText(null);
      mob.setText(null);
      loadt();
      edit();
      
      JOptionPane.showMessageDialog(null, "Successfully updated");
    }
    catch (Exception e00)
    {
      JOptionPane.showMessageDialog(null, "Failed To CheckOut " + e00);
    }
  }
  
  static void delete()
  {
    try
    {
      String query = "Delete  From Emp where Id='" + id.getText() + "'  ";
      PreparedStatement pst = dbc.prepareStatement(query);
      pst.execute();
      pst.close();
      name.setText(null);
      id.setText(null);
      post.setText(null);
      mob.setText(null);
      loadt();
      edit();
      
      JOptionPane.showMessageDialog(null, "Successfully Deleted");
    }
    catch (Exception e00)
    {
      JOptionPane.showMessageDialog(null, "Failed To CheckOut " + e00);
    }
  }
  
  static void addd()
  {
    try
    {
      String query = "Insert into  Emp (Name,Post,Mobile,Id) values('" + name.getText() + "','" + post.getText() + "','" + mob.getText() + "','" + id.getText() + "b" + "') ";
      PreparedStatement pst = dbc.prepareStatement(query);
      pst.execute();
      pst.close();
      name.setText(null);
      id.setText(null);
      post.setText(null);
      mob.setText(null);
      loadt();
      edit();
      
      JOptionPane.showMessageDialog(null, "Successfully added");
    }
    catch (Exception e00)
    {
      JOptionPane.showMessageDialog(null, "Failed To CheckOut " + e00);
    }
  }
  
  static void edit()
  {
    name.setEditable(false);
    post.setEditable(false);
    mob.setEditable(false);
  }
}
