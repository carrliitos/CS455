/* @author Benzon Carlitos Salazar
*	This program will contain getters and setters used for accessing each letter
*	in the alphabet, key, and/or the plaintext
*/

import java.awt.Point;

public class Letter {
	private final String LETTER;
	private boolean usedLetter;
	private Point point;

	Letter(String alphabet) {
		this.LETTER = alphabet;
		// indicates that the letter has not been used in the tableau
		this.usedLetter = false;
		this.point = new Point();
	}

	public void setUsed(boolean usedLetter) { this.usedLetter = usedLetter; }
	public void setPoint(Point point) { this.point = point; }

	public boolean getUsed() { return usedLetter; }
	public String getLetter() { return LETTER; }
	public Point getPoint() { return point; }
}