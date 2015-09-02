package br.estudo.tw.exam.service.imp;

import br.estudo.tw.exam.domain.Hotel;
import br.estudo.tw.exam.service.HotelSelectService;
import br.estudo.tw.exam.service.ServiceException;
import br.estudo.tw.exam.util.LogUtil;
import org.springframework.stereotype.Component;

/**
 * Created by torugo on 01/09/15.
 */
@Component
public class HotelSelectServiceImp implements HotelSelectService {

    private static final LogUtil LOGGER =
            LogUtil.getLogger(HotelSelectServiceImp.class);

    @Override
    public Hotel selectCheapest() throws ServiceException {
        return null;
    }
}
