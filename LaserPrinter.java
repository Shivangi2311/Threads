package CWK_1747361;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Shivangi Shah
 */
public class LaserPrinter implements ServicePrinter, Global{
    
    private Document document;
    private final String printer_Id;
    private double paper_level; //current paper level 
    private int toner_level; // current toner level
    private int no_doc_printed; //number of documnets printed
   
    private Boolean waited; 
    private static int refilled; // used to keep track of the number of times the toner cartridge is refilled
    int randNum = (int)(Math.random() * 10 + 1); // random number generated between 1-10

    
    public LaserPrinter(String printer_Id) 
    {
        this.printer_Id = printer_Id;
        this.paper_level = Max_Paper; // initialises the current paper level to max, i.e. 250
        this.toner_level = Max_Toner; // initialises the current toner level to max, i.e. 500
        this.no_doc_printed = 0; // initialises the document printed at 0
        
        System.out.printf("\nLaserPrinter monitor has started with paper level of: %d and toner level of: %d\n", 
                         Math.round(this.paper_level), this.toner_level);
 // gives a print statement of the current levels
    }

    @Override
public synchronized void replaceTonerCartridge() {
    boolean isWaited = false;
    
    while (!cartridgeReplaceRequired()) {
        if (isWaited) {
            break;
        } else {
            System.out.println("The toner technician is waiting before checking again, if the toner needs replacement\n");
            try {
                wait(randNum);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isWaited = true;
    }
    while (cartridgeReplaceRequired()) {
        System.out.println("\nToner cartridge is getting replaced\n");
        toner_level = Max_Toner;
        refilled++;  // incrementing refilled variable here
        System.out.printf("\nToner technician finished. Cartridges replaced: %d\n", refilled);
        System.out.println(toString());
    }
    notifyAll();
}

 
 @Override
    public synchronized void refillPaper() 
    {
        waited = false;
        try{
            while (!paperRefillRequired()){ // while the paper does not require to be refilled
                if(waited)
                {
                 break;    
                }
                
                else{
                    System.out.println("The paper technician is waiting sometime before checking again, if the paper needs to be replenished");
                    wait(randNum);
                }
                waited = true ; 
            }
            
            if (paperRefillRequired()){
                System.out.println("\nPaper is being replenished...");
                int pack = noOfPack();
                paper_level = Max_Paper;
                System.out.println("Paper Technician Finished, packs of paper used: " + pack );
                System.out.println(toString());                
            }
            notifyAll();
        }
        catch (InterruptedException ex) {        
            Logger.getLogger(LaserPrinter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

 
 
    @Override
    public synchronized void printDocument(Document doc) 
    {
      waited = false;
      this.document = doc; 
      try{
          while(paper_level < doc.getNum_Pages() || toner_level < doc.getNum_Pages()) //either paper or toner is insufficient
          {
            if (waited){
                break;
            }
              else if (paper_level < doc.getNum_Pages()) {
                System.out.println("\nInsufficient Papers. Waiting until refilled\n"); 
            } else if (toner_level < doc.getNum_Pages()) {
                System.out.println( "\nInsufficient Toner level. Waiting until cartridge is replaced\n");
            } else {
                System.out.println("\nInsufficient Papers & Toner level. Waiting until they become available\n");
            }
            wait (randNum);
            waited = true;
          } 
          
            if (paper_level >= doc.getNum_Pages() && toner_level >= doc.getNum_Pages()) 
            {
                paper_level -= doc.getNum_Pages();
                toner_level -= doc.getNum_Pages();
                no_doc_printed++;
                
                System.out.printf("\nPrint Job requested by %s document %s with %d pages is complete!!\n",
                  this.document.getStudent_name(), 
                  this.document.getDocName(), 
                  this.document.getNum_Pages());

            }
        System.out.println(toString());
        notifyAll();
      }
        catch (InterruptedException ex) {
            Logger.getLogger(LaserPrinter.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    
    String getPrinterID() 
    {
        return printer_Id;
    }
    
    @Override
    public String toString() 
    {
        return "[ PrinterID: " + printer_Id +
                ", Paper Level: " + Math.round(paper_level) +
                ", Toner Level: " + toner_level +
                ", Documents Printed: " + no_doc_printed + " ]";
    }
    
    //method to check if the toner cartridge needs to be replaced. 
    private boolean cartridgeReplaceRequired()
    {
        //returns the value if the toner level is less than the minimum toner level i.e 10
        return toner_level < Min_Toner;
    }
    
    //method to check if the paper tray needs to be replenished. 
    private boolean paperRefillRequired()
    {
        // returns the value if the current paper level+paper in a pack (50) will be less than or equal to 250(max paper allowed in the paper tray)
        return (paper_level + Paper_in_Pack) <= Max_Paper;
    }
    
    //calculates the number of packs of paper refilled and will always be rounded up to the nearest integer, since a pack of apper cannot be eg. 4.1, it will round up to 5
    private int noOfPack()
    {
        double p = ((Max_Paper - paper_level) / Paper_in_Pack);   
        return (int)Math.ceil(p);
    }

    public double getPaper_level() {
        return paper_level;
    }

    public int getToner_level() {
        return toner_level;
    }

    public int getNo_doc_printed() {
        return no_doc_printed;
    }

}
















//Reference:
//reentrantLock, to use instead of Synchronisation
//http://myjavaadventures.com/blog/2019/08/10/java-1-5-lock-classes-or-an-alternative-to-the-synchronized-keyword/#:~:text=Synchronized%20keyword%20limitations,-The%20java.&text=concurrent.,using%20synchronized%20methods%20and%20statements.
//https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/locks/ReentrantLock.html
//https://stackoverflow.com/questions/63547495/reentrantlock-condition-how-does-signallall-work#:~:text=signalAll()%20wakes%20up%20all,lock%20before%20relinquishes%20the%20lock.