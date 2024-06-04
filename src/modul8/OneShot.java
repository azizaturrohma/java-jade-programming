package modul8;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class OneShot extends Agent {

    protected void setup() {
        addBehaviour(new OneShotBehaviour(this) {

            @Override
            public void action() {
                System.out.println("local name: " + getAID().getLocalName());
            }
        });
    }
}
