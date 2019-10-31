import java.util.Scanner;

public class mainClass {

    public static void main(String[] args) throws InterruptedException{

        int length = 0;
        Scanner input= new Scanner(System.in);
        System.out.println("What is the size of your array?");
        length = input.nextInt();
        int[] source = new int[length];
        System.out.println("enter your digits:");
        for(int i=0;i<length;i++)
        {
            source[i]=input.nextInt();
        }

        String threadName="T";
        MultiThreadedMergeSort mergeSortObject=new MultiThreadedMergeSort(threadName,0,source.length-1,source);
        mergeSortObject.start();
        mergeSortObject.join();
        System.out.println("Array Sorted in Ascending Order.");
    }
}
