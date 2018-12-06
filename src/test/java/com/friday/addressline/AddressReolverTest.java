package com.friday.addressline;

import java.util.Arrays;
import java.util.Collection;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class AddressReolverTest {

	private String rawAddress;
	private Address processedAddress;

	public AddressReolverTest(String rawAddress, Address processedAddress) {
		this.rawAddress = rawAddress;
		this.processedAddress = processedAddress;
	}

	@Parameters(name = "{index}: testResolveAddress({0}) = {1}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { 
				//simple cases
				{ "Winterallee 3", new Address("3", "Winterallee") },
				{ "Musterstrasse 45", new Address("45", "Musterstrasse") },
				{ "Blaufeldweg 123ä", new Address("123ä", "Blaufeldweg") },
				//complicated cases
				{ "Am Bächle 23", new Address("23", "Am Bächle") },
				{ "Auf der Vogelwiese 23 b", new Address("23 b", "Auf der Vogelwiese") },
				{ "4, rue de la revolution", new Address("4", "rue de la revolution") },
				//complex cases
				{ "200 Broadway Av", new Address("200", "Broadway Av") },
				{ "Calle Aduana, 29", new Address("29", "Calle Aduana") },
				{ "Calle 39 No 1540", new Address("No 1540", "Calle 39") },
				// more complicated cases
				{ "No 1540 Calle 39", new Address("No 1540", "Calle 39") },
				{ "23 b, Auf der Vogelwiese", new Address("23 b", "Auf der Vogelwiese") },
				{ "123B Blaufeldweg", new Address("123B", "Blaufeldweg") }
		});
	}

	@Test
	public void testResolveAddress() {
		Assert.assertThat(AddressResolver.resolve(rawAddress), Is.is(processedAddress));
	}
}
