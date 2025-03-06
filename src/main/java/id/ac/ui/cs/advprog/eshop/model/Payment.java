package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;

import java.util.Map;

@Getter
public class Payment {
    String id;
    String method;
    String status;

    private Map<String, String> paymentData;

    public Payment(String id, String method, Map<String, String> paymentData) {
        this.id = id;
        this.setMethod(method);
        this.status = "CHECKING_PAYMENT";
        this.paymentData = paymentData;

        if ("CASH".equals(method)) {
            if (!paymentData.containsKey("address") || !paymentData.containsKey("deliveryFee")) {
                throw new IllegalArgumentException("Missing address or deliveryFee for CASH method");
            }
        }
        else if ("VOUCHER".equals(method)) {
            String voucherCode = paymentData.get("VoucherCode");
            if (voucherCode == null
                    || voucherCode.length() != 16
                    || !voucherCode.startsWith("ESHOP")
                    || voucherCode.replaceAll("[^0-9]", "").length() != 8) {
                this.status = "REJECTED";
            } else {
                this.status = "SUCCESS";
            }
        }
    }

    public Payment(String id, String method,String status, Map<String, String> paymentData){
        this(id, method, paymentData);
        this.setStatus(status);
    }

    public void setStatus(String status){
        if (status.equals("SUCCESS") || status.equals("REJECTED")){
            this.status = status;
        }
        else{
            throw new IllegalArgumentException();
        }
    }
    public void setMethod(String method){
        if (method.equals("VOUCHER") || method.equals("CASH")){
            this.method = method;
        }
        else{
            throw new IllegalArgumentException();
        }
    }
}