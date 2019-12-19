package cn.wzhospital.demo.config.db;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;
import java.util.Objects;

/**
 * 数据源Ylzg3
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryYlgz3",
        transactionManagerRef = "transactionManagerYlgz3",
        basePackages = {"cn.wzhospital.demo.repository.ylgz3"}) //设置Repository所在位置
public class Ylgz3Config {
    @Autowired
    @Qualifier("ylgz3DataSource")
    private DataSource ylgz3DataSource;

    @Primary
    @Bean(name = "entityManagerYlgz3")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryYlgz3(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "entityManagerFactoryYlgz3")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryYlgz3(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(ylgz3DataSource)
                .properties(getVendorProperties())
                .packages("cn.wzhospital.demo.dao.ylgz3") //设置实体类所在位置
                .persistenceUnit("ylgz3PersistenceUnit")
                .build();
    }

    @Autowired
    private JpaProperties jpaProperties;

    private Map<String, String> getVendorProperties() {
        return jpaProperties.getProperties();
    }

    @Primary
    @Bean(name = "transactionManagerYlgz3")
    public PlatformTransactionManager transactionManagerYlgz3(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactoryYlgz3(builder).getObject()));
    }

}
