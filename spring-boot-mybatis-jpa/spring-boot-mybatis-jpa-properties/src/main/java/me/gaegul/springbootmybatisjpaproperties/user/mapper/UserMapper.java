package me.gaegul.springbootmybatisjpaproperties.user.mapper;

import me.gaegul.springbootmybatisjpaproperties.user.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    public List<User> selectUsers();

}
