package br.estudo.tw.exam.input;

import br.estudo.tw.exam.domain.CustomerTypeEnum;
import br.estudo.tw.exam.tests.AbstractTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class HotelInputReaderTest extends AbstractTests {

    @Autowired
    private HotelInputReader hotelInputReader;

    @Test
    public void testReadInput1Dates() throws Exception {
        hotelInputReader.read(getExampleInput1());

        String data1 = "16Mar2009(mon)";
        String data2 = "17Mar2009(tues)";
        String data3 = "18Mar2009(wed)";

        List<String> dates = hotelInputReader.getDates();
        assertTrue(dates.contains(data1));
        assertTrue(dates.contains(data2));
        assertTrue(dates.contains(data3));
    }

    @Test
    public void testReadInput1Customer() throws Exception {
        hotelInputReader.read(getExampleInput1());

        String customerType = hotelInputReader.getCustomerType();
        assertNotNull(customerType);
        assertFalse(customerType.isEmpty());
        assertEquals(CustomerTypeEnum.REGULAR.getDesction(), customerType);
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
        return "Regular: 16Mar2009(mon), 17Mar2009(tues), 18Mar2009(wed)";
    }

    private String getExampleInput2() {
        return "Regular: 20Mar2009(fri), 21Mar2009(sat), 22Mar2009(sun)";
    }

    private String getExampleInput3() {
        return "Rewards: 26Mar2009(thur), 27Mar2009(fri), 28Mar2009(sat)";
    }
}