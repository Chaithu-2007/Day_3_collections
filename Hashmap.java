import java.util.HashMap;
 class Hashmap {
    public static void main(String[] args) {
        HashMap<Integer,String> students=new HashMap<>();
        students.put(101,"chaithu");
        students.put(102,"priya");
        students.put(103,"bhavana");
        System.out.println(students);
        System.out.println("Student 101:"+students.get(101));
        students.put(102,"kala");
        students.remove(103);
        System.out.println("updated map:"+students);
    }
    
}
