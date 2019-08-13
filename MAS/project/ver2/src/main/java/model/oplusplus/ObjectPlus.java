package model.oplusplus;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * @author Mariusz Trzaska
 * Fill free to send me any remarks: mtrzaska@pjwstk.edu.pl
 *
 * The code could be improved - see the homework in the lecture.
 */
public abstract class ObjectPlus implements Serializable {

    private static Map<Class, List<ObjectPlus>> allExtents = new Hashtable<>();

    /**
     * Constructor.
     */
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

    /**
     * Gets the extent of the class
     * @param objectClass
     * @return
     */
    public static List<ObjectPlus> getClassExtent(Class objectClass) {
        if (allExtents.containsKey(objectClass)) {
            return allExtents.get(objectClass);
        }
        return null;
    }

    /**
     * Writes all extents to the given stream (a class method).
     * @throws IOException
     */
    public static void writeExtents(ObjectOutputStream stream) throws IOException {
        stream.writeObject(allExtents);
    }

    /**
     * Reads all extents from the given stream (a utility class method).
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void readExtents(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        allExtents = (Hashtable) stream.readObject();
    }

}