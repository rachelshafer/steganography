//Rachel Shafer
//Image Encoder

import java.io.File;
 import java.awt.image.BufferedImage;
 import java.awt.image.WritableRaster;
 import java.awt.image.DataBufferByte;
 import javax.imageio.ImageIO;
 import javax.swing.JOptionPane;

 import java.awt.Color; 


public class ImageEncoders{
  
     public boolean imageencoder(String path1, String original1, String ext1, String stegan, String path2, String original2, String ext2, String encodemethod)
     {
         String          file_name1   = image_path(path1,original1,ext1);
         BufferedImage   hiddenimage  = getImage(file_name1);
         
         String          file_name2   = image_path(path2,original2,ext2);
         BufferedImage   coverimage  = getImage(file_name2);
              
         //user space is not necessary for Encrypting
         BufferedImage image = mergeimages(hiddenimage, coverimage, encodemethod);
          
        return(setImage(image,new File(image_path(path1,stegan,ext2)),"png"));
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
 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private BufferedImage mergeimages(BufferedImage hiddenimage, BufferedImage coverimage, String encodemethod)
    {
      int counthiddden = 20, n=0, m=0, o=0, k=0;
      int w_hidden = hiddenimage.getWidth();
      int h_hidden = hiddenimage.getHeight();
      
      //hide flag and encode type
      {
        for (m = 0; m < 1; m++) {
                               
            for (n = 0; n < 2; n++) {
              Color c_pixel = new Color(coverimage.getRGB(n, m));
              int c_alpha = c_pixel.getAlpha();
              int c_red = c_pixel.getRed();
              int c_blue = c_pixel.getBlue();
              int c_green = c_pixel.getGreen();
              
              String c_alpha_binary = String.format("%8s", Integer.toBinaryString(c_alpha)).replace(' ', '0');
              String c_red_binary = String.format("%8s", Integer.toBinaryString(c_red)).replace(' ', '0');
              String c_Blue_binary = String.format("%8s", Integer.toBinaryString(c_blue)).replace(' ', '0');
              String c_Green_binary = String.format("%8s", Integer.toBinaryString(c_green)).replace(' ', '0');
              
              String c_alpha_highbits = c_alpha_binary.substring(0, 6);
              String c_red_highbits = c_red_binary.substring(0, 6);
              String c_blue_highbits = c_Blue_binary.substring(0, 6);
              String c_green_highbits = c_Green_binary.substring(0, 6);
              
              String new_alpha = c_alpha_highbits + "11";
              String new_red = c_red_highbits + "11";
              String new_green = c_green_highbits + "11";
              String new_blue = c_blue_highbits + "11";
              
              if(n==1&&encodemethod=="4bits")//hide encodetype in blue   10 = 4bits
              {
                new_blue = c_blue_highbits + "10";
              }
              else if(n==1&&encodemethod=="3bits")//hide encodetype in blue   01 = 3bits
              {
                new_blue = c_blue_highbits + "01";
              }

              
              int new_alpha_int = Integer.parseInt(new_alpha, 2);
              int new_red_int = Integer.parseInt(new_red, 2);
              int new_green_int = Integer.parseInt(new_green, 2);
              int new_blue_int = Integer.parseInt(new_blue, 2);
              
              int new_pixelint = (new_alpha_int << 24) | (new_red_int << 16) | (new_green_int << 8) | new_blue_int;
              
              coverimage.setRGB(n, m, new_pixelint);
              
              counthiddden--;
            }
        }
      }
    
      
      int countwh = 0;
      String height = String.format("%18s", Integer.toBinaryString(h_hidden)).replace(' ', '0');
      String width = String.format("%18s", Integer.toBinaryString(w_hidden)).replace(' ', '0');

      
      //hide width and height
      {  
                                      
            for (k = 2; k < 8; k++) {
              
              if(k==5)
              {
                countwh = 0;
              }
              
              String new_alpha, new_red, new_green, new_blue;
              Color c_pixel = new Color(coverimage.getRGB(k, 0));
              int c_alpha = c_pixel.getAlpha();
              int c_red = c_pixel.getRed();
              int c_blue = c_pixel.getBlue();
              int c_green = c_pixel.getGreen();
              
              String c_alpha_binary = String.format("%8s", Integer.toBinaryString(c_alpha)).replace(' ', '0');
              String c_red_binary = String.format("%8s", Integer.toBinaryString(c_red)).replace(' ', '0');
              String c_Blue_binary = String.format("%8s", Integer.toBinaryString(c_blue)).replace(' ', '0');
              String c_Green_binary = String.format("%8s", Integer.toBinaryString(c_green)).replace(' ', '0');
              
              String c_red_highbits = c_red_binary.substring(0, 6);
              String c_blue_highbits = c_Blue_binary.substring(0, 6);
              String c_green_highbits = c_Green_binary.substring(0, 6);
              
              if(k < 5)//height
              {
                new_red = c_red_highbits + height.substring(countwh, countwh+2);
                new_green = c_green_highbits + height.substring(countwh+2, countwh+4);
                new_blue = c_blue_highbits + height.substring(countwh+4, countwh+6);
              }
              else//width
              {
                new_red = c_red_highbits + width.substring(countwh, countwh+2);
                new_green = c_green_highbits + width.substring(countwh+2, countwh+4);
                new_blue = c_blue_highbits + width.substring(countwh+4, countwh+6);
              }
               
              int new_alpha_int = Integer.parseInt(c_alpha_binary, 2);
              int new_red_int = Integer.parseInt(new_red, 2);
              int new_green_int = Integer.parseInt(new_green, 2);
              int new_blue_int = Integer.parseInt(new_blue, 2);
              
              int new_pixelint = (new_alpha_int << 24) | (new_red_int << 16) | (new_green_int << 8) | new_blue_int;

              coverimage.setRGB(k, 0, new_pixelint);
              
              countwh = countwh + 6;
              
              
              counthiddden--;
            }
      }      
      
      
      //Start encoding

          for (int i = 0; i < h_hidden; i++) {

            for (int j = 0; j < w_hidden; j++) {

              Color h_pixel = new Color(hiddenimage.getRGB(j, i));
              Color c_pixel = new Color(coverimage.getRGB(j, i+1));
              
              //get rgb of h_pixel
              int h_alpha = h_pixel.getAlpha();
              int h_red = h_pixel.getRed();
              int h_blue = h_pixel.getBlue();
              int h_green = h_pixel.getGreen();
              
              //get rgb of c_pixel
              int c_alpha = c_pixel.getAlpha();
              int c_red = c_pixel.getRed();
              int c_blue = c_pixel.getBlue();
              int c_green = c_pixel.getGreen();
   
              int hpixel = hiddenimage.getRGB(j, i);
              String h_binarypixel = Integer.toBinaryString(hpixel);
              
              int cpixel = coverimage.getRGB(j, i+1);
              String c_binarypixel = Integer.toBinaryString(cpixel); 
              
              
              //get binary values           
              String h_alpha_binary = String.format("%8s", Integer.toBinaryString(h_alpha)).replace(' ', '0');
              String h_red_binary = String.format("%8s", Integer.toBinaryString(h_red)).replace(' ', '0');
              String h_Blue_binary = String.format("%8s", Integer.toBinaryString(h_blue)).replace(' ', '0');
              String h_Green_binary = String.format("%8s", Integer.toBinaryString(h_green)).replace(' ', '0');

              String c_alpha_binary = String.format("%8s", Integer.toBinaryString(c_alpha)).replace(' ', '0');
              String c_red_binary = String.format("%8s", Integer.toBinaryString(c_red)).replace(' ', '0');
              String c_Blue_binary = String.format("%8s", Integer.toBinaryString(c_blue)).replace(' ', '0');
              String c_Green_binary = String.format("%8s", Integer.toBinaryString(c_green)).replace(' ', '0');
             
              String h_alpha_highbits = "", c_alpha_highbits = "", h_red_highbits = "", c_red_highbits = "", h_green_highbits = "", c_green_highbits = "", h_blue_highbits = "", c_blue_highbits = "";
     
              if(encodemethod=="4bits")
              {
                //merge alpha
                h_alpha_highbits = h_alpha_binary.substring(0, 4);
                c_alpha_highbits = c_alpha_binary.substring(0, 4);
                //merge red
                h_red_highbits = h_red_binary.substring(0, 4);
                c_red_highbits = c_red_binary.substring(0, 4);              
                //merge Green
                h_green_highbits = h_Green_binary.substring(0, 4);
                 c_green_highbits = c_Green_binary.substring(0, 4);  
                //merge Blue
                h_blue_highbits = h_Blue_binary.substring(0, 4);
                c_blue_highbits = c_Blue_binary.substring(0, 4);              
              }
            
              if(encodemethod=="3bits")
              {
                //merge alpha
                h_alpha_highbits = h_alpha_binary.substring(0, 3);
                c_alpha_highbits = c_alpha_binary.substring(0, 5);
                //merge red
                h_red_highbits = h_red_binary.substring(0, 3);
                c_red_highbits = c_red_binary.substring(0, 5);              
                //merge Green
                h_green_highbits = h_Green_binary.substring(0, 3);
                c_green_highbits = c_Green_binary.substring(0, 5);  
                //merge Blue
                h_blue_highbits = h_Blue_binary.substring(0, 3);
                c_blue_highbits = c_Blue_binary.substring(0, 5);              
              }
              String new_alpha = c_alpha_highbits + h_alpha_highbits;
              String new_red = c_red_highbits + h_red_highbits;
              String new_green = c_green_highbits + h_green_highbits;
              String new_blue = c_blue_highbits + h_blue_highbits;
            
              int new_alpha_int = Integer.parseInt(new_alpha, 2);
              int new_red_int = Integer.parseInt(new_red, 2);
              int new_green_int = Integer.parseInt(new_green, 2);
              int new_blue_int = Integer.parseInt(new_blue, 2);
              
       
              int new_pixelint = (new_alpha_int << 24) | (new_red_int << 16) | (new_green_int << 8) | new_blue_int;
              
              coverimage.setRGB(j, i+1, new_pixelint);

            }
          } 
         
         return coverimage;
     }
 
     private byte[] get_byte_data(BufferedImage image)
     {
        WritableRaster raster = image.getRaster();
         DataBufferByte buffer = (DataBufferByte)raster.getDataBuffer();
        return buffer.getData();
     }
  
  
}