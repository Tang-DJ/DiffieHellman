import com.sun.org.apache.xml.internal.security.keys.keyresolver.implementations.PrivateKeyResolver;

import java.util.Scanner;

public class DiffieHellman {

    public static void main(String[] args){
        Data data = new Data();
        DiffieHellman diffieHellman = new DiffieHellman();
        data.setP(diffieHellman.inputQ());

    }

    //输入p
    public int inputQ(){
        Scanner input = new Scanner(System.in);
        int p;
        System.out.println("请输入一个素数q:");
        p = input.nextInt();
        while(isPrime(p)){
            System.out.println("请输入一个素数q:");
            p = input.nextInt();
        }
        return p;
    }

    //判断是否是素数
    public boolean isPrime(int n)
    {
        if(n < 2) return false;
        if(n == 2) return true;
        if(n%2==0) return false;
        for(int i = 3; i*i <= n; i += 2)
            if(n%i == 0) return false;
        return true;
    }


}
