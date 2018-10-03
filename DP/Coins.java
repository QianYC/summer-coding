import java.util.Arrays;

/**
 * leetcode 322
 */
public class Coins {

    /**
     *
     * dp[i]: the fewest num of coins to change i $
     *
     * dp[i]=min(dp[i-coins[j]])+1
     */
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int[] dp = new int[amount + 1];
        boolean[] flag = new boolean[amount + 1];
        dp[0] = 0;
        flag[0] = true;
        for (int i = 0; i < coins.length && coins[i] < dp.length; i++) {
            dp[coins[i]] = 1;
            flag[coins[i]] = true;
        }

        for (int i = 0; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            IF:{
                if (!flag[i]) {
                    for (int j : coins) {
                        if (i - j >= 0) {
                            if (dp[i - j] != -1 && dp[i - j] < min) {
                                min = dp[i - j] + 1;
                            }
                        } else {
                            break IF;
                        }
                    }
                } else continue;
            }
            dp[i] = min == Integer.MAX_VALUE ? -1 : min;
            flag[i] = true;
        }

        return dp[amount];
    }

    public static void main(String[] args) {
        Coins coins = new Coins();
        int[] c = new int[]{ 1,2, 5};
        System.out.println(coins.coinChange(c, 11));
    }
}
