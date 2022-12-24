package com.lijunweiz.rtool;

import com.lijunweiz.rtool.util.IOUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class IOUtilTest {

    @Test
    public void copyTest() throws IOException {
        IOUtil.copy("D:\\code\\idea\\rtool\\rtool-core\\src\\main\\java\\com\\lijunweiz\\rtool\\util\\IOUtil.java", "D:\\code\\idea\\IOUtil2.java");
        Assert.assertTrue(true);
    }

}
