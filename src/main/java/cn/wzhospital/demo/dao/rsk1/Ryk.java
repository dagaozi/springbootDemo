package cn.wzhospital.demo.dao.rsk1;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@ToString
@Entity
@Table(name="r_ryk")
public class Ryk {
    @Id
    private Integer id;
    private String xm;
}
