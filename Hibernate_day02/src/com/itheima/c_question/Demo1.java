package com.itheima.c_question;

import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import com.itheima.domain.User;
import com.itheima.utils.HibernateUtils;
//学生问题
//1 save 和 persist 方法 对比之后,没有区别?
public class Demo1 {
	@Test
	public void fun1(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		User u = new User();
		
		u.setId(99);
		
		session.persist(u); // 体现的是持久化. persist提供的理念是将对象完整的持久化. 持久化也包括对象的ID.
							// 在保存之前设置了ID.那么就会将设置的ID进行insert. 但是 主键策略是由数据库来维护. 所以产生矛盾.所以抛出异常.
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	@Test
	public void fun2(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		User u = new User();
		
		u.setId(99);
		
		session.save(u); // save方法,如果保存的对象在保存之前设置了ID.那么该ID也被认为是无效的ID.
		System.out.println(u.getId());
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
}
