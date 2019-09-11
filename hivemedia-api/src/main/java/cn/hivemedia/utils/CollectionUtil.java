package cn.hivemedia.utils;

import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Function;

/**
 * Created by chenzq
 * Date: 2019/1/6 下午6:36
 **/
public class CollectionUtil {
    public static <T> String collectionToString(Collection<T> values) {
        return StringUtils.collectionToDelimitedString(values, ",");
    }

    public static Set<Integer> parseIntSet(String str) {
        Set<String> set = StringUtils.commaDelimitedListToSet(str);
        Set<Integer> intSet = new LinkedHashSet<>();
        set.forEach((s)->{
            intSet.add(Integer.parseInt(s));
        });
        return intSet;
    }

    public static <K, T> Map<K, T> listToMap(List<T> tList, Function<T, K> keyMethod) {
        if (tList == null || tList.size() == 0) {
            return Collections.emptyMap();
        } else {
            Map<K, T> map = new HashMap<>(tList.size());
            for (T t : tList) {
                map.put(keyMethod.apply(t), t);
            }
            return map;
        }
    }

    public static <K, T> Map<K, List<T>> listToMultiMap(List<T> tList, Function<T, K> keyMethod) {
        if (tList == null || tList.size() == 0) {
            return Collections.emptyMap();
        } else {
            Map<K, List<T>> map = new HashMap<>(tList.size());
            for (T t : tList) {
                map.computeIfAbsent(keyMethod.apply(t), k -> new ArrayList<>()).add(t);
            }
            return map;
        }
    }
}
