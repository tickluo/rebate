package org.sixcity.mapper;

import model.CrudDao;
import org.sixcity.domain.Merchant;
import org.sixcity.domain.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends CrudDao<User> {

    int insert(User user);

    int insertMerchant(Merchant merchant);

    int updatePassword(@Param("id") Long id, @Param("password") String password);

    int updateUser(User user);

    int updateAmount(User user);

    User findByUsername(String username);

    User findById(@Param("id") Long id);

    List<User> getAllMerchant();
}
