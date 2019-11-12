package com.itheima.b_lazy;

import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import com.itheima.domain.Customer;
import com.itheima.domain.Order;
import com.itheima.utils.HibernateUtils;

public class Demo2 {
	@Test
	//关联级别懒加载
	//默认: 与我关联的数据,在使用时才会加载.
	public void fun1(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		Customer c = (Customer) session.get(Customer.class, 1);
		

		for(Order o : c.getOrders()){
			System.out.println(o.getName());
		}
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	
	@Test
	//关联级别懒加载
	//lazy: false
	public void fun2(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		Customer c = (Customer) session.get(Customer.class, 1);
		

		for(Order o : c.getOrders()){
			System.out.println(o.getName());
		}
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	@Test
	//关联级别懒加载
	//lazy: false/true
	//fetch:join
	public void fun3(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		Customer c = (Customer) session.get(Customer.class, 1);
		

		for(Order o : c.getOrders()){
			System.out.println(o.getName());
		}
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	@Test
	//关联级别懒加载
	//lazy:true
	//fetch:subselect
	public void fun4(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
			List<Customer> list = session.createQuery("from Customer").list();
			
			for(Customer c:list){
				System.out.println(c.getName()+"下单数量:"+c.getOrders().size());
			}
			
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	@Test
	//关联级别懒加载
	//lazy:false
	//fetch:subselect
	public void fun5(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
			List<Customer> list = session.createQuery("from Customer").list();
			
			for(Customer c:list){
				System.out.println(c.getName()+"下单数量:"+c.getOrders().size());
			}
			
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	@Test
	//关联级别懒加载
	//lazy:extra
	//fetch:select
	public void fun6(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		//查询Customer
		Customer c = (Customer) session.get(Customer.class, 1);
		//查询Customer下订单 数量
		System.out.println(c.getOrders().size());

		//真正使用订单中的数据
		for(Order o : c.getOrders()){
			System.out.println(o.getName());
		}
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	@SuppressWarnings("unchecked")
	@Test
	//关联级别懒加载
	//lazy:extra
	//fetch:subselect
	public void fun7(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
			List<Customer> list = session.createQuery("from Customer").list();
			
			for(Customer c:list){
				System.out.println(c.getName()+"下单数量:"+c.getOrders().size());
			}
			
			for(Customer c:list){
				for(Order o : c.getOrders()){
					System.out.println(c.getName()+"下单名称:"+o.getName());
					
				}
			}
			
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
}
