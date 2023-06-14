import java.util.Random;
import java.util.Scanner;

public class jong {
    public static void main(String[] args) {
        Random random = new Random();


//        int[] ={1,2,3}
        // 위아래 같다
        int rdNumber1 = random.nextInt(10);
        int rdNumber2 = random.nextInt(10);
        int rdNumber3 = random.nextInt(10);

//        System.out.println("3자리 랜덤숫자" + rdNumber1*100 + rdNumber2*10 + rdNumber3);



        for(int i=0;i>=0;i++){
            if (rdNumber1==rdNumber2){
                rdNumber2 = random.nextInt(10);
            } else {
                break;
            }
        }

        for(int j=0;j>=0;j++){
            if ((rdNumber1==rdNumber3)||(rdNumber2==rdNumber3)){
                rdNumber3 = random.nextInt(10);
            } else {
                break;
            }
        }
        System.out.println("3자리 랜덤숫자" + rdNumber1 + rdNumber2 + rdNumber3);

        Scanner sc = new Scanner(System.in);


        int st = 0;
        int ba = 0;

        while(true){
            int cNumber1 = sc.nextInt();
            int cNumber2 = sc.nextInt();
            int cNumber3 = sc.nextInt();

            if(rdNumber1==cNumber1){
                st++;
            } else if(rdNumber1==cNumber2){
                ba++;
            } else if(rdNumber1==cNumber3) {
                ba++;
            }
            if(rdNumber2==cNumber2){
                st++;
            } else if(rdNumber2==cNumber1){
                ba++;
            } else if(rdNumber2==cNumber3) {
                ba++;
            }
            if(rdNumber3==cNumber3){
                st++;
            } else if(rdNumber3==cNumber1){
                ba++;
            } else if(rdNumber3==cNumber2) {
                ba++;
            }
            //else if 분리 시키기 밑으로 쭉 진행되는데 조건이 만족하면 if문을 빠져나오게 된다


            System.out.println(st+"S "+ba+"B");

            //반복이 될때 마다 +되는것이 아니라 초기화를 시킨다
            if(st==3){

                System.out.println("정답");
                break;
            }
            st = 0;
            ba = 0;

        }
        // 입력을 했을때 출력이 되어야되고
        // 틀렸을 경우에는 다시 적을수 있게 되어야되고
        // 3스트라이크가 되면 종료가 된다
    }

}

//맞출때까지 반복한다 (st가 3이 될때까지)


//rdNumber1이랑 cNumber23이랑 같을때 볼에 넣기
//rdNumber2랑 cNumber2랑 같을때 스트라이크 넣고 rdNumber2랑 cNumber13이랑 같을때 볼넣고
// rdNumber3이랑 cNumber3랑 같을때 스트라이크 넣고 rdNumber3이랑 cNumber12랑 같을때 볼넣고



//1. 랜덤 숫자 만들기
//2. 한자리 숫자에 대해 볼, 스크라이크 판단 하는 부분 구현하기
//3. 볼, 스트라이크를 표현하는 부분 구현하기
//4. 게임 종료하는 부분 구현하기