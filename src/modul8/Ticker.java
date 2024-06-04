package modul8;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

public class Ticker extends Agent {

    protected void setup() {
        addBehaviour(new TickerBehaviour(this, 1000) {

            @Override
            public void onTick() {
                System.out.println("local name: " + getAID().getLocalName());
            }
        });
    }
}
