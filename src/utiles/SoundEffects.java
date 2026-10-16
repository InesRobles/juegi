package utiles;

import java.io.BufferedInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundEffects {
	private Clip clip;
	//Copiado y pegado tal cual de los ejemplos de Soraya
	 public void play(String nombreSonido){
	       try {
	    	   //He tenido que cambiar esto para que funcione, sinceramente no se que es lo que pasaba (Error de la ruta que era absoluta o no se que)
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream(nombreSonido)));
	        clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	       } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
	    	   ex.printStackTrace();
	       }
	     }
	 
	 public boolean isPlaying() {
		 if(clip==null) {
			 return false;
		 }
		 if(clip.isRunning()) {
			 return true;
		 }
		 return false;
	 }
	 public void stop() {
		 //Para la musica si el clip esta lleno y si se esta reproduciendo
	        if (clip != null && clip.isRunning()) {
	            clip.stop();
	            clip.close();
	        }
	    }
}
