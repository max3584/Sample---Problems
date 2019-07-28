package Execution;

import Note.Note;

import Note.Symbol.SymbolDefine;

public class LocalNote implements Note {
	
	String[] note;

	// SymbolDefine のOverrideと実装(並び替え)
	SymbolDefine Oders =(str) -> {
		str.toUpperCase();
		String[] stock = str.split(",");

		// バブルソート(文字列版)
		if (stock.length >= 2 & str.indexOf("Edditer") == -1) {
			for (int i = 0; i < stock.length - 1; i++) {
				for (int j = i + 1; j < stock.length; j++) {
					if (stock[i].compareTo(stock[j]) > 0) {
						String st = stock[i];
						stock[i] = stock[j];
						stock[j] = st;
					}
				}
			}
		}
		
		// 成形処理
		StringBuffer format = new StringBuffer();
		for (String print : stock)
			format.append(print + ",");
		format.delete(format.length() - 1, format.length());
		
		return format.substring(0);
	};

	public LocalNote(int n) {
		this.note = new String[n];
	}

	// 問に対する設問用のメソッド
	@Override
	public String edit() {
		StringBuffer sb = new StringBuffer("Edditer\t\n");
		System.out.println("タイトルを入力してください");
		String title = Note.stdIn.nextLine();
		sb.append(String.format("%s\n", title));
		String memo;
		loop: do {
			System.out.println("eofが入力されるまでループされます");
			System.out.println("タイトルの変更の場合はccと打ってください");
			System.out.print(String.format("%s＞", title));
			memo = Note.stdIn.nextLine();
			switch (memo) {
			case "cc":
				System.out.println(String.format("現在のタイトルは%sです", title));
				title = Note.stdIn.nextLine();
				sb.append(String.format("%s\n", title));
				continue;
			case "eof":
				break loop;
			default:
				sb.append(String.format("\t%s", memo));
			}

		} while (memo.equals("eof"));

		return Oders.CharacterChanger(sb.substring(0));
	}

	// 仮引数で受け取ったものをすべて表示する
	@Override
	public void read(String... StreamText) {
		for (int i = 0; i < StreamText.length - 1; i++) {
			System.out.println(String.format("%d問目: %s", i, StreamText[i]));
		}

	}

	// 問いに対する回答入力用メソッド
	@Override
	public String write() {
		return Oders.CharacterChanger(Note.stdIn.nextLine());
	}

}
