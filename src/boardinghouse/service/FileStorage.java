package boardinghouse.service;
import boardinghouse.model.Tenant;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class FileStorage {
    private final Path dir;

    public FileStorage(String folder) {
        this.dir = Paths.get(folder);
    }

}
