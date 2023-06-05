package cn.unminded.rtool.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class IOUtilsTest {

    @Test
    public void copyTest() throws IOException {
        String path = IOUtilsTest.class.getResource("").getPath();
        String src = path + "/IOUtilsTest.class";
        String des = path + "/IOUtilsTest.class2";
        IOUtils.copy(src, des);
        File file = new File(des);
        Assert.assertTrue(file.exists());
        if (file.exists()) {
            file.delete();
        }
    }

}
