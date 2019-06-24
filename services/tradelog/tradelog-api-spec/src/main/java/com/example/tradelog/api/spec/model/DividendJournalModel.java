package com.example.tradelog.api.spec.model;

public class DividendJournalModel {

    private static final long serialVersionUID = 1L;

    private TransactionModel transactionDetails;
    private double dividend;

    public DividendJournalModel() {
        //required by Jackson
    }

    public TransactionModel getTransactionDetails() {
        return transactionDetails;
    }

    public double getDividend() {
        return dividend;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        DividendJournalModel model;

        public Builder() {
            model = new DividendJournalModel();
        }

        public DividendJournalModel build() {
            DividendJournalModel buildModel = this.model;
            this.model = new DividendJournalModel();
            return buildModel;
        }

        public Builder withTransactionModel(TransactionModel transactionModel) {
            model.transactionDetails = transactionModel;
            return this;
        }

        public Builder withDividend(double dividend) {
            model.dividend = dividend;
            return this;
        }
    }
}
