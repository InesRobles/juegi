package modelo;

public abstract class Item {
	protected int room;
	protected int x;
	protected int y;
	protected boolean active;
	protected boolean createdByEnemy;
	protected int amount;
	protected int price;
	protected boolean respawn;
	public Item(int room, int amount, int x,int y, boolean createdByEnemy, int price, boolean respawn) {
		this.room=room;
		this.x=x;
		this.y=y;
		this.amount=amount;
		active=true;
		this.createdByEnemy=createdByEnemy;
		this.price=price;
		this.respawn=respawn;
	}
	public boolean isCreatedByEnemy() {
		return createdByEnemy;
	}
	public void setCreatedByEnemy(boolean createdByEnemy) {
		this.createdByEnemy = createdByEnemy;
	}
	public boolean isActive() {
		return active;
	}
	public void setAlive(boolean alive) {
		this.active = alive;
	}
	public int getRoom() {
		return room;
	}
	public void setRoom(int room) {
		this.room = room;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getAmount() {
		// TODO Auto-generated method stub
		return amount;
	}
	public int getPrice() {
		return price;
	}
	public void update(Player player, Rooms rooms) {
		if(room==rooms.getSalaActual()) {
			if(isActive()) {
				player.itemCollect(this);
			}
		}else {
			if(isCreatedByEnemy()) {
				active=false;
			}
			if(respawn) {
				active=true;
			}
		}
	}
}
