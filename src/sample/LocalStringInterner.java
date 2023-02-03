package sample;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * string intern
 * @param <T>
 */
public class LocalStringInterner <T> {
    private final Map<T, T> map;
    private static LocalStringInterner interner;
    public static LocalStringInterner getInstance(){
        if(interner==null){
            interner = new LocalStringInterner();
        } return interner;
    }

    public LocalStringInterner() {map = new ConcurrentHashMap<>();}

    public T intern(T t) {
        T exist = map.putIfAbsent(t, t);
        return (exist == null) ? t : exist;
    }
    public int internSize() {
        return map.size();
    }
}
