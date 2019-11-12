package com.itheima.b_api;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.itheima.a_hello.User;
//详解Cretiaria对象
public class Cretiaria_test {
	@Test
	//Cretiaria对象 与 Query对象功能很像
	//控制查询
	public void fun1(){
		//1加载配置
		Configuration  conf = new Configuration().configure();
		//2 根据Configuration 配置信息创建 SessionFactory
		SessionFactory sf = conf.buildSessionFactory();
		//3 获得session
		Session session = sf.openSession();
		//打开事务
		Transaction ts = session.beginTransaction();
		
		//--------------------------------------------------------
		//Criteria 查询 => Hibernate独创的面向对象的查询=> 无语句
		Criteria criteria = session.createCriteria(User.class);
		// 查找name属性值为tom的 记录
		criteria.add(Restrictions.eq("name", "tom"));
		
		//select * from t_user; 
		// list() 将查询执行,并返回结果(多行)
		//List<User> list =	criteria.list();
		//System.out.println(list);
		
		//返回一个查询结果
		User u = (User) criteria.uniqueResult();
		System.out.println(u);
		//---------------------------------------------------------
		//提交事务
		ts.commit();
		
		//关闭资源
		session.close();
		sf.close();
	}
	
	@Test
	//Cretiaria对象 与 Query对象功能很像
	//控制查询
	public void fun2(){
		//1加载配置
		Configuration  conf = new Configuration().configure();
		//2 根据Configuration 配置信息创建 SessionFactory
		SessionFactory sf = conf.buildSessionFactory();
		//3 获得session
		Session session = sf.openSession();
		//打开事务
		Transaction ts = session.beginTransaction();
		
		//--------------------------------------------------------
		//Criteria 查询 => Hibernate独创的面向对象的查询=> 无语句
		Criteria criteria = session.createCriteria(User.class);
		
		//查找名字中包含字母o的用户
		criteria.add(Restrictions.like("name", "%o%"));
		
		//返回一个查询结果
		List<User> list =	criteria.list();
		System.out.println(list);
		//---------------------------------------------------------
		//提交事务
		ts.commit();
		
		//关闭资源
		session.close();
		sf.close();
	}
	
	@Test
	//Cretiaria对象 与 Query对象功能很像
	//>  gt
	//< lt
	//= eq
	//>= ge
	//<= le
	//like 
	//between
	public void fun3(){
		//1加载配置
		Configuration  conf = new Configuration().configure();
		//2 根据Configuration 配置信息创建 SessionFactory
		SessionFactory sf = conf.buildSessionFactory();
		//3 获得session
		Session session = sf.openSession();
		//打开事务
		Transaction ts = session.beginTransaction();
		
		//--------------------------------------------------------
		//Criteria 查询 => Hibernate独创的面向对象的查询=> 无语句
		Criteria criteria = session.createCriteria(User.class);
		
		//查找id大于3的用户
		criteria.add(Restrictions.gt("id", 3));
		//返回一个查询结果
		List<User> list =	criteria.list();
		System.out.println(list);
		//---------------------------------------------------------
		//提交事务
		ts.commit();
		
		//关闭资源
		session.close();
		sf.close();
	}
	
	
}
