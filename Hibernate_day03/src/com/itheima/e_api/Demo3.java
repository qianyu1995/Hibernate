package com.itheima.e_api;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.itheima.domain.Customer;
import com.itheima.utils.HibernateUtils;

public class Demo3 {
	@Test
	//乐观锁
	public void fun1(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		Customer c
		 = (Customer) session.get(Customer.class, 1);
		
		c.setName("tom");
		
		System.out.println(c);
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	@Test
	//悲观锁
	//读锁
	public void fun2(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		Customer c
		 = (Customer) session.get(Customer.class, 1, LockOptions.READ);
		
		System.out.println(c);
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
}
