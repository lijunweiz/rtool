package cn.unminded.rtool.cache;

public class TokenCacheConfig {

    private Integer tokenExpireSeconds = 300;

    private Integer initCheckDelaySeconds = 30;

    private Integer checkDelaySeconds = 300;

    public Integer getTokenExpireSeconds() {
        return tokenExpireSeconds;
    }

    public TokenCacheConfig setTokenExpireSeconds(Integer tokenExpireSeconds) {
        this.tokenExpireSeconds = tokenExpireSeconds;
        return this;
    }

    public Integer getInitCheckDelaySeconds() {
        return initCheckDelaySeconds;
    }

    public TokenCacheConfig setInitCheckDelaySeconds(Integer initCheckDelaySeconds) {
        this.initCheckDelaySeconds = initCheckDelaySeconds;
        return this;
    }

    public Integer getCheckDelaySeconds() {
        return checkDelaySeconds;
    }

    public TokenCacheConfig setCheckDelaySeconds(Integer checkDelaySeconds) {
        this.checkDelaySeconds = checkDelaySeconds;
        return this;
    }
}
