package com.itheima.b_lazy;

import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import com.itheima.domain.Customer;
import com.itheima.domain.Order;
import com.itheima.utils.HibernateUtils;
//多对一检索策略
public class Demo3 {
	@Test
	//fetch: select
	//lazy: false
	public void fun1(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		Order o = (Order) session.get(Order.class, 2);
		
		System.out.println(o.getCustomer().getName());
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	
	@Test
	//fetch: select
	//lazy: proxy
	// Customer lazy:false
	public void fun2(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		Order o = (Order) session.get(Order.class, 2);
		
		System.out.println(o.getCustomer().getName());
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	@Test
	//fetch: select
	//lazy: proxy
	// Customer lazy:true
	public void fun3(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		Order o = (Order) session.get(Order.class, 2);
		
		System.out.println(o.getCustomer().getName());
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	@Test
	//fetch: join
	//lazy: proxy|false
	public void fun4(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		Order o = (Order) session.get(Order.class, 2);
		
		System.out.println(o.getCustomer().getName());
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
}
