package com.erojas.serie;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerieCorrelativeUtils {

    private static Logger logger = LoggerFactory.getLogger(SerieCorrelativeUtils.class);

    public static String build(String serieCorrelativo) {

        StringBuilder serieBuilder = new StringBuilder();
        StringBuilder correlativoBuilder = new StringBuilder();

        try {
            String[] serieCorrelativoTemp = serieCorrelativo.split("-");
            String serie = String.valueOf(serieCorrelativoTemp[0]);
            String correlativo = String.valueOf(serieCorrelativoTemp[1]);

            Integer correlativoInteger = Integer.valueOf(correlativo) + 1;
            Integer correlativoLength = String.valueOf(correlativoInteger).length();

            for (int i = correlativoLength; i < 8; i++) {
                correlativoBuilder.append("0");
            }
            correlativoBuilder.append(correlativoInteger);
            serieBuilder.append(serie).append("-").append(correlativoBuilder);

        } catch (Exception e) {
            logger.info(e.getMessage(), e);
        } finally {
            if (StringUtils.isEmpty(serieBuilder.toString())) {
                serieBuilder.append(serieCorrelativo);
            }
        }

        return serieBuilder.toString();
    }
}
