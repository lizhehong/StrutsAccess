package cn.ITHong.action;

import java.util.List;


import cn.ITHong.dao.EntityDao;
import cn.ITHong.dao.impl.EntityDaoFactory;
import cn.ITHong.dao.impl.EntityDaoImpl;
import cn.ITHong.domain.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	private User model = new User();
	public User getModel() {
		return this.model;
	}
	public String getAllUser() throws Exception{
		EntityDao<User> entityDao = EntityDaoFactory.getInstance(EntityDaoImpl.class);
		List<User> uList = entityDao.getAllEntity(User.class);	
		
		ActionContext.getContext().put("uList", uList);
		
		return "userList";
	}
}
