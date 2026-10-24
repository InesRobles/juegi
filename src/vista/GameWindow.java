package vista;
import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import controlador.GameController;
import modelo.GameModel;
import utiles.BackgroundMusic;
public class GameWindow extends JFrame {
	private final int TILES_RES=16;
	private final int HEIGHT=11;
	private final int WIDTH=16;
	private final int SCALE=4;


	private final int GAP=TILES_RES*SCALE;
	private final int SCREEN_HEIGHT=TILES_RES*HEIGHT*SCALE;
	private final int SCREEN_WIDTH=TILES_RES*WIDTH*SCALE;

	private GamePanel gamepanel;
	private GameOver gameover;
	private JLayeredPane layeredPane;
	private MainMenu mainmenu;
	private Intro intro;
	private Pause pause;
	private Credits credits;
	private GameController gc;
	private BackgroundMusic music;
	public GameWindow(GameModel g) {
		setTitle("Three Keys Quest");
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		Image icon = new ImageIcon(getClass().getResource("/Resources/Sprites/Player/WalkDown1.PNG")).getImage();
		setIconImage(icon);
		setBackground(Color.BLACK);
		music = new BackgroundMusic();
		//Creamos las capas
		layeredPane = new JLayeredPane();
		layeredPane.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);

		//Creamos el menu y lo añadimos a la Default_layer (Tiene un valor de 0, primera capa)
		mainmenu=new MainMenu(this);
		mainmenu.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		layeredPane.add(mainmenu, JLayeredPane.DEFAULT_LAYER);
		mainmenu.setVisible(true);
		
		intro = new Intro(this);
		intro.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		intro.setVisible(false);
		layeredPane.add(intro, 50);
		
		//Creamos la pantalla del juego y lo añadimos a la Palette_layer (Valor de 100, por encima del default)
		gamepanel =new GamePanel(g);
		gamepanel.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		layeredPane.add(gamepanel, JLayeredPane.PALETTE_LAYER);
		gamepanel.setVisible(false);
		//Creamos el gameOver y lo añadimos a la Modal_Layer(Valor de 200, por encima de la Palette)
		gameover=new GameOver(this);
		gameover.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		gameover.setVisible(false);
		layeredPane.add(gameover, JLayeredPane.MODAL_LAYER);
		//Se pueden poner tambien numeros enteros para las capas, pero como vamos a usar solo 3 paneles con las constantes vamos bien 

		pause= new Pause(this);
		pause.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		pause.setVisible(false);
		layeredPane.add(pause, JLayeredPane.POPUP_LAYER);

		credits = new Credits(this);
		credits.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		credits.setVisible(false);
		layeredPane.add(credits, JLayeredPane.DRAG_LAYER);

		add(layeredPane);

		music.play("/Resources/SFX/Music/TitleScreen.wav");
	}

	public GamePanel getGamePanel() {
		return gamepanel;
	}
	public void setController(GameController gc) {
		this.gc=gc;
	}
	
	public void showMainMenu() {
		music.stop();
		music.play("/Resources/SFX/Music/TitleScreen.wav");
		mainmenu.setVisible(true);
		gamepanel.setVisible(false);
		credits.setVisible(false);
		mainmenu.requestFocus();
	}
	public void showCredits() {
    	music.play("/Resources/SFX/Music/Ending.wav");
		credits.setVisible(true);
		credits.start();
	}
	public void showGame() {
		music.stop();
		mainmenu.setVisible(false);
		gamepanel.setVisible(true);
		// el foco pasa al juego
		gamepanel.requestFocus();
		gc.start();

	}
	public void ShowIntro() {
		mainmenu.setVisible(false);
		 intro.setVisible(true);
		    intro.start();
	}
	public void ShowGameOver() {
		gameover.setVisible(true);
		//El foco pasa al gameover para que ahora el controlador le afecte a él
		gameover.requestFocus();
	}

	public void ShowPause() {
		pause.setVisible(true);
		pause.requestFocus();
	}

	public void QuitPause() {
		pause.setVisible(false);
		gamepanel.requestFocus();
		gc.setPause(false);
	}

	public void restart() {
		gc.stop();
		gameover.setVisible(false);

		//creo un modelo nuevo desde cero
		GameModel gamemodel = new GameModel();
		gamepanel.setModel(gamemodel);
		gc.setModel(gamemodel);

		gamepanel.requestFocus();
		gc.start();
	}
	public void startGame() {
		music.stop();
		GameModel gamemodel = new GameModel();
	    gamepanel.setModel(gamemodel);
	    gc.setModel(gamemodel);
	    gc.stop();
	    credits.setVisible(false);
	    gameover.setVisible(false);
	    mainmenu.setVisible(false);
	    gamepanel.setVisible(true);
	    intro.setVisible(false);
	    gamepanel.requestFocus();
	    
	    gc.start();
	}
}
