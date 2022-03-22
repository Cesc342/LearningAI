import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import all.*;
import all.motxila.SimuladorMotxilla;

public class Main {	
	public static void main(String[] args) {
		CanvasFrame cf = new CanvasFrame("Hola", new EventsCanvas());
		cf.start();
		
		/*Main m = new Main();
		m.runSimulator();*/
	}
	
	public Map <String, Object> objectes;
	
	public void runSimulator() {
		Scanner console = new Scanner(System.in);
		boolean running = true;
		objectes = new HashMap<String, Object>();
		objectes.put("sm", new SimuladorMotxilla(100, 10) );
		
		while(running) {
			String[] inp = console.nextLine().split(" ");
			System.out.println();
			
			switch(inp[0]) {
			case "getBest":
				break;
				
			case "train":
				break;
				
			case "input":
				outArrayString(inp);
				
				break;
				
				
			case "call":
				
				switch(inp[1]) {
				case "SimuladorMotxilla":
					
					break;
				}
				
				break;
				
				
			case "create":
				
				switch(inp[1]) {
				
				case "SimuladorMotxilla":
					System.out.println();
					System.out.print("Num. Motxilles: ");
					int numMotxilles = console.nextInt();
					if(numMotxilles < 1) break;
					
					System.out.println();
					System.out.print("Num. Objectes (gens): ");
					int numObjectes = console.nextInt();
					if(numObjectes < 1) break;
					
					objectes.put("sm", new SimuladorMotxilla(numMotxilles, numObjectes) );

					break;
					
				default:
					break;
				}
				
				System.out.println("Creacio terminada");
				
				break;
				
				
				
				
			case "exit":
				running = false;
				break;
				
			default:
				try {
					Object objecta = objectes.get(inp[0]);
					Class<? extends Object> clase = objecta.getClass();
					
					if(inp.length > 2) {
						Method metode = clase.getMethod("_" + inp[1], String[].class);
						String[] args = getArgs(inp);
						metode.invoke(objecta, (Object) args);
					} else {
						Method metode = clase.getMethod(inp[1]);
						metode.invoke(objecta);
					}
					
				}catch(Exception e) {
					System.out.println("ERROR: " + e.getLocalizedMessage());
					e.printStackTrace();
				}
				
				
				break;
			}
		}
		
		console.close();
	}
	
	
	public static void outArrayString(String[] arr) {
		System.out.println();
		System.out.print("[");

		System.out.print( String.join(",", arr) );
		
		System.out.print("]");
		System.out.println();
	}
	
	public static String[] getArgs(String[] inp) {
		String[] args = new String[inp.length - 2];
		for(int i = 2; i < inp.length; i++) {
			args[i - 2] = inp[i];
		}
		return args;
	}

}


class EventsCanvas implements CanvasManager {
	static final int CF_HEIGHT = CanvasFrame.HEIGHT;
	static final int CF_WIDTH = CanvasFrame.WIDTH;

	
	Graphics g;
	int x = 0, y = 0;
	
	@Override
	public synchronized void innit() {
		// TODO Auto-generated method stub
		System.out.println("START");
	}

	@Override
	public synchronized void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public synchronized void tick(double delta) {
		// TODO Auto-generated method stub
		x++;
		y++;
		System.out.println(delta);
	}

	@Override
	public synchronized void render(BufferStrategy bs) {
		// TODO Auto-generated method stub
		g = bs.getDrawGraphics();
		
		g.clearRect(0, 0, CF_WIDTH, CF_HEIGHT);
		g.fillRect(x, y, 100, 100);
		
		bs.show();
		g.dispose();
	}
}
