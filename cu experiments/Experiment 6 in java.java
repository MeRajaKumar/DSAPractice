import java.util.*;

public class SubsetSumSolver {

    public static void findSubsets(int[] set, int target) {
        List<Integer> currentSubset = new ArrayList<>();
        List<Integer> binaryMask = new ArrayList<>(Collections.nCopies(set.length, 0));
        List<List<Integer>> allSolutions = new ArrayList<>();
        List<List<Integer>> allBinaryMasks = new ArrayList<>();

        findSubsetsHelper(set, 0, target, currentSubset, binaryMask, allSolutions, allBinaryMasks);

        if (allSolutions.isEmpty()) {
            System.out.println("No subset found with the given sum.");
        } else {
            System.out.println("Subsets with sum " + target + " are:");
            for (int i = 0; i < allSolutions.size(); i++) {
                System.out.println("Subset: " + allSolutions.get(i) + " | Binary: " + allBinaryMasks.get(i));
            }
        }
    }

    private static void findSubsetsHelper(int[] set, int index, int target,
                                          List<Integer> currentSubset, List<Integer> binaryMask,
                                          List<List<Integer>> allSolutions, List<List<Integer>> allBinaryMasks) {

        if (target == 0) {
            allSolutions.add(new ArrayList<>(currentSubset));
            allBinaryMasks.add(new ArrayList<>(binaryMask));
            return;
        }

        if (index >= set.length || target < 0) {
            return;
        }

        // Include current element
        currentSubset.add(set[index]);
        binaryMask.set(index, 1);
        findSubsetsHelper(set, index + 1, target - set[index], currentSubset, binaryMask, allSolutions, allBinaryMasks);

        // Backtrack and exclude current element
        currentSubset.remove(currentSubset.size() - 1);
        binaryMask.set(index, 0);
        findSubsetsHelper(set, index + 1, target, currentSubset, binaryMask, allSolutions, allBinaryMasks);
    }

    public static void main(String[] args) {
        // Example input
        int[] set = {1, 2, 5, 6, 8};
        int targetSum = 9;

        findSubsets(set, targetSum);
    }
}
