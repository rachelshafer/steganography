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

public class EncodeImageUI {

  private String  stat_path = "";
  private String  stat_name = "";
  private String  ext = "";
  private String  stat_path_2 = "";
  private String  stat_name_2 = "";
  private String  ext_2 = "";
  private String newFileName;
  private String method;
  private String imagest;
  private String imagest2;
  private JLabel image;
  private JLabel image2;
  private JLabel image3;
  
  public EncodeImageUI()
  {
    JFrame frame = new JFrame("Encode Image into Image");//NAME THE JFRAME
    //JPanel pa = new JPanel(); //CREATE THE PANEL
    JLabel encodingimagelabel1 = new JLabel("Select an Image File to Encode");//LABEL FOR SEARCH BUTTON
    JLabel encodingimagelabel2 = new JLabel("Select an Image File to Contain the Encoded Image");//LABEL FOR THE OTHER SEARCH BUTTON
    image = new JLabel();//IMAGE TO ENCODE
    image2 = new JLabel();//IMAGE TO CONTAIN
    image3 = new JLabel();//ENCODED IMAGE
    
    String hold = "C:\\Users\\Natalie\\Desktop\\Camo Project Improved/BlankSpace.jpg ";
    image.setIcon(ResizeImage(hold));
    image2.setIcon(ResizeImage(hold));
    image3.setIcon(ResizeImage(hold));
    image.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
    image2.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
    image3.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
    
    
        Dimension size = new Dimension(400, 400);
    image.setPreferredSize(size);
    image.setMinimumSize(size);
    image2.setPreferredSize(size);
    image2.setMinimumSize(size);   
    image3.setPreferredSize(size);
    image3.setMinimumSize(size);  
    
    
    //ALL THE BUTTONS
    JButton encodeimagebt = new JButton("Encode");
    JButton searchencode1bt = new JButton("Search");
    JButton searchencode2bt = new JButton("Search");
    JButton clearimage = new JButton("Clear");
    JButton clearimage2 = new JButton("Clear");
    JButton clearimage3 = new JButton("Clear");
    JButton zoomone = new JButton("Zoom");
    JButton zoomtwo = new JButton("Zoom");
    JButton zoomthree = new JButton("Zoom");
    JButton back1 = new JButton("Back");
    JComboBox<String> c1 = new JComboBox<String>(new String[] {"3 Bit Encoded Image","4 Bit Encoded Image"});

    //ALL THE LISTENERS
    back1.addActionListener(new ActionListener() {//BACK BUTTON LISTENER
      public void actionPerformed(ActionEvent e) { 
        new MainPage();
        frame.dispose(); 
      }
    });
    
    clearimage.addActionListener(new ActionListener() {//CLEAR SEARCHED IMAGE TO ENCODE
      public void actionPerformed(ActionEvent e) {
        image.setIcon(ResizeImage(hold));
        image3.setIcon(ResizeImage(hold));
        stat_path = "";
        stat_name = "";
        ext = "";
      }
    });
     
    clearimage2.addActionListener(new ActionListener() {//CLEAR SEARCHED IMAGE TO CONTAIN
      public void actionPerformed(ActionEvent e) {
        image2.setIcon(ResizeImage(hold));
        image3.setIcon(ResizeImage(hold));
        stat_path_2 = "";
        stat_name_2 = "";
        ext_2 = "";
      }
    });
    
    clearimage3.addActionListener(new ActionListener() {//CLEAR SEARCHED IMAGE TO CONTAIN
      public void actionPerformed(ActionEvent e) {
        image3.setIcon(ResizeImage(hold));
      }
    });

    zoomone.addActionListener(new ActionListener() {//ZOOM BUTTON FOR IMAGE TO ENCODE
      public void actionPerformed(ActionEvent e) {
        try{new ImageZoom( stat_path + "/" + stat_name + "." + ext , "Image Zoom In and Zoom Out Image To Encode");
        }
        catch(Exception except){}
      }
    });
    
    zoomtwo.addActionListener(new ActionListener() {//ZOOM BUTTON FOR IMAGE TO CONTAIN
      public void actionPerformed(ActionEvent e) {
        try{new ImageZoom( stat_path_2 + "/" + stat_name_2 + "." + ext_2 , "Image Zoom In and Zoom Out Image To Contain"); 
        }
        catch(Exception except){}
      }
    });
    
    zoomthree.addActionListener(new ActionListener() {//ZOOM BUTTON FOR ENCODED IMAGE
      public void actionPerformed(ActionEvent e) {
        try{new ImageZoom(stat_path + "/"  + newFileName + "." + ext_2, "Image Zoom In and Zoom Out Encoded Image");
        }
        catch(Exception except){}
      }
    });    
    
    c1.addActionListener(new ActionListener() {//COMBO BOX FOR METHODS
      public void actionPerformed(ActionEvent event) {
        JComboBox c1 = (JComboBox)event.getSource();
        method = String.valueOf(c1.getSelectedItem());
      }
    });
      
    searchencode1bt.addActionListener(new ActionListener() {//SEARCH BUTTON LISTENER
      public void actionPerformed(ActionEvent e) {
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
     
    searchencode2bt.addActionListener(new ActionListener() {//SEARCH BUTTON LISTENER
      public void actionPerformed(ActionEvent e) {
 
        JFileChooser chooser = new JFileChooser("./");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setFileFilter(new Checker());
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION){
          File directory = chooser.getSelectedFile();
          try{
            ext_2  = Checker.getExtension(directory);
            imagest2 = directory.getPath();
            stat_name_2 = directory.getName();
            stat_path_2 = directory.getPath();
            stat_path_2 = stat_path_2.substring(0,stat_path_2.length()-stat_name_2.length()-1);
            stat_name_2 = stat_name_2.substring(0, stat_name_2.length()-4);      
            image2.setIcon(ResizeImage(imagest2));
          }
          
          catch(Exception except) {
            JOptionPane.showMessageDialog(null, "The File cannot be opened!", "Error!", JOptionPane.INFORMATION_MESSAGE);
          }
        }
      }
    });
  
      
    encodeimagebt.addActionListener(new ActionListener() {//ENCODE BUTTON LISTENER
      public void actionPerformed(ActionEvent e) {   
        
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmmss");
        Date date = new Date();
        newFileName = dateFormat.format(date);
        
        newFileName =  newFileName + "Image" + stat_name_2; 
        String holder = stat_path_2 + "/" + newFileName + "." + ext_2;//the entire path
        
        
        if(stat_path_2 == "" && stat_name_2 ==""|| stat_path == "" && stat_name == "")
        {
          JOptionPane.showMessageDialog(null, "Please select a image to encode and a image to Contain" , "Error!", JOptionPane.INFORMATION_MESSAGE);
          return;//exit the action listener as to not encode the wrong thing
        }
        
        
        //Check the image sizes
        Checker imagetoimagechecker = new Checker();
        boolean tooLarge = imagetoimagechecker.imageChecker(imagest, imagest2);
        if(tooLarge)
        {
          JOptionPane.showMessageDialog(null, "The image to encode must have a smaller width and height than the containing image" , "Error!", JOptionPane.INFORMATION_MESSAGE);
          return;//exit the action listener as to not encode the wrong thing
        }
        
        ImageEncoders model = new ImageEncoders();//model
        
        if(method == "4 Bit Encoded Image" )//4 bit method
        { model.imageencoder(stat_path, stat_name, ext, newFileName, stat_path_2, stat_name_2, ext_2, "4bits");}
        else//3 bit method
        { model.imageencoder(stat_path, stat_name, ext, newFileName, stat_path_2, stat_name_2, ext_2, "3bits");}        
        
        image3.setIcon(ResizeImage(holder));//display image
        Logger imageEncodecodeLog = new Logger();
        imageEncodecodeLog.logger("Image Encoded Into Image, File Name: " +" "+ newFileName);
        
      }
    });
    
    //SET UP THE PANEL SIZE FOR DISPLAY        
    
    Color mybackground = new Color(225,225,225); // Color white
   
    JPanel encodeImagePanel = new JPanel(); 
    encodeImagePanel.setOpaque(true);
    encodeImagePanel.setBackground(mybackground);
    encodeImagePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
    GroupLayout layout = new GroupLayout(encodeImagePanel);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);
////////////////////////////////////////////////////////////////////////////HorizontalLayout          
    layout.setHorizontalGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(LEADING)
                                            .addComponent(back1)
                                            .addComponent(encodingimagelabel1)     
                                            .addGroup(layout.createSequentialGroup()                 
                                                        .addGroup(layout.createParallelGroup(CENTER)
                                                                    .addComponent(searchencode1bt))
                                                        .addGroup(layout.createParallelGroup(LEADING)
                                                                    .addComponent(clearimage))
                                                     )                  
                                            .addGroup(layout.createSequentialGroup()  
                                                        .addComponent(image))
                                            .addGroup(layout.createSequentialGroup()       
                                                        .addComponent(zoomone))
                                         )
                                .addGroup(layout.createParallelGroup(LEADING)
                                            .addComponent(encodingimagelabel2)     
                                            .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(CENTER)
                                                                    .addComponent(searchencode2bt))
                                                        .addGroup(layout.createParallelGroup(LEADING)
                                                                    .addComponent(clearimage2))
                                                     )                  
                                            .addGroup(layout.createSequentialGroup()  
                                                        .addComponent(image2)  )
                                            .addGroup(layout.createSequentialGroup()       
                                                        .addComponent(zoomtwo))
                                            .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(LEADING)
                                                                    .addComponent(c1))          
                                                        .addGroup(layout.createParallelGroup(LEADING)
                                                                    .addComponent(encodeimagebt))
                                                     )         
                                         )   
                                .addGroup(layout.createParallelGroup(LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(LEADING)
                                                                    .addComponent(clearimage3))
                                                     )                  
                                            .addGroup(layout.createSequentialGroup()  
                                                        .addComponent(image3))
                                            .addGroup(layout.createSequentialGroup()       
                                                        .addComponent(zoomthree))
                                         )        
                             );
////////////////////////////////////////////////////////////////////////////VerticalLayout                
    layout.setVerticalGroup(layout.createSequentialGroup()
                              .addGroup(layout.createParallelGroup(CENTER)
                                          .addComponent(back1))
                              .addGroup(layout.createParallelGroup(CENTER)                   
                                          .addComponent(encodingimagelabel1)
                                          .addComponent(encodingimagelabel2))
                              .addGroup(layout.createParallelGroup(CENTER)
                                          .addComponent(searchencode1bt)
                                          .addComponent(clearimage)
                                          .addComponent(searchencode2bt)
                                          .addComponent(clearimage2)
                                          .addComponent(clearimage3))  
                              .addGroup(layout.createParallelGroup(CENTER)
                                          .addComponent(image)
                                          .addComponent(image2)
                                          .addComponent(image3))
                              .addGroup(layout.createParallelGroup(CENTER)
                                          .addComponent(zoomone)
                                          .addComponent(zoomtwo)
                                          .addComponent(zoomthree))
                              .addGroup(layout.createParallelGroup(CENTER)
                                          .addComponent(c1)
                                          .addComponent(encodeimagebt))
                           );
   /////////////////////////////////////////////////////////////////////////////////////Makes the buttons the same size      
    layout.linkSize(SwingConstants.HORIZONTAL,back1,zoomone,zoomtwo,zoomthree, clearimage,clearimage2, clearimage3, searchencode1bt,searchencode2bt, encodeimagebt);
    layout.linkSize(SwingConstants.VERTICAL, back1,zoomone,zoomtwo,zoomthree, clearimage,clearimage2, clearimage3, searchencode1bt,searchencode2bt, encodeimagebt) ;
    
    layout.linkSize(SwingConstants.HORIZONTAL, c1);
    layout.linkSize(SwingConstants.VERTICAL, c1);
    
    layout.linkSize(SwingConstants.HORIZONTAL, image, image2, image3);
    layout.linkSize(SwingConstants.VERTICAL, image, image2, image3);
    
    encodeImagePanel.setLayout(layout);
             
    //Finish making the frame
    frame.setContentPane(encodeImagePanel);
    frame.pack();
    frame.setResizable(true);
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