package org.sixcity.mapper;

import org.apache.ibatis.annotations.Param;
import org.sixcity.domain.Bank;
import model.CrudDao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BankMapper extends CrudDao<Bank> {

    int insert(Bank bank);

    List<Bank> findByUserId(@Param("userId") Long userId);

    Bank findById(@Param("id") Long id);

    Boolean checkBankExist(@Param("bankNum") String bankNumber);

}
