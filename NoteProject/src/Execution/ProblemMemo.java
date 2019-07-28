package Execution;

import java.util.NoSuchElementException;

import Note.Note;
import System.console.WindowsCC;

public class ProblemMemo {

	public static void main(String[] args) {
		WindowsCC wc = new WindowsCC(); // windows command(利用目的は単純に見やすさだけである)
		String command = "cls"; // 引数入力省略のための変数
		LocalNote book; // 処理のメインとなるクラス
		// 初期設定
		do {
			System.out.println("問題数はいくつか");
			try {
				int n = Integer.parseInt(Note.stdIn.nextLine());
				book = new LocalNote(n);
				break;
			} catch (NumberFormatException e) {
				System.out.println("数字のみの入力です\n");
				wc.ConsoleCommand(command);
			} catch (NoSuchElementException e) {
				System.out.println("何も入力されていませんので再度入力しなおしてください\n");
				wc.ConsoleCommand(command);
			}
		} while (true);

		int brkNumber = -1;

		loop: for (int i = 0; i < book.note.length; i++) {
			System.out.println("使用可能なコマンドは[edit, read, end, <SymbolMark>]");
			System.out.print(String.format("%d問目>", i + 1));
			String select = book.write();
			switch (select) {
			case "edit":
				book.note[i] = book.edit();
				break;
			case "read":
				book.read(book.note);
				break;
			case "end":
				brkNumber = i;
				break loop;
			default:
				book.note[i] = select;
			}
			wc.ConsoleCommand(command);
		}

		System.out.println("結果::");
		for (int i = 0; i < book.note.length; i++) {
			System.out.println(String.format("%d問目：%s", i + 1, book.note[i]));
		}

		System.out.println("何問間違えたか");
		int falls = Integer.parseInt(book.write());
		// 結果表示のための変数
		if (brkNumber == -1) {
			double trues = book.note.length - falls;
			System.out.println(String.format("問題数:%d", book.note.length));
			System.out.println(String.format("正当数:%d", (int) trues));
			System.out.println(String.format("正答率 : %3.2f%%", (double) (trues / book.note.length) * 100));
		} else {
			double trues = brkNumber - falls;
			System.out.println(String.format("問題数:%d", brkNumber));
			System.out.println(String.format("正当数:%d", (int) trues));
			System.out.println(String.format("正答率 : %s", (double) (trues / brkNumber) * 100));
		}
	}
}
