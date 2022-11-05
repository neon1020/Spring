package com.itwillbs.mvc_board.cipher;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MyMessageDigest {
	private MessageDigest md;
	private String hashAlgorithm = "";
	
	public MyMessageDigest(String hashAlgorithm) {
		this.hashAlgorithm = hashAlgorithm;
	}
	
	public String hashing(String strSourceData) {
		
		String strHashedData = "";
		
		try {
			MessageDigest md = MessageDigest.getInstance(hashAlgorithm);
			// System.out.println(md.getAlgorithm() + " : 해당 알고리즘은 존재합니다.");
			
			byte[] byteData = strSourceData.getBytes();
			// System.out.println(Arrays.toString(byteData));
			
			md.update(byteData);
			
			byte[] digestResult = md.digest();
			// System.out.println(Arrays.toString(digestResult));
			
			for(int i = 0; i < digestResult.length; i++) {
				strHashedData += Integer.toHexString(digestResult[i] & 0xFF).toUpperCase();
			}
			
			// System.out.println("암호문 : " + strHashedData);
			
		} catch (NoSuchAlgorithmException e) {
			// System.out.println("해당 암호화 알고리즘이 존재하지 않습니다.");
			e.printStackTrace();
		}
		
		return strHashedData;
	}
	
	// 두 패스워드 비교하는 matches() 메소드 정의
	public boolean matches(String encryptedStr, String plainStr) {
		
		return encryptedStr.equals(hashing(plainStr));
		
	}
}
