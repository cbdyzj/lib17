package draft.algorithm;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 水塘抽样
 *
 * @param <T> 抽样元素类型
 */
public class ReservoirSample<T> {

    private final List<T> collection;

    public ReservoirSample(@NonNull List<T> collection) {
        this.collection = collection;
    }

    public List<T> sample(int quantity) {
        if (quantity >= this.collection.size()) {
            return new ArrayList<>(this.collection);
        }
        var sampled = new ArrayList<T>(quantity);
        var i = 0;
        while (i < quantity) {
            sampled.add(this.collection.get(i));
            i++;
        }
        var random = new Random();
        while (i < this.collection.size()) {
            var j = random.nextInt(i + 1);
            if (j < quantity) {
                sampled.set(j, this.collection.get(i));
            }
            i++;
        }
        return sampled;
    }
}
