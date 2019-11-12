package com.itheima.b_cache;

import org.hibernate.Session;
import org.junit.Test;

import com.itheima.domain.User;
import com.itheima.utils.HibernateUtils;
//session缓存
public class Demo1 {
	@Test
	//证明session缓存的存在
	public void fun1(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		User u1 = (User) session.get(User.class, 1);// 发送select语句,从数据库取出记录.并封装成对象
												   // 持久化状态对象=> 存到缓存中
		
		User u2 = (User) session.get(User.class, 1);//再次查询时,会从缓存中查找,不会发送select
		
		User u3 = (User) session.get(User.class, 1);//再次查询时,会从缓存中查找,不会发送select
		
		System.out.println(u1==u2);//true
		System.out.println(u1==u3);//true
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	@Test
	//session缓存中的快照
	public void fun2(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		User u1 = (User) session.get(User.class, 1);// 发送select语句,从数据库取出记录.并封装成对象
		
		session.update(u1);
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	
	@Test
	//session缓存中的快照
	public void fun3(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		User u1 = new User();
		u1.setId(1);
		u1.setName("jerry");
		u1.setPassword("1234");
		
		session.update(u1);
	
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	
	@Test
	//感受一级缓存效率的提高
	public void fun4(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		User u1 = (User) session.get(User.class, 1);
		
		u1.setName("tom");
		session.update(u1);
		u1.setName("jack");
		session.update(u1);
		u1.setName("rose");
		session.update(u1);
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	// 持久化状态: 本质就是存在缓存中的对象,就是持久化状态.
	
	
	
}
