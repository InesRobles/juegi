package modelo;

import java.util.ArrayList;

import utiles.Direction;
import utiles.SoundEffects;

public abstract class Enemy extends Entity {
	protected final int TILES_RES=16;
	protected final int HEIGHT=11;
	protected final int WIDTH=16;
	protected final int SCALE=4;


	protected final int GAP=TILES_RES*SCALE;
	protected final int SCREEN_HEIGHT=TILES_RES*HEIGHT*SCALE;
	protected final int SCREEN_WIDTH=TILES_RES*WIDTH*SCALE;
	protected final int DETECTION_RANGE=400;
	protected int room;
	protected Direction directionCollision;
	protected int originalx, originaly;
	protected SoundEffects sound;
	protected int movingTimer;
	protected boolean check;
	protected int originalHealth;
	protected boolean attackable;
	protected boolean tackling;
	public Enemy() {
		sound = new SoundEffects();
		check=false;
		dying=false;
		dyingTimer=20;
		attackable=true;
	}

	public int getRoom() {
		return room;
	}

	public boolean isCheck() {
		return check;
	}

	public void setTackling(boolean tackling) {
		this.tackling = tackling;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	protected abstract void update(Player player, ArrayList<Item> items, Rooms room, ArrayList<Projectile> projectiles);

	public void Attacked(Player player) {
		if(!attackable) {
			return;
		}
		//Sacamos la posición en la matriz del jugador y del enemigo
		int columnaEnemy=(y + GAP/2)/ GAP; 
		int filaEnemy=(x+ GAP/2) /GAP;
		int columnaAttack=(player.getY() + GAP/2)/ GAP;
		int filaAttack=(player.getX()+ GAP/2) /GAP;
		int columnaPlayer=columnaAttack;
		int filaPlayer=filaAttack;
		//Depende de hacia que lado ataca el jugador resta o suma en las columnas o fila
		if(player.isAttacking()) {
			switch(player.getDirection()) {
			case UP:
				filaAttack--;
				break;
			case DOWN:
				filaAttack++;
				break;
			case RIGHT:
				columnaAttack++;
				break;
			case LEFT:
				columnaAttack--;
				break;
			}

			if(((columnaAttack==columnaEnemy && filaAttack==filaEnemy) || (filaPlayer==filaEnemy && columnaPlayer==columnaEnemy)) && !beingAttacked) {
				sound.play("/Resources/SFX/Soundeffects/EnemyHit.wav");
				int damage=player.getDamage();
				health-=damage;
				beingAttacked=true;
				attackedTimer=30;
				directionAttacked=player.getDirection();
				canMove=false;
			}
		}
	}
	public void AttackedProjectile(Player player, StraightProjectile projectile) {
		int columnaEnemy=(y + GAP/2)/ GAP; 
		int filaEnemy=(x+ GAP/2) /GAP;
		int columnaProjectile=(projectile.getY() + GAP/2)/ GAP; 
		int filaProjectile=(projectile.getX()+ GAP/2) /GAP;
		if((columnaProjectile==columnaEnemy && filaProjectile==filaEnemy) && !beingAttacked) {
		sound.play("/Resources/SFX/Soundeffects/EnemyHit.wav");
		int damage=projectile.getDamage();
		health-=damage;
		beingAttacked=true;
		attackedTimer=30;
		directionAttacked=player.getDirection();
		canMove=false;
		projectile.setActive(false);
		}
	}

	public Item generateItem() {
		int i=(int) ((Math.random() * 100) + 1);
		int i2=(int) ((Math.random()*50)+1);
		if(i<=30) {
			if(i2<=20) {
				Item item=new Rupee(getRoom(), 10, getX(), getY(), true,0, false);
				return item;
			}else {
				Item item=new Rupee(getRoom(), 20, getX(), getY(), true,0, false);
				return item;
			}
		} else if (i<=60) {
			if(i2<=25) {
				Item item=new Heart(getRoom(), 1, getX(), getY(), true,0, false);
				return item;
			}else {
				Item item=new Heart(getRoom(), 2, getX(), getY(), true,0, false);
				return item;
			}
		}
		return null;
	}

	protected void checkCollisionsEnemy(Rooms rooms) {
		int[][][] room= rooms.getSalas();
		int sala = rooms.getSalaActual();
		//Saca la columna y fila donde esta el enemigo
		int columna=(y + GAP/2)/ GAP; 
		int fila=(x+ GAP/2) /GAP;
		switch(direction) {
		case UP:
			//Comprueba la que esta justo antes de el
			fila= x / GAP;
			break;
		case DOWN:
			//Comrpueba la siguiente casilla
			fila= (x+GAP) / GAP;
			break;
		case LEFT:
			columna= y /GAP;
			break;
		case RIGHT:
			columna= (y+GAP) /GAP;
			break;
		}
		//System.out.println("x: "+player.getX() + "y: "+player.getY());
		if (fila>=room[sala].length || fila<0 || columna>=room[sala][fila].length || columna<0) {
			undoMove();
			changeDirection();
		    movingTimer = 0;
		    tackling=false;
			return;//Salimos del metodo para no seguir comprobando y que no de error
		}
		//Si en esa posicion hay un elemento que sea colisionable llama a undoMove para deshacer el movimiento
		if(room[sala][fila][columna]==2 || room[sala][fila][columna]==3 || room[sala][fila][columna]==4 || room[sala][fila][columna]==5 || room[sala][fila][columna]==6 || room[sala][fila][columna]==7 || room[sala][fila][columna]==8 || room[sala][fila][columna]==9 || room[sala][fila][columna]==10 || room[sala][fila][columna]==11 || room[sala][fila][columna]==12 || room[sala][fila][columna]==13 || room[sala][fila][columna]==14 || room[sala][fila][columna]==15 || room[sala][fila][columna]==16 || room[sala][fila][columna]==22 || room[sala][fila][columna]==23 || room[sala][fila][columna]==24 || room[sala][fila][columna]==25 || room[sala][fila][columna]==27 || room[sala][fila][columna]==28 || room[sala][fila][columna]==29 || room[sala][fila][columna]==30 || room[sala][fila][columna]==31 ||  room[sala][fila][columna]==33 ||  room[sala][fila][columna]==35 ||  room[sala][fila][columna]==36 ||  room[sala][fila][columna]==40 ||  room[sala][fila][columna]==41 ||  room[sala][fila][columna]==42 ||   room[sala][fila][columna]==43 ||   room[sala][fila][columna]==46 ||   room[sala][fila][columna]==47 ||   room[sala][fila][columna]==48 ||  room[sala][fila][columna]==49 ||  room[sala][fila][columna]==50 ||  room[sala][fila][columna]==51 ||  room[sala][fila][columna]==52 ||  room[sala][fila][columna]==53 ||  room[sala][fila][columna]==54 ||  room[sala][fila][columna]==55 ||   room[sala][fila][columna]==56 ||  room[sala][fila][columna]==57||  room[sala][fila][columna]==58||  room[sala][fila][columna]==59||  room[sala][fila][columna]==60||  room[sala][fila][columna]==61||  room[sala][fila][columna]==62 || room[sala][fila][columna]==63 || room[sala][fila][columna]==64 || room[sala][fila][columna]==66 || room[sala][fila][columna]==65 || room[sala][fila][columna]==67 || room[sala][fila][columna]==68 || room[sala][fila][columna]==71) {
			undoMove();
			changeDirection();
		    movingTimer = 0;
			directionCollision=direction;
			tackling=false;
			return; //Salimos del metodo
		}
		directionCollision=null;
	}

	public void undoMove() {
		x=lastx;
		y=lasty;
	}

	 protected void changeDirection() {
	    }
	public Direction getDirectionCollision() {
		return directionCollision;
	}

	public void setDirectionCollision(Direction directionCollision) {
		this.directionCollision = directionCollision;
	}

	protected abstract void reset();

	public abstract int getEyeFrame();


}
