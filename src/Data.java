import java.math.BigInteger;

public class Data {


    private BigInteger p;//素数p
    private BigInteger a;//本原根a
    private int PrivateKeyA;//A的密钥
    private int PrivateKeyB;//B的密钥
    private BigInteger PublicKeyA;//A的公钥
    private BigInteger PublicKeyB;//B的公钥
    private int SecretKeyA;//A的加密密钥
    private int SecretKeyB;//A的加密密钥

    public void setP(BigInteger p){
        this.p = p;
    }

    public BigInteger getP(){
        return this.p;
    }

    public void setA(BigInteger a){
        this.a = a;
    }

    public BigInteger getA(){
        return this.a;
    }

    public int getPrivateKeyA(){
        return this.PrivateKeyA;
    }

    public void setPrivateKeyA(int PrivateKeyA){
        this.PrivateKeyA = PrivateKeyA;
    }

    public int getPrivateKeyB(){
        return this.PrivateKeyB;
    }

    public void setPrivateKeyB(int PrivateKeyB){
        this.PrivateKeyB = PrivateKeyB;
    }

    public BigInteger getPublicKeyA(){
        return this.PublicKeyA;
    }

    public void setPublicKeyA(BigInteger PublicKeyA){
        this.PublicKeyA = PublicKeyA;
    }

    public BigInteger getPublicKeyB(){
        return this.PublicKeyB;
    }

    public void setPublicKeyB(BigInteger PublicKeyB){
        this.PublicKeyB = PublicKeyB;
    }

    public int getSecretKeyA(){
        return this.SecretKeyA;
    }

    public void setSecretKeyA(int SecretKeyA){
        this.SecretKeyA = SecretKeyA;
    }

    public int getSecretKeyB(){
        return this.SecretKeyB;
    }

    public void setSecretKeyB(int SecretKeyB){
        this.SecretKeyB = SecretKeyB;
    }
}
