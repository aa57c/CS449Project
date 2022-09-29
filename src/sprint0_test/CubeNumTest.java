package sprint0_test;
import static org.junit.Assert.*;  
import sprint0_product.*;
import org.junit.Test;


public class CubeNumTest {
	@Test
	 public void testCube(){  
        System.out.println("test case cube");  
        assertEquals(27,CubeNum.cube(3));  
    }  

}
