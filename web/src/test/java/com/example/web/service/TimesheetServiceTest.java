package com.example.web.service;


import com.example.common.converter.TimeConversion;
import com.example.core.model.TimeSheetEntryModel;
import com.example.web.gateway.GatewayApi;
import com.example.web.gateway.GatewayApiMock;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class TimesheetServiceTest {

    @Mock
    private GatewayApi gatewayApi;

    @InjectMocks
    private final TimesheetService timesheetService = new TimesheetService();

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        GatewayApiMock gatewayApiMock = new GatewayApiMock();

        when(gatewayApi.getEntries(anyString(), any(BigInteger.class), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(gatewayApiMock.getEntries("123", BigInteger.ONE, TimeConversion.getStartOfDay(), TimeConversion.getEndOfDay()));
    }

    @Test
    public void testStartSlot() {
        TimeSheetEntryModel model = TimeSheetEntryModel.builder()
                .fromTime(TimeConversion.getStartOfDay().plusHours(8).toInstant(ZoneOffset.UTC))
                .toTime(TimeConversion.getStartOfDay().plusHours(10).plusMinutes(29).toInstant(ZoneOffset.UTC))
                .build();

        int start = timesheetService.getStartSlot(model);
        assertEquals(start, 16, "Wrong slot,");
    }

    @Test
    public void testEndSlot() {
        TimeSheetEntryModel model = TimeSheetEntryModel.builder()
                .fromTime(TimeConversion.getStartOfDay().plusHours(8).toInstant(ZoneOffset.UTC))
                .toTime(TimeConversion.getStartOfDay().plusHours(10).plusMinutes(29).toInstant(ZoneOffset.UTC))
                .build();

        int end = timesheetService.getEndSlot(model);
        assertEquals(end, 20, "Wrong slot,");
    }

    @Test
    public void testSlotAllocation() {
        LocalDateTime fromDate = TimeConversion.getStartOfDay();
        LocalDateTime toDate = TimeConversion.getEndOfDay();
        List<TimeSheetEntryModel> timesheetSlots = gatewayApi.getEntries("123", BigInteger.ONE, fromDate, toDate);

        List<TimeSheetEntryModel> slots = timesheetService.generateDaySlots(timesheetSlots);
        assertEquals("Slot 35", slots.get(35).getProject().getTitle());
//        for (int slotNumber=0; slotNumber<slots.size(); slotNumber++) {
//            TimeSheetEntryModel model = slots.get(slotNumber);
//            System.out.println(slotNumber + " -> " + (model != null ? model.getProject().getTitle() + " " + model.getTask().getTitle() : "empty slot"));
//        }
    }
}
