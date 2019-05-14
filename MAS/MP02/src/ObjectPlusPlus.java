import java.io.PrintStream;
import java.io.Serializable;
import java.util.*;

public abstract class ObjectPlusPlus extends ObjectPlus implements Serializable {

    private Map<String, Map<Object, ObjectPlusPlus>> links = new Hashtable<>();
    private static Set<ObjectPlusPlus> allParts = new HashSet<>();

    public void addOneToManyLink(String roleName, String reverseRoleName, ObjectPlusPlus obj) throws Exception {
//        if (obj.getLinks(reverseRoleName).length == 0) {
//            addLink(roleName, reverseRoleName, obj);
//        } else {
//            throw new Exception("Couldn't create link");
//        }
        try {
            obj.getLinks(reverseRoleName);
        } catch (Exception e) {
            addLink(roleName, reverseRoleName, obj);
        }
    }

    private void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObj, Object qualifier, int counter) {
        Map<Object, ObjectPlusPlus> objectLinks;

        if(counter < 1) {
            return;
        }

        if(links.containsKey(roleName)) {
            objectLinks = links.get(roleName);
        } else {
            objectLinks = new HashMap<>();
            links.put(roleName, objectLinks);
        }

        if(!objectLinks.containsKey(qualifier)) {
            objectLinks.put(qualifier, targetObj);
            targetObj.addLink(reverseRoleName, roleName, this, this, counter - 1);
        }
    }

    public void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObj, Object qualifier) {
        addLink(roleName, reverseRoleName, targetObj, qualifier, 2);
    }

    public void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObj) {
        addLink(roleName, reverseRoleName, targetObj, targetObj);
    }

    public void addPart(String roleName, String reverseRoleName, ObjectPlusPlus partObj) throws  Exception {
        if(allParts.contains(partObj)) {
            throw new Exception("The part is already connected to a whole!");
        }

        addLink(roleName, reverseRoleName, partObj);
        allParts.add(partObj);
    }

    public ObjectPlusPlus[] getLinks(String roleName) throws Exception {
        Map<Object, ObjectPlusPlus> objectLinks;

        if(!links.containsKey(roleName)) {
            throw new Exception("No links for the role: " + roleName);
        }

        objectLinks = links.get(roleName);
        return (ObjectPlusPlus[]) objectLinks.values().toArray(new ObjectPlusPlus[0]);
    }

    public void showLinks(String roleName, PrintStream stream) throws Exception {
        Map<Object, ObjectPlusPlus> objectLinks;

        if(!links.containsKey(roleName)) {
            throw new Exception("No links for the role: " + roleName);
        }

        objectLinks = links.get(roleName);
        Collection col = objectLinks.values();
        stream.println(this.getClass().getSimpleName() + " links, role '" + roleName + "':");

        for(Object obj : col) {
            stream.println(" " + obj);
        }
    }

    public ObjectPlusPlus getLinkedObject(String roleName, Object qualifier) throws Exception {
        Map<Object, ObjectPlusPlus> objectLinks;

        if(!links.containsKey(roleName)) {
            throw new Exception("No links for the role: " + roleName);
        }

        objectLinks = links.get(roleName);

        if(!objectLinks.containsKey(qualifier)) {
            throw new Exception("No link for the qualifer: " + qualifier);
        }

        return objectLinks.get(qualifier);
    }

}
