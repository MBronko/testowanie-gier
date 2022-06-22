package zad_3_test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import zadania.FileExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestFileExtension {
    private final String path = "textfile.txt";

    @Test
    @ExtendWith(FileExtension.class)
    void test(@FileExtension.File(path = path) String content) {
        try {
            Assertions.assertEquals(Files.readString(Path.of(path)), content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
