import java.util.HashMap;
import java.util.Map;

public class ContinousSum {

    /**
     * dp[i][j]: the sum from i to j
     * dp[i][j]=dp[i][j-1]+[j] || dp[i+1][j]+[i]
     * <p>
     * 23 2 6 4 7
     * 23 * * * 42
     * *  2 * * 19
     * *  * 6 * 17
     * *  * * 4 11
     * *  * * * 7
     * <p>
     * memory limit exceed !
     * <p>
     * dp[i]: the sum fro 0 to i,so dp[j]-dp[i-1]=dp[i][j]!
     *
     * 二维数组会超内存
     * 使用set无法排除len=1的子序列
     */

    public boolean dp(int[] nums, int k) {
        int len = nums.length;
        int[] dp = new int[len + 1];
        Map<Integer, Integer> map = new HashMap<>();
        dp[0] = 1;
        map.put(k == 0 ? 1 : 1 % k, -1);
        for (int i = 0; i < len; i++) {
            dp[i + 1] = dp[i] + nums[i];
            int remain = k == 0 ? dp[i + 1] : dp[i + 1] % k;
            if (map.get(remain) != null) {
                if (i - map.get(remain) > 1) {
                    return true;
                }
            } else {
                map.put(remain, i);
            }
        }
        return false;
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        int len = nums.length;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = nums[i];
        }

        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = dp[i][j - 1] + nums[j];
                if (k == 0 && dp[i][j] == 0 || k != 0 && dp[i][j] % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {

        int[] t1 = {0, 1, 0};

        ContinousSum continousSum = new ContinousSum();
        System.out.println(continousSum.dp(t1, 0));
    }
}
