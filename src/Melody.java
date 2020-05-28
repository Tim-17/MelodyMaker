public class Melody {

    private boolean[] rhythm;
    private String[] melody;
    private final String[] allNotes = new String[]{"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
    private String[] keyNotes;
    private int length;
    private double pausePercentage;

    public Melody(int newLength){
        setLength(newLength);
        setPausePercentage(0.75);
        setKeyNotes(new String[7]);
    }

    // Getters & Setters

    public boolean[] getRhythm(){
        return this.rhythm;
    }

    public void setRhythm(boolean[] newRhythm){
        if(newRhythm != null && newRhythm.length > 0){
            this.rhythm = newRhythm;
        } else {
            System.out.println("The given rhythm does not contain any content!");
        }
    }

    public String[] getMelody(){
        return this.melody;
    }

    public void setMelody(String[] newMelody){
        if(newMelody != null && newMelody.length != 0){
            this.melody = newMelody;
        } else {
            System.out.println("The given melody does not contain any content!");
        }
    }

    public String[] getAllNotes(){
        return this.allNotes;
    }

    public String[] getKeyNotes(){
        return this.keyNotes;
    }

    public void setKeyNotes(String[] newKeyNotes) {
        this.keyNotes = newKeyNotes;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int newLength){
        if(newLength != 0){
            this.length = newLength;
        } else {
            System.out.println("The given length is too short!");
        }
    }

    private double getPausePercentage() {
        return this.pausePercentage;
    }

    private void setPausePercentage(double newPercentage){
        if(newPercentage >= 0 && newPercentage <= 1){
            this.pausePercentage = newPercentage;
        } else {
            System.out.println("The given pausePercentage is out of bounds!");
        }
    }

    // Actual Methods

    public void createRhythm(){
        setRhythm(new boolean[getLength()]);
        for(int i = 0; i < getLength(); i++){ // TODO: Optional - Add higher chance for first beat to be a note
            if(Math.random() < getPausePercentage()){
                getRhythm()[i] = false;
                setPausePercentage(getPausePercentage() * 0.9); // pausePercentage decreases the longer the pause has been
            } else {
                getRhythm()[i] = true;
                setPausePercentage(0.75);
            }
        }
    }

    public void createKeyNotes(String tonica, boolean major){
        getKeyNotes()[0] = tonica;
        int currentIndex = -1;
        for(int i = 0; i < getAllNotes().length; i++) {
            if(getAllNotes()[i].equals(tonica)){
                currentIndex = i;
                break;
            }
        }
        if(currentIndex != -1){
            if(major){
                int i = 1;
                for(int next : new int[]{2,2,1,2,2,2}){
                    if(currentIndex + next < getAllNotes().length){
                        currentIndex = currentIndex + next;
                    } else {
                        currentIndex = currentIndex + next - getAllNotes().length;
                    }
                    getKeyNotes()[i] = getAllNotes()[currentIndex];
                    i++;
                }
            } else {
                int i = 1;
                for(int next : new int[]{2,1,2,2,1,2}){
                    if(currentIndex + next < getAllNotes().length){
                        currentIndex = currentIndex + next;
                    } else {
                        currentIndex = currentIndex + next - getAllNotes().length;
                    }
                    getKeyNotes()[i] = getAllNotes()[currentIndex];
                    i++;
                }
            }
        } else {
            System.out.println("The given tonica is not part of the Western note system!");
        }
    }
}
