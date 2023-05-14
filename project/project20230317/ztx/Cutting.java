package dynamic_programming;

import java.security.SecureClassLoader;

public class Cutting {

    public static int maxProfit(int length,int[] price){
        if(length == 0)
            return 0;
        int ans = 0;
        for(int i=1;i<=length;i++)
            ans = Math.max(ans,price[i] + maxProfit(length-i,price));
        return ans;
    }

    public static int maxProfit2(int length,int[] price){
        if(length <= 0)
            return 0;
        int[] dp = new int[length+1];
        for(int i=1;i<=length;i++){
            for(int j=1;j<=i;j++)
                dp[i] = Math.max(dp[i],price[j] + dp[i-j]);
        }
        return dp[length];
    }

    public static int maxProfit(int length,int[] price,int cut){
        if(length == 0)
            return 0;
        int ans = 0;
        for(int i=1;i<length;i++)
            ans = Math.max(ans,price[i] + maxProfit(length-i,price,cut) - cut);
        ans = Math.max(ans,price[length]);
        return ans;
    }

    public static int minMatrixMultiple(int[] scale,int i,int j){
        if(i == j)
            return 0;
        int ans = Integer.MAX_VALUE;
        for(int k=i;k<j;k++)
            ans = Math.min(ans,scale[i] * scale[k+1] * scale[j] + minMatrixMultiple(scale,i,k) + minMatrixMultiple(scale,k+1,j));
        return ans;
    }

    public static int minMatrixMultiple(int[] scale){
        int n = scale.length - 1;
        int[][] dp = new int[n][n];
        int[][] K = new int[n][n];
        for(int i=n-2;i>=0;i--){
            for(int j=i+1;j<n;j++){
                dp[i][j] = Integer.MAX_VALUE;
                for(int k=i;k<j;k++){
                    int t = scale[i] * scale[k+1] * scale[j+1] + dp[i][k] + dp[k+1][j];
                    if(dp[i][j] > t){
                        K[i][j] = k;
                        dp[i][j] = t;
                    }
                }
            }
        }
        printBracket(K,0,n-1);
        return dp[0][n-1];
    }

    public static void printBracket(int[][] K,int i,int j){
        if(i == j)
            System.out.print("A"+i);
        else {
            System.out.print("(");
            printBracket(K,i,K[i][j]);
            printBracket(K,K[i][j]+1,j);
            System.out.print(")");
        }
    }

    public static void main(String[] args) {
        int[] scale = {30,35,15,5,10,20,25};
        System.out.println(minMatrixMultiple(scale));
    }
}
