package me.huu_thinh.main.service;

import me.huu_thinh.main.dao.UserDAO;
import me.huu_thinh.main.model.User;

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

	}


