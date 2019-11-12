package com.itheima.c_hql;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.itheima.domain.Customer;
import com.itheima.utils.HibernateUtils;
//HQL详解2-表连接

//内连接	=>
//左外连接 =>
//右外连接 =>
public class Demo2 {
	@Test
	//交叉连接 => 笛卡尔积
	//开发时要避免出现笛卡尔积
	public void fun1(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		
		Query query = session.createQuery("from Customer c,Order o");
		
		
		List<Object[]> list = query.list();
		
		for(Object[] objs : list){
			System.out.println(Arrays.toString(objs));
		}
		
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	@Test
	//内连接 
	//隐式内连接 => 在笛卡尔积基础上过滤无效数据
	public void fun2(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		
		Query query = session.createQuery("from Customer c,Order o where o.customer = c");
		
		
		List<Object[]> list = query.list();
		
		for(Object[] objs : list){
			System.out.println(Arrays.toString(objs));
		}
		
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	@Test
	//内连接
	//显式内连接( 非迫切)=> inner join
	// List<Object[]>
	// Object[] => [Customer,Order]
	// 将父 与 子 对象装入数组中分别 返回
	public void fun3(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		
		//Query query = session.createQuery("from Customer c  inner join c.orders ");
		
		Query query = session.createQuery("from Customer c  inner join   c.orders ");
		
		List<Object[]> list = query.list();
		
		for(Object[] objs : list){
			System.out.println(Arrays.toString(objs));
		}
		
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	@Test
	//内连接
	//显式内连接(迫切)=> inner join
	// List<Customer> 
	// 迫切连接会将 子装入父中,组装成一个对象
	public void fun4(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		
		
		Query query = session.createQuery("from Customer c  inner join fetch  c.orders ");
		
		List<Object> list = query.list();
		
		for(Object obj : list){
			System.out.println(obj);
		}
		
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	
	@Test
	//左外连接
	//left [outer] join
	public void fun5(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		
		
		Query query = session.createQuery("from Customer c  left outer join   c.orders ");
		
		List<Object[]> list = query.list();
		
		for(Object[] objs : list){
			System.out.println(Arrays.toString(objs));
		}
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	@Test
	//左外连接 迫切
	//left [outer] join fetch
	public void fun6(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		
		
		Query query = session.createQuery("from Customer c  left outer join fetch  c.orders ");
		
		
		List<Object> list = query.list();
		
		for(Object obj : list){
			System.out.println(obj);
		}
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	

	@Test
	//右外连接
	//right [outer] join
	public void fun7(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		
		
		Query query = session.createQuery("from Customer c  right outer join   c.orders ");
		
		List<Object[]> list = query.list();
		
		for(Object[] objs : list){
			System.out.println(Arrays.toString(objs));
		}
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	@Test
	//右外连接 迫切
	//right [outer] join fetch
	public void fun8(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		
		
		Query query = session.createQuery("from Customer c  right outer join fetch  c.orders ");
		
		
		List<Object> list = query.list();
		
		for(Object obj : list){
			System.out.println(obj);
		}
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
}
