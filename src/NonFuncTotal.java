import java.util.Arrays;
import java.util.List;

/**
 * Created by Vikram on 7/12/2014.
 */
interface Selector {
    public boolean pick(int value);
}

class EvenSelector implements Selector {

    public boolean pick(final int value){
        return value % 2 == 0;
    }
}

public class NonFuncTotal {

    public static int totalValues(List<Integer> numbers, Selector selector){
        int result = 0;

        for(int e : numbers) {
            if(selector.pick(e)) result += e;
        }

        return result;
    }

    public static void main(String[] args) {
        List<Integer> values = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        System.out.println(
                totalValues(values, new EvenSelector())
        );
    }

}
