//
// $Id$

package java.io;

import java.io.IOException;

/**
 * A minimal version of {@code Writer} to satisfy GWT.
 */
public abstract class Writer
{
    public abstract void write (String text) throws IOException;
}
