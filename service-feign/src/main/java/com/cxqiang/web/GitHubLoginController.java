package com.cxqiang.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * https://github.com/login/oauth/authorize?client_id=e52d56289abaa59c7b1a&state=xxx&redirect_uri=http://localhost:8031/login/github
 * GitHub登录
 * Created by xuqiang
 * 2017/11/13.
 */
@Controller
@RequestMapping(value = "/login/github", method = RequestMethod.GET)
public class GitHubLoginController {

    private static String client_id = "e52d56289abaa59c7b1a";
    private static String client_secret = "591008cb8e5b634a4f94e1cea04e13ab7218cc87";
    private static String redirect_uri = "http://localhost:8031/login/github/callback";

    private static String state = "github";

    //跳转到第三方登录 github
    private static String authorize_url = "https://github.com/login/oauth/authorize";

    private static String access_token_url = "https://github.com/login/oauth/access_token";

    private static String  api_user_url = "https://api.github.com/user";

    @RequestMapping
    public void index(HttpServletResponse response) throws IOException {
        String url = authorize_url + "?client_id=" + client_id + "&state=github" + "&redirect_uri=" + redirect_uri;
        response.sendRedirect(url);
    }

    @RequestMapping("/callback")
    public void loginGithub(String code, String state) {

        if (StringUtils.isNotEmpty(code)) {
            OkHttpClient okHttpClient = new OkHttpClient();


            String access_token = null;
            String redirect_uri = "http://localhost:8031/login/github/callback";
            String url = access_token_url + "?client_id=" + client_id + "&client_secret=" + client_secret + "&code=" + code + "&redirect_uri=" + redirect_uri;
            Request request = new Request.Builder().url(url).build();
            Call call = okHttpClient.newCall(request);
            try {
                Response response = call.execute();
                String body = response.body().string();
                JSONObject jsonObject = JSON.parseObject(body);
                access_token = jsonObject.getString("access_token");

                if(StringUtils.isNotEmpty(access_token)){
                    url = api_user_url + "access_token=" + access_token;
                    request = new Request.Builder().url(url).build();
                    call = okHttpClient.newCall(request);
                    response = call.execute();
                    body = response.body().string();
                    System.out.println(body);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
