package com.yiii.provider.mapper;


import com.yiii.provider.entity.Yi;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MainMapper {


    public Yi selectString();
}
