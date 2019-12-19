package cn.wzhospital.demo.dao;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@ToString
@Entity
@Table(name="vi_sjkyh")
public class DbUser {
    @Id
    private String sjkyhm;
    private String sjkmm;
//    @Transient
//    private String sourceId=sjkyhm;
    /**
     * 多数据库时需要数据库返回
     */
    @Transient
   // private String url="jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=tcp)(HOST=10.255.253.8)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=his8.wyyy.com)))";
    private String url="jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=tcp)(HOST=10.104.141.152)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=test)))";
    /**
     * 暂时就一个oracle
     */
    @Transient
    private String databasetype="oracle";
    /**
     * 多数据库时最好后端返回
     */
    @Transient
   // private String dbName="his8.wyyy.com";
    private String dbName="test";
}
