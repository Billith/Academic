/**
 *
 *  @author Dyduch Łukasz S15599
 *
 */

package zad1;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Anagrams {

    List<List<String>> anagrams = new ArrayList<>();

    public Anagrams(String path) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(path));
        while(scan.hasNext()) {
            String current_word = scan.next();
            boolean added = false;
            for (List<String> list : this.anagrams) {
                char[] current_word_array = current_word.toCharArray();
                char[] compere_word_array = list.get(0).toCharArray();
                Arrays.sort(current_word_array);
                Arrays.sort(compere_word_array);
                if(Arrays.equals(current_word_array, compere_word_array)) {
                    list.add(current_word);
                    added = true;
                }
            }
            if (!added) {
                List<String> new_list = new ArrayList<>();
                new_list.add(current_word);
                this.anagrams.add(new_list);
            }
        }
    }

    public List<List<String>> getSortedByAnQty() {
        Collections.sort(this.anagrams, (o1, o2) -> o2.size() - o1.size());
        return this.anagrams;
    }

    public String getAnagramsFor(String word) {
        List<String> new_list = new ArrayList<>();
        for(List<String> list : this.anagrams) {
            for(String element : list) {
                if(element.equals(word)) {
                    new_list = list;
                    new_list.remove(word);
                    return word + ": " + new_list;
                }
            }
        }
        return word + ": " + new_list;
    }
}
