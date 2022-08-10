package com.lijunweiz.rtool.test;

import com.lijunweiz.rtool.util.IOUtil;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class IOUtilTest {

    @Test
    public void copyTest() throws IOException {
        IOUtil.copy("D:\\code\\idea\\rtool\\rtool-core\\src\\main\\java\\com\\lijunweiz\\rtool\\util\\IOUtil.java", "D:\\code\\idea\\IOUtil2.java");
        Assert.assertTrue(true);
    }

}
