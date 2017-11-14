package com.cxqiang.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cxqiang.config.SampleAuthenticationManager;
import com.cxqiang.entity.sys.SysThirdPartyUser;
import com.cxqiang.entity.sys.SysUser;
import com.cxqiang.service.UserInfoService;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * https://github.com/login/oauth/authorize?client_id=e52d56289abaa59c7b1a&state=xxx&redirect_uri=http://localhost:8031/login/github
 * GitHub登录
 * Created by xuqiang
 * 2017/11/13.
 */
@Controller
@RequestMapping(value = "/login/github", method = RequestMethod.GET)
public class GitHubLoginController {

    private Logger logger = LoggerFactory.getLogger(GitHubLoginController.class);

    private static String CLIENT_ID = "e52d56289abaa59c7b1a";
    private static String CLIENT_SECRET = "591008cb8e5b634a4f94e1cea04e13ab7218cc87";
    private static String REDIRECT_URI = "http://localhost:8031/login/github/callback";

    private static String STATE = "github";

    //跳转到第三方登录 github
    private static String AUTHORIZE_URL = "https://github.com/login/oauth/authorize";

    private static String ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";

    private static String  API_USER_URL = "https://api.github.com/user";

    private static String  ACCESS_TOKEN = "access_token";

    @Autowired
    private SampleAuthenticationManager authenticationManager;

    @Autowired
    private UserInfoService userInfoService;


    @RequestMapping
    public void index(HttpServletResponse response) throws IOException {
        String url = AUTHORIZE_URL + "?client_id=" + CLIENT_ID + "&state="+ STATE + "&redirect_uri=" + REDIRECT_URI;
        response.sendRedirect(url);
    }

    @RequestMapping("/callback")
    public void loginGithub(HttpServletResponse res, String code, String state) throws Exception {

        if (StringUtils.isNotEmpty(code)) {
            OkHttpClient okHttpClient = new OkHttpClient();


            String access_token = null;
            String url = ACCESS_TOKEN_URL + "?client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&code=" + code + "&redirect_uri=" + REDIRECT_URI;
            Request request = new Request.Builder().url(url).build();
            Call call = okHttpClient.newCall(request);
            try {
                Response response = call.execute();
                String body = response.body().string();

                String[] tmps = body.split("&");
                if(tmps.length > 0){
                    for (String tmp :tmps){
                        if(tmp.startsWith(ACCESS_TOKEN)){
                            access_token = tmp.split("=")[1];
                            break;
                        }
                    }
                }

                if(StringUtils.isNotEmpty(access_token)) {
                    url = API_USER_URL + "?" + ACCESS_TOKEN + "=" + access_token;
                    request = new Request.Builder().url(url).build();
                    call = okHttpClient.newCall(request);
                    response = call.execute();
                    body = response.body().string();

                    logger.info("Github login:" + body);

                    JSONObject jsonObject = JSON.parseObject(body);
                    String login = jsonObject.getString("login");
                    Long id = jsonObject.getLong("id");

                    //查询github用户
                    SysThirdPartyUser user = userInfoService.findByThirdPartyUserId(id, 1);
                    if(user == null){
                        //初始化第三方用户
                        user = new SysThirdPartyUser();
                        user.setThirdPartyId(id);
                        user.setType(1);//Github
                        user.setLoginName(login);
                        user.setNick(login);
                        user.setUrl(jsonObject.getString("html_url"));
                        user.setDetailUrl(jsonObject.getString("url"));
                        user.setEmail(jsonObject.getString("email"));
                        user.setUpdateTime(jsonObject.getDate("updated_at"));
                        user.setCreatedTime(jsonObject.getDate("created_at"));
                        Long userId =  userInfoService.insertUserAndSysThirdPartyUser(user);
                        user.setUserId(userId);
                    }

                    if(user.getUserId() == null){
                        throw new UsernameNotFoundException("add user failure'" + login + "'");
                    }

                    Authentication req = new UsernamePasswordAuthenticationToken(user.getUserId(), null);
                    Authentication result = authenticationManager.authenticate(req);
                    if(result != null){
                        SecurityContextHolder.getContext().setAuthentication(result);
                    }else {
                        throw new UsernameNotFoundException("could not find the user '" + login + "'");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        res.sendRedirect("/");
    }

}
