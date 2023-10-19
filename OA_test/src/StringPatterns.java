import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Objects;

public class StringPatterns {
    public int solveWithBFS(int wordLen, int maxVowels) {
        if (wordLen == 0)
            return 0;
            ArrayDeque q = new ArrayDeque<>();
            q.offer(new Item(1, 0, 21));
        if (maxVowels > 0) {
            q.offer(new Item(1, 1, 5));
        }
        for (int l = 1; l < wordLen; l++) {
            int qs = q.size();
            HashMap<Item, Item> map = new HashMap<>();
            while (qs > 0) {
                var item = q.poll();
                long count;
                if (item.vowels + 1 <= maxVowels) {
                    int mod = 1000000007;
                    count = (item.count * 21) % mod;
                    var next = new Item(item.len + 1, item.vowels, count);
                    var exist = map.get(next);
                    if (exist == null) {
                        map.put(next, next);
                        q.offer(next);
                    } else {
                        exist.count = (exist.count + count) % mod;
                    }
                    count = (item.count * 5) % mod;
                    next = new Item(item.len + 1, item.vowels + 1, count);
                    exist = map.get(next);
                    if (exist == null) {
                        map.put(next, next);
                        q.offer(next);
                    } else {
                        exist.count = (exist.count + count) % mod;
                    }
                } else {
                    count = (item.count * 21) % mod;
                    var next = new Item(item.len + 1, 0, count);
                    var exist = map.get(next);
                    if (exist == null) {
                        map.put(next, next);
                        q.offer(next);
                    } else {
                        exist.count = (exist.count + count) % mod;
                    }
                }
                qs--;
            }
        }
        long ans = 0;
        while (!q.isEmpty()) {
            ans = (ans + q.poll().count) % mod;
        }
        return (int)ans;
    }
}

class Item {
    int len;
    int vowels;
    long count;

    public Item(int len, int vowels, long count) {
        this.len = len;
        this.vowels = vowels;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return len == item.len && vowels == item.vowels;
    }

    @Override
    public int hashCode() {
        return Objects.hash(len, vowels);
    }
}
