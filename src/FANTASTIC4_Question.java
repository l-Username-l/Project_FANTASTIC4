import java.sql.Statement;
import java.util.Scanner;

import java.sql.ResultSet;
//import java.sql.SQLException;
import java.sql.SQLException;

public class FANTASTIC4_Question {

    static String QUERY1 = "SELECT SURV_NO, SURV FROM SURV_NO";
    static String QUERY2 = "SELECT ANS_NO, ANS FROM ANS_NO";
    static String QUERY3 = "SELECT NAME_NO, NAME FROM NAME_NO";
    static String QUERY4 = "SELECT NAME_NO, SURV_NO, ANS_NO FROM result";
    Statement stmt;
    String current_name;
    ResultSet rsQues;
    ResultSet rsAns;
    ResultSet rsName;
    ResultSet rsResult;
    int val;
    int val1;

    public String[] questFunction(String user_name){


        // Step1: User name 받음
        Scanner scanner = new Scanner(System.in);//static 
        current_name = user_name;
        String[] usr_output = new String[4];//static
        
        


        // Step2: Fantastic4 DB 내의 모든 table과 연동 & Query 창 열기
        try {
            FANTASTIC_MySQL_Connect connect = new FANTASTIC_MySQL_Connect();
            stmt = connect.SQL_connect();
            //FANTASTIC_MySQL_Connect connect = new FANTASTIC_MySQL_Connect();
            //stmt = FANTASTIC_MySQL_Connect.SQL_connect(QUERY1);

            // Tables 연동
            //stmt = FANTASTIC_MySQL_Connect.SQL_connect();
            rsQues = stmt.executeQuery(QUERY1);
            rsAns = stmt.executeQuery(QUERY2);
            //rsName = stmt.executeQuery(QUERY3);

            // Step3: 'name_no' table에 사용자 번호와 이름 update
            int usr_number;//Static 
            QUERY3 = "select count(*) from NAME_NO";
            ResultSet re_arbi = stmt.executeQuery(QUERY3);// + 1;
            re_arbi.next();
            usr_number = re_arbi.getInt(1);
            usr_number += 1;
            // Update!!
            QUERY3 = "insert into name_no (NAME_NO, NAME) values ("+usr_number+",\""+current_name+"\")";
            val = stmt.executeUpdate(QUERY3);



            // Step4: 'surv_no' table에서 질문 사항 불러들이기
            // Step5: 질문 사항 하위에 'ans_no' table에서 질문들 불러들이기
            int i = 0;  // 여기서 오류남 
            while (!rsQues.next()) { 
                // Retrieve by column name
                System.out.print(rsQues.getInt("SURV_NO")+"번 문항 : ");
                System.out.println(rsQues.getString("SURV"));
                
                System.out.println("----------------------------------------");
                while (rsAns.next()){

                    System.out.print(rsAns.getInt("ANS_NO")+"번 대답 : ");
                    System.out.println(rsAns.getString("ANS"));

                }

                // Step6: 설문자의 답 받아들이기
                System.out.print("--------답을 선택해 주세요 : ");

                String choose_num = scanner.nextLine();
                if (choose_num.length() == 1){

                    // Input the result of the question
                    usr_output[i] = choose_num;
                    i++;
                } else {
                    System.out.println("복수의 답이 입력 되었거나 답항지에 없는 번호입니다.");
                    System.out.println("답을 다시 입력하여주세요.");
                    
                    // 조건에 맞는 입력을 하지 않았을 경우 다시 해당 질문을 console 창에 띄기
                    rsQues.previous();

                }


            }

            // Step7: 설문 조사 결과로 'result' table update
            //rsResult = stmt.executeQuery(QUERY4);
            for (int j = 0; j < usr_output.length; j++){
                QUERY4 = "insert into result (NAME_NO, SURV_NO, ANS_NO) values ("+ usr_number +","+ j +","+ usr_output[j] +")";
                val1 = stmt.executeUpdate(QUERY3);
            }
        } catch (SQLException e) {
            
            e.printStackTrace();

        }
        
        return usr_output;

        
    }
}