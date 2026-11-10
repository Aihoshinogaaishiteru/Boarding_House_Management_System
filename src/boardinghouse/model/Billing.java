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
 public int getId() { return id; }
    public int getTenantId() { return tenantId; }
    public YearMonth getMonth() { return month; }
    public double getAmount() { return amount; }
    public LocalDate getDatePaid() { return datePaid; }

      public String toCsv() {
        return String.join(",", String.valueOf(id), String.valueOf(tenantId), month.toString(),
                String.valueOf(amount), datePaid.toString());
    }


}
