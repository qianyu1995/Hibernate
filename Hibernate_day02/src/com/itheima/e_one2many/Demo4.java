package com.itheima.e_one2many;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.junit.Test;

import com.itheima.domain.Customer;
import com.itheima.domain.Order;
import com.itheima.utils.HibernateUtils;
//测试 一对多关系
public class Demo4 {
	@Test
	//cascade: all-delete-orphan => 相当于配置 save-update,delete,delete-orphan
	public void fun1(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		Customer c = new Customer();
		c.setName("tom");
		
		Order o1 = new Order();
		o1.setName("肥皂");
		
		Order o2 = new Order();
		o2.setName("蜡烛");
		
		c.getOrders().add(o1);//维护关系
		c.getOrders().add(o2); //维护关系
		
		session.save(c);
		//------------------------------------------------
			session.getTransaction().commit();
			session.close(); // 游离状态
	}
	@Test
	//cascade: all-delete-orphan => 相当于配置 save-update,delete,delete-orphan
	public void fun2(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		Customer c = (Customer) session.get(Customer.class, 10);
		session.delete(c);
		//------------------------------------------------
			session.getTransaction().commit();
			session.close(); // 游离状态
	}
	
	
	@Test
	//cascade: all-delete-orphan => 相当于配置 save-update,delete,delete-orphan
	public void fun3(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		Customer c = (Customer) session.get(Customer.class, 12);

		Iterator<Order> it = c.getOrders().iterator();
		
		while(it.hasNext()){ // 遍历Customer下的订单,并将订单删除 => 维护关系
			it.next();
			it.remove();
		}
		
		//------------------------------------------------
			session.getTransaction().commit();
			session.close(); // 游离状态
	}
}