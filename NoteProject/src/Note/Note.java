package Note;

import java.util.Scanner;

public interface Note {
	
	// スタンダードインプット
	public static final Scanner stdIn = new Scanner(System.in);
	
	// 書き込み、読み込み処理用のメソッド
	public String edit();									// 複数行にわたる文を打ち込むときに使用するメソッド
	public void read(String... StreamText);						// 読み込むためのメソッド
	public String write();									// 書き込む(一文字またはcsv形式の文字列を読み込む場合
}
