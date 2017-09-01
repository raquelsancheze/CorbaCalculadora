package CalcServer;

import Calculadora.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

class serverimpl extends CalculadoraAppPOA {

    private ORB orb;

    public void setorb(ORB orb_val) {
        orb = orb_val;
    }

    @Override
    public double somafn(double a, double b) {
        return (a + b);
    }

    @Override
    public double subtracaofn(double a, double b) {
        return (a - b);
    }

    @Override
    public double multiplicacaofn(double a, double b) {
        return (a * b);
    }

    @Override
    public double divisaofn(double a, double b) {
        return (a / b);
    }

    @Override
    public double potenciafn(double a, double b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double raizfn(double a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

public class CalcServer {

    public static void main(String args[]) {
        try {
            ORB orb = ORB.init(args, null);

            org.omg.CORBA.Object objref1 = orb.resolve_initial_references("RootPOA");
            POA rootpoa = POAHelper.narrow(objref1);
            rootpoa.the_POAManager().activate();

            serverimpl serverobj = new serverimpl();
            serverobj.setorb(orb);
            org.omg.CORBA.Object objref2 = rootpoa.servant_to_reference(serverobj);
            CalculadoraApp href = CalculadoraAppHelper.narrow(objref2);

            org.omg.CORBA.Object objref3 = orb.resolve_initial_references("NameService");
            NamingContextExt ncref = NamingContextExtHelper.narrow(objref3);
            String pathname = "CalculadoraApp";
            NameComponent path[] = ncref.to_name(pathname);
            ncref.rebind(path, href);

            System.out.println("Servidor pronto e esperando...");

            orb.run();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}