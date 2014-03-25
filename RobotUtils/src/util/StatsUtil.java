package util;

import java.util.List;

public class StatsUtil {
    public static int removeAnomaliesAndGetAverage(List<Float> list) {
        int sum = 0;
        for(int i = 0; i < list.size(); i++) {
            sum += list.get(i); 
        }

        int mean = sum / list.size();
        int meandiffs = 0;

        for(int i = 0; i < list.size(); i++) {
            meandiffs += ((list.get(i) - mean) * (list.get(i) - mean));
        }

        double sigma = Math.sqrt(meandiffs / list.size());

        boolean changed = false;
        for(int i = 0; i < list.size(); i++) {
            if(Math.abs(list.get(i) - mean) > sigma) {
                list.remove(i);
                i--;
                changed = true;
            }
        }

        if(changed) {
            return removeAnomaliesAndGetAverage(list);
        } else {
            return mean;
        }

    }
}
