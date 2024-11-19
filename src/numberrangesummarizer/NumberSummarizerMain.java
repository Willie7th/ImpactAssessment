package numberrangesummarizer;

import java.util.*;

public class NumberSummarizerMain implements NumberRangeSummarizer {

    @Override
    public Collection<Integer> collect(String input) {
        //Throw exception if no or empty list
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("List cannot be null or empty");
        }

        Scanner scLine = new Scanner(input).useDelimiter(",");
        Set<Integer> numSet = new TreeSet<>();

        while(scLine.hasNext())
        {
            int num = scLine.nextInt();
            numSet.add(num);
        }

        //Convert to a list to order it
        List<Integer> list = new ArrayList<>(numSet);
        Collections.sort(list);
        return list;
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        //If list is null or empty return empty string
        if (input == null || input.isEmpty()) {
            return "";
        }

        List<Integer> list = new ArrayList<>(input);

        //If list contains a single element then return it
        if(list.size() == 1)
            return list.get(0) + "";

        StringBuilder result = new StringBuilder();
        int maxPosition = input.size();

        for (int i = 0; i < maxPosition; i++) {
            int currentNum = list.get(i);

            if(i == 0) { //If looking at the first number in the list, add the number to the summary and if the next number is not sequential add a comma
                result.append(currentNum);
                int nextInt = list.get(i+1);

                if(!isSequential(currentNum, nextInt)) //If next int in list is not sequential nextInt != num+1
                    result.append(", ");

                continue;
            }

            //If looking at final int add it to the list with a - in front if it follows the previous number
            if (i == maxPosition - 1) {
                int prevInt = list.get(i - 1);

                if(isSequential(prevInt, currentNum))
                    result.append("-");

                result.append(currentNum);
                continue;
            }

            //No out of bounds exception as edge cases are taken care of before
            int nextInt = list.get(i+1);
            int prevInt = list.get(i-1);

            //If current number is the last in a sequence of sequential numbers then append with a dash in front
            if(isSequential(prevInt, currentNum) && !isSequential(currentNum, nextInt))
            {
                result.append("-").append(currentNum);
                result.append(", ");
            }

            //If the current number is not following sequentially then append, and if the next number is not sequential then add a comma
            if(!isSequential(prevInt, currentNum)) //(list.get(i - 1) != currentNum - 1 && list.get(i + 1) == currentNum + 1)
            {
                result.append(currentNum);

                if(!isSequential(currentNum, nextInt))
                    result.append(", ");
            }
        }

        return result.toString();
    }

    public boolean isSequential(int a, int b)
    {
        return b == a + 1;
    }

    public static void main(String[] args) {
        //String test = "1,3,4,5,2,5,3,6,7,8,8,12,11,13,17,19,44,56,45,55,68";
        String test = "1,6,3,3,16,17";

        String input = test; //String input = args[1];

        NumberSummarizerMain summarizer = new NumberSummarizerMain();

        Collection<Integer> list = summarizer.collect(input);

        System.out.println("Collected numbers: " + list);

        String summary = summarizer.summarizeCollection(list);

        System.out.println("Final summary: " + summary);
    }
}
