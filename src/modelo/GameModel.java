package modelo;

import java.util.ArrayList;


//Clase que guarda todos los datos del juego.
public class GameModel {
	private Player player;
	private Rooms rooms;
	private ArrayList<Enemy> enemies;
	private ArrayList <Item> items;
	private ArrayList<Projectile> projectiles;

	private boolean gamefinished;
	
	public GameModel() {
		rooms = new Rooms();
		player = new Player();
		enemies = new ArrayList<>();
		items = new ArrayList<>();
		projectiles = new ArrayList<>();
		
		//Enemigos sala 1
		enemies.add(new ShooterEnemy(265,495, 1, 2));
		enemies.add(new ShooterEnemy(360,495, 1, 2));

		//Enemigos sala 2
		enemies.add(new TackleEnemy(85,215, 2, 2));
		enemies.add(new TackleEnemy(560,215, 2, 2));
		enemies.add(new TackleEnemy(85,745, 2, 2));
		enemies.add(new TackleEnemy(560,745, 2, 2));
		enemies.add(new ShooterEnemy(275,660, 2, 2));
		enemies.add(new ShooterEnemy(370,660, 2, 2));
		
		//Enemigos sala 4
		enemies.add(new TackleEnemy(280,285, 4, 2));
		enemies.add(new TackleEnemy(490,125, 4, 2));
		enemies.add(new TackleEnemy(240,835, 4, 2));
		enemies.add(new TackleEnemy(390,670, 4, 2));
		
		//Enemigos sala 5
		enemies.add(new TackleEnemy(495,125, 5, 2));
		enemies.add(new TackleEnemy(140,85, 5, 2));
		enemies.add(new TackleEnemy(255,595, 5, 2));
		enemies.add(new TackleEnemy(510,795, 5, 2));
		
		//Enemigos sala 6
		enemies.add(new ShooterEnemy(265,495, 6, 2));
		enemies.add(new ShooterEnemy(360,495, 6, 2));
		
	    /*//Enemigos sala 7
		enemies.add(new ShooterEnemy(90,870, 7, 2));
		enemies.add(new ShooterEnemy(90,485, 7, 2));
		enemies.add(new ShooterEnemy(90,110, 7, 2));
		enemies.add(new TackleEnemy(555,110, 7, 2));
		enemies.add(new TackleEnemy(555,870, 7, 2));
		*/
		//Enemigos sala 9
		enemies.add(new ShooterEnemy(240,160, 9, 2));
		enemies.add(new ShooterEnemy(385,160, 9, 2));
		enemies.add(new ShooterEnemy(225,770, 9, 2));
		enemies.add(new ShooterEnemy(385,770, 9, 2));

		//Enemigos sala 10
		enemies.add(new ShooterEnemy(260,555, 10, 2));
		enemies.add(new ShooterEnemy(380,265, 10, 2));
		
		//Enemigos sala 11
		enemies.add(new ShooterEnemy(155,315, 11, 2));
		enemies.add(new ShooterEnemy(430,285, 11, 2));
		enemies.add(new ShooterEnemy(475,745, 11, 2));
				
		//Enemigos sala 12
		enemies.add(new ShooterEnemy(454,220, 12, 1));
		enemies.add(new ShooterEnemy(509,485, 12, 1));
		enemies.add(new ShooterEnemy(454,755, 12, 1));
		enemies.add(new ShooterEnemy(194,785, 12, 1));
		enemies.add(new ShooterEnemy(194,205, 12, 1));

		//Enemigos sala 13
		enemies.add(new ShooterEnemy(415,290, 13, 2));
		enemies.add(new ShooterEnemy(330,850, 13, 2));
		
		//Enemigos sala 14
		enemies.add(new TourretEnemy(60,385, 14, 1));
		enemies.add(new TourretEnemy(310,195, 14, 1));
		enemies.add(new TourretEnemy(510,260, 14, 1));
		enemies.add(new ShooterEnemy(235,370, 14, 2));
		enemies.add(new ShooterEnemy(270,580, 14, 2));
		enemies.add(new ShooterEnemy(370,195, 14, 2));
				
		//Enemigos sala 15
		enemies.add(new TourretEnemy(35, 200, 15, 1));
		enemies.add(new ShooterEnemy(245,425, 15, 2));		
		enemies.add(new ShooterEnemy(220,270, 15, 2));
		enemies.add(new ShooterEnemy(335,450, 15, 2));
		
		//Enemigos sala 16
		enemies.add(new TourretEnemy(265,620, 16, 1));
		enemies.add(new TourretEnemy(280,700, 16, 1));
		enemies.add(new TourretEnemy(150,305, 16, 1));
		enemies.add(new ShooterEnemy(345,430, 16, 2));
		enemies.add(new ShooterEnemy(415,200, 16, 2));
				
		//Enemigos sala 17
		enemies.add(new TourretEnemy(180,580, 17, 1));
		enemies.add(new TourretEnemy(235,190, 17, 1));
		enemies.add(new TourretEnemy(285,190, 17, 1));
		enemies.add(new ShooterEnemy(265,320, 17, 2));
		
		//Enemigos sala 18
		enemies.add(new TourretEnemy(200,335, 18, 1));
		enemies.add(new TourretEnemy(310,200, 18, 1));
		enemies.add(new TourretEnemy(310,150, 18, 1));
		
		//Enemigos sala 18
		enemies.add(new FollowerEnemy(225,205, 20, 2));
		enemies.add(new FollowerEnemy(450,205, 20, 2));
		enemies.add(new FollowerEnemy(235,775, 20, 2));
		enemies.add(new FollowerEnemy(405,775, 20, 2));
				
		//Enemigos sala 23
		enemies.add(new Boss(225, 475, 23, 10));

		

		//Objetos sala 18
		items.add(new Key(18, 1,310,780,false,0, false));

		//Objetos sala 19
		items.add(new Key(19, 1,225,483,false,0, false));

		//Objetos tienda
		items.add(new HeartContainer(22, 2, 375, 350, false, 60, false));
		items.add(new Bow(22, 1, 375, 600, false, 120, false));
		items.add(new Heart(22, 2, 375, 475, false, 10, true));
		gamefinished=false;
	}

	public boolean getGamefinished() {
		return gamefinished;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public Rooms getRooms() {
		return rooms;
	}

	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}

	public void update() {
		player.update(rooms);
		//System.out.println("x: "+player.getX()+" y: "+player.getY());
		for(Enemy e : enemies) {
			eventRoom12(e);
			player.attacked(e, rooms);
			e.update(player, items, rooms, projectiles);
			if(e instanceof Boss) {
				if (!e.isAlive()) {
					gamefinished=true;
				}
			}
		}
		for(Item i : items) {
			i.update(player, rooms);
		}
		for(Projectile t : projectiles) {
			t.update(rooms, player, enemies);
		}
	}

	private void eventRoom12(Enemy e) {
		if(rooms.getSalaActual()==12) {
			if(!rooms.isRoom12Complete()) {
				if(!e.isAlive() && !e.isCheck()) {
					e.setCheck(true);
					rooms.setRoom12Enemies(1);
				}
				if(rooms.getRoom12Enemies()==0) {
					rooms.setRoom12Complete(true);
					rooms.setRoomValue(12, 0, 6, 7);
					rooms.setRoomValue(12, 0, 7, 0);
					rooms.setRoomValue(12, 0, 8, 0);
					rooms.setRoomValue(12, 0, 9, 5);
					items.add(new Key(12,1,329,485,false,0, false));
				}
			}

		}
	}
}
