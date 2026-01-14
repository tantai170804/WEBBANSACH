package me.huu_thinh.main.util;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class PasswordEncoding {

	
	public static String encodingPassword(String password) {
		Encoder encoder = Base64.getEncoder();
		String result = encoder.encodeToString(password.getBytes());
		return result;
	}
	
	public static String decodingPassword(String password_hash) {
		Decoder decoder = Base64.getDecoder();
		byte[] result = decoder.decode(password_hash);
		return new String(result);
	}
	
}
