package com.roy.springbootcache.service;

import com.roy.springbootcache.entity.Employee;
import com.roy.springbootcache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@CacheConfig(cacheManager = "employeeCacheManager")
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

//    @Autowired
//    RedisTemplate<Object, Employee> employeeRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @Qualifier("employeeCacheManager")
    @Autowired
    RedisCacheManager redisCacheManager;

    @Cacheable(value = "emp", key = "#id")
    public Employee getEmp(Integer id){
        System.out.println("获取Employee: " + id);
        return employeeMapper.getEmpById(id);
    }

    @CachePut(value = "emp", key = "#employee.id")
    public Employee updateEmp(Employee employee){
        System.out.println("更新Employee：" + employee.getId());
        employeeMapper.updateEmployee(employee);
        return employee;
    }

    @CacheEvict(value = "emp", allEntries = true)
    public void clearCache(){
        System.out.println("清空了所有缓存");
    }

    public void test(){
        //获取Cache
        Cache cache = redisCacheManager.getCache("emp");
        //进行Cache操作
        cache.put("name","roy");
    }
}
