import java.io.IOException;
import java.io.OutputStream;

interface Report {
    byte[] asBytes();
    void writeTo(OutputStream os) throws IOException;
}
