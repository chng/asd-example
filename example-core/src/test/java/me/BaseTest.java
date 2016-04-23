package me;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by chn on 16/4/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-core.xml"})
public class BaseTest extends AbstractJUnit4SpringContextTests {
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        String tmpDir = System.getProperty("java.io.tmpdir");
        System.out.println("tmpDir is "+tmpDir);
//		HSFEasyStarter.start("tmpDir", "1.4.9.6");
    }
}
