package org.sixcity.mapper;

import model.CrudDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sixcity.domain.User;

@Mapper
public interface UserMapper extends CrudDao<User> {

    int insert(User user);

    int updatePassword(@Param("id")Long id, @Param("password") String password);

    int updateUser(@Param("id")Long id,
                   @Param("email") String email,
                   @Param("phone") String phone);

    User findByUsername(String username);

    User findById(Long id);
}
