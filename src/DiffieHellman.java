import com.sun.org.apache.xml.internal.security.keys.keyresolver.implementations.PrivateKeyResolver;
import com.sun.org.apache.xml.internal.security.signature.reference.ReferenceNodeSetData;

import javax.swing.*;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class DiffieHellman {

    public static void main(String[] args){
        Data data = new Data();
        DiffieHellman diffieHellman = new DiffieHellman();



        diffieHellman.isPrimitiveRoot(71,2);

        /*diffieHellman.SecretSwapNext();*/
    }

    //初始化
    public void start(Data data){
        int p = inputP();
        int a = inputA(p);
        data.setP(p);
        data.setA(a);


    }

    //输入p
    public int inputP(){
        /*Scanner input = new Scanner(System.in);
        int p;
        System.out.println("请输入一个素数q:");
        p = input.nextInt();
        while(isPrime(p)){
            System.out.println("输入非法，请输入一个素数q:");
            p = input.nextInt();
        }
        System.out.println("q="+p);
        input.close();
        return p;*/

        //随机产生大素数



        return 0;

    }

    //判断是否是素数
    public boolean isPrime(int n)
    {
        if(n < 2) return true;
        if(n == 2) return false;
        if(n%2==0) return true;
        for(int i = 3; i*i <= n; i += 2)
            if(n%i == 0) return true;
        return false;
    }

    //输入a
    public int inputA(int p){
        Scanner input = new Scanner(System.in);
        int a=1;
        System.out.println("请输入素数q的一个本原根a：");
        while(isPrimitiveRoot(p,a)){
            System.out.println("输入非法，请输入素数q的一个本原根a：");
            a = input.nextInt();
        }
        System.out.println("a="+a);
        input.close();
        return a;
    }

    //判断是否是本原根
    public boolean isPrimitiveRoot(int p,int a){
        double[] result = new double[a];
        for(int i=1;i<a-1;i++){
            result[i]=Math.pow(a,i)%p;
            for(int k=1;k<i;k++){
                if(result[i]==result[k]) {
                    return false;
                }
            }
        }
        return true;
    }

    /*public void SecretSwapNext(){
        try{
            int bitLength = 512;
            SecureRandom rnd = new SecureRandom();
            System.out.println("\nBitLength:"+bitLength);
            BigInteger y = BigInteger.probablePrime(bitLength, rnd);
            BigInteger p = BigInteger.ONE;
            BigInteger g = BigInteger.ONE;
            for(long j=0;j<200000;j++){
                    BigInteger q = BigInteger.probablePrime(bitLength,new SecureRandom());
                    System.out.println("Q="+q);
                    BigInteger pp = BigInteger.valueOf(2).multiply(q).add(BigInteger.valueOf(1));
                    System.out.println("p="+p);
                    if(BigInteger.valueOf(2).multiply(q).add(BigInteger.valueOf(1)).isProbablePrime(1)){
                        System.out.println("\n第"+j+"次找到"+"p="+pp);
                        p=pp;
                        for(int i=2;i<5000;i++) {
                            BigInteger gg = new BigInteger(String.valueOf(i));
                            if (gg.modPow(BigInteger.valueOf(2), pp).intValue() == 1 || gg.modPow(q, pp).intValue() == 1)
                            {
                                continue;
                            }
                            else{
                                g = gg;
                                break;
                            }
                        }

                    }
                    else {
                        continue;
                    }
            }
        }
        catch (Exception a){
            System.out.println(a);
        }
    }*/


}
