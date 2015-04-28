package cn.ITHong.action;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;

import org.apache.struts2.ServletActionContext;

import cn.ITHong.dao.EntityDao;
import cn.ITHong.dao.impl.EntityDaoFactory;
import cn.ITHong.dao.impl.EntityDaoImpl;
import cn.ITHong.domain.Permission;
import cn.ITHong.domain.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport {
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String login() throws Exception {
		@SuppressWarnings("unchecked")
		EntityDao<User> entityDao = EntityDaoFactory
				.getInstance(EntityDaoImpl.class);
		User user = entityDao.findEntity(User.class, getUsername(), getPassword());
		if(user!=null){
			@SuppressWarnings("unchecked")
			Map<String, List<Permission>> userPermissionMap = (Map<String, List<Permission>>) ServletActionContext
					.getServletContext().getAttribute("permission");
			List<Permission> pSet = userPermissionMap.get(user.getUsername());//该用户具有的权限
			ServletActionContext.getRequest().getSession().setAttribute("permission_session", pSet);
			return "index";
		}else{
			return "input";
			
		}
	}
}
