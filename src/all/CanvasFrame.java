package all;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class CanvasFrame extends JFrame implements Runnable{
	public static final int HEIGHT = 500, WIDTH = 500;
	
	
	Thread thread;
	boolean running = false;
	
	Canvas canvas = new Canvas();
	Graphics g;
	BufferStrategy bs;
	
	CanvasManager cm;
	
	public CanvasFrame(String title, CanvasManager cm) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(HEIGHT, WIDTH);
		add(canvas);
		
		
		this.cm = cm;
		
		setVisible(true);
		canvas.createBufferStrategy(2);
	}
	
	public void setCanvasManager(CanvasManager x) {
		if(running) this.stop();
		cm = x;
	}
	
	public void start() {
		System.out.println("AAA" + running);
		if(running) return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() {
		running = false;
	}

	
	@Override
	public void run() {
		long time = 0, time_past = 0;
		final int fps = 60;
		int frames = 0;
		final long ratio_t = 1000000000 / fps;
		double delta = 0;
		
		long timer_s = 0;
		
		cm.innit();
		
		while(running) {
			time = System.nanoTime();
			
			if(1 < delta) { 
				cm.tick(delta);
				delta = 0;
			}
			cm.render( canvas.getBufferStrategy() );
			
			long dif = time - time_past;
			delta += dif / 1000000000 * 60;
			timer_s += dif;
			frames++;
			
			if(timer_s > 1000000000) {
				System.out.println("SI-> " + frames);
				System.out.println(dif / ratio_t);
				timer_s = 0;
				frames = 0;
			}
			
			time_past = time;
		}
		
		cm.end();
	}

}
