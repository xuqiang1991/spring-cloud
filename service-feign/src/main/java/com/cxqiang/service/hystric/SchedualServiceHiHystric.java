package com.cxqiang.service.hystric;

import com.cxqiang.service.SchedualServiceHi;
import org.springframework.stereotype.Component;

/**
 * Created by xuqiang
 * 2017/8/11.
 */
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {

    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }

}
