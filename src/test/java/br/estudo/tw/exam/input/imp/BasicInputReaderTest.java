package br.estudo.tw.exam.input.imp;

import br.estudo.tw.exam.domain.CustomerTypeEnum;
import br.estudo.tw.exam.input.HotelInputException;
import br.estudo.tw.exam.input.HotelInputReader;
import br.estudo.tw.exam.tests.AbstractTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by torugo on 30/08/15.
 */
public class BasicInputReaderTest extends AbstractTests {

    @Autowired
    private HotelInputReader hotelInputReader;

    @Test
    public void testReadInput1Dates() throws Exception {
        hotelInputReader.read(getExampleInput1());
        assertFalse(hotelInputReader.getDates().isEmpty());
        assertTrue(hotelInputReader.getDates().size() == 4);

        String data1 = "16Mar2009(mon)";
        String data2 = "17Mar2009(tues)";
        String data3 = "8Mar2009(wed)";
        String data4 = "19Mar2009(thurs)";

        assertTrue(hotelInputReader.getDates().contains(data1));
        assertTrue(hotelInputReader.getDates().contains(data2));
        assertTrue(hotelInputReader.getDates().contains(data3));
        assertTrue(hotelInputReader.getDates().contains(data4));
    }

    @Test
    public void testReadCustomerRegular() throws Exception {
        hotelInputReader.read(getExampleInput1());

        String customerType = hotelInputReader.getCustomerType();
        assertNotNull(customerType);
        assertFalse(customerType.isEmpty());
        assertEquals(CustomerTypeEnum.REGULAR.getDesction(), customerType);
    }

    @Test
    public void testReadCustomerRewards() throws Exception {
        hotelInputReader.read(getExampleInput2());

        String customerType = hotelInputReader.getCustomerType();
        assertNotNull(customerType);
        assertFalse(customerType.isEmpty());
        assertEquals(CustomerTypeEnum.REWARDS.getDesction(), customerType);
    }

    @Test(expected = HotelInputException.class)
    public void testReadNullInput() throws Exception {
        hotelInputReader.read(null);
    }

    @Test(expected = HotelInputException.class)
    public void testReadEmptyInput() throws Exception {
        hotelInputReader.read("");
    }

    private String getExampleInput1() {
        return "Regular: 16Mar2009(mon), 17Mar2009(tues), 8Mar2009(wed), 19Mar2009(thurs)";
    }

    private String getExampleInput2() {
        return "Rewards: 26Mar2009(thur), 27Mar2009(fri), 28Mar2009(sat)";
    }

}