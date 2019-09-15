package com.vainglory.utils;

/*
 * wgy 2019/9/12 16:26
 * 佛祖保佑，永无BUG!
 */
public class WeiXinResult {

    /**
     * result : {"appid":"wx632c8f211f8122c6","bank_type":"CFT","cash_fee":"1","fee_type":"CNY","is_subscribe":"Y","mch_id":"1497984412","nonce_str":"1631171182","openid":"oUuptwrJudIfdihz1Z_T1AciMahs","out_trade_no":"1221ea762d54496e83a33c9dab72f320","result_code":"SUCCESS","return_code":"SUCCESS","sign":"5C7314AA4EB21772B42DBBCD65E56ACF","time_end":"20180207163125","total_fee":"1","trade_type":"NATIVE","transaction_id":"4200000065201802078895888133"}
     * type : 0
     */

    private ResultBean result;
    private int type;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static class ResultBean {
        /**
         * appid : wx632c8f211f8122c6
         * bank_type : CFT
         * cash_fee : 1
         * fee_type : CNY
         * is_subscribe : Y
         * mch_id : 1497984412
         * nonce_str : 1631171182
         * openid : oUuptwrJudIfdihz1Z_T1AciMahs
         * out_trade_no : 1221ea762d54496e83a33c9dab72f320
         * result_code : SUCCESS
         * return_code : SUCCESS
         * sign : 5C7314AA4EB21772B42DBBCD65E56ACF
         * time_end : 20180207163125
         * total_fee : 1
         * trade_type : NATIVE
         * transaction_id : 4200000065201802078895888133
         */

        private String appid;
        private String bank_type;
        private String cash_fee;
        private String fee_type;
        private String is_subscribe;
        private String mch_id;
        private String nonce_str;
        private String openid;
        private String out_trade_no;
        private String result_code;
        private String return_code;
        private String sign;
        private String time_end;
        private String total_fee;
        private String trade_type;
        private String transaction_id;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getBank_type() {
            return bank_type;
        }

        public void setBank_type(String bank_type) {
            this.bank_type = bank_type;
        }

        public String getCash_fee() {
            return cash_fee;
        }

        public void setCash_fee(String cash_fee) {
            this.cash_fee = cash_fee;
        }

        public String getFee_type() {
            return fee_type;
        }

        public void setFee_type(String fee_type) {
            this.fee_type = fee_type;
        }

        public String getIs_subscribe() {
            return is_subscribe;
        }

        public void setIs_subscribe(String is_subscribe) {
            this.is_subscribe = is_subscribe;
        }

        public String getMch_id() {
            return mch_id;
        }

        public void setMch_id(String mch_id) {
            this.mch_id = mch_id;
        }

        public String getNonce_str() {
            return nonce_str;
        }

        public void setNonce_str(String nonce_str) {
            this.nonce_str = nonce_str;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getOut_trade_no() {
            return out_trade_no;
        }

        public void setOut_trade_no(String out_trade_no) {
            this.out_trade_no = out_trade_no;
        }

        public String getResult_code() {
            return result_code;
        }

        public void setResult_code(String result_code) {
            this.result_code = result_code;
        }

        public String getReturn_code() {
            return return_code;
        }

        public void setReturn_code(String return_code) {
            this.return_code = return_code;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getTime_end() {
            return time_end;
        }

        public void setTime_end(String time_end) {
            this.time_end = time_end;
        }

        public String getTotal_fee() {
            return total_fee;
        }

        public void setTotal_fee(String total_fee) {
            this.total_fee = total_fee;
        }

        public String getTrade_type() {
            return trade_type;
        }

        public void setTrade_type(String trade_type) {
            this.trade_type = trade_type;
        }

        public String getTransaction_id() {
            return transaction_id;
        }

        public void setTransaction_id(String transaction_id) {
            this.transaction_id = transaction_id;
        }
    }
}
