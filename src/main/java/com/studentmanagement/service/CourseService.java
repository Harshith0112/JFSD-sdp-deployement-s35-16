package com.studentmanagement.service;

import java.util.List;

import com.studentmanagement.entity.Course;

public interface CourseService {
	
	public void save(Course course);
	
	public List<Course> findAllCourses();
	
	public Course findCourseById(int id);
	
	public void deleteCourseById(int id);
}
