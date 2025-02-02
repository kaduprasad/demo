package spring.demo.testing;

import java.util.Arrays;
import java.util.*;
import java.util.Objects;
import java.util.stream.Collectors;

public class testThread {
    // practice multithreading

    public static void main(String[] args){

//        String arr[] = {"test1","test2","abc"};
//
//        String newarr[]  = Arrays.stream(arr)
//                .filter((element) -> Objects.equals(element, "abc"))
//                .toArray(String[]::new);
//        Arrays.asList();
//
//        List<String> arrList = new ArrayList<>();
//        arrList.add("prasad");
//        arrList.add("soham");
//        arrList.add("nitish");
//
//        List<String> filteredList = arrList.stream().filter((element) -> element.equals("prasad")).toList();
//        System.out.println(filteredList.toString());
//
//        // how to convert list to array
//        // how to convert array to list
//
//        String[] arr2 = {"prasad", "test","test"};
//
//        String[] res = Arrays.stream(arr2).filter((element) -> element.startsWith("t")).toArray(String[]::new);
//
//        System.out.println("res"+ Arrays.toString(res));
//
//        Map<String, Integer> map = new HashMap<>();
//
//        for (String element: arr2) {
//            Integer count = map.get(element);
//            if(count == null){
//                map.put(element, 1);
//            }else{
//                map.put(element, count+1);
//            }
//        }
//
//        Collection<Integer> vals = map.values();
//        Collection<String> keys = map.keySet();
//        System.out.println("vals"+vals);
//        System.out.println("keys"+keys);
//
//
//        System.out.println("map"+map);
//
//        for( Map.Entry<String, Integer> entry : map.entrySet()){
//            entry.getKey();
//            entry.getValue();
//        }

        // get count of elements
//        Map<String, Integer> map = new HashMap<>();
//
//        String s = "a count of words of";
//        String[] arr3 = s.split(" ");
//
//        for(String word : arr3){
//            Integer count = map.get(word);
//            if(count != null) {
//                map.put(word, count + 1);
//            }else {
//                map.put(word, 1);
//            }
//        }
//
//        System.out.println("Map"+map);
//
//        for(Map.Entry<String, Integer> entry : map.entrySet()){
//            System.out.println("Entry:"+entry.getKey()+" : "+entry.getValue());
//        }

        String[] arr4 = {"p", "e", "t", "p","p"};

        List<String> slist = new ArrayList<>();

        for( String element : arr4){

//            int index = slist.indexOf(element);

//            boolean flag = slist.contains(element);
//            System.out.println("flag"+flag);

            if(!slist.contains(element)){
                slist.add(element);
            }else{
                slist.remove(element);
            }

        }

        System.out.println("arr4 list"+ slist);

    }
}
