package com.productCatalog.dtos;

public class ExceptionDto {
    private String message;
    private String resolutionMethod;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResolutionMethod() {
        return resolutionMethod;
    }

    public void setResolutionMethod(String resolutionMethod) {
        this.resolutionMethod = resolutionMethod;
    }
}
