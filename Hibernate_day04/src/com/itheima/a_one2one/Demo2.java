package com.itheima.a_one2one;

import java.util.Iterator;

import org.hibernate.Session;
import org.junit.Test;

import com.itheima.domain.Address;
import com.itheima.domain.Company;
import com.itheima.domain.Customer;
import com.itheima.domain.Order;
import com.itheima.utils.HibernateUtils;
//演示二级缓存操作
public class Demo2 {
	@SuppressWarnings("unused")
	@Test
	//演示:集合缓存
	public void fun1(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		
		Customer customer1 = (Customer) session.get(Customer.class, 1);
		
		for(Order o :customer1.getOrders()){
			System.out.println(o.getName());
		}
	
		session.clear();
		
		Customer customer2 = (Customer) session.get(Customer.class, 1);
		
		Iterator<Order> it = customer2.getOrders().iterator();
		
		while(it.hasNext()){
			Order o = it.next();
			System.out.println(o.getName());
		}
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	@Test
	//查询一对一数据
	//Hibernate查询一对一: 会使用表关联查询
	public void fun2(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		
		Company company = (Company) session.get(Company.class, 1);
		
		System.out.println(company);
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
}
