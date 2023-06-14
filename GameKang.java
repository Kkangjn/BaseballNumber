import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameKang {
    public static void main(String[] args) {
        System.out.println("컴퓨터가 랜덤숫자를 생성합니다.");
        Random random = new Random();
        ArrayList<Integer> computerNumList = new ArrayList<Integer>();

        ArrayList<Integer> randomNumber = new ArrayList<Integer>();
        for (int i=0; i<=9; i++) { // 배열 생성 = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
            randomNumber.add(i);
        }

        for (int i=0;i<=2;){
            int j = 10-i;
            int randomNum = random.nextInt(j); // 랜덤 정수 하나 생성
            computerNumList.add(randomNumber.get(randomNum)); // 랜덤정수로 배열 [0, 1, 2, 3, 4, 5, 6, 7, 8, 9] 에서 하나 뽑기
            randomNumber.remove(randomNum); // 뽑은 배열 인덱스 삭제
            i++;
        }
//        System.out.println(computerNumList.toString()); // 컴퓨터 생성 출력()
        System.out.println("컴퓨터가 숫자 생성 완료! 답을 맞춰보세요!");



        replay:
        for (int x=1;x>=0;x++) {
            System.out.println("숫자를 입력하세요");
            Scanner sc = new Scanner(System.in);
            ArrayList<Integer> userNumList = new ArrayList<Integer>();
            createUserNum:
            for (int i = 1; i <= computerNumList.size(); ) { //유저 입력
                System.out.println(i+"번째 숫자를 입력하세요");
                int userNum = sc.nextInt(); // Scanner 로 받음

                if (userNum>=10){ // 잘못된값 예외처리
                    System.out.println("잘못된 입력입니다.");
                    continue createUserNum;
                }

                for (int j : userNumList) {
                    if (j == userNum) { // 중복값 처리
                        System.out.println("중복된 값입니다.");
                        continue createUserNum;
                    }
                }
                userNumList.add(userNum);
                i++;
            }
//            System.out.println(userNumList.toString());

            System.out.println(x+"번째 시도: "+userNumList.toString());
            int strike = 0;
            int ball = 0;
            for (int i = 0; i < userNumList.size(); i++) { // 값 비교부분
                int checkUserNum = userNumList.get(i); // 유저 인덱스 1, 2, 3
                for (int j = 0; j < computerNumList.size(); j++) { // 유저인덱스를 각각 랜덤숫자 1, 2, 3 과 비교
                    int checkComputerNum = computerNumList.get(j);
                    if (checkComputerNum == checkUserNum) {
                        if (i == j) { // 숫자 자리 비교
                            strike++;
                        } else {
                            ball++;
                        }
                    } else {
                        continue;
                    }
                }
            }
            if (strike == 3) {
                System.out.println("정답입니다.");
                System.out.println(x+"번만에 맞히셨습니다!");
                System.out.println("게임을 종료합니다.");
                break replay;
            } else {
                System.out.println(ball + "B" + strike + "S");
                continue replay; // 입력부터 반복!
            }
        }
    }
}
