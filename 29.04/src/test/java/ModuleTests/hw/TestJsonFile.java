package ModuleTests.hw;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestJsonFile {
    @Test
    public void  validJsonFile () throws IOException {
        String fileName = "C:\\ITISproga\\season26\\file.json";
        JsonFile jf = new JsonFile();
        int result = jf.checkJsonFile(fileName);
        Assertions.assertEquals(0, result, "Скобки должны быть сбалансированы");
    }

    @Test
    public void InvalidJsonFile() throws IOException {
        String fileName1 = "C:\\ITISproga\\season26\\29.04\\src\\main\\java\\ModuleTests\\hw\\wrong.json";
        JsonFile jf = new JsonFile();
        int result1 = jf.checkJsonFile(fileName1);
        Assertions.assertEquals(17, result1, "Ошибка на последней позиции");
    }

    @Test
    public void nullException () {
        JsonFile jf = new JsonFile();
        assertThrows(NullPointerException.class, () -> {
            jf.checkJsonFile(null);
        });
    }

    @Test
    public void noSuchFile() {
        String fileName1 = "notExist.json";
        JsonFile jf = new JsonFile();
        assertThrows(IOException.class, () -> {
            jf.checkJsonFile(fileName1);
        });
    }

    @Test
    public void wrongExtension() {
        String fileName1 = "extension.txt";
        JsonFile jf = new JsonFile();
        assertThrows(NottheFileWeNeed_text.class, () -> {
            jf.checkJsonFile(fileName1);
        });
    }
}
