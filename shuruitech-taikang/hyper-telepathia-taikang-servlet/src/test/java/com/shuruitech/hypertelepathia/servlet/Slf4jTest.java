package com.shuruitech.hypertelepathia.servlet;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jTest {
    @Test
    public void testStdout() {
        Logger logger = LoggerFactory.getLogger(Slf4jTest.class);
        for (int i = 0; i < 10; i++) {
            logger.info("你好");
        }
    }

}
