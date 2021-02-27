package me.gaegul.mybatisjpa.user.mapper;

import me.gaegul.mybatisjpa.user.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    public List<User> selectUsers();

}
