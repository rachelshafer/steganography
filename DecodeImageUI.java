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

public class DecodeImageUI {

  private String stat_path = "";
  private String stat_name = "";
  private JLabel image;
  private JLabel image2;
  private String ext ="";
  private String newFileName ="";
  
  public DecodeImageUI()
  {
    
    JFrame frame = new JFrame("Decode Image From Image");//NAME THE JFRAME
    JPanel pa = new JPanel();//CREATE THE PANEL
    JLabel decodingimagelabel = new JLabel("Select an Image file");//LABEL FOR SEARCH BUTTON
    image = new JLabel();//ENCODED IMAGE
    image2 = new JLabel();//DECODED IMAGE

    String hold = "C:\\Users\\Natalie\\Desktop\\Camo Project Improved/BlankSpace.jpg ";
    image.setIcon(ResizeImage(hold));
    image.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
    image2.setIcon(ResizeImage(hold));
    image2.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
    
    image.setPreferredSize(new Dimension(400, 400));//hint at size
    image2.setPreferredSize(new Dimension(400, 400));//hint at size
    
    
    
            Dimension size = new Dimension(400, 400);
    image.setPreferredSize(size);
    image.setMinimumSize(size);
    image2.setPreferredSize(size);
    image2.setMinimumSize(size);   
     
    
    
    
    //ALL THE BUTTONS
    JButton decodeimagebt = new JButton("Decode");
    JButton searchbt = new JButton("Search");
    JButton clearimage = new JButton("Clear");
    JButton clearimage2 = new JButton("Clear");
    JButton back1 = new JButton("Back"); 
    JButton zoomone = new JButton("Zoom In/Out");
    JButton zoomtwo = new JButton("Zoom In/Out");

    //ALL THE LISTENERS
    back1.addActionListener(new ActionListener() {//BACK BUTTON LISTENER
      public void actionPerformed(ActionEvent e) {
        new MainPage();
        frame.dispose();
      }
    });
        
    clearimage.addActionListener(new ActionListener() {//CLEAR SEARCHED IMAGE LISTENER
      public void actionPerformed(ActionEvent e) {
        image.setIcon(ResizeImage(hold));
        image2.setIcon(ResizeImage(hold));
        stat_path = "";
        stat_name = "";
      }
    });
    
    clearimage2.addActionListener(new ActionListener() {//CLEAR SEARCHED IMAGE LISTENER
      public void actionPerformed(ActionEvent e) {
        image2.setIcon(ResizeImage(hold));
        newFileName ="";
      }
    });
        
    zoomone.addActionListener(new ActionListener() {//ZOOM BUTTON FOR ENCODED IMAGE LISTENER
      public void actionPerformed(ActionEvent e) {
        try{if(stat_path ==  "")
               return;
        new ImageZoom(stat_path + "/" + stat_name + "." + ext , "Image Zoom In and Zoom Out Original Image");
        }
        catch(Exception except){}
      }
    });
    
    zoomtwo.addActionListener(new ActionListener() {//ZOOM BUTTON FOR DECODED IMAGE LISTENER
      public void actionPerformed(ActionEvent e) {
        try{if(newFileName ==  "")
               return;
        new ImageZoom(stat_path + "/"  + newFileName + "." + ext, "Image Zoom In and Zoom Out Encoded Image");
        }
        catch(Exception except){}
      }
    });     

    searchbt.addActionListener(new ActionListener() {//SEARCH BUTTON LISTENER
      public void actionPerformed(ActionEvent e) {
 
        JFileChooser chooser = new JFileChooser("./");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setFileFilter(new Checker());
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION){
          File directory = chooser.getSelectedFile();
          
          try{
            ext  = Checker.getExtension(directory);
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
    
    decodeimagebt.addActionListener(new ActionListener() {//DECODE BUTTON LISTENER
      public void actionPerformed(ActionEvent e) {       
         DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmmss");
         Date date = new Date();
         newFileName = dateFormat.format(date);
         newFileName = stat_name + "Decoded" + newFileName;
         String holder = stat_path + "/"  + newFileName + "." + ext;
        
         ImageDecoders model = new ImageDecoders();
         model.imagedecoder(stat_path, stat_name, ext, newFileName);          
         image2.setIcon(ResizeImage(holder));

         Logger imageDecodecodeLog = new Logger();
         imageDecodecodeLog.logger("Image Decoded From Image, File Name: " +" "+ stat_name);
      }
    });

//SET UP THE PANEL SIZE FOR DISPLAY   
    Color mybackground = new Color(225,225,225); // Color white
   
    JPanel decodeImagePanel = new JPanel(); 
    decodeImagePanel.setOpaque(true);
    decodeImagePanel.setBackground(mybackground);        
    decodeImagePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
    
    GroupLayout layout = new GroupLayout(decodeImagePanel);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);
    
    ////////////////////////////////////////////////////////////////////////////HorizontalLayout    
    layout.setHorizontalGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(LEADING) 
                                               .addGroup(layout.createParallelGroup(CENTER)
                                                           .addComponent(back1))
                                            .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(CENTER)
                                                                    .addComponent(decodingimagelabel))
                                                        .addGroup(layout.createParallelGroup(LEADING)
                                                                    .addComponent(searchbt))
                                                        .addGroup(layout.createParallelGroup(LEADING)
                                                                    .addComponent(clearimage)) 
                                                     )
                                            .addGroup(layout.createSequentialGroup()  
                                                        .addComponent(image))
                                            .addGroup(layout.createSequentialGroup()       
                                                        .addComponent(zoomone))
                                            .addGroup(layout.createSequentialGroup()       
                                                        .addComponent(decodeimagebt))                 
                                         )
                                .addGroup(layout.createParallelGroup(LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(LEADING)
                                                                    .addComponent(clearimage2)) 
                                                     )
                                            .addGroup(layout.createSequentialGroup()  
                                                        .addComponent(image2))  
                                            .addGroup(layout.createSequentialGroup()       
                                                        .addComponent(zoomtwo)) 
                                         )                           
                             );
////////////////////////////////////////////////////////////////////////////VerticalLayout               
    layout.setVerticalGroup(layout.createSequentialGroup()
                              .addGroup(layout.createParallelGroup(CENTER)
                                          .addComponent(back1))
                              .addGroup(layout.createParallelGroup(CENTER)
                                          .addComponent(decodingimagelabel)
                                          .addComponent(searchbt)
                                          .addComponent(clearimage)
                                          .addComponent(clearimage2))
                              .addGroup(layout.createParallelGroup(CENTER)
                                          .addComponent(image)
                                          .addComponent(image2))  
                              .addGroup(layout.createParallelGroup(CENTER)
                                          .addComponent(zoomone)
                                          .addComponent(zoomtwo))  
                              .addGroup(layout.createParallelGroup(CENTER)          
                                          .addComponent(decodeimagebt))
                           );
    
    /////////////////////////////////////////////////////////////////////////////////////Makes the buttons the same size      
    layout.linkSize(SwingConstants.HORIZONTAL, back1, zoomone,zoomtwo, clearimage, clearimage2, decodeimagebt, searchbt);
    layout.linkSize(SwingConstants.VERTICAL, back1 , zoomone, zoomtwo, clearimage, clearimage2, decodeimagebt, searchbt) ;
    
    layout.linkSize(SwingConstants.HORIZONTAL, image, image2);
    layout.linkSize(SwingConstants.VERTICAL, image, image2);
    
    decodeImagePanel.setLayout(layout);
    
    
    //Finish making the frame
    frame.setContentPane(decodeImagePanel);
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
