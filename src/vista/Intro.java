package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import net.java.games.input.Component;
import utiles.BackgroundMusic;

public class Intro extends JPanel {
	private net.java.games.input.Controller gamepad;
	
	private final int TILES_RES=16;
	private final int HEIGHT=11;
	private final int WIDTH=16;
	private final int SCALE=4;


	private final int GAP=TILES_RES*SCALE;
	private final int SCREEN_HEIGHT=TILES_RES*HEIGHT*SCALE;
	private final int SCREEN_WIDTH=TILES_RES*WIDTH*SCALE;
    private int currentPage = 0;
    private Font font;
    
    private String[][] paginas = {
        {"En el pequeño pueblo de Ilerna, ", "solo los caballeros más valientes ", "podían convertirse en su Protector."},
        {"Para lograrlo, ", " debían superar una última prueba:", "encontrar tres llaves ocultas", "y abrir el camino hacia una mazmorra olvidada."},
        {"Dentro de la cueva aguardaba el desafío final.", "Una enorme araña que custodiaba el destino de", "aquellos que se atrevieran a enfrentarse a ella"}
    };
    private BackgroundMusic music;
	public Intro(GameWindow gw) {
		music = new BackgroundMusic();
		
		  setLayout(null);
	        setFocusable(true);
	        setBackground(Color.BLACK);
	        
	        try {
	            font = Font.createFont(Font.TRUETYPE_FONT,
	                getClass().getResourceAsStream("/Resources/Fonts/menuFont.ttf"));
	            font = font.deriveFont(20f);
	        } catch (Exception e) {
	            font = new Font("Arial", Font.BOLD, 30);
	        }
	        
	        addKeyListener(new KeyAdapter() {
	            @Override
	            public void keyPressed(KeyEvent e) {
	                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	                    currentPage++;
	                    if (currentPage >= paginas.length) {
	                        gw.startGame();
	                        gw.showGame();
	                        music.stop();
	                    } else {
	                        repaint();
	                    }
	                }
	            }
	        });
	}
	public void start() {
        currentPage = 0;
        repaint();
        requestFocus();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.setColor(Color.WHITE);
        g.setFont(font);
        
        FontMetrics fm = g.getFontMetrics();
        String[] lines = paginas[currentPage];
        
        int startY = SCREEN_HEIGHT/2 - (lines.length * 50) / 2;
        for (String line : lines) {
            int x = (SCREEN_WIDTH - fm.stringWidth(line)) / 2;
            g.drawString(line, x, startY);
            startY += 50;
        }
        
        // indicador de continuar
        g.setFont(font.deriveFont(20f));
        g.drawString("ENTER para continuar", SCREEN_WIDTH/2 - 80, SCREEN_HEIGHT - 50);
    }
    
}
