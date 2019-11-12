package com.itheima.d_qbc;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.itheima.domain.Customer;
import com.itheima.utils.HibernateUtils;
//QBC 离线查询
public class Demo {
	@Test
	public void fun1(){
		//Service层
			DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);
			dc.add(Restrictions.eq("id", 1));
		//-------------------------------------------------
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		Criteria c = dc.getExecutableCriteria(session);
		
		System.out.println(c.list());
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
}
