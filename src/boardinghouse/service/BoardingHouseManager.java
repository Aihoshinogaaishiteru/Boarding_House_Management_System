package boardinghouse.service;

import boardinghouse.model.Billing;
import boardinghouse.model.Room;
import boardinghouse.model.Tenant;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * All the business rules live here. Main only talks to this class.
 * Methods throw IllegalArgumentException / IllegalStateException with a
 * readable message when something is not allowed.
 */
public class BoardingHouseManager {
    private final FileStorage storage;
    private final List<Room> rooms;
    private final List<Tenant> tenants;
    private final List<Billing> billings;

public BoardingHouseManager(FileStorage storage) {
        this.storage = storage;
        this.rooms = storage.loadRooms();
        this.tenants = storage.loadTenants();
        this.billings = storage.loadBillings();
        // link each loaded tenant to their room   
        for (Tenant t : tenants) {
            Room r = findRoom(t.getRoomNo());
            if (r != null) r.addOccupant(t);
        }
    }
    private void save() {
        storage.saveAll(rooms, tenants, billings);
    }

    public List<Room> getRooms() { return Collections.unmodifiableList(rooms); }

    public Room findRoom(String roomNo) {
        for (Room r : rooms) if (r.getRoomNo().equalsIgnoreCase(roomNo)) return r;
        return null;
    }
     public void addRoom(String roomNo, String type, double rate, int capacity) {
        if (roomNo.isEmpty()) throw new IllegalArgumentException("Room number cannot be empty.");
        if (findRoom(roomNo) != null) throw new IllegalArgumentException("Room " + roomNo + " already exists.");
        if (rate < 0 || capacity < 1) throw new IllegalArgumentException("Rate must be 0 or more and capacity at least 1.");
        rooms.add(new Room(roomNo, type, rate, capacity));
        save();
    }
}   