package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListCreator<S> {
    List<S> list;

    public ListCreator(List<S> list) {
        this.list = list;
    }

    static <S> ListCreator<S> collectFrom(List<S> list) {
        return new ListCreator<>(list);
    }

    public ListCreator<S> when(Predicate<S> f) {
        List<S> tmp = new ArrayList<>();
        for (int i=0; i<this.list.size(); i++) {
            if(f.test(this.list.get(i))) {
                tmp.add(this.list.get(i));
            }
        }
        this.list = tmp;
        return this;
    }

    public List<S> mapEvery(Function<S,S> f) {
        List<S> tmp = new ArrayList<>();
        for (int i=0; i<this.list.size(); i++) {
            tmp.add(f.apply(this.list.get(i)));
        }
        return tmp;
    }
}
