package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

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
        jframe.addWindowFocusListener( new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e){
               System.out.println("i am here");
            }

            @Override
            public void windowLostFocus(WindowEvent e){
                gamePanel.getGame().updateWindowFocus();
            }
        });


    }
    
}
