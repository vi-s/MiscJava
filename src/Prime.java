import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by Vikram on 7/12/2014.
 */
public class Prime {

    public static boolean isGreaterThan3(int number) {
        return number > 3;
    }

    public static void main(String[] args){
        //find the double of the first even number greater than 3
        List<Integer> values = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        Predicate<Integer> isDivisibleBy2 = number -> number % 2 == 0;
        Predicate<Integer> isGT3 = number -> number > 3;

        //Functional interface that returns a predicate based on an input.
        //First generic param is input type, second input param is the Predicate return type
        Function<Integer, Predicate<Integer>> isGreaterThan = pivot ->
                number -> number > pivot;
        Function<Integer, Predicate<Integer>> isDivisible = factor ->
                number -> number % factor == 0;

        System.out.println(
                //each step is evaluated lazily.
                //nothing is evaluated unless a terminal operator, like findFirst() is called
                values.stream()
                        .filter(Prime::isGreaterThan3) //Method Reference; could also use the lambda: e -> e > 3
                        .filter(isDivisibleBy2) //equivalently, isDivisible.apply(2)
                        .map(e -> e * 2) //filter and map are intermediary operations
                        .findFirst() //findFirst, reduce, and collect are terminal operations
                        .get()
        );

//        int result = 0;

//        for(int e : values){
//            if(e > 3 && e%2==0){
//                result = e * 2;
//                break;
//            }
//        }



//        System.out.println(isPrime(1));
//        System.out.println(isPrime(2));
//        System.out.println(isPrime(3));
//        System.out.println(isPrime(4));
    }


//    private static boolean isPrime(final int number){
//        Predicate<Integer> isDivisible = divisor -> number % divisor == 0;
//
//        return number > 1 && IntStream.range(2, number)
//                                        .noneMatch(index -> isDivisible(index));
//    }
}
