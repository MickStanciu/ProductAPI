package com.example.common.rest;

import com.example.catalog.rest.CatalogRest;
import com.example.common.rest.envelope.ResponseEnvelope;
import com.example.model.ItemDto;
import com.example.service.CatalogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CatalogRestTest {

    @Mock
    private CatalogService catalogService;

    @InjectMocks
    private final CatalogRest catalogRest = new CatalogRest();

    @Test
    public void testGetItem() {
        ItemDto itemFixture = new ItemDto(5, "iPhone 6s", "sku1234", new BigDecimal(1000.0005));
        when(catalogService.getItem(5)).thenReturn(itemFixture);

        Response response = catalogRest.find(5);
        ResponseEnvelope responseEnvelope = (ResponseEnvelope) response.getEntity();
        ItemDto item = (ItemDto) responseEnvelope.getData();

        assertEquals("Id should be 5", 5, item.getId());
    }
}
