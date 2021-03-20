package me.gaegul.mybatisjpaloop.pay.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface PayMapper {

    String selectNewId(Map<String,Object> paramMap);

}
