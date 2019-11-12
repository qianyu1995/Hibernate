package com.itheima.c_hql;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.itheima.domain.Customer;
import com.itheima.utils.HibernateUtils;
//HQL详解
public class Demo {
	@Test
	// Hql查询所有
	public void fun1(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		//Query query = session.createQuery("from Customer");
		
		//Query query = session.createQuery("from Customer c");
		
		Query query = session.createQuery("select c from Customer c");
		
		List<Customer> list = query.list();
		
		System.out.println(list);
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	@Test
	// Hql查询对象的某几个属性
	public void fun2(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		
		//Query query = session.createQuery("select c.name from Customer c");
		
		Query query = session.createQuery("select c.id , c.name from Customer c");
		
		List<Object[]> list = query.list();
		
		for(Object[] objs : list){
			System.out.println(Arrays.toString(objs));
		}
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	@Test
	// 投影查询
	// 选择查询的基础上,想把查询结果封装到对象中
	public void fun3(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		
		//Query query = session.createQuery("select c.name from Customer c");
		
		Query query = session.createQuery("select new Customer(c.id,c.name) from Customer c");
		
		List<Customer> list = query.list();
		
		System.out.println(list);
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	@Test
	// 投影查询
	// 选择查询的基础上,想把查询结果封装到对象中
	public void fun4(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		
		//Query query = session.createQuery("select c.name from Customer c");
		
		Query query = session.createQuery("select new Customer(c.id,c.name) from Customer c");
		
		List<Customer> list = query.list();
		
		System.out.println(list);
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	@Test
	// 排序
	public void fun5(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		
		//Query query = session.createQuery("from Customer c order by c.id desc ");
		Query query = session.createQuery("from Customer c order by c.id asc ");
		
		List<Customer> list = query.list();
		
		System.out.println(list);
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	@Test
	// 分页
	public void fun6(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		
		Query query = session.createQuery("from Customer c order by c.id asc ");
		
		// limit ?,?  setFirstResult,setMaxResults
		//setFirstResult:  (当前页数-1)*每页最大记录数
		
		query.setFirstResult(1);//从哪个索引开始取数据.包裹索引本身的记录
		query.setMaxResults(1);//查询出多少条数据
		
		List<Customer> list = query.list();
		
		System.out.println(list);
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	@Test
	// 绑定参数
	public void fun7(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		
		/*Query query = session.createQuery("from Customer c where c.id = ? ");
		//参数1:?占位符的索引 ,第一个问好索引为0
		query.setInteger(0,2);*/
		
		Query query = session.createQuery("from Customer c where c.id = :haha ");
		//参数1: 参数占位符的名称
		query.setInteger("haha",2);
		
		
		Customer c = (Customer) query.uniqueResult();
		
		System.out.println(c);
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	
	@Test
	// 聚合函数
	public void fun8(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		//Query query = session.createQuery(" select count(*) from  Customer c ");
		//Query query = session.createQuery(" select avg(c.id) from  Customer c ");
		//Query query = session.createQuery(" select sum(c.id) from  Customer c ");
		//Query query = session.createQuery(" select max(c.id) from  Customer c ");
		Query query = session.createQuery(" select min(c.id) from  Customer c ");
		Object count = query.uniqueResult();
		System.out.println(count);
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	@Test
	// 分组
	// group by .. having..
	public void fun9(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		Query query = session.createQuery(" select o.customer, count(o)  " +
				"					from Order o " +
				"					group by o.customer " +
				"					having  count(o) > 2 ");
	
		List<Object[]> list=	query.list();
		
		for(Object[] objs : list){
			System.out.println(Arrays.toString(objs));
		}
	
	
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
}
