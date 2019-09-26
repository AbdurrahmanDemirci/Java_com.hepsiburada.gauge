package com.hepsiburada.test.utils;

import org.apache.commons.lang3.exception.*;
import org.apache.log4j.*;
import java.io.*;
import java.util.*;


public class PropertyUtils {

    private static final Logger logger = Logger.getLogger(PropertyUtils.class);
    public static Properties properties;

    public static void loadProperties() {

        try {

            Properties props = new Properties();
            props.load(new FileInputStream("./src/test/resources/log4j.properties"));
            PropertyConfigurator.configure(props);
        } catch (Exception e) {
            logger.error("Load Properties Failure | Reason: " + ExceptionUtils.getMessage(e));
        }
    }
}