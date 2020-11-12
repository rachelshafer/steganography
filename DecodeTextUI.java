//Natalie Shafer
//UI
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.border.EtchedBorder;
import static javax.swing.GroupLayout.Alignment.*;

public class DecodeTextUI {

  private String  stat_path = "";
  private String  stat_name = "";
  private String ext ="";
  private String mess;
  private JLabel image;
  private JTextArea outmess;
  
  public DecodeTextUI()
  {
    JFrame frame = new JFrame("Decode Text From Image");//NAME THE JFRAME
    //JPanel pa = new JPanel(); //CREATE THE PANEL
    JLabel decodingtextlabel = new JLabel("Select an Image file");//LABEL FOR SEARCH BUTTON
    image = new JLabel();//ENCODED IMAGE
    outmess = new JTextArea();//TEXT AREA OUTPUT
    outmess.setLineWrap(true);//ALLOW LINE OF TEXT TO WRAP    

    String hold = "C:\\Users\\Natalie\\Desktop\\Camo Project Improved/BlankSpace.jpg ";
    image.setIcon(ResizeImage(hold));
    image.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

    
            Dimension size = new Dimension(400, 400);
    image.setPreferredSize(size);
    image.setMinimumSize(size);

    
    
    outmess.setPreferredSize(new Dimension(651, 150));
    outmess.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

    //ALL THE BUTTONS
    JButton decodetextbt = new JButton("Decode");
    JButton searchdecodetextbt = new JButton("Search");
    JButton clearimage = new JButton("Clear");
    JButton back1 = new JButton("Back");
    JButton cleartext = new JButton("Clear");
    
    //ALL THE LISTENERS
    back1.addActionListener(new ActionListener() {//BACK BUTTON LISTENER
      public void actionPerformed(ActionEvent e) {
        new MainPage();
        frame.dispose(); 
      }
    });
  
    cleartext.addActionListener(new ActionListener() {//CLEAR TEXT BUTTON LISTENER
      public void actionPerformed(ActionEvent e) {  
        outmess.setText(""); 
      }
    });
      
    clearimage.addActionListener(new ActionListener() {//CLEAR SEARCHED IMAGE LISTENER
      public void actionPerformed(ActionEvent e) { 
        image.setIcon(ResizeImage(hold));
        stat_path = "";
        stat_name = "";
        ext ="";
      }
    });
        
    searchdecodetextbt.addActionListener(new ActionListener() {//SEARCH BUTTON LISTENER
      public void actionPerformed(ActionEvent e) {
        outmess.setText("");
        
        JFileChooser chooser = new JFileChooser("./");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setFileFilter(new Checker());
        
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION){
        File directory = chooser.getSelectedFile();
   
        try{
           ext = Checker.getExtension(directory);
           String imagest = directory.getPath();
           stat_name = directory.getName();
           stat_path = directory.getPath();
           stat_path = stat_path.substring(0,stat_path.length()-stat_name.length()-1);
           stat_name = stat_name.substring(0, stat_name.length()-4);
           image.setIcon(ResizeImage(imagest));
        }
        catch(Exception except) {
        JOptionPane.showMessageDialog(null, "The File cannot be opened!", "Error!", JOptionPane.INFORMATION_MESSAGE);
        }
        }
      }
    });
    
    decodetextbt.addActionListener(new ActionListener() {//DECODE BUTTON LISTENER
      public void actionPerformed(ActionEvent e) {
        if(stat_name == "" && stat_path =="")
        {
        JOptionPane.showMessageDialog(null, "Please select an image to decode" , "Error!", JOptionPane.INFORMATION_MESSAGE);
        return;//exit the action listener as to not encode the wrong thing
        }
        TextDecoder model = new TextDecoder();
        mess = model.textdecode(stat_path, stat_name, ext);
        outmess.setText(mess);
        if(stat_name == "" && stat_path =="")
        {
        JOptionPane.showMessageDialog(null, "The image to encode must have a smaller width and height than the containing image" , "Error!", JOptionPane.INFORMATION_MESSAGE);
        return;//exit the action listener as to not encode the wrong thing
        }
        Logger textDecodeLog = new Logger();
        textDecodeLog.logger("Text Decoded From Image, File Name: " +" "+ stat_name);
      }
    });

    //SET UP THE PANEL SIZE FOR DISPLAY   

     Color mybackground = new Color(225,225,225); // Color white
   
     JPanel decodeTextPanel = new JPanel(); 
     decodeTextPanel.setOpaque(true);
     decodeTextPanel.setBackground(mybackground);
     //leftPanel.setBorder(BorderFactory.createTitledBorder("Left Panel"));
     
     decodeTextPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
     
     GroupLayout layout = new GroupLayout(decodeTextPanel);
     layout.setAutoCreateGaps(true);
     layout.setAutoCreateContainerGaps(true);
     
////////////////////////////////////////////////////////////////////////////HorizontalLayout         
     layout.setHorizontalGroup(layout.createSequentialGroup()
                                 .addGroup(layout.createParallelGroup(LEADING)
                                             .addGroup(layout.createParallelGroup(CENTER)
                                                         .addComponent(back1))        
                                             .addGroup(layout.createSequentialGroup()
                                                         .addGroup(layout.createParallelGroup(CENTER)
                                                                     .addComponent(decodingtextlabel))
                                                         .addGroup(layout.createParallelGroup(LEADING)
                                                                     .addComponent(searchdecodetextbt))
                                                         .addGroup(layout.createParallelGroup(LEADING)
                                                                     .addComponent(clearimage)))
                                             .addGroup(layout.createSequentialGroup()  
                                                         .addGap (123)
                                                         .addComponent(image)  
                                                         .addGap (123))
                                             .addGroup(layout.createSequentialGroup()       
                                                         .addComponent(outmess))
                                             .addGroup(layout.createSequentialGroup()
                                                         .addGroup(layout.createParallelGroup(LEADING)
                                                                     .addComponent(cleartext)  )          
                                                         .addGroup(layout.createParallelGroup(LEADING)
                                                                     .addComponent(decodetextbt)))                 
                                          )        
                              );
////////////////////////////////////////////////////////////////////////////VerticalLayout            
     layout.setVerticalGroup(layout.createSequentialGroup()
                               .addGroup(layout.createParallelGroup(CENTER)
                                           .addComponent(back1))
                               .addGroup(layout.createParallelGroup(CENTER)        
                                           .addComponent(decodingtextlabel)
                                           .addComponent(searchdecodetextbt)
                                           .addComponent(clearimage))
                               .addGroup(layout.createParallelGroup(CENTER)
                                           .addComponent(image))  
                               .addGroup(layout.createParallelGroup(CENTER)
                                           .addComponent(outmess)) 
                               .addGroup(layout.createParallelGroup(CENTER)
                                           .addComponent(cleartext)
                                           .addComponent(decodetextbt))                
                            );
     /////////////////////////////////////////////////////////////////////////////////////Makes the buttons the same size        
     layout.linkSize(SwingConstants.HORIZONTAL,back1,clearimage, cleartext, decodetextbt);
     layout.linkSize(SwingConstants.VERTICAL, back1,clearimage, cleartext, decodetextbt) ;
     
     layout.linkSize(SwingConstants.HORIZONTAL, outmess);
     layout.linkSize(SwingConstants.VERTICAL, outmess);
     
     layout.linkSize(SwingConstants.HORIZONTAL, image);
     layout.linkSize(SwingConstants.VERTICAL, image);
     
     decodeTextPanel.setLayout(layout);
     
     //Finish making the frame
     frame.setResizable(true);
     frame.setContentPane(decodeTextPanel);
     frame.pack();
     frame.setLocationRelativeTo(null);
     frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
     frame.setVisible(true);
  }
  
   //RESIZE THE IMAGE FOR THE DISPLAY
  public ImageIcon ResizeImage(String ImagePath)
  {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        double width = MyImage.getIconWidth();
        double height = MyImage.getIconHeight();
        if(width > height)
        {height = (400/width)*(height); width = 400;}
        else
        {width = (400/height)*(width); height = 400;}
 
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance((int)width, (int)height, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
}
