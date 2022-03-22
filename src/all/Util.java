package all;

public class Util {
	public static <T> T RandomValueArray(T[] arr) {
		int i = (int) (Math.random() * arr.length);
		return arr[i];
	}
}
