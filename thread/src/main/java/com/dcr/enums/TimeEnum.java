package com.dcr.enums;

/**
 * @descriptions:
 * @author: dcr
 * @date: 2021/12/15 16:30
 */
public enum TimeEnum {

    ONE(1, "秦"),
    TWO(2, "汉"),
    THREE(3, "唐"),
    FOUR(4, "宋"),
    FIVE(5, "元"),
    SIX(6, "明"),
    SEVEN(7, "清");

    private Integer code;

    private String value;

    TimeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 一个静态实例
     * @param code
     * @return
     */
    public static TimeEnum getInstance(Integer code){

        for (TimeEnum timeEnum : TimeEnum.values()) {
            if(code.equals(timeEnum.getCode())) {
                return timeEnum;
            }
        }
        return null;
    }
}
