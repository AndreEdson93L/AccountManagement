package com.accountmanagement.Exercise.service;

import com.accountmanagement.Exercise.model.History;
import com.accountmanagement.Exercise.repository.HistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class HistoryService {

    private final HistoryRepository historyRepository;

    @Autowired
    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public History saveHistory(History history) {
        return historyRepository.save(history);
    }

    public List<History> getHistories() {
        return historyRepository.findAll();
    }

    public History getHistory(Long id) {
        return historyRepository.findById(id).orElse(null);
    }

    public void deleteHistory(Long id) {
        historyRepository.deleteById(id);
    }

    public History updateHistory(History history) {
        return historyRepository.save(history);
    }
}
