package CWK_1747361;

/**
 * @author Shivangi Shah
 */

public class TonerTechnician extends Thread implements Global {
    private final String toner_Tech_Name;
    private final ThreadGroup threadGroup;
    private final LaserPrinter printer;

    public TonerTechnician(String toner_Tech_Name, ThreadGroup threadGroup, LaserPrinter printer) {
        super(threadGroup, toner_Tech_Name);
        this.toner_Tech_Name = "Joe"; //the name of the toner technician
        this.threadGroup = threadGroup;
        this.printer = printer;
        
        System.out.printf("\nThe Toner Technician %s is in thread state: %s\n", this.toner_Tech_Name, this.getState());
    }

    @Override
    public void run() {
        for (int attempt = 1; attempt <= MAX_ATTEMPT; attempt++) {
            System.out.printf("\nToner technician %s is making attempt %d to replace the toner cartridge\n", toner_Tech_Name, attempt);
            printer.replaceTonerCartridge();

            int randNum = (int)(Math.random() * 10 + 1); //random number from 1 - 10
            try {
                System.out.printf("\nToner technician %s is sleeping for %d seconds\n", toner_Tech_Name, randNum);
                Thread.sleep(randNum * OneSecond);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("\nToner technician %s thread is terminating\n", toner_Tech_Name);
    }
}
