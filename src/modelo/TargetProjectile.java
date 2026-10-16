package modelo;

import java.util.ArrayList;

import utiles.Direction;
import utiles.SoundEffects;

public class TargetProjectile extends Projectile {
	protected final int TILES_RES=16;
	protected final int SCALE=4;

	protected final int GAP=TILES_RES*SCALE;

	private SoundEffects sound;

	public TargetProjectile(int x, int y,int dx, int dy, int room) {
		sound= new SoundEffects();
		this.x=x;
		this.y=y;
		damage=1;
		speed=5;
		active=true;
		frameTimer=0;
		deflectedTimer=0;
		this.playerX=dx;
		this.playerY=dy;
		this.room=room;
		//Calculando el seno y el coseno y multiplicandolo por la velocidad sacamos la direccion en la que se dispara
		double angle = Math.atan2(dy - y, dx - x);
		this.dx = Math.cos(angle) * speed;
		this.dy = Math.sin(angle) * speed;
	}

	public void move() {
		x+=dx;
		y+=dy;
	}
	public void Collision(Rooms rooms, Player player) {
		int[][][] room= rooms.getSalas();
		int sala = rooms.getSalaActual();
		//Saca la columna y fila donde esta el enemigo
		int columna=(y + GAP/2)/ GAP; 
		int fila=(x+ GAP/2) /GAP;
		int columnaPlayer=(player.getY() + GAP/2)/ GAP; 
		int filaPlayer=(player.getX()+ GAP/2) /GAP;
		//Se sale fuera de la sala
		if (fila>=room[sala].length || fila<0 || columna>=room[sala][fila].length || columna<0) {
			active=false;
			return;
		}

		//Choca con el jugador
		if(columnaPlayer==columna && filaPlayer==fila && player.getAttackedTimer()==0) {
			sound.play("/Resources/SFX/Soundeffects/LinkHurt.wav");
			int health=player.getHealth();
			health-=damage;
			player.setHealth(health);
			player.setAttackedTimer(100);
			player.setBeingAttacked(true);
			player.setCanMove(false);
			player.setDirectionAttacked(Direction.DOWN);
			return;
		}
	}
	public void update(Rooms rooms, Player player, ArrayList<Enemy> enemies) {
		if(rooms.getSalaActual()==room) {
			if(active) {
				lastx=x;
				lasty=y;
				Collision(rooms, player);
				move();

				frameTimer++;
				if(frameTimer%10==0) {
					frames=(frames+1)%4;
				}
			}
		}else {
			active=false;
		}
	}
}
