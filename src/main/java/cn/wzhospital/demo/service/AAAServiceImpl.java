package cn.wzhospital.demo.service;

import cn.wzhospital.demo.dao.ylgz3.AAA;
import cn.wzhospital.demo.repository.ylgz3.AAARepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class AAAServiceImpl implements AAAService{
    @Autowired
    private AAARepo aaaRepo;
    @Override
    public List<AAA> getList() {
        return aaaRepo.findAll();
    }
}
