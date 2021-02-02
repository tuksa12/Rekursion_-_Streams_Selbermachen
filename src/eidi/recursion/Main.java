package eidi.recursion;

public class Main {


    // Hier können Sie Ihre Implementierung testen. Diese Methode wird nicht getestet.
    public static void main(String[] args) {

        // Beispielverwendung von MyList und myStream
        MyList<String> list = new MyList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.myStream().filter(p -> p.equals("a")).forEach(System.out::println);

    }

}
