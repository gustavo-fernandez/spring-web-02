package com.example.demo.service.model;

import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class AccountRequestDto {

  @NotNull
  @Length(min = 2)
  private String owner;
  @NotNull
  @Length(min = 6, max = 6)
  private String accountNumber;

}
