package cn.unminded.rtool.id;

import java.util.UUID;

/**
 * 根据{@link UUID}规则的id生成器
 */
public class UUIDIdGenerator implements IdGenerator<String> {

    @Override
    public String generator() {
        return nextId();
    }

    /**
     * 只是对生成的uuid替换其中的横线"-" 共32位字符
     * @return
     */
    private synchronized String nextId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
