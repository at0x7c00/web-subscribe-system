package me.huqiao.wss.expandimp.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import me.huqiao.wss.datamodel.ModelProp;
import me.huqiao.wss.expandimp.PropParser;
import me.huqiao.wss.expandimp.ScanResult;
import me.huqiao.wss.expandimp.Scaner;

public class ScanerTest {

	public static void main(String[] args) {
		Course c1 = new Course(1,"语文");
		Course c2 = new Course(2,"数学");
		
		Student s1 = new Student(1,"张三");
		Student s2 = new Student(2,"李四");
		Student s3 = new Student(3,"王五");
		Student s4 = new Student(4,"赵六");
		
		Teacher t = new Teacher(1,"张老师");
		
		Set<Course> courses = new HashSet<Course>();
		courses.add(c1);
		courses.add(c2);
		t.setCourses(courses);
		
		Set<Student> studetns = new HashSet<Student>();
		studetns.add(s1);
		studetns.add(s2);
		studetns.add(s3);
		studetns.add(s4);
		t.setStudents(studetns);
		
		List<Teacher> dataList = new ArrayList<Teacher>();
		dataList.add(t);
		
		PropParser parser = new PropParser();
		List<ModelProp> props = parser.parseProp("me.huqiao.wss.expandimp.test.Teacher");
		
		Scaner scaner = new Scaner();
		
		List<String> propTryToDisplay = new ArrayList<String>();
		propTryToDisplay.add("name");
		scaner.setTryToDisplayOfComplexObject(propTryToDisplay);
		List<ScanResult> resultList = scaner.doScan(dataList, props);
		
		for(ScanResult result : resultList){
			for(int i = 0;i<result.rowCount();i++){
				Map<String,String> map = result.readRow(i);
				System.out.println(map);
			}
		}
		
	}
}
