package modelo;

import java.util.ArrayList;

import utiles.Direction;
import utiles.SoundEffects;

/*Esta clase se llama projectile a secas no porque sea una clase abstracta de la otra clase projectil, sino porque en un principio
solo iba a haber un unico projectile, pero he cambiado de idea*/
public class StraightProjectile extends Projectile{
	protected final int TILES_RES=16;
	protected final int SCALE=4;

	protected final int GAP=TILES_RES*SCALE;
	private SoundEffects sound;
	public StraightProjectile(int x, int y, Direction direction, String owner, int room) {
		sound= new SoundEffects();
		this.x=x;
		this.y=y;
		this.direction=direction;
		this.owner=owner;
		damage=1;
		speed=7;
		active=true;
		frameTimer=0;
		deflectedTimer=0;
		this.room=room;
	}
	public void move() {
		switch(direction) {
		case UP:
			x-=speed;
			break;
		case DOWN:
			x+=speed;
			break;
		case RIGHT:
			y+=speed;
			break;
		case LEFT:
			y-=speed;
			break;
		}
	}
	public void Collision(Rooms rooms, Player player, ArrayList<Enemy> enemies) {
		int[][][] room= rooms.getSalas();
		int sala = rooms.getSalaActual();
		//Saca la columna y fila donde esta el enemigo
		int columna=(y + GAP/2)/ GAP; 
		int fila=(x+ GAP/2) /GAP;
		int columnaPlayer=(player.getY() + GAP/2)/ GAP; 
		int filaPlayer=(player.getX()+ GAP/2) /GAP;
		switch(direction) {
		case UP:
			//Comprueba la que esta justo antes de el
			fila= x / GAP;
			break;
		case DOWN:
			//Comrpueba la siguiente casilla
			fila= (x+GAP) / GAP;
			break;
		default:
			break;
		}
		//Se sale fuera de la sala
		if (fila>=room[sala].length || fila<0 || columna>=room[sala][fila].length || columna<0) {
			active=false;
			return;
		}

		if(owner.equals("enemy")) {
			//Choca con el jugador
			if(columnaPlayer==columna && filaPlayer==fila && player.getAttackedTimer()==0) {
				if((direction==Direction.LEFT && player.getDirection()==Direction.RIGHT) || (direction==Direction.DOWN && player.getDirection()==Direction.UP) || (direction==Direction.UP && player.getDirection()==Direction.DOWN) || (direction==Direction.RIGHT && player.getDirection()==Direction.LEFT) && !player.isAttacking()) {
					sound.play("/Resources/SFX/Soundeffects/Shield.wav");
					beingDeflected=true;
					return;
				}
				sound.play("/Resources/SFX/Soundeffects/LinkHurt.wav");
				int health=player.getHealth();
				health-=damage;
				player.setHealth(health);
				player.setAttackedTimer(100);
				player.setBeingAttacked(true);
				player.setCanMove(false);
				player.setDirectionAttacked(direction);
				return;
			}
		}else if (owner.equals("player")) {
			for(Enemy e : enemies) {
				if(e.isAlive()) {
					if(e.getRoom()==sala) {
						e.AttackedProjectile(player, this);
					}
				}
			}
		}

		//Choca contra un objeto colisionable
		if(room[sala][fila][columna]==2 || room[sala][fila][columna]==3 || room[sala][fila][columna]==4 || room[sala][fila][columna]==5 || room[sala][fila][columna]==6 || room[sala][fila][columna]==7 || room[sala][fila][columna]==22 || room[sala][fila][columna]==23 || room[sala][fila][columna]==24 || room[sala][fila][columna]==25 || room[sala][fila][columna]==27 || room[sala][fila][columna]==28 || room[sala][fila][columna]==29 || room[sala][fila][columna]==30 || room[sala][fila][columna]==31 ||  room[sala][fila][columna]==33 ||  room[sala][fila][columna]==35 ||  room[sala][fila][columna]==36 ||  room[sala][fila][columna]==40 ||  room[sala][fila][columna]==41 ||  room[sala][fila][columna]==42 ||   room[sala][fila][columna]==43 ||   room[sala][fila][columna]==46 ||   room[sala][fila][columna]==47 ||   room[sala][fila][columna]==48 ||  room[sala][fila][columna]==49 ||  room[sala][fila][columna]==50 ||  room[sala][fila][columna]==51 ||  room[sala][fila][columna]==52 ||  room[sala][fila][columna]==53 ||  room[sala][fila][columna]==54 ||  room[sala][fila][columna]==55 ||   room[sala][fila][columna]==56 ||  room[sala][fila][columna]==57||  room[sala][fila][columna]==58||  room[sala][fila][columna]==59||  room[sala][fila][columna]==60||  room[sala][fila][columna]==61||  room[sala][fila][columna]==62 || room[sala][fila][columna]==63 || room[sala][fila][columna]==64 || room[sala][fila][columna]==66 || room[sala][fila][columna]==65 || room[sala][fila][columna]==67 || room[sala][fila][columna]==68 || room[sala][fila][columna]==71) {
			active=false;
			return;
		}

	}
	public void update(Rooms rooms, Player player, ArrayList<Enemy> enemies) {
		if(rooms.getSalaActual()==room) {
			if(active) {
				if(!beingDeflected) {
					lastx=x;
					lasty=y;
					Collision(rooms, player, enemies);
					move();

					frameTimer++;
					if(frameTimer%10==0) {
						frames=(frames+1)%2;
					}
				}else {
					deflected();
				}
			}
		}else {
			active=false;
		}
	}
	public void deflected() {
		deflectedTimer++;
		if(deflectedTimer>=20) {
			active=false;
		}
		switch(direction) {
		case RIGHT:
			x-=3;
			y-=4;
			break;
		case LEFT:
			x-=3;
			y+=4;
			break;
		case UP:
			x+=4;
			y-=3;
			break;
		case DOWN:
			x-=4;
			y+=3;
			break;
		}
	}
}