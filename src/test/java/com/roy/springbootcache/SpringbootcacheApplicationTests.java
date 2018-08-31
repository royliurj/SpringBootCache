package com.roy.springbootcache;

import com.roy.springbootcache.entity.Employee;
import com.roy.springbootcache.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootcacheApplicationTests {

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Autowired
	RedisTemplate redisTemplate;

	@Autowired
	EmployeeMapper employeeMapper;

//	@Autowired
//	RedisTemplate<Object,Employee> employeeRedisTemplate;

	@Test
	public void contextLoads() {
	}

	/** 
	 * String（字符串）, List（列表）, Set（集合）, Hash（散列）, ZSet（有序集合）
	 */
	@Test
	public void test(){
		//字符串
		stringRedisTemplate.opsForValue().append("test","hello");
		stringRedisTemplate.opsForValue().append("test"," redis");

		System.out.println(stringRedisTemplate.opsForValue().get("test"));

		stringRedisTemplate.opsForList().leftPush("mylist","1");
		stringRedisTemplate.opsForList().leftPush("mylist","2");
		stringRedisTemplate.opsForList().leftPush("mylist","3");
		stringRedisTemplate.opsForList().leftPush("mylist","4");
	}

	@Test
	public void testSaveObject(){

		Employee employee = employeeMapper.getEmpById(1);

		//默认保存对象，使用jdk序列化机制，保存到redis缓存中
		redisTemplate.opsForValue().set("emp:1",employee);

		Employee employee1 = (Employee) redisTemplate.opsForValue().get("emp:1");
		System.out.println(employee1);
	}


	@Test
	public void testSaveObject2(){

		Employee employee = employeeMapper.getEmpById(1);

		//默认保存对象，使用jdk序列化机制，保存到redis缓存中
//		employeeRedisTemplate.opsForValue().set("emp:1",employee);

//		Employee employee1 = (Employee) employeeRedisTemplate.opsForValue().get("emp:1");
//		System.out.println(employee1);
	}

}
