package org.sixcity.service.serviceimpl;

import org.sixcity.domain.User;
import org.sixcity.mapper.UserMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import service.CrudService;

import org.springframework.stereotype.Service;

/**
 * 用户服务实现
 */
@Service
public class UserService extends CrudService<UserMapper, User> {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional(readOnly = false)
    public int registerUser(User user) {

        user.preInsert();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return getDao().insert(user);
    }

    public boolean checkLogin(String username, String password) {

        User userFindByUsername = findByUsername(username);
        return passwordEncoder.matches(password, userFindByUsername.getPassword());
    }

    @Transactional(readOnly = false)
    public int modifyPasswordById(Long id, String password) {
        return getDao().updatePassword(id, passwordEncoder.encode(password));
    }

    @Transactional(readOnly = false)
    public int modifyUserInfoById(Long id, String email, String phone) {
        return getDao().updateUser(id, email, phone);
    }

    public User findById(Long id) {
        return getDao().findById(id);
    }

    public User findByUsername(String username) {
        return getDao().findByUsername(username);
    }

}