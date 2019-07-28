package Export;

import java.io.FileWriter;
import java.io.FileReader;

/**
 * 	document
 * <p>・ファイル書き込みと読み込み・追加ファイル等の作成
 * 		  ・コンソール打ち出し用</p>
 */
public interface ReportPrint {
	public void report(FileWriter fw);		//ファイル書き込み用
	public void read(FileReader fr);			//ファイル読み込み用
	public void Cprint(String str);			// コンソール出力用
}
