package test.testNG;

import org.testng.annotations.Test;

public class TestOrder {

    @Test (dependsOnMethods = {"testA"})
    public void testB() {
        System.out.println("test B");
    }

    @Test (priority = 1)
    public void testA() {
        System.out.println("test A");
    }
}
