package boardinghouse.model;

import java.time.LocalDate;

public class Tenant extends Person {
    private final int id;
    private final String roomNo;
    private final LocalDate moveIn;
    private final double deposit;
 
     public Tenant(int id, String name, String contact, String roomNo, LocalDate moveIn, double deposit) {
        super(name, contact);
        this.id = id;
        this.roomNo = roomNo;
        this.moveIn = moveIn;
        this.deposit = deposit;
    }
    
    public int getId() { return id; }
    public String getRoomNo() { return roomNo; }
    public LocalDate getMoveIn() { return moveIn; }
    public double getDeposit() { return deposit; }

    @Override
    public String getRole() { return "Tenant"; }

    public String toCsv() {
        return String.join(",", String.valueOf(id), getName(), getContact(), roomNo,
                moveIn.toString(), String.valueOf(deposit));
    }
}