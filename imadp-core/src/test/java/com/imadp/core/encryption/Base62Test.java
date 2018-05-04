package com.imadp.core.encryption;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.imadp.core.test.IMADPCoreTestCase;

/**
 * Base62Test
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Base62Test extends IMADPCoreTestCase {

	@Test
	public void encodeAndDecode() {
		for(long i = 0; i<10000000; i++)
		{
			String encoded = Base62.encode(i);
			long decoded = Base62.decode(encoded);

			assertTrue(String.valueOf(i).length() >= encoded.length());
			assertEquals(i, decoded);
		}
	}

}
