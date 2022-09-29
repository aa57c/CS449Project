package sprint0_test;

import static org.junit.Assert.*;
import sprint0_product.*;
import org.junit.Test;

public class MaxNumOfArrayTest {

	@Test
	public void testFindMax(){  
        assertEquals(4,MaxNumOfArray.findMax(new int[]{1,3,4,2}));  
        assertEquals(-1,MaxNumOfArray.findMax(new int[]{-12,-1,-3,-4,-2}));  
    }  
}
