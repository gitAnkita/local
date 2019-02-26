package com.paytm.local.common;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
enum ONLINE_REFUND_TXN_STATUS {
    INITIATED, PROCESSING, SUCCESS, FAILURE;

    public static boolean isTerminatingState(String status) {
        if (SUCCESS.name().equalsIgnoreCase(status) || FAILURE.name().equalsIgnoreCase(status))
            return true;
        return false;
    }

}

@Getter
enum ONLINE_REFUND_RESPONSE {

    INVALID_REFUND_ID("INT-2000", "INVALID REFUND ID.", ONLINE_REFUND_TXN_STATUS.FAILURE),
    INVALID_REFUND_AMOUNT("INT-2001", "INVALID REFUND AMOUNT.", ONLINE_REFUND_TXN_STATUS.FAILURE),
    INVALID_ORG_TXN_ID("INT-2002", "INVALID ORIGINAL TXN ID.", ONLINE_REFUND_TXN_STATUS.FAILURE),
    INVALID_NOTE("INT-2003", "INVALID TXN NOTE.", ONLINE_REFUND_TXN_STATUS.FAILURE),
    BLANK_CALLBACK_URL("INT-2004", "BLANK CALLBACK URL.", ONLINE_REFUND_TXN_STATUS.FAILURE),
    INVALID_JWT_TOKEN("INT-2005", "INVALID JWT TOKEN.", ONLINE_REFUND_TXN_STATUS.FAILURE),
    INVALID_CHANNEL_CODE("INT-2006", "INVALID CHANNEL CODE.", ONLINE_REFUND_TXN_STATUS.FAILURE),

    DUPLICATE_REFUND_ID("INT-2007", "DUPLICATE REFUND ID.", ONLINE_REFUND_TXN_STATUS.FAILURE),
    REFUND_AMOUNT_EXCEEDS_ORG_TXN_AMOUNT("INT-2008", "TOTAL REFUND AMOUNT EXCEEDS ORIGINAL TXN AMOUNT.", ONLINE_REFUND_TXN_STATUS.FAILURE),
    ORG_TXN_NOT_SUCCESS("INT-2009", "ORIGINAL TXN NOT SUCCESS.", ONLINE_REFUND_TXN_STATUS.FAILURE),
    ORG_TXN_NOT_EXISTS("INT-2010", "ORIGINAL TXN NOT EXISTS.", ONLINE_REFUND_TXN_STATUS.FAILURE),
    INTERNAL_SYSTEM_ERROR("INT-2011", "INTERNAL SYSTEM ERROR.", ONLINE_REFUND_TXN_STATUS.FAILURE),
    PAY_FAILED("INT-2012", "PAYMENT REQUEST WAS UNSUCCESSFUL.", ONLINE_REFUND_TXN_STATUS.FAILURE),
    USER_INACTIVE("INT-2013", "USER PROFILE IS INACTIVE.", ONLINE_REFUND_TXN_STATUS.FAILURE),
    ONLINE_REFUND_TURNED_OFF("INT-2014", "ONLINE REFUND TEMPORARILY DISABLED.", ONLINE_REFUND_TXN_STATUS.FAILURE),

    INITATED_RESPONSE("0000", "Refund Transaction is being processed.", ONLINE_REFUND_TXN_STATUS.INITIATED),
    SUCCESS_RESPONSE("0001", "Refund Transaction is success.", ONLINE_REFUND_TXN_STATUS.SUCCESS),

    INVALID_TXN_STATUS("INT-2015", "INVALID_TXN_STATUS", ONLINE_REFUND_TXN_STATUS.FAILURE),

    AMOUNT_NULL("INT-2016", "AMOUNT_NULL", ONLINE_REFUND_TXN_STATUS.FAILURE),
    PAYEE_NAME_NULL("INT-2017", "PAYEE_NAME_NULL", ONLINE_REFUND_TXN_STATUS.FAILURE),
    PAYER_VA_NULL("INT-2018", "PAYER_VA_NULL", ONLINE_REFUND_TXN_STATUS.FAILURE),
    MERCHANT_TRANSACTION_ID_NULL("INT-2019", "MERCHANT_TRANSACTION_ID_NULL", ONLINE_REFUND_TXN_STATUS.FAILURE),
    BUSINESS_TYPE_NULL("INT-2020", "BUSINESS_TYPE_NULL", ONLINE_REFUND_TXN_STATUS.FAILURE),
    PAYEE_ACCOUNT_IFSC_NULL("INT-2021", "PAYEE_ACCOUNT_IFSC_NULL", ONLINE_REFUND_TXN_STATUS.FAILURE),
    PAYEE_ACCOUNT_DETAILS("INT-2022", "PAYEE_ACCOUNT_DETAILS", ONLINE_REFUND_TXN_STATUS.FAILURE),
    PAYER_VA_LENGTH("INT-2023", "PAYER_VA_LENGTH", ONLINE_REFUND_TXN_STATUS.FAILURE),
    MOBILE_NUMBER_LENGTH("INT-2024", "MOBILE_NUMBER_LENGTH", ONLINE_REFUND_TXN_STATUS.FAILURE),
    PAYEE_NAME_LENGTH("INT-2025", "PAYEE_NAME_LENGTH", ONLINE_REFUND_TXN_STATUS.FAILURE),
    DUPLICATE_EXT_TXN_ID("INT-2026", "DUPLICATE_EXT_TXN_ID", ONLINE_REFUND_TXN_STATUS.FAILURE),
    INVALID_BUSINESS_TYPE("INT-2027", "INVALID_BUSINESS_TYPE", ONLINE_REFUND_TXN_STATUS.FAILURE),
    INVALID_ORDERID("INT-2028", "INVALID_ORDERID", ONLINE_REFUND_TXN_STATUS.FAILURE),
    INVALID_EXT_TXN_SOURCE("INT-2029", "INVALID_EXT_TXN_SOURCE", ONLINE_REFUND_TXN_STATUS.FAILURE),
    INVALID_EXT_TXN_ID("INT-2030", "INVALID_EXT_TXN_ID", ONLINE_REFUND_TXN_STATUS.FAILURE)
    ;

    private static Map<String, ONLINE_REFUND_RESPONSE> responseCodes = new HashMap<>();
    private static Map<String, ONLINE_REFUND_RESPONSE> responseConstants = new HashMap<>();

    ONLINE_REFUND_RESPONSE(String code, String message, ONLINE_REFUND_TXN_STATUS status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    static {
        ONLINE_REFUND_RESPONSE[] refundResponseList = values();
        for (ONLINE_REFUND_RESPONSE response : refundResponseList) {
            responseCodes.put(response.getCode(), response);
            responseConstants.put(response.name(), response);
        }
    }

    private String code;

    private String message;

    private ONLINE_REFUND_TXN_STATUS status;

    public static ONLINE_REFUND_RESPONSE getRefundResponseFromCode(String responseCode) {
        return responseCodes.get(responseCode);
    }

    public static ONLINE_REFUND_RESPONSE getRefundResponseFromConstant(String responseCode) {
        return responseConstants.get(responseCode);
    }

}


public class TestUtils {

    public static void main(String[] args) {

        StringBuffer str = new StringBuffer();

        for (ONLINE_REFUND_RESPONSE refundEnum : ONLINE_REFUND_RESPONSE.values()) {
            str.append("\nINSERT INTO response_mapper (response_constant,type,resp_code,resp_msg,description) VALUES (");
            str.append("'"+refundEnum.name()+"',");
            str.append("'EXTERNAL',");
            str.append("'"+refundEnum.getCode()+"',");
            str.append("'"+refundEnum.getMessage()+"',");
            str.append("'"+refundEnum.getMessage()+"');");
        }

        System.out.println(str.toString());
    }
}
