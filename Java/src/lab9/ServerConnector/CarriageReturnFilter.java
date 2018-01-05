package lab9.ServerConnector;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

class CarriageReturnFilter extends FilterWriter {

    public CarriageReturnFilter(Writer out) {
        super(out);
    }

    public void write(int c) throws IOException {
        if( c == '\n' )
            out.write('\r');
        out.write(c);
    }

}