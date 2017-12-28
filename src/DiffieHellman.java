
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class DiffieHellman {
    public static void main(String[] args){
        Data data = new Data();
        DiffieHellman diffieHellman = new DiffieHellman();

        diffieHellman.createSafePrimeNumber(2,data);
        diffieHellman.start(data);
        diffieHellman.result(data);
    }



    /**
     * 求DH安全大素数P
     * @param length 目标素数长度
     * **/
    public void createSafePrimeNumber(int length,Data data){

        SecureRandom rnd = new SecureRandom();//强制随机
        boolean temp1,temp2;

        do {
            BigInteger Q = BigInteger.probablePrime(length, rnd);//随机选取素数Q

            BigInteger QQ = createPrimeNumber(Q);
            BigInteger P = BigInteger.valueOf(2).multiply(QQ).add(BigInteger.ONE);//P=2*QQ+1

            //2的p-1次方                                                      //1modP
            temp1 = BigInteger.valueOf(2).pow(P.subtract(BigInteger.ONE).intValue()) == BigInteger.ONE.mod(P);
            //
            temp2 = P.gcd(BigInteger.valueOf(2).pow(P.subtract(BigInteger.ONE).divide(Q).intValue()).subtract(BigInteger.ONE)) == BigInteger.ONE;
            if(temp1&&temp2){
                System.out.println("P="+P);
                data.setP(P);
                data.setA(this.findRootNumber(QQ,P));
                System.out.println("find");
                break;
            }
        }while (true);

    }

    /**
     * 生成大素数q
     * @param Q 随机素数Q
     * **/
    public BigInteger createPrimeNumber(BigInteger Q){
        SecureRandom rnd = new SecureRandom();//强制随机

        BigInteger a = BigInteger.valueOf(2);//基a
        BigInteger i = BigInteger.ONE;//1
        BigInteger two = BigInteger.valueOf(2);
        BigInteger QQ;
        int r = rnd.nextInt(100);//获得一个bound以内的随机数
        BigInteger R = BigInteger.valueOf(r).multiply(two);//随机选取偶数

        do{
            QQ = two.multiply(R).multiply(Q).add(i);//QQ=2*R*Q+1
            R = R.add(two);//R=R+2
        }
        while (!(a.pow(two.multiply(R).multiply(Q).intValue())==i.mod(QQ))&&a.pow(two.multiply(R).intValue()).subtract(i).gcd(QQ)==i);
        System.out.println(QQ);
        return QQ;
    }

    /**
     * 生成本原根
     * @param P 安全大素数
     * @param Q 随机生成的素数
     * **/
    public BigInteger findRootNumber(BigInteger Q,BigInteger P){
        SecureRandom rnd = new SecureRandom();//强制随机
        int l = rnd.nextInt(Q.multiply(BigInteger.valueOf(2)).subtract(BigInteger.ONE).intValue());
        //如果l==q就重新随机
        while(l==Q.intValue()){
            l = rnd.nextInt(Q.multiply(BigInteger.valueOf(2)).subtract(BigInteger.ONE).intValue());
        }
        BigInteger g = BigInteger.valueOf(2).pow(l).mod(P);

        return g;
    }

    /**
     *A输入自己的秘钥Sa
     */
    public int createPrivateKeyA(){
        Scanner input = new Scanner(System.in);
        System.out.println("A用户您好，请输入您的秘钥");
        int PrivateKeyA = input.nextInt();
        input.close();
        return PrivateKeyA;
    }

    /**
     *B输入自己的秘钥Sb
     */
    public int createPrivateKeyB(){
        Scanner input = new Scanner(System.in);
        System.out.println("B用户您好，请输入您的秘钥");
        int PrivateKeyB = input.nextInt();
        input.close();
        return PrivateKeyB;
    }

    public void start(Data data){
        data.setPrivateKeyA(this.createPrivateKeyA());//密钥
        data.setPrivateKeyB(this.createPrivateKeyB());

        data.setPublicKeyA(publicKey(data.getA(),data.getSecretKeyA(),data.getP()));//公钥
        data.setPublicKeyB(publicKey(data.getA(),data.getSecretKeyB(),data.getP()));

    }

    /**
     * 计算公钥
     * @param P 安全大素数P
     * @param A 本原根A
     * @param secret 密钥
     * **/
    public BigInteger publicKey(BigInteger A,int secret,BigInteger P){
        BigInteger publicK = A.pow(secret).mod(P);
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

    public void result(Data data){
        System.out.println("最后的结果：");
        System.out.println("A求出的："+secretKey(data.getPublicKeyA(),data.getPrivateKeyA(),data.getP()));
        System.out.println("B求出的："+secretKey(data.getPublicKeyB(),data.getPrivateKeyB(),data.getP()));
    }


}
