package client;

import server.PhoneDirectoryInterface;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import java.rmi.RemoteException;

public class PhoneBookClient {

    PhoneDirectoryInterface pdi;
    Context ctx;

    public PhoneBookClient() throws NamingException {
        ctx = new InitialContext();
        Object obj = ctx.lookup("PhoneDirectoryService");
        pdi = (PhoneDirectoryInterface) PortableRemoteObject.narrow(obj, PhoneDirectoryInterface.class);
    }

    public String getPhoneNumber(String name) throws RemoteException {
        return pdi.getPhoneNumber(name);
    }

    public boolean addPhoneNumber(String name, String phoneNumber) throws RemoteException {
        return pdi.addPhoneNumber(name, phoneNumber);
    }

    public boolean replacePhoneNumber(String name, String phoneNumber) throws RemoteException {
        return pdi.replacePhoneNumber(name, phoneNumber);
    }

}


