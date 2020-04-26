/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
#include <iostream>
#include <vector>

using namespace std;
struct ListNode
    {
        int val;
        ListNode *next;
        ListNode(int x) : val(x), next(NULL) {}
    };
class Solution
{
public:
    ListNode *partition(ListNode *head, int x)
    {
        if(head == NULL || head->next == NULL){
            return head;
        }
        ListNode *p1 = head;
        while(p1->next->val < x){
            p1 = p1->next;
        }
        ListNode *p2 = p1;
        ListNode *tmp;
        while(p2->next != NULL){
            if(p2->next->val < x){
                tmp = p2->next;
                p2->next = p2->next->next;
                tmp->next = p1->next;
                p1->next = tmp;
                p1 = tmp;
            }
            p2 = p2->next;
        }
        return head;
    }

    void test(){
        ListNode* list = new ListNode(3);
        cout << list->val << endl;
        delete list;
    }
    
};

int main(){
    Solution solution;
    solution.test();
    return 0;
}