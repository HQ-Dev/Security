package main.java.com.security.base;


public class Base64 {

	// 将要加密的原始字符串
	private static String src = "security jdkBase 64";
	
	
	public static void main(String[] args) {
		System.out.println("commonsCodesBase64():");
		commonsCodesBase64();
		System.out.println("bouncyCastleBase64():");
		bouncyCastleBase64();
		jdkBase64();
	}
	
	// Jdk 默认方法加密
	public static void jdkBase64() {
		byte[] encodeBytes = java.util.Base64.getEncoder().encode(src.getBytes());
		System.out.println("jdk base64 encode: " + new String(encodeBytes));
		
		byte[] decodeBytes = java.util.Base64.getDecoder().decode(encodeBytes);
		System.out.println("jdk base64 decode: " + new String(decodeBytes));
	}
	
	// 使用 Commons Codec 
	public static void commonsCodesBase64() {
		byte[] encodeBytes = org.apache.commons.codec.binary.Base64.encodeBase64(src.getBytes());
		System.out.println("encode:" + new String(encodeBytes));
		
		byte[] decodeBytes = org.apache.commons.codec.binary.Base64.decodeBase64(encodeBytes);
		System.out.println("decode:" + new String(decodeBytes));
	}
	
	// 使用 Bouncy Castle
	public static void bouncyCastleBase64() {
		byte[] encodeBytes = org.bouncycastle.util.encoders.Base64.encode(src.getBytes());
		System.out.println("encode:" + new String(encodeBytes));
		
		byte[] decodeBytes  = org.bouncycastle.util.encoders.Base64.decode(encodeBytes);
		System.out.println("decode:" + new String(decodeBytes));
	}

}
