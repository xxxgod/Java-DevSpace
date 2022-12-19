package com.yiii.provider.service;


import com.yiii.provider.entity.Yi;
import com.yiii.provider.mapper.MainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainServiceImpl implements MainService {

    @Autowired
    private MainMapper mainMapper;

    @Override
    public Yi getString() {
        return mainMapper.selectString();
    }
}
