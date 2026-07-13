package ModuleTests.hw;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestFilesMerge {
    @Test
    public void testMergeFiles() throws IOException {
        String path1 = "temp1.txt";
        String path2 = "temp2.bin";
        String pathResult = "res.dat";
        String pathExpected = "resExpected.dat";

        Files.write(Path.of(path1), "Hello ".getBytes("UTF-8"));
        Files.write(Path.of(path2), new byte[]{0, -1, 127});

        try (FileOutputStream fos = new FileOutputStream(pathExpected)) {
            fos.write("Hello ".getBytes("UTF-8"));
            fos.write(new byte[]{0, -1, 127});
        }

        FilesMerge.mergeFiles(path1, path2, pathResult);

        long expectedSize = Files.size(Path.of(pathExpected));
        long actualSize = Files.size(Path.of(pathResult));

        Assertions.assertEquals(expectedSize, actualSize, "Размеры файлов не совпадают");

        byte[] expectedBytes = Files.readAllBytes(Path.of(pathExpected));
        byte[] actualBytes = Files.readAllBytes(Path.of(pathResult));

        Assertions.assertArrayEquals(expectedBytes, actualBytes, "Содержимое файлов не совпадает");

        Files.deleteIfExists(Path.of(path1));
        Files.deleteIfExists(Path.of(path2));
        Files.deleteIfExists(Path.of(pathResult));
        Files.deleteIfExists(Path.of(pathExpected));
    }
}
