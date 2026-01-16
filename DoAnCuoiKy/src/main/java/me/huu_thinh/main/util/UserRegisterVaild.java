package me.huu_thinh.main.util;

import java.util.regex.Pattern;

public class UserRegisterVaild {

	public static boolean isPhone(String input) {
		String nPhoneRegex = "(\\+84|0)(3[2-9]|5[2689]|7[06-9]|8[1-9]|9[0-9])([0-9]{7})\\b";
        Pattern nPhonePattern = Pattern.compile(nPhoneRegex);
        return nPhonePattern.matcher(input).find();
	}
}
