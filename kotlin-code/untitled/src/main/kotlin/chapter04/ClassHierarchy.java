package chapter04;

public class ClassHierarchy {
    interface Clickable {
        default void showOff() {
            System.out.println("Clickable");
        }
    }

    interface Focusable {
        default void showOff() {
            System.out.println("Focusable");
        }
    }

    static class Button implements Clickable, Focusable {
        public void showOff() {
            Clickable.super.showOff();
            System.out.println();
        }
    }

    static class Focus implements Focusable {
        public void sayHello() {

        }
    }

    public static void main(String[] args) {
        Button button = new Button();
        button.showOff();
    }
}
