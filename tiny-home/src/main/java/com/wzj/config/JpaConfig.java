package com.wzj.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.dialect.MySQL5Dialect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.ValidationMode;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "com.wzj.dao")
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class JpaConfig {
    @Value("${druid.url}")
    private String url;
    @Value("${druid.userName}")
    private String userName;
    @Value("${druid.password}")
    private String password;
    @Value("${druid.driverClassName}")
    private String driverClassName;
    @Value("${druid.maxActive}")
    private Integer maxActive;
    @Value("${druid.initialSize}")
    private Integer initialSize;
    @Value("${druid.maxWait}")
    private Integer maxWait;
    @Value("${druid.validationQuery}")
    private String validationQuery;
    @Value("${druid.testOnBorrow}")
    private Boolean testOnBorrow;
    @Value("${druid.testOnReturn}")
    private Boolean testOnReturn;
    @Value("${druid.testWhileIdle}")
    private Boolean testWhileIdle;
    @Value("${druid.timeBetweenEvictionRunsMillis}")
    private Integer timeBetweenEvictionRunsMillis;
    @Value("${jpa.scanEntityPackage}")
    private String scanEntityPackage;

    @Bean
    public DataSource dataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(userName);
        druidDataSource.setPassword(password);
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setMaxWait(maxWait);
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setValidationQuery(validationQuery);
        druidDataSource.setTestOnBorrow(testOnBorrow);
        druidDataSource.setTestOnReturn(testOnReturn);
        druidDataSource.setTestWhileIdle(testWhileIdle);
        druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        druidDataSource.setFilters("stat,wall");
        return druidDataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPackagesToScan(scanEntityPackage);
        entityManagerFactory.setValidationMode(ValidationMode.NONE);
        entityManagerFactory.setPersistenceUnitName("PERSISTENCE_UNIT");
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty(AvailableSettings.SHOW_SQL, "true");
        jpaProperties.setProperty(AvailableSettings.FORMAT_SQL, "true");
        jpaProperties.setProperty(AvailableSettings.HBM2DDL_AUTO, "none");
        jpaProperties.setProperty(AvailableSettings.DIALECT, MySQL5Dialect.class.getName());
        jpaProperties.setProperty(AvailableSettings.DEFAULT_BATCH_FETCH_SIZE, "50");
        jpaProperties.setProperty(AvailableSettings.USE_NEW_ID_GENERATOR_MAPPINGS, "false");
        jpaProperties.setProperty(AvailableSettings.PHYSICAL_NAMING_STRATEGY, SpringPhysicalNamingStrategy.class.getName());
        entityManagerFactory.setJpaProperties(jpaProperties);
        return entityManagerFactory;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory, DataSource dataSource) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(entityManagerFactory);
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        jpaTransactionManager.setDataSource(dataSource);
        return jpaTransactionManager;
    }
}
