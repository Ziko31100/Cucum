package utils;

import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.openqa.selenium.json.Json;

public class APIPayloadConstants {
public static String createEmployeePayload(){
    String createEmployeePayload= "{\n" +
            "  \"emp_firstname\": \"Voya\",\n" +
            "  \"emp_lastname\": \"D\",\n" +
            "  \"emp_middle_name\": \"ms\",\n" +
            "  \"emp_gender\": \"M\",\n" +
            "  \"emp_birthday\": \"1990-03-03\",\n" +
            "  \"emp_status\": \"Active\",\n" +
            "  \"emp_job_title\": \"IT\"\n" +
            "}";
    return createEmployeePayload;
}
public static String createEmployeeJsonPayload(){
    JSONObject obj=new JSONObject();
   obj.put("emp_firstname","Voya");
   obj.put("emp_lastname","D");
    obj.put("emp_middle_name","ms");
    obj.put("emp_gender","M");
    obj.put("emp_birthday","1990-03-03");
    obj.put("emp_status","Active");
    obj.put("emp_job_title","IT");
    return obj.toString();
}

    public static String createEmployeeJsonPayloadDynamic(
    String emp_firstname,String emp_lastname,
    String emp_middle_name,String emp_gender,
    String emp_birthday,String emp_status,String emp_job_title){
    JSONObject obj=new JSONObject();
    obj.put("emp_firstname",emp_firstname);
        obj.put("emp_lastname",emp_lastname);
        obj.put("emp_middle_name",emp_middle_name);
        obj.put("emp_gender",emp_gender);
        obj.put("emp_birthday",emp_birthday);
        obj.put("emp_status",emp_status);
        obj.put("emp_job_title",emp_job_title);


        return obj.toString();



}

public static String UpdateEmployee(String empId,String emp_firstname,String emp_lastname,
                                    String emp_middle_name,String emp_gender,
                                    String emp_birthday,String emp_status,String emp_job_title){
    JSONObject obj=new JSONObject();
    obj.put("employee_id",empId);
    obj.put("emp_firstname",emp_firstname);
    obj.put("emp_lastname",emp_lastname);
    obj.put("emp_middle_name",emp_middle_name);
    obj.put("emp_gender",emp_gender);
    obj.put("emp_birthday",emp_birthday);
    obj.put("emp_status",emp_status);
    obj.put("emp_job_title",emp_job_title);
    return obj.toString();
}


}
