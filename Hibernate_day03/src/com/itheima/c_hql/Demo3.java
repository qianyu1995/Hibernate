package com.itheima.c_hql;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.itheima.domain.Customer;
import com.itheima.utils.HibernateUtils;
//HQL详解2-表连接

public class Demo3 {
	@Test
	//找到局部配置的HQL语句
	public void fun1(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		//从com.itheima.domain.Customer 的 hbm文件中找
		Query query = session.getNamedQuery("com.itheima.domain.Customer.bcd");
		
		
		List<Object> list = query.list();
		
		System.out.println(list);
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	@Test
	//找到全局配置的HQL语句
	public void fun2(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		//从com.itheima.domain.Customer 的 hbm文件中找
		Query query = session.getNamedQuery("abc");
		
		
		List<Object> list = query.list();
		
		System.out.println(list);
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
}
