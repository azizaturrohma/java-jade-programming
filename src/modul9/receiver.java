package modul9;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class receiver extends Agent {
    protected void setu() {
        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                // seleksi pesan berdasarkan performative
                MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
                // seleksi pesan berdasarkan nama agent pengirim dan performative
                MessageTemplate mt2 = MessageTemplate.and(MessageTemplate.MatchSender(
                        new AID("pengirim", AID.ISLOCALNAME)),
                        MessageTemplate.MatchPerformative(ACLMessage.INFORM));

                ACLMessage msg = myAgent.receive(mt);
                String content;
                if (msg != null) {
                    content = msg.getContent();
                    System.out.println("Pesan yang diterima: " + content);
                } else {
                    block();
                }
            }
        });
    }
}
