package com.example.demo.service.impl;

import com.example.demo.common.annotation.Timed;
import com.example.demo.repository.model.AccountEntity;
import com.example.demo.repository.spi.AccountRepository;
import com.example.demo.service.exception.TransferException;
import com.example.demo.service.model.TransferDto;
import com.example.demo.service.model.TransferResponseDto;
import com.example.demo.service.spi.TransferService;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

  private final AccountRepository accountRepository;

  @Timed
  @Override
  public TransferResponseDto transfer(TransferDto transferDto) {
    // try { Thread.sleep(2_000); } catch (Exception e) {} // Simular delay
    // Validar que cuenta origen exista
    AccountEntity originAccount = accountRepository.findByAccountNumber(transferDto.getOrigin());
    if (originAccount == null) {
      throw new TransferException("E01", "Cuenta origen no existe");
    }
    // Validar que cuenta destino exista
    AccountEntity destinationAccount = accountRepository.findByAccountNumber(transferDto.getDestinationAccount());
    if (destinationAccount == null) {
      throw new TransferException("E02", "Cuenta destino no existe");
    }
    // Validar que el origen tenga saldo suficiente
    BigDecimal initialAmount = originAccount.getAmount();
    boolean isGreaterOrEqual = initialAmount.compareTo(transferDto.getAmount()) >= 0;
    if (!isGreaterOrEqual) {
      throw new TransferException("E03", "Cuenta origen no tiene saldo suficiente");
    }
    // Realizar transferencia
    AccountEntity newOriginAccount = AccountEntity.builder()
      .owner(originAccount.getOwner())
      .accountNumber(originAccount.getAccountNumber())
      .amount(originAccount.getAmount().subtract(transferDto.getAmount()))
      .build();
    accountRepository.updateAccount(newOriginAccount);

    AccountEntity newDestinationAccount = AccountEntity.builder()
      .owner(destinationAccount.getOwner())
      .accountNumber(destinationAccount.getAccountNumber())
      .amount(destinationAccount.getAmount().add(transferDto.getAmount()))
      .build();
    accountRepository.updateAccount(newDestinationAccount);

    String operationNumber = UUID.randomUUID().toString();

    TransferResponseDto transferResponseDto = new TransferResponseDto();
    transferResponseDto.setSuccess(true);
    transferResponseDto.setOperationNumber(operationNumber);
    return transferResponseDto;
  }

}
