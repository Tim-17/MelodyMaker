import java.util.ArrayList;
import java.util.Collections;

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
        for(int i = 0; i < getLength(); i++){
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
        double randDouble;
        for(int i = 0; i < getMelody().length; i++){
            if(getRhythm()[i]){
                randDouble = Math.random();
                setMelodyNote(randDouble, i);
            }
        }
    }

    public void createChordsMelody(Chord[] chords){
        setMelody(new String[getLength()]);
        // declare variables outside of loop in order to save storage space
        double randDouble;
        String[] arpeggiateNotes;
        String[] noMinor2ndIntervalsNotes;
        double p;
        // create melody
        for(int i = 0; i < getMelody().length; i++){ // chordsArray should have the same length as the melodyArray
            if(getRhythm()[i]){
                randDouble = Math.random();
                if(chords[i] == null){ // no chord entered -> normal melodyNote creation
                    setMelodyNote(randDouble, i);
                } else { // chord is entered
                    if(chords[i].getArpeggiate()){ // arpeggiate chord
                        arpeggiateNotes = extractPossibleArpeggiateNotes(chords[i].getKeyChordNotes(), chords[i].getExtraChordNotes());
                        outputArray("ArpeggiateNotes: ", arpeggiateNotes);
                        p = 1.0/arpeggiateNotes.length; // give each possible note an equal probability
                        for(int j = 0; j < arpeggiateNotes.length; j++){
                            if(p*j < randDouble && randDouble < p*(j+1)){ // in order to understand this, compare it to the setMelodyNote() method -> it's the same thing just in a for loop because the equal probability allows it
                                getMelody()[i] = arpeggiateNotes[j];
                                break;
                            }
                        }
                    } else { // no minor 2nd interval chord
                        noMinor2ndIntervalsNotes = extractPossibleNoMinor2ndIntervalsNotes(chords[i].getKeyChordNotes(), chords[i].getExtraChordNotes(), chords[i]);
                        outputArray("NoMinor2ndIntervalsNotes: ", noMinor2ndIntervalsNotes);
                        p = 1.0/noMinor2ndIntervalsNotes.length; // give each possible note an equal probability
                        for(int j = 0; j < noMinor2ndIntervalsNotes.length; j++){
                            if(p*j < randDouble && randDouble < p*(j+1)){ // in order to understand this, compare it to the setMelodyNote() method -> it's the same thing just in a for loop because the equal probability allows it
                                getMelody()[i] = noMinor2ndIntervalsNotes[j];
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    private void setMelodyNote(double randDouble, int index){
        if(randDouble < getiPercentage()){ // I
            getMelody()[index] = getKeyNotes()[0];
        } else if(getiPercentage() < randDouble && randDouble < getiiPercentage()){ // II
            getMelody()[index] = getKeyNotes()[1];
        } else if(getiiPercentage() < randDouble && randDouble < getiiiPercentage()){ // III
            getMelody()[index] = getKeyNotes()[2];
        } else if(getiiiPercentage() < randDouble && randDouble < getivPercentage()){ // IV
            getMelody()[index] = getKeyNotes()[3];
        }
        else if(getivPercentage() < randDouble && randDouble < getvPercentage()){ // V
            getMelody()[index] = getKeyNotes()[4];
        }
        else if(getvPercentage() < randDouble && randDouble < getviPercentage()){ // VI
            getMelody()[index] = getKeyNotes()[5];
        }
        else { // VII
            getMelody()[index] = getKeyNotes()[6];
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

    private String[] extractPossibleArpeggiateNotes(String[] keyChordNotes, String[] extraChordNotes){
        ArrayList<String> possibleNotes = new ArrayList<>();
        for(String note : keyChordNotes){
            if(note != null){
                possibleNotes.add(note);
            }
        }
        for(String note : extraChordNotes){
            if(note != null){
                possibleNotes.add(note);
            }
        }
        return convertToStringArray(possibleNotes);
    }

    private String[] extractPossibleNoMinor2ndIntervalsNotes(String[] keyChordNotes, String[] extraChordNotes, Chord chord){
        ArrayList<Integer> noteIndicesArrayList = new ArrayList<>(); // store the indices of the chord notes
        int indexBuffer; // this variable is declared here so that it won't be declared in every iteration of the for loop (to save storage space)

        // store all chord notes in an array like getAllNotes() and order them by placing them at their respective index -> one wants to be able to compare allNotesArray with getAllNotes()
        String[] allNotesArray = new String[12];
        for(String keyChordNote : keyChordNotes){
            if(keyChordNote != null){
                indexBuffer = findAllNotesIndex(keyChordNote); // this variable shortens the runtime by 'preventing' the findAllNotesIndex() method from running twice
                allNotesArray[indexBuffer] = keyChordNote;
                noteIndicesArrayList.add(indexBuffer);
            }
        }
        for(String extraChordNote : extraChordNotes){
            if(extraChordNote != null){
                indexBuffer = findAllNotesIndex(extraChordNote); // this variable shortens the runtime by 'preventing' the findAllNotesIndex() method from running twice
                allNotesArray[indexBuffer] = extraChordNote;
                noteIndicesArrayList.add(indexBuffer);
            }
        }
        Collections.sort(noteIndicesArrayList);

        // find out which keyNotes could be added to the allNotesArray because they don't have a minor 2nd interval with any of the chord notes
        for(int i = 0; i < noteIndicesArrayList.size()-1; i++){
            // the "if(noteIndicesArrayList.get(i+1) - noteIndicesArrayList.get(i) > 3)" part could be removed because the for-loop already indirectly includes this if-statement -> it's still in there because it makes the method easier to understand (I hope)
            if(noteIndicesArrayList.get(i+1) - noteIndicesArrayList.get(i) > 3){ // check whether the 'space' between two notes allows for a note (in between those notes) that doesn't have a minor 2nd interval with both of the surrounding notes
                for(int j = noteIndicesArrayList.get(i)+2; j <= noteIndicesArrayList.get(i+1)-2; j++){ // check whether any of the notes in between the two surrounding notes (that doesn't have a minor 2nd interval with the surrounding notes -> hence the +2/-2) is a keyNote and could therefore be added to the allNotesArray
                    if(findKeyNoteIndex(getAllNotes()[j]) != -1){ // note == keyNote
                        allNotesArray[j] = getAllNotes()[j];
                    }
                }
            }
        }
        // the "if(getAllNotes().length - (noteIndicesArrayList.get(noteIndicesArrayList.size()-1) - noteIndicesArrayList.get(0)) > 3)" part could be removed because the for-loop already indirectly includes this if-statement -> it's still in there because it makes the method easier to understand (I hope)
        if(getAllNotes().length - (noteIndicesArrayList.get(noteIndicesArrayList.size()-1) - noteIndicesArrayList.get(0)) > 3){ // check the space between the last and the first note in the array -> this always starts somewhere "at the end" of the allNotesArray and ends somewhere "at the beginning" -> hence the getAllNotes().length - ()
            // check the "end part" of the array
            for(int i = noteIndicesArrayList.get(noteIndicesArrayList.size()-1)+2; i < getAllNotes().length; i++){ // check whether any of the notes in between the two surrounding notes (that doesn't have a minor 2nd interval with the surrounding notes -> hence the +2/-2) is a keyNote and could therefore be added to the allNotesArray
                if(findKeyNoteIndex(getAllNotes()[i]) != -1){ // note == keyNote
                    allNotesArray[i] = getAllNotes()[i];
                }
            }
            // check the "beginning part" of the array
            for(int i = 0; i <= noteIndicesArrayList.get(0)-2; i++){ // check whether any of the notes in between the two surrounding notes (that doesn't have a minor 2nd interval with the surrounding notes -> hence the +2/-2) is a keyNote and could therefore be added to the allNotesArray
                if(findKeyNoteIndex(getAllNotes()[i]) != -1){ // note == keyNote
                    allNotesArray[i] = getAllNotes()[i];
                }
            }
        }

        // remove notes with null value from allNotesArray
        ArrayList<String> possibleNotes = new ArrayList<>();
        for(String note : allNotesArray){
            if(note != null){
                possibleNotes.add(note);
            }
        }
        return convertToStringArray(possibleNotes);
    }

    private String[] convertToStringArray(ArrayList<String> arrayList){
        String[] array = new String[arrayList.size()];
        for (int i = 0; i < array.length; i++){
            array[i] = arrayList.get(i);
        }
        return array;
    }

    private void outputArray(String message, String[] array){
        System.out.println(message);
        for(int i = 0; i < array.length; i++){
            System.out.print("[" + array[i] + "] ");
        }
        System.out.println("");
    }
}
