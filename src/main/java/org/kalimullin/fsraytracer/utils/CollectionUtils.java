package org.kalimullin.fsraytracer.utils;

import java.util.List;

public class CollectionUtils {
    public static boolean isListEqualsIgnoreOrder(List list1, List list2) {
        if (list1 == list2)
            return true;
        if (list1 == null || list2 == null || list1.size() != list2.size())
            return false;
        // really slow method (O(n^2))
        return list1.containsAll(list2);
    }
}
