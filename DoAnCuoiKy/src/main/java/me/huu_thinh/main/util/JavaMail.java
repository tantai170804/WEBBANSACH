package me.huu_thinh.main.util;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

public class JavaMail {
	
	private static Context context;

    static {
        try {
            context = (Context) new InitialContext()
                    .lookup("java:comp/env");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String get(String name) {
        try {
            return (String) context.lookup(name);
        } catch (Exception e) {
            throw new RuntimeException("Cannot load JNDI: " + name);
        }
    }
    
	public static Properties getMailProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        return props;
    }

    public static String getUsername() {
        return get("MAIL_USERNAME");
    }

    public static String getPassword() {
        return get("MAIL_PASSWORD"); // Gmail App Password
    }
}
