package boardinghouse.model;

import java.util.ArrayList;
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
}
