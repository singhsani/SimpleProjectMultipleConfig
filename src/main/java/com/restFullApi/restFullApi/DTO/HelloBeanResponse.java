package com.restFullApi.restFullApi.DTO;

import lombok.Data;

@Data
public class HelloBeanResponse {
    private String message;
    public HelloBeanResponse(String message){
        this.message=message;
    }

}
