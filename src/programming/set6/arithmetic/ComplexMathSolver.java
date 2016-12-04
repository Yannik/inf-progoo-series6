package programming.set6.arithmetic;

import acm.program.ConsoleProgram;

public class ComplexMathSolver extends ConsoleProgram {
    @Override
    public void run() {
        HandyInt factor = new HandyInt(5);
        HandyInt exp = new HandyInt(3);

        HandyInt result = multiplyWithPowerOfTwo(factor, exp);

        println(factor + " * 2^" + exp + " = " + result);
    }

    /**
     * Returns the result of multiplying {@code factor} with 2 to the power of
     * {@code exp}.
     *
     * @param factor
     *            the handy integer to multiply with the power.
     * @param exp
     *            the exponent of the power.
     * @return the result of the multiplication.
     */
    private HandyInt multiplyWithPowerOfTwo(HandyInt factor, HandyInt exp) {
        HandyInt one = new HandyInt(1);

        while (exp.getInt() > 0) {
            factor = factor.add(factor);
            exp = exp.sub(one);
        }

        return factor;
    }
}