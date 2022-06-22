package Zad2;

public class SessionFactory<T> {
    public Session<T> newSession() {
        return new Session<T>();
    }
}