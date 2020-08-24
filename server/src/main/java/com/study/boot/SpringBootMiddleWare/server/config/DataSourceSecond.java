package com.study.boot.SpringBootMiddleWare.server.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @ClassName DataSourcePrimary
 * @Description: TODO 给MyBatis显示注入DataSource(从数据源)
 * @Author lxl
 * @Date 2020/8/23
 * @Version V1.0
 **/
@Configuration
@MapperScan(basePackages = "com.study.boot.SpringBootMiddleWare.model.mapper.second", sqlSessionTemplateRef = "primarySqlSessionTemplate")
public class DataSourceSecond {

    @Autowired
    private Environment env;
    @Primary
    @Bean(name = "secondDatasource")
    public DataSource dataSource(){
        DataSource dataSource = DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url(env.getProperty("datasource.two.url"))
                .username("datasource.two.username")
                .password("datasource.two.password").build();
        return dataSource;
    }

    @Primary
    @Bean(name = "secondSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("secondDatasource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classPath:/second/*.xml"));
        return bean.getObject();
    }

    @Primary
    @Bean(name = "secondDataSourceTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("secondDatasource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "secondSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("secondSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Primary
    @Bean(name = "secondJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(@Qualifier("secondDatasource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
