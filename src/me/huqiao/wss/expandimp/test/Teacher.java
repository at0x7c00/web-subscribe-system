package me.huqiao.wss.expandimp.test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import me.huqiao.wss.sys.entity.Department;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Teacher {

	private Integer id;
	private String name;
	private Set<Student> students;
	private Set<Course> courses;
	private Sex sex;
	
	private Department dept;
	
	public Teacher(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@OneToMany(targetEntity = Student.class)
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
	@OneToMany(targetEntity = Course.class)
	public Set<Course> getCourses() {
		return courses;
	}
	
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
	@Column(name = "origin", nullable = true, columnDefinition = "enum('P','M','A',' D')")
	@Enumerated(EnumType.STRING)
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	
	

	/**
	 * @return Department 部门
	 */
	@ManyToOne(targetEntity = me.huqiao.wss.sys.entity.Department.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "dept", nullable = true)
	@Fetch(FetchMode.SELECT)
	@JsonIgnore
	public Department getDept() {
		return dept;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}




	private enum Sex{
		F("女"), M("男");
		private String description;

		private Sex(String description) {
			this.description = description;
		}

		public String getDescription() {
			return description;
		}

		public final static Map<Sex, String> userStatusMap = new LinkedHashMap<Sex, String>();
		static {
			for (Sex s : Sex.values()) {
				userStatusMap.put(s, s.getDescription());
			}
		}
	}
}
