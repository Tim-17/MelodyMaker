import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.advanced.AdvancedPlayer;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/*
 * @author David J. Barnes und Michael KÃ¶lling
 * @version 31.07.2011
 */

public class MusicPlayer{

    private AdvancedPlayer player;
    private HashMap<String, String> notes;

    public MusicPlayer(){
        player = null;
        notes = new HashMap<>();
        getNotes().put("C", "res/SynthesizedPianoNotes/Piano11.mp3");
        getNotes().put("C#", "res/SynthesizedPianoNotes/Piano12.mp3");
        getNotes().put("D", "res/SynthesizedPianoNotes/Piano13.mp3");
        getNotes().put("D#", "res/SynthesizedPianoNotes/Piano14.mp3");
        getNotes().put("E", "res/SynthesizedPianoNotes/Piano15.mp3");
        getNotes().put("F", "res/SynthesizedPianoNotes/Piano16.mp3");
        getNotes().put("F#", "res/SynthesizedPianoNotes/Piano17.mp3");
        getNotes().put("G", "res/SynthesizedPianoNotes/Piano18.mp3");
        getNotes().put("G#", "res/SynthesizedPianoNotes/Piano19.mp3");
        getNotes().put("A", "res/SynthesizedPianoNotes/Piano110.mp3");
        getNotes().put("A#", "res/SynthesizedPianoNotes/Piano111.mp3");
        getNotes().put("B", "res/SynthesizedPianoNotes/Piano112.mp3");
        getNotes().put("C'", "res/SynthesizedPianoNotes/Piano113.mp3");
        getNotes().put("C#'", "res/SynthesizedPianoNotes/Piano114.mp3");
        getNotes().put("D'", "res/SynthesizedPianoNotes/Piano115.mp3");
        getNotes().put("D#'", "res/SynthesizedPianoNotes/Piano116.mp3");
        getNotes().put("E'", "res/SynthesizedPianoNotes/Piano117.mp3");
        getNotes().put("F'", "res/SynthesizedPianoNotes/Piano118.mp3");
        getNotes().put("F#'", "res/SynthesizedPianoNotes/Piano119.mp3");
        getNotes().put("G'", "res/SynthesizedPianoNotes/Piano120.mp3");
        getNotes().put("G#'", "res/SynthesizedPianoNotes/Piano121.mp3");
        getNotes().put("A'", "res/SynthesizedPianoNotes/Piano122.mp3");
        getNotes().put("A#'", "res/SynthesizedPianoNotes/Piano123.mp3");
        getNotes().put("B'", "res/SynthesizedPianoNotes/Piano124.mp3");
        getNotes().put("click", "res/SynthesizedPianoNotes/click.wav");
    }

    public void playNote(final String path, int duration){
        try{
            preparePlayer(path);
            Thread playerThread = new Thread(){
                public void run(){
                    try{
                        player.play(duration);
                    } catch(JavaLayerException e){
                        outputProblem(path);
                    } finally{
                        killPlayer();
                    }
                }
            };
            playerThread.start();
        } catch (Exception ex){
            outputProblem(path);
        }
    }

    public void stop(){
        killPlayer();
    }

    private void preparePlayer(String path){
        try{
            InputStream is = returnInputStream(path);
            player = new AdvancedPlayer(is, createAudioDevice());
        } catch (IOException | JavaLayerException e){
            outputProblem(path);
            killPlayer();
        }
    }

    private InputStream returnInputStream(String path) throws IOException{
        return new BufferedInputStream(
                new FileInputStream(path));
    }

    private AudioDevice createAudioDevice() throws JavaLayerException{
        return FactoryRegistry.systemRegistry().createAudioDevice();
    }

    private void killPlayer(){
        synchronized(this){
            if(player != null){
                player.stop();
                player = null;
            }
        }
    }

    private void outputProblem(String path){
        System.out.println("There was a problem with playing: " + path);
    }

    public HashMap<String, String> getNotes(){
        return this.notes;
    }

    public void setNotes(HashMap<String, String> notes){
        this.notes = notes;
    }
}