package com.itheima.e_api;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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
		System.out.println(session);
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
}
