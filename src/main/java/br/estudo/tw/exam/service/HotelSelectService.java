package br.estudo.tw.exam.service;

import br.estudo.tw.exam.domain.Customer;
import br.estudo.tw.exam.domain.Hotel;

/**
 * Created by torugo on 01/09/15.
 */
public interface HotelSelectService {

    Hotel selectCheapest(Customer customer) throws ServiceException;
}
