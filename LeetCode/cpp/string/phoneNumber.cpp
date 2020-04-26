#include <iostream>
#include <vector>
#include <string>

using namespace std;

class Solution
{
public:
    vector<string> letterCombinations(string digits)
    {
        vector<string> res;
        if(digits.size() == 0){
            return res;
        }
        for(int i = 0; i < letters[digits[0] - '0' - 2].size(); i++){
            string s(1, letters[digits[0] - '0' - 2][i]);
            res.push_back(s);
        }
        for(int i = 1; i < digits.size(); i++){
            letter(digits[i] - '0' - 2, res);
        }
        return res;
    }

private:
    void letter(int index, vector<string>& res)
    {
        vector<string> res1;
        int len = res.size();
        while(res.size() != 0)
        {
            string tmp = res.back();
            for (int j = 0; j < letters[index].size(); j++)
            {
                string s(1, letters[index][j]);
                res1.push_back(tmp + s);
            }
            res.pop_back();
        }
        res = res1;
    }
    vector<string> letters = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
};

int main()
{
    Solution solution;
    vector<string> v = solution.letterCombinations("235");
    for(string s : v){
        cout << s << endl;
    }
    return 0;
}