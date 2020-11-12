import java.awt.*;
import javax.swing.*;
import static javax.swing.GroupLayout.Alignment.*;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainPage
{
    private JPanel contentPane;
    private int hgap;
    private int vgap;

    public MainPage()
    {
        hgap = 5;
        vgap = 5;
        
        Color mybackground = new Color(225,225,225);
        JFrame frame = new JFrame("CAMO");
        JPanel contentPane = new JPanel();
        contentPane.setOpaque(true);
        contentPane.setBackground(mybackground);
        contentPane.setBorder(
        BorderFactory.createEmptyBorder(hgap, hgap, hgap, hgap));
        contentPane.setLayout(new GridLayout(0,2));
        
        JLabel space = new JLabel("     ");
        JLabel el = new JLabel("Encoding Information");
        JLabel dl = new JLabel("Decoding Information");
        el.setFont(new Font("Serif", Font.BOLD, 24));
        dl.setFont(new Font("Serif", Font.BOLD, 24));
        JButton encode1 = new JButton("Encode Text into Image");
        JButton encode2 = new JButton("Encode Image into Image");
        JButton decode1 = new JButton("Decode Text from Image");
        JButton decode2 = new JButton("Decode Image from Image");
        
        JPanel leftPanel = new JPanel(); 
        leftPanel.setOpaque(true);
        leftPanel.setBackground(Color.WHITE);      
        leftPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        
        GroupLayout layout = new GroupLayout(leftPanel);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
     
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(CENTER)
                .addComponent(el)
                .addComponent(encode1)
                .addComponent(encode2))       
            );
           
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(CENTER)
                .addComponent(el)  )
            .addGroup(layout.createParallelGroup(CENTER)
                .addComponent(encode1) )  
            .addGroup(layout.createParallelGroup(CENTER)
                .addComponent(encode2)  )  
            );
        
        layout.linkSize(SwingConstants.HORIZONTAL, encode1, encode2);

        leftPanel.setLayout(layout);
        contentPane.add(leftPanel);
        
        JPanel rightPanel = new JPanel(); 
        rightPanel.setOpaque(true);
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));                     
                             
        GroupLayout layout2 = new GroupLayout(rightPanel);
        layout2.setAutoCreateGaps(true);
        layout2.setAutoCreateContainerGaps(true);
     
        layout2.setHorizontalGroup(layout2.createSequentialGroup()
            .addGroup(layout2.createParallelGroup(CENTER)
                .addComponent(dl)
                .addComponent(decode1)
                .addComponent(decode2))       
            );
           
        layout2.setVerticalGroup(layout2.createSequentialGroup()
            .addGroup(layout2.createParallelGroup(CENTER)
                .addComponent(dl)  )
            .addGroup(layout2.createParallelGroup(CENTER)
                .addComponent(decode1) )  
            .addGroup(layout2.createParallelGroup(CENTER)
                .addComponent(decode2)  )  
            );
        
        layout2.linkSize(SwingConstants.HORIZONTAL, decode1, decode2);
    
        rightPanel.setLayout(layout2);
        contentPane.add(rightPanel);
  
    encode1.addActionListener(new ActionListener()//when clicked open up this page, and close the main meun
    { public void actionPerformed(ActionEvent e)
      {new EncodeTextUI(); frame.dispose();}
    });
    encode2.addActionListener(new ActionListener()//when clicked open up this page, and close the main meun
    { public void actionPerformed(ActionEvent e)
      {new EncodeImageUI(); frame.dispose();}
    });  
    decode1.addActionListener(new ActionListener()//when clicked open up this page, and close the main meun
    { public void actionPerformed(ActionEvent e)
      {new DecodeTextUI(); frame.dispose();}
    });
    decode2.addActionListener(new ActionListener()//when clicked open up this page, and close the main meun
    { public void actionPerformed(ActionEvent e)
      {new DecodeImageUI(); frame.dispose();}
    });
    
    frame.setContentPane(contentPane);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //frame.setLocationByPlatform(true);
    frame.setVisible(true);
    }
    public static void main(String... args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new MainPage();
            }
        });
    }
}