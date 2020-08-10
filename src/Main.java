import java.awt.*;

import static java.awt.Toolkit.getDefaultToolkit;

public class Main{

    public static final int MAIN_FRAME_WIDTH = (int)(getDefaultToolkit().getScreenSize().getWidth()*0.75);
    public static final int MAIN_FRAME_HEIGHT = (int)(getDefaultToolkit().getScreenSize().getHeight()*0.75);
    public static final int OTHER_FRAME_WIDTH = (int)(getDefaultToolkit().getScreenSize().getWidth()*0.5);
    public static final int OTHER_FRAME_HEIGHT = (int)(getDefaultToolkit().getScreenSize().getHeight()*0.5);
    public static final int CHORD_FRAME_WIDTH = (int)(getDefaultToolkit().getScreenSize().getWidth()*0.25);
    public static final int CHORD_FRAME_HEIGHT = (int)(getDefaultToolkit().getScreenSize().getHeight()*0.25);
    public static final Color[] keyNoteColors = new Color[]{new Color(213, 0, 4), new Color(232, 84, 15), new Color(231, 182, 7), new Color(139, 210, 4), new Color(7, 241, 194), new Color(0, 4, 206), new Color(181, 3, 229)};

    private static GUI gui;

    public Main(){

    }
    
    public static void main(String[] args){
        gui = new GUI();
    }
}
