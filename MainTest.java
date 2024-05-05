import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;


class MainTest extends CoreTestCase {

    @BeforeEach
    public void textStartTest() {
        System.out.println("Start test");
    }

    @AfterEach
    public void textFinishTest() {
        System.out.println("Finish test");
    }

    @Test
    public void firstTest() {
        System.out.println("firstTest");
    }

    @Test
    public void secondTest() {
        System.out.println("secondTest");
    }




    /*MathHelper Math = new MathHelper();

    @Test
    public void myFirstTest4() {
        int a = Math.calc(10, 20, '+');
        System.out.println(a);
    }

    @Test
    public void myFirstTest5() {
        System.out.println("First test: Before changing static_int " + MathHelper.static_int);
        MathHelper.static_int = 8;
        MathHelper mathObject = new MathHelper();
        System.out.println("First test: Before changes simple_int " + mathObject.simple_int);
        mathObject.simple_int = 8;

    }

    @Test
    public void mySecondTest6() {
        System.out.println("Second test: Before changing static_int " + MathHelper.static_int);
        MathHelper.static_int = 8;
        MathHelper mathObject = new MathHelper();
        System.out.println("Second test: Before changes simple_int " + mathObject.simple_int);
        mathObject.simple_int = 8;
    }*/


}


