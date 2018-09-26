package com.gawain.daoapi;

import java.util.List;
import com.gawain.entities.Users;

public interface UsersDao {
	public boolean saveOrUpdate(Users users);
	public List<Users> list();
	public boolean delete(Users users);
}