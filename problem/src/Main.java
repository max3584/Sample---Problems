package problem;

public class Main {
    public static void main(String[] args) {
        IPcalcProblem calc = new IPcalcProblem();
        System.out.println("以下のＩＰアドレスのネットワークアドレスとブロードキャストアドレスを求めよ \n");
        if (calc.getRandom() == 0) {
            System.out.println(String.format("%s / %s",calc.printip(),calc.printmask()));
        } else {
            System.out.println(String.format("%s / %s",calc.printip(),calc.printmask2()));
        }

        calc.plzpushkey();
        System.out.println(String.format("ネットワークアドレス : %s\n", calc.printnet()));
        System.out.println(String.format("ブロードキャストアドレス : %s\n", calc.printbload()));
        System.out.println(String.format("サブネット内アドレス範囲 : %s - %s\n", calc.printstnetwork(), calc.printednetwork()));
    }
}