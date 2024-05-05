import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainClassTest {

    MainClass mainClass = new MainClass();

    @Test
    public void testGetLocalNumber() {
        int number = mainClass.getLocalNumber(14);
        Assert.assertTrue("Expected number != 14", number == 14);
    }

    @Test
    public void testGetClassNumber() {
        int number = mainClass.getClassNumber();
        Assert.assertTrue("Expected number < 45", number > 45);
    }

    @Test
    public void testGetClassString() {
        String current_string = mainClass.getClassString();
        Assert.assertTrue("There's no expected string", current_string.equals("Hello, world") || current_string.equals("hello, world"));
    }
}


