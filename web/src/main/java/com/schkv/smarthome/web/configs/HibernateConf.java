package com.schkv.smarthome.web.configs;

import javax.sql.DataSource;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@Slf4j
public class HibernateConf {
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setHibernateProperties(hibernateProperties());

        log.debug("Was added session Factory: " + sessionFactory);

        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://212.109.220.106:4320/SmartHome");
        dataSource.setUsername("schkv");
        dataSource.setPassword("kde2308");

        log.debug("Was added dataSource: " + dataSource);

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
            = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());

        log.info("Was added transaction Manager: " + transactionManager);

        return transactionManager;
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(
            "hibernate.hbm2ddl.auto", "create-drop");
        hibernateProperties.setProperty(
            "hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        hibernateProperties.setProperty(
            "logging.level.org.hibernate.SQL", "DEBUG");
        hibernateProperties.setProperty(
            "logging.level.org.hibernate.type.descriptor.sql.BasicBinder","TRACE");
        hibernateProperties.setProperty(
            "hibernate.format_sql", "true");
        hibernateProperties.setProperty(
            "hibernate.use_sql_comments","true");
        hibernateProperties.setProperty(
            "javax.persistence.schema-generation.database.action", "drop-and-create");

        return hibernateProperties;
    }
}