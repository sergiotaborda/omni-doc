package org.omnidoc.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtils {

	public static void copy(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024 * 4]; //4 Kb
		int n = 0;
		while (-1 != (n = in.read(buffer))) {
			out.write(buffer, 0, n);
		}
		out.flush();

		out.close();
		in.close();
	}

}
