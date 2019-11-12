package com.itheima.c_hbm;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

import com.itheima.a_hello.User;
import com.itheima.utils.HibernateUtils;
public class session_test {
	@Test
	//演示dynamic-insert 
	public void fun1(){
		org.hibernate.Session session = HibernateUtils.openSession();
		Transaction ts = session.beginTransaction();
		
		User u = new User();
		u.setName("zhangsan");
		//调用Session的save方法保存对象到数据库中
		session.save(u);
		
		ts.commit();
		//关闭资源
		session.close();
	}
	@Test
	//演示dynamic-update
	public void fun2(){
		org.hibernate.Session session = HibernateUtils.openSession();
		
		Transaction ts = session.beginTransaction();
		
		User u = (User) session.get(User.class, 1);
		
		u.setName("jerry");
		
		session.update(u);
		
		ts.commit();
		//关闭资源
		session.close();
	}
}
