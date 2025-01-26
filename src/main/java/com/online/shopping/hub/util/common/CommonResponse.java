package com.online.shopping.hub.util.common;

import lombok.Data;

/**
 * This class is to handle API response of all.
 *
 * @param <T> API response data type
 */
@Data
public class CommonResponse<T> {
    private String statusCode;
    private String message;
    private T data;

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NL = System.lineSeparator();
        result.append("statusCode: ").append(statusCode).append(NL);
        result.append("message: ").append(message).append(NL);
        if (null != data)
            result.append("data: ").append(data).append(NL);
        return result.toString();
    }

}

