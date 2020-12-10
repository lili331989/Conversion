import conversion.Quantities;
import org.junit.Test;
import java.util.HashMap;

import static org.junit.Assert.*;

public class QuantitiesAddRatioTest {

    @Test
    public void testAddRation() {
        Quantities quantities = new Quantities();
        quantities.addRatio("bar","ring", 6.0);
        HashMap<String, HashMap<String,Double>> quantityMap=quantities.getQuantityMap();
        HashMap<String,Double> setOfRatio1=quantityMap.get("bar");
        assertEquals("6.0", String.valueOf(setOfRatio1.get("ring")));
    }
}
