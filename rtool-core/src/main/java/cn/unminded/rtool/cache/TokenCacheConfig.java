package cn.unminded.rtool.cache;

public class TokenCacheConfig {

    private int tokenExpireSeconds = 300;

    private int initCheckDelaySeconds = 30;

    public int getTokenExpireSeconds() {
        return tokenExpireSeconds;
    }

    public TokenCacheConfig setTokenExpireSeconds(int tokenExpireSeconds) {
        this.tokenExpireSeconds = tokenExpireSeconds;
        return this;
    }

    public int getInitCheckDelaySeconds() {
        return initCheckDelaySeconds;
    }

    public TokenCacheConfig setInitCheckDelaySeconds(int initCheckDelaySeconds) {
        this.initCheckDelaySeconds = initCheckDelaySeconds;
        return this;
    }
}
