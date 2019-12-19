package cn.wzhospital.demo.service;

import cn.wzhospital.demo.dao.rsk1.Ryk;
import cn.wzhospital.demo.repository.rsk1.RykRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RykServiceImpl implements RykService {
    @Autowired
    private RykRepo rykRepo;

    @Override
    public List<Ryk> getRykList() {
        return rykRepo.findAll();
    }
}
