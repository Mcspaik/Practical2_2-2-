/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.practical22_2;
import java.util.HashMap;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
/**
 *
 * @author Люба
 */
public class Practical22_2 {

    public static void main(String[] args) {
        System.out.println("РИБО-02-22, Степеренкова Любовь Алексеевна");
        System.out.println("Start program!!!!!!");
        String server = "https://android-for-students.ru";
        String serverPath = "/materials/practical/hello.php";
        HashMap<String,String> map = new HashMap();
        map.put("name", "");
        map.put("group", "RIBO-02-22");
        HTTPRunnable httpRunnable = new HTTPRunnable(server + serverPath, map);
        Thread th = new Thread(httpRunnable);
        th.start();
        try{
            th.join();
        }catch (InterruptedException ex){
            
        }finally{
            JSONObject jSONObject = new JSONObject(httpRunnable.getResponseBody());
            int result = jSONObject.getInt("result_code");
            System.out.println("Result: " + result);
            System.out.println("Type: " + jSONObject.getString("message_type"));
            try {
                System.out.println("Text: " + jSONObject.getString("message_text"));
            }catch (JSONException ex){
                ex.getMessage();
            }
            switch(result){
                case 1:
                    JSONArray jSONArray = jSONObject.getJSONArray("task_list");
                    System.out.println("Task list:");
                    for(int i = 0;i < jSONArray.length();i++){
                        System.out.println((i+1) + ") " + jSONArray.getString(i));
                    }
                    break;
                case 0:
                    System.out.println("Something's wrong....:(");
                    break;
                default:
                    System.out.println("Situation becomes worse");
                    break;
            }
        }
    }
 }