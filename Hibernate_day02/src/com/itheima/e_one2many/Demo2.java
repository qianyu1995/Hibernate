package com.itheima.e_one2many;

import java.util.Set;

import org.hibernate.Session;
import org.junit.Test;

import com.itheima.domain.Customer;
import com.itheima.domain.Order;
import com.itheima.utils.HibernateUtils;
//测试 一对多关系
public class Demo2 {
	@Test
	//增
	//我们希望在保存Customer时,自动将未保存的Orders当中的Order保存
	//cascade: save-update
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
		
		
		/*
		o1.setCustomer(c);//维护关系
		o2.setCustomer(c);//维护关系
		 */		
		
		session.save(c);//保存对象
		//session.save(o1);//保存对象
		//session.save(o2);//保存对象
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	@Test
	//增
	//我们希望在保存Customer时,自动将未保存的Orders当中的Order保存
	//cascade: save-update
	public void fun2(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		
		Customer c = (Customer) session.get(Customer.class, 8);//1条 select
		
		for(Order o :c.getOrders()){ // 1条 select
			o.setName("哇哈哈"); // 修改订单
		}
		
		//------------------------------------------------
		session.getTransaction().commit();//因为设置级联修改,自动将订单的修改保存到数据
										  //update语句
		session.close(); // 游离状态
	}
	
	@Test
	//cascade: delete
	//删除Customer时 ,会将Customer下的订单一并删除
	//inverse : false   6条sql语句   
	//inverse : true    5条sql语句 比上面少一条维护外键
			
	public void fun3(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		
		Customer c = (Customer) session.get(Customer.class, 7);//1条 select
		
		
		session.delete(c);//删除Customer
						 // 删除两个Order
		
		//------------------------------------------------
		session.getTransaction().commit();
										  
		session.close(); // 游离状态
	}
	
	@Test
	//cascade: delete
	//操作的两方cascade值都为delete
	//需要注意: 千万不要在两方都配置 级联删除. 删除任何一方,会导致整个关系链对象全部删除.
	public void fun4(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		Order o  = (Order) session.get(Order.class, 9);//select
		
		session.delete(o);//delete删除当前order
		
							//找到所有关联的Customer删除 select
							// delete Customer
							// Customer配置了级联删除=> select 找下面的order
							// 删除所有Order
							//删除Customer
		
		//------------------------------------------------
		session.getTransaction().commit();
										  
		session.close(); // 游离状态
	}
}