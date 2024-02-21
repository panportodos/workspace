package com.study.insert_and_select1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.study.insert_and_select1.config.DBConfig;
import com.study.insert_and_select1.entity.Student;

public class StudentDAO {

	private static StudentDAO instance;

	private StudentDAO() {
	}

	public static StudentDAO getInstance() {
		if (instance == null) {
			instance = new StudentDAO();
		}
	}

	private String url = "jdbc:mysql://mysqldb.czqyo2moyeic.ap-northeast-2.rds.amazonaws.com/db_study"; // mysql 서버에 접속할
																										// 때 사용하는 서버
	private String username = "aws";
	private String password = "1q2w3e4r!!";

	public Student findStudentByName(String name) { // 이름가지고 Student 객체 찾아주는 메서드
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Student student = null;

		try {
			con = DriverManager.getConnection(DBConfig.URL, DBConfig.USERNAME, DBConfig.PASSWORD);
			String sql = "insert into student_tb(student_name, student_age) values(?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, student.getName());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				student = Student.builder().name(rs.getString(2)).age(rs.getInt(3)).build();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return student;
	}

	public int saveStudent(Student student) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int successCount = 0;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection(DBConfig.URL, DBConfig.USERNAME, DBConfig.PASSWORD);
			String sql = "insert into student_tb(student_name, student_age) values(?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, student.getName());
			successCount = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (pstmt != null) {
					pstmt.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return successCount;
	}

	public List<Student> getStudentListAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Student> students = new ArrayList<>();
		
		try {
			con = DriverManager.getConnection(DBConfig.URL, DBConfig.USERNAME, DBConfig.PASSWORD);
			String sql = "select * from student_tb";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Student student = Student.builder().name(rs.getString(2)).age(rs.getInt(3)).build();
				
				students.add(student); //rs가 끝이 날 때 까지 학생 리스트를 더한다
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return student;
	}

	return students; // 학생 객체를 담을 수 있는 리스트를 생성
}

}
