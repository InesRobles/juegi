package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
//Clase que solo comprueba que tecla se pulsa. No hace nada más
public class ImputController implements KeyListener {

	private static final float DEADZONE = 0.5f;
	//Atributo que me guarda la tecla pulsada.
	private final Set<Integer> keysPressed = new HashSet<>();
	private net.java.games.input.Controller gamepad;

	private float stickX;
	private float stickY;

	private boolean botonA, botonB, botonX, botonY, botonStart;
	public ImputController() {
	    initGamepad();
	  
	    
	    
	}
	//Es para cuando escribes algo, un caracter Ej: 'a', '3'. En el juego no usamos los caracteres asi que nada
	@Override
	public void keyTyped(KeyEvent e) {
	//No lo uso pero como viene de la interfaz KeyListener tiene que esta aqui si o si.	
	}

	//Guarda cuando se pulsa una tecla
	@Override
	public void keyPressed(KeyEvent e) {
		//Me guarda la tecla pulsada
		keysPressed.add(e.getKeyCode());
		
	}

	//Guarda cuando se deja de pulsar una tecla
	@Override
	public void keyReleased(KeyEvent e) {
		//Me quita la tecla pulsada
				keysPressed.remove(e.getKeyCode());
	}

	//Si la tecla que se le pasa esta en el HashSet entonces te devuelve true, si no esta te devuelve false
	public boolean isKeyPressed(int key) {
	return keysPressed.contains(key);
	}
	private void initGamepad() {
	    for (Controller c : ControllerEnvironment.getDefaultEnvironment().getControllers()) {
	        // Solo GAMEPAD; STICK suele colar ratones/teclados raros.
	        // Si tu mando sale como STICK, descomenta la segunda condicion
	        // pero comprueba tambien que tenga botones.
	        if (c.getType() == Controller.Type.GAMEPAD) {
	            gamepad = c;
	            System.out.println("Mando detectado: " + c.getName());
	            return;
	        }
	    }
	}
	public void updateGamepad() {
	
	    if (gamepad == null) return;

	    gamepad.poll();
	   
	    
	    // poll() devuelve false si el mando se ha desconectado
	    if (!gamepad.poll()) {
	        gamepad = null;
	        stickX = stickY = 0;
	        botonA = botonB = false;
	        return;
	    }
	    
	    Component x = gamepad.getComponent(Component.Identifier.Axis.X);
	    Component y = gamepad.getComponent(Component.Identifier.Axis.Y);
	    stickX = (x != null) ? x.getPollData() : 0;
	    stickY = (y != null) ? y.getPollData() : 0;

	    // Cruceta (POV hat)
	    Component pov = gamepad.getComponent(Component.Identifier.Axis.POV);
	    if (pov != null) {
	        float p = pov.getPollData();
	        if (p == Component.POV.LEFT || p == Component.POV.UP_LEFT || p == Component.POV.DOWN_LEFT) stickX = -1;
	        if (p == Component.POV.RIGHT || p == Component.POV.UP_RIGHT || p == Component.POV.DOWN_RIGHT) stickX = 1;
	        if (p == Component.POV.UP || p == Component.POV.UP_LEFT || p == Component.POV.UP_RIGHT) stickY = -1;
	        if (p == Component.POV.DOWN || p == Component.POV.DOWN_LEFT || p == Component.POV.DOWN_RIGHT) stickY = 1;
	    }

	    Component a = gamepad.getComponent(Component.Identifier.Button._0);
	    Component b = gamepad.getComponent(Component.Identifier.Button._1);
	    
	    
	    botonA     = isButtonDown(Component.Identifier.Button._0);
	    botonB     = isButtonDown(Component.Identifier.Button._1);
	    botonX     = isButtonDown(Component.Identifier.Button._2);
	    botonY     = isButtonDown(Component.Identifier.Button._3);
	    botonStart = isButtonDown(Component.Identifier.Button._7);
	   
	}
	
	
	//Lee el boton
	private boolean isButtonDown(Component.Identifier id) {
	    Component c = gamepad.getComponent(id);
	    return c != null && c.getPollData() > 0.5f;
	}
	
	public boolean up() {
	    return isKeyPressed(KeyEvent.VK_W)
	            || isKeyPressed(KeyEvent.VK_UP)
	            || stickY < -0.5f;
	}

	public boolean down() {
	    return isKeyPressed(KeyEvent.VK_S)
	            || isKeyPressed(KeyEvent.VK_DOWN)
	            || stickY > 0.5f;
	}

	public boolean left() {
	    return isKeyPressed(KeyEvent.VK_A)
	            || isKeyPressed(KeyEvent.VK_LEFT)
	            || stickX < -0.5f;
	}

	public boolean right() {
	    return isKeyPressed(KeyEvent.VK_D)
	            || isKeyPressed(KeyEvent.VK_RIGHT)
	            || stickX > 0.5f;
	}

	public boolean attack() {
	    return isKeyPressed(KeyEvent.VK_Z)
	            || isKeyPressed(KeyEvent.VK_SPACE)
	            || botonA || botonX;
	}

	public boolean action() {
	    return isKeyPressed(KeyEvent.VK_X)
	            || isKeyPressed(KeyEvent.VK_M)
	            || botonB || botonY;
	}
	public boolean pause() {
	    return isKeyPressed(KeyEvent.VK_ESCAPE) || botonStart;
	}
	
	
}
