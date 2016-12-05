package programming.set6.quiz;

import acm.program.ConsoleProgram;
import acm.util.RandomGenerator;


public class MathQuiz extends ConsoleProgram {
    private static String[] successMessages = { "You did it!", "Whoop whoop!",
            "That's correct!", "You got this going, eh" };

    private static String[] failMessages = { "Incorrect. %d is correct", "Try again, it should be %d",
            "Dumbo.. %d is the answer!", "Not far off, but it should have been %d." };

    private RandomGenerator generator;

    private static int MAXIMUM_NUMBER = 20;

    public void run() {
        generator = new RandomGenerator();

        println("Be prepared. This is not gonna be easy!");

        for (int i = 0; i < 5; i++) {
            int operand1;
            int operand2;

            String operator = generator.nextBoolean() ? "+" : "-";
            operator = "-";

            int minimum = operator.equals("-") ? 1 : 0;

            operand1 = generator.nextInt(minimum, MAXIMUM_NUMBER);
            operand2 = generator.nextInt(1, (operator.equals("-") ? operand1 : MAXIMUM_NUMBER -operand1));

            int result = readInt(String.format("What is %d %s %d? ", operand1, operator, operand2));
            if (this.isCorrect(operand1, operator, operand2, result)) {
                println(generateSuccessMessage());
            } else {
                println(generateFailMessage(this.getResult(operand1, operator, operand2)));
            }
        }
    }


    /**
     * Checks whether the given result is really the result of the operation on
     * the two operands.
     *
     * @param operand1
     *            the first operand.
     * @param operator
     *            the operator, given as a String. This must be either
     *            {@code "+"} or {@code "-"}. Otherwise, this method will always
     *            return {@code false}.
     * @param operand2
     *            the second operand.
     * @param result
     *            the proposed result.
     * @return {@code true} if {@code result} is really the result of the
     *         calculation, {@code false} otherwise.
     */
    public boolean isCorrect(int operand1, String operator, int operand2, int result) {
        Integer actualResult = getResult(operand1, operator, operand2);
        return actualResult != null && actualResult == result;
    }

    public Integer getResult(int operand1, String operator, int operand2) {

        if (operator.equals("+")) {
            return operand1 + operand2;
        } else if (operator.equals("-")) {
            return operand1 - operand2;
        }
        return null;
    }

    /**
     * Returns a message that can be displayed to the user after successfully
     * solving a problem. There are at least four messages the method randomly
     * chooses from to keep it interesting.
     *
     * @return randomly selected success message.
     */
    private String generateSuccessMessage() {
        return successMessages[
                generator.nextInt(0, successMessages.length-1)
                ];
    }

    /**
     * Returns a message that can be displayed to the user after failing to
     * solve a problem correctly. There are at least four messages the method
     * randomly chooses from to keep it interesting. The correct result is
     * included in the message somewhere to teach the user a lesson.
     *
     * @param correctResult
     *            the number that would have been the correct result. This
     *            number is included somehow in the returned messsage.
     * @return randomly selected fail message.
     */
    private String generateFailMessage(int correctResult) {
        return String.format(
                failMessages[generator.nextInt(0, failMessages.length-1)],
                correctResult);
    }
}
