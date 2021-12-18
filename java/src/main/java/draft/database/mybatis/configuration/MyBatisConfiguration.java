package draft.database.mybatis.configuration;


import org.apache.ibatis.session.SqlSessionFactory;
import draft.database.DataSourceHolder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class MyBatisConfiguration {

    @Bean
    public DataSource dataSource() {
        return DataSourceHolder.get();
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource ds) {
        var factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(ds);
        return factoryBean;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        var configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("draft.database.mybatis.mapper");
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        return configurer;
    }
}
