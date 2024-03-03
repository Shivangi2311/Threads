package CWK_1747361;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Shivangi Shah
 */
public class PrintingSystem {
    public static void main(String[] args) {
        //thread group for students
        ThreadGroup studentThreadGroup = new ThreadGroup("Students");
        //thread group for technicians
        ThreadGroup technicianThreadGroup = new ThreadGroup("Technicians");
        //instance of a laser printer
        LaserPrinter laser_printer = new LaserPrinter("lp-CG.24");
        
        //declaration of the name of students
        Student student_1 = new Student("Ron Weasley", studentThreadGroup, laser_printer);
        Student student_2 = new Student("Hermione Granger", studentThreadGroup, laser_printer);
        Student student_3 = new Student("Neville Longbottom", studentThreadGroup, laser_printer);
        Student student_4 = new Student("Draco Malfoy", studentThreadGroup, laser_printer);
        
        PaperTechnician paperTechnician = new PaperTechnician("PaperTechnician", technicianThreadGroup, laser_printer);
        TonerTechnician tonerTechnician = new TonerTechnician("TonerTechnician", technicianThreadGroup, laser_printer);
        
        
        student_1.start();
        student_2.start();
        student_3.start();
        student_4.start();

        paperTechnician.start();
        tonerTechnician.start();
        
        
        try
        {
            student_1.join();
            student_2.join();
            student_3.join();
            student_4.join();
            
            paperTechnician.join();
            tonerTechnician.join();
        } 
        catch (InterruptedException ex) 
        {
            Logger.getLogger(PrintingSystem.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        System.out.printf("\nThread %s is in thread state %s\n", student_1.getName(), student_1.getState());
        System.out.printf("\nThread %s is in thread state %s\n", student_2.getName(), student_2.getState());
        System.out.printf("\nThread %s is in thread state %s\n", student_3.getName(), student_3.getState());
        System.out.printf("\nThread %s is in thread state %s\n", student_4.getName(), student_4.getState());

        System.out.printf("\nThread %s is in thread state %s\n", tonerTechnician.getName(), tonerTechnician.getState());
        System.out.printf("\nThread %s is in thread state %s\n", paperTechnician.getName(), paperTechnician.getState());


        //prints the final status of the printer
        System.out.printf("\nFinal status of the printer: %s", laser_printer.toString());
        
    }
}
