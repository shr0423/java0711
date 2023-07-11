package org.sp.app0711.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class GameMain extends JFrame{
	GamePanel gamePanel;
	
	public GameMain() {
		gamePanel=new GamePanel();
		add(gamePanel);
		
		
		pack();
		setVisible(true);
		setLocationRelativeTo(null);//html 마진오토
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//윈도우가 취득한 포커스를 패널에 전달하기
		gamePanel.requestFocus();
		
	}
	
	
	public static void main(String[] args) {
		new GameMain();
	}
}
