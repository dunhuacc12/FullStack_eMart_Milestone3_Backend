package pers.chenxi.emart.common.util;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class LambdaUtils {

    public static <T> Consumer<T> consumerWithIndex(BiConsumer<T, Integer> consumer) {
        class Item {
            int i;
        }
        Item item = new Item();
        return t -> {
            int index = item.i ++;
            consumer.accept(t, index);
        };
    }
}
