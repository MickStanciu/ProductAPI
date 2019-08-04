package com.example.tradelog.api.service;

import com.example.tradelog.api.repository.TransactionRepository;
import com.example.tradelog.api.spec.model.TransactionModel;
import com.example.tradelog.api.spec.model.TransactionSettingsModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionJournalService {

    private static final Logger log = LoggerFactory.getLogger(TransactionJournalService.class);

    private TransactionRepository transactionRepository;

    public TransactionJournalService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<String> getAllTradedSymbols(String accountId) {
        return transactionRepository.getUniqueSymbols(accountId);
    }

    public boolean updateOptions(TransactionSettingsModel model) {
        return transactionRepository.updateSettings(model);
    }

    public void updateSettingsBulk(List<TransactionSettingsModel> models) {
        //todo: find a better way
        for (TransactionSettingsModel model : models) {
            if (!transactionRepository.updateSettings(model)) {
                log.error("Failed to update settings for {}", model.getTransactionId());
            }
        }
    }

    public Optional<String> createTransactionRecord(TransactionModel model) {
        Optional<String> optionalId = transactionRepository.createTransactionRecord(model);

        if (optionalId.isPresent()) {
            TransactionSettingsModel transactionSettingsModel = TransactionSettingsModel.builder()
                    .withGroupSelected(true)
                    .withLegClosed(false)
                    .build();
            boolean transactionSettingsSuccess = transactionRepository.createSettings(optionalId.get(), transactionSettingsModel);
            if (transactionSettingsSuccess) {
                return optionalId;
            }
        }

        return Optional.empty();
    }

    public boolean deleteSettingsRecord(String transactionId) {
        return transactionRepository.deleteSettingsRecord(transactionId);
    }

    public boolean deleteDividendRecord(String transactionId, String accountId, String symbol) {
        return transactionRepository.deleteDividendRecord(transactionId, accountId, symbol);
    }

    public boolean deleteShareRecord(String transactionId, String accountId, String symbol) {
        return transactionRepository.deleteShareRecord(transactionId, accountId, symbol);
    }

    public boolean deleteOptionRecord(String transactionId, String accountId, String symbol) {
        return transactionRepository.deleteOptionRecord(transactionId, accountId, symbol);
    }
}
