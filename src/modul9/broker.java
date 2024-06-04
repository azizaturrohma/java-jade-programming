package modul9;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class broker extends Agent {

    Vector aTerimaBaru = new Vector();

    @Override
    protected void setup() {
        addBehaviour(new behav(this, aTerimaBaru));
        addBehaviour(new cari_df(this, 10000, aTerimaBaru));
    }

    class behav extends CyclicBehaviour {
        MessageTemplate mt_Kirim;
        boolean StaKirim = false, StaTerima = false;
        Vector vTerima;
        int iTerima = 0;

        public behav(broker aThis, Vector aTerimaBaru) {
            vTerima = aTerimaBaru;
            mt_Kirim = MessageTemplate.MatchConversationId("kirim");
        }

        public void action() {
            // terima pesan dari terima
            ACLMessage msgKrmPesan = myAgent.receive(mt_Kirim);
            // terima pesan dari kirim
            if (msgKrmPesan != null) {
                if (vTerima.size() > 0) {
                    ACLMessage Krmbalas = new ACLMessage(ACLMessage.PROPOSE);
                    Krmbalas.setContent(msgKrmPesan.getContent());
                    Krmbalas.addReceiver((AID) vTerima.elementAt(iTerima++));
                    myAgent.send(Krmbalas);
                    if (iTerima >= vTerima.size()) {
                        iTerima = 0;
                    }
                    StaKirim = true;
                    System.out.println("size ok\n");
                }
                System.out.println("msg ok\n");
            } else {
                StaKirim = false;
            }
            if (!(StaKirim || StaTerima)) {
                block(50);
            }
        }
    }

    class cari_df extends TickerBehaviour {

        String[] aTerimaBaru;
        DFAgentDescription tm = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        Vector vTerima;

        public cari_df(Agent aThis, int i, Vector aTerimaBaru) {
            super(aThis, i);
            sd.setType("terima");
            tm.addServices(sd);
            vTerima = aTerimaBaru;
        }

        @Override
        protected void onTick() {
            try {
                DFAgentDescription[] dfPenerima = DFService.search(myAgent, tm);
                vTerima.clear();
                for (int i = 0; i < dfPenerima.length; i++) {
                    vTerima.addElement(dfPenerima[i].getName());
                }
            } catch (FIPAException ex) {
                Logger.getLogger(broker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
