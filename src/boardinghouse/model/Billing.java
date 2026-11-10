package boardinghouse.model;


import java.time.LocalDate;
import java.time.YearMonth;

public class Billing {
    private final int id;
    private final int tenantId;
    private final YearMonth month;
    private final  double amount;
    private final LocalDate datePaid;

    public Billing(int id, int tenantId, YearMonth month, double amount, LocalDate datePaid) {
        this.id = id;
        this.tenantId = tenantId;
        this.month = month;
        this.amount = amount;
        this.datePaid = datePaid;
    }




    
}
