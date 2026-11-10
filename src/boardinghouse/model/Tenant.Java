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
     public static Tenant fromCsv(String line) {
        String[] p = line.split(",", -1);
        return new Tenant(Integer.parseInt(p[0]), p[1], p[2], p[3],
                LocalDate.parse(p[4]), Double.parseDouble(p[5]));
    }
}