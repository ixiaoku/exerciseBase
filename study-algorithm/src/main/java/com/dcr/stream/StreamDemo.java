package com.dcr.stream;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @descriptions:
 * @author: dcr
 * @date: 2021/4/25 17:15
 */
public class StreamDemo {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<Student>() {
            {
                add(new Student(20160001, "孔明", 20, 1, "土木工程", "武汉大学"));
                add(new Student(20160002, "伯约", 21, 2, "信息安全", "武汉大学"));
                add(new Student(20160003, "玄德", 22, 3, "经济管理", "武汉大学"));
                add(new Student(20160004, "云长", 21, 2, "信息安全", "武汉大学"));
                add(new Student(20161001, "翼德", 21, 2, "机械与自动化", "华中科技大学"));
                add(new Student(20161002, "元直", 22, 4, "土木工程", "华中科技大学"));
                add(new Student(20161003, "奉孝", 23, 4, "计算机科学", "华中科技大学"));
                add(new Student(20162001, "仲谋", 22, 3, "土木工程", "浙江大学"));
                add(new Student(20162002, "鲁肃", 23, 4, "计算机科学", "浙江大学"));
                add(new Student(20163001, "丁奉", 24, 5, "土木工程", "南京大学"));
            }
        };


        /**过滤（筛选：filter,distinct,limit,skip）*/
        List<Student> whuStudents = students.stream().filter(student -> "武汉大学".equals(student.getSchool())).collect(Collectors.toList());


        //distinct,独一无二，类似sql
        List<Integer> nums=new ArrayList(){
            {
                add(1);
                add(2);
                add(2);
            }
        };
        List<Integer> evens = nums.stream()
                .filter(num -> num % 2 == 0).distinct()
                .collect(Collectors.toList());
        System.out.println(evens);


        //limit,取前n项，类似sql
        List<Student> civilStudents = students.stream()
                .filter(student -> "土木工程".equals(student.getMajor())).limit(2)
                .collect(Collectors.toList());
        System.out.println(civilStudents.get(0).getMajor());
        //sorted,实现sorted(Comparator<? super T> comparator)
        List<Student> sortedStudents=students.stream()
                .filter(student -> "土木工程".equals(student.getMajor())).sorted(Comparator.comparingInt(Student::getAge))
                .limit(2)
                .collect(Collectors.toList());
        //lambda遍历集合
        sortedStudents.forEach(element->{
            System.out.println(element.getAge());
        });

        //skip，与limit相反，跳过前n个元素，找出排序在2之后的
        List<Student> civilStudents1 = students.stream()
                .filter(student -> "土木工程".equals(student.getMajor()))
                .skip(2)
                .collect(Collectors.toList());

        /**映射（map,flatmap）*/
        //map
        List<String> names=students.stream()
                .filter(student -> "计算机科学".equals(student.getName()))
                .map(Student::getName)
                .collect(Collectors.toList());

        //java8还提供mapToDouble,mapToInt,mapToLong
        int totalage=students.stream()
                .filter(student -> "计算机科学".equals(student.getMajor()))
                .mapToInt(Student::getAge).sum();

        //flatMap,了解字符串分割函数spilt(),输出构成这一数组的所有非重复字符
        String[] strs={"java8","is","easy","to","use"};
        List<String> distinctstr = Arrays.stream(strs)
                .map(str->str.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        distinctstr .forEach(element->{
            System.out.print(element);
        });

        /**终端操作，在终端实现对流的查找、归约等操作*/
        /**查找*/
        //allMatch(全部)
        boolean isAdult=students.stream().allMatch(student -> student.getAge()>=18);

        //anyMatch(是否存在一个或多个)
        boolean hasWhu=students.stream().anyMatch(student -> "武汉大学".equals(student.getSchool()));

        //noneMatch(是否不存在)
        boolean noneCs=students.stream().noneMatch(student -> "计算机科学".equals(student.getMajor()));

        //findFirst(找到符合条件的第一个元素)，了解java8新特性optional类（解决空指针问题）
        Optional<Student> optStu=students.stream().filter(student -> "土木工程".equals(student.getMajor()))
                .findFirst();

        //findAny,返回满足条件的任意一个元素(并式流式，findAny性能优于findFirst)
        Optional<Student> optStu1=students.stream().filter(student -> "土木工程".equals(student.getMajor()))
                .findAny();

        /**归约，不返回集合，而是进行进一步操作,进行上述进行的求和操作*/
        //利用reduce方法来达到这一目的
        int total=students.stream()
                .filter(student -> "计算机科学".equals(student.getMajor()))
                .map(Student::getAge)
                .reduce(0,(a,b)->a+b);
        System.out.println("\n"+total);
        // 进一步简化
        int totalAge2 = students.stream()
                .filter(student -> "计算机科学".equals(student.getMajor()))
                .map(Student::getAge)
                .reduce(0, Integer::sum);
        // 采用无初始值的重载版本，需要注意返回Optional
        Optional<Integer> totalAge = students.stream()
                .filter(student -> "计算机科学".equals(student.getMajor()))
                .map(Student::getAge)
                .reduce(Integer::sum);


        /**收集，toSet,toMap(收集器),大多数操作均有相适应的long和double操作*/
        //归约
        long count=students.stream().collect(Collectors.counting());
        //进一步简化
        long count1=students.stream().count();

        Optional<Student> olderStudent1=students.stream().collect(Collectors.maxBy((s1, s2)->s1.getAge()-s2.getAge()));
        // 进一步简化
        Optional<Student> olderStudent2 = students.stream().collect(Collectors.maxBy(Comparator.comparing(Student::getAge)));
        // 求最小年龄
        Optional<Student> olderStudent3 = students.stream().collect(Collectors.minBy(Comparator.comparing(Student::getAge)));
        //求总和
        int total3=students.stream().collect(Collectors.summingInt(Student::getAge));
        //求平均值
        double avgage=students.stream().collect(Collectors.averagingInt(Student::getAge));
        System.out.println(avgage);
        //一次性得到元素个数、总和、均值、最大值、最小值(了解IntSummaryStatistics)
        IntSummaryStatistics statistics=students.stream().collect(Collectors.summarizingInt(Student::getAge));
        //字符串拼接
        String nameJoin=students.stream().map(Student::getName).collect(Collectors.joining(","));

        /**分组，类似数据库操作里面的group by*/
        Map<String,List<Student>> groups=students.stream().collect(Collectors.groupingBy(Student::getSchool));
        //多级分组
        Map<String, Map<String, List<Student>>> groups1 = students.stream().collect(
                Collectors.groupingBy(Student::getSchool,Collectors.groupingBy(Student::getMajor)));
        System.out.println(groups1.get("浙江大学"));
        //第二个参数可以用来统计一些分组信息
        Map<String, Long> groups2 = students.stream().collect(Collectors.groupingBy(Student::getSchool, Collectors.counting()));

        /**分区*/
        //区分是否为武大学生
        Map<Boolean, List<Student>> partition = students.stream().collect(Collectors.partitioningBy(student -> "武汉大学".equals(student.getSchool())));
    }
}
