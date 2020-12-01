//Natalie Shafer, Colden Deaja 
//Logger
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger
 {
   public void logger(String mess)
  {
    try{

      BufferedWriter output = new BufferedWriter(new FileWriter("CAMOLOG.txt", true));
 
      DateFormat logdateFormat = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
      Date logdate = new Date();
      String currentdate = logdateFormat.format(logdate);

      output.write(currentdate + "\t" + mess );
      output.newLine();
      output.close();
       }
    catch(Exception except){}
     
  }
 }