package one;

import com.zero.one.DRoot;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DRootExampleTest {

    @Test
    public void Tests() {
        assertEquals( "Nope!" , 7, DRoot.digital_root(16));
        assertEquals( "Nope!" , 6, DRoot.digital_root(456989));
        assertEquals( "Nope!" , 6, DRoot.digital_root(6));
    }
}
