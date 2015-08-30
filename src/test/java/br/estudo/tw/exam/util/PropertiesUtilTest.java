package br.estudo.tw.exam.util;

import br.estudo.tw.exam.input.HotelInputReader;
import br.estudo.tw.exam.input.imp.BasicInputReader;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Created by torugo on 30/08/15.
 */
public class PropertiesUtilTest {

    @Test
    public void testGetMessage() throws Exception {
        String msg = PropertiesUtil.getMessage(HotelInputReader.ENTRADA_INVALIDA);
        assertNotNull(msg);
        assertFalse(msg.trim().isEmpty());
    }

    @Test
    public void testGetMessageInvalid() throws Exception {
        String message = PropertiesUtil.getMessage("abc");
        assertNull(message);
    }
}