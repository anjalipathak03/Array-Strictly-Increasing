import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the length of arr1: ");
        int n = scanner.nextInt();

        int[] arr1 = new int[n];
        System.out.println("Enter the elements of arr1:");
        for (int i = 0; i < n; i++) {
            arr1[i] = scanner.nextInt();
        }

        System.out.print("Enter the length of arr2: ");
        int m = scanner.nextInt();

        int[] arr2 = new int[m];
        System.out.println("Enter the elements of arr2:");
        for (int i = 0; i < m; i++) {
            arr2[i] = scanner.nextInt();
        }

        int answer = makeArrayIncreasing(arr1, arr2);
        System.out.println("Minimum number of operations: " + answer);
    }

    public static int makeArrayIncreasing(int[] arr1, int[] arr2) {
        Arrays.sort(arr2);

        int answer = dfs(0, -1, arr1, arr2);

        return answer < 2_001 ? answer : -1;
    }

    static Map<Map.Entry<Integer, Integer>, Integer> dp = new HashMap<>();

    private static int dfs(int i, int prev, int[] arr1, int[] arr2) {
        if (i == arr1.length) {
            return 0;
        }
        if (dp.containsKey(new AbstractMap.SimpleEntry<>(i, prev))) {
            return dp.get(new AbstractMap.SimpleEntry<>(i, prev));
        }

        int cost = 2_001;

        // If arr1[i] is already greater than prev, we can leave it be.
        if (arr1[i] > prev) {
            cost = dfs(i + 1, arr1[i], arr1, arr2);
        }

        // Find a replacement with the smallest value in arr2.
        int idx = bisectRight(arr2, prev);

        // Replace arr1[i], with a cost of 1 operation.
        if (idx < arr2.length) {
            cost = Math.min(cost, 1 + dfs(i + 1, arr2[idx], arr1, arr2));
        }

        dp.put(new AbstractMap.SimpleEntry<>(i, prev), cost);
        return cost;
    }

    private static int bisectRight(int[] arr, int value) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] <= value) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
