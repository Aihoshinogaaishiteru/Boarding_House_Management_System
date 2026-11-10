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
    
}