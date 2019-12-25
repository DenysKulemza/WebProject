package db.entity;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Courses implements Serializable{

    private static final long serialVersionUID = 1L;

    private ArrayList<String>courses = new ArrayList<>();

    public void addCourses(){
      try{
          File file = new File("D:\\courses.txt");
          BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
          String line = "";

          while ((line = bufferedReader.readLine()) != null) {
              courses.add(line);
          }
      }catch (IOException ex){
          ex.printStackTrace();
      }
    }

    public void sortCourses(){
        addCourses();
        Collections.sort(courses);

        for(String s : courses){
            System.out.println(s);
        }
    }

}
