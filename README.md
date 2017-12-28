# DiffieHellman
DiffieHellman密钥交换算法的实现

Alice和Bob利用Diffie-Hellman 密钥交换算法交换密钥的过程来说明该算法： 
（0）前提说明，Alice和Bob都知道密钥交换过程中需要用到的素数p和p的一个本原根a（本原根的概念在文末有简单介绍）,这两个值可以由发起通信的那一方选择并发送给通信的另一方。 
（1）Alice选择自己密钥SA，计算出自己的公钥，PA = aSA mod p，然后把PA传给Bob 
（2）Bob选择自己密钥SB，计算出自己的公钥，PB = aSB mod p，然后把PB传给Alice 
（3）Alice根据Bob的公钥、自己的私钥、P和a计算出用于对称加密的加密密钥，K = PBSA mod p 
（4）Bob根据Alice的公钥、自己的私钥、P和a计算出用于对称加密的加密密钥，K = PASB mod p 
最终，Alice和Bob得到了用于对称加密的加密密钥。

参考资料 
https://wenku.baidu.com/view/8e6ba317011ca300a6c390dc.html