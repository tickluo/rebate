package org.sixcity.constant.state;


public final class ProductStatusConst {

    // 下单
    public static final Integer ORDER = 9;
    // 待付款
    public static final Integer WAIT_PAYS = 10;
    // 已取消
    public static final Integer CANCELED = 11;
    // 等待官网发货
    public static final Integer WAIT_DELIVERY = 12;
    // 官方已发货
    public static final Integer DELIVERED = 13;
    // 转运中(可结算)
    public static final Integer TRANSPORT_IN = 14;
    // 已收货（可结算）
    public static final Integer RECEIVED = 15;
}

