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
public class Demo3 {
	@Test
	//inverse:false
	//cascade: delete-orphan 孤儿删除 => 当没有任何外键引用Order时,order 会被删除
	public void fun1(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
			Customer c = (Customer) session.get(Customer.class, 9);
			Iterator<Order> it = c.getOrders().iterator();
			//注意: 删除Customer下的订单时,不能使用 c.setOrders(null); c.setOrders(new HashSet());
			while(it.hasNext()){ // 遍历Customer下的订单,并将订单删除 => 维护关系
				it.next();
				it.remove();
			}
			//------------------------------------------------
			session.getTransaction().commit();
			session.close(); // 游离状态
	}
	
	
}