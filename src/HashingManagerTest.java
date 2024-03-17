import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class HashingManagerTest {

    @Test
    void hashFileWithAllAlgorithms_ValidFile_ReturnsNonEmptyMap() throws IOException {
        HashingManager hashingManager = new HashingManager();
        File file = new File("./sample.txt");
        file.createNewFile();

        Map<String, String> hashTimes = hashingManager.hashFileWithAllAlgorithms(file);

        assertFalse(hashTimes.isEmpty());
        file.delete();
    }
}