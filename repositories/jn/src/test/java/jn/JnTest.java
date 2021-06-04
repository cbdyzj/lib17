package jn;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JnTest {

    @Test
    public void testFibonacci() {
        var jn = new Jn();
        for (int n = 1; n < 9; n++) {
            var expected = jn.fibonacciJava(n);
            assertEquals(expected, jn.fibonacciJni(n));
            assertEquals(expected, jn.fibonacciJna(n));
        }
    }
}
