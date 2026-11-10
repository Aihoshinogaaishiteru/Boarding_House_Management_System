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
     public void removeRoom(String roomNo) {
        Room r = findRoom(roomNo);
        if (r == null) throw new IllegalArgumentException("Room not found.");
        if (r.getOccupantCount() > 0) throw new IllegalStateException("Cannot remove: room still has tenants.");
        rooms.remove(r);
        save();
    }
     public List<Tenant> getTenants() { return Collections.unmodifiableList(tenants); }

      public Tenant findTenant(int id) {
        for (Tenant t : tenants) if (t.getId() == id) return t;
        return null;
    }
    private int nextTenantId() {
        int max = 0;
        for (Tenant t : tenants) max = Math.max(max, t.getId());
        return max + 1;
    }

      public Tenant checkIn(String name, String contact, String roomNo, double deposit) {
        if (name.isEmpty()) throw new IllegalArgumentException("Name cannot be empty.");
        Room room = findRoom(roomNo);
        if (room == null) throw new IllegalArgumentException("Room not found.");
        Tenant t = new Tenant(nextTenantId(), name, contact, room.getRoomNo(), LocalDate.now(), deposit);
        room.addOccupant(t); // throws if the room is full
        tenants.add(t);
        save();
        return t;
    }
     public Tenant checkOut(int tenantId) {
        Tenant t = findTenant(tenantId);
        if (t == null) throw new IllegalArgumentException("Tenant not found.");
        Room r = findRoom(t.getRoomNo());
        if (r != null) r.removeOccupant(t);
        tenants.remove(t);
        save();
        return t;
    }
    
       public double getBalance(Tenant t, YearMonth month) {
        Room r = findRoom(t.getRoomNo());
        double rate = (r == null) ? 0 : r.getRate();
        double paid = 0;
        for (Billing b : billings) {
            if (b.getTenantId() == t.getId() && b.getMonth().equals(month)) paid += b.getAmount();
        }
        return Math.max(rate - paid, 0);
    }
    
       private int nextBillingId() {
        int max = 0;
        for (Billing b : billings) max = Math.max(max, b.getId());
        return max + 1;
    }
}   
