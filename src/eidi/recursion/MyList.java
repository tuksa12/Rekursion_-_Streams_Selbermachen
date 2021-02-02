package eidi.recursion;

import java.util.Iterator;
import java.util.LinkedList;

public class MyList<T> extends LinkedList<T> {

    public MyStream<T> myStream() {
        class State {
            Iterator<T> x = iterator();

            MyStream<T> stream() {
                return () -> (x.hasNext()) ? new MyStream.Pair<T>(x.next(), stream()) : null;
            }
        }
        return new State().stream();
    }

}
