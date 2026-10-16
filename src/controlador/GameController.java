package controlador;
import java.awt.event.KeyEvent;

import modelo.GameModel;
import utiles.Direction;
import vista.GamePanel;
import vista.GameWindow;
public class GameController implements Runnable {
	private GameModel modelo;
	private GamePanel vista;
	private GameWindow ventana;
	private ImputController imput;
	private boolean running;
	private boolean pause;
	private boolean escPresionado;
	private Thread thread;
	public GameController(GameModel g, GamePanel p, GameWindow w) {
		running=true;
		imput=new ImputController();
		modelo=g;
		vista=p;
		ventana=w;
		vista.getPanel().addKeyListener(imput);//Le ponemos para que detecte los imputs
		vista.getPanel().setFocusable(true);//Activamos para que vayan los imputs
		vista.getPanel().requestFocus(); 
		pause=false;
	}


	public void setRunning(boolean running) {
		this.running = running;
	}


	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public void setModel(GameModel modelo) {
		this.modelo = modelo;
	}
	//Inicia el hilo
	public void start() {
		stop(); //Me aseguro de que se para el hilo anterior
		running=true;
		thread = new Thread(this);
		thread.start();
	}

	//Mientras se corre ejecuta esto
	@Override
	public void run() {
		while (running) {
			update();
			try {
				Thread.sleep(1000 / 60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void stop() {
		running=false;
		 try {
		        if (thread != null) {
		            thread.join(); // espera a que el hilo termine
		        }
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
	}

	//Actualiza el gameModel y la vista
	public void update() {
		if(!modelo.getPlayer().isAlive()) {
			running=false;
			ventana.ShowGameOver();
			return;
		}else if(modelo.getPlayer().isDying()) {
			modelo.getPlayer().update(modelo.getRooms());
			vista.repaint();
		}else {
			Imputs();  
			if(pause) {
				return;
			}
			if(modelo.getGamefinished()) {
				running = false;
				ventana.showCredits();
			}
			Imputs();
			modelo.update();
			vista.repaint();
		}
	}

	public void Imputs() {
		imput.updateGamepad();
		//Si se presiona el escape se "Despresiona"  para que al volver a pulsarlo funcione y no se mantenga como presionado
		if (imput.pause()) {
			//Si no esta presionado se presiona y se pausa o despausa
			if (!escPresionado) {
				escPresionado = true;
				pause = !pause;
				if (pause) {
					ventana.ShowPause();
				}
				else {
					ventana.QuitPause();
				}
			}
			//Si se ha presionado se pone como false
		} else {
			escPresionado = false;
		}
		if (pause) {
			return;
		}

		if (imput.attack()) {

		    modelo.getPlayer().setMoving(false);
		    modelo.getPlayer().attack();

		} else if(imput.action()) {

		    modelo.getPlayer().action(
		        modelo.getProjectiles(),
		        modelo.getRooms().getSalaActual()
		    );

		} else if(imput.up()) {

		    modelo.getPlayer().setDirection(Direction.UP);
		    modelo.getPlayer().setMoving(true);

		} else if(imput.down()) {

		    modelo.getPlayer().setDirection(Direction.DOWN);
		    modelo.getPlayer().setMoving(true);

		} else if(imput.right()) {

		    modelo.getPlayer().setDirection(Direction.RIGHT);
		    modelo.getPlayer().setMoving(true);

		} else if(imput.left()) {

		    modelo.getPlayer().setDirection(Direction.LEFT);
		    modelo.getPlayer().setMoving(true);

		} else {

		    modelo.getPlayer().setMoving(false);

		}
	
	}
	
}
