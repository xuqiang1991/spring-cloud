package com.cxqiang.utils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenAuthTokenAppQueryRequest;
import com.alipay.api.request.AlipayOpenAuthTokenAppRequest;
import com.alipay.api.response.AlipayOpenAuthTokenAppQueryResponse;
import com.alipay.api.response.AlipayOpenAuthTokenAppResponse;

/**
 * Created by xuqiang
 * 2017/11/16.
 */
public class AlipayAPIUtils {

    //支付宝应用ID
    private static String APP_ID = "2016080900203123";
    //应用秘钥
    private static String PRIVATEKEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCfAgpOQUOhiT/dSjJaDiegjOiKa4TaPAjTh2ZmBV4FUqTs4t+YqPYsLuWUWCColIZQuwFFIVJHJxQqoP1P57OLjLSZ5uNSP37Z3JOHEqvwVLHY5jqVFyAsgWQ9JthLFeDWbl55nYKJg+6HU1rEKfChqRl4/6l86TqNZWtNrnqxzELHmVs+Gr+yrmA24aaHYW5C5/9m6elxxHV9AlXFlo1ACG9t/kEd7DE+JNFzXdJ/D6zFsb3OeKoXQ3r/98IYGK4A4GkoSAq2o6MqW6XpZuB5kZHvGt6khCyN8MpaViDRA4AAcJ9bHDgyAWxKVr6OupBf/V9ZqnLQJsy/rgULEt5rAgMBAAECggEAT58k6/SwWgYxLa1Gs2OdIj8M/qtCVklzngCSHQoY/49XuKsOjhIlWxCMs5DBocjlzS0nQKej3K9L4ODGl6mNdZaapOpB8GCNM6jJUvzdjjXK7LK0qQ1wdfjA6gI2krq84INZ2You4I9o9OifbdUWgsz7TWSwSTU8tasQZVC2barskiB0J3HYUCqSCaXnXHjUC0/bxCXYvGIR0SAjNDbevZwo5bxhJj7h/DpJYgQ0BTS9yG2b8+Jln4TM/q9ii+FD5G60LGbjeICt7lIrTWBJu6yI+TgnMZPaYLuB5tjjiVJX+Uqr5gGIXX1Y3RwMGGsPMmFFWmsrnnbedJQIOo7p+QKBgQD89L5tSwtz5mv0H5hsDJAdd8jKfu2TP07w0iYNlVskQFrWZZvFpjWjr0sH/A1OmfCfeIMzmSS+0iSBk/+nJEY7HWXuGXgWFTAs+7mnlC2XxEw/MISk2fZY5bl4fHKJ9ZvtvhDBFFiyCgnMEWNwePBhVuD+mBKRcb1ws37oDpNwjwKBgQCg6+FM8uVcVef2T++ov9qCPel1U1AGZIy7YYZyY1+vM9fT2hOn33bFgzWzD0kqonkK38Wjmp5xudF0EgvE9jXpRMQLrzxrXTdIWi0f00/V21A5RtPRYw9aKY4JZuuNCr2ws3NKdf6UzlCFfEW2IQeSTjr7kvKwh9aGaxHQlpAqZQKBgFUfby2MQXBhLBbcwwgpOh4QfytVh1EzzrbPoSqVBjSuy2K2krLyeb55cMBhG4FF4Glpvo0QjgMA6HQKf8+ZL2EZRYHYZEbaHnokw5cXEDiwQBBLnXd0Oag0tjNLfafbVPuuKCkxVZ+kLSPU/ieNKyc2e0RxnF4BboSMgrpInWkVAoGAUscDrkGEGmtWVy3najbGgBi47tFcaO1wNzODpXPY1JcmfqO/WKtqvU8xYFqMmJHbEezqDa6LspSTWdGunDLKqKPm9Nu/iLr6pQ0+CRD6EFgwfEYiz6LVUtvtZIi8wmC0EyE8PSFVQ9adNQ0oDvgOnQQA80gz7vrxL1QEZfgNUMECgYADRNEdFvQdLm58fIlSiUf9UWkuP4vm/WbGelEfJ1NjU4oeJMNorsKmSvFDyEDtuxG5hFu/gYYcTIn5vCGCkYsuHGW25YOdnC94o85qVN5uC3Ps/W606QOtBHWoA9xicMW/c42l0JKxfGdcyzvZD1piGCmxU4iVQs0wwTjScrBQCQ==";
    //支付宝公钥
    private static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAq6Bli6Ka4NYL3gwQkUpaO8w/OBgyQuMfhuo70OJCMhk/fzUAnxtxz6kM1ECI5lpFVEC99s4/hWsd6CeffjWisFe85cpRQF2TRSv+6JZt6PKbvxdIti3pYcusq3SjsNVJZyzRmtN+umo0AmfgG02YRJftqEqmw9MVDB2ad2k5XmxSlfrZB5wykxNKiHKZsOW2iP3sXlU4PalJXFmoxIMDuycyBXk52NkWKMxUO38vEhMxbbhHT8DfiQnr2UzjsiGFqPSJ4ByE9lw8hfI9Q7DckaohgJw6cTWWjnuyZ6T8J/lvsMddkJeQ2C1m4m6t6LT17aHhBGmtYnkTmCf1bSH/nwIDAQAB";
    //回调地址
    public static String REDIRECT_URI = "http://127.0.0.1:8031/login/alipay/callback";

    /**
     * auth_base：以auth_base为scope发起的网页授权，是用来获取进入页面的用户的userId的，并且是静默授权并自动跳转到回调页的。用户感知的就是直接进入了回调页（通常是业务页面）。
     * auth_user：以auth_user为scope发起的网页授权，是用来获取用户的基本信息的（比如头像、昵称等）。但这种授权需要用户手动同意，用户同意后，就可在授权后获取到该用户的基本信息。
     */
    private static String SCOPE = "auth_user";

    private static String AUTHORIZE_URL = "https://openauth.alipaydev.com/oauth2/appToAppAuth.htm";
    //正式环境网关 https://openapi.alipay.com/gateway.do
    private static String ACCESS_TOKEN_URL = "https://openapi.alipaydev.com/gateway.do";


    private static AlipayClient alipayClient = new DefaultAlipayClient(ACCESS_TOKEN_URL,APP_ID, PRIVATEKEY,"json","UTF-8",ALIPAY_PUBLIC_KEY,"RSA2");


    /**
     * 支付宝登陆入口
     * @return
     */
    public static String getAliPayLoginUrl(){
        return AlipayAPIUtils.AUTHORIZE_URL + "?app_id=" + AlipayAPIUtils.APP_ID + "&scope=" + AlipayAPIUtils.SCOPE + "&redirect_uri=" + AlipayAPIUtils.REDIRECT_URI;
    }

    /**
     * 获取商户信息授权
     * @param appAuthToken
     * @return
     */
    public static AlipayOpenAuthTokenAppResponse alipayOpenAuthTokenApp(String appAuthToken) throws AlipayApiException {
        String biz = "{\"grant_type\":\"authorization_code\",\"code\":\"" + appAuthToken + "\"}";
        AlipayOpenAuthTokenAppRequest requestLogin2 = new AlipayOpenAuthTokenAppRequest();
        requestLogin2.setBizContent(biz);
        return alipayClient.execute(requestLogin2);
    }


    /**
     * 查询授权接口
     * @param appAuthToken
     * @return
     */
    public static AlipayOpenAuthTokenAppQueryResponse alipayOpenAuthTokenAppQuery(String appAuthToken) throws AlipayApiException {
        AlipayOpenAuthTokenAppQueryRequest request = new AlipayOpenAuthTokenAppQueryRequest();
        request.setBizContent("{\"app_auth_token\":\"" + appAuthToken + "\"}");
        return alipayClient.execute(request);
    }



    public static void main(String[] args) throws AlipayApiException {
    }

}
