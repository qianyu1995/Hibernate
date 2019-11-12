package com.itheima.a_one2one;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.itheima.domain.Customer;
import com.itheima.utils.HibernateUtils;
//演示二级缓存操作
public class Demo3 {
	@Test
	//演示:查询缓存
	// 对hql语句查询的缓存
	public void fun1(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		 Query query = session.createQuery("from Customer");
		 
		 //使用二级(查询)缓存
		 // 查询时,会先从二级缓存中取结果.
		 // 取不到就执行语句,将结果放入二级查询缓存中
		 query.setCacheable(true);
		 
		 List<Customer> list = query.list();
		 
		 session.clear();
		 
		 Query query2 = session.createQuery("select c from Customer c");
		 
		 query2.setCacheable(true);
		 
		 List<Customer> list2 = query2.list();
		 
		 
		 
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
}
