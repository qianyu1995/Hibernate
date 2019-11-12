package com.itheima.e_one2many;

import java.util.Set;

import org.hibernate.Session;
import org.junit.Test;

import com.itheima.domain.Customer;
import com.itheima.domain.Order;
import com.itheima.utils.HibernateUtils;
//测试 一对多关系
public class Demo1 {
	@Test
	//1 测试1对多关系中,保存操作
	//共打印5条语句
	//前3条打印insert	=> 保存对象,维护外键
	//后两条打印update => 维护外键
	//解决=>  单纯指定 关系由其中一方来维护.另一方不维护关系.
	//注意=> 外键维护的放弃,只能由非外键所在对象来放弃.
	//Customer  inverse属性: true
	//只打印3条语句=> 外键由order自己来维护
	
	public void fun1(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		Customer c = new Customer();
		c.setName("tom");
		
		Order o1 = new Order();
		o1.setName("肥皂");
		
		Order o2 = new Order();
		o2.setName("蜡烛");
		
		//c.getOrders().add(o1);//维护关系
		//c.getOrders().add(o2); //维护关系
		
		o1.setCustomer(c);//维护关系
		o2.setCustomer(c);//维护关系
		
		session.save(c);//保存对象
		session.save(o1);//保存对象
		session.save(o2);//保存对象
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	//多表关系=> 删除
	//删除 用户时 ,会先移除 Customer中引用的外键.然后再删除Customer
	
	// 结论: 维护一方的对象时,会自动维护另一方的关系
	// Customer 的 inverse属性: true
	// 会报错 => Customer不负责维护外键, 直接删除Customer 会导致,order引用了无效的id.违反了外键约束.
	@Test
	public void fun2(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		
		Customer c = (Customer) session.get(Customer.class, 3);
		
		// Customer 的 inverse属性: true 
		
		Set<Order> set = c.getOrders();
		for(Order o : set){
			o.setCustomer(null);//设置订单不属于任何Customer
		}
		
		session.delete(c);
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	//什么时候配置inverse属性?
	// 主要看业务. 如果一的一方经常需要维护外键 = 在1的一方不要配置inverse属性.
}
