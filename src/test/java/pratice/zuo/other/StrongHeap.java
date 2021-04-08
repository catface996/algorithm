package pratice.zuo.other;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by catface
 * @date 2021/3/22 10:04 上午
 */
public class StrongHeap<T> {

    private int heapSize;

    private ArrayList<T> arr;

    private Map<T, Integer> revertMap;

    private Comparator<T> comp;

    private StrongHeap() {

    }

    public StrongHeap(Comparator<T> comp) {
        this.arr = new ArrayList<>();
        this.revertMap = new HashMap<>();
        this.comp = comp;
        this.heapSize = 0;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    public boolean contains(T obj) {
        return revertMap.containsKey(obj);
    }

    public T peek() {
        return arr.get(0);
    }

    public void push(T obj) {
        arr.add(obj);
        revertMap.put(obj, heapSize);
        heapInsert(heapSize++);
    }

    public T pop() {
        if (heapSize <= 0) {
            return null;
        }
        T ans = arr.get(0);
        swap(0, heapSize - 1);
        revertMap.remove(ans);
        arr.remove(--heapSize);
        heapify(0);
        return ans;
    }

    public void heapInsert(int index) {
        while (comp.compare(arr.get(index), arr.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public void heapify(int index) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int best = left + 1 < heapSize && comp.compare(arr.get(left + 1), arr.get(left)) < 0 ? (left + 1) : left;
            best = comp.compare(arr.get(best), arr.get(index)) < 0 ? best : index;
            if (best == index) {
                return;
            }
            swap(best, index);
            index = best;
            left = index * 2 + 1;
        }
    }

    public void resign(T obj) {
        heapInsert(revertMap.get(obj));
        heapify(revertMap.get(obj));
    }

    public void remove(T obj) {
        T replace = arr.get(heapSize - 1);
        int index = revertMap.get(obj);
        revertMap.remove(obj);
        arr.remove(--heapSize);
        if (obj != replace) {
            arr.set(index, replace);
            revertMap.put(replace, index);
            resign(replace);
        }
    }

    private void swap(int a, int b) {
        T objA = arr.get(a);
        T objB = arr.get(b);
        arr.set(a, objB);
        arr.set(b, objA);
        revertMap.put(objA, b);
        revertMap.put(objB, a);
    }

    public List<T> getAllElements() {
        List<T> ansList = new ArrayList<>(heapSize);
        ansList.addAll(arr);
        return ansList;
    }

}
