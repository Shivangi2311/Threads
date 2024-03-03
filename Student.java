package CWK_1747361;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Shivangi Shah
 */

public class Student extends Thread implements Global
{
    private final String student_name;
    private final ThreadGroup threadGroup;
    private final LaserPrinter printer;
    private final List<Document> doc_list;
    
    public Student(String student_name, ThreadGroup threadGroup, LaserPrinter printer) 
    {
        super(threadGroup, student_name);
        
        this.student_name = student_name;
        this.threadGroup = threadGroup;
        this.printer = printer;
        this.doc_list = new ArrayList<>(); //arraylist to store document info 

        System.out.printf("\nStudent %s is in thread state %s\n", student_name, this.getState());

    }
    
    @Override
    public void run()
    {
        System.out.printf("\nStudent %s is in thread state %s\n", student_name, this.getState());

        
        //names of documents to be printed
        Document doc_1 = new Document(student_name, "Harry Potter", 200);
        Document doc_2 = new Document(student_name, "Percy Jackson", 23);
        Document doc_3 = new Document(student_name, "Divergent", 130);
        Document doc_4 = new Document(student_name, "Malory Towers", 173);
        Document doc_5 = new Document(student_name, "Charlie and the Chocolate Factory", 90);
        
        //adding documents to arraylist 
        doc_list.add(doc_1);
        doc_list.add(doc_2);
        doc_list.add(doc_3);
        doc_list.add(doc_4);
        doc_list.add(doc_5);
                
        int randNum = (int)(Math.random() * 10 + 1); //random number from 1 - 10
        
        /*loops through all the list of the document to see which student is requesting to print
        which document and the number of pages*/
        for(int i = 0; i < doc_list.size(); i++)
        {
            Document doc = doc_list.get(i);

    System.out.printf("\nStudent %s is requesting to print document %s that has %d of pages\n",
        doc.getStudent_name(), doc.getDocName(), doc.getNum_Pages());


            printer.printDocument(doc);

            try 
            {
                System.out.printf("\nStudent %s is sleeping for %d seconds before making another print request\n",doc.getStudent_name(),randNum);

                
                Thread.sleep(randNum * OneSecond); //sleep for random amount of time
            } 
            
            catch (InterruptedException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
        }
            
        System.out.printf("\nStudent %s thread is terminating\n",student_name);

    
    }
}
