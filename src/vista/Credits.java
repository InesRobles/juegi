package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Credits extends JPanel{
	private final int TILES_RES=16;
	private final int HEIGHT=11;
	private final int WIDTH=16;
	private final int SCALE=4;


	private final int SCREEN_HEIGHT=TILES_RES*HEIGHT*SCALE;
	private final int SCREEN_WIDTH=TILES_RES*WIDTH*SCALE;

	private int scrollY = 0;
    private Timer scrollTimer;
    private Font font;
    public Credits(GameWindow gw) {
 
        setLayout(null);
        setOpaque(false);
        setFocusable(true);
        
        try {
			font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/Resources/Fonts/menuFont.ttf"));
			setFont(font.deriveFont(30f));
		} catch (Exception e) {
			//Si por lo que sea no se encuentra el archivo se le da una fuente por defecto
			    font = new Font("Arial", Font.BOLD, 30);
		}
       
        //Cada 20ms se ejecuta el codigo que hay dentro. Es decir, cada 20ms se le resta uno al scroll y se vuelve a pintar el panel
        scrollTimer = new Timer(20, e -> {
            scrollY--;
            repaint();
        });
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    scrollTimer.stop();
                    gw.showMainMenu();
                }
            }
        });
    }
    
    public void start() {
        scrollY = SCREEN_HEIGHT;
        scrollTimer.start();
        requestFocus();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.setColor(Color.WHITE);
        
        FontMetrics fm = g.getFontMetrics();
        int y = scrollY;
        String texto1="FIN DEL JUEGO";
        String texto2="Desarrollado por:";
        String texto3="Sergio González Marquínez";
        String texto4="Programación: ";
        String texto5="Diseño de niveles: ";
        String texto6="Manuel González Marquínez";
        String texto7="Arte: ";
        String texto8="Nintendo";
        String texto9="Sonido y Musica: ";
        String texto10="Desarrollado en: ";
        String texto11="Java";
        String texto12="Eclipse IDE";
        String texto13="BetaTester:";
        String texto14="Arturo García Montaño";
        String texto15="ENTER para volver al menú";
        String texto16="Ya no hay nada más";
        String texto17="pulsa ENTER para volver";
        g.drawString(texto1, (SCREEN_WIDTH - fm.stringWidth(texto1)) / 2, y);
        y += 100;
        g.drawString(texto2, (SCREEN_WIDTH - fm.stringWidth(texto2)) / 2, y);
        y += 70;
        g.drawString(texto3, (SCREEN_WIDTH - fm.stringWidth(texto3)) / 2, y);
        y += 100;
        g.drawString(texto4, (SCREEN_WIDTH - fm.stringWidth(texto4)) / 2, y);
        y += 70;
        g.drawString(texto3, (SCREEN_WIDTH - fm.stringWidth(texto3)) / 2, y);
        y += 100;
        g.drawString(texto5, (SCREEN_WIDTH - fm.stringWidth(texto5)) / 2, y);
        y += 70;
        g.drawString(texto6, (SCREEN_WIDTH - fm.stringWidth(texto6)) / 2, y);
        y += 100;
        g.drawString(texto7, (SCREEN_WIDTH - fm.stringWidth(texto7)) / 2, y);
        y += 70;
        g.drawString(texto8, (SCREEN_WIDTH - fm.stringWidth(texto8)) / 2, y);
        y += 100;
        g.drawString(texto9, (SCREEN_WIDTH - fm.stringWidth(texto9)) / 2, y);
        y += 70;
        g.drawString(texto8, (SCREEN_WIDTH - fm.stringWidth(texto8)) / 2, y);
        y += 100;
        g.drawString(texto10, (SCREEN_WIDTH - fm.stringWidth(texto10)) / 2, y);
        y += 70;
        g.drawString(texto11, (SCREEN_WIDTH - fm.stringWidth(texto11)) / 2, y);
        y += 70;
        g.drawString(texto12, (SCREEN_WIDTH - fm.stringWidth(texto12)) / 2, y);
        y += 100;
        g.drawString(texto13, (SCREEN_WIDTH - fm.stringWidth(texto13)) / 2, y);
        y += 70;
        g.drawString(texto14, (SCREEN_WIDTH - fm.stringWidth(texto14)) / 2, y);
        y += 120;
        g.drawString(texto15, (SCREEN_WIDTH - fm.stringWidth(texto15)) / 2, y);
        y += 2000;
        g.drawString(texto16, (SCREEN_WIDTH - fm.stringWidth(texto16)) / 2, y);
        y += 70;
        g.drawString(texto17, (SCREEN_WIDTH - fm.stringWidth(texto17)) / 2, y);
    }
}