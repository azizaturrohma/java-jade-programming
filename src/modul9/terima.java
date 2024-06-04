package modul9;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;
import java.util.logging.Level;

public class terima extends Agent {
    protected void setup() {
        // registrasi agent
        DFAgentDescription dfad = new DFAgentDescription();
        dfad.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("terima");
        sd.setName(getLocalName());
        dfad.addServices(sd);
        try {
            DFService.register(this, dfad);
        } catch (FIPAException ex) {
            Logger.getLogger(terima.class.getName()).log(Level.SEVERE, null, ex);
        }

        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                ACLMessage pesan = myAgent.receive();
                if (pesan != null) {
                    String msg = pesan.getContent();
                    System.out.println("terima " + myAgent.getLocalName() + " : " + msg + "\n");
                } else {
                    block(200);
                }
            }
        });
    }
}