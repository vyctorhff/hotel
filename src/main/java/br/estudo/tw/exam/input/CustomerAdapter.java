package br.estudo.tw.exam.input;

import br.estudo.tw.exam.domain.*;
import br.estudo.tw.exam.util.LogUtil;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by torugo on 30/08/15.
 */
public class CustomerAdapter {

    private static final LogUtil LOGGER =
            LogUtil.getLogger(CustomerAdapter.class);

    private static final String RESERVATION_DAY_EXPRESSION =
            "(^\\d{2})";

    private static final String RESERVATION_MOTH_EXPRESSION =
            "([A-Z][a-z]{2})";

    private static final String RESERVATION_YEAR_EXPRESSION =
            "(\\d{4})";

    private static final String RESERVATION_WEEK_DAY_EXPRESSION =
            "([a-z]{3})";

    private static final String CUSTOMER_TYPE_INVALID =
            "input.customer_type_invalid";

    private static final String DATES_INVALID =
            "input.dates_invalid";

    private CustomerTypeEnum customerType;
    private List<DateReservation> reservations;
    private HotelInputReader hotelInputReader;

    public CustomerAdapter(HotelInputReader hoteReader) {
        hotelInputReader = hoteReader;
        reservations = new ArrayList<>();
    }

    public void doAdapter() throws HotelInputException {
        converterToCustomerType();
        hotelInputReader.getDates().forEach(
                strDate -> converterToReservation(strDate)
        );
    }

    private void converterToCustomerType() throws HotelInputException {
        String strCustomerType = hotelInputReader.getCustomerType();

        if (Objects.isNull(strCustomerType) || StringUtils.isEmpty(strCustomerType)) {
            throw new HotelInputException(CUSTOMER_TYPE_INVALID);
        }

        customerType = CustomerTypeEnum.getCustomerTypByString(
                        hotelInputReader.getCustomerType());

        if (Objects.isNull(customerType)) {
            throw new HotelInputException(CUSTOMER_TYPE_INVALID);
        }
    }

    private void converterToReservation(String strReservation) {
        DateReservation reservation = new DateReservation();
        reservation.setDay(converterToDay(strReservation));
        reservation.setYear(converterToYear(strReservation));
        reservation.setMoth(converterToMoth(strReservation));
        reservation.setWeekDay(converteToWeekDay(strReservation));

        reservations.add(reservation);
    }

    private WeekDayEnum converteToWeekDay(String strReservation) {
        String strWeekDay = getStringByExpression(RESERVATION_WEEK_DAY_EXPRESSION, strReservation);
        return WeekDayEnum.getWeekDayByString(strWeekDay);
    }

    private MothEnum converterToMoth(String strReservation) {
        String strMoth = getStringByExpression(RESERVATION_MOTH_EXPRESSION, strReservation);
        return MothEnum.getMothByString(strMoth);
    }

    private int converterToYear(String strReservation) {
        String strYear = getStringByExpression(RESERVATION_YEAR_EXPRESSION, strReservation);
        return Integer.parseInt(strYear);
    }

    private int converterToDay(String strReservation) {
        String strDay = getStringByExpression(RESERVATION_DAY_EXPRESSION, strReservation);
        return Integer.parseInt(strDay);
    }

    private String getStringByExpression(String expression, String input) {
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            int indexStart = matcher.start();
            int indexEnd   = matcher.end();

            return input.substring(indexStart, indexEnd);
        }
        return null;
    }

    public Customer getCustomer() {
        Customer customer = new Customer();
        customer.setType(customerType);
        customer.setReservations(reservations);
        return customer;
    }
}
