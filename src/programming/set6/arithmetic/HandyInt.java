package programming.set6.arithmetic;

/*
Modifications made to this class:

Before, this class was mutable. Now it is immutable.

Before, when someone called add, the actual HandyInt was modified and returned.
The reason for this being that java is strictly pass by value (meaning: a copy
is passed) but when passing an object, a copy of the REFERENCE to the actual
object is passed, resulting in the object that a variable is referring to
being the same.

Now, when someone calls add, a new HandyInt which is the sum is returned.

The old behaviour led to the output being tied to a factor/exp that was previously modified.
Now it is not modified anymore due to its immutability.
 */

/**
 * An integer that provides arithmetic operations for great glory.
 */
public class HandyInt {
    /** The integer represented by an instance of this class. */
    private final int theInt;

    /**
     * Constructs a new handy integer representing the given int.
     *
     * @param theInt
     *            the integer to be represented by this instance.
     */
    public HandyInt(int theInt) {
        this.theInt = theInt;
    }

    /**
     * Constructs a new handy integer representing the integer represented by
     * the given handy integer.
     *
     * @param handyInt
     *            the handy integer to intialize the new object with.
     */
    public HandyInt(HandyInt handyInt) {
        this.theInt = handyInt.theInt;
    }

    /**
     * Returns the integer represented by this instance.
     *
     * @return the represented integer.
     */
    public int getInt() {
        return theInt;
    }

    /**
     * Returns a handy integer that represents the sum of this and the other
     * handy integer.
     *
     * @param other
     *            the handy integer to add.
     * @return sum of the two handy integers.
     */
    public HandyInt add(HandyInt other) {
        return new HandyInt(theInt+other.theInt);
    }

    /**
     * Returns a handy integer that represents the result of subtracting the
     * other integer from this one.
     *
     * @param other
     *            the handy integer to subtract from this one.
     * @return difference of the two handy integers.
     */
    public HandyInt sub(HandyInt other) {
        return new HandyInt(theInt-other.theInt);
    }

    @Override
    public String toString() {
        return Integer.toString(theInt);
    }
}