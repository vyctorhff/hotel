package br.estudo.tw.exam.service.imp;

import br.estudo.tw.exam.domain.*;
import br.estudo.tw.exam.service.HotelSelectService;
import br.estudo.tw.exam.service.ServiceException;
import br.estudo.tw.exam.util.LogUtil;
import com.google.common.collect.Ordering;
import org.springframework.stereotype.Component;
import sun.util.resources.cldr.so.CurrencyNames_so;

import java.util.ArrayList;

/**
 * Created by torugo on 01/09/15.
 */
@Component
public class HotelSelectServiceImp implements HotelSelectService {

    private static final LogUtil LOGGER =
            LogUtil.getLogger(HotelSelectServiceImp.class);

    private static final String HOTEL_RATE_NOT_FIND =
            "service.select_hotel.rate_not_final";

    private static final String HOTEL_RATE_NOT_MATCH =
            "service.select_hotel.rate_not_match";

    @Override
    public Hotel selectCheapest(Customer customer) throws ServiceException {
        double lakewood   = processLakewoodCost(customer);
        double bridgewood = processBridgewoodCost(customer);
        double ridgewood  = processRidgewoodCost(customer);

        if (hasTie(lakewood, bridgewood, ridgewood)) {
            if (lakewood == bridgewood) {
                return getRatesForBridgewood();

            } else if (bridgewood == ridgewood) {
                return getRatesForRidgewood();

            } else if (lakewood == ridgewood) {
                return getRatesForRidgewood();
            }
        }

        double cheapest = Ordering.natural().min(lakewood, bridgewood, ridgewood);
        if (cheapest == lakewood) {
            return getRatesForLakewood();

        } else if (cheapest == bridgewood) {
            return getRatesForBridgewood();

        } else if (cheapest == ridgewood) {
            return getRatesForRidgewood();
        }

        throw new ServiceException(HOTEL_RATE_NOT_MATCH);
    }

    private boolean hasTie(double value1, double value2, double value3) {
        return value1 == value2 || value2 == value3 || value1 == value3;
    }

    private double processLakewoodCost(Customer customer) throws ServiceException {
        Hotel lakewood = getRatesForLakewood();
        return processHotelCost(lakewood, customer);
    }

    private double processBridgewoodCost(Customer customer) throws ServiceException {
        Hotel bridgewood = getRatesForBridgewood();
        return processHotelCost(bridgewood, customer);
    }

    private double processRidgewoodCost(Customer customer) throws ServiceException {
        Hotel ridgewood = getRatesForRidgewood();
        return processHotelCost(ridgewood, customer);
    }

    private double processHotelCost(Hotel hotel, Customer customer) throws ServiceException {
        double total = 0.0;

        for (DateReservation dateReservation : customer.getReservations()) {

            for (DayRate dayRate: hotel.getCustomerRate().getDayRates()) {
                if (customer.getType().equals(dayRate.getType())) {

                    if (dateReservation.getWeekDay().isWeekDay()
                            && dayRate.getWeekDay().isWeekDay()) {
                        total += dayRate.getRate();
                        break;
                    }

                    if (dateReservation.getWeekDay().isWeekEnd()
                            && dayRate.getWeekDay().isWeekEnd()) {
                        total += dayRate.getRate();
                        break;
                    }
                }
            }
        }

        if (total == 0.0) {
            throw new ServiceException(HOTEL_RATE_NOT_FIND);
        }

        return total;
    }

    private Hotel getRatesForLakewood() {
        // TODO: criar dao para retornar os hoteis

        Hotel hotel = new Hotel();
        hotel.setName("Lakewood");
        hotel.setRating(3);

        ArrayList<DayRate> dayRates = new ArrayList<>();
        dayRates.add(createByRegular(WeekDayEnum.WEEKDAY, 110));
        dayRates.add(createByRegular(WeekDayEnum.WEEKEND, 90));
        dayRates.add(createByRewards(WeekDayEnum.WEEKDAY, 80));
        dayRates.add(createByRewards(WeekDayEnum.WEEKEND, 80));

        CustomerRate customerRate = new CustomerRate();
        customerRate.setDayRates(dayRates);

        hotel.setCustomerRate(customerRate);
        return hotel;
    }

    private Hotel getRatesForBridgewood() {
        // TODO: criar dao para retornar os hoteis

        Hotel hotel = new Hotel();
        hotel.setName("Bridgewood");
        hotel.setRating(4);

        ArrayList<DayRate> dayRates = new ArrayList<>();
        dayRates.add(createByRegular(WeekDayEnum.WEEKDAY, 160));
        dayRates.add(createByRegular(WeekDayEnum.WEEKEND, 60));
        dayRates.add(createByRewards(WeekDayEnum.WEEKDAY, 110));
        dayRates.add(createByRewards(WeekDayEnum.WEEKEND, 50));

        CustomerRate customerRate = new CustomerRate();
        customerRate.setDayRates(dayRates);

        hotel.setCustomerRate(customerRate);
        return hotel;
    }

    private Hotel getRatesForRidgewood() {
        // TODO: criar dao para retornar os hoteis

        Hotel hotel = new Hotel();
        hotel.setName("Ridgewood");
        hotel.setRating(5);

        ArrayList<DayRate> dayRates = new ArrayList<>();
        dayRates.add(createByRegular(WeekDayEnum.WEEKDAY, 220));
        dayRates.add(createByRegular(WeekDayEnum.WEEKEND, 150));
        dayRates.add(createByRewards(WeekDayEnum.WEEKDAY, 100));
        dayRates.add(createByRewards(WeekDayEnum.WEEKEND, 40));

        CustomerRate customerRate = new CustomerRate();
        customerRate.setDayRates(dayRates);

        hotel.setCustomerRate(customerRate);
        return hotel;
    }

    private DayRate createByRegular(WeekDayEnum weekDay, double rate) {
        DayRate dayRate = new DayRate();
        dayRate.setType(CustomerTypeEnum.REGULAR);
        dayRate.setRate(rate);
        dayRate.setWeekDay(weekDay);
        return dayRate;
    }
    private DayRate createByRewards(WeekDayEnum weekDay, double rate) {
        DayRate dayRate = new DayRate();
        dayRate.setType(CustomerTypeEnum.REWARDS);
        dayRate.setRate(rate);
        dayRate.setWeekDay(weekDay);
        return dayRate;
    }


}

