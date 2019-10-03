package com.example.tradelog.api.converter;

import com.example.tradelog.api.spec.model.Action;
import com.example.tradelog.api.spec.model.ShareJournalModel;
import com.example.tradelog.api.spec.model.TransactionModel;
import com.example.tradelog.api.spec.model.TransactionSettingsModel;
import com.example.tradelog.api.spec.model.TransactionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

class SyntheticSharesGeneratorTest {

    private List<ShareJournalModel> shareList;

    @BeforeEach
    void init() {
        this.shareList = new ArrayList<>();
    }

    @Test
    void testWhereThereIsNoNeedToGenerate() {
        TransactionSettingsModel settingsModel = new TransactionSettingsModel("1234", 44.00, false, false);

        shareList.add(ShareJournalModel.builder()
                .withTransactionModel(TransactionModel.builder()
                        .withType(TransactionType.SHARE)
                        .withDate(OffsetDateTime.now())
                        .withSymbol("XYZ")
                        .withSettings(settingsModel)
                        .build())
                .withQuantity(500)
                .withPrice(10.0)
                .withAction(Action.BUY)
                .build());

        shareList.add(ShareJournalModel.builder()
                .withTransactionModel(TransactionModel.builder()
                        .withType(TransactionType.SHARE)
                        .withDate(OffsetDateTime.now())
                        .withSymbol("XYZ")
                        .withSettings(settingsModel)
                        .build())
                .withQuantity(500)
                .withPrice(10.0)
                .withActualPrice(60.0)
                .withAction(Action.SELL)
                .build());

        List<ShareJournalModel> syn = SyntheticSharesGenerator.createSynthetic.apply(shareList);
        Assertions.assertEquals(0, syn.size());
    }


    @Test
    void testAveragePrice_1() {
        TransactionSettingsModel settingsModel = new TransactionSettingsModel("1234", 0.0, false, false);

        shareList.add(ShareJournalModel.builder()
                .withTransactionModel(TransactionModel.builder()
                        .withType(TransactionType.SHARE)
                        .withDate(OffsetDateTime.now())
                        .withSymbol("XYZ")
                        .withSettings(settingsModel)
                        .build())
                .withQuantity(100)
                .withPrice(55.0)
                .withActualPrice(60.0)
                .withAction(Action.BUY)
                .build());

        shareList.add(ShareJournalModel.builder()
                .withTransactionModel(TransactionModel.builder()
                        .withType(TransactionType.SHARE)
                        .withDate(OffsetDateTime.now())
                        .withSymbol("XYZ")
                        .withSettings(settingsModel)
                        .build())
                .withQuantity(200)
                .withPrice(66.0)
                .withActualPrice(60.0)
                .withAction(Action.BUY)
                .build());

        List<ShareJournalModel> syn = SyntheticSharesGenerator.createSynthetic.apply(shareList);
        Assertions.assertEquals(1, syn.size());

        ShareJournalModel xyz = syn.get(0);
        Assertions.assertEquals(Action.SELL, xyz.getAction());
        Assertions.assertEquals(300, xyz.getQuantity());
        Assertions.assertEquals(62.333333333333336, xyz.getPrice());
        Assertions.assertEquals(60.0, xyz.getActualPrice());
        Assertions.assertEquals(Double.valueOf(0.0), Double.valueOf(xyz.getTransactionDetails().getSettings().getPreferredPrice()));
    }


    @Test
    void testAveragePrice_2() {
        TransactionSettingsModel settingsModel_1 = new TransactionSettingsModel("1234", 99.00, false, false);
        TransactionSettingsModel settingsModel_2 = new TransactionSettingsModel("1234", 99.00, false, true);

        shareList.add(ShareJournalModel.builder()
                .withTransactionModel(TransactionModel.builder()
                        .withType(TransactionType.SHARE)
                        .withDate(OffsetDateTime.now())
                        .withSymbol("XYZ")
                        .withSettings(settingsModel_1)
                        .build())
                .withQuantity(100)
                .withPrice(55.0)
                .withActualPrice(60.0)
                .withAction(Action.BUY)
                .build());

        shareList.add(ShareJournalModel.builder()
                .withTransactionModel(TransactionModel.builder()
                        .withType(TransactionType.SHARE)
                        .withDate(OffsetDateTime.now())
                        .withSymbol("XYZ")
                        .withSettings(settingsModel_1)
                        .build())
                .withQuantity(50)
                .withPrice(45.0)
                .withActualPrice(60.0)
                .withAction(Action.BUY)
                .build());

        shareList.add(ShareJournalModel.builder()
                .withTransactionModel(TransactionModel.builder()
                        .withType(TransactionType.SHARE)
                        .withDate(OffsetDateTime.now())
                        .withSymbol("XYZ")
                        .withSettings(settingsModel_2)
                        .build())
                .withQuantity(550)
                .withPrice(80.0)
                .withActualPrice(60.0)
                .withAction(Action.BUY)
                .build());

        List<ShareJournalModel> syn = SyntheticSharesGenerator.createSynthetic.apply(shareList);
        Assertions.assertEquals(1, syn.size());

        ShareJournalModel xyz = syn.get(0);
        Assertions.assertEquals(Action.SELL, xyz.getAction());
        Assertions.assertEquals(150, xyz.getQuantity());
        Assertions.assertEquals(51.666666666666664, xyz.getPrice());
        Assertions.assertEquals(60.0, xyz.getActualPrice());
        Assertions.assertEquals(Double.valueOf(99.0), Double.valueOf(xyz.getTransactionDetails().getSettings().getPreferredPrice()));
    }


    @Test
    void testSynCSCO100AndADBE200() {
        TransactionSettingsModel settingsModel = new TransactionSettingsModel("1234", 44.00, false, false);

        shareList.add(ShareJournalModel.builder()
                .withTransactionModel(TransactionModel.builder()
                        .withType(TransactionType.SHARE)
                        .withDate(OffsetDateTime.now())
                        .withSymbol("CSCO")
                        .withSettings(settingsModel)
                        .build())
                .withQuantity(500)
                .withPrice(10.0)
                .withAction(Action.BUY)
                .build());

        shareList.add(ShareJournalModel.builder()
                .withTransactionModel(TransactionModel.builder()
                        .withType(TransactionType.SHARE)
                        .withDate(OffsetDateTime.now())
                        .withSymbol("ADBE")
                        .withSettings(settingsModel)
                        .build())
                .withQuantity(500)
                .withPrice(10.0)
                .withAction(Action.BUY)
                .build());


        shareList.add(ShareJournalModel.builder()
                .withTransactionModel(TransactionModel.builder()
                        .withType(TransactionType.SHARE)
                        .withDate(OffsetDateTime.now())
                        .withSymbol("CSCO")
                        .withSettings(settingsModel)
                        .build())
                .withQuantity(400)
                .withPrice(10.0)
                .withAction(Action.SELL)
                .build());

        shareList.add(ShareJournalModel.builder()
                .withTransactionModel(TransactionModel.builder()
                        .withType(TransactionType.SHARE)
                        .withDate(OffsetDateTime.now())
                        .withSymbol("ADBE")
                        .withSettings(settingsModel)
                        .build())
                .withQuantity(300)
                .withPrice(10.0)
                .withAction(Action.SELL)
                .build());


        List<ShareJournalModel> syn = SyntheticSharesGenerator.createSynthetic.apply(shareList);
        Assertions.assertEquals(2, syn.size());
        syn.stream().filter(s -> s.getTransactionDetails().getSymbol().equals("CSCO")).forEach(f -> {
            Assertions.assertEquals(Action.SELL, f.getAction());
            Assertions.assertEquals(100, f.getQuantity());
        });

        syn.stream().filter(s -> s.getTransactionDetails().getSymbol().equals("ADBE")).forEach(f -> {
            Assertions.assertEquals(Action.SELL, f.getAction());
            Assertions.assertEquals(200, f.getQuantity());
        });
    }



    @Test
    void testSingleBuyTrade() {
        TransactionSettingsModel settingsModel = new TransactionSettingsModel("1234", 44.00, false, false);

        ShareJournalModel model = ShareJournalModel.builder()
                .withTransactionModel(TransactionModel.builder()
                        .withType(TransactionType.SHARE)
                        .withSymbol("XYZ")
                        .withSettings(settingsModel)
                        .build())
                .withPrice(10)
                .withQuantity(100)
                .withAction(Action.BUY)
                .build();

        shareList.add(model);

        List<ShareJournalModel> newList = SyntheticSharesGenerator.createSynthetic.apply(shareList);
        Assertions.assertEquals(1, newList.size());

        ShareJournalModel synthetic = newList.get(0);
        Assertions.assertEquals(model.getTransactionDetails().getSymbol(), synthetic.getTransactionDetails().getSymbol());
        Assertions.assertEquals(TransactionType.SYNTHETIC_SHARE, synthetic.getTransactionDetails().getType());
        Assertions.assertEquals(Action.SELL, synthetic.getAction());
        Assertions.assertEquals(model.getQuantity(), synthetic.getQuantity());
    }
}
