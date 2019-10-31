import java.io.FileWriter;
import java.io.IOException;

public class MultiThreadedMergeSort extends Thread{
    int[] source;
    String threadName;
    int begin, end;

    public MultiThreadedMergeSort(String threadName, int begin, int end, int[] source) {
        this.threadName = threadName;
        this.begin = begin;
        this.end = end;
        this.source=source;
    }

    public void run() {
        try {
            sort(source, begin, end);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void merging(int [] source,int[] a1,int[] a2,int begin,int centre,int end) {
        int check1=centre-begin+1;
        int check2=end-centre;

        int c1=0;
        int c2=0;
        int c3=begin;

        while (c2 < check2 && c1 < check1)
        {
            if (a1[c1] > a2[c2])
            {
                source[c3] = a2[c2];
                c2++;
            }
            else
            {
                source[c3] = a1[c1];
                c1++;

            }
            c3++;
        }
        while (c1 < check1)
        {
            source[c3] = a1[c1];
            c1++;
            c3++;
        }
        while (c2 < check2)
        {
            source[c3] = a2[c2];
            c2++;
            c3++;
        }
    }

    private void sort(int[] source, int begin, int end) throws IOException, InterruptedException {
         if (end - begin == 0)
         {
             //BLANK
         }
         else if (end-begin==1)
         {
             FileWriter writeFile = new FileWriter(this.threadName +".txt");

             writeFile.write("Input Array: ");

             for (int k = begin; k <= end; k++) {
                 writeFile.write(" " + source[k]);
             }

             writeFile.write("\n");

             if (source[begin] > source[end]) {
                 int swapVal = source[end];

                 source[end] = source[begin];

                 source[begin] = swapVal;

             }
             writeFile.write("Sorted Array: ");

             for (int k = begin; k <= end; k++) {
                 writeFile.write(" " + source[k]);
             }
             writeFile.write("\n");

             writeFile.close();
         }
        else
        {
            FileWriter writeFile = new FileWriter(this.threadName +".txt");

            writeFile.write("Input Array: ");

            for (int k = begin; k <= end; k++) {
                writeFile.write(source[k]+" ");

            }

            writeFile.write("\n");

            int t = begin + end;

            int centre=t/2;

            //System.out.println(threadName +centre);

            String leftThread=this.threadName.concat(".l");

            String rightThread=this.threadName.concat(".r");



            MultiThreadedMergeSort left=new MultiThreadedMergeSort(leftThread,begin,centre,source);

            MultiThreadedMergeSort right=new MultiThreadedMergeSort(rightThread,centre+1,end,source);

            left.start();

            right.start();

            left.join();

            right.join();


            int t1 = centre-begin+1;

            int t2 = end-centre;

            int[] a1=new int[t1];

            int[] a2=new int[t2];

            for(int k=0;k<t1;k++)
            {
                a1[k]=source[begin+k];
            }

            for (int k=0;k<end-centre;k++)
            {
                a2[k]=source[centre+k+1];
            }

            merging(source,a1,a2,begin,centre,end);

            writeFile.write("Sorted Array: ");
            for (int k = begin; k <= end; k++)
            {
                writeFile.write(source[k]+" ");
            }

            writeFile.write("\n");

            writeFile.close();
        }
    }
}
