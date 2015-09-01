package br.estudo.tw.exam.input;

import br.estudo.tw.exam.domain.*;
import br.estudo.tw.exam.tests.AbstractTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Created by torugo on 31/08/15.
 */
public class CustomerAdapterTest extends AbstractTests {

    @Autowired
    private HotelInputReader hotelInputReader;

    @Test(expected = HotelInputException.class)
    public void testDoAdapterWithEmptyInput() throws Exception {
        hotelInputReader.clean();

        CustomerAdapter adapter = new CustomerAdapter(hotelInputReader);
        adapter.doAdapter();
    }

    @Test
    public void testDoAdapterWithRegularCustomer() throws Exception {
        Customer customer = getCustomerByFile(super.FILE_CUSTOMER_REGULAR);

        assertNotNull(customer);
        assertNotNull(customer.getType());
        assertEquals(CustomerTypeEnum.REGULAR, customer.getType());
    }

    @Test
    public void testDoAdapterWithRewardCustomer() throws Exception {
        Customer customer = getCustomerByFile(super.FILE_CUSTOMER_REWARDS);

        assertNotNull(customer);
        assertNotNull(customer.getType());
        assertEquals(CustomerTypeEnum.REWARDS, customer.getType());
    }

    @Test
    public void testDoAdapterWithAllWeekDays() throws Exception {
        Customer customer = getCustomerByFile(super.FILE_INPUT_WEEK_DAYS);

        assertEquals(WeekDayEnum.values().length, customer.getReservations().size());

        // The order of WeekDayEnum must bem the same that of the file.
        Iterator<DateReservation> iterator = customer.getReservations().iterator();
        for (WeekDayEnum weekDay : WeekDayEnum.values()) {
            if (iterator.hasNext()) {
                WeekDayEnum weekDay1 = iterator.next().getWeekDay();
                assertEquals(weekDay, weekDay1);
            } else {
                fail();
            }
        }
    }

    @Test
    public void testDoAdapterWithAllMonths() throws Exception {
        Customer customer = getCustomerByFile(super.FILE_INPUT_MONTHS);
        fail();
    }

    private Customer getCustomerByFile(String file) throws IOException, HotelInputException {
        String input = super.loadInputFromFile(file);
        hotelInputReader.read(input);

        CustomerAdapter adapter = new CustomerAdapter(hotelInputReader);
        adapter.doAdapter();

        return adapter.getCustomer();
    }
}