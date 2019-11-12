package com.itheima.b_secondcache;

import org.hibernate.Session;
import org.junit.Test;

import com.itheima.domain.Address;
import com.itheima.domain.Company;
import com.itheima.utils.HibernateUtils;
//一对一的操作
public class Demo {
	@Test
	//保存一对一数据
	//注意: 在一对一使用外键时, 外键所在的对象才能维护关系. 另一方无法维护关系
	public void fun1(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		
		Company company = new Company();
		company.setName("传智播客");
		
		Address addr = new Address();
		addr.setName("金燕龙");
		
		
		addr.setCompany(company);
		
		session.save(company);
		session.save(addr);
		
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
