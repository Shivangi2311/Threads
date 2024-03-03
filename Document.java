package CWK_1747361;

/**
 * @author Shivangi Shah
 */
class Document {
    private final String Student_name; //name of the student asking for the print
    private final String doc_Name; // name of the document to be printed
    private final int num_Pages; // number of pages of the document to be printed 

    //constructor
    public Document(String SID, String doc_name, int page_num) {
        this.Student_name = SID;
        this.doc_Name = doc_name;
        this.num_Pages = page_num;
    }

    public String getStudent_name() {
        return Student_name;
    }

    public String getDocName() {
        return doc_Name;
    }

    public int getNum_Pages() {
        return num_Pages;
    }


    @Override
    public String toString() {
        return "Document[ " + doc_Name+
                "Stuent Name: " + Student_name + ", " +
                "Name: " + doc_Name + ", " +
                "Pages: " + num_Pages +
                "]";
    }

} 
