#include <iostream>
#include <vector>
using namespace std;

//Function to partition the array
int partition (vector<int>&arr, int low, int high){
    int pivot = arr[high]; //Last element as pivot
    int i = low - 1; //Index for smaller element

    for(int j=low; j<high; j++){
        if(arr[j]<=pivot){
            i++;
            swap (arr[i], arr[j]); //Swap smaller element with i
        }
    }
    swap(arr[i+1], arr[high]); //Swap pivot into correct position
    return i+1;//Return partition index
}

//Recursive function to perform QuickSort
void quicksort (vector<int>&arr, int low, int high){
    if(low<high){
        int pi=partition(arr, low, high);
        quicksort(arr, low, pi-1); //Sort left partition 
        quicksort(arr, pi+1, high); //Sort right partition
    }
}

//Function to print the array
void printArray(const vector<int>& arr){
    for (int num : arr)
        cout << num << " ";
    cout << endl;
}

int main(){
    vector<int> arr = {10, 7, 8, 9, 1, 5};  // n = 6
    int n = arr.size();

    cout << "Original array: ";
    printArray(arr);

    quicksort(arr, 0, n-1);

    cout << "Sorted array: ";
    printArray(arr);

    return 0;
}