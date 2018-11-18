package io.aidantaylor.towerdefense.window;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import io.aidantaylor.towerdefense.gameobject.PattyTower;
import io.aidantaylor.towerdefense.gameobject.TomTower;

public class GameMenuPanel extends JPanel {
	
	private static final long serialVersionUID = 7476274516694678403L;

	private int width, height;

	private GameDisplayPanel display;
	
	private JLabel healthLabel, moneyLabel;
	private JButton addTomTower, addPattyTower;
	private JButton startRound;
	
	public GameMenuPanel(int w, int h, GameDisplayPanel d) {
		super(new GridBagLayout());
		
		width = w;
		height = h;
		
		display = d;
		
		setPreferredSize(new Dimension(width, height));
		
		// begin creating all the components we will add to the panel
		
		// health and money labels
		healthLabel = new JLabel();
		moneyLabel = new JLabel();
		
		// button to add TomTower to map
		addTomTower = new JButton("Tom Tosser");
		addTomTower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.setTowerPreview(TomTower.class);
			}
		});
		
		// button to add PattyTower to map
		addPattyTower = new JButton("Patty Tosser");
		addPattyTower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.setTowerPreview(PattyTower.class);
			}
		});
		
		//button to start the round
		startRound = new JButton("Start");
	
		// setup GridBag Constraints for panel
				GridBagConstraints c = new GridBagConstraints();
				c.fill = GridBagConstraints.BOTH;
				
				c.gridx = 0;
				c.gridy = 0;
				c.gridwidth = 2;
				add(healthLabel);
				
				c.gridx = 0;
				c.gridy = 1;
				add(moneyLabel);
				
				c.gridx = 0;
				c.gridy = 2;
				c.gridwidth = 1;
				add(addTomTower, c);
				
				c.gridx = 1;
				c.gridy = 2;
				add(addPattyTower, c);
				
				c.gridx = 0;
				c.gridy = 3;
				c.gridwidth = 2;
				add(startRound, c);
	}
	
}
