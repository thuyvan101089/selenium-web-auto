package test.testNG;

import org.testng.annotations.*;

public class TestNGHooks {

    /*
     * BeforeSuite
     * BeforeTest
     * BeforeClass
     * BeforeMethod
     * */
    @BeforeSuite
    public void BeforeSuite() {
        System.out.println("Before Suite");
    }

    @BeforeTest
    public void BeforeTest() {
        System.out.println("\tBefore Test");
    }

    @BeforeClass
    public void BeforeClass() {
        System.out.println("\t\tBefore Class");
    }

    @BeforeMethod
    public void BeforeMethod() {
        System.out.println("\t\t\tBefore Method");
    }

    @Test
    public void test01() {
        System.out.println("\t\t\t\ttest 01");
    }

    @Test
    public void test02() {
        System.out.println("\t\t\t\ttest 02");
    }
}
