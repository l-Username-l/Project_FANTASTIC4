import java.sql.SQLException;
import java.util.Scanner;

public class FANTASTIC4_MAIN {

    //public static int P = 1;
    //static int S = 2;
    //static int Q = 3;
    //String[] usr_output = new String[4];
    //String usr_name;
    //Scanner scanner;
    //int i;

    public static void main(String[] aStrings) throws SQLException {
        boolean run = true;
        String[] usr_output = new String[4];
        String usr_name;
        Scanner scanner;
        int i;

        //scanner = new Scanner(System.in);

        while (run) {
            
            scanner = new Scanner(System.in);
            System.out.println("----------------------------------");
            System.out.println(" P.설문시작 | S.통계 | Q.종료 ");
            System.out.println("----------------------------------");
            System.out.print("선택) ");

            //int selectNo = Integer.parseInt(scanner.nextLine());
            String selectNo = scanner.nextLine();

            if (selectNo.equals("P") ) {
                System.out.println("--------설문을 시작합니다---------");
                System.out.print("이릅을 입력하세요: ");
                usr_name = scanner.nextLine();

                // Generation of the instance
                FANTASTIC4_Question quest = new FANTASTIC4_Question();
                
                i = 0;
                while (i < 4){
                    usr_output[i] = quest.questFunction(usr_name)[i];
                    i++;
                }

                //usr_output = quest.questFunction(usr_name);

            } else if (selectNo.equals("S" )) {

                FANTASTIC4_Static stat = new FANTASTIC4_Static();
                usr_name = scanner.nextLine();
                stat.statFunction(usr_name);

            } else {
                System.out.println("이용해주셔서 감사합니다!");
                break;
            }
        }
        //eturn;
    }
}