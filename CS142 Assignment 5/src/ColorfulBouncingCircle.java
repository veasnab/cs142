import java.awt.Color;

public class ColorfulBouncingCircle extends ColorfulCircle {
	private double xVelocity, yVelocity;

	private static double thisWidth, thisHeight;

	public ColorfulBouncingCircle(double radius, double centerX, double centerY, Color color, double xVelocity,
			double yVelocity) {
		super(radius, centerX, centerY, color);
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
	}

	public static void setPlayingFieldSize(double newWidth, double newHeight) {
		thisWidth = newWidth;
		thisHeight = newHeight;
	}

	public void tick() {
		// centerX and centerY with each time tick() method is called.
		if (getCenterX() + xVelocity >= 0 && getCenterX() + xVelocity <= thisWidth && getCenterY() + yVelocity >= 0
				&& getCenterY() + yVelocity <= thisHeight) {
			// x position should be altered according to x velocity and y position according
			// to y velocity.
			setCenterCoordinates(getCenterX() + xVelocity, getCenterY() + yVelocity);
			return;
		}
		// However, before changing the positions, check if the center position would,
		// after moving, be either less than 0 or greater than the playing field
		// dimensions. If so, instead of changing the center position, alter the
		// velocity.
		else if (getCenterX() + xVelocity < 0 || getCenterX() + xVelocity > thisWidth) {
			xVelocity = -1 * xVelocity;
			if (getCenterY() + yVelocity < 0 || getCenterY() + yVelocity > thisHeight) {
				yVelocity = -1 * yVelocity;
				return;
			}
			return;
		} else if (getCenterY() + yVelocity < 0 || getCenterY() + yVelocity > thisHeight) {
			yVelocity = -1 * yVelocity;
			if (getCenterX() + xVelocity < 0 || getCenterX() + xVelocity > thisWidth) {
				xVelocity = -1 * xVelocity;
				return;
			}
			return;
		}
	}

	// Finally: we should override the overlaps method to make balls bounce away
	// from each other.
	@Override
	public boolean overlaps(Circle c) {
		// By calling the super method to see if your circle overlaps.
		if (super.overlaps(c)) {
			// If it’s to the left or right, flip the horizontal velocity.
			if (getCenterX() >= c.getCenterX() || getCenterX() <= c.getCenterX()) {
				xVelocity = -1 * xVelocity;
			}
			// If “this circle” is above or below the “other circle,” flip the sign of “this
			// circle’s” vertical velocity.
			if (getCenterY() >= c.getCenterY() || getCenterY() <= c.getCenterY()) {
				yVelocity = -1 * yVelocity;
			}
			// As before, both may be flipped.
			else {
				xVelocity = -1 * xVelocity;
				yVelocity = -1 * yVelocity;
			}
		}
		return (super.overlaps(c));
	}

}
