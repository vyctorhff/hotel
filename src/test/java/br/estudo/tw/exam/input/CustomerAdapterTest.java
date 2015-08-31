package br.estudo.tw.exam.input;

import br.estudo.tw.exam.domain.*;
import br.estudo.tw.exam.tests.AbstractTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by torugo on 31/08/15.
 */
public class CustomerAdapterTest extends AbstractTests {

    @Autowired
    private HotelInputReader hotelInputReader;

    @Test(expected = HotelInputException.class)
    public void testDoAdapterWithEmptyInput() throws Exception {
        CustomerAdapter adapter = new CustomerAdapter(hotelInputReader);
        adapter.doAdapter();
    }

    @Test
    public void testDoAdapterWithRegularCustomer() throws Exception {
        String input = super.loadInputFromFile(super.FILE_INPUT_1);
        hotelInputReader.read(input);

        CustomerAdapter adapter = new CustomerAdapter(hotelInputReader);
        adapter.doAdapter();

        Customer customer = adapter.getCustomer();
        assertNotNull(customer);
        assertNotNull(customer.getType());
        assertEquals(CustomerTypeEnum.REGULAR, customer.getType());

        assertNotNull(customer.getReservations());
        assertEquals(3, customer.getReservations().size());

        // verify if the date reservation match with the text
        DateReservation dateReservation = customer.getReservations().iterator().next();
        assertEquals(16, dateReservation.getDay());
        assertEquals(MonthEnum.MARCH, dateReservation.getMoth());
        assertEquals(2009, dateReservation.getYear());
        assertEquals(WeekDayEnum.MONDAY, dateReservation.getWeekDay());
    }

    @Test
    public void testDoAdapterWithRewardCustomer() throws Exception {
        fail();
    }

    @Test
    public void testDoAdapterWithAllWeekDays() throws Exception {
        fail();
    }

    @Test
    public void testDoAdapterWithAllMonths() throws Exception {
        fail();
    }
}