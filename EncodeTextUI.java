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
import java.awt.Dimension;

import javax.swing.JFrame;

public class EncodeTextUI {

  private JTextArea textInput;
  private String stat_path = "";
  private String stat_name = "";
  private String ext = "";
  private  String imagest;
  private String text;
  private String newFileName;
  private JLabel image;
  private JLabel image2;
  
  
  public EncodeTextUI()
  {
    JFrame frame = new JFrame("Encode Text into Image");
    JLabel encodingtextlabel1 = new JLabel("Select an Image File");//LABEL FOR SEARCH BUTTON
    JLabel encodingtextlabel2 = new JLabel("Input 500 characters or less of text to encode");//LABEL FOR TEXT BOX
    image = new JLabel();//IMAGE TO CONTAIN THE TEXT
    image2 = new JLabel();//ENCODED IMAGE
    textInput = new JTextArea();//TEXT AREA INPUT
    textInput.setLineWrap(true);    
    JComboBox<String> c1 = new JComboBox<String>(new String[] {"One Bit Encoding"});
     
    
    


    textInput.setPreferredSize(new Dimension(813, 100));
    textInput.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
    
    String hold = "C:\\Users\\Natalie\\Desktop\\Camo Project Improved/BlankSpace.jpg ";
    image.setIcon(ResizeImage(hold));
    image2.setIcon(ResizeImage(hold));
    image.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
    image2.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
   
    
    Dimension size = new Dimension(400, 400);
    image.setPreferredSize(size);
    image.setMinimumSize(size);
    image2.setPreferredSize(size);
    image2.setMinimumSize(size);    
   
    
    
     //ALL THE BUTTONS
    JButton encodetextbt = new JButton("Encode");
    JButton searchbt = new JButton("Search");
    JButton back1 = new JButton("Back");
    JButton clearimage = new JButton("Clear");
    JButton cleartext = new JButton("Clear");
    JButton zoomone = new JButton("Zoom In");
    JButton zoomtwo = new JButton("Zoom In");

    //ALL THE LISTENERS
    back1.addActionListener(new ActionListener() {//BACK BUTTON LISTENER
      public void actionPerformed(ActionEvent e) 
      { new MainPage();
        frame.dispose(); 
      }
    });
        
    clearimage.addActionListener(new ActionListener() {//CLEAR SEARCHED IMAGE LISTENER
      public void actionPerformed(ActionEvent e) {
        image.setIcon(ResizeImage(hold));
        image2.setIcon(ResizeImage(hold));
        stat_path = "";
        stat_name = "";
        ext = "";
      }
    });
    
    cleartext.addActionListener(new ActionListener() {//CLEAR TEXT LISTENER
      public void actionPerformed(ActionEvent e) {
        textInput.setText("");
      }
    });
    
    zoomone.addActionListener(new ActionListener() {//ZOOM BUTTON FOR ORIGINAL IMAGE LISTENER
      public void actionPerformed(ActionEvent e) {
        try{new ImageZoom( stat_path + "/" + stat_name + "." + ext , "Image Zoom In and Zoom Out Original Image");
        }
        catch(Exception except){}
      }
    });
    
    zoomtwo.addActionListener(new ActionListener() {//ZOOM BUTTON FOR ENCODED IMAGE
      public void actionPerformed(ActionEvent e) {
        try{new ImageZoom(stat_path + "/"  + newFileName + "." + ext, "Image Zoom In and Zoom Out Encoded Image");
        }
        catch(Exception except){}
      }
    });     

    
    searchbt.addActionListener(new ActionListener() {//SEARCH BUTTON LISTENER
      public void actionPerformed(ActionEvent e) {
 
        //image2.setIcon(ResizeImage(""));
        image2.setIcon(ResizeImage(hold));
        
        JFileChooser chooser = new JFileChooser("./");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setFileFilter(new Checker());
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION){
          File directory = chooser.getSelectedFile();
          
          try{
          ext  = Checker.getExtension(directory);
          imagest = directory.getPath();
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
    
    encodetextbt.addActionListener(new ActionListener() {//ENCODE BUTTON LISTENER
      public void actionPerformed(ActionEvent e) {
        TextEncoder model = new TextEncoder();
        text = textInput.getText(); //text to encode into the image
        
        //Check the text length
        Checker textcheck = new Checker();
        boolean texttooLarge = textcheck.textChecker(text);
        if(texttooLarge)
        {
          JOptionPane.showMessageDialog(null, "Please input 500 characters or less", "Error!", JOptionPane.INFORMATION_MESSAGE);
          return;
        }
        
        //Check the image size
        boolean tooSmall = textcheck.textimageChecker(imagest);
        if(tooSmall)
        {
          JOptionPane.showMessageDialog(null, "The Image is Too Small to Contain The Message", "Error!", JOptionPane.INFORMATION_MESSAGE);
          return;
        }
        
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmmss");
        Date date = new Date();
        newFileName = dateFormat.format(date);
        newFileName =  newFileName  + "Text" +  stat_name;  
        String holder = stat_path + "/"  + newFileName + "." + ext;//the entire path
        System.out.println(holder);
        //String newFileName = JOptionPane.showInputDialog("Enter a File Name for the Encoded Image:");//get the new file name
        //File temholder =new File(holder);//does this file already exists?
        //boolean exists = temholder.exists();//does this file already exists?
        /*//File nameing 
         while(exists || newFileName.length() == 0)//While this file exists ask for another file name
         {  
         JOptionPane.showMessageDialog(null, "This File Already Exists Please Try Again", "Error!", JOptionPane.INFORMATION_MESSAGE);
         newFileName = JOptionPane.showInputDialog("Enter a UNIQUE File Name for the Encoded Image:");//get the new file name
         holder = stat_path + "/" + newFileName + "." + ext;
         temholder =new File(holder);
         exists = temholder.exists();
         }
         */
        if(stat_path =="" && stat_name=="")
        {
          JOptionPane.showMessageDialog(null, "No image was selected!\nPlease select an image.", "Error!", JOptionPane.INFORMATION_MESSAGE);
          return;
        }
        model.textencode(stat_path, stat_name, ext, newFileName, text);//encodes image and saves images
        image2.setIcon(ResizeImage(holder));
        
        Logger textEncodeLog = new Logger();
        textEncodeLog.logger("Text Encoded into Image, File Name: " +" "+ newFileName);
      }
    });
    
    //SET UP THE PANEL SIZE FOR DISPLAY      
   
    Color mybackground = new Color(225,225,225); // Color white
   
    JPanel encodeTextPanel = new JPanel(); 
    encodeTextPanel.setOpaque(true);
    encodeTextPanel.setBackground(mybackground);
    //leftPanel.setBorder(BorderFactory.createTitledBorder("Left Panel"));
    
    encodeTextPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
    
    GroupLayout layout = new GroupLayout(encodeTextPanel);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);
////////////////////////////////////////////////////////////////////////////HorizontalLayout           
    layout.setHorizontalGroup(layout.createSequentialGroup()                               
                                .addGroup(layout.createParallelGroup(LEADING)
                                            
                                            
                                            .addGroup(layout.createParallelGroup(LEADING)
                                                        .addComponent(back1))
                                            .addGroup(layout.createSequentialGroup() 
                                                        .addGroup(layout.createParallelGroup(LEADING)
                                                                    .addComponent(encodingtextlabel1))
                                                        
                                                        .addGroup(layout.createParallelGroup(LEADING)
                                                                    .addComponent(searchbt))
                                                        
                                                        .addGroup(layout.createParallelGroup(LEADING)
                                                                    .addComponent(clearimage))  
                                                     )
                                            .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(LEADING)
                                                                    .addComponent(image)  
                                                                    .addComponent(zoomone)  
                                                                    .addComponent(encodingtextlabel2) )     
                                                        .addGroup(layout.createParallelGroup(LEADING)
                                                                    .addComponent(image2) 
                                                                    .addComponent(zoomtwo))       
                                                     )
                                            
                                            .addComponent(textInput)  
                                            
                                            .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(LEADING)
                                                                    .addComponent(cleartext)  )     
                                                        .addGroup(layout.createParallelGroup(LEADING)
                                                                    .addComponent(c1))      
                                                        .addGroup(layout.createParallelGroup(LEADING)
                                                                    .addComponent(encodetextbt) )
                                                     )                 
                                         )        
                             );
////////////////////////////////////////////////////////////////////////////VerticalLayout           
    layout.setVerticalGroup(layout.createSequentialGroup()
                              .addGroup(layout.createParallelGroup(LEADING)
                                          .addComponent(back1))
                              .addGroup(layout.createParallelGroup(LEADING)        
                                          .addComponent(encodingtextlabel1)
                                          .addComponent(searchbt)
                                          .addComponent(clearimage))
                              .addGroup(layout.createParallelGroup(LEADING)
                                          .addComponent(image)
                                          .addComponent(image2))  
                              .addGroup(layout.createParallelGroup(LEADING)
                                          .addComponent(zoomone)
                                          .addComponent(zoomtwo))  
                              .addGroup(layout.createParallelGroup(LEADING)
                                          .addComponent(encodingtextlabel2))
                              .addGroup(layout.createParallelGroup(LEADING)
                                          .addComponent(textInput))
                              .addGroup(layout.createParallelGroup(LEADING)
                                          .addComponent(cleartext)
                                          .addComponent(c1)
                                          .addComponent(encodetextbt))
                           );
    /////////////////////////////////////////////////////////////////////////////////////Makes the buttons the same size     
    layout.linkSize(SwingConstants.HORIZONTAL, back1, zoomone,zoomtwo, searchbt, clearimage, cleartext,  encodingtextlabel1, encodetextbt);
    layout.linkSize(SwingConstants.VERTICAL, back1, zoomone,zoomtwo, searchbt, clearimage, cleartext,  encodingtextlabel1, encodetextbt);
    
    layout.linkSize(SwingConstants.HORIZONTAL, textInput);
    layout.linkSize(SwingConstants.VERTICAL, textInput);
    layout.linkSize(SwingConstants.HORIZONTAL, c1);
    layout.linkSize(SwingConstants.VERTICAL, c1);
    
    encodeTextPanel.setLayout(layout);
    
    //Finish making the frame
    frame.setResizable(true);
    frame.setContentPane(encodeTextPanel);
    frame.pack();
    frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);   
    frame.setLocationRelativeTo(null);
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