package modul8;

import jade.core.Agent;
import java.util.Iterator;

public class HelloWorldAgent extends Agent {

    @Override
    protected void setup() {
        // Printout a welcome message
        System.out.println("Hello World. I'm an agent!");

        // Printout agent identifiers
        System.out.println("My local-name is " + getAID().getLocalName());
        System.out.println("My GUID is " + getAID().getName());
        System.out.println("My addresses are: ");
        Iterator it = getAID().getAllAddresses();
        while (it.hasNext()) {
            System.out.println("-" + it.next());
        }
    }
}