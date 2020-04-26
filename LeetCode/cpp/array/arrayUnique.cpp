#include <iostream>
#include <set>
#include <vector>
#include <map>
#include <algorithm>

using namespace std;

class Solution {
public:
    int minIncrementForUnique(vector<int>& A) {
        if(A.size() == 0){
            return 0;
        }
        set<int> set;
        sort(A.begin(), A.end());

        // if(A.size() == 0){
        //     return 0;
        // }
        // int res = 0;
        // set<int> tmp;
        // int max = A[0];
        // for(int a : A){
        //     //a在tmp中
        //     while(tmp.count(a)){
        //         res += max - a + 1;
        //         a = max + 1;
        //     }
        //     max = max > a ? max : a;
        //     tmp.insert(a);
        // }
        // return res;
    }
};

int main(){
    cout << "h";
    struct student{
        string name;
        int age;
    };
    struct student stu;
    stu.age = 10;
    cout << "hello";
    cout<< stu.age;
    string s = "hhh";
    s.substr(2);
    return 0;
}