package Games;

public class Sample001 {
	public static void main(String... args) {
		Sample001 debug = new Sample001();
		/********************/
		int n = 100; 					//行数の数
		int top = 1; 					//トップの列を何列で開始するか
		String[] mark = {"A", "B"};		//マークの種類
		/********************/

		System.out.print("Sample");
		System.out.print(debug.pyramid( n, top, new String(), mark));
	}

	/**
	 * @param n			行数の数
	 * @param top		トップの列を何個にするか
	 * @param text		再帰を行うための保存Stringバッファ
	 * @param mark...	2種類以上のマークを付けてください、そうしないとピラミッドの形がきれいに表示されない可能性があります
	 */

	// Sample
	public String pyramid(int n, int top, String text, String... mark) {
		for (int i = 0; i < ((n - 1) + top); i++)
			text += ( i < (n - 1) ) ?mark[0] : mark[1];
		return (n > 1) ? this.pyramid(n - 1, top + 2,String.format("%s\n", text), mark) : text;
	}
	
}
