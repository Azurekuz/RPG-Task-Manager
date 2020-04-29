public class NumTools {
    static int intClamp(int min, int max, int curNum, int numChange){
        if(curNum + numChange > max){
            return max;
        }else if(curNum + numChange < min){
            return min;
        }else{
            return curNum + numChange;
        }
    }

    static int intLowerBorder(int min, int curNum, int numChange){
        if(curNum + numChange < min){
            return min;
        }else{
            return curNum + numChange;
        }
    }

    static int intUpperBorder(int max, int curNum, int numChange){
        if(curNum + numChange > max){
            return max;
        }else{
            return curNum + numChange;
        }
    }
}
