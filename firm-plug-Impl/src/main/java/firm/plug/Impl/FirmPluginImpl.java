package firm.plug.Impl;

import firm.plug.common.FirmPlug;
import redis.clients.jedis.Jedis;

/**
 * @author: hj
 * @date: 2023/2/15
 * @time: 2:53 PM
 */
public class FirmPluginImpl implements FirmPlug {
    @Override
    public void doPlug() {
        Jedis jedis = new Jedis("10.55.255.54", 6379);
        jedis.auth("123456");
        jedis.set("name", "hj");
        System.out.println(jedis.get("name"));
    }
}
