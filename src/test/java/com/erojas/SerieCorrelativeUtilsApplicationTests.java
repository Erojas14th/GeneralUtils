package com.erojas;

import com.erojas.serie.SerieCorrelativeUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SerieCorrelativeUtilsApplicationTests {

    private String serieCorrelative = "F000-00000001";
    private String newSerieCorrelative = null;
    @Before
    public void setup(){

    }

    @Test
    public void build() {
        String newSerieCorrelative = SerieCorrelativeUtils.build(serieCorrelative);
        assertEquals("F000-00000002",newSerieCorrelative);
        assertNotEquals("F000-00000003",newSerieCorrelative);
    }
}
