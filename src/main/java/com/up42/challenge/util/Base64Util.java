package com.up42.challenge.util;

import com.up42.challenge.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.util.Base64Utils;

public class Base64Util {
    private Base64Util() {
    }

    public static byte[] convertToImage(String imageString) {
        byte[] imageByte;
        try {
            imageByte = Base64Utils.decodeFromString((imageString));
        } catch (Exception e) {
            throw new BusinessException(
                    MessageUtil.getMessageWithoutParameter("CANNOT.CONVERT.QUICKLOOK"),
                    HttpStatus.UNPROCESSABLE_ENTITY.value());
        }
        return imageByte;
    }
}
