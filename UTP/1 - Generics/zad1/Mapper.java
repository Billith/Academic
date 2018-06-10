/**
 *
 *  @author Dyduch Łukasz S15599
 *
 */

package zad1;


public interface Mapper<S,T> { // Uwaga: interfejs musi być sparametrtyzowany
    T map(S arg);
} 
