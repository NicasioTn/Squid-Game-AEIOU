import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author 63011212098
 *
 */
public class Squid_Game_098 {

	public static void main(String[] args) {
		MySquidGame window = new MySquidGame();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

}

class MySquidGame extends JFrame {
	
	EveryOne boss = new EveryOne();

	public MySquidGame() {

		setTitle("Lab SquidGame \"in class A M\"");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 750);
		setLayout(null);
		setResizable(false);
		
		add(boss); // on frame

	}
}

class EveryOne extends JPanel {
	
	Image girlback = Toolkit.getDefaultToolkit()
			.createImage(System.getProperty("user.dir") + File.separator + "dollback.png");
	Image girlfront = Toolkit.getDefaultToolkit()
			.createImage(System.getProperty("user.dir") + File.separator + "dollfront.png");

	Image userGreen = Toolkit.getDefaultToolkit()
			.createImage(System.getProperty("user.dir") + File.separator + "runner.png");

	
	boolean state = false;
	boolean end = false;
	int[] runX = new int[10];
	int[] runY = new int[10];
	
	JPanel redline = new JPanel();
	Timer time1, time2;
	
	public EveryOne() {
		
		setSize(400, 750);
		setLayout(null);
		setBackground(Color.black);
		
		redline.setBounds(0,220,400,20);
		redline.setBackground(Color.red);
		
		add(redline);
		
		for (int i = 0; i < runX.length; i++) {
			runX[i] = new Random().nextInt(350) + 0;
			runY[i] = 650;
		}

		time1 = new Timer();
		time1.scheduleAtFixedRate(new ReHead(this), 1000, 1000);
		time2 = new Timer();
		time2.scheduleAtFixedRate(new Hero(this), 0, 100);

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g); 
		
		for (int i = 0; i < runX.length; i++) 
		{
			if(runY[i] <= 220)
			{
				g.drawImage(null, runX[i], runY[i], 50, 50, this);
			}
			else
			{
				g.drawImage(userGreen, runX[i], runY[i], 50, 50, this);
			}
			if(runY[i] <= 100) // finished 
			{
				time1.cancel();
				time2.cancel();
				removeAll();
				end = true;
			}
			
		}
		
		if (state == false) // front
		{ 
			if(end == true)
			{
				g.drawImage(girlfront, 0, 60, 400, 600, this); // Big Picture
			}
			else // if program stay still running.
			{
				g.drawImage(girlfront, 140, 10, 100, 200, this);
			}
			
			//System.out.println("x");
		}
		else// true == back
		{
			g.drawImage(girlback, 140, 10, 100, 200, this);
			//System.out.println("y");
		}
	}

	public void setState(boolean set) {
		this.state = set;
	}

	public boolean getState() {
		return this.state;
	}
}

//Control Head of Girl
class ReHead extends TimerTask { 
	
	EveryOne girl;

	public ReHead(EveryOne girl) {
		this.girl = girl;
	}

	@Override
	public void run() {

		//System.out.println(girl.getState());
		if (girl.getState() == false) // front
		{
			try {Thread.sleep(1000);} 
			catch (InterruptedException e1) {e1.printStackTrace();}
			girl.setState(true);
			
		} 
		else  // true back
		{
			try {Thread.sleep(1000);} 
			catch (InterruptedException e1) {e1.printStackTrace();}
			girl.setState(false);
			
		}
		girl.repaint();
	}
}
// Control lake for runner
class Hero extends TimerTask
{
	EveryOne run;
	
	public Hero(EveryOne runner) {
		this.run = runner;
	}
	
	@Override
	public void run() {
		if(run.getState() == true)
		{
			for(int i = 0 ;i < run.runY.length; i++)
			{
				run.runY[i] += new Random().nextInt(10)-10; // random speed
				//System.out.println(run.runY[i]);
			}
		}
		else
		{
			for(int i = 0 ;i < run.runY.length; i++)
			{
				run.runY[i]= run.runY[i];
			}
		}
		run.repaint();
		
	}
}

