package com.lijunweiz.rtool.util;

import com.lijunweiz.rtool.id.IdGenerator;
import com.lijunweiz.rtool.id.SnowflakeIdGenerator;
import com.lijunweiz.rtool.id.UUIDIdGenerator;

/**
 * id生成工具
 */
public class IDUtil {

    private IDUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * 基于雪花算法
     * @return
     */
    public static Long nextId() {
        return IdGeneratorHolder.SNOWFLAKE_ID_GENERATOR.generator();
    }

    /**
     * 基于jdk的{@link java.util.UUID}
     * @return
     */
    public static String uuid() {
        return IdGeneratorHolder.UUID_ID_GENERATOR.generator();
    }

    private static class IdGeneratorHolder {
        private final static String DATACENTER_ID = System.getProperty("datacenterId", "1");;
        private final static String MACHINE_ID = System.getProperty("machineId", "1");;
        private final static IdGenerator<Long> SNOWFLAKE_ID_GENERATOR = new SnowflakeIdGenerator(Integer.parseInt(DATACENTER_ID), Integer.parseInt(MACHINE_ID));
        private final static IdGenerator<String> UUID_ID_GENERATOR = new UUIDIdGenerator();
    }

}
