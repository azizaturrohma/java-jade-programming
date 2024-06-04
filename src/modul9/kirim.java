package modul9;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class kirim extends Agent {
    protected void setup() {
        addBehaviour(new CyclicBehaviour(this) {

            public void action() {
                ACLMessage msg = new ACLMessage(ACLMessage.SUBSCRIBE);
                msg.setConversationId("kirim");
                msg.setContent("dari " + myAgent.getLocalName() + " apa kabar? \n");
                msg.addReceiver(new AID("broker", AID.ISLOCALNAME));
                myAgent.send(msg);
                block(2000);
            }
        });
    }
}
