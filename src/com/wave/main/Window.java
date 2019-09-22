package com.wave.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7091006469336366864L;
	
	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);
		
		//sets sizes for the window
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//If not defined, doesn't stop thread of game operations
		frame.setResizable(false);//Test This as Well
		frame.setLocationRelativeTo(null);//Test why this is important!!
		frame.add(game);
		frame.setVisible(true);
		game.start();
		
	}

}
