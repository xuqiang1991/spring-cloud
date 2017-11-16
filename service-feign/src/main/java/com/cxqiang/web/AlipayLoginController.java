package com.cxqiang.web;

import com.alipay.api.response.AlipayOpenAuthTokenAppQueryResponse;
import com.alipay.api.response.AlipayOpenAuthTokenAppResponse;
import com.cxqiang.config.SampleAuthenticationManager;
import com.cxqiang.entity.sys.SysThirdPartyUser;
import com.cxqiang.service.UserInfoService;
import com.cxqiang.utils.AlipayAPIUtils;
import com.google.common.base.Joiner;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Alipay登录
 * Created by xuqiang
 * 2017/11/13.
 */
@Controller
@RequestMapping(value = "/login/alipay", method = RequestMethod.GET)
public class AlipayLoginController {

    private Logger logger = LoggerFactory.getLogger(AlipayLoginController.class);

    @Autowired
    private SampleAuthenticationManager authenticationManager;

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect(AlipayAPIUtils.getAliPayLoginUrl());
    }

    @RequestMapping("/callback")
    public void loginAlipay(HttpServletResponse res, @RequestParam String app_auth_code) throws Exception {

        //获取商户信息授权
        AlipayOpenAuthTokenAppResponse oauthTokenResponse = AlipayAPIUtils.alipayOpenAuthTokenApp(app_auth_code);
        logger.info("支付宝授权" + oauthTokenResponse.getBody());
        if(oauthTokenResponse.isSuccess()){
            Long ailId = Long.parseLong(oauthTokenResponse.getUserId());
            String appAuthToken = oauthTokenResponse.getAppAuthToken();
            String appRefreshToken = oauthTokenResponse.getAppRefreshToken();
            Long authAppId = Long.parseLong(oauthTokenResponse.getAuthAppId());
            int expiresIn = Integer.parseInt(oauthTokenResponse.getExpiresIn());
            int reExpiresIn = Integer.parseInt(oauthTokenResponse.getReExpiresIn());
            SysThirdPartyUser user = userInfoService.findByThirdPartyUserId(ailId, 1);
            Date curTime = new Date();
            Date expiresInDate = DateUtils.addSeconds(curTime, expiresIn - (10 * 60));
            Date reExpiresInDate = DateUtils.addSeconds(curTime, reExpiresIn - (10 * 60));
            if(user == null){
                //查询授权接口
                AlipayOpenAuthTokenAppQueryResponse response = AlipayAPIUtils.alipayOpenAuthTokenAppQuery(app_auth_code);
                List<String> authMethods = response.getAuthMethods();
                Date authStart = response.getAuthStart();
                Date authEnd = response.getAuthEnd();
                logger.info("支付宝授权接口" + oauthTokenResponse.getBody());
                //初始化第三方用户
                user = new SysThirdPartyUser();
                user.setThirdPartyId(ailId);
                user.setType(2);//alipay
                user.setAppAuthToken(appAuthToken);
                user.setAppRefreshToken(appRefreshToken);
                user.setAuthAppId(authAppId);
                user.setExpiresIn(expiresInDate);
                user.setReExpiresIn(reExpiresInDate);
                user.setAuthMethods(CollectionUtils.isEmpty(authMethods) ? null : Joiner.on(",").join(authMethods));
                user.setAuthStart(authStart);
                user.setAuthEnd(authEnd);
                user.setUpdateTime(curTime);
                user.setCreatedTime(curTime);
                Long userId =  userInfoService.insertUserAndSysThirdPartyUser(user);
                user.setUserId(userId);
            }

            if(user.getUserId() == null){
                throw new UsernameNotFoundException("add user failure ailId:'" + ailId + "'");
            }

            Authentication req = new UsernamePasswordAuthenticationToken(user.getUserId(), null);
            Authentication result = authenticationManager.authenticate(req);
            if(result != null){
                SecurityContextHolder.getContext().setAuthentication(result);
            }else {
                throw new UsernameNotFoundException("could not find the user ailId '" + ailId + "'");
            }
        }else {
            throw new UsernameNotFoundException("Alipay Authorization failed");
        }

        res.sendRedirect("/");
    }

}
