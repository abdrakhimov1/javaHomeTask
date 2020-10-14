import java.io.IOException;
import java.io.OutputStream;

public class ReportImpl implements Report {

    private final byte[] report;

    public ReportImpl(byte[] report) {
        this.report = report;
    }

    @Override
    public byte[] asBytes() {
        return report;
    }

    @Override
    public void writeTo(OutputStream os) throws IOException {
        os.write(report);
    }
}
