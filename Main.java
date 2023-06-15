package ForkBaseballNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 1. 랜덤으로 0~9 사이 숫자 3개 생성하기  = 랜덤함수 생성
        // 1-1 랜덤으로 숫자 3개를 생성하고 저장할 곳 만들기
        //      단, 숫자가 겹치면 안됨.
        int[] randomNumbers = new int[3]; // 3개의 값이 저장될 곳 만들기
        // 수정 1. 컴퓨터가 랜덤숫자를 하나만 계속 뽑는 경우 방지
        // 수정 1-1 0~9를 가지는 숫자 배열 생성
        Random selectNumber = new Random();
        ArrayList<Integer> numberList = new ArrayList(); // 수정 1-1-1 초안 int []로 만들었으나, 특정 인덱스를 제거하는 메서드가 없어서 ArrayList 이용
        for (int i=0;i<10;i++){
            numberList.add(i);
        }
        for (int i=0; i<randomNumbers.length;){
            int j = 10-i;
            int ranomNumber = selectNumber.nextInt(j); // 수정 1-2 랜덤 숫자를 첫바퀴는 0~9사이 두번째는 0~8.. 진행 할 때 마다 하나씩 줄어들게 만든다.
                                                       // (int) (Math.random() * 10) 초안 : Math.random은 0이상 1미만의 값을 반환하기 때문에 10을 곱해서
                                                       // 0 ~ 10을 받아오게 만들어 줘야한다.
            randomNumbers[i] = numberList.get(ranomNumber); // 수정 1-3 생성된 랜덤숫자를 이용해서 미리 만들어둔 0~9 배열에서 하나를 가져온다.
            numberList.remove(ranomNumber); // 수정 1-4 배열에서 가져온 인덱스를 삭제해서 배열의 크기를 줄여준다.
                                            // 수정 1-2 ~ 1-4를 통해 배열의 크기를 조절해서 컴퓨터가 9를 연속으로 뽑지 못하게 방지하고 그에따라 배열의 크기도 수정한다.
                                            // 수정을 통해 컴퓨터가 하나의 랜덤숫자를 뽑더라도 이상없이 랜덤으로 숫자가 생성된다.
            i++;
        }
        // System.out.println(Arrays.toString(randomNumbers)); // 중복없는 난수를 출력

        // 1-2 만들고 나서 "컴퓨터가 숫자를 생성하였습니다. 답을 맞춰보세요!" 출력하기
        System.out.println("컴퓨터가 숫자를 생성하였습니다. 답을 맞춰보세요!");

        // 2. 숫자 3개 입력받기
        Scanner scanner = new Scanner(System.in);
        // 2-2 프린트로 몇번째 시도인지 출력하기
        int tryCount = 0;                                           // fix1 수정을 위해 try count = 0 으로 초기값을 수정

        // [0,0,0] 다른 수정 방법
        // while (true) {                                              // fix1 1번째 시도 000은 초기값인데, 0번째 시도로 나오게 표시하였고,
        //   if (tryCount > 0){                                      // 0번째 시도는 출력하지 않기위해 if (tryCount > 0) 이라는 조건을 추가함
        //       
        //   }

        reply:
        while (true) {
            System.out.println("세 자리 숫자를 입력하시오");
            // 2-1 숫자 3개를 입력받아 저장할 곳 만들기
            int[] pickNumbers = new int[3];
            userInput:
            // 수정 2. 유저가 범위 밖의 값을 입력하거나 중복된 값을 입력
            for (int i = 0; i < pickNumbers.length; ) {
                System.out.println((i+1)+"번째 숫자를 입력하세요");
                int pickNumber = scanner.nextInt();
                // 수정 2-1 유저가 범위 밖의 값을 입력할 때
                if(pickNumber>=10 || pickNumber<0){
                    System.out.println("잘못된 입력입니다.");
                    continue;
                }
                // 수정 2-2 유저가 중복된 값을 입력
                for(int j=0; j<i; j++){ // 수정 2-2-1 for(int j : pickNumbers) 향상된 for문 이용 -> 문제발생 초기값이 0 이어서 0은 항상 중복됨
                    if (pickNumber==pickNumbers[j]){ // 수정 2-2-2 수정된 for 문에 맞춰서 조건 수정
                        System.out.println("중복된 값입니다.");
                        continue userInput;
                    }
                }
                pickNumbers[i] = pickNumber;
                i++;
            }
            // 3. 숫자 3개를 비교해서 출력하기
            int strike = 0;
            int ball = 0;
            // 3-1 숫자를 하나씩 꺼내서 비교하여 출력하기.
            for (int i = 0; i < randomNumbers.length; i++) {
                for (int j = 0; j < pickNumbers.length; j++) {
                    if (randomNumbers[i] == pickNumbers[j]) {
                        if (i == j) {
                            strike++;
                        } else {
                            ball++;
                        }
                    }
                }
            }
            // 3-2 숫자의 값과 위치가 모두 일치하면 일치한 횟수 + "S" +
            //     숫자의 값은 일치하지만 위치가 틀렸린 경우의 횟수 + "B" 출력하기
            System.out.print(tryCount + "번째 시도 : ");         // fix2 "번째 시도 : " ) + ~~~ 뒷부분 Arrays.toString 삭제후
            for (int i = 0; i < pickNumbers.length; i++) {      // 반복문으로 picknumbers 배열 0 1 2 번째 출력
                System.out.print(pickNumbers[i]);               // println 때문에 출력이 한 칸씩 내려와서 두 줄 모두 print로 작성
            }
            System.out.println();                               // 답을 입력하는 곳이 n번째 시도 : abc 바로옆에서 시작되어서 한 줄 밑에서 시작하게 추가함
            System.out.println(ball + "B" + strike + "S");

            // 4. 3S가 출력이 된다면 몇번만에 맞췄는지 출력한 후에 게임 종료하기.
            if (strike == 3) {
                System.out.println((tryCount+1) + "번만에 맞추셨습니다!");
                System.out.println("게임을 종료합니다.");
                break;
            }
            // 4-1 3S가 아니라면 다시 시도하게 만들기
            tryCount++;
            }
        }
    }



// 1. 랜덤으로 0~9 사이 숫자 3개 생성하기
// -> 랜덤함수 찾아서 쓰기
// 1-1 랜덤으로 숫자 3개를 생성하고 저장할 곳 만들기
// 랜덤함수 저장할 곳 int[] 랜덤함수 =

// 1-2 만들고 나서 "컴퓨터가 숫자를 생성하였습니다. 답을 맞춰보세요!" 출력하기
//      단, 숫자가 겹치면 안됨.

// 2. 숫자 3개 입력받기
// 2-1 숫자 3개를 입력받아 저장할 곳 만들기
// 2-2 프린트로 몇번째 시도인지 출력하기

// 3. 숫자 3개를 비교해서 출력하기
// 3-1 숫자를 하나씩 꺼내서 비교하여 출력하기.
// 3-2 숫자의 값과 위치가 모두 일치하면 일치한 횟수 + "S" +
//     숫자의 값은 일치하지만 위치가 틀렸린 경우의 횟수 + "B" 출력하기.

// 4. 3S가 출력이 된다면 몇번만에 맞췄는지 출력한 후에 게임 종료하기.
// 4-1 3S가 아니라면 다시 시도하게 만들기
