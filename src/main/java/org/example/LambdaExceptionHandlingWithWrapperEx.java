package org.example;

// **Enhance the below application to  EFFECTIVELY IMPLEMENT NULLPOINTEREXCETPION  / SCORECANNOTBENEGATIVEEXCEPTION(CHECKED) / ALL OTHER EXCPETIONS**

import org.example.exceptions.ScoreCannotBeNegativeException;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class LambdaExceptionHandlingWithWrapperEx {
    public static void main(String[] args) {
//      exceptionHandlingInLambda();
        exceptionHandlingInLambdaWithwrapper();
    }

    private static void exceptionHandlingInLambda() {
        List<Integer> scores = Arrays.asList(150, 180, 120, 140);
        List<Integer> scores2 = Arrays.asList(150, 0, 120, 140);
        // divide 500 by these scores...
//        scores.forEach(score -> System.out.println(500/score));
//        System.out.println("generate exception....");

        scores = Arrays.asList(50, 180, 0, 140);
//        conciseness of the lambdo had gone for a toss.....
        scores.forEach(score -> {
            try {
                System.out.println(500 / score);
            } catch (ArithmeticException arithmeticException) {
                System.out.println("Check for zero values : " + arithmeticException.getMessage());
            }
        });
        System.out.println("Reptitive code.....");
        Consumer<Integer> scoreConsumer = score -> {
            try {
                System.out.println(500 / score);
            } catch (ArithmeticException arithmeticException) {
                System.out.println("Check for zero values : " + arithmeticException.getMessage());
            }
        };
        scores2.forEach(scoreConsumer);
    }

    private static void exceptionHandlingInLambdaWithwrapper() {
        //ENHANCEMENT LAB : EFFECTIVELY IMPLEMENT NULLPOINTEREXCETPION  / SCORECANNOTBENEGATIVEEXCEPTION(CHECKED) / ALL OTHER EXCPETIONS
        List<Integer> scores = Arrays.asList(150, -50, null, 0);
        scores.forEach(wrapperForLambdaWithExceptionHandler(score -> System.out.println(1000 / score)));
    }

    public static <T extends Integer> Consumer<T> wrapperForLambdaWithExceptionHandler(Consumer<T> consumer){

        Consumer<T> consumerWithExceptionHandling = (source) -> {
            try{
                if(source.intValue() < 0){
                    throw new ScoreCannotBeNegativeException("NegativeInteger");
                }
                consumer.accept(source);
            }catch(Throwable e){
                if(e instanceof NullPointerException) {
                    System.out.println("Null detected");
                }else if (e instanceof ArithmeticException){
                    System.out.println("Check for zero values : " + e.getMessage());
                }else if(e instanceof ScoreCannotBeNegativeException){
                    System.out.println("Value cannot be negative");
                }else {
                    System.out.println("Caught:"+ e.getClass().toString());
                }
            }
        };
        return consumerWithExceptionHandling;
    }

    //    The wrapper handles exception in an elegant manner with lambda
//    inputs : score, Consumer<Integer> to Consume the Integer
//    return : a consumer that is capable of handling ArithmeticException
    static Consumer<Integer> wrapperForLamdaWithArithmeticException(Consumer<Integer> scoreConsumer) {
        //wrapping the consumer for excpetion handling capabilities....
        Consumer<Integer> scoreConsumerWithArithmeticExceptionHandlingCapabilites = (score) -> {
            try {
                scoreConsumer.accept(score);
            } catch (ArithmeticException arithmeticException) {
                System.out.println("Check for zero values : " + arithmeticException.getMessage());
            }
        };
        return scoreConsumerWithArithmeticExceptionHandlingCapabilites;
    }

}
