package cn.wzhospital.demo.dao.ylgz3;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@ToString
@Entity
@Table(name="AAA")
public class AAA {
    @Id
    private Integer A;
    private String B;
    private String C;
    private String D;
    private String E;
    private String F;
}
