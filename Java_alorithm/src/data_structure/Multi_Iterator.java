package data_structure;

import java.util.*;

public class Multi_Iterator implements Iterable<String> {
 private String[] words = "May I get offers this summer.".split(" ");

    //默认的迭代器，前向遍历
    public Iterator<String> iterator() {
       //匿名内部类
        return new Iterator<String>() {
            private int index = 0;
            public boolean hasNext() {return index < words.length;}
            public String next() { return words[index++];    }
            public void remove() { // Not implemented
                throw new UnsupportedOperationException();
            }
        };
    }

//反向迭代器
    public Iterable<String> reverseIterator() {
        return new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                return new Iterator<String>() {
                    private int index = words.length - 1;
                    public boolean hasNext() {return index > -1; }
                    public String next() {return words[index--]; }
                    public void remove() { // Not implemented
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
//随机迭代器，注意这里不是创建一个新的Iterator，而是返回了一个打乱的List中的迭代器
    public Iterable<String> randomized() {
        return new Iterable<String>() {
            public Iterator<String> iterator() {
                List<String> shuffled = new ArrayList<>(Arrays.asList(words));
                Collections.shuffle(shuffled, new Random(47));
                return shuffled.iterator();
            }
        };
    }
public static void main(String[] args) {
        Multi_Iterator mi = new Multi_Iterator();
       //默认的迭代器
        for (String String : mi) {
            System.out.print(String + " ");
        }
        System.out.println();
       //反向迭代器
        for (String String : mi.reverseIterator()) {
            System.out.print(String + " ");
        }
        System.out.println();
        //随机迭代器
        for (String String : mi.randomized()) {
            System.out.print(String + " ");
        }
    }
}
