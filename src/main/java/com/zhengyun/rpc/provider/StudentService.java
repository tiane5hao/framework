package com.zhengyun.rpc.provider;

import com.zhengyun.rpc.IStudentService;
import com.zhengyun.rpc.Student;

/**
 * Created by 听风 on 2017/12/29.
 */
public class StudentService implements IStudentService{
    public String print(Student student) {
        System.out.println(student);
        return student.getUserName();
    }
}
