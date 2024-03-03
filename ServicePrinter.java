package CWK_1747361;

/**
 * @author Shivangi Shah
 */
public interface ServicePrinter extends Printer
{
    //this is the maximum number of paper that the printer can hold
    public final double Max_Paper  = 250 ;
    //this is the maximum amount of toner that the printer can hold
    public final int Max_Toner  = 500 ;
    //this is the maximum amount of toner that the printer can have before it needing to be replaced
    public final int Min_Toner = 10 ;
    //this is the number of sheets of paper per pack
    public final double Paper_in_Pack = 50 ;
    //this is the maximum number of pages that can be printed per Toner Cartridge
    public final int PagesPerTonerCartridge = 500 ;


    // Technician methods
    //this method replaces the toner cartridge, only the toner technician can call it 
    public void replaceTonerCartridge( ) ;
    //this method refills the paper in the printer, only the paper technician can call it 
    public void refillPaper( ) ;
}
