/**
 *
 *  @author Dyduch Łukasz S15599
 *
 */

package zad1;

import java.util.ArrayList;
import java.util.List;

public class ListCreator<S,T> { // Uwaga: klasa musi być sparametrtyzowana
	List<S> list;
	
	public ListCreator(List<S> list) {
        this.list = list;
    }

    static <S,T> ListCreator<S,T> collectFrom(List<S> list) {
        return new ListCreator<>(list);
    }

    public ListCreator<S,T> when(Selector sel) {
        List<S> tmp = new ArrayList<>();
        for (int i=0; i<this.list.size(); i++) {
            if(sel.select(this.list.get(i))) {
                tmp.add(this.list.get(i));
            }
        }
        this.list = tmp;
        return this;
    }

    public List<T> mapEvery(Mapper map) {
        List<T> tmp = new ArrayList<>();
        for (int i=0; i<this.list.size(); i++) {
            tmp.add((T)map.map(this.list.get(i)));
        }
        return tmp;
    }
}  
