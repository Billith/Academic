package server;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;

public class PhoneBookServer {

    public static void main(String[] args) {

        try {
            PhoneDirectory phoneDirectory = new PhoneDirectory(args[0]);
            Context ctx = new InitialContext();
            ctx.rebind("PhoneDirectoryService", phoneDirectory);
        } catch (NamingException | RemoteException e) {
            e.printStackTrace();
        }

    }

}