package stack;

public class SequenceStack {

    public static void main(String[] args) {
        SequenceStack sequenceStack = new SequenceStack();
        sequenceStack.push(1);
        sequenceStack.push(2);
        sequenceStack.push(3);

    }

    private int count = 0;
    private int capacity = 10;

    private int[] array = new int[capacity];


    public void push(int item) {
        if (count == capacity) {
            capacity = capacity * 2;
            int[] newItems = new int[capacity];
            System.arraycopy(array, 0, newItems, 0, array.length);
            array = newItems;
        }
        array[count++] = item;
    }

    public int  pop(){
        if(count == 0){
            return -1;
        }
        return  array[--count];

    }


}
