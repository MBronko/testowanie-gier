package Zad2;

import Exceptions.SessionOpenException;

public class Session<T> {
    public void open() throws SessionOpenException {
    }

    public Transaction<T> beginTransaction() {
        return null;
    }

    public void close() {
    }
}