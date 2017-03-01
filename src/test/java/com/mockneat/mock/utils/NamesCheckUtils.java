package com.mockneat.mock.utils;

import com.mockneat.mock.utils.file.FileManager;
import com.mockneat.types.enums.NameType;

import java.util.*;

/**
 * Created by andreinicolinciobanu on 02/03/2017.
 */
public class NamesCheckUtils {

    private static FileManager FILE_MANAGER = FileManager.getInstance();
    private static Map<NameType, List<Set<String>>> NAMES = new EnumMap<>(NameType.class);

    static {
        Arrays.stream(NameType.values()).forEach(nt -> {
            List<Set<String>> ll = new LinkedList<>();
            Arrays.stream(nt.getDictionaries()).forEach(dict ->
                    ll.add(new HashSet<>(FILE_MANAGER.getLines(dict))));
            NAMES.put(nt, ll);
        });
    }

    private static boolean isInSets(String value, List<Set<String>> sets) {
        return sets.stream().filter(s -> s.contains(value)).findFirst().isPresent();
    }

    public static boolean isNameOfType(String name, NameType type) {
        return isInSets(name, NAMES.get(type));
    }
}
