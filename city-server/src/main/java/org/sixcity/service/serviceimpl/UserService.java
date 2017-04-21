package org.sixcity.service.serviceimpl;

import org.sixcity.constant.SecurityConst;
import org.sixcity.domain.Merchant;
import org.sixcity.domain.User;
import org.sixcity.domain.dto.view.MerchantUser;
import org.sixcity.mapper.UserMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import service.CrudService;

import org.springframework.stereotype.Service;
import util.RandomUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 用户服务实现
 */
@Service
public class UserService extends CrudService<UserMapper, User> {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional(readOnly = false)
    public Boolean registerUser(User user) {
        //generate merchant keys
        String appKey = RandomUtils.uuid();
        String appSecret = RandomUtils.uuid();
        String appId = "1088".concat(String.valueOf(new Date().getTime()));

        //build User
        user.preInsert();
        user.setAppId(appId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(SecurityConst.SECURITY_ROLE_USER);
        //build Merchant
        Merchant merchant = new Merchant();
        merchant.setAppId(appId);
        merchant.setAppKey(appKey);
        merchant.setAppSecret(appSecret);
        merchant.preInsert();

        int addUser = getDao().insert(user);
        int addMerchant = getDao().insertMerchant(merchant);
        return addUser > 0 && addMerchant > 0;
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
        User user = new User();
        user.preUpdate();
        user.setId(id);
        user.setEmail(email);
        user.setPhone(phone);
        return getDao().updateUser(user);
    }

    @Transactional(readOnly = false)
    public int modifyAmountById(Long id, BigDecimal amount) {
        User user = new User();
        user.setId(id);
        user.setAmount(amount);
        return getDao().updateAmount(user);
    }

    public User findById(Long id) {
        return getDao().findById(id);
    }

    public User findByUsername(String username) {
        return getDao().findByUsername(username);
    }

    public MerchantUser findMerchantUserByAppKey(String appKey) {
        return getDao().findMerchantUserByAppKey(appKey);
    }

    public MerchantUser findMerchantUserById(Long id) {
        return getDao().findMerchantUserById(id);
    }

    public List<User> getAllMerchant() {
        return getDao().getAllMerchant();
    }

    public List<User> getAllRebatingUser(Date startTime, Date endTime) {
        return getDao().getAllRebatingUser(startTime, endTime);
    }
}
