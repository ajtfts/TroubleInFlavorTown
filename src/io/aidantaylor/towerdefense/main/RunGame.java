package io.aidantaylor.towerdefense.main;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import io.aidantaylor.towerdefense.gameobject.Enemy;
import io.aidantaylor.towerdefense.gameobject.GameObject;
import io.aidantaylor.towerdefense.gameobject.OrangeEnemy;
import io.aidantaylor.towerdefense.gameobject.Tower;
import io.aidantaylor.towerdefense.gameobject.TowerBullet;
import io.aidantaylor.towerdefense.utils.GameMap;
import io.aidantaylor.towerdefense.utils.IntObj;
import io.aidantaylor.towerdefense.window.GameDisplayPanel;
import io.aidantaylor.towerdefense.window.GameMenuPanel;
import io.aidantaylor.towerdefense.window.GameWindow;

/**
 * 
 * @author Aidan Taylor
 * @date 11/29/2018
 * @description Driver file. Contains the main loop and everything required to construct it.
 */

public class RunGame {

	private static GameWindow window;
	private static GameDisplayPanel display;
	private static GameMenuPanel menu;
	private static float aspectRatio = 16.0f/9.0f;
	private static int windowHeight = 1200, windowWidth = (int) (windowHeight / aspectRatio);
	
	private static GameMap map;
	private static int[] mapStartPos, mapEndPos;
	
	private static IntObj playerMoney = new IntObj(100), playerHealth = new IntObj(3);
	private static ArrayList<GameObject> renderList = new ArrayList<GameObject>();
	private static ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	
	private static ArrayList<CallbackObject> queue = new ArrayList<CallbackObject>(); // queue is ArrayList of CallbackObjects, each containing a callback and a waitTime.
	
	private static final int TARGET_FPS = 60;
	private static final long OPTIMAL_TIME = 1000000000 / TARGET_FPS; // convert target frames-per-second to target time between frames in nanoseconds
	private static boolean running = true;
	
	public static void main(String[] args) {
		
		map = new GameMap("maptest.txt");
		mapStartPos = map.getStartPos();
		mapEndPos = map.getEndPos();
		
		window = new GameWindow(windowHeight, windowWidth, map, playerMoney, playerHealth, renderList);
		display = window.getDisplayPanel();
		menu = window.getMenuPanel();
		
		menu.setMoneyLabel(playerMoney.value);
		menu.setHealthLabel(playerHealth.value);
		
		
		// what code to execute when the start button is pressed
		menu.startRoundButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Callback createEnemy = () -> {
					float[] gameStartPos = GameMap.MapToGamePos(mapStartPos[0], mapStartPos[1], GameDisplayPanel.TILE_SIZE, GameObject.Anchor.CENTER);
					OrangeEnemy enemy = new OrangeEnemy(gameStartPos[0], gameStartPos[1]);
					enemyList.add(enemy);
					enemy.setVelocity(1, 0);
				};
				
				for (int i = 0; i < 5; i++)
					queueCallback(createEnemy, 1000*i); // create 5 enemies, each 1 second apart from each other
			}
		});
		
		gameLoop();
	}
	
	private static void updateLogic(long deltaT) {
		double w = deltaT/((double) OPTIMAL_TIME);
		
		for (GameObject obj : renderList) {
			float[] velocity = obj.getVelocity();
			obj.move(velocity[0], velocity[1], w);
			
			if (obj instanceof Enemy) {
				float[] pos = obj.getPos();
				if (GameMap.PointInTile(pos[0], pos[1], mapEndPos[0], mapEndPos[1], GameDisplayPanel.TILE_SIZE)) {
					queueCallback(() -> {
						renderList.remove(obj);
						enemyList.remove(obj);
						playerHealth.value--; // would put some "game over" code after this if playerHealth is reduced to zero, but the grading criteria doesn't explicitly state it's necessary
						menu.setHealthLabel(playerHealth.value);
					});
				}
			}
			
			if (obj instanceof TowerBullet) {
				float[] bulletPos;
				float[] enemyPos;
				for (Enemy enemy : enemyList) {
					bulletPos = obj.getPos();
					enemyPos = enemy.getPos();
					// next line is collision detection between enemies and bullets. since both are circles, you can just test whether the distance between them is greater than the sum of their radiuses.
					if (distanceBetweenPoints(bulletPos, enemyPos) < TowerBullet.DIAMETER/2+Enemy.getDimsDict().get(enemy.getClass())[0]/2) {
						queueCallback(() -> {
							enemy.takeDamage(TowerBullet.DAMAGE);
							if (enemy.getHealth() <= 0) {
								renderList.remove(enemy);
								enemyList.remove(enemy);
							}
							renderList.remove(obj);
						});
					}
					
				}
			}
			
			if (obj instanceof Tower) { 
				Enemy closestToEndInRange = null;
				float[] gameEndPos = GameMap.MapToGamePos(mapEndPos[0], mapEndPos[1], GameDisplayPanel.TILE_SIZE, GameObject.Anchor.CENTER);

				for (Enemy enemy : enemyList) { // find the enemy within the range of the tower that is closest to the end. worth noting that I haven't implemented tower range
					if (closestToEndInRange == null)
						closestToEndInRange = enemy;
					else {
						if (distanceBetweenPoints(closestToEndInRange.getPos(), gameEndPos) > distanceBetweenPoints(enemy.getPos(), gameEndPos))
							closestToEndInRange = enemy;
					}
				}
			
				if (closestToEndInRange != null) {
					float[] enemyPos = closestToEndInRange.getPos();
					obj.lookAt(enemyPos[0], enemyPos[1]);
					if (((Tower) obj).isFiring() == false)
						((Tower) obj).beginFiring();
				} else {
					((Tower) obj).stopFiring();
				}
			}
			
		}
		
		// following code will handle the callback queue.
		for (int i = 0; i < queue.size(); i++) {
			CallbackObject cur = queue.get(i);
			long waitTime = cur.getWaitTime();
			if (waitTime == 0) { // if there isn't a waitTime on the callback object just execute it immediately
				cur.getCallback().execute();
				queue.remove(i); // make sure to pop the callback object from the queue after its been executed
			}
			else if (waitTime > 0) {
				long creationTime = cur.getCreationTime();
				if (creationTime + waitTime <= System.nanoTime()) { // tests if the callback is ready to be executed
					cur.getCallback().execute();
					queue.remove(i);
				}
			}
		}
		
		menu.setMoneyLabel(playerMoney.value);
	}
	
	private static void gameLoop() {
		
		boolean first = true;
		
		long now, deltaT;
		long lastFrame = System.nanoTime();
		
		int difX = 0, difY = 0, mapX = 0, mapY = 0, initXOffset = 0, initYOffset = 0;
		
		while (running) {
			now = System.nanoTime();
			deltaT = now - lastFrame; 
			
			lastFrame = now;
			
			//update game state
			updateLogic(deltaT);
			
			// change map view by dragging mouse
			if (display.getMouseState()) {
				if (first) {
					Point mPos = MouseInfo.getPointerInfo().getLocation();
					mapX = (int) mPos.getX();
					mapY = (int) mPos.getY();
					initXOffset = display.getXOffset();
					initYOffset = display.getYOffset();
					first = false;
				} else {
					Point mPos = MouseInfo.getPointerInfo().getLocation();
					difX = (int) mPos.getX() - mapX;
					difY = (int) mPos.getY() - mapY;
					display.setOffset(initXOffset+difX, initYOffset+difY);
				}
			} else {
				first = true;
			}
			
			// redraw
			display.repaint();
			
			try {
				
				now = System.nanoTime();
				
				if (lastFrame - now >= 0)
					// Sleep for OPTIMAL_TIME - how long updateLogic and repaint took. Divide by 10^6 as Thread.sleep takes milliseconds
					Thread.sleep((lastFrame-now + OPTIMAL_TIME)/1000000); 
				else
					Thread.sleep(OPTIMAL_TIME/1000000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static float distanceBetweenPoints(float[] p1, float[] p2) {
		return (float) Math.sqrt(Math.pow((p1[0]-p2[0]), 2) + Math.pow((p1[1]-p2[1]), 2));
	}
	
	public static void queueCallback(Callback callback) {
		queue.add(new CallbackObject(callback, 0));
	}
	
	public static void queueCallback(Callback callback, long ms) { // this method is the functional equivalent of javascript's setTimeout.
		queue.add(new CallbackObject(callback, ms));               // takes a callback and a time representing how long to wait to execute the callback.
	}
}



