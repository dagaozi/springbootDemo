package cn.wzhospital.demo.config.db;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
 * 数据源Rsk1
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryRsk1",
        transactionManagerRef = "transactionManagerRsk1",
        basePackages = {"cn.wzhospital.demo.repository.rsk1"}) //设置Repository所在位置
public class Rsk1Config {
    @Autowired
    @Qualifier("rsk1DataSource")
    private DataSource rsk1DataSource;

    @Bean(name = "entityManagerRsk1")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return Objects.requireNonNull(entityManagerFactoryRsk1(builder).getObject()).createEntityManager();
    }

    @Bean(name = "entityManagerFactoryRsk1")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryRsk1(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(rsk1DataSource)
                .properties(getVendorProperties())
                .packages("cn.wzhospital.demo.dao.rsk1") //设置实体类所在位置
                .persistenceUnit("rsk1PersistenceUnit")
                .build();
    }
    @Autowired
    private JpaProperties jpaProperties;

    private Map<String, String> getVendorProperties() {
        return jpaProperties.getProperties();
    }

    @Bean(name = "transactionManagerRsk1")
    public PlatformTransactionManager transactionManagerRsk1(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactoryRsk1(builder).getObject()));
    }
}
