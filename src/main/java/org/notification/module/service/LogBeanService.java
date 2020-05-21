package org.notification.module.service;

import org.notification.module.entities.LogBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.notification.module.repository.LogBeanRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LogBeanService {

    @Autowired
    LogBeanRepository logBeanRepository;

    public List<LogBean> getAllLogBeans() {
        return logBeanRepository.findAll();
    }

    public Optional<LogBean> getLogBeanById(int id) {
        return logBeanRepository.findById(id);
    }
}
