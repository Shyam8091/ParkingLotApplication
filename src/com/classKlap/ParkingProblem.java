package com.classKlap;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * @author SShyam
 */
public class ParkingProblem {
	private static Integer size = 0;
	private static Integer slotNumber = 0;
	private static boolean notFound = true;
	private static Scanner scanner = new Scanner(System.in);
	private static TreeSet<Integer> treeSet = new TreeSet<Integer>();
	private static Map<Integer, LinkedHashMap<String, String>> map = new LinkedHashMap<Integer, LinkedHashMap<String, String>>();

	public static void main(String[] args) {

		int sizeOfParkingLot = sizeOfParkingLot();
		for (int i = 1; i <= sizeOfParkingLot; i++) {
			treeSet.add(i);
		}
		while (true) {
			commands();
		}

	}

	static int sizeOfParkingLot() {
		System.out.println("Please enter size of your parking lot");

		try {
			size = Integer.parseInt(scanner.nextLine());
			if (size < 1) {
				System.out.println("Can not have size 0");
				sizeOfParkingLot();
			}
		} catch (NumberFormatException numberFormatException) {
			System.out.println("Only Numbers");
			sizeOfParkingLot();
		}

		return size;
	}

	static void commands() {
		int input = 0;
		System.out.println("1 Park");
		System.out.println("2 Leave");
		System.out.println("3 Status");
		System.out.println("4 Find cars by colour");
		System.out.println("5 Find slot by colour");
		System.out.println("6 Enter registration number to find slot");
		try {
			input = Integer.parseInt(scanner.nextLine());
		}

		catch (NumberFormatException numberFormatException) {
			System.out.println("Invalid command!");
		}
		switch (input) {
		case 1:
			if (treeSet.size() > 0) {
				LinkedHashMap<String, String> valSet = new LinkedHashMap<String, String>();
				System.out.println("Enter registration number and colour");
				try {
					String[] carDetails = scanner.nextLine().trim().split(" ");
					valSet.put(carDetails[0], carDetails[1]);
					map.put(treeSet.first(), valSet);
					System.out.println("Allocated slot Number " + treeSet.first());
					treeSet.remove(treeSet.first());
				} catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
					System.out.println("Please enter valid registration number and colour (KA-**-*** Black)");
				}

			} else {
				System.out.println("Sorry, parking lot is full");

			}

			break;

		case 2:
			System.out.println("Enter slot number ");
			try {
				slotNumber = Integer.parseInt(scanner.nextLine());
				if (treeSet.contains(slotNumber)) {
					System.out.println("Invalid slot! No car found at this slot");
				} else if (slotNumber > size) {
					System.out.println("We dont have this slot number in out parking");
				} else {
					treeSet.add(slotNumber);
					System.out.println("Slot Number " + slotNumber + " is free");
					map.put(slotNumber, new LinkedHashMap<String, String>());
				}
			} catch (NumberFormatException numberFormatException) {
				System.out.println("Only Integer value");
			}
			break;

		case 3:
			System.out.println("SLOT    Number         color");
			for (Map.Entry<Integer, LinkedHashMap<String, String>> vals : map.entrySet()) {
				if (vals.getValue().size() > 0) {
					System.out.print(vals.getKey());
					LinkedHashMap<String, String> outerKey = vals.getValue();
					for (Map.Entry<String, String> nestedEntity : outerKey.entrySet()) {
						System.out.print("\t" + nestedEntity.getKey());
						System.out.print("\t" + nestedEntity.getValue());
						System.out.println();
					}
				}
			}
			break;

		case 4:
			System.out.println("Enter colour to find registration number");
			String givenColor = scanner.nextLine();
			for (Map.Entry<Integer, LinkedHashMap<String, String>> vals : map.entrySet()) {
				LinkedHashMap<String, String> outerKey = vals.getValue();
				for (Map.Entry<String, String> nestedEntity : outerKey.entrySet()) {
					if (nestedEntity.getValue().equalsIgnoreCase(givenColor)) {
						notFound = false;
						System.out.println(nestedEntity.getKey());
					}
				}
			}
			if (notFound) {
				System.out.println("No record found");
			}
			notFound = true;
			break;

		case 5:
			System.out.println("Enter color to find slot");
			String color = scanner.nextLine();
			for (Map.Entry<Integer, LinkedHashMap<String, String>> vals : map.entrySet()) {
				LinkedHashMap<String, String> outerKey = vals.getValue();
				for (Map.Entry<String, String> nestedEntity : outerKey.entrySet()) {
					if (nestedEntity.getValue().equalsIgnoreCase(color)) {
						System.out.println(vals.getKey());
						notFound = false;
					}
				}
			}
			if (notFound) {
				System.out.println("No record found");
			}
			notFound = true;
			break;

		case 6:
			System.out.println("Enter registration number to find slot");
			String registrationNumber = scanner.nextLine();
			for (Map.Entry<Integer, LinkedHashMap<String, String>> vals : map.entrySet()) {
				LinkedHashMap<String, String> outerKey = vals.getValue();
				for (Map.Entry<String, String> nestedEntity : outerKey.entrySet()) {
					if (nestedEntity.getKey().equalsIgnoreCase(registrationNumber)) {
						System.out.println(vals.getKey());
						notFound = false;
					}
				}
			}
			if (notFound) {
				System.out.println("No record found");
			}
			notFound = true;
			break;

		default:
			System.out.println("Please enter Numeric digit between 1 - 6");
			break;
		}

	}

}
