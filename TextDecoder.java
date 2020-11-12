//Rachel Shafer
//Text Decoder
//Adapted from: https://www.dreamincode.net/forums/topic/27950-steganography/
//Original version: *@author  William_Wilson, *@version 1.6


 import java.io.File;
 import java.awt.Graphics2D;
 import java.awt.image.BufferedImage;
 import java.awt.image.WritableRaster;
 import java.awt.image.DataBufferByte;
 import javax.imageio.ImageIO;
 import javax.swing.JOptionPane;

 public class TextDecoder
 {

     //Decoder Empty Constructor
     public TextDecoder()
     {}
  
     /*
      *Decrypt assumes the image being used is of type .png, extracts the hidden text from an image
      *@param path   The path (folder) containing the image to extract the message from
      *@param name The name of the image to extract the message from
      *@param type integer representing either basic or advanced encoding
     */
     public String textdecode(String path, String name, String ext1)
     {
         byte[] decode;
         try
         {
             //user space is necessary for decrypting
             BufferedImage image  = user_space(getImage(image_path(path,name, ext1)));
             decode = decode_text(get_byte_data(image));
             return(new String(decode));
         }
         catch(Exception e)
         {
             JOptionPane.showMessageDialog(null,
                 "There is no hidden message in this image!","Error",
                 JOptionPane.ERROR_MESSAGE);
           return "";
         }
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
         File file = new File(f);
          
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
      *Creates a user space version of a Buffered Image, for editing and saving bytes
      *@param image The image to put into user space, removes compression interferences
      *@return The user space version of the supplied image
      */
     private BufferedImage user_space(BufferedImage image)
     {
         //create new_img with the attributes of image
         BufferedImage new_img  = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D  graphics = new_img.createGraphics();
         graphics.drawRenderedImage(image, null);
         graphics.dispose(); //release all allocated memory for this image
         return new_img;
     }
      
     /*
      *Gets the byte array of an image
      *@param image The image to get byte data from
      *@return Returns the byte array of the image supplied
      *@see Raster
      *@see WritableRaster
      *@see DataBufferByte
      */
     private byte[] get_byte_data(BufferedImage image)
     {
        WritableRaster raster   = image.getRaster();
         DataBufferByte buffer = (DataBufferByte)raster.getDataBuffer();
        return buffer.getData();
     }
      
      
     /*
      *Retrieves hidden text from an image
      *@param image Array of data, representing an image
      *@return Array of data which contains the hidden text
      */
     private byte[] decode_text(byte[] image)
     {
         int length = 0;
         int offset  = 32;
         //loop through 32 bytes of data to determine text length
         for(int i=0; i<32; ++i) //i=24 will also work, as only the 4th byte contains real data
         {
             length = (length << 1) | (image[i] & 1);
         }
         byte[] result = new byte[length];
         
         //loop through each byte of text
         for(int b=0; b<result.length; ++b )
        {
             //loop through each bit within a byte of text
             for(int i=0; i<8; ++i, ++offset)
            {
                //assign bit: [(new byte value) << 1] OR [(text byte) AND 1]
                result[b] = (byte)((result[b] << 1) | (image[offset] & 1));
            }
        }
       return result;
    }
 }