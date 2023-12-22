package test_data;

import com.google.gson.Gson;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataObjectBuilder {
    public static <T> T buildDataObjectFrom(String relativeFileLocation, Class<T> datatypeClass) {
        T data;
        String currentProjectLocation = System.getProperty("user.dir");
        String absoluteFileLocation = currentProjectLocation + relativeFileLocation;

        try (
                Reader jsonContentReader = Files.newBufferedReader(Paths.get(absoluteFileLocation));
        ) {
            Gson gson = new Gson();
            data= gson.fromJson(jsonContentReader, datatypeClass);

        } catch (Exception e) {
            throw new RuntimeException("[ERROR]: Error while reading file" + absoluteFileLocation);
        }
        return data;

    }
}
