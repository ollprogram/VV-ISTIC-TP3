package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class BinaryHeap<T> {

    private int count;
    private List<T> maxHeap;
    private Comparator<T> comparator;

    public BinaryHeap(Comparator<T> comparator) {
        this.count = 0;
        this.maxHeap = new ArrayList<>();
        this.comparator = comparator;
    }

    public T pop() { return null; }

    public T peek() { return null; }

    public void push(T element) {
        T current = element;
        for(int i = 0; i < this.count; i++){
            if(comparator.compare(element, this.maxHeap.get(i)) == 1){

            }
        }
    }

    public int count() { return this.count(); }

    /**
     * Return the level of numberOfNode
     * @param numberOfNode
     * @return integer
     */
    public int getLevel(float numberOfNode){
        if(numberOfNode == 0){
            return 0;
        } else {
            int i = 1;
            while(numberOfNode/i <= 1.0) {
                i*=2;
            }
            return i;
        }
    }

}