import javax.swing.*;
import java.awt.*;

class ComboBoxColorRenderer extends DefaultListCellRenderer{

    private ListCellRenderer defaultRenderer;
    private Melody calcMelody;

    public ComboBoxColorRenderer(ListCellRenderer defaultRenderer, Melody calcMelody){
        this.defaultRenderer = defaultRenderer;
        setCalcMelody(calcMelody);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
        Component c = defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if(c instanceof JLabel){
            if(getCalcMelody().findKeyNoteIndex(getCalcMelody().extractActualNoteName((String)value)) != -1){
                c.setBackground(new Color(0, 147, 65, 34));
                c.setForeground(Main.keyNoteColors[getCalcMelody().findKeyNoteIndex(getCalcMelody().extractActualNoteName((String)value))]);
            } else {
                c.setBackground(new Color(0,0,0,0));
            }
        } else {
            c.setBackground(Color.red);
            c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        }
        return c;
    }


    // Getters & Setters

    public Melody getCalcMelody(){
        return this.calcMelody;
    }

    public void setCalcMelody(Melody calcMelody){
        this.calcMelody = calcMelody;
    }
}
