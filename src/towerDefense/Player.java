package towerDefense;

public class Player {

	private int health, money;
	
	public Player() {
		health = 100;
		money = 100;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int h) {
		health = h;
	}
	
	public int getMoney() { // lets get this bread
		return money;
	}
	
	public void setMoney(int m) {
		money = m;
	}
}
