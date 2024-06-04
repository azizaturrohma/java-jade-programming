package modul8;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;

public class Cyclic extends Agent {

    protected void setup() {
        addBehaviour(new CyclicBehaviour(this) {

            @Override
            public void action() {
                System.out.println("local name: " + getAID().getLocalName());
            }
        });
    }
}
