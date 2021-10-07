package com.example.demo.service.mapper;

import com.example.demo.repository.model.AccountEntity;
import com.example.demo.service.model.AccountDto;
import com.example.demo.service.model.AccountRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

  AccountDto toAccountDto(AccountEntity accountEntity);

  @Mapping(constant = "0.0", target = "amount")
  AccountEntity toAccountEntity(AccountRequestDto accountRequestDto);

}
