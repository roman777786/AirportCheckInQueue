package datastructure;

import models.Passenger;

public class PassengerQueue {
    private int maxSize = 50;
    private Passenger[] queueArray;
    private int front;
    private int rear;
    private int currentSize;

    public PassengerQueue() {
        queueArray = new Passenger[maxSize];
        front = 0;
        rear = 0;
        currentSize = 0;
    }

    public boolean enqueue(Passenger p) {
        if (isFull()) {
            return false;
        }
        queueArray[rear] = p;
        rear = (rear + 1) % maxSize;
        currentSize++;
        return true;
    }

    public Passenger dequeue() {
        if (isEmpty()) {
            return null;
        }
        Passenger p = queueArray[front];
        front = (front + 1) % maxSize;
        currentSize--;
        return p;
    }

    public Passenger peek() {
        if (isEmpty()) {
            return null;
        }
        return queueArray[front];
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean isFull() {
        return currentSize == maxSize;
    }

    public int size() {
        return currentSize;
    }

    public Passenger getPassengerAt(int index) {
        if (index < 0 || index >= currentSize) {
            return null;
        }
        int actualIndex = (front + index) % maxSize;
        return queueArray[actualIndex];
    }

    public String getAllPassengers() {
        if (isEmpty()) {
            return "Queue is empty!";
        }

        StringBuilder sb = new StringBuilder();
        int i = front;
        for (int count = 0; count < currentSize; count++) {
            sb.append(queueArray[i].toString()).append("\n");
            i = (i + 1) % maxSize;
        }
        return sb.toString();
    }
}
