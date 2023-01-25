package cn.unminded.rtool.cache;

import cn.unminded.rtool.util.IDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * token缓存
 */
public class TokenCache {
    private static final Logger logger = LoggerFactory.getLogger(TokenCache.class);

    /**
     * token 缓存
     */
    private static final Map<String, Long> CACHE_TOKEN = new ConcurrentHashMap<>();

    private static long DEFAULT_EXPIRE_SECONDS = 300;

    private TokenCache() {
        throw new UnsupportedOperationException();
    }

    /**
     * 使用默认配置初始化token缓存
     */
    public static void initTokenCache() {
        initTokenCache(new TokenCacheConfig());
    }

    /**
     * 初始化token缓存
     * @param cacheConfig 用户配置
     */
    public static void initTokenCache(TokenCacheConfig cacheConfig) {
        long delaySeconds = cacheConfig.getInitCheckDelaySeconds() > 0 ? cacheConfig.getInitCheckDelaySeconds() : DEFAULT_EXPIRE_SECONDS;
        long tokenExpireSeconds = cacheConfig.getTokenExpireSeconds() > 0 ? cacheConfig.getTokenExpireSeconds() : DEFAULT_EXPIRE_SECONDS;
        long expireMillis = tokenExpireSeconds * 1000;

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleWithFixedDelay(
                () -> clearExpireToken(expireMillis),
                delaySeconds,
                tokenExpireSeconds,
                TimeUnit.SECONDS
        );
    }

    /**
     * 获取指定数量的token的数量
     * @param size 获取token
     * @return
     */
    public static List<String> tokenList(Integer size) {
        Objects.requireNonNull(size);
        if (size <= 0) {
            return Collections.emptyList();
        }

        List<String> tokens = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            String token = token();
            tokens.add(token);
            add(token);
        }
        return tokens;
    }

    /**
     * 生成token
     * @return
     */
    public static String token() {
        return IDUtil.uuid();
    }

    public static boolean containsKey(String token) {
        return CACHE_TOKEN.containsKey(token);
    }

    /**
     * 向容器中添加token
     * @param token
     */
    public static void add(String token) {
        Objects.requireNonNull(token);
        CACHE_TOKEN.put(token, System.currentTimeMillis());
    }

    /**
     * 从容器中移除token
     * @param token
     */
    public static void remove(String token) {
        Objects.requireNonNull(token);
        CACHE_TOKEN.remove(token);
    }

    /**
     * 清除无效的token
     */
    private static void clearExpireToken(long expireMillis) {
        if (CACHE_TOKEN.isEmpty()) {
            return;
        }
        if (logger.isDebugEnabled()) {
            logger.debug("开始清理无效token, 当前token数量: {}", CACHE_TOKEN.size());
        }

        long now = System.currentTimeMillis();

        CACHE_TOKEN.forEach((token, time) -> {
            if (now - time >= expireMillis) {
                CACHE_TOKEN.remove(token);
            }
        });
        if (logger.isDebugEnabled()) {
            logger.debug("清理无效token完成, 当前token数量: {}", CACHE_TOKEN.size());
        }
    }
}
