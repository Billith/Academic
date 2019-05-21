package abstract_class;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public abstract class ObjectPlus implements Serializable {

    private static Map<Class, List<ObjectPlus>> allExtents = new Hashtable<>();

    public ObjectPlus() {
        List<ObjectPlus> extent;
        Class theClass = this.getClass();
        if (allExtents.containsKey(theClass)) {
            extent = allExtents.get(theClass);
        } else {
            extent = new ArrayList<>();
            allExtents.put(theClass, extent);
        }
        extent.add(this);
    }

    public static void removeObjectFromExtent(ObjectPlus object) {
        if (allExtents.containsKey(object.getClass())) {
            List<ObjectPlus> extent = allExtents.get(object.getClass());
            if (extent.contains(object)) {
                extent.remove(object);
            }
        }
    }


    public static List<ObjectPlus> getClassExtent(Class objectClass) {
        if (allExtents.containsKey(objectClass)) {
            return allExtents.get(objectClass);
        }
        return null;
    }

}