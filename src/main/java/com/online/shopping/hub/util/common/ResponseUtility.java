package com.online.shopping.hub.util.common;

public class ResponseUtility {
    /**
     *
     * @param <T>        Generic data type
     * @param statusCode API response status(success:200, created:201,
     *                   internal_server_error:500)
     * @param data       API response data
     * @param message    API response message
     * @return Accumulated API response
     */
    public static <T> CommonResponse<T> getResponse(String statusCode, T data, String message) {
        CommonResponse<T> commonResponse = new CommonResponse<T>();
        commonResponse.setStatusCode(statusCode);
        commonResponse.setMessage(message);
        commonResponse.setData(data);
        return commonResponse;
    }
}
