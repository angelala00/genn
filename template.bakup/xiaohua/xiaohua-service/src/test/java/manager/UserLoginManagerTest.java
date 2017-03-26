package manager;

import com.cjteam.xiao.manager.UserLoginManager;
import com.cjteam.xiao.model.Administrator;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

import static com.cjteam.xiao.model.CrapIssue.CRAP_BIG;
import static com.cjteam.xiao.model.CrapIssue.CRAP_SMALL;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by ChenLong
 * Date: 13-11-9
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/service-context.xml"})
@Transactional
public class UserLoginManagerTest {
    public void setUp() throws Exception {

    }

    public void testLogin() throws Exception {

    }

    @Test
    @Rollback(value = false)
    public void testCreateUser() throws Exception {
        String username = "phoenix";
        String password = "1q2w3e";
        Administrator newAdmin = new Administrator();
        newAdmin.setName("吴一敏");
        newAdmin.setUsername(username);
        newAdmin.setPassword(password);
        newAdmin.setEmail("wym.missyou@gmail.com");
        newAdmin = userLoginManager.createUser(newAdmin);
        assertNotNull("", newAdmin);
        assertTrue("", StringUtils.equals(UserLoginManager.packagePassword(password), newAdmin.getPassword()));
    }

    @Test
    public void createSinglePassword() throws Exception {
        String[] passowrds = {"1q2w3e"};
        for (String password : passowrds) {
            System.out.printf("==%s - %s\n", password, UserLoginManager.packagePassword(password));
        }
    }

    private static final Random random = new Random(System.currentTimeMillis());

    @Test
    public void lotteryResult() {
        int times = 100;
        System.out.println(NumberUtils.max(new int[]{CRAP_BIG, CRAP_SMALL}));
        while (times-->0){
            System.out.println("result " + ((Math.abs(random.nextInt()) % NumberUtils.max(new int[]{CRAP_BIG, CRAP_SMALL})) + 1));
        }
    }

    public static void main(String[] args) {
        System.out.println(URLDecoder.decode("%E5%9B%BD%E7%BE%8E%E5%9C%A8%E7%BA%BF"));
        System.out.println(URLDecoder.decode("2014-05-29+17%3A10%3A30"));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
                    System.out.print(df.parse(URLDecoder.decode("2014-05-29+17%3A10%3A30")).toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
    @Autowired
    UserLoginManager userLoginManager;
}
