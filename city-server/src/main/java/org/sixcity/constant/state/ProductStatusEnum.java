package org.sixcity.constant.state;

public enum ProductStatusEnum {

    ORDER(9, "已下单"),
    WAIT_PAYS(10, "待付款"),
    CANCELED(11, "已取消"),
    WAIT_DELIVERY(12, "等待官网发货"),
    DELIVERED(13, "官方已发货"),
    TRANSPORT_IN(14, "转运中(可结算)"),
    RECEIVED(15, "已收货（可结算）");

    private int code;
    private String text;

    private ProductStatusEnum(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public static String getText(int code) {
        for (ProductStatusEnum status : ProductStatusEnum.values()) {
            if (status.getCode() == code) {
                return status.getText();
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
