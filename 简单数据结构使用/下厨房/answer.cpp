#include<iostream>
#include<set>
#include<string>

using namespace std;
/*
本题就是找不重复元素的个数，一下子就想到了java的HashSet
奈何Scanner就是报no line found，转而使用c++

1. set的使用 insert()、size()、没有add()！
2. cin特性：读取两个非空白字符间的内容（我还看了好久c++的split实现……不过这一块确实不会……）
*/
int main(){
    string line;
    set<string> s;
    while(cin>>line){
        s.insert(line);
    }
    cout<<s.size();
}
