package utiles;

import java.io.BufferedInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class BackgroundMusic {
	private Clip clip;
	private String actual;
	 public void play(String rutaSonido){
		 //Se sale si la ruta introducida es la misma que la que se esta reproduciendo ahora
		 if(rutaSonido.equals(actual)) {
			 return;
		 }
		 stop();
	       try {
	    	   //He tenido que cambiar esto para que funcione, sinceramente no se que es lo que pasaba (Error de la ruta que era absoluta o no se que)
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream(rutaSonido)));
	        clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        //Para que entre en un bucle
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
	        clip.start();
	        actual=rutaSonido;
	       } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
	    	   ex.printStackTrace();
	       }
	     }
	 //Para parar la musica
	 public void stop() {
		 //Para la musica si el clip esta lleno y si se esta reproduciendo
	        if (clip != null && clip.isRunning()) {
	            clip.stop();
	            clip.close();
	        }
	        actual = "";
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
}
