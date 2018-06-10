package zad1;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class XList<T> extends ArrayList<T>{

    public XList(T... args) {
        this.addAll(Arrays.asList(args));
    }

    public XList(Collection<T> col) {
        this.addAll(col);
    }

    static <T> XList<T> of(T... args) {
        XList list = new XList<>(args);
        return list;
    }

    static <T> XList<T> of(Collection<T> col) {
        XList list = new XList<>(col);
        return list;
    }

    static <T> XList<T> charsOf(String s) {
        XList list = new XList<>();
        for (char c : s.toCharArray()) {
            list.add(c);
        }
        return list;
    }

    static <T> XList<T> tokensOf(String s) {
        XList list = new XList<>();
        StringTokenizer st = new StringTokenizer(s);
        while(st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
        return list;
    }

    static <T> XList<T> tokensOf(String s, String delim) {
        XList list = new XList<>();
        StringTokenizer st = new StringTokenizer(s);
        while(st.hasMoreTokens()) {
            list.add(st.nextToken(delim));
        }
        return list;
    }

    public XList<T> union(Collection<T> col) {
        XList<T> list = new XList<>();
        list.addAll(this);
        list.addAll(col);
        return list;
    }

    public XList<T> union(T... args) {
        XList<T> list = new XList<>();
        list.addAll(this);
        list.addAll(Arrays.asList(args));
        return list;
    }

    public XList<T> diff(Collection<T> col) {
        XList<T> list = new XList<>();
        list.addAll(this);
        list.removeAll(col);
        return list;
    }

    public XList<T> unique() {
        LinkedHashSet lhs = new LinkedHashSet<T>(this);
        return new XList<T>(lhs);
    }

    public XList<T> combine() {
        XList<T> list = new XList<>();

        int solutions = 1;

        for(int i = 0; i < this.size(); solutions *= ((List)this.get(i)).size(), i++);
        for(int i = 0; i < solutions; i++) {
            XList new_list = new XList<>();
            int j = 1;
            for(T element : this) {
                List current_list = (List) element;
                new_list.add(current_list.get((i/j)%current_list.size()));
                j *= current_list.size();
            }
            list.add((T)new_list);
        }

        return list;
    }

    public XList<String> collect(Function<T,String> fun) {
        XList<String> list = new XList<>();
        for (T element : this) {
            list.add(fun.apply(element));
        }
        return list;
    }

    public String join() {
        String output = "";
        for (int i = 0; i < this.size(); i++ ) {
            output += this.get(i);
        }
        return output;
    }

    public String join(String delim) {
        String output = "";
        for (int i = 0; i < this.size(); i++ ) {
            if (i != this.size()-1)
                output += this.get(i) + delim;
            else
                output += this.get(i);
        }
        return output;
    }

    public void forEachWithIndex(BiConsumer<T, Integer> cons) {
        for (int i = 0; i < this.size(); i++) {
            cons.accept(this.get(i), i);
        }
    }

}
