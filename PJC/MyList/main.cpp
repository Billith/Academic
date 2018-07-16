#include <iostream>

class Node {
public:
    Node *next;
    int data;
    Node();
    Node(int value);
    ~Node();
};

Node::Node() {
    this->next = nullptr;
}

Node::~Node() {
    std::cout << "Destroy! " << this->data << std::endl;
}

Node::Node(int value) {
    data = value;
    this->next = nullptr;
}

class MyList {
public:
    int size(void);
    void add(int value);
    void print(void);
    int get(int index);
    void remove(int index);
    Node *head;
    MyList();
};

int MyList::size() {
    int size = 0;
    Node *current = this->head;
    if(current->next != nullptr) {
        while (current->next != nullptr) {
            current = current->next;
            size++;
        }
    }
    return size;
}

MyList::MyList() {
    this->head = new Node();
}

void MyList::add(int value) {
    if(this->size() == 0) {
        this->head->next = new Node(value);
    } else {
        Node *last = this->head;
        while(last->next != nullptr) {
            last = last->next;
        }
        last->next = new Node(value);
    }
    std::cout << "MyList add value: " << value << std::endl;
}

void MyList::print() {
    Node *current = this->head->next;
    std::cout << "[";
    while(current->next != nullptr) {
        std::cout << current->data << ", ";
        current = current->next;
    }
    std::cout << current->data;
    std::cout << "]" << std::endl;
}

int MyList::get(int index) {
    Node *current = this->head->next;
    int current_index = 0;
    while(current->next != nullptr) {
        if(current_index == index)
            return current->data;
        current = current->next;
        current_index++;
    }
}

void MyList::remove(int index) {
    Node *current = this->head->next;
    Node *previous = nullptr;
    int current_index = 0;
    while(true) {
        if(current_index >= this->size())
            return;
        if(current_index == index) {
            if(current->next == nullptr) {
                previous->next = nullptr;
                delete current;
                break;
            }
            if(previous == nullptr) {
                this->head->next = current->next;
            }
            previous->next = current->next;
            std::cout << "MyList remove value: " << current->data << std::endl;
            delete current;
            break;
        }
        previous = current;
        current = current->next;
        current_index++;
    }

}

int main() {
    MyList *list = new MyList();
    list->add(10);
    list->add(11);
    list->add(12);
    list->add(127);
    list->print();
    list->remove(3);
    list->print();
}
