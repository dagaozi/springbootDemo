package cn.wzhospital.demo.controller;
import cn.wzhospital.demo.dao.rsk1.Ryk;
import cn.wzhospital.demo.dao.ylgz3.AAA;
import cn.wzhospital.demo.service.AAAService;
import cn.wzhospital.demo.service.RykService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DbUserController {
    @Autowired
    private AAAService aaaService;
    @Autowired
    private RykService rykService;
    @GetMapping("/getAAA")
    public List<AAA> getAAAlist(){

        return aaaService.getList();
    }
    @GetMapping("/getRyk")
    public List<Ryk> getRyklist() {
        return rykService.getRykList();
    }

}
