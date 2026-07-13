package ModuleTests.hw;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FilesMerge {
    public static void mergeFiles(String path1, String path2, String pathResult) {
        try(FileOutputStream fos = new FileOutputStream(pathResult);
        FileInputStream fis = new FileInputStream(path1);
        FileInputStream fis2 = new FileInputStream(path2)) {
            byte[] buffer = new byte[8192];
            int bytesread;
            while((bytesread = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesread);
            }

            while ((bytesread = fis2.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesread);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
