package CWK_1747361;

/**
 * @author Shivangi Shah
 */

public class PaperTechnician extends Thread implements Global {
    private final String paper_Tech_Name;
    private final ThreadGroup threadGroup;
    private final LaserPrinter printer;

    public PaperTechnician(String paper_Tech_Name, ThreadGroup threadGroup, LaserPrinter printer) {
        super(threadGroup, paper_Tech_Name);
        this.paper_Tech_Name = "John"; //the name of the toner technician
        this.threadGroup = threadGroup;
        this.printer = printer;
        
        System.out.printf("\nThe Paper Technician %s is in thread state: %s\n", this.paper_Tech_Name, this.getState());
    }

    @Override
    public void run() {
        for (int attempt = 1; attempt <= MAX_ATTEMPT; attempt++) {
            System.out.printf("\nPaper technician %s is making attempt %d to replace the paper \n", paper_Tech_Name, attempt);
            printer.refillPaper();

            int randNum = (int)(Math.random() * 10 + 1); //random number from 1 - 10
            try {
                System.out.printf("\nPaper technician %s is sleeping for %d seconds\n", paper_Tech_Name, randNum);
                Thread.sleep(randNum * OneSecond);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("\nPaper technician %s thread is terminating\n", paper_Tech_Name);
    }
}


