package main;

import javax.swing.JFrame;

public class Window extends JFrame {
    private JFrame jframe;
    private GamePanel gamePanel;
    public Window(GamePanel gamePanel){
        
        jframe = new JFrame();

        
       
        jframe.setVisible(true);
        jframe.add(gamePanel);
        jframe.setResizable(false);
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
    
}
