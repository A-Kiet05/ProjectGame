package main;

import javax.swing.JFrame;

public class Window extends JFrame {
    private JFrame jframe;
    private GamePanel gamePanel;
    public Window(GamePanel gamePanel){
        
        jframe = new JFrame();

        
        jframe.setSize(400,400);
        jframe.setVisible(true);
        jframe.add(gamePanel);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
    
}
