package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

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

    public static void printAllExtents() {
        for (Class entry : allExtents.keySet()) {
            allExtents.get(entry).forEach(System.out::println);
        }
    }

    public static void writeExtents(ObjectOutputStream stream) throws IOException {
        stream.writeObject(allExtents);
    }

    public static void readExtents(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        allExtents = (Hashtable) stream.readObject();
    }

    public static List<ObjectPlus> getClassExtent(Class objectClass) {
        if (allExtents.containsKey(objectClass)) {
            return allExtents.get(objectClass);
        }
        return null;
    }

}
