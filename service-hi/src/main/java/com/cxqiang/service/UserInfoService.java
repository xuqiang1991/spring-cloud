package com.cxqiang.service;

import com.cxqiang.entity.sys.Account;
import com.cxqiang.entity.sys.SysThirdPartyUser;
import com.cxqiang.entity.sys.SysUser;
import com.cxqiang.mapper.SysThirdPartyUserMapper;
import com.cxqiang.mapper.SysUserMapper;
import com.cxqiang.mapper.UserInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by xuqiang
 * 2017/8/28.
 */
@Service
@Transactional
public class UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private SysThirdPartyUserMapper sysThirdPartyUserMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询用户
     * @param name
     * @return
     */
    public Account findAccountByUsername(String name) {
        return userInfoMapper.findByUsername(name);
    }

    /**
     * 根据用户ID查询用户
     * @param userId
     * @return
     */
    public Account findAccountByUserId(Long userId) {
        return userInfoMapper.findByUserId(userId);
    }

    /**
     * 查询第三方用户
     * @param thirdPartyUserId
     * @param type
     * @return
     */
    public SysThirdPartyUser findByThirdPartyUserId(Long thirdPartyUserId, Integer type) {
        return sysThirdPartyUserMapper.selectByThirdPartyId(thirdPartyUserId,type);
    }

    /**
     * 查询用户
     * @param userId
     * @return
     */
    public SysUser findUserId(Long userId) {
        return sysUserMapper.selectByPrimaryKey(userId);
    }

    /**
     * 添加第三方用户
     * @param sysThirdPartyUser
     * @return
     */
    public Long insertUserAndSysThirdPartyUser(SysThirdPartyUser sysThirdPartyUser) {
        Date curTime = new Date();
        SysUser sysUser = new SysUser();
        sysUser.setUserName(StringUtils.EMPTY);
        sysUser.setPassword(StringUtils.EMPTY);
        sysUser.setType(sysThirdPartyUser.getType());
        sysUser.setStatus(true);
        sysUser.setErrorTimes(0);
        sysUser.setLoginTime(curTime);
        sysUser.setUpdateTime(curTime);
        sysUser.setCreateTime(curTime);
        sysUserMapper.insert(sysUser);

        sysThirdPartyUser.setUserId(sysUser.getUserId());
        sysThirdPartyUserMapper.insert(sysThirdPartyUser);

        return  sysUser.getUserId();
    }

    /**
     * 添加用户
     * @param sysUser
     * @sysUser
     */
    public int insertUser(SysUser sysUser) {
        return sysUserMapper.insert(sysUser);
    }

}
