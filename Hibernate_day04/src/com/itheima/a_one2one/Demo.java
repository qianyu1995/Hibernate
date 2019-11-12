package com.itheima.a_one2one;

import org.hibernate.Session;
import org.junit.Test;

import com.itheima.domain.Address;
import com.itheima.domain.Company;
import com.itheima.domain.Customer;
import com.itheima.utils.HibernateUtils;
//演示二级缓存操作
public class Demo {
	@SuppressWarnings("unused")
	@Test
	//演示:类缓存
	public void fun1(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		
		Customer customer1 = (Customer) session.get(Customer.class, 1);
		
		session.clear();//清空一级缓存中的内容
		
		Customer customer2 = (Customer) session.get(Customer.class, 1);
		
		System.out.println(customer1 == customer2);//false
		//二级缓存在缓存数据时,并不是以对象的形式缓存. 缓存的是对象数据的散列. 每次从二级缓存拿 会在一级缓存中组装成对象.
			
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
