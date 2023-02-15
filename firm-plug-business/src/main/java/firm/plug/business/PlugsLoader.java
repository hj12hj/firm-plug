package firm.plug.business;

import firm.plug.core.CustomPlugLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author: hj
 * @date: 2023/2/15
 * @time: 3:07 PM
 */
@Component
public class PlugsLoader {

    @PostConstruct
    public void init() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        CustomPlugLoader customPlugLoader = new CustomPlugLoader();
        Class<?> aClass = customPlugLoader.loadSingleJar("/Users/hejie/Desktop/myCode/firm-plug/firm-plug-business/Plugs/firm-plug-Impl.jar");
        aClass.getMethod("doPlug").invoke(aClass.newInstance());

    }

}
