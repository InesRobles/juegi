package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utiles.SoundEffects;

public class GameOver extends JPanel {
	private final int TILES_RES=16;
	private final int HEIGHT=11;
	private final int WIDTH=16;
	private final int SCALE=4;

	
	private final int GAP=TILES_RES*SCALE;
	private final int SCREEN_HEIGHT=TILES_RES*HEIGHT*SCALE;
	private final int SCREEN_WIDTH=TILES_RES*WIDTH*SCALE;
	private SoundEffects effect;
	private Font font;
	public GameOver(GameWindow gw) {
		effect = new SoundEffects();
		//Para que funcione bien el bounds
		setLayout(null);
		setOpaque(false);
		// Le paso una fuente personalizada
		
		//Key listener para la pausa
		addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
	                gw.QuitPause();
	            }
	        }
	    });
		
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/Resources/Fonts/menuFont.ttf"));
			setFont(font.deriveFont(30f));
		} catch (Exception e) {
			//Si por lo que sea no se encuentra el archivo se le da uina fuente por defecto
			setFont(new Font("Arial", Font.BOLD, 30));
		}
		
		JLabel titulo = new JLabel("FIN DE LA PARTIDA");
		titulo.setFont(font);
		titulo.setForeground(Color.RED);
		titulo.setBounds(SCREEN_WIDTH/2 - 240, SCREEN_HEIGHT/5, 700, 50);
		add(titulo);


		MenuBton btnJugar = new MenuBton("CONTINUAR");
		btnJugar.setBounds(SCREEN_WIDTH/2 - 178, SCREEN_HEIGHT/2 + 20, 350, 50);
		//Al pulsarlo muestra la ventana y para la musica
		btnJugar.addActionListener(e -> {
			effect.play("/Resources/SFX/Soundeffects/itemCollected.wav");
			gw.restart();
		});
		add(btnJugar);


		MenuBton btnSalir = new MenuBton("SALIR");
		btnSalir.setBounds(SCREEN_WIDTH/2 - 100, SCREEN_HEIGHT/2 + 110, 200, 50);
		btnSalir.addActionListener(e -> {
			effect.play("/Resources/SFX/Soundeffects/itemCollected.wav");
			System.exit(0);
		});
		add(btnSalir);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(0, 0, 0, 150));
		g2d.fillRect(0, 0, getWidth(), getHeight());
	}
}
