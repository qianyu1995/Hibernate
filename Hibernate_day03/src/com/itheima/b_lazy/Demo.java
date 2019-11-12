package com.itheima.b_lazy;

import org.hibernate.Session;
import org.junit.Test;

import com.itheima.domain.Course;
import com.itheima.domain.Customer;
import com.itheima.domain.Student;
import com.itheima.utils.HibernateUtils;

public class Demo {
	@Test
	//类级别懒加载
	//load方法
	// class lazy属性
	// 默认值: true load获得时,会返回代理对象,不查询数据库.使用时才查询
	public void fun1(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		Customer c = (Customer) session.load(Customer.class, 1);
		
		System.out.println(c.getName());
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	@Test
	//类级别懒加载
	//load方法
	// class lazy属性
	// lazy: false  load方法执行就会发送sql语句.与get方法一致.
	public void fun2(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		Customer c = (Customer) session.load(Customer.class, 1);
		
		System.out.println(c.getName());
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
}
