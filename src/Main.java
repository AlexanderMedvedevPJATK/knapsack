public class Main {
    public static void main(String[] args) {
        int[] weights = {3, 1, 6, 10, 1, 4, 9, 1, 7, 2, 6, 1, 6, 2, 2, 4, 8, 1, 7, 3, 6, 2, 9, 5, 3, 3, 4, 7, 3, 5, 30, 50};
        int[] values = {7, 4, 9, 18, 9, 15, 4, 2, 6, 13, 18, 12, 12, 16, 19, 19, 10, 16, 14, 3, 14, 4, 15, 7, 5, 10, 10, 13, 19, 9, 8, 5};
        int maxWeight = 75;

        System.out.println(bruteForceKnapsack(weights, values, maxWeight));

//        TESTING OUTPUT

//        int value = 0;
//        int weight = 0;
//        String result = "11011100011111110110110001111";
//        result = "00011110001101101111111000111011";
//        for (int i = 0; i < result.length(); i++) {
//            if (result.charAt(i) == '1') {
//                System.out.println(values[i]);
//                value += values[i];
//                weight += weights[i];
//            }
//        }
//        System.out.println(value);
//        System.out.println(weight);


    }

    private static Result bruteForceKnapsack(int[] weights, int[] values, int maxWeight) {
        // each bit represents item being in or out of knapsack
        long knapsack = 0;

        int maxValue = 0;
        long maxValueCombination = 0;
        int maxValueCombinationWeight = 0;

        while (knapsack < Math.pow(2, weights.length)) {
            knapsack++;
            String selectedItems = Long.toBinaryString(knapsack);
            int weight = 0;
            int value = 0;
            for (int i = selectedItems.length() - 1; i >= 0; i--) {
                if (selectedItems.charAt(i) == '1') {
                    weight += weights[i];
                    value += values[i];
                }
            }
            if (weight <= maxWeight && value > maxValue) {
                maxValue = value;
                maxValueCombination = knapsack;
                maxValueCombinationWeight = weight;
            }
        }

        return new Result(Long.toBinaryString(maxValueCombination), maxValueCombinationWeight, maxValue);
    }

    record Result(String knapsack, int weight, int value) { }
}