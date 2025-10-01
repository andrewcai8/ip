package grace;

public class Parser {
    private static int parseIndex(String input, int taskCount) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index >= 0 && index < taskCount) {
                return index;
            }
        } catch (Exception e) {
            return -1;
        }
        return -1;
    }



}
