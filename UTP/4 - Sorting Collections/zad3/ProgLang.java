package zad3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProgLang {

    File file;
    Map<String,Collection<String>> langsMap = new LinkedHashMap<>();
    Map<String,Collection<String>> progsMap = new LinkedHashMap<>();

    public ProgLang(String pathToFile) {
        this.file = new File(pathToFile);
    }

    public Map<String,Collection<String>> getLangsMap() {
        try {
            Scanner scan = new Scanner(this.file);
            while(scan.hasNextLine()) {
                String currentLine = scan.nextLine();
                String[] currentLineTab = currentLine.split("\t");
                Set<String> programmers = new LinkedHashSet<>();
                for(int i = 1; i < currentLineTab.length; programmers.add(currentLineTab[i]), i++);
                this.langsMap.put(currentLineTab[0], programmers);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return this.langsMap;
    }

    public Map<String,Collection<String>> getProgsMap() {
        try {
            Scanner scan = new Scanner(this.file);
            while(scan.hasNextLine()) {
                String currentLine = scan.nextLine();
                String[] currentLineTab = currentLine.split("\t");
                for(int i = 1; i < currentLineTab.length; i++) {
                    if(this.progsMap.containsKey(currentLineTab[i]))
                            this.progsMap.get(currentLineTab[i]).add(currentLineTab[0]);
                    else {
                        Set<String> newList = new LinkedHashSet<>();
                        newList.add(currentLineTab[0]);
                        this.progsMap.put(currentLineTab[i], newList);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return this.progsMap;
    }

    public Map<String,Collection<String>> getLangsMapSortedByNumOfProgs() {
        return sorted(
                this.getLangsMap(),
                ((Comparator<Map.Entry<String,Collection<String>>>)(e1, e2) -> e1.getValue().size() - e2.getValue().size()).reversed().thenComparing(l -> l.getKey())
        );
    }

    public Map<String,Collection<String>> getProgsMapSortedByNumOfLangs() {
        return sorted(
                this.getProgsMap(),
                ((Comparator<Map.Entry<String,Collection<String>>>)(e1, e2) -> e1.getValue().size() - e2.getValue().size()).reversed().thenComparing(l -> l.getKey())
        );
    }

    public Map<String,Collection<String>> getProgsMapForNumOfLangsGreaterThan(int number) {
        return filtered(this.getProgsMap(), p -> p.getValue().size() > number);
    }

    public static <S,T> Map<S,Collection<T>> sorted(Map<S,Collection<T>> map, Comparator<Map.Entry<S,Collection<T>>> f) {
        List<Map.Entry<S,Collection<T>>> entryList = new ArrayList<>(map.entrySet());
        entryList.sort(f);
        Map result = new LinkedHashMap<>();
        for(Map.Entry<S, Collection<T>> entry : entryList)
            result.put(entry.getKey(), entry.getValue());
        return result;
    }

    public static <S,T> Map<S,Collection<T>> filtered(Map<S,Collection<T>> map, Predicate<Map.Entry<S,Collection<T>>> f) {
        List<Map.Entry<S,Collection<T>>> entryList = new ArrayList<>(map.entrySet());
        entryList = entryList.stream()
                .filter(f)
                .collect(Collectors.toList());
        Map result = new LinkedHashMap<>();
        for(Map.Entry<S,Collection<T>> entry : entryList) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
