package io.aidantaylor.towerdefense.window;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import io.aidantaylor.towerdefense.gameobject.PattyTower;
import io.aidantaylor.towerdefense.gameobject.TomTower;
import io.aidantaylor.towerdefense.gameobject.Tower;
import io.aidantaylor.towerdefense.utils.IntObj;

public class GameMenuPanel extends JPanel {
	
	private static final long serialVersionUID = 7476274516694678403L;

	private int width, height;

	private GameDisplayPanel display;
	
	private JLabel healthLabel, moneyLabel;
	private JButton addTomTowerButton, addPattyTowerButton;
	public JButton startRoundButton;
	
	private IntObj playerMoney;
	
	public GameMenuPanel(int w, int h, IntObj m, GameDisplayPanel d) {
		super(new GridBagLayout());
		
		width = w;
		height = h;
		playerMoney = m;
		display = d;
		
		setPreferredSize(new Dimension(width, height));
		
		// begin creating all the components we will add to the panel
		
		// health and money labels
		healthLabel = new JLabel();
		moneyLabel = new JLabel();
		
		// button to add TomTower to map
		addTomTowerButton = new JButton("Tom Tosser");
		addTomTowerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (playerMoney.value >= Tower.getPriceMap().get(TomTower.class)) 
					display.setTowerPreview(TomTower.class);
			}
		});
		
		// button to add PattyTower to map
		addPattyTowerButton = new JButton("Patty Tosser");
		addPattyTowerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (playerMoney.value >= Tower.getPriceMap().get(PattyTower.class))
					display.setTowerPreview(PattyTower.class);
			}
		});
		
		//button to start the round
		startRoundButton = new JButton("Start");
	
		// setup GridBag Constraints for panel
				GridBagConstraints c = new GridBagConstraints();
				c.fill = GridBagConstraints.BOTH;
				
				c.gridx = 0;
				c.gridy = 0;
				c.gridwidth = 2;
				c.anchor = GridBagConstraints.LINE_END;
				healthLabel.setHorizontalAlignment(SwingConstants.CENTER);
				add(healthLabel, c);
				
				c.gridx = 0;
				c.gridy = 1;
				moneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
				add(moneyLabel, c);
				
				c.gridx = 0;
				c.gridy = 2;
				c.gridwidth = 1;
				add(addTomTowerButton, c);
				
				c.gridx = 1;
				c.gridy = 2;
				add(addPattyTowerButton, c);
				
				c.gridx = 0;
				c.gridy = 3;
				c.gridwidth = 2;
				add(startRoundButton, c);
	}
	
	public void setHealthLabel(int h) {
		healthLabel.setText("Health: "+h);
	}
	
	public void setMoneyLabel(int m) {
		moneyLabel.setText("Money: "+m);
	}
	
}
