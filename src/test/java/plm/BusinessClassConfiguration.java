package plm;

import base.BaseTester;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BusinessClassConfiguration extends BaseTester {


    @Test
    public void f1() throws InterruptedException {

        to("plmBusinessClassPath");
        typeWord("用户名输入框", "yuqiqi");
        typeWord("密码输入框", "123456");
        click("登录按钮");

        Thread.sleep(2000);
        to("plmBusinessClassConfiguration");
        click("铁盒配置按钮");
    }

    @Test
    public void f2() {
        typeWord("加色-单款色前三天累计销量", "15");
        typeWord("密码输入框", "123456");
    }
}
