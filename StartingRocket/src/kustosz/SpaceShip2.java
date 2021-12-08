package kustosz;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;

import satelite_v_1.1.src.Trust;

public class SpaceShip2 extends JPanel implements KeyListener {
	
	private static JSlider slider;
	private static int maxRange = 300;
	private static int m = 5;
	private static double g = 9.81*0.4;
	
	
	int xStarShip = 500;
	int yStarShip = 950;
	private int trustValue = 0;

	public SpaceShip2() {
		
	//setTitle(" ***Rocket Science*** ");
		
		slider = new JSlider(JSlider.HORIZONTAL, 0, maxRange,299);
		slider.setMinorTickSpacing(10);
		slider.setMajorTickSpacing(50);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		
		this.add(slider);

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1024, 1024);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		rysujTlo(g);
		drawSpaceShip(g);

	}

	private void drawSpaceShip(Graphics g) {
		try {
			g.drawImage(ImageIO.read(new File("spaceShip.png")), xStarShip, yStarShip, 80, 80, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void rysujTlo(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	private void showPosition() {
		System.out.println("x: " + xStarShip + " y: " + yStarShip);
	}

//=========================================================================	
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//		Trust ciag = new Trust();
//		ciag.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		ciag.setBounds(1100, 300, 300, 150);
//		ciag.setVisible(true);

		SpaceShip okno = new SpaceShip();

		window.add(okno);
		window.setVisible(true);
		window.addKeyListener(okno);
		window.pack();
	}
//=========================================================================

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_UP) {

			//yStarShip -= 20;
			
			trustValue +=50;
			slider.setValue(trustValue);
			System.out.println("thrust = " + trustValue);
		}
		
		if (key == KeyEvent.VK_DOWN) {
			//yStarShip += 20;
			trustValue -=50;
			slider.setValue(trustValue);
			System.out.println("thrust = " + trustValue);
		}

		showPosition();
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

//		if (key == KeyEvent.VK_SPACE) {
//
//		}
//
//		repaint();

	}

}
