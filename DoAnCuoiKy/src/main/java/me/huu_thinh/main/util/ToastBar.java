package me.huu_thinh.main.util;

import javax.servlet.http.HttpSession;

public class ToastBar {

	
	public static void showToast(HttpSession session,String text) {
		session.setAttribute("toastSuccess", text);
	}
}
