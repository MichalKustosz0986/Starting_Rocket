package kustosz;

import javax.swing.*;


public class AppRocket extends JFrame {
	
	private static JSlider slider;
	private static int maxRange = 300;
	private static int m = 5;
	private static double g = 9.81*0.4;
	
	public AppRocket() {
		
		
		setTitle(" ***Rocket Science*** ");
		
		slider = new JSlider(JSlider.HORIZONTAL, 0, maxRange,299);
		slider.setMinorTickSpacing(10);
		slider.setMajorTickSpacing(50);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		
		this.add(slider);
	}
//===========================================================================	
	public int getSliderValue() {
		
		int result = slider.getMinorTickSpacing();
		slider.getValue();
		return result;
	}
//===========================================================================
	public static void main(String s[]) throws InterruptedException {
		
		
		double v = 0;
		long time = 0;
		int dt = 50;
		double integral = 0;
		double VmaxEndurance = 400;
		
		int yPos = 900;
		Canvas canvas = Canvas.getCanvas();
		
		
		AppRocket frame = new AppRocket();
		frame.setVisible(true);
		frame.setSize(500, 150);
		frame.setLocation(850, 20);
		
		
		Circle earth = new Circle();
		earth.makeVisible();
		
		earth.setXYPosition(-250, 930);
		earth.changeSize(1000);
		earth.changeColor("brown");
		
		
		
		Circle rocket = new Circle();
		rocket.makeVisible();
		rocket.setXYPosition(225, yPos);
		
		Circle fireBall = new Circle();
		fireBall.makeVisible();
		fireBall.changeColor("yellow");
		fireBall.setXYPosition(  rocket.getXCoord()+10, rocket.getYCoord() + 30  );
		
		do {
			try {
		
				
				integral +=slider.getValue()*dt * 0.1;
				v = g*time - (1.0 / m)* integral;
				
				yPos = (int) v*dt;   
				int yPosRescaled = (int) ((int) yPos / 1e5);   //skalowanie coby za szybko nie spadalo
				
				
				rocket.moveVertical(  yPosRescaled  );
				fireBall.changeSize( (slider.getValue() / 5) );
				fireBall.moveVertical(  yPosRescaled  );
																								//reskalowanie
				System.out.println("time = " + time + "\t trust = " + slider.getValue()+"\t yPos = " + rocket.getYCoord()+
						"\tv= "+v);
				
				time+=dt;
				Thread.sleep(dt);
				canvas.setForegroundColor("blue");
				
				
				if(rocket.getYCoord() >= 980 && v>=VmaxEndurance ) {
					
					System.out.println("***********************");
					System.out.println("**ZNISZCZYLES STATEK***");
					System.out.println("***********************");
					
					JOptionPane.showMessageDialog(new JFrame() ," ZNISZCZYLES STATEK !!!");  
					break;
				}
				
				else if( slider.getValue() == maxRange ) {
					
					System.out.println("****************************");
					System.out.println("!!! PRZEGRZALEŚ SILNIK !!!");
					System.out.println("!!! KONEC GRY !!!");
					System.out.println("****************************");
					JOptionPane.showMessageDialog(new JFrame() ,"PRZEGRZALEŚ SILNIK ");  
					break;
				}
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while ( slider.getValue() != maxRange);
		
		
		System.out.println("****************************");
		System.out.println("KONIEC GRY");
		System.out.println("****************************");
		JOptionPane.showMessageDialog(new JFrame() ,
				" ****************************\n"
				+ "KONIEC GRY\n"
				+ " **************************** ");  
		
	}
}