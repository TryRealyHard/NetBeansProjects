package pavlin.slow_mover;

import java.io.File;

/**
 * @author TryRealyHard
 */
public class Slow_mover {

    public static void main(String[] args) {
        String pot = "C:/Users/TryRealyHard/Documents/NetBeansProjects/slow_mover";
        //funkcija Premakne iz Štartne mape v Končno na 1 sekundo, Štart prazen -> čaka 15 sec, Konec vsebuje max količino datotek -> čaka 5 sec (trenutni max = 10).
        zasedenostPremik(pot+"/start_premika", pot+"/konec_premika", 1000, 15000, 5000, 10);
    }
    
    public static void zasedenostPremik(String mapaIzvor, String mapaCilj, long casPremika, long casDopusta, long casPawze, int kolicinaDatotek){
        File izMape = new File(mapaIzvor);
        File vMapo = new File(mapaCilj);
        while(true){
            long casPredPreverjanjem = System.currentTimeMillis();
            File[] izSeznam = izMape.listFiles();
            if (izSeznam.length != 0){
                for (var datoteka : izSeznam ){
                    long casPredPremikom = System.currentTimeMillis();
                    File[] vSeznam = vMapo.listFiles();
                    if(vSeznam.length >= kolicinaDatotek){
                        pocakiMilis(casPredPremikom, casPawze);
                        break;
                    }
                    pocakiMilis(casPredPremikom, casPremika);
                    datoteka.renameTo(new File(vMapo + "/" + datoteka.getName()));
                }
            }else{
                pocakiMilis(casPredPreverjanjem, casDopusta);
            }
            
        }
    }
    
    public static void pocakiMilis(long casAktivacije, long casZakasnitev)
    {
        long casTrenutni = System.currentTimeMillis();
        long casRazlika = casTrenutni + casZakasnitev - casAktivacije;
        if (casRazlika > 0){
            try{
                Thread.sleep(casRazlika);
            }
            catch(InterruptedException e){
                System.out.println(e.toString());
            }
        }
    } 
}
