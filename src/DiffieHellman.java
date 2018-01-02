

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class DiffieHellman {
    public static void main(String[] args){
        Data data = new Data();
        DiffieHellman diffieHellman = new DiffieHellman();

        System.out.println("欢迎使用DiffieHellman算法系统！");
        diffieHellman.menu(data);
    }

    /**
     * 菜单选择
     * **/
    public void menu(Data data){
        Scanner input = new Scanner(System.in);
        int chose = 0;
        while(chose!=3){
            System.out.println("进入菜单，请输入您的选择：(1~3)");
            System.out.println("1.自行输入素数以及素数的本原根");
            System.out.println("2.随机获得素数以及素数的本原根");
            System.out.println("3.退出系统");

            chose = input.nextInt();
            switch (chose){
                case 1:
                    this.writePandA(data);
                    this.start(data);
                    break;
                case 2:
                    this.start(data);
                    break;
                case 3:
                    System.out.println("退出系统成功！");
                    break;
                default:
                    System.out.println("输入不合法，请重新输入");
                    break;
            }
        }
        input.close();

    }

    /**
     * 输入安全大素数P,A
     * **/
    public void writePandA(Data data){
        Scanner input = new Scanner(System.in);
        int privateNum;//素数
        int rootNum;//本原根
        System.out.print("请输入一个素数:");
        privateNum = input.nextInt();
        while(!isPrime(privateNum)){
            System.out.print("该数不是素数，请输入一个素数:");
            privateNum = input.nextInt();
        }
        System.out.print("输入成功！\n请输入素数"+privateNum+"的一个本原根：");
        rootNum = input.nextInt();
        while (!isRootNumber(BigInteger.valueOf(privateNum),BigInteger.valueOf(rootNum))){
            System.out.print("该数不是素数"+privateNum+"的一个本原根!请输入该素数的一个本原根：");
            rootNum = input.nextInt();
        }
        System.out.println("输入成功！\n素数为"+privateNum+",该素数的本原根为"+rootNum);
        data.setP(BigInteger.valueOf(privateNum));
        data.setA(BigInteger.valueOf(rootNum));

    }

    /**
     * 判断是否是素数
     * @param P 输入素数
     * **/
    public boolean isPrime(int P){
        if(P < 2)
            return false;
        if(P == 2)
            return true;
        if(P%2==0)
            return false;
        for(int i = 3; i*i <= P; i += 2)
            if(P%i == 0)
                return false;
        return true;
    }

    /**
     * 判断是否是本原根
     * @param P 安全大素数
     * @param A 本原根
     * **/
        public boolean isRootNumber(BigInteger A,BigInteger P){
            BigInteger[] temp =new BigInteger[P.intValue()];
            for(int i=0;i<P.intValue()-1;i++){
                temp[i]= A.pow(i+1).mod(P);
                for(int k=0;k<i;k++){
                    if(temp[i].equals(temp[k])){
                        return false;
                    }
                }
        }
        return true;
    }

    /**
     * 随机获得一个素数以及其一个本原根
     * **/
    public void getPandA(){
        SecureRandom rd = new SecureRandom();

    }

    /**
     *AB输入自己的秘钥Sa
     */
    public void createPrivateKeyAB(Data data){
        Scanner input = new Scanner(System.in);
        System.out.println("A用户您好，请输入您的秘钥");
        int PrivateKeyA = input.nextInt();
        System.out.println("您的秘钥为："+PrivateKeyA);

        System.out.println("B用户您好，请输入您的秘钥");
        int PrivateKeyB = input.nextInt();
        System.out.println("您的秘钥为："+PrivateKeyB);

        data.setPrivateKeyA(PrivateKeyA);
        data.setPrivateKeyB(PrivateKeyB);

    }

    /**
     * 操作函数
     * **/
    public void start(Data data){
        this.createPrivateKeyAB(data);//密钥

        data.setPublicKeyA(publicKey(data.getA(),data.getPrivateKeyA(),data.getP()));//公钥
        System.out.println("A公钥为"+data.getPublicKeyA());
        data.setPublicKeyB(publicKey(data.getA(),data.getPrivateKeyB(),data.getP()));
        System.out.println("B公钥为"+data.getPublicKeyB());

        //得到结果
        System.out.println("A用户的秘钥："+this.secretKey(data.getPublicKeyA(),data.getSecretKeyA(),data.getP()));
        System.out.println("B用户的秘钥："+this.secretKey(data.getPublicKeyB(),data.getSecretKeyB(),data.getP()));
    }

    /**
     * 计算公钥
     * @param P 安全大素数P
     * @param A 本原根A
     * @param secret 密钥
     * **/
    public BigInteger publicKey(BigInteger A,int secret,BigInteger P){
        BigInteger publicK = A.pow(secret).mod(P);
       /* System.out.println("A="+A+" p="+secret+" P="+P+" re="+publicK);*/
        return publicK;
    }

    /**
     * 计算加密密钥
     * @param publicK 公钥
     * @param secret 密钥
     * @param P 安全大素数
     * **/
    public BigInteger secretKey(BigInteger publicK,int secret,BigInteger P){
        BigInteger K = publicK.pow(secret).mod(P);
        return K;
    }

}
