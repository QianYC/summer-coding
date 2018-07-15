#include <iostream>
#include <climits>
using namespace std;
/*
此题有明显的可递归的特征，考虑到时空限制，故用迭代代替递归
而动态规划就是找出问题的状态空间以及状态转移方程，给定初始状态求出终止状态（有种迭代的味道对吧），因此用DP

一开始我考虑建立n*n的矩阵，A[i][j]表示ai和aj额乘积，但是这好像并没有什么卵用，我们的矩阵应该要和迭代的步数有关系
于是建立了n*k的矩阵，A[i][j]表示前i个数，长度为j+1的序列的最大乘积，但是这样有个缺陷，我们无法判断这个序列包含了哪些元素，也就是说，某个极大的元素会在A[i][j]表示的序列中重复出现，我们需要给A[i][j]赋予更多的信息
看了网上的思路，最后用A[i][j]表示前i个数，必定包含ai，长度为j+1的序列的最大乘积，由此得出状态转移方程：A[i][j]=max(ai*A[i-1][j-1],ai*A[i-2][j-1],...,ai*A[i-d][j-1])

此题的坑有三：
1. 能力值可以为负，故用两个矩阵记录最大值和最小值，状态转移方程要稍加修改
2. int范围不够
3. 二维数组又不会使用了……
*/
int main(){
    int n,k,d;
    int*a;
    long **A;long **B;
    cin>>n;
    a=new int[n];
    for(int i=0;i<n;i++)
        cin>>a[i];
    cin>>k>>d;
    A=new long*[n];
    B=new long*[n];
    for(int i=0;i<n;i++){
        A[i]=new long[k];
        B[i]=new long[k];
    }

    for(int i=0;i<n;i++){
        A[i][0]=a[i];
        B[i][0]=a[i];
    }


    for(int i=0;i<k;i++)
        for(int j=i+1;j<k;j++){
            A[i][j]=LONG_MIN;
            B[i][j]=LONG_MAX;
        }

    for(int i=1;i<n;i++)
        for(int j=1;j<=i&&j<k;j++){
            if(a[i]>0){
                long max=A[i-1][j-1];
                for(int m=1;m<=d;m++){
                    if(i-m>=j-1&&A[i-m][j-1]>max)
                        max=A[i-m][j-1];
                }
                A[i][j]=a[i]*max;

                long min=B[i-1][j-1];
                for(int m=1;m<=d;m++){
                    if(i-m>=j-1&&B[i-m][j-1]<min)
                        min=B[i-m][j-1];
                }
                B[i][j]=a[i]*min;
            }else{
                long min=B[i-1][j-1];
                for(int m=1;m<=d;m++){
                    if(i-m>=j-1&&B[i-m][j-1]<min)
                        min=B[i-m][j-1];
                }
                A[i][j]=a[i]*min;

                long max=A[i-1][j-1];
                for(int m=1;m<=d;m++){
                    if(i-m>=j-1&&A[i-m][j-1]>max)
                        max=A[i-m][j-1];
                }
                B[i][j]=a[i]*max;
            }
        }

    long max=A[0][k-1];
    for(int i=k-1;i<n;i++)
        if(A[i][k-1]>max)
            max=A[i][k-1];

    cout<<max;
}
