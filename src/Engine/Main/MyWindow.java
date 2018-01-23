package Engine.Main;

import javax.swing.*;
import java.awt.*;

public class MyWindow extends Canvas{

    private static int width;
    private static int height;
    public static MyKeyListener keyListener = new MyKeyListener();

    MyWindow(int width, int height) {
        MyWindow.width = width;
        MyWindow.height = height;

        makeCanvas();
        makeWindow();
    }

    private void makeCanvas() {
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        setVisible(true);
        addKeyListener(keyListener);
    }

    private void makeWindow() {
        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(this);
        createBufferStrategy(2);

        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    public static int getWindowWidth() {
        return width;
    }

    public static int getWindowHeight() {
        return height;
    }
}
