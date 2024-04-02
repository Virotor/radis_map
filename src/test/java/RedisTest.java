import com.lessons.redis.ConnectorToRedis;
import com.lessons.redis.RedisMap;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.JedisPool;

import java.util.NoSuchElementException;


@Slf4j
public class RedisTest {

    private static RedisMap redisMap;

    static  String value;
    static  String key;

    static String nullString;

    static String invalidKey;

    @BeforeAll
    public static void setRedis() {
        value = "value";
        key = "key";
        invalidKey = "key1";
        nullString = null;
        redisMap =  new RedisMap();
        ConnectorToRedis.getInstance(new JedisPool("localhost", 6379));

    }
    @AfterEach
    public void stopServer() {

    }
    @Test
    public void testNullPointer() {
        NullPointerException nullPointerException = Assertions.assertThrows(NullPointerException.class,
                () -> redisMap.put(nullString, value)
        );
        Assertions.assertEquals(nullPointerException.getClass(), NullPointerException.class);
    }
    @Test
    public void testNullPointerValueAndKey() {
        NullPointerException nullPointerException = Assertions.assertThrows(NullPointerException.class,
                () -> redisMap.put(nullString, nullString)
        );
        Assertions.assertEquals(nullPointerException.getClass(), NullPointerException.class);
    }
    @Test
    public void testNullPointerValue() {

        NullPointerException nullPointerException = Assertions.assertThrows(NullPointerException.class,
                () -> redisMap.put(key, nullString)
        );
        Assertions.assertEquals(nullPointerException.getClass(), NullPointerException.class);
    }
    @Test
    public void testIllegalArgumentExceptionKey() {

        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> redisMap.get(Integer.valueOf(0))
        );
        Assertions.assertEquals(illegalArgumentException.getClass(), IllegalArgumentException.class);
    }
    @Test
    public void testNullPointerExceptionKeyGet() {

        NullPointerException nullPointerException = Assertions.assertThrows(NullPointerException.class,
                () -> redisMap.get(nullString)
        );
        Assertions.assertEquals(nullPointerException.getClass(), NullPointerException.class);
    }
    @Test
    public void testNullPointerExceptionGetValue() {

        NoSuchElementException noSuchElementException = Assertions.assertThrows(NoSuchElementException.class,
                () -> redisMap.get(invalidKey)
        );
        Assertions.assertEquals(noSuchElementException.getClass(), NoSuchElementException.class);
    }
    @Test
    public void testPutFunction() {
        RedisMap redisMap = new RedisMap();

        redisMap.put(key, value);
        Assertions.assertEquals(redisMap.get(key), value);
    }

}
