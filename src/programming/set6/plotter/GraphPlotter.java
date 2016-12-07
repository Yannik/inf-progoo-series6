package programming.set6.plotter;

import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;

public class GraphPlotter extends GraphicsProgram {

    /** Width of the drawing. */
    private static final int WIDTH = 600;
    /** Height of the drawing. */
    private static final int HEIGHT = 400;
    /** Min x coordinate. */
    private static final double MIN_X = -1.5;
    /** Max x coordinate. */
    private static final double MAX_X = 5.5;
    /** X tick interval. */
    private static final double X_TICK_INTERVAL = 1;
    /** Min y coordinate. */
    private static final double MIN_Y = -2.5;
    /** Max y coordinate. */
    private static final double MAX_Y = 2.5;
    /** Y tick interval. */
    private static final double Y_TICK_INTERVAL = 1;

    public void run() {
        setSize(WIDTH, HEIGHT);
        drawYAxis();
        drawXAxis();
        drawRects(-0.6, 4, 0.2, false);
        drawRects(-0.6, 4, 0.2, true);
        plotGraph();
    }

    /**
     * The function whose area to calculate.
     *
     * @param x
     *            the x coordinate.
     * @return the value of f at the x coordinate.
     */
    public double f(double x) {
        // This is the function whose area we want to calculate. Hardcoding
        // it here is a bit unfortunate; we can improve the design once we
        // know more about classes and interfaces
        return Math.sin(x) + Math.cos(0.5 * x);
    }

    /**
     * Calculates the height of a rectangle at the given x coordinate and with
     * the given width. The height of the rectangle is the function value at its
     * left and right side that is nearer to the x axis. If the function value
     * is negative, the height is negative.
     *
     * @param x
     *            the rectangle's x coordinate (left boundary).
     * @param width
     *            the rectangle's width.
     * @return the rectangle's height, which may actually be negative.
     */
    public double minRectHeight(double x, double width) {
        double leftSide = f(x);
        double rightSide = f(x + width);
        if (Math.abs(leftSide) < Math.abs(rightSide)) {
            return leftSide;
        }
        return rightSide;
    }

    /**
     * Calculates the height of a rectangle at the given x coordinate and with
     * the given width. The height of the rectangle is the function value at its
     * left and right side that is further from the x axis. If the function value
     * is negative, the height is negative.
     *
     * @param x
     *            the rectangle's x coordinate (left boundary).
     * @param width
     *            the rectangle's width.
     * @return the rectangle's height, which may actually be negative.
     */
    public double maxRectHeight(double x, double width) {
        double leftSide = f(x);
        double rightSide = f(x + width);
        if (Math.abs(leftSide) > Math.abs(rightSide)) {
            return leftSide;
        }
        return rightSide;
    }

    /**
     * Calculates the effective x screen coordinate for the given x coordinate
     * in the graph's coordinate system.
     *
     * @param x
     *            the x coordinate.
     * @return the screen coordinate.
     */
    public double xToScreen(double x) {
        double xTotal = Math.abs(MIN_X) + Math.abs(MAX_X);
        return x * (WIDTH / xTotal) + Math.abs(MIN_X)/xTotal * WIDTH;
    }

    /**
     * Calculates the coordinate in the coordinate system for the given x
     * coordinate on the screen.
     *
     * @param screenX
     *            screen x coordinate.
     * @return the coordinate system coordinate.
     */
    public double screenToX(double screenX) {
        double xTotal = Math.abs(MIN_X) + Math.abs(MAX_X);
        return (screenX - Math.abs(MIN_X)/xTotal * WIDTH) / (WIDTH/xTotal);
    }

    /**
     * Calculates the effective y screen coordinate for the given y coordinate
     * in the graph's coordinate system.
     *
     * @param y
     *            the y coordinate.
     * @return the screen coordinate.
     */
    public double yToScreen(double y) {
        double yTotal = Math.abs(MIN_Y) + Math.abs(MAX_Y);
        // the real y value increases if the graph's y decreases, so we use -y,
        // or the graph would be inverted
        return -y * (HEIGHT/yTotal) + (Math.abs(MIN_Y) / yTotal) * HEIGHT;
    }

    /**
     * Sets up the x axis of the coordinate system. The method assumes that the
     * coordinate system's top left corner is at (0, 0). Its width and height as
     * well as the tick marks are controlled through the constants defined in
     * this class.
     */
    public void drawXAxis() {
        GLine xAxis = new GLine(0, yToScreen(0), WIDTH, yToScreen(0));
        add(xAxis);

        double x = 0;
        while (true) {
            x += X_TICK_INTERVAL;

            double[] xVals = {x, -x};
            for (double xVal : xVals) {
                if (xVal < MAX_X && xVal > MIN_X) {
                    GLine tick = new GLine(xToScreen(xVal), yToScreen(0) - 2, xToScreen(xVal), yToScreen(0) + 2);
                    GLabel label = new GLabel(String.format("%.1f", xVal));
                    add(tick);
                    add(label, elementHorizontalCenter(label, xToScreen(xVal)), yToScreen(0) + 15);
                }
            }

            if (x > MAX_X && -x < MIN_X) {
                break;
            }
        }
    }

    /**
     * Sets up the y axis of the coordinate system. The method assumes that the
     * coordinate system's top left corner is at (0, 0). Its width and height as
     * well as the tick marks are controlled through the constants defined in
     * this class.
     */
    public void drawYAxis() {
        GLine yAxis = new GLine(xToScreen(0), 0, xToScreen(0), HEIGHT);
        add(yAxis);

        double y = 0;
        while (true) {
            y += Y_TICK_INTERVAL;

            double[] yVals = {y, -y};
            for (double yVal: yVals) {
                if (yVal < MAX_Y && yVal > MIN_Y) {
                    GLine tick = new GLine(xToScreen(0) - 2, yToScreen(yVal), xToScreen(0) + 2, yToScreen(yVal));
                    GLabel label = new GLabel(String.format("%.1f", yVal));
                    add(tick);
                    add(label, xToScreen(0) - 30, yToScreen(yVal));
                }
            }

            if (y > MAX_X && -y < MIN_X) {
                break;
            }
        }
    }

    /**
     * Plots the graph by iterating over all x coordinates and generating
     * points. A point is only added if it's in the visible area. Points are
     * visualized through tiny rectangles with a side length of 1.
     */
    public void plotGraph() {
        for (double i = 0; i < WIDTH; i++) {
            GRect rect = new GRect(1, 1);
            add(rect, i, yToScreen(f(screenToX(i))));
        }
    }

    /**
     * Draws the rectangles used to approximate the graph's area. All inputs are
     * specified in the graph's coordinate system.
     *
     * @param minX
     *            leftmost rectangle coordinate.
     * @param maxX
     *            rightmost rectangle coordinate.
     * @param rectWidth
     *            width of the rectangles.
     * @param minRects
     *            if {@code true}, the min rects are drawn; otherwise, the max
     *            rects are drawn.
     */
    public void drawRects(double minX, double maxX, double rectWidth, boolean minRects) {
        for (double x = minX; x <= maxX; x+=rectWidth) {
            double actualRectWidth = rectWidth;
            if (x + rectWidth > maxX) {
                actualRectWidth = maxX - x;
            }
            double y;
            Color color;
            if (minRects) {
                y = minRectHeight(x, actualRectWidth);
                color = new Color(100, 170, 240, 80);
            } else {
                y = maxRectHeight(x, actualRectWidth);
                color = new Color(255,255,255);
            }

            GRect maxRect = new GRect(xToScreen(actualRectWidth) - xToScreen(0), Math.abs(yToScreen(0) - yToScreen(y)));
            maxRect.setFillColor(color);
            maxRect.setFilled(true);
            maxRect.setColor(new Color(100, 170, 240, 255));
            if (y > 0) {
                add(maxRect, xToScreen(x), yToScreen(y));
            } else {
                add(maxRect, xToScreen(x), yToScreen(0));
            }
        }
    }

    public double elementHorizontalCenter(GObject object, double x) {
        return (x - object.getWidth() / 2);
    }

    public double elementVerticalCenter(GObject object, double y) {
        return (y - object.getHeight() / 2);
    }

}
