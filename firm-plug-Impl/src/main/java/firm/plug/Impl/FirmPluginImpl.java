package firm.plug.Impl;

import cn.hutool.extra.spring.SpringUtil;
import firm.plug.common.FirmPlug;

import java.lang.reflect.InvocationTargetException;

/**
 * @author: hj
 * @date: 2023/2/15
 * @time: 2:53 PM
 */
public class FirmPluginImpl implements FirmPlug {
    @Override
    public void doPlug() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Object test = SpringUtil.getBean("test");
        test.getClass().getMethod("test").invoke(test);

    }
}
