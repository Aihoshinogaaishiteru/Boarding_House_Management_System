package boardinghouse.service;

import boardinghouse.model.Billing;
import boardinghouse.model.Room;
import boardinghouse.model.Tenant;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** Only job: read and write the CSV files. */
public class FileStorage {
    private final Path dir;

    public FileStorage(String folder) {
        this.dir = Paths.get(folder);
    }

    public List<Room> loadRooms() {
        List<Room> list = new ArrayList<>();
        for (String l : readLines("rooms.csv")) list.add(Room.fromCsv(l));
        return list;    
    }

    public List<Tenant> loadTenants() {
        List<Tenant> list = new ArrayList<>();
        for (String l : readLines("tenants.csv")) list.add(Tenant.fromCsv(l));
        return list;
    }

    public List<Billing> loadBillings() {
        List<Billing> list = new ArrayList<>();
        for (String l : readLines("billings.csv")) list.add(Billing.fromCsv(l));
        return list;
    }

    public void saveAll(List<Room> rooms, List<Tenant> tenants, List<Billing> billings) {
        try {
            Files.createDirectories(dir);
            Files.write(dir.resolve("rooms.csv"),
                    rooms.stream().map(Room::toCsv).collect(Collectors.toList()));
            Files.write(dir.resolve("tenants.csv"),
                    tenants.stream().map(Tenant::toCsv).collect(Collectors.toList()));
            Files.write(dir.resolve("billings.csv"),
                    billings.stream().map(Billing::toCsv).collect(Collectors.toList()));
        } catch (IOException e) {
            System.out.println("Could not save data: " + e.getMessage());
        }
    }

    private List<String> readLines(String file) {
        List<String> out = new ArrayList<>();
        Path p = dir.resolve(file);
        if (!Files.exists(p)) return out;
        try {
            for (String l : Files.readAllLines(p)) {
                if (!l.trim().isEmpty()) out.add(l);
            }
        } catch (IOException e) {
            System.out.println("Could not read " + file + ": " + e.getMessage());
        }
        return out;
    }
}
