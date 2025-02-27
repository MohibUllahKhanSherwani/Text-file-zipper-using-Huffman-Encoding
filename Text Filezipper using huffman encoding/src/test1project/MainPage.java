/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1project;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Mohib
 */
public class MainPage implements ActionListener
{
    static Color color = new Color (0, 102, 102);
    private JFrame main;
    private JPanel mainPanel;
    private JButton compress, decompress;
    private JFileChooser choose;
    
    public MainPage()
    {
        initialize();
    }
    private void initialize()
    {
        main = new JFrame("Huffman Compression");
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setSize(450, 250);
        main.setLocationRelativeTo(null);
        main.setResizable(false);
        main.setLayout(null);
        
        mainPanel = new JPanel();
        mainPanel.setBackground(color);
        mainPanel.setSize(450, 250);
        mainPanel.setLayout(null);
        
        compress = new JButton("Compress Text File");
        compress.addActionListener(this);
        compress.setBounds(30, 50, 180, 50);
        mainPanel.add(compress);
        
        decompress = new JButton("Decompress Text File");
        decompress.setBounds(230, 50, 180, 50);
        decompress.addActionListener(this);
        mainPanel.add(decompress);
        
        main.add(mainPanel);
        main.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == compress)
        {
            choose = new JFileChooser();
            choose.showSaveDialog(null);  
            HuffCompression.compress(choose.getSelectedFile().getAbsolutePath(), removeExtension(choose.getSelectedFile().                  getAbsolutePath()) + "compressed.txt");
            System.out.println(choose.getSelectedFile().getAbsolutePath());
            System.out.println(removeExtension(choose.getSelectedFile().getAbsolutePath()));
        }
        else if (e.getSource() == decompress)
        {
            choose = new JFileChooser();
            choose.showSaveDialog(null);
            HuffCompression.decompress(choose.getSelectedFile().getAbsolutePath(), removeExtension(choose.getSelectedFile().                getAbsolutePath()).replace("compressed", "decompressed") + ".txt");
            
        }
    }

   public static String removeExtension(String fname) 
   {
      int pos = fname.lastIndexOf('.');
      if(pos > -1)
         return fname.substring(0, pos);
      else
         return fname;
   }
}
