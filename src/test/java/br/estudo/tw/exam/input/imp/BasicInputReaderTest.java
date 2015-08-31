package br.estudo.tw.exam.input.imp;

import br.estudo.tw.exam.domain.CustomerTypeEnum;
import br.estudo.tw.exam.input.HotelInputException;
import br.estudo.tw.exam.input.HotelInputReader;
import br.estudo.tw.exam.tests.AbstractTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        assertNotNull(hotelInputReader.getCustomer());
        assertNotNull(hotelInputReader.getCustomer().getType());
        assertFalse(hotelInputReader.getCustomer().getReservations().isEmpty());

        String data1 = "16Mar2009(mon)";
        String data2 = "17Mar2009(tues)";
        String data3 = "18Mar2009(wed)";
    }

    @Test
    public void testReadInput1Customer() throws Exception {
        hotelInputReader.read(getExampleInput1());

        String customerType = hotelInputReader.getCustomer().getType().getDesction();
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

    @Test
    public void test() throws Exception {
        String str = "16Mar2009(mon)";

        Pattern parttern = Pattern.compile("(\\d{4})");
        Matcher matcher = parttern.matcher(str);

        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();

            String part = str.substring(start, end);
            System.out.println(part);
        }
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