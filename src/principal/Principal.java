package principal;
import vista.GameWindow;
import modelo.GameModel;
import controlador.GameController;
public class Principal {

	public static void main(String[] args) {
		GameModel gamemodel= new GameModel();
		GameWindow window = new GameWindow(gamemodel);
		GameController gc = new GameController(gamemodel, window.getGamePanel(), window);
		window.setVisible(true);
		window.setController(gc);
	}
}