package edu.grinnell.glimmer.mist.parser;

import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Encapsulates a HashMap and an Integer counter
 * @author Albert Owusu-Asare
 * @since 1.0
 *
 */
public class Table {

	HashMap<HashNode, Integer> map;
	Integer availableNumber;

	/**
	 * Constructs a <code>table</code>
	 * @param map a HashMap of key <code>HashNode</code> value:
	 *            <code>Integer</code>
	 */
	public Table(HashMap<HashNode, Integer> map) {
		this.map = map;
		this.availableNumber = 1;
	}// Table

	/**
	 * Increments the available Number
	 */

	public void increment() {
		this.availableNumber++;
	}//

	/**
	 * Returns the available number
	 */
	public Integer getAvailableNumber() {
		return availableNumber;
	}// getAvailableNumber()

	public static void main(String[] args) {
		PrintWriter pen = new PrintWriter(System.out, true);
		HashMap<HashNode, Integer> map = new HashMap<HashNode, Integer>();
		Table table = new Table(map);

		// Testing increment
		pen.println("Before increment():" + table.getAvailableNumber());
		table.increment();
		pen.println("After increment():" + table.getAvailableNumber());

	}

}// Table
