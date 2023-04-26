package cn.unminded.rtool.cache;

import cn.unminded.rtool.util.IDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * token缓
 */
public class TokenCache {
    private final Logger logger = LoggerFactory.getLogger(TokenCache.class);

    /**
     * token 缓存
     */
    private final Map<String, Long> cacheToken = new ConcurrentHashMap<>();

    private static final long DEFAULT_EXPIRE_SECONDS = 300;

    public TokenCache() {
        this.initTokenCache();
    }

    public TokenCache(TokenCacheConfig config) {
        this.initTokenCache(config);
    }

    /**
     * 使用默认配置初始化token缓存
     */
    public void initTokenCache() {
        this.initTokenCache(new TokenCacheConfig());
    }

    /**
     * 初始化token缓存
     * @param cacheConfig 用户配置
     */
    public void initTokenCache(TokenCacheConfig cacheConfig) {
        long delaySeconds = cacheConfig.getInitCheckDelaySeconds() > 0 ? cacheConfig.getInitCheckDelaySeconds() : DEFAULT_EXPIRE_SECONDS;
        long tokenExpireSeconds = cacheConfig.getTokenExpireSeconds() > 0 ? cacheConfig.getTokenExpireSeconds() : DEFAULT_EXPIRE_SECONDS;
        long checkDelaySeconds = cacheConfig.getCheckDelaySeconds() > 0 ? cacheConfig.getInitCheckDelaySeconds() : DEFAULT_EXPIRE_SECONDS;
        long expireMillis = tokenExpireSeconds * 1000;

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r);
            if (!t.isDaemon()) {
                t.setDaemon(true);
            }
            t.setName("token-clear");
            return t;
        });
        executor.scheduleWithFixedDelay(
                () -> clearExpireToken(expireMillis),
                delaySeconds,
                checkDelaySeconds,
                TimeUnit.SECONDS
        );
    }

    /**
     * 获取指定数量的token的数量
     * @param size 获取token
     * @return
     */
    public List<String> tokenList(Integer size) {
        Objects.requireNonNull(size);
        if (size <= 0) {
            return Collections.emptyList();
        }

        List<String> tokens = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            String token = token();
            tokens.add(token);
        }
        return tokens;
    }

    /**
     * 生成token
     * @return
     */
    public String token() {
        String token = IDUtil.uuid();
        add(token);
        return token;
    }

    public boolean contains(String token) {
        return cacheToken.containsKey(token);
    }

    /**
     * 向容器中添加token
     * @param token
     */
    public void add(String token) {
        Objects.requireNonNull(token);
        cacheToken.put(token, System.currentTimeMillis());
    }

    /**
     * 从容器中移除token
     * @param token
     */
    public void remove(String token) {
        Objects.requireNonNull(token);
        cacheToken.remove(token);
    }

    public int size() {
        return cacheToken.size();
    }

    /**
     * 清除无效的token
     */
    private void clearExpireToken(long expireMillis) {
        if (cacheToken.isEmpty()) {
            return;
        }
        if (logger.isDebugEnabled()) {
            logger.debug("开始清理无效token, 当前token数量: {}", cacheToken.size());
        }

        long now = System.currentTimeMillis();

        AtomicInteger count = new AtomicInteger();
        cacheToken.forEach((token, time) -> {
            if (now - time >= expireMillis) {
                cacheToken.remove(token);
                count.getAndIncrement();
            }
        });
        if (logger.isDebugEnabled()) {
            logger.debug("清理无效token数量{}, 当前token数量: {}", count.get(), cacheToken.size());
        }
    }
}
