import javax.swing.*;
import java.awt.*;

public class ChordsPanel extends JPanel {

    private int length;

    public ChordsPanel(){

    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(Main.OTHER_FRAME_WIDTH, Main.OTHER_FRAME_HEIGHT);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

}
