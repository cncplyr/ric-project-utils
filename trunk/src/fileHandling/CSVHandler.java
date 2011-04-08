package fileHandling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import opencsv.CSVReader;
import opencsv.CSVWriter;



/**
 * Uses openCSV 2.2 - http://opencsv.sourceforge.net/
 * 
 * Provides methods to interact simply with csv files, wrapping around methods
 * from the openCSV 2.2 library.
 * 
 * @author Richard Jenkin
 * 
 */
public class CSVHandler {
	CSVWriter writer;
	private String csvFolder = "output";
	private String fileName = "boundingBoxes";

	/**
	 * Reads the currently selected file and returns it. Will probably fail if
	 * entries are not <code>int</code>s.
	 * 
	 * @return The requested file in the form of a <code>List</code> of arrays
	 *         of <code>int</code>s. Each array represents a single row.
	 */
	public List<int[]> readCSVint() {
		// TODO: Change this to read each line in turn, then add to
		// convertedEntries, saving the creation of storedEntries?
		// Might be more memory efficient
		CSVReader reader;
		// The strings pulled from file
		List<String[]> storedEntries = new ArrayList<String[]>();
		// Each string converted to int.
		List<int[]> convertedEntries = new ArrayList<int[]>();

		// Read the entire file into a List.
		try {
			reader = new CSVReader(new FileReader(getCSVFolder() + File.separator + getFileName() + ".csv"));
			storedEntries = reader.readAll();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// The current list contains strings, so we convert them.
		int size = storedEntries.get(0).length;
		for (String[] row : storedEntries) {
			int[] convertedRow = new int[size];
			for (int i = 0; i < size; i++) {
				convertedRow[i] = Integer.parseInt(row[i]);
			}
			convertedEntries.add(convertedRow);
		}

		return convertedEntries;
	}

	/**
	 * Reads the currently selected file and returns it.
	 * 
	 * @return The requested file in the form of a List of arrays of strings.
	 *         Each array represents a single row.
	 */
	public List<String[]> readCSV() {
		CSVReader reader;
		List<String[]> myEntries = new ArrayList<String[]>();
		try {
			reader = new CSVReader(new FileReader(getCSVFolder() + File.separator + getFileName() + ".csv"));
			myEntries = reader.readAll();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return myEntries;
	}

	/**
	 * Opens a <code>CSVWriter</code> to write to, using the current output
	 * folder and file name.
	 * 
	 * This method must be called before writeCSVLine() can be called.
	 * 
	 */
	public void openCSVStream() {
		try {
			writer = new CSVWriter(new FileWriter(getCSVFolder() + File.separator + getFileName() + ".csv"), ',');
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Closes the current writer.
	 */
	public void closeCSVStream() {
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sends a single row to be written to the csv file.
	 * 
	 * @param entries
	 *            The array of integers to write to file.
	 */
	public void writeCSVLine(int[] entries) {
		int size = entries.length;
		String[] convertedEntries = new String[size];
		for (int i = 0; i < size; i++) {
			convertedEntries[i] = Integer.toString(entries[i]);
		}
		writer.writeNext(convertedEntries);
	}

	public void writeCSVLine(List<Integer> entries) {
		int size = entries.size();
		String[] convertedEntries = new String[size];
		for (int i = 0; i < size; i++) {
			convertedEntries[i] = Integer.toString(entries.get(i));
		}
		writer.writeNext(convertedEntries);
	}

	public String getCSVFolder() {
		return csvFolder;
	}

	public String getFileName() {
		return fileName;
	}

	public void setCSVFolder(String csvFolder) {
		this.csvFolder = csvFolder;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
