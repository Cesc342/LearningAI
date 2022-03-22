package all;

import java.awt.image.BufferStrategy;

public interface CanvasManager {
	public void innit();
	public void end();
	public void tick(double delta);
	public void render(BufferStrategy g);
}
