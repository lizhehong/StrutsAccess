package cn.ITHong.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionContext;

import cn.ITHong.domain.Permission;
import cn.ITHong.domain.Role;
import cn.ITHong.domain.User;
import cn.ITHong.util.HibernateUtils;

public class AccessListener extends HibernateUtils implements
		ServletContextListener {
	static{
		url="cn/ITHong/domain/hibernate.cfg.xml";
		new HibernateUtils().init();
	}
	public void contextInitialized(ServletContextEvent sce) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		/**
		 * 三表连查 用户 角色 权限
		 * */
		List<User> uList = session.createQuery("from User u left outer join fetch u.rSet r left outer join fetch r.pSet p").list();
		
		Set<User> uSet = new HashSet<User>(uList);
		
		Map<String, List<Permission>> userPermissionMap = new HashMap<String, List<Permission>>();
		for(User user:uSet){
			//对应得到用户的所有角色
			//因为一个用户有多个角色	一个角色有多个权限	角色对应的权限有所相同
			//1.得到一个用户的 所有角色
			Set<Role> rSet = user.getrSet();
			//2.遍历所有角色 创建一个新的set用于放permission
			Set<Permission> pSet = new HashSet<Permission>();
			for(Role role:rSet){
				pSet.addAll(role.getpSet());
			}
			//3.填充角色权限 权限应该放入set中 因为不需要重复
			userPermissionMap.put(user.getUsername(),new ArrayList<Permission>(pSet) );
			
		}
		transaction.commit();
		sce.getServletContext().setAttribute("permission", userPermissionMap);

	}

	/**
	 * 在tomcat关闭的时候，把application域中的权限值清空
	 * */
	public void contextDestroyed(ServletContextEvent sce) {
		sce.getServletContext().removeAttribute("permission");

	}

}
