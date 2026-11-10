package boardinghouse.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Room {
     private final String roomNo;
    private final String type;
    private final double rate;
    private final int capacity;
    private final List<Tenant> occupants = new ArrayList<>();

      public Room(String roomNo, String type, double rate, int capacity) {
        this.roomNo = roomNo;
        this.type = type;
        this.rate = rate;
        this.capacity = capacity;
    }
      public String getRoomNo() { return roomNo; }
    public String getType() { return type; }
    public double getRate() { return rate; }
     public int getCapacity() { return capacity; }
    public int getOccupantCount() { return occupants.size(); }
    public List<Tenant> getOccupants() { return Collections.unmodifiableList(occupants); }

     public boolean isFull() { return occupants.size() >= capacity; }
}
