package firm.plug.common;

import java.lang.reflect.InvocationTargetException;

/**
 * @author: hj
 * @date: 2023/2/15
 * @time: 2:51 PM
 */
public interface FirmPlug {

    /**
     * 插件处理逻辑
     */
    void doPlug() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

}
