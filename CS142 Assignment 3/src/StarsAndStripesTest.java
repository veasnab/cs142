import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Test of CS 142 Assignment 3 by Martin Hock (Version of 11:00 PM 2/3/2019)
 * 
 * This code is for personal educational use only and may not be redistributed
 * without permission of the author.
 */
public class StarsAndStripesTest {
	private static class Shape implements Comparable<Shape> {
		enum Type {
			Line, FillRect
		};

		public final Type t;
		public final Color color;
		public int a;
		public int b;
		public int c;
		public int d;

		public Shape(Type t, Color c, int ux, int uy, int lx, int ly) {
			this.t = t;
			this.color = c;
			this.a = ux;
			this.b = uy;
			this.c = lx;
			this.d = ly;
		}

		@Override
		public int compareTo(Shape o) {
			if (this.t != o.t)
				return t.compareTo(o.t);
			if (this.color != o.color)
				return color.getRGB() - o.color.getRGB();
			if (this.b != o.b)
				return b - o.b;
			if (this.a != o.a)
				return a - o.a;
			if (this.d != o.d)
				return d - o.d;
			if (this.c != o.c)
				return c - o.c;
			return 0;
		}

		public boolean equals(Object o) {
			return compareTo((Shape) o) == 0;
		}

		public int hashCode() {
			return t.ordinal() * 13 + a * 11 + b * 7 + c * 5 + d * 3;
		}

		public static String colorString(Color c) {
			if (c == Color.WHITE)
				return "white";
			if (c == Color.RED)
				return "red";
			if (c == Color.BLUE)
				return "blue";
			throw new IllegalArgumentException();
		}

		public String toString() {
			if (t == Type.FillRect)
				return colorString(color) + " fillRect(" + a + ", " + b + ", " + c + ", " + d + ")";
			else
				return colorString(color) + " drawLine(" + a + ", " + b + ", " + c + ", " + d + ")";
		}
	}

	private static class MiniGraphics extends Graphics {

		public ArrayList<Shape> shapes = new ArrayList<Shape>();
		private Color color = null;

		@Override
		public void drawOval(int x, int y, int width, int height) {
			throw new UnsupportedOperationException();
		}

		@Override
		public Graphics create() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void translate(int x, int y) {
			throw new UnsupportedOperationException();
		}

		@Override
		public Color getColor() {
			return color;
		}

		@Override
		public void setColor(Color c) {
			if (c != Color.red && c != Color.white && c != Color.blue) {
				throw new UnsupportedOperationException("Unsupported color ");
			}
			color = c;
		}

		@Override
		public void setPaintMode() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setXORMode(Color c1) {
			throw new UnsupportedOperationException();
		}

		@Override
		public Font getFont() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setFont(Font font) {
			throw new UnsupportedOperationException();
		}

		@Override
		public FontMetrics getFontMetrics(Font f) {
			throw new UnsupportedOperationException();
		}

		@Override
		public Rectangle getClipBounds() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void clipRect(int x, int y, int width, int height) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setClip(int x, int y, int width, int height) {
			throw new UnsupportedOperationException();
		}

		@Override
		public java.awt.Shape getClip() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setClip(java.awt.Shape clip) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void copyArea(int x, int y, int width, int height, int dx, int dy) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void drawLine(int x1, int y1, int x2, int y2) {
			if (color == null) {
				throw new UnsupportedOperationException("Call setColor before calling drawLine!");
			}
			shapes.add(new Shape(Shape.Type.Line, color, x1, y1, x2, y2));
		}

		@Override
		public void drawRect(int x, int y, int width, int height) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void fillRect(int x, int y, int width, int height) {
			if (width < 0 || height < 0) {
				throw new IllegalArgumentException("width and height must not be negative");
			}
			if (color == null) {
				throw new UnsupportedOperationException("Call setColor before calling fillRect!");
			}
			shapes.add(new Shape(Shape.Type.FillRect, color, x, y, width, height));
		}

		@Override
		public void clearRect(int x, int y, int width, int height) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void fillOval(int x, int y, int width, int height) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void drawString(String str, int x, int y) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void drawString(AttributedCharacterIterator iterator, int x, int y) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor,
				ImageObserver observer) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2,
				ImageObserver observer) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2,
				Color bgcolor, ImageObserver observer) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void dispose() {
			throw new UnsupportedOperationException();
		}
	}

	private static boolean differingSubstringsContainComma(String a, String b) {
		int i = -1, j = -1;
		for (i = 0; i < a.length() && i < b.length(); i++) {
			if (a.charAt(i) != b.charAt(i))
				break;
		}
		for (j = 0; j < a.length() && j < b.length(); j++) {
			if (a.charAt(a.length() - 1 - j) != b.charAt(b.length() - 1 - j))
				break;
		}
		return a.substring(i, a.length() - j).contains(",") || b.substring(i, b.length() - j).contains(",");
	}

	public static int checkString(ArrayList<Shape> a, int index, String expected, String label) {
		if (a.size() <= index) {
			System.out.println("The " + label + " is not present.");
			return 0;
		}
		String got = a.get(index).toString();
		if (!got.equals(expected)) {
			System.out.println("For " + label + ", you drew " + got + ", but I expected " + expected);
			if (!differingSubstringsContainComma(got, expected)) {
				System.out.println("Only one item differs.");
				return 1;
			}
			return 0;
		}
		System.out.println("Correct " + label + ".");
		return 2;
	}

	public static boolean allSameWidth = true;

	private static class Star implements Comparable<Star> {
		public final int x, y, width;

		public Star(int x, int y, int width) {
			this.x = x;
			this.y = y;
			this.width = width;
		}

		@Override
		public int compareTo(Star o) {
			if (width != o.width)
				allSameWidth = false;
			if (y != o.y)
				return y - o.y;
			if (x != o.x)
				return x - o.x;
			return width - o.width;
		}

		public boolean equals(Object o) {
			return o instanceof Star && compareTo((Star) o) == 0;
		}

		public int hashCode() {
			return (width * 92821 + x) * 92821 + y;
		}
	}

	private static Star superstar(ArrayList<Shape> shapes, int index, int star) {
		if (shapes.size() < index + 5) {
			System.out.println("Not enough line segments for star " + star + "!");
			return null;
		}
		// Pull 5 line segments into array
		Shape[] a = { shapes.get(index), shapes.get(index + 1), shapes.get(index + 2), shapes.get(index + 3),
				shapes.get(index + 4) };
		// Find lower left to mid right line
		int ll = -1;
		for (int i = 0; i < 5; i++) {
			if (ll == -1 || (a[i].b >= a[ll].b && a[i].b >= a[ll].d && a[i].c >= a[ll].c && a[i].c >= a[ll].a)
					|| (a[i].d >= a[ll].b && a[i].d >= a[ll].d && a[i].a >= a[ll].c && a[i].a >= a[ll].a)) {
				ll = i;
			}
		}
		// System.out.println("Identified line " + ll + "): " + a[ll]);
		// Flip points if needed
		if (a[ll].a > a[ll].c) {
			// System.out.println("Swap points");
			int x = a[ll].a;
			a[ll].a = a[ll].c;
			a[ll].c = x;
			x = a[ll].b;
			a[ll].b = a[ll].d;
			a[ll].d = x;
		}
		// Put ll first
		if (ll != 0) {
			// System.out.println("Swap first");
			Shape x = a[ll];
			a[ll] = a[0];
			a[0] = x;
		}
		// Find next point and swap
		for (int j = 0; j < 4; j++) {
			if (a[j].t != Shape.Type.Line) {
				System.out.println("Star " + star + " contains non-line! Only lines should be drawn after starfield.");
				return null;
			}
			ll = -1;
			for (int i = j + 1; i < 5; i++) {
				if ((a[j].c == a[i].a && a[j].d == a[i].b) || (a[j].c == a[i].c && a[j].d == a[i].d)) {
					if (ll != -1) {
						System.out.println("Duplicate endpoints for lines " + (ll + 1) + " and " + (i + 1)
								+ " matching endpoint of line " + (j + 1) + " in star " + star);
						return null;
					}
					ll = i;
				}
			}
			if (ll == -1) {
				System.out.println("Did not find matching endpoint for line " + (j + 1) + " in star " + star);
				return null;
			}
			// Flip points if needed
			if (a[ll].a != a[j].c) {
				int x = a[ll].a;
				a[ll].a = a[ll].c;
				a[ll].c = x;
				x = a[ll].b;
				a[ll].b = a[ll].d;
				a[ll].d = x;
			}
			// Move to correct location in array
			if (ll != j + 1) {
				Shape x = a[ll];
				a[ll] = a[j + 1];
				a[j + 1] = x;
			}
		}
		int minX = -1, maxX = -1, minY = -1, maxY = -1;
		for (int i = 0; i < 5; i++) {
			if (i > 0) {
				if (a[i - 1].c != a[i].a || a[i - 1].d != a[i].b)
					throw new RuntimeException("Star " + star + " end point of line " + i
							+ " doesn't match start point of line " + (i + 1));
				if (i == 1) {
					minX = a[i].c;
				} else if (i == 1) {
					if (a[i].b != a[i].d) {
						throw new RuntimeException("Star " + star + " should have a straight across line");
					}
				} else if (i == 3) {
					minY = a[i].d;
					if (a[i].c - (maxX + minX) / 2 > 2) {
						throw new RuntimeException("Star " + star + " top point is not centered");
					}
				}
			} else {
				maxY = a[i].b;
				maxX = a[i].c;
			}
		}
		if (maxX < minX || maxY < minY) {
			throw new RuntimeException("Star " + star + " not drawn in order specified in assignment");
		}
		if (Math.abs((maxX - minX) - (maxY - minY)) > 0) {
			throw new RuntimeException(
					"Star " + star + " is not square): width is " + (maxX - minX) + " but height is " + (maxY - minY));
		}
		return new Star(minX, minY, maxX - minX);
	}

	public static int stars(MiniGraphics g, int a, int n, int startI, int fieldX, int fieldY, int fieldWidth,
			int fieldHeight) {
		int score = 5;
		try {
			System.out.println("Detecting grid formation of stars...");
			TreeSet<Star> stars = new TreeSet<>();
			if (startI < 1 || startI >= g.shapes.size()) {
				System.out.println("I don't see any stars!");
				return 0;
			}
			if (g.shapes.get(startI - 1).t != Shape.Type.FillRect) {
				System.out.println("Lines not starting at correct spot, backing up...");
				score -= 1;
				while (startI > 0 && g.shapes.get(startI - 1).t != Shape.Type.FillRect)
					startI--;
			}
			if (g.shapes.get(startI).t != Shape.Type.Line) {
				System.out.println("Lines not starting at correct spot, moving forward...");
				score -= 1;
				while (startI < g.shapes.size() && g.shapes.get(startI).t != Shape.Type.Line)
					startI++;
			}
			for (int i = startI; i < g.shapes.size(); i += 5) {
				Star s = superstar(g.shapes, i, (i - startI) / 5 + 1);
				if (s == null) {
					score -= 1;
				} else if (!stars.add(s)) {
					System.out.println("Star " + ((i - startI) / 5 + 1) + " redraws an earlier one!");
					score--;
				}
			}
			if (stars.size() != n) {
				System.out.println("Expected " + n + " stars but got " + stars.size());
				if (stars.size() == 0)
					return 0;
				score -= 3;
			}
			ArrayList<Star> astars = new ArrayList<>(stars);
			int width = astars.get(0).width;
			if (astars.get(0).x < fieldX || astars.get(0).y < fieldY) {
				System.out.println("First star is outside of top or left side of starfield");
				score -= 1;
			}
			if (astars.get(astars.size() - 1).x + width > fieldX + fieldWidth
					|| astars.get(astars.size() - 1).y + width > fieldY + fieldHeight) {
				System.out.println("Last star is outside of bottom or right side of starfield");
				score -= 1;
			}
			int firstX = astars.get(0).x;
			int rowY = -1;
			int rowno = 1;
			for (int i = 0; i < astars.size();) {
				if (i > 0 && astars.get(i).y - rowY != width) {
					System.out.println("Inconsistency in row spacing for row " + rowno);
					score--;
				}
				rowY = astars.get(i).y;
				if (astars.get(i).x != firstX) {
					System.out.println("Row " + rowno + " not aligned with first star");
					score--;
				}
				for (int j = 0; j < a; j++, i++) {
					if (astars.get(i).y != rowY) {
						System.out.println("Inconsistency in row y for row " + rowno);
						score--;
					}
					if (astars.get(i).width != width) {
						System.out.println("Inconsistency in width for star " + (i + 1));
						score--;
					}
					if (j > 0 && astars.get(i).x - astars.get(i - 1).x != width) {
						System.out.println("Inconsistency in horizontal spacing for star " + (i + 1));
						score--;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			score = 0;
		}
		if (score == 5)
			System.out.println("Stars look good.");
		return Math.max(score, 0);
	}

	public static void main(String[] args) {

		int totalBScore = 0, totalSScore = 0, totalFScore = 0, totalGScore = 0, totalDSScore = 0;
		try {
			try {
				int[] rows = { 2, 3, 3, 4, 4, 4, 5, 5, 7 };
				int[] cols = { 3, 4, 5, 5, 6, 7, 6, 9, 10 };

				for (int i = 0; i < rows.length; i++) {
					try {
						if (i % 2 == 0) {
							System.out.println("Creating a flag with " + rows[i] * cols[i] + " stars and 15 stripes:");
							MiniGraphics g15 = new MiniGraphics();
							StarsAndStripes.drawFlag(rows[i] * cols[i], 15, g15, 0, 0, 300, 200);
							count(g15.shapes, rows[i]*cols[i], 8);
							int bscore = 0;
							int sscore = 0;
							int fscore = 0;
							if (i == 0) {
								if (g15.shapes.size() < 10) {
									System.out.println(
											"You are not drawing the base rectangle, 8 red stripes, and a starfield!");
								}
																
								bscore += checkString(g15.shapes, 0, "white fillRect(0, 0, 300, 200)", "base");
								sscore += checkString(g15.shapes, 1, "red fillRect(0, 0, 300, 13)", "red stripe 1");
								sscore += checkString(g15.shapes, 2, "red fillRect(0, 26, 300, 13)", "red stripe 2");
								sscore += checkString(g15.shapes, 3, "red fillRect(0, 52, 300, 13)", "red stripe 3");
								sscore += checkString(g15.shapes, 4, "red fillRect(0, 78, 300, 13)", "red stripe 4");
								sscore += checkString(g15.shapes, 5, "red fillRect(0, 104, 300, 13)", "red stripe 5");
								sscore += checkString(g15.shapes, 6, "red fillRect(0, 130, 300, 13)", "red stripe 6");
								sscore += checkString(g15.shapes, 7, "red fillRect(0, 156, 300, 13)", "red stripe 7");
								sscore += checkString(g15.shapes, 8, "red fillRect(0, 182, 300, 18)", "red stripe 8");
								fscore += checkString(g15.shapes, 9, "blue fillRect(0, 0, 156, 104)", "starfield");
							}
							totalBScore += bscore;
							totalSScore += sscore;
							totalFScore += fscore;
							if (g15.shapes.size() <= 10) {
								System.out.println("You are not drawing any stars!");
							} else {
								totalGScore += stars(g15, cols[i], rows[i] * cols[i], 10, 0, 0, 156, 104);
							}
						} else {
							System.out.println(
									"Creating an offset flag with " + rows[i] * cols[i] + " stars and 13 stripes:");
							MiniGraphics g13 = new MiniGraphics();
							StarsAndStripes.drawFlag(rows[i] * cols[i], 13, g13, 300, 200, 300, 200);
							count(g13.shapes, rows[i]*cols[i], 7);
							int bscore = 0;
							int sscore = 0;
							int fscore = 0;
							if (i == 1) {
								if (g13.shapes.size() < 9) {
									System.out.println(
											"You are not drawing the base rectangle, 7 red stripes, and a starfield!");
								}
																
								bscore += checkString(g13.shapes, 0, "white fillRect(300, 200, 300, 200)", "base");
								sscore += checkString(g13.shapes, 1, "red fillRect(300, 200, 300, 15)", "red stripe 1");
								sscore += checkString(g13.shapes, 2, "red fillRect(300, 230, 300, 15)", "red stripe 2");
								sscore += checkString(g13.shapes, 3, "red fillRect(300, 260, 300, 15)", "red stripe 3");
								sscore += checkString(g13.shapes, 4, "red fillRect(300, 290, 300, 15)", "red stripe 4");
								sscore += checkString(g13.shapes, 5, "red fillRect(300, 320, 300, 15)", "red stripe 5");
								sscore += checkString(g13.shapes, 6, "red fillRect(300, 350, 300, 15)", "red stripe 6");
								sscore += checkString(g13.shapes, 7, "red fillRect(300, 380, 300, 20)", "red stripe 7");
								fscore += checkString(g13.shapes, 8, "blue fillRect(300, 200, 157, 105)", "starfield");
							}
							totalBScore += bscore;
							totalSScore += sscore;
							totalFScore += fscore;
							if (g13.shapes.size() <= 9) {
								System.out.println("You are not drawing any stars!");
							} else {
								totalGScore += stars(g13, cols[i], rows[i] * cols[i], 9, 300, 200, 158, 105);
							}
						}
					} catch (UnsupportedOperationException u) {
						System.out.println("Your code called a method other than the ones specified on the assignment sheet!");
						System.out.println("The problem occurs on line "+u.getStackTrace()[1].getLineNumber()+" of "+u.getStackTrace()[1].getClassName());
						System.out.println("Continuing tests after error...");
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Continuing tests after error...");
					}
				}
				System.out.println("Creating a flag with 48 stars and 12 stripes:");
				MiniGraphics g48 = new MiniGraphics();
				StarsAndStripes.drawFlag(48, 12, g48, 0, 0, 600, 400);
				count(g48.shapes, 48, 6);
				totalBScore += checkString(g48.shapes, 0, "white fillRect(0, 0, 600, 400)", "base");
				totalSScore += checkString(g48.shapes, 1, "red fillRect(0, 0, 600, 33)", "red stripe 1");
				totalSScore += checkString(g48.shapes, 6, "red fillRect(0, 330, 600, 33)", "red stripe 6");
				totalFScore += checkString(g48.shapes, 7, "blue fillRect(0, 0, 297, 198)", "starfield");
			} catch (UnsupportedOperationException u) {
				System.out.println("Your code called a method other than the ones specified on the assignment sheet!");
				System.out.println("The problem occurs on line "+u.getStackTrace()[1].getLineNumber()+" of "+u.getStackTrace()[1].getClassName());
				System.out.println("Continuing tests after error...");
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Testing drawStar method for three stars...");
			int[] x = { 0, 10, 20 };
			int[] y = { 0, 10, 15 };
			int[] size = { 10, 20, 22 };
			for (int i = 0; i < x.length; i++) {
				try {
					MiniGraphics g = new MiniGraphics();
					g.setColor(Color.white);
					System.out.println("Calling drawStar(g, " + x[i] + ", " + y[i] + ", " + size[i] + ")...");
					StarsAndStripes.drawStar(g, x[i], y[i], size[i]);
					Star s = superstar(g.shapes, 0, i + 1);
					if (s != null) {
						if (s.x != x[i]) {
							System.out.println("Star " + (i + 1) + " should have x coordinate " + x[i]
									+ " but instead has x coordinate " + s.x);
						} else {
							totalDSScore++;
						}
						if (s.y != y[i]) {
							System.out.println("Star " + (i + 1) + " should have y coordinate " + y[i]
									+ " but instead has y coordinate " + s.y);
						} else {
							totalDSScore++;
						}
						if (s.width != size[i]) {
							System.out.println("Star " + (i + 1) + " should have size " + size[i]
									+ " but instead has x coordinate " + s.width);
						} else {
							totalDSScore++;
						}
					}
				} catch (UnsupportedOperationException u) {
					System.out.println("Your code called a method other than the ones specified on the assignment sheet!");
					System.out.println("The problem occurs on line "+u.getStackTrace()[1].getLineNumber()+" of "+u.getStackTrace()[1].getClassName());
					System.out.println("Continuing tests after error...");
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Continuing tests after error...");
				}
			}
		} catch (UnsupportedOperationException x) {
			System.out.println("Your code called a method other than the ones specified on the assignment sheet!");
			System.out.println("The problem occurs on line "+x.getStackTrace()[1].getLineNumber()+" of "+x.getStackTrace()[1].getClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		int totalScore = totalBScore + totalSScore + totalFScore + totalGScore + totalDSScore;
		System.out.println("Base rectangle score: " + totalBScore + " / 6");
		System.out.println("Red stripe score: " + totalSScore + " / 34");
		System.out.println("Starfield score score: " + totalFScore + " / 6");
		System.out.println("Star grid score: " + totalGScore + " / 45");
		System.out.println("drawStar score: " + totalDSScore + " / 9");
		System.out.println("*** Tentative score: " + totalScore + " / 100 (Extra credit not tested)");
	}

	private static void count(ArrayList<Shape> shapes, int stars, int stripes) {
		int basecount = 0, stripecount = 0, fieldcount = 0, starlinecount = 0, othercount = 0;
		int oobasecount = 0, oostripecount = 0, oofieldcount = 0;
		for (Shape s : shapes) {
			if (s.color == Color.white && s.t == Shape.Type.FillRect) {
				basecount++;
				if (stripecount > 0 || fieldcount > 0 || starlinecount > 0) {
					oobasecount++;
				}
			}
			else if (s.color == Color.red && s.t == Shape.Type.FillRect) {
				stripecount++;
				if (fieldcount > 0 || starlinecount > 0) {
					oostripecount++;
				}
			}
			else if (s.color == Color.blue && s.t == Shape.Type.FillRect) {
				fieldcount++;
				if (starlinecount > 0) {
					oofieldcount++;
				}
			}
			else if (s.color == Color.white && s.t == Shape.Type.Line) starlinecount++;
			else othercount++;
		}
		if (basecount != 1) {
			System.out.println("There should be one base rectangle but there are "+basecount+".");
		}
		if (oobasecount > 0) {
			System.out.println("The base rectangle should be the first thing drawn but "+oobasecount+" of them got drawn after other objects.");
		}
		if (stripecount != stripes) {
			System.out.println("There should be "+stripes+" red stripe rectangles drawn but there are "+stripecount+".");
		}
		if (oostripecount > 0) {
			System.out.println("The red stripe rectangles should be the second thing drawn but "+oostripecount+" of them got drawn after the starfield or star lines.");
		}
		if (fieldcount != 1) {
			System.out.println("There should be one starfield rectangle but there are "+basecount+".");			
		}
		if (oofieldcount > 0) {
			System.out.println("The starfield rectangle should be the third thing drawn but "+oofieldcount+" of them got drawn after star lines.");
		}
		if (starlinecount != 5*stars) {
			System.out.println("There should be "+5*stars+" star lines drawn but there are "+starlinecount+".");
		}
		if (othercount > 0) {
			System.out.println("There are "+othercount+" unknown objects being drawn!");
		}
	}
}
