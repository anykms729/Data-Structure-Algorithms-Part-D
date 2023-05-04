import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TABA {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Stock[] stocks = readStockData();

        // Invoke question 1 (a): Read Stock CSV file into memory using the Buffered reader
//        try {
//            for (Stock stock : stocks) {
//                System.out.println(stock);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // Invoke question 1 (b): Write a recursive method to compute the sum of weight column for Stock CSV file
        System.out.println("Sum of weight column for Stock CSV file: " + sumWeights(stocks, 0));

        // Invoke question 1 (c): Write an iterative method to find the largest value in weight column from part (b)
        System.out.println("Largest value of weight column for Stock CSV file: " + findLargestWeight(stocks));


        // Invoke question 1 (d): Write a sorting method for all possible columns in the data file. Your application should allow the user the option to sort the array using any column
        System.out.print("Enter the column to sort by (1-6): ");
        int columnChoice = scanner.nextInt();
        // sort the array based on user's choice
        mergeSort(stocks, columnChoice);
         for (Stock stock : stocks) {
            System.out.println(stock);
        }

        // Invoke question 4 (b)
//        sortAndRemoveMultiplesOfFiveImplementationWithoutMultiThreading();

        // Invoke question 4 (c)
//        sortAndRemoveMultiplesOfFiveImplementationWithMultiThreading();
    }

    // Question 1 (a)
    public static Stock[] readStockData() throws IOException {

        // parsing and reading the CSV file data into the stock (object) array
        // provide the path here...
        File directory = new File("./");
        String name = directory.getAbsolutePath() + "//Stock.csv";
        BufferedReader br = new BufferedReader(new FileReader(name));
        Stock[] stocks = new Stock[10000];

        // This will just print the header in CSV file
        br.readLine();

        int i = 0;
        String st;

        while ((st = br.readLine()) != null) // Read the file line by line
        {
            String[] data = st.split(",");
            stocks[i++] = new Stock(Integer.parseInt(data[0]), Float.parseFloat(data[1]), Float.parseFloat(data[2]),
                    data[3], Float.parseFloat(data[4]), data[5]);
        }
        br.close(); // close the BufferedReader
        return stocks;
    }


    static class Stock implements Comparable<Stock> {

        private int stockNo;
        private float stockSize;
        private float profit;
        private String productType;
        private float weight;
        private String productName;


        // Constructor
        public Stock(int stockNo, float stockSize, float profit, String productType, float weight, String productName) {
            super();
            this.stockNo = stockNo;
            this.stockSize = stockSize;
            this.profit = profit;
            this.productType = productType;
            this.weight = weight;
            this.productName = productName;
        }

        // setters and getters
        public int getStockNo() {
            return stockNo;
        }

        public void setStockNo(int stockNo) {
            this.stockNo = stockNo;
        }

        public float getStockSize() {
            return stockSize;
        }

        public void setStockSize(float stockSize) {
            this.stockSize = stockSize;
        }

        public float getProfit() {
            return profit;
        }

        public void setProfit(float profit) {
            this.profit = profit;
        }

        public float getWeight() {
            return weight;
        }

        public void setWeight(float weight) {
            this.weight = weight;
        }

        public String getProductType() {
            return productType;
        }

        public void setProductType(String productType) {
            this.productType = productType;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        @Override
        public String toString() {
            return "Stock{" +
                    "stockNo=" + stockNo +
                    ", stockSize=" + stockSize +
                    ", profit=" + profit +
                    ", productType='" + productType + '\'' +
                    ", weight=" + weight +
                    ", productName='" + productName + '\'' +
                    '}';
        }

        @Override
        public int compareTo(Stock other) {
            // default to sorting by stockNo
            return compareTo(other, 1);
        }

        public int compareTo(Stock other, int choice) {
            switch (choice) {
                case 1: // compare based on stockNo
                    return this.stockNo - other.stockNo;
                case 2: // compare based on stockSize
                    return Float.compare(this.stockSize, other.stockSize);
                case 3: // compare based on profit
                    return Float.compare(this.profit, other.profit);
                case 4: // compare based on productType
                    return this.productType.compareTo(other.productType);
                case 5: // compare based on weight
                    return Float.compare(this.weight, other.weight);
                case 6: // compare based on productName
                    return this.productName.compareTo(other.productName);
                default:
                    throw new IllegalArgumentException("Invalid choice");
            }
        }
    }

    // Question 1 (b)
    public static float sumWeights(Stock[] stocks, int index) {
        // base case: if we've reached the end of the array, return 0
        if (index == stocks.length) {
            return 0;
        } else {
            // recursive case: add the weight of the current stock to the sum of the weights of the remaining stocks
            return stocks[index].getWeight() + sumWeights(stocks, index + 1);
        }
    }

    // Question 1 (c)
    public static float findLargestWeight(Stock[] stocks) {
        float largestWeight = stocks[0].getWeight();
        for (int j = 1; j < stocks.length; j++) {
            if (stocks[j].getWeight() > largestWeight) {
                largestWeight = stocks[j].getWeight();
            }
        }
        return largestWeight;
    }

    // Question 1 (d)
    public static void mergeSort(Stock[] arr, int choice) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int mid = arr.length / 2;
        Stock[] left = Arrays.copyOfRange(arr, 0, mid);
        Stock[] right = Arrays.copyOfRange(arr, mid, arr.length);
        mergeSort(left, choice);
        mergeSort(right, choice);
        merge(arr, left, right, choice);
    }

    private static void merge(Stock[] arr, Stock[] left, Stock[] right, int choice) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i].compareTo(right[j], choice) < 0) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while (i < left.length) {
            arr[k++] = left[i++];
        }
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }


    // Question 1 (e)


    // Question 4 (b)
    public static void sortAndRemoveMultiplesOfFiveImplementationWithoutMultiThreading() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the total number of arrays you want to create: ");
        int numOfArrays = sc.nextInt();

        // Create arrays based on user's input
        List<int[]> arrays = new ArrayList<>();
        for (int i = 0; i < numOfArrays; i++) {
            System.out.println("");
            System.out.print("Enter the size of array " + (i + 1) + ": ");
            int size = sc.nextInt();
            int[] array = new int[size];

            // Add element for each array
            for (int j = 0; j < size; j++) {
                System.out.print("Element " + j + " of array " + (i + 1) + ": ");
                array[j] = sc.nextInt();
            }
            System.out.println("");
            arrays.add(array);
        }

        List<int[]> sortedArrays = sortAndRemoveMultiplesOfFiveWithoutMultiThreading(numOfArrays, arrays);

        for (int i = sortedArrays.size() - 1; i >= 0; i--) {
            int[] arr = sortedArrays.get(i);
            System.out.println(Arrays.toString(arr));
        }
    }

    // Question 4 (b)
    public static List<int[]> sortAndRemoveMultiplesOfFiveWithoutMultiThreading(int numOfArrays, List<int[]> arraysList) {
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < numOfArrays; i++) {
            int[] arr = arraysList.get(i);
            int size = 0;
            for (int num : arr) {
                if (num % 5 != 0) {
                    size++;
                }
            }
            int[] filteredArr = new int[size];
            int index = 0;
            for (int num : arr) {
                if (num % 5 != 0) {
                    filteredArr[index] = num;
                    index++;
                }
            }
            Arrays.sort(filteredArr);
            result.add(filteredArr);
        }

        // Sort the result list based on the length of sub-arrays
        class ArrayLengthComparator implements Comparator<int[]> {
            @Override
            public int compare(int[] a, int[] b) {
                return a.length - b.length;
            }
        }

        Collections.sort(result, new ArrayLengthComparator());
        return result;
    }

    // Question 4 (c)
    public static void sortAndRemoveMultiplesOfFiveImplementationWithMultiThreading() {
//        int numOfArrays = 3;
//        int[] array1 = {98, 6, 2};
//        int[] array2 = {5, 6, 7, 4, 10, 1};
//        int[] array3 = {10, 20, 2, 3};

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the total number of arrays you want to create: ");
        int numOfArrays = sc.nextInt();

        // Create arrays based on user's input
        List<int[]> arrays = new ArrayList<>();
        for (int i = 0; i < numOfArrays; i++) {
            System.out.println("");
            System.out.print("Enter the size of array " + (i + 1) + ": ");
            int size = sc.nextInt();
            int[] array = new int[size];

            // Add element for each array
            for (int j = 0; j < size; j++) {
                System.out.print("Element " + j + " of array " + (i + 1) + ": ");
                array[j] = sc.nextInt();
            }
            System.out.println("");
            arrays.add(array);
        }

        List<int[]> sortedArrays = null;
        try {
            sortedArrays = sortAndRemoveMultiplesOfFiveWithMultiThreading(numOfArrays, arrays);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (int i = sortedArrays.size() - 1; i >= 0; i--) {
            int[] arr = sortedArrays.get(i);
            System.out.println(Arrays.toString(arr));
        }
    }

    // Question 4 (c)
    public static List<int[]> sortAndRemoveMultiplesOfFiveWithMultiThreading(int numOfArrays, List<int[]> arraysList) throws InterruptedException {
        List<int[]> result = new ArrayList<>();
        Thread[] threads = new Thread[numOfArrays];

        // Create a thread for each sub-array
        for (int i = 0; i < numOfArrays; i++) {
            int[] arr = arraysList.get(i);
            int size = 0;
            for (int num : arr) {
                if (num % 5 != 0) {
                    size++;
                }
            }
            int[] filteredArr = new int[size];
            int index = 0;
            for (int num : arr) {
                if (num % 5 != 0) {
                    filteredArr[index] = num;
                    index++;
                }
            }

            // Create a new thread for sorting and storing the filtered array
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    Arrays.sort(filteredArr);
                    synchronized (result) {
                        result.add(filteredArr);
                    }
                }
            });
            threads[i] = t;
            t.start();
        }

        // Wait for all threads to finish
        for (Thread t : threads) {
            t.join();
        }

        // Sort the result list based on the length of sub-arrays
        class ArrayLengthComparator implements Comparator<int[]> {
            @Override
            public int compare(int[] a, int[] b) {
                return a.length - b.length;
            }
        }

        Collections.sort(result, new ArrayLengthComparator());
        return result;
    }
}
