package com.example.demo.service.spi;

import com.example.demo.service.model.TransferDto;
import com.example.demo.service.model.TransferResponseDto;

public interface TransferService {

  TransferResponseDto transfer(TransferDto transferDto);

}
