package com.itheima.e_api;

import org.hibernate.Session;
import org.junit.Test;

import com.itheima.domain.Customer;
import com.itheima.utils.HibernateUtils;

public class Demo {
	@Test
	//验证c3p0连接池
	public void fun1(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
			Customer c = new Customer();
			c.setName("tom");
			session.save(c);
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
}
