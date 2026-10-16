package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import utiles.SoundEffects;

public class MenuBton extends JButton {
	public MenuBton(String texto) {
		super(texto);

		//Personalizo el boton 
		//Quitamos fondo
		setContentAreaFilled(false);
		//Quitamos borde
		setBorderPainted(false);
		//Quitamos que se marque el boton seleccionado
		setFocusPainted(false);
		//Lo ponemos de color blanco
		setForeground(Color.WHITE);

		//Creamos el efecto de sonido
		SoundEffects effect=new SoundEffects();
		// Le paso una fuente personalizada
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/Resources/Fonts/menuFont.ttf"));
			setFont(font.deriveFont(30f));
		} catch (Exception e) {
			//Si por lo que sea no se encuentra el archivo se le da uina fuente por defecto
			setFont(new Font("Arial", Font.BOLD, 30));
		}

		// efecto al pasar el raton por encima
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setForeground(Color.YELLOW);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setForeground(Color.WHITE);
			}
		});
	}
}