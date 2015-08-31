package br.estudo.tw.exam.input.imp;

import br.estudo.tw.exam.domain.*;
import br.estudo.tw.exam.input.HotelInputException;
import br.estudo.tw.exam.input.HotelInputReader;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by n5dc on 29/04/2015.
 */
@Component
public class BasicInputReader implements HotelInputReader {

    private static final String RESERVATION_EXPRESSION =
            "(\\d{2}\\w{3}\\d{4}\\(\\w{3}\\))";

    private static final String RESERVATION_DAY_EXPRESSION =
            "(^\\d{2})";

    private static final String RESERVATION_MOTH_EXPRESSION =
            "([A-Z][a-z]{2})";

    private static final String RESERVATION_YEAR_EXPRESSION =
            "(\\d{4})";

    private static final String RESERVATION_WEEK_DAY_EXPRESSION =
            "([a-z]{3})";

    private CustomerTypeEnum customerType;
    private List<DateReservation> reservations;

    @Override
    public void read(String input) throws HotelInputException {
        if (StringUtils.isEmpty(input) || StringUtils.isBlank(input)) {
            throw new HotelInputException(ENTRADA_INVALIDA);
        }

        readCustomer(input);
        readDates(input);
    }

    private void readCustomer(String input) throws HotelInputException {
        for (CustomerTypeEnum customerTypeEnum : CustomerTypeEnum.values()) {
            String description = customerTypeEnum.getDesction().toLowerCase();

            if (input.toLowerCase().contains(description)) {
                customerType = customerTypeEnum;
            }
        }

        if (Objects.isNull(customerType)) {
            throw new HotelInputException(MSG_ERROR_CUSTOMER_TYPE);
        }
    }

    private void readDates(String input) {
        Pattern pattern = Pattern.compile(RESERVATION_EXPRESSION);
        Matcher matcher = pattern.matcher(input);

        String strReservation;
        reservations = new ArrayList<DateReservation>();

        while (matcher.find()) {
            int indexStart = matcher.start();
            int indexEnd   = matcher.end();

            strReservation = input.substring(indexStart, indexEnd);
            reservations.add(converterToReservation(strReservation));
        }
    }

    private DateReservation converterToReservation(String strReservation) {
        DateReservation reservation = new DateReservation();
        reservation.setDay(converterToDay(strReservation));
        reservation.setYear(converterToYear(strReservation));
        reservation.setMoth(converterToMoth(strReservation));
        reservation.setWeekDay(converteToWeekDay(strReservation));
        return reservation;
    }

    private WeekDayEnum converteToWeekDay(String strReservation) {
        Pattern pattern = Pattern.compile(RESERVATION_WEEK_DAY_EXPRESSION);
        Matcher matcher = pattern.matcher(strReservation);

        while (matcher.find()) {
            int indexStart = matcher.start();
            int indexEnd   = matcher.end();

            String strWeekDay = strReservation.substring(indexStart, indexEnd);
            return WeekDayEnum.getWeekDayByString(strWeekDay);
        }

        return null;
    }

    private MothEnum converterToMoth(String strReservation) {
        Pattern pattern = Pattern.compile(RESERVATION_MOTH_EXPRESSION);
        Matcher matcher = pattern.matcher(strReservation);

        while (matcher.find()) {
            int indexStart = matcher.start();
            int indexEnd   = matcher.end();

            String strMoth = strReservation.substring(indexStart, indexEnd);
            return MothEnum.getMothByString(strMoth);
        }

        return null;
    }

    private int converterToYear(String strReservation) {
        Pattern pattern = Pattern.compile(RESERVATION_YEAR_EXPRESSION);
        Matcher matcher = pattern.matcher(strReservation);

        while (matcher.find()) {
            int indexStart = matcher.start();
            int indexEnd   = matcher.end();

            String strDay = strReservation.substring(indexStart, indexEnd);
            return Integer.parseInt(strDay);
        }

        return 0;
    }

    private int converterToDay(String strReservation) {
        Pattern pattern = Pattern.compile(RESERVATION_DAY_EXPRESSION);
        Matcher matcher = pattern.matcher(strReservation);

        while (matcher.find()) {
            int indexStart = matcher.start();
            int indexEnd   = matcher.end();

            String strDay = strReservation.substring(indexStart, indexEnd);
            return Integer.parseInt(strDay);
        }

        return 0;
    }

    @Override
    public Customer getCustomer() {
        Customer customer = new Customer();
        customer.setType(customerType);
        customer.setReservations(reservations);
        return customer;
    }
}
