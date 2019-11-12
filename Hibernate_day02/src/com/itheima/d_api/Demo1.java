package com.itheima.d_api;

import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import com.itheima.domain.User;
import com.itheima.utils.HibernateUtils;
//其他API (大部分都是了解)
public class Demo1 {
	@Test
	//1. evict 将缓存中的对象移除.
	//2. clear 清空1级缓存 
	public void fun1(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		User u1 = (User) session.get(User.class, 1);
		
		session.clear();
		
		User u2 = (User) session.get(User.class, 1);
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	@Test
	//3 refresh 刷新 => 强制刷新缓存中的对象 => (可以用来解决缓存与数据库数据不同步的问题)
	public void fun2(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		User u1 = (User) session.get(User.class, 1);
		
		session.refresh(u1); //将缓存中的对象立刻与数据库同步,会再发送一个sql语句
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	@Test
	//4 flush 对比快照,并提交缓存对象
	public void fun3(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		User u1 = (User) session.get(User.class, 1);
		
		//u1.setName("zhangsan");
		
		session.flush();// 立刻提交session缓存中的对象到数据库
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	@Test
	// 代理主键=> native
	//5.1 aveOrUpdate方法
	//saveOrUpdate 可以同时完成保存或更新操作
	//主键为空=>save
	//主键有值=> update
	public void fun4(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		User u = new User();
		u.setId(99);
		u.setName("jack");
		u.setPassword("1234");
		
		session.saveOrUpdate(u);
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	// 自然主键=> assigned
	//5 update 与 saveOrUpdate方法
		//saveOrUpdate 可以同时完成保存或更新操作
		//主键为空=> 报错,因为无论是save还是update 都必须指定id
		//主键有值=> 先会根据主键查询数据库.
				// 数据库中存在=> 执行update
				// 数据库中不存在=> 执行insert
	@Test
		public void fun5(){
			Session session = HibernateUtils.openSession();
			session.beginTransaction();
			//------------------------------------------------
			User u = new User();
			u.setId(88);
			u.setName("jack01");
			u.setPassword("1234");
			
			session.saveOrUpdate(u);
			//------------------------------------------------
			session.getTransaction().commit();
			session.close(); // 游离状态
		}
	
	
	@Test
	//在我们使用Hibernate时候,注意要避免出现,两个相同的ID对象.放入一级缓存的情况.
	public void fun6(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		User u = (User) session.get(User.class, 1);// 持久化,缓存中存在
		
		session.evict(u); // 游离态,缓存中不存在
		
		User u2 = (User) session.get(User.class, 1);// 持久化,缓存中存在
		
		session.update(u); // 将U重新变为持久化状态,缓存中存在
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
}
