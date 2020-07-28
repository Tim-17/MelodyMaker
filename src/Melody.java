public class Melody {

    private boolean[] rhythm;
    private String[] melody;
    private final String[] allNotes = new String[]{"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
    private String[] keyNotes, extraNotes;
    private int length, changeIndex;
    private double pausePercentage, iPercentage, iiPercentage, iiiPercentage, ivPercentage, vPercentage, viPercentage;
    private boolean changeIndexFound;

    public Melody(int length){
        setKeyNotes(new String[7]);
        setExtraNotes(new String[5]);
        setLength(length);
        setPausePercentage(0.75);
        // This percentage distribution lays emphasis on I and V -> can always be manipulated for different results
        setiPercentage(0.25);
        setiiPercentage(getiPercentage() + 0.1);
        setiiiPercentage(getiiPercentage() + 0.1);
        setivPercentage(getiiiPercentage() + 0.1);
        setvPercentage(getivPercentage() + 0.25);
        setviPercentage(getvPercentage() + 0.1);
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

    public String[] getExtraNotes() {
        return this.extraNotes;
    }

    public void setExtraNotes(String[] extraNotes) {
        this.extraNotes = extraNotes;
    }

    public int getLength(){
        return this.length;
    }

    public void setLength(int newLength){
        if(newLength != 0){
            this.length = newLength;
        } else {
            System.out.println("The given length is too short!");
        }
    }

    public int getChangeIndex(){
        return this.changeIndex;
    }

    public void setChangeIndex(int changeIndex){
        this.changeIndex = changeIndex;
    }

    private double getPausePercentage(){
        return this.pausePercentage;
    }

    private void setPausePercentage(double newPercentage){
        if(newPercentage >= 0 && newPercentage <= 1){
            this.pausePercentage = newPercentage;
        } else {
            System.out.println("The given pausePercentage is out of bounds!");
        }
    }

    private double getiPercentage(){
        return this.iPercentage;
    }

    private void setiPercentage(double newPercentage){
        if(newPercentage >= 0 && newPercentage <= 1){
            this.iPercentage = newPercentage;
        } else {
            System.out.println("The given iPercentage is out of bounds!");
        }
    }

    private double getiiPercentage(){
        return this.iiPercentage;
    }

    private void setiiPercentage(double newPercentage){
        if(newPercentage >= 0 && newPercentage <= 1){
            this.iiPercentage = newPercentage;
        } else {
            System.out.println("The given iiPercentage is out of bounds!");
        }
    }

    private double getiiiPercentage(){
        return this.iiiPercentage;
    }

    private void setiiiPercentage(double newPercentage){
        if(newPercentage >= 0 && newPercentage <= 1){
            this.iiiPercentage = newPercentage;
        } else {
            System.out.println("The given iiiPercentage is out of bounds!");
        }
    }

    private double getivPercentage(){
        return this.ivPercentage;
    }

    private void setivPercentage(double newPercentage){
        if(newPercentage >= 0 && newPercentage <= 1){
            this.ivPercentage = newPercentage;
        } else {
            System.out.println("The given ivPercentage is out of bounds!");
        }
    }

    private double getvPercentage(){
        return this.vPercentage;
    }

    private void setvPercentage(double newPercentage){
        if(newPercentage >= 0 && newPercentage <= 1){
            this.vPercentage = newPercentage;
        } else {
            System.out.println("The given vPercentage is out of bounds!");
        }
    }

    private double getviPercentage(){
        return this.viPercentage;
    }

    private void setviPercentage(double newPercentage){
        if(newPercentage >= 0 && newPercentage <= 1){
            this.viPercentage = newPercentage;
        } else {
            System.out.println("The given viPercentage is out of bounds!");
        }
    }

    public boolean getChangeIndexFound(){
        return this.changeIndexFound;
    }

    public void setChangeIndexFound(boolean changeIndexFound){
        this.changeIndexFound = changeIndexFound;
    }


    // Actual Methods

    public void createRhythm(){
        setRhythm(new boolean[getLength()]);
        for(int i = 0; i < getLength(); i++){ // TODO: Optional - Add higher chance for first beat to be a note
            if(Math.random() < getPausePercentage()){
                getRhythm()[i] = false;
                setPausePercentage(getPausePercentage() * 0.9); // "notePercentage" increases (pausePercentage decreases) the longer the pause has been
            } else {
                getRhythm()[i] = true;
                setPausePercentage(0.75);
            }
        }
    }

    public void createKeyNotes(String tonic, boolean major){
        getKeyNotes()[0] = tonic;
        setChangeIndexFound(false);
        int currentIndex = -1;
        // Find index of tonic
        for(int i = 0; i < getAllNotes().length; i++) {
            if(getAllNotes()[i].equals(tonic)){
                currentIndex = i;
                break;
            }
        }
        // Create rest of notes based on index of tonic
        if(currentIndex != -1){
            if(major){
                int i = 1;
                for(int next : new int[]{2,2,1,2,2,2}){
                    if(currentIndex + next < getAllNotes().length){
                        currentIndex = currentIndex + next;
                    } else {
                        currentIndex = currentIndex + next - getAllNotes().length;
                        if(!getChangeIndexFound()){
                            setChangeIndex(i);
                            setChangeIndexFound(true);
                        }
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
                        if(!getChangeIndexFound()){
                            setChangeIndex(i);
                            setChangeIndexFound(true);
                        }
                    }
                    getKeyNotes()[i] = getAllNotes()[currentIndex];
                    i++;
                }
            }
        } else {
            System.out.println("The given tonic is not part of the Western note system!");
        }
        createExtraNotes();
    }

    private void createExtraNotes(){
        int j = 0;
        for(int i = 0; i < getAllNotes().length; i++){
           if(findKeyNoteIndex(getAllNotes()[i]) == -1){
               getExtraNotes()[j] = getAllNotes()[i];
               j++;
           }
       }
    }

    public int findKeyNoteIndex(String keyNote){
        for(int i = 0; i < getKeyNotes().length; i++){
            if(getKeyNotes()[i].equals(keyNote)){
                return i;
            }
        }
        return -1;
    }

    public int findAllNotesIndex(String note){
        for(int i = 0; i < getAllNotes().length; i++){
            if(getAllNotes()[i].equals(note)){
                return i;
            }
        }
        return -1;
    }

    public void createMelody(){
        setMelody(new String[getLength()]);
        double randDouble = 0;
        for(int i = 0; i < getMelody().length; i++){
            if(getRhythm()[i]){
                randDouble = Math.random();
                if(randDouble < getiPercentage()){ // I
                    getMelody()[i] = getKeyNotes()[0];
                } else if(getiPercentage() < randDouble && randDouble < getiiPercentage()){ // II
                    getMelody()[i] = getKeyNotes()[1];
                } else if(getiiPercentage() < randDouble && randDouble < getiiiPercentage()){ // III
                    getMelody()[i] = getKeyNotes()[2];
                } else if(getiiiPercentage() < randDouble && randDouble < getivPercentage()){ // IV
                    getMelody()[i] = getKeyNotes()[3];
                }
                else if(getivPercentage() < randDouble && randDouble < getvPercentage()){ // V
                    getMelody()[i] = getKeyNotes()[4];
                }
                else if(getvPercentage() < randDouble && randDouble < getviPercentage()){ // VI
                    getMelody()[i] = getKeyNotes()[5];
                }
                else { // VII
                    getMelody()[i] = getKeyNotes()[6];
                }
            }
        }
    }

    public static String extractActualNoteName(String note){
        int index = 0;
        String actualNoteName = "";
        while(index < note.length() && note.charAt(index) != ' '){ // (index < note.length) is not necessary for the names of the CheckBoxNotes; could be used for chordBaseNoteCB if roman numbers in brackets (C (I); C#; D (II) ...) are added for chord function in key
            actualNoteName += note.charAt(index);
            index++;
        }
        return actualNoteName;
    }
}
