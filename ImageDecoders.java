//Rachel Shafer
//Image Decoder

import java.io.File;
 import java.awt.image.BufferedImage;
 import java.awt.image.WritableRaster;
 import java.awt.image.DataBufferByte;
 import javax.imageio.ImageIO;
 import javax.swing.JOptionPane;
 import java.awt.Color; 

public class ImageDecoders{
  
     public boolean imagedecoder(String path, String name, String ext1, String stegan)
     {
         String          file_name1   = image_path(path,name,ext1);
         BufferedImage   coverimage  = getImage(file_name1);
        
         //user space is not necessary for Encrypting
         boolean hiddenimage = flagcheck(coverimage);
         
         if(hiddenimage==true)
         {
           BufferedImage image = separateimages(coverimage);
           return(setImage(image,new File(image_path(path,stegan,ext1)),"png"));
         }
         return true;
    
     } 
  
    /*
      *Returns the complete path of a file, in the form: path\name.ext
      *@param path   The path (folder) of the file
      *@param name The name of the file
     *@param ext      The extension of the file
      *@return A String representing the complete path of a file
      */
     private String image_path(String path, String name, String ext)
     {
         return path + "/" + name + "." + ext;
     }
      
     /*
      *Get method to return an image file
      *@param f The complete path name of the image.
      *@return A BufferedImage of the supplied file path
      *@see  Steganography.image_path
    */
     private BufferedImage getImage(String f)
     {
         BufferedImage image  = null;
         File file  = new File(f);
          
         try
         {
             image = ImageIO.read(file);
         }
        catch(Exception ex)
         {
             JOptionPane.showMessageDialog(null,
               "Image could not be read!","Error",JOptionPane.ERROR_MESSAGE);
         }
         return image;
    }
      
    /*
      *Set method to save an image file
      *@param image The image file to save
      *@param file     File  to save the image to
      *@param ext      The extension and thus format of the file to be saved
     *@return Returns true if the save is succesful
      */
    private boolean setImage(BufferedImage image, File file, String ext)
    {
         try
         {
             file.delete(); //delete resources used by the File
             ImageIO.write(image,ext,file);
             return true;
        }
        catch(Exception e)
         {
             JOptionPane.showMessageDialog(null,
               "File could not be saved!","Error",JOptionPane.ERROR_MESSAGE);
             return false;
         }
     }
    
    private boolean flagcheck(BufferedImage coverimage)
    {
      //Check for flag
      String flag = "";
      for (int i = 0; i < 2; i++) {
        Color flag_pixel = new Color(coverimage.getRGB(i, 0));
        int flag_red = flag_pixel.getRed();
        int flag_blue = flag_pixel.getBlue();
        int flag_green = flag_pixel.getGreen();
        String flag_red_binary = String.format("%8s", Integer.toBinaryString(flag_red)).replace(' ', '0');
        String flag_blue_binary = String.format("%8s", Integer.toBinaryString(flag_blue)).replace(' ', '0');
        String flag_green_binary = String.format("%8s", Integer.toBinaryString(flag_green)).replace(' ', '0');
        String flag_red_bits = flag_red_binary.substring(6, 8);
        String flag_blue_bits = flag_blue_binary.substring(6, 8);
        String flag_green_bits = flag_green_binary.substring(6, 8);
        flag = flag + flag_red_bits + flag_green_bits + flag_blue_bits;
        
      }
      String flagfinal = flag.substring(0, 10);
      int flagfinalint = Integer.parseInt(flagfinal, 2);
      if(flagfinalint == 1023)
      {
        return true;
        
      }
      JOptionPane.showMessageDialog(null,
                 "There is no hidden message in this image!","Error",
                 JOptionPane.ERROR_MESSAGE);
      return false;
    }
   
 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private BufferedImage separateimages(BufferedImage coverimage)
    {
      //get encode method
      String encodemethod = "";
      Color height_pixel = new Color(coverimage.getRGB(1, 0));
      int method = height_pixel.getBlue();
      String method_binary = String.format("%8s", Integer.toBinaryString(method)).replace(' ', '0');
      String method_bits= method_binary.substring(6, 8);
      int methodint = Integer.parseInt(method_bits, 2);
      if(methodint==2)
      {
        encodemethod = "4bits";
      }
      else if(methodint==1)
      {
        encodemethod = "3bits";
      }
      //get height and width for hidden image
      String height = "", width = "";
      for (int i = 2; i < 8; i++) {
        
        Color hw_pixel = new Color(coverimage.getRGB(i, 0));
        int hw_red = hw_pixel.getRed();
        int hw_blue = hw_pixel.getBlue();
        int hw_green = hw_pixel.getGreen();
        String hw_red_binary = String.format("%8s", Integer.toBinaryString(hw_red)).replace(' ', '0');
        String hw_blue_binary = String.format("%8s", Integer.toBinaryString(hw_blue)).replace(' ', '0');
        String hw_green_binary = String.format("%8s", Integer.toBinaryString(hw_green)).replace(' ', '0');
        String hw_red_bits = hw_red_binary.substring(6, 8);
        String hw_blue_bits = hw_blue_binary.substring(6, 8);
        String hw_green_bits = hw_green_binary.substring(6, 8);
        if(i<5)
        {
          height = height + hw_red_bits + hw_green_bits + hw_blue_bits;
        }
        else
        {
          width = width + hw_red_bits + hw_green_bits + hw_blue_bits;
        }
      }
      
      int h_heightint = Integer.parseInt(height, 2);
      int h_widthint = Integer.parseInt(width, 2);
    
      //start decoding
      int w_cover = coverimage.getWidth();
      int h_cover = coverimage.getHeight();
                            //h_hidden
          for (int i = 0; i < h_cover; i++) {
                               //w_hidden
            for (int j = 0; j < w_cover; j++) {
              Color c_pixel = new Color(coverimage.getRGB(j, i));
              
              //get rgb of c_pixel
              int c_alpha = c_pixel.getAlpha();
              int c_red = c_pixel.getRed();
              int c_blue = c_pixel.getBlue();
              int c_green = c_pixel.getGreen();           ////////////////////////////////////////////
             
              int cpixel = coverimage.getRGB(j, i);
              String c_binarypixel = Integer.toBinaryString(cpixel);   ////////////////////////////////////////
           
              //get binary values

              String c_alpha_binary = String.format("%8s", Integer.toBinaryString(c_alpha)).replace(' ', '0');
              String c_red_binary = String.format("%8s", Integer.toBinaryString(c_red)).replace(' ', '0');
              String c_Blue_binary = String.format("%8s", Integer.toBinaryString(c_blue)).replace(' ', '0');
              String c_Green_binary = String.format("%8s", Integer.toBinaryString(c_green)).replace(' ', '0');
              
              String new_alpha = "", new_red = "", new_green = "", new_blue = "";
              
              if(encodemethod == "4bits")
              {
              //separate alpha
              String h_alpha_highbits = c_alpha_binary.substring(4, 8);
              //separate red
              String h_red_highbits = c_red_binary.substring(4, 8);              
              //separate Green
              String h_green_highbits = c_Green_binary.substring(4, 8);  
              //separate Blue
              String h_blue_highbits = c_Blue_binary.substring(4, 8);              
              
              new_alpha = h_alpha_highbits + "0000";
              new_red = h_red_highbits + "0000";
              new_green = h_green_highbits + "0000";
              new_blue = h_blue_highbits + "0000";
              }
              
              
              if(encodemethod == "3bits")
              {
              //separate alpha
              String h_alpha_highbits = c_alpha_binary.substring(5, 8);
              //separate red
              String h_red_highbits = c_red_binary.substring(5, 8);              
              //separate Green
              String h_green_highbits = c_Green_binary.substring(5, 8);  
              //separate Blue
              String h_blue_highbits = c_Blue_binary.substring(5, 8);              
              
              new_alpha = h_alpha_highbits + "00000";
              new_red = h_red_highbits + "00000";
              new_green = h_green_highbits + "00000";
              new_blue = h_blue_highbits + "00000";
              }
              
              int new_alpha_int = Integer.parseInt(new_alpha, 2);
              int new_red_int = Integer.parseInt(new_red, 2);
              int new_green_int = Integer.parseInt(new_green, 2);
              int new_blue_int = Integer.parseInt(new_blue, 2);
              
              int new_pixelint = (new_alpha_int << 24) | (new_red_int << 16) | (new_green_int << 8) | new_blue_int;
              coverimage.setRGB(j, i, new_pixelint);
            }
          } 
      
          return coverimage.getSubimage(0, 1, h_widthint, h_heightint);
     }
  
}