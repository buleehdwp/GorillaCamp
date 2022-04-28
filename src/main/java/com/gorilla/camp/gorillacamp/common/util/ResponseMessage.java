package com.gorilla.camp.gorillacamp.common.util;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResponseMessage {
    private boolean error;
    private String message;
    private Object data;

    public static ResponseMessage responseMap(Object data) throws Exception {
        ResponseMessage s = new ResponseMessage();
        try{
            s.setError(false);
            s.setMessage("예약성공");
            s.setData(data);
            return s;
        }catch (Exception e){
            e.printStackTrace();
            s.setError(false);
            s.setMessage("예약실패");
            s.setData(data);
            return s;
        }
    }
}
