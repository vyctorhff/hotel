package br.estudo.tw.exam.service.imp;

import br.estudo.tw.exam.service.HotelSelectService;
import br.estudo.tw.exam.tests.AbstractTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by torugo on 01/09/15.
 */
public class HotelSelectServiceImpTest extends AbstractTests {

    @Autowired
    private HotelSelectService hotelSelectService;

    @Test
    public void testSelectCheapestHotel() throws Exception {
        fail();
    }
}