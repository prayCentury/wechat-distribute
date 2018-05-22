package com.pray.model;

/**
 * Package Name: com.pray.model
 * Created by Liu xi on 2018/5/20.
 * Version: V1.0
 * Des:
 */
public class Result {
    private Integer code;
    private String note;

    public Integer getCode() {
        return code;
    }

    public  Result(int code) {
        this.code = code;
    }


    public void setCode(Integer code) {
        this.code = code;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
