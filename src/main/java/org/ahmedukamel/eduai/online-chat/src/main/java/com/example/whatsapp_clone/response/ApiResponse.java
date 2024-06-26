package com.example.whatsapp_clone.response;

public class ApiResponse {

    private String message;
    private boolean status;

    public ApiResponse(String message,boolean status){
        super();
        this.message = message;
        this.status = status;
    }

}
