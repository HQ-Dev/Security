package main.java.com.security.md;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD2Digest;
import org.bouncycastle.crypto.digests.MD4Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;


/*
 * CC 可以简化操作
 * BC 提供了MD4的算法实现
 */

public class MD {

	private static String src = "archy security md";
	
	public static void main(String[] args) {
		jdkMD5();
		jdkMD2();
		jdkMD4();
		bcMD4();
		bcMD5();
		bcMD2();
		ccMD5();
		ccMD2();
	}
	
	
	public static void jdkMD5() {
		// 得到一个MD5算法的加密对象
		try {
			MessageDigest md =  MessageDigest.getInstance("MD5");
			byte[] md5Bytes = md.digest(src.getBytes());

			// 需要转成16进制,可以借助第三方的包
			System.out.println("JDK MD5: " + Hex.encodeHexString(md5Bytes));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	// JDK没有提供md4的实现
	public static void jdkMD2() {
		try {
			MessageDigest md =  MessageDigest.getInstance("MD2");
			byte[] md2Bytes = md.digest(src.getBytes());

			// 需要转成16进制,可以借助第三方的包
			System.out.println("JDK MD2: " + Hex.encodeHexString(md2Bytes));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	// 看如何使用JDK MD4 算法
	public static void jdkMD4() {
		Security.addProvider(new BouncyCastleProvider());
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD4");
			byte[] md4Bytes = md.digest(src.getBytes());
			System.out.println("jdk & bc md4 :" + Hex.encodeHexString(md4Bytes));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// BC 提供的MD4 算法
	public static void bcMD4() {
		Digest digest = new MD4Digest();
		digest.update(src.getBytes(), 0, src.getBytes().length);
		byte[] md4Bytes = new byte[digest.getDigestSize()];
		digest.doFinal(md4Bytes, 0);
		System.out.println("BC MD4: " + org.bouncycastle.util.encoders.Hex.toHexString(md4Bytes));
		
	}
	
	// BC MD5 实现
	public static void bcMD5() {
		Digest digest = new MD5Digest();
		digest.update(src.getBytes(), 0, src.getBytes().length);
		byte[] md5Bytes = new byte[digest.getDigestSize()];
		digest.doFinal(md5Bytes, 0);
		System.out.println("BC MD5: " + 
		org.bouncycastle.util.encoders.Hex.toHexString(md5Bytes));
	}
	
	public static void bcMD2() {
		Digest digest = new MD2Digest();
		digest.update(src.getBytes(), 0, src.getBytes().length);
		byte[] md2Bytes = new byte[digest.getDigestSize()];
		digest.doFinal(md2Bytes, 0);
		System.out.println("bc md2 :" + org.bouncycastle.util.encoders.Hex.toHexString(md2Bytes));
	}
	
	// CC MD5
	public static void ccMD5() {
		String md5String = DigestUtils.md5Hex(src.getBytes());
		System.out.println("ccMD5:" + md5String);
	}
	
	public static void ccMD2() {
		String md2String = DigestUtils.md2Hex(src.getBytes());
		System.out.println("ccMD2:" + md2String);
	}
}
