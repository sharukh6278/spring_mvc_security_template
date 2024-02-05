package org.spring.security.model;

public class FridayResponse {
    private Object data;
    private String message;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public FridayResponse(Object data) {
        this.data = data;
    }

    public FridayResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "FridayResponse{" +
                "data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
