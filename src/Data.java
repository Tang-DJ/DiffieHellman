public class Data {


    private int p;//素数p
    private int a;//本原根a
    private String PrivateKeyA;//A的密钥
    private String PrivateKeyB;//B的密钥
    private String PublicKeyA;//A的公钥
    private String PublicKeyB;//B的公钥
    private String SecretKeyA;//A的加密密钥
    private String SecretKeyB;//A的加密密钥

    public void setP(int p){
        this.p = p;
    }

    public int getP(){
        return this.p;
    }

    public void setA(int a){
        this.a = a;
    }

    public int getA(){
        return this.a;
    }

    public String getPrivateKeyA(){
        return this.PrivateKeyA;
    }

    public void setPrivateKeyA(String PrivateKeyA){
        this.PrivateKeyA = PrivateKeyA;
    }

    public String getPrivateKeyB(){
        return this.PrivateKeyB;
    }

    public void setPrivateKeyB(String PrivateKeyB){
        this.PrivateKeyB = PrivateKeyB;
    }

    public String getPublicKeyA(){
        return this.PublicKeyA;
    }

    public void setPublicKeyA(String PublicKeyA){
        this.PublicKeyA = PublicKeyA;
    }

    public String getPublicKeyB(){
        return this.PublicKeyB;
    }

    public void setPublicKeyB(String PublicKeyB){
        this.PublicKeyB = PublicKeyB;
    }

    public String getSecretKeyA(){
        return this.SecretKeyA;
    }

    public void setSecretKeyA(String SecretKeyA){
        this.SecretKeyA = SecretKeyA;
    }

    public String getSecretKeyB(){
        return this.SecretKeyB;
    }

    public void setSecretKeyB(String SecretKeyB){
        this.SecretKeyB = SecretKeyB;
    }
}
