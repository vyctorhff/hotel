package br.estudo.tw.exam.service.imp;

import br.estudo.tw.exam.domain.Customer;
import br.estudo.tw.exam.domain.Hotel;
import br.estudo.tw.exam.input.CustomerAdapter;
import br.estudo.tw.exam.input.HotelInputException;
import br.estudo.tw.exam.input.HotelInputReader;
import br.estudo.tw.exam.service.HotelSelectService;
import br.estudo.tw.exam.service.ServiceException;
import br.estudo.tw.exam.tests.AbstractTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by torugo on 01/09/15.
 */
public class HotelSelectServiceImpTest extends AbstractTests {

    private static final String LAKEWOOD   = "Lakewood";
    private static final String RIDGEWOOD  = "Ridgewood";
    private static final String BRIDGEWOOD = "Bridgewood";

    @Autowired
    private HotelSelectService hotelSelectService;

    @Autowired
    private HotelInputReader hotelInputReader;

    @Test
    public void testSelectCheapestHotelLakewood() throws Exception {
        Hotel selectHotel = getSelectedHotelByFile(super.FILE_INPUT_1);

        assertNotNull(selectHotel);
        assertEquals(LAKEWOOD, selectHotel.getName());
    }

    @Test
    public void testSelectCheapestHotelBridgewood() throws Exception {
        Hotel selectHotel = getSelectedHotelByFile(super.FILE_INPUT_2);

        assertNotNull(selectHotel);
        assertEquals(BRIDGEWOOD, selectHotel.getName());
    }

    @Test
    public void testSelectCheapestHotelRidgewood() throws Exception {
        Hotel selectHotel = getSelectedHotelByFile(super.FILE_INPUT_3);

        assertNotNull(selectHotel);
        assertEquals(RIDGEWOOD, selectHotel.getName());
    }

    private Hotel getSelectedHotelByFile(String file) throws Exception {
        hotelInputReader.read(super.loadInputFromFile(file));
        CustomerAdapter adapter = new CustomerAdapter(hotelInputReader);
        adapter.doAdapter();

        Customer customer = adapter.getCustomer();
        return hotelSelectService.selectCheapest(customer);
    }
}