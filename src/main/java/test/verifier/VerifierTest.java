package test.verifier;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test.BaseTest;

public class VerifierTest extends BaseTest {
    @Test
    public void testHardAssertion() {
        // Verifier.assertEqual("van","van2");
        //Hard Assertion: if meet failure, stop process
        Assert.assertEquals("van", "van2", "Actual result is different from expected result");
        Assert.assertTrue(true);
        Assert.assertFalse(false);
        // if(list is Empty){
        Assert.fail();
    }

    @Test
    public void testSoftAssertion() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(1,2);
        softAssert.assertTrue(false);
        softAssert.fail("Test fail");
        softAssert.assertAll();

    }
}

