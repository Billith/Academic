#include <iostream>

struct Node {
    int   data;
    Node* next = nullptr;
};

Node* arrayToList(const int arr[], size_t size) {

    static Node head;

    for(int i=0; i<size; i++) {
        Node* current = &head;
        if(i == 0) {
            head.data = arr[0];
            head.next = new Node;
        } else {
            while(current->next != nullptr) {
                current = current->next;
            }
            current->data = arr[i];
//            if(i == size-1)
//                current->next = nullptr;
//            else
//                current->next = new Node;
            current->next = new Node;
        }
    }
    return &head;
}

Node* removeOdd(Node* head) {

    while((head->data)%2 != 0 && head->next != nullptr) {
        std::cout << "DEL: " << head->data << " ";
        head = head->next;
    }
    if(head->data%2 != 0 && head->next == nullptr)
        return nullptr;
    Node* current = head;
    while(current->next != nullptr) {
        while(current->next->data%2 != 0) {
            std::cout << "DEL: " << current->next->data << " ";
            current->next = current->next->next;
        }
        current = current->next;
    }
    std::cout << std::endl;
    return head;
}

void showList(const Node* head) {

    if(head == nullptr) {
        std::cout << "Empty list" << std::endl;
        return;
    }

    std::cout << head->data << " ";
    Node* current = head->next;
    while(current->next != nullptr) {
        std::cout << current->data << " ";
        current = current->next;
    }
//    while(true) {
//        std::cout << current->data << " ";
//        if(current->next == nullptr)
//            break;
//        current = current->next;
//    }
    std::cout << std::endl;
}

void deleteList(Node*& head) {

    Node* current = head;
    while(current->next != nullptr) {
        while (current->next->next != nullptr) {
            current = current->next;
        }
        std::cout << "del: " << current->data << " ";
        current->next = nullptr;
        current = head;
    }
    head = nullptr;
    std::cout << std::endl;
}

int main() {
    int arr[] = {1,2,3,4,5,6};
    //int arr[] = {21,12,33,44,55,10,8,14,13};
    size_t size = sizeof(arr)/sizeof(*arr);
    Node* head = arrayToList(arr,size);
    showList(head);
    head = removeOdd(head);
    showList(head);
    deleteList(head);
    try {
        showList(head);
    } catch (int e) {
        std::cout << "Empty list" << std::endl;
    }
}