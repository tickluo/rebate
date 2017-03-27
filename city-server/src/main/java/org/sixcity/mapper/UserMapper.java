package org.sixcity.mapper;

import model.CrudDao;
import org.apache.ibatis.annotations.Mapper;
import org.sixcity.domain.User;

@Mapper
public interface UserMapper extends CrudDao<User>{

    int insert(User user);

    User findByUsername(String username);
}
