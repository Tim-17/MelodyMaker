import static java.awt.Toolkit.getDefaultToolkit;

public class Main{

    public static final int MAIN_FRAME_WIDTH = (int)(getDefaultToolkit().getScreenSize().getWidth()*0.75);
    public static final int MAIN_FRAME_HEIGHT = (int)(getDefaultToolkit().getScreenSize().getHeight()*0.75);
    public static final int OTHER_FRAME_WIDTH = (int)(getDefaultToolkit().getScreenSize().getWidth()*0.5);
    public static final int OTHER_FRAME_HEIGHT = (int)(getDefaultToolkit().getScreenSize().getHeight()*0.5);

    private static GUI gui;

    public Main(){

    }
    
    public static void main(String[] args){
        gui = new GUI();
    }
}
