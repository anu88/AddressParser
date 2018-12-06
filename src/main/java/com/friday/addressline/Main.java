package com.friday.addressline;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	private static String[] addresses = { "Winterallee 3", "Musterstrasse 45", "Blaufeldweg 123B", "Am Bächle 23",
			"Auf der Vogelwiese 23 b", "4, rue de la revolution", "200 Broadway Av", "Calle Aduana, 29",
			"Calle 39 No 1540" };

	public static void main(String[] args) {
		Arrays.stream(addresses).forEach(address -> AddressResolver.resolve(address));
		try (Scanner scanner = new Scanner(System.in)) {
			String rawAddress;
			System.out.println("PLEASE ENTER A RAW ADDRESS STRING.....\n");
			while (!(rawAddress = scanner.nextLine()).isEmpty()) {
				try {
					AddressResolver.resolve(rawAddress);
				} catch (IllegalArgumentException ex) {
					System.out.println(ex.getMessage());
					System.out.println();
					continue;
				}
				System.out.println("\nONE MORE ADDRESS.....\n");
			}
		} 

	}
}
