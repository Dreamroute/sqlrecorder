package com.github.dreamroute.sqlrecorder.interceptor;

import java.sql.Connection;

import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

// @Signature注解的3个参数分别是：拦截的接口，接口的方法，方法的参数类型
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class SqlRecorder implements Interceptor {

    
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 所有的逻辑都写在此方法，invocation包装了StatementHandler对象以及参数
        System.err.println(invocation);
        RoutingStatementHandler rsh = (RoutingStatementHandler) invocation.getTarget();
        BoundSql bs = rsh.getBoundSql();
        String sql = bs.getSql();
        // 这里我们就找到了sql语句
        System.err.println(sql);
        
        Object param = bs.getParameterObject();
        // 把param转成JSON就行了
        
        System.err.println(sql);
        System.err.println(param);
        
        // 最后执行一下默认方法
        return invocation.proceed();
    }
    
    // 这里是过滤掉不需要拦截的接口，我们只需要拦截StatementHandler，所以这里只需要判断一下如果不是StatementHandler，那么直接返回原始对象
    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            target = Plugin.wrap(target, this);
        }
        return target;
    }
    
}