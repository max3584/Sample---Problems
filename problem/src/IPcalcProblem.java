package problem;

import java.util.Random;
import java.util.Scanner;

public class IPcalcProblem {
    private String prints;                  // print variable
    private int nums;                       // nums variable (0 => x <= 255)
    private Random rand;                    // random variable
    private String networkClass;            //network Class
    private int[] address = new int[4];     // ip address variable
    private int[] network = new int[4];     // network address variable
    private int[] subnet = new int[4];      // subnetwork address variable
    private int[] bloadcast = new int[4];   // bloadcast address variable

    // consturacter
    public IPcalcProblem () {
        this.rand = new Random();
        setup();
        System.out.println("ネットワークアドレスとのクラスが決まりました");
        createSubnet();
        System.out.println("サブネットワーク作成が終了しました");
    }

    // ネットワークアドレスの策定とクラスフルアドレスの作成

    // setups
    private void setup() {
        this.nums = this.rand.nextInt(236);
        this.networkClass = checkClass(this.nums);
        // ここで決まるのはクラスフルのＩＰアドレスのみ
        this.setSubnet(classFullSubnet(this.networkClass));
        // IP アドレスの確定
        this.address[0] = this.nums;
        for (int i = 1; i < address.length; i++) {
            this.address[i] = rand.nextInt(256);
        }

    }

    // checkClass
    public String checkClass(int num) {
        if (num <= 127) {
            return "A";
        } else if (num <= 191) {
            return "B";
        } else if (num <= 223) {
            return "C";
        }
        return "no Class";
    }

    // クラスフルサブネット決定
    private int[] classFullSubnet(String className) {
        int cnt = 0;
        switch (className) {
            case "C":
                cnt++;
            case "B":
                cnt++;
            case "A":
                cnt++;
                break;
            default:
                cnt = 3;
        }
        int[] subnetmask = new int[4];
        for (int i = 0; i < cnt; i++) {
            subnetmask[i] = 255;
        }
        return subnetmask;
    }

    // サブネット構築
    // Create Subnet
    public void createSubnet() {
        for (int i = 0; i < this.subnet.length; i++) {
            if ( getSubnet(i) == 0 ) {
                if (i < this.subnet.length - 1 & getRandom() == 0) {
                    this.subnet[i] = subnetbitnumber(rand.nextInt(9));
                    if (this.subnet[i] < 255) {
                        break;
                    }
                } else if (getRandom() == 1) {
                    this.subnet[i] = subnetbitnumber(rand.nextInt(7));
                    if (this.subnet[i] < 255) {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
    }

    // subnet bit
    public int subnetbitnumber(int bit) {
        switch(bit) {
            case 8:
                return 255;
            case 7:
                return 254;
            case 6:
                return 252;
            case 5:
                return 248;
            case 4:
                return 240;
            case 3:
                return 224;
            case 2:
                return 192;
            case 1:
                return 128;
            default:
                return 0;
        }
    }

    // 表示時に規定の値を超えていないかをチェック
    public int limitCheck(int num) {
        if (num >= 255) {
            return num -= 1;
        } else if (num <= 0) {
            return num += 1;
        }
        return num; 
    }

    // 表示メソッド類
    // @printip() :String                   主にＩＰアドレスを表示します
    // @printmask() : String                主にサブネットマスクを表示します
    // @print() :String                     デバッグ用
    // @comple(int[] address) :String       表示フォーマット型で返答
    // @duplex(int[] mask) :int             マスク帳に変更

    public String printip() {
        return comple(this.address);
    }

    public String printmask() {
        return comple(this.subnet);
    }

    public String printmask2() {
        return duplex(this.subnet);
    }

    public String comple(int[] address) {
        return String.format(String.valueOf(address[0]) + "."
        + String.valueOf(address[1]) + "."
        + String.valueOf(address[2]) + "."
        + String.valueOf(address[3]));
    }

    public String duplex(int[] mask) {
        int cnt = 0;
        String stack2, st[];
        for (int num : mask) {
            if (num == 255) {
                cnt += 8;
            } if (num > 0 & num < 255) {
                stack2 = Integer.toBinaryString(num);
                st = stack2.split("");
                for (String flg :  st) {
                    if (flg.equals("1")) {
                        cnt++;
                    }
                }
            }
        }
        return String.valueOf(cnt);
    }

    // 答え表示用のメソッド類
    // @printnet() : String         ネットワークアドレスを表示
    // @printbload() : String       ブロードキャストアドレスを表示
    // @printstnetwork() : String   サブネットワーク内の使用可能なＩＰアドレスの始まり
    // @printednetwork() : String   サブネットワーク内の使用可能なＩＰアドレスの終わり

    public String printnet() {
        for (int i = 0; i < this.network.length; i++) {
            this.network[i] = this.subnet[i] & this.address[i];
        }
        return comple(this.network);
    }

    public String printbload() {
        for (int i = 0; i < this.network.length; i++) {
            if (this.subnet[i] == 0) {
                this.bloadcast[i] = 255;
            } else if (this.subnet[i] < 255) {
                this.bloadcast[i] = Math.abs( (this.subnet[i] & this.address[i]) ^ ( ~this.subnet[i] & this.address[i]));
            } else {
                setBloadcast(this.network[i],i);
            }
        }
        return comple(this.bloadcast);
    }

    public String printstnetwork() {
        return String.format("%s.%s.%s.%s",
            this.network[0],this.network[1],this.network[2],limitCheck(this.network[3]++));
    }

    public String printednetwork() {
        return String.format("%s.%s.%s.%s",
            this.bloadcast[0],this.bloadcast[1],this.bloadcast[2],limitCheck(this.bloadcast[3]--));
    }

    //getter and setter
    // @getter
    public String getPrints() {
        return this.prints;
    }

    public int getNums() {
        return this.nums;
    }

    public int[] getSubnet() {
        return this.subnet;
    }

    // Overload
    public int getSubnet(int index) {
        return this.subnet[index];
    }

    public int[] getAddress() {
        return this.address;
    }

    public int getRandom() {
        return this.rand.nextInt(2);
    }

    // @ setter
    public void setPrints(String prints) {
        this.prints = prints;
    }

    public void setNums(int num) {
        this.nums = num;
    }

    public void setSubnet(int[] num) {
        this.subnet = num;
    }

    public void setBloadcast(int[] num) {
        this.bloadcast = num;
    }

    public void setBloadcast(int num,int index) {
        this.bloadcast[index] = num;
    }

    // propaty options
    public void plzpushkey() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n[Enter]キーを入力することで答えを表示します");
        sc.nextLine();
        sc.close();
    }

} 