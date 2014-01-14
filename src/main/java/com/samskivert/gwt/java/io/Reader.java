//
// $Id$

package java.io;

import java.io.IOException;

/**
 * A minimal version of {@code Reader} to satisfy GWT.
 */
public abstract class Reader
{
    public abstract int read () throws IOException;
}
