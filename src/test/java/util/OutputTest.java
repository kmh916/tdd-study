package util;

import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;

public class OutputTest {

    protected ByteArrayOutputStream output;

    @BeforeEach
    public void setUpStreams() {
        output = new ByteArrayOutputStream();
    }
}
