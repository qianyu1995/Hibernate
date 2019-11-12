package com.itheima.a_one2one;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.itheima.domain.Customer;
import com.itheima.utils.HibernateUtils;
//演示二级缓存操作
public class Demo4 {
	@Test
	//演示:时间戳
	public void fun1(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
			
			Customer customer1 = (Customer) session.get(Customer.class, 1);
			
			session.createQuery("update Customer set name=:name where id = :id ")
				.setString("name", "rose").setInteger("id", 1).executeUpdate();
		 
			session.clear();
		
			Customer customer2 = (Customer) session.get(Customer.class, 1);
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
}
