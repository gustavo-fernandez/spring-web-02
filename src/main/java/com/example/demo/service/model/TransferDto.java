package com.example.demo.service.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class TransferDto {

  @NotNull
  @Length(min = 6, max = 6)
  @JsonProperty("origin")
  private String originAccount;
  @NotNull
  @Length(min = 6, max = 6)
  @JsonProperty("destination")
  private String destinationAccount;
  @Min(1)
  @Max(1_000)
  private BigDecimal amount;

}
