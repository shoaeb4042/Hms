
import java.util.Random;

public class Code
{
  public static String getcode()
  {
    Random cd = new Random();
    
    String rand = "";
    for (int c = 1; c <= 8; c++)
    {
      int num = cd.nextInt(8);
      
      rand = rand + Integer.toString(num);
    }
    return rand;
  }
  
  public static String geid()
  {
    Random cd = new Random();
    
    String rand = "";
    for (int c = 1; c <= 10; c++)
    {
      int num = cd.nextInt(10);
      
      rand = rand + Integer.toString(num);
    }
    return rand;
  }
}
