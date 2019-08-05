package com.ps.usercenter.service;

import com.ps.dto.HistoryDTO;

import java.util.List;

public interface HistoryService {
    List<HistoryDTO> findHistoryAll(int userId);
}
