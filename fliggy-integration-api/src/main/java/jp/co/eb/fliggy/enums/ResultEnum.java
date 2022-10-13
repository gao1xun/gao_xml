package jp.co.eb.fliggy.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {
    // @formatter:off
    SUCCESS(true, "OK", "请求成功"), 
    NO_INVENTORY(false, "NO_INVENTORY", "The rate has no enough inventory"), 
    HOTEL_INVALID(false, "HOTEL_INVALID", "The hotel is invalid, no more query again"), 
    ROOM_TYPE_INVALID(false, "ROOM_TYPE_INVALID", "The room type is invalid, no more query again"), 
    RATE_PLAN_INVALID(false, "RATE_PLAN_INVALID", "The rate plain is invalid, no more query again"), 
    RATE_EXPIRED(false, "RATE_EXPIRED", "The rate is expired, please validate again"), 
    PRICE_CHANGED(false, "PRICE_CHANGED", "The total price is changed, please validate again"), 
    DUPLICATE_CREATE(false, "DUPLICATE_CREATE", "Duplicate create, please check the order id"), 
    ALREADY_CANCELLED(false, "ALREADY_CANCELLED", "The order is cancelled, please do not cancel again"), 
    ORDER_NOT_FOUND(false, "ORDER_NOT_FOUND", "Can not find the order, please check the order id"), 
    AUTH_ERROR(false, "AUTH_ERROR", "Authorization failed"), 
    PARA_ERROR(false, "PARA_ERROR", "Parameters error"), 
    SYSTEM_EXCEPTION(false, "SYSTEM_EXCEPTION", "System error, please check the rate/order status again.Notice that, when this error occurs, the trade status should be unclear, the extra order query operations should be done.");

    // @formatter:on
    private Boolean success;
    private String code;
    private String message;
}
