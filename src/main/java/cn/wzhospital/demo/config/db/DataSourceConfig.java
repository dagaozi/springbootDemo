package cn.wzhospital.demo.config.db;


import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean(name = "ylgz3DataSource")
    @Qualifier("ylgz3DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.ylgz3")
    @Primary
    public DataSource ylgz3DataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "rsk1DataSource")
    @Qualifier("rsk1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.rsk1")
    public DataSource rsk1DataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 注册一个StatViewServlet    druid监控页面配置1-帐号密码配置
     *
     * @return servlet registration bean
     */
    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(
                new StatViewServlet(), "/druid/*");
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    /**
     * 注册一个：filterRegistrationBean   druid监控页面配置2-允许页面正常浏览
     *
     * @return filter registration bean
     */
    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(
                new WebStatFilter());
        // 添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        // 添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

}
