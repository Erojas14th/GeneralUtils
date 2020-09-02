package com.erojas;

import com.erojas.message.HtmlMessageUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HtmlMessageUtilsApplicationTests {
    private static Logger logger = LoggerFactory.getLogger(HtmlMessageUtilsApplicationTests.class);

    @Value("${message}")
    private String message;

    @Test
    public void build() throws IOException {

        StringBuilder html = new StringBuilder();

        String newMessage = HtmlMessageUtils.getMessage(message);
        logger.info(" {}", newMessage);

        html.append("<html>").append("<body>")
                .append(newMessage).append("</body>")
                .append("</html>");

        IOUtils.write(html.toString(), new FileOutputStream("message.html"));


    }
}
