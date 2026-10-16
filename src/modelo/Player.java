package modelo;

import java.util.ArrayList;

import utiles.Direction;
import utiles.SoundEffects;
public class Player extends Entity {
	private final int TILES_RES=16;
	private final int SCALE=4;


	private final int GAP=TILES_RES*SCALE;

	private int maxHealth;
	private SoundEffects sound;
	private int money;
	private int keys;
	private boolean bowObtained;
	private boolean action;
	private int actionTimer;
	private int actionCoolDown;
	private boolean specialpush;
	public Player() {
		health=6;
		speed=5;
		direction=Direction.UP; 
		walkframes=true;
		cooldown=false;
		attacking=false;
		alive=true;
		canMove=true;
		attackedFrames=true;
		damage=1;
		maxHealth=6;
		sound = new SoundEffects();
		dyingTimer=160;
		keys=0;
		money=0;
		bowObtained=true;
		action=false;
		actionTimer=9;
		actionCoolDown=0;
		specialpush=false;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getMoney() {
		return money;
	}
	public int getKeys() {
		return keys;
	}
	public void useKey(int key) {
		keys-=key;
	}

	public boolean isAction() {
		return action;
	}

	public void move() {
		if(canMove) {
			if(direction.equals(Direction.UP) && moving) {
				x-=speed;
			}else if(direction.equals(Direction.DOWN) && moving) {
				x+=speed;	
			}else if(direction.equals(Direction.LEFT) && moving) {
				y-=speed;
			}else if(direction.equals(Direction.RIGHT) && moving) {
				y+=speed;	
			}
		}
	}
	public void undoMove() {
		x=lastx;
		y=lasty;
	}

	public void attack() {
		if(attacking || cooldown || !canMove) {
			return; //Se sale si esta atacando o el cooldown esta activo
		}
		sound.play("/Resources/SFX/Soundeffects/SwordSlash.wav");
		attacking=true;
		attackframes=0;
		attacktimer=0;
	}
	public void action(ArrayList<Projectile> projectiles, int room) {
		if(!attacking && actionCoolDown<=0) {
			if(bowObtained) {
				if(!specialpush) {
					projectiles.add(new StraightProjectile(x, y, direction, "player", room));
				}
				action=true;
				specialpush=true;
				canMove=false;
			}
		}
	}
	public void attacked(Enemy e, Rooms room) {
		if(!e.isAlive() || room.getSalaActual()!=e.getRoom()) {
			return;
		}
		//Sacamos la posición en la matriz del jugador y del enemigo
		int columnaPlayer=(y + GAP/2)/ GAP; 
		int filaPlayer=(x+ GAP/2) /GAP;
		int columnaEnemy=(e.getY() + GAP/2)/ GAP;
		int filaEnemy=(e.getX()+ GAP/2) /GAP;

		int damaged=e.getDamage();
		Direction attackdirection=e.getDirection();
		if(columnaPlayer==columnaEnemy && filaPlayer==filaEnemy && attackedTimer==0 && !e.isDying()) {
			hurt(damaged, attackdirection);
			e.setTackling(false);
		}
	}
	public void hurt(int damaged, Direction attackdirection) {
		sound.play("/Resources/SFX/Soundeffects/LinkHurt.wav");
		health-=damaged;
		attackedTimer=100;
		beingAttacked=true;
		canMove=false;
		directionAttacked=attackdirection;
	}
	public void itemCollect(Item i) {
		int columnaItem=(i.getY() + GAP/2)/ GAP; 
		int filaItem=(i.getX()+ GAP/2) /GAP;
		int columna=(y + GAP/2)/ GAP; 
		int fila=(x+ GAP/2) /GAP;

		if(columnaItem==columna && filaItem==fila) {
			if(money>=i.getPrice()) {
				if(i instanceof Rupee) {
					sound.play("/Resources/SFX/Soundeffects/itemCollected.wav");
					money+=i.getAmount();
					i.setAlive(false);
					money-=i.getPrice();
				}else if(i instanceof Heart) {

					if(maxHealth!=health) {
						sound.play("/Resources/SFX/Soundeffects/HealthHeart.wav");
						health+=i.getAmount();
						if(health>maxHealth) {
							health=maxHealth;
						}
						i.setAlive(false);
						money-=i.getPrice();
					}
				}else if(i instanceof Key) {
					sound.play("/Resources/SFX/Soundeffects/KeyCollected.wav");
					keys+=i.getAmount();
					i.setAlive(false);
					money-=i.getPrice();
				}else if(i instanceof HeartContainer) {
					sound.play("/Resources/SFX/Soundeffects/KeyCollected.wav");
					maxHealth+=i.getAmount();
					health=maxHealth;
					i.setAlive(false);
					money-=i.getPrice();
				}else if(i instanceof Bow) {
					sound.play("/Resources/SFX/Soundeffects/ItemReceived.wav");
					bowObtained=true;
					i.setAlive(false);
					money-=i.getPrice();
				}

			}
		}
	}
	public void checkCollisions(Rooms rooms) {
		int[][][] room= rooms.getSalas();
		int sala = rooms.getSalaActual();
		//Para comprobar solo a partir de la mitad del sprite
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
		if (fila>=room[sala].length || fila<0 || columna>=room[sala][fila].length || columna<0) {
			rooms.changeRoom(this);
			return;//Salimos del metodo para no seguir comprobando y que no de error
		}
		if(room[sala][fila][columna]==32 || room[sala][fila][columna]==44 || room[sala][fila][columna]==45 || room[sala][fila][columna]==70 || room[sala][fila][columna]==69) {
			rooms.door(this);
			return;
		}
		if(room[sala][fila][columna]==38) {
			Direction attackdirection=Direction.UP;
			switch(direction) {
			case UP:
				attackdirection=Direction.DOWN;
				break;
			case DOWN:
				attackdirection=Direction.UP;
				break;
			case RIGHT:
				attackdirection=Direction.LEFT;
				break;
			case LEFT:
				attackdirection=Direction.RIGHT;
				break;
			}
			hurt(2, attackdirection);
		}
		if(room[sala][fila][columna]==2 || room[sala][fila][columna]==3 || room[sala][fila][columna]==4 || room[sala][fila][columna]==5 || room[sala][fila][columna]==6 || room[sala][fila][columna]==7 || room[sala][fila][columna]==8 || room[sala][fila][columna]==9 || room[sala][fila][columna]==10 || room[sala][fila][columna]==11 || room[sala][fila][columna]==12 || room[sala][fila][columna]==13 || room[sala][fila][columna]==14 || room[sala][fila][columna]==15 || room[sala][fila][columna]==16 || room[sala][fila][columna]==22 || room[sala][fila][columna]==23 || room[sala][fila][columna]==24 || room[sala][fila][columna]==25 || room[sala][fila][columna]==27 || room[sala][fila][columna]==28 || room[sala][fila][columna]==29 || room[sala][fila][columna]==30 || room[sala][fila][columna]==31 ||  room[sala][fila][columna]==33 ||  room[sala][fila][columna]==35 ||  room[sala][fila][columna]==36 ||  room[sala][fila][columna]==40 ||  room[sala][fila][columna]==41 ||  room[sala][fila][columna]==42 ||   room[sala][fila][columna]==43 ||   room[sala][fila][columna]==46 ||   room[sala][fila][columna]==47 ||   room[sala][fila][columna]==48 ||  room[sala][fila][columna]==49 ||  room[sala][fila][columna]==50 ||  room[sala][fila][columna]==51 ||  room[sala][fila][columna]==52 ||  room[sala][fila][columna]==53 ||  room[sala][fila][columna]==54 ||  room[sala][fila][columna]==55 ||   room[sala][fila][columna]==56 ||  room[sala][fila][columna]==57||  room[sala][fila][columna]==58||  room[sala][fila][columna]==59||  room[sala][fila][columna]==60||  room[sala][fila][columna]==61||  room[sala][fila][columna]==62 || room[sala][fila][columna]==63 || room[sala][fila][columna]==64 || room[sala][fila][columna]==66 || room[sala][fila][columna]==65 || room[sala][fila][columna]==67 || room[sala][fila][columna]==68 || room[sala][fila][columna]==71) {
			undoMove();
			if(room[sala][fila][columna]==42||room[sala][fila][columna]==43) {
				rooms.lockedDoor(this);
			}
		}
	}
	public void update(Rooms rooms) {
		if(!dying) {
			lastx=x;
			lasty=y;
			if(attacking) {
				attacktimer++;
				if(attacktimer%3==0) { //Cada 5 ticks cambia el frame

					attackframes= (attackframes + 1)%8;//Saca el resto que es 0, 1, 2 o 3 . Para los 3 sprites de ataque

					if(attackframes==0) {//cuando llega a 0 se acaba el ataque
						attacking=false;
						cooldown=true;
						cooldowntimer=30;
					}
				}
			}else if (action){
				actionTimer--;
				if(actionTimer==0) {
					actionCoolDown=50;
					action=false;
					specialpush=false;
					actionTimer=15;
					canMove=true;
				}
			}else {
				move(); //meto el move dentro del else para que no se pueda mover mientras ataca
				checkCollisions(rooms);
				if(moving) {
					walktimer++;
					if(walktimer%10==0) {
						walkframes= !walkframes;
					}
				}else {
					walkframes=true;
					walktimer=0;
				}
			}

			if(cooldown) { //Si cooldown activo va restando cada tick hasta llegar a cero
				cooldowntimer--;
				if(cooldowntimer==0) {
					cooldown=false;
				}
			}

			if(beingAttacked) {
				attackedTimer--;
				if(attackedTimer%5==0) {
					attackedFrames=!attackedFrames;
				}
				if(attackedTimer==0) {
					beingAttacked=false;
				}
				if(attackedTimer>80) {
					lastx=x;
					lasty=y;
					switch(directionAttacked) {
					case LEFT:
						y-=5;
						break;
					case RIGHT:
						y+=5;
						break;
					case DOWN:
						x+=5;
						break;
					case UP:
						x-=5;
						break;
					}
					checkCollisions(rooms);
				}else {
					canMove=true;
				}
			}
			actionCoolDown--;
		}
		if(health<=0 && alive) {
			if(dyingTimer==160) {
				sound.play("/Resources/SFX/Soundeffects/PlayerDie.wav");
			}
			dying=true;
			dyingTimer--;
			if(dyingTimer%5==0) {
				dyingFrames=(dyingFrames+1)%4;
			}
			if(dyingTimer==0) {
				alive=false;
			}

		}
	}

}
