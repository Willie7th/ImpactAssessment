package numberrangesummarizer;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class UnitTests{

    private final NumberSummarizerMain summarizer = new NumberSummarizerMain();


    /*ASSUMPTIONS
    - Numbers have to be integers and cannot be negative
    - The original list can have repeated numbers
    - The original list does not have to be ordered
    - Original list will always be integer followed by comma
     */
    @Test
    public void collectMethod_ValidInput() {  //Checks ordering and handling duplicates
        String input = "1,3,5,2,4,5,3,6,7,8";
        Collection<Integer> result = summarizer.collect(input);
        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8), result);
    }

    @Test
    public void collectMethod_EmptyInput() {
        assertThrows(IllegalArgumentException.class, () -> summarizer.collect(""));
    }

    @Test
    public void collectMethod_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> summarizer.collect(null));
    }

    @Test
    public void summarizeMethod_ValidInput1() {  //Tests with first number being part of sequence and last number not
        Collection<Integer> input = List.of(1, 2, 3, 6, 7, 8, 10, 11, 13);
        String summary = summarizer.summarizeCollection(input);
        assertEquals("1-3, 6-8, 10-11, 13", summary);
    }

    @Test
    public void summarizeMethod_ValidInput2() {  //Tests with last number being part of sequence and first number not
        Collection<Integer> input = List.of(1, 3, 4, 9, 10);
        String summary = summarizer.summarizeCollection(input);
        assertEquals("1, 3-4, 9-10", summary);
    }

    @Test
    public void summarizeMethod_TwoSeqNumber() {
        Collection<Integer> input = List.of(1, 2);
        String summary = summarizer.summarizeCollection(input);
        assertEquals("1-2", summary);
    }

    @Test
    public void summarizeMethod_SingleNumber() {
        Collection<Integer> input = List.of(5);
        String summary = summarizer.summarizeCollection(input);
        assertEquals("5", summary);
    }

    @Test
    public void summarizeMethod_EmptyCollection() {
        Collection<Integer> input = List.of();
        String summary = summarizer.summarizeCollection(input);
        assertEquals("", summary);
    }

    @Test
    public void summarizeMethod_NonSequentialNumbers() {
        Collection<Integer> input = List.of(2, 4, 6, 8, 10);
        String summary = summarizer.summarizeCollection(input);
        assertEquals("2, 4, 6, 8, 10", summary);
    }

    @Test
    public void isSequentialMethod() {
        assertTrue(summarizer.isSequential(1, 2));
        assertFalse(summarizer.isSequential(1, 3));
    }
}
