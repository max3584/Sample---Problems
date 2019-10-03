package Games;

public class Sample001 {
	public static void main(String... args) {
		Sample001 debug = new Sample001();
		System.out.print("Sample")
		System.out.print(debug.pyramid( 100, 1, new String(), "A","B"));
	}

	// Sample
	public String pyramid(int n, int top, String text, String... mark) {
		for (int i = 0; i < ((n - 1) + top); i++)
			text += ( i < (n - 1) ) ?mark[0] : mark[1];
		return (n > 1) ? this.pyramid(n - 1, top + 2,String.format("%s\n", text), mark) : text;
	}
	
}
