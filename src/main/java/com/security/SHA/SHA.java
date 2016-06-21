package main.java.com.security.SHA;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA224Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class SHA {

	private static String src = "SHA archy security";
	
	public static void main(String[] args) {
		jdkSHA1();
		bcSHA1();
		bcSHA224();
		bcSHA224_2();
		ccSHA1();
	}

	
	public static void jdkSHA1() {
		try {
			// SHA-1 的算法名称就是 SHA
			MessageDigest md = MessageDigest.getInstance("SHA");
			md.update(src.getBytes());
			System.out.println("jdk SHA-1: " + Hex.encodeHexString(md.digest()));
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void bcSHA1() {
		Digest digest = new SHA1Digest();
		digest.update(src.getBytes(), 0, src.getBytes().length);
		byte[] sha1Bytes = new byte[digest.getDigestSize()];
		// 第一个参数是输出到哪里，第二个是偏移量
		digest.doFinal(sha1Bytes, 0);
		System.out.println("bc SHA-1: " + 
		org.bouncycastle.util.encoders.Hex.toHexString(sha1Bytes));
	}
	
	public static void bcSHA224() {
		Digest digest = new SHA224Digest();
		digest.update(src.getBytes(), 0, src.getBytes().length);
		// 定义一个数组接受摘要的结果
		byte[] sha224Bytes = new byte[digest.getDigestSize()];
		// 把摘要的结果输出到数组中
		digest.doFinal(sha224Bytes, 0);
		System.out.println("bc SHA-224: " +
		org.bouncycastle.util.encoders.Hex.toHexString(sha224Bytes));
	}
	
	// 另外的方式实现 SHA-224 
	public static void bcSHA224_2() {
		Security.addProvider(new BouncyCastleProvider());
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-224");
			md.update(src.getBytes());
			System.out.println("jdk & bc SHA224: " + Hex.encodeHexString(md.digest()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	
	// CC 的实现
	public static void ccSHA1() {
		System.out.println("cc SHA1: " + DigestUtils.sha1Hex(src.getBytes()));
		System.out.println(DigestUtils.sha1Hex(src));
	}
	
}


