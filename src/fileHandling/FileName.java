package fileHandling;

public class FileName {

	/**
	 * Take a name and an Id number, and appends them. Adds leading zeros to the
	 * number to create a 5-digit number.
	 * 
	 * @param name
	 *            The filename to use.
	 * @param imageID
	 *            The file ID number to use.
	 * @return The complete name in the correct format.
	 */
	public static String formatFileName(String filename, int imageID, String fileType) {
		if (imageID > 9999) {
			filename += Integer.toString(imageID) + fileType;
		} else if (imageID > 999) {
			filename += "0" + Integer.toString(imageID) + fileType;
		} else if (imageID > 99) {
			filename += "00" + Integer.toString(imageID) + fileType;
		} else if (imageID > 9) {
			filename += "000" + Integer.toString(imageID) + fileType;
		} else {
			filename += "0000" + Integer.toString(imageID) + fileType;
		}
		return filename;
	}
}
