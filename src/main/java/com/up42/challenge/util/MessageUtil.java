package com.up42.challenge.util;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

public class MessageUtil {

    private MessageUtil() {

    }

    private static final ReloadableResourceBundleMessageSource MESSAGE_SOURCE;
    private static final Locale EN_LOCALE = new Locale("en", "EN");

    static {
        MESSAGE_SOURCE = new ReloadableResourceBundleMessageSource();
        MESSAGE_SOURCE.setBasename("classpath:i18n/messages");
    }

    public static String getMessageWithoutParameter(final String messageCode) {
        return getMessageParameter(messageCode, new Object[0]);
    }

    public static String getMessageParameter(final String messageCode, Object[] objects) {
        return MESSAGE_SOURCE.getMessage(messageCode, objects, EN_LOCALE);
    }

}
