package com.hepsiburada.test.driver;

import org.apache.commons.lang3.*;
import org.slf4j.*;


public class FindOS {

    private static Logger logger = LoggerFactory.getLogger(FindOS.class);
    public static String getOperationSystemName() {

        logger.info(System.getProperty("os.name"));
        if(SystemUtils.IS_OS_WINDOWS) {
            return "WINDOWS";
        } else if (SystemUtils.IS_OS_MAC) {
            return "MAC";
        } else if (SystemUtils.IS_OS_LINUX) {
            return "LINUX";
        } else
            return "null";
    }

}
