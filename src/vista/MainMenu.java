package vista;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import controlador.GameController;
import utiles.BackgroundMusic;
import utiles.SoundEffects;

public class MainMenu extends JPanel {
	private final int TILES_RES=16;
	private final int HEIGHT=11;
	private final int WIDTH=16;
	private final int SCALE=4;


	private final int GAP=TILES_RES*SCALE;
	private final int SCREEN_HEIGHT=TILES_RES*HEIGHT*SCALE;
	private final int SCREEN_WIDTH=TILES_RES*WIDTH*SCALE;
	private Image imgmenu;
	private SoundEffects effect;
	public MainMenu(GameWindow gw) {
		effect = new SoundEffects();
		//Para que funcione bien el bounds
		setLayout(null);
		imgmenu=new ImageIcon(getClass().getResource("/Resources/Sprites/Menu/MenuBackground.png")).getImage();
	
		MenuBton btnJugar = new MenuBton("JUGAR");
	        btnJugar.setBounds(SCREEN_WIDTH/2 - 100, SCREEN_HEIGHT/2 + 90, 200, 50);
	        //Al pulsarlo muestra la ventana y para la musica
	        btnJugar.addActionListener(e -> {
	        	effect.play("/Resources/SFX/Soundeffects/itemCollected.wav");
	        	gw.ShowIntro();
	        });
	        add(btnJugar);

	        MenuBton btnSalir = new MenuBton("SALIR");
	        btnSalir.setBounds(SCREEN_WIDTH/2 - 100, SCREEN_HEIGHT/2 + 180, 200, 50);
	        btnSalir.addActionListener(e -> {
	        	effect.play("/Resources/SFX/Soundeffects/itemCollected.wav");
	        	System.exit(0);
	        });
	        add(btnSalir);
	}
	
	   @Override
	    public void paint(Graphics g){
	        Dimension dimension = this.getSize();
	        g.drawImage(imgmenu, 0, 0, dimension.width, dimension.height, this);
	        setOpaque(false);
	        super.paintChildren(g);
	    }
}
