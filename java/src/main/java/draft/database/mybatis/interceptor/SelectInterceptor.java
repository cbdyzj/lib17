package draft.database.mybatis.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.Map;
import java.util.Properties;

/**
 * 在准备SQL的阶段处理修改SQL
 *
 * @author cbdyzj
 * @since 2018.3.6
 */
@Slf4j
@Intercepts({@Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class, Integer.class})})
public class SelectInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        var statementHandler = this.realTarget(invocation.getTarget());
        var metaObject = SystemMetaObject.forObject(statementHandler);
        var mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");

        if (SqlCommandType.SELECT != mappedStatement.getSqlCommandType()) {
            return invocation.proceed();
        }

        var boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        var parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");

        var originalSql = boundSql.getSql().replaceAll("\\s+", " ");
        var parameter = parameterHandler.getParameterObject();
        var newSql = originalSql + " LIMIT 10";
        metaObject.setValue("delegate.boundSql.sql", newSql);

        log.debug("Original SQL: {} \nOriginal Parameter: {}", originalSql, parameter);
        log.debug("Modified SQL: " + newSql);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    private StatementHandler realTarget(Object target) {
        if (Proxy.isProxyClass(target.getClass())) {
            var metaObject = SystemMetaObject.forObject(target);
            return realTarget(metaObject.getValue("h.target"));
        }
        return (StatementHandler) target;
    }
}
