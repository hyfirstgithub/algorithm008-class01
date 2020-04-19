学习笔记

#作业一：add first或add last这套新的API改写Deque的代码
```
public static void addFirst(){
        Deque<String> deque = new LinkedList<>();
        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");
        System.out.println(deque);
        String str = deque.getFirst();
        System.out.println(str);
        System.out.println(deque);


        while (deque.size() > 0) {
            System.out.println(deque.removeFirst());
        }
        System.out.println(deque);

        //分析Queue 和 priority Queue的源码
    }

    public static void addLast() {
        Deque<String> deque = new LinkedList<>();
        deque.addLast("c");
        deque.addLast("b");
        deque.addLast("a");
        System.out.println(deque);

        String str = deque.getLast();
        deque.poll();
        System.out.println(str);
        System.out.println(deque);

        while (deque.size() > 0) {
            System.out.println(deque.removeLast());
        }
        System.out.println(deque);
    }
```

#作业二：分析Queue和Priority Queue源码

