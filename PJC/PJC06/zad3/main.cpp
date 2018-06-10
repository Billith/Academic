#include <iostream>
#include <functional>

struct Node {
    int     data;
    Node*   next = nullptr;
};

void printList (const Node* head) {

    if(head == nullptr) {
        std::cout << "Empty list" << std::endl;
        return;
    }

    std::cout << head->data << " ";
    Node* current = head->next;
    while(true) {
        std::cout << current->data << " ";
        if(current->next == nullptr)
            break;
        current = current->next;
    }
    std::cout << std::endl;
}

void add(Node*& head, int data) {

    Node* current = head;
    Node* newNode = new Node{data, nullptr};

    if(head == 0) {
        head = new Node{data, nullptr};
    }

    if(head->data >= data) {
        head = newNode;
        head->next = current;
    } else {
        current = head;
        while(true) {
            if(head->next == nullptr) {
                head->next = newNode;
                newNode->next = nullptr;
                break;
            }

            if(current->next == nullptr && current->data <= data) {
                current->next = newNode;
                break;
            }

            if(current->data <= data && current->next->data >= data && current->next != nullptr) {
                Node* tmp;
                if (current->next == nullptr)
                    tmp = nullptr;
                else
                    tmp = current->next;

                current->next = newNode;
                newNode->next = tmp;
                break;
            }
            current = current->next;
        }
    }
}

bool any(const Node* head, std::function<bool(int)> pred) {

    if(pred(head->data) == true)
        return true;

    Node* current = head->next;

    while(true) {
        if(pred(current->data) == true)
            return true;
        if(current->next == nullptr) {
            return false;
        }
        current = current->next;
    }
}

bool all(const Node* head, std::function<bool(int)> pred) {

    if(pred(head->data) == false)
        return false;

    Node* current = head->next;

    while(true) {
        if(pred(current->data) == false)
            return false;
        if(current->next == nullptr) {
            return true;
        }
        current = current->next;
    }
}

void deleteList(Node*& head) {

    Node* current = head;
    while(true) {
        while(current->next->next != nullptr) {
            current = current->next;
        }
        std::cout << "del: " << current->next->data << " ";
        current->next = nullptr;
        current = head;

        if(head->next == nullptr)
            break;
    }
    std::cout << "del: " << head->data << " ";
    head = nullptr;
    std::cout << std::endl;
}

int main() {
    Node* head = 0;
    add(head, 3);
    add(head, 6);
    add(head, 2);
    add(head, 8);
    add(head, 5);
    printList(head);
    std::cout << std::boolalpha;
    std::cout << "less than 10 all "
         << all(head,[] (int i) -> bool {return i < 10;})
         << std::endl;
    std::cout << "is even all "
         << all(head,[] (int i) -> bool {return i%2 == 0;})
         << std::endl;
    std::cout << "is even any "
         << any(head,[] (int i) -> bool {return i%2 == 0;})
         << std::endl;
    deleteList(head);
}