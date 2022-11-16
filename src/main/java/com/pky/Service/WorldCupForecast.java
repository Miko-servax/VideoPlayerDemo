package com.pky.Service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class WorldCupForecast {

    List<String> list = new ArrayList<>();

    public String forecast(String host, String guest) {
        // String host = "巴西",guest = "瑞士";
        Random random = new Random();
        double rateLeft = 0.8 + 0.1,rateMid = 0.4, rateRight = 0.6+0.4;
        String res = null;
        String rate = null;
        list.clear();
        // List<String> list = new ArrayList<>();
        double rateTotal = rateLeft +rateMid +rateRight;
        double result = random.nextDouble() + rateTotal;
        if (result < rateTotal - rateMid -rateRight){
            res = "结果为：主队("+host+")胜，可选比分如下：";
            for (int i =0;i<3;i++){
                rate = randomUnFairScore(random, host, guest, true);
                list.add(rate);
            }
        }else if (result<rateTotal-rateRight){
            res = "结果为：主队("+host+")平，可选比分如下：";
            rate = randomFairScore(random, host, guest);
            list.add(rate);
        }else {
            res = "结果为：主队("+host+")败，可选比分如下：";
            for (int i =0;i<3;i++){
                rate = randomUnFairScore(random,host,guest,false);
                list.add(rate);
            }
        }
        // model.addAttribute("rate",list);
        return res;
    }

    public List<String> getRate(){
        return list;
    }

    public static String randomFairScore(Random random,String host,String guest){
        int score = 4 - (int)Math.sqrt(random.nextInt(25));
        System.out.println(host+":"+guest+"="+score+":"+score);
        return host+":"+guest+"="+score+":"+score;
    }

    public static String randomUnFairScore(Random random, String host, String guest, boolean win){
        int left = randomInt(1,4);
        int right = Math.max(0,left-randomInt(1,4));

        if (win){
            System.out.println(host+" : "+guest+" = "+left+" : "+right);
            return host+" : "+guest+" = "+left+" : "+right;
        }else{
            System.out.println(host+" : "+guest+" = "+right+" : "+left);
            return host+" : "+guest+" = "+right+" : "+left;
        }
    }

    private static int randomInt(int min, int max){
        return new Random().nextInt(max)%(max-min+1) + min;
    }

}
