package me.huu_thinh.main.service;

import me.huu_thinh.main.dao.UserDAO;
import me.huu_thinh.main.model.User;
import me.huu_thinh.main.util.PasswordEncoding;

public class UserService {

	    public boolean delete(int id, User currentUser) {
	        if (currentUser.getUserId() == id) return false;
	        return UserDAO.delete(id);
	    }

	    public boolean update(User u) {
	        if (u.getUserId() <= 0) return false;
	        if (u.getRole() == null || u.getRole().isBlank()) return false;
	        return UserDAO.update(u);
	    }
	    
	    public User getUserFromName(String name) {
	    	return UserDAO.findByUserName(name);
	    }
	    
	    public int updatePassword(int user_id,String password, String newpass, String confirmpass) {
	    	User user = UserDAO.findById(user_id);
	    	if(user == null) return 0;
	    	String password_hash = PasswordEncoding.encodingPassword(password);
	    	if(!user.getPassword().equals(password_hash)) return 1;
	    	if(!newpass.equals(confirmpass)) return 2;
	    	if(password.equals(newpass)) return 3;
	    	UserDAO.updatePass(user, PasswordEncoding.encodingPassword(newpass));
	    	return 4;
	    }

	}


