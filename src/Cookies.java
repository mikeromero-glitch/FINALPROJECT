import java.util.TimerTask;
import java.util.Timer;

public class Cookies {
    int cookieCount = 0;
    int AmountPerClick = 1;
    int AmountPerClick2 = 2;
    int Upgrade1Purchases = 0;
    int Upgrade2Purchases = 0;
    int Upgrade1BaseCost = 10;
    int Upgrade2BaseCost = 50;
    Timer CPSTimer = new Timer();

    public int getCookieCount(){return cookieCount;}
    public int getAmountPerClick(){return AmountPerClick;}
    public int getUpgrade1Purchases(){return Upgrade1Purchases;}
    public int getUpgrade2Purchases(){return Upgrade2Purchases;}

    public void AutoTimer() {
        CPSTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                cookieCount+=AmountPerClick;
            }
        },0,1000);
    }
        public void Click () {

        cookieCount+=AmountPerClick;
    }


    public int getUpgrade1Cost(){
        //15(base cost)*(1.10)^(times purchased)
        return (int) Math.round(Upgrade1BaseCost*Math.pow(1.10,Upgrade1Purchases));
    }
    public int getUpgrade2Cost(){
        //15(base cost)*(1.50)^(times purchased)
        return (int) Math.round(Upgrade2BaseCost*Math.pow(1.50,Upgrade2Purchases));
    }

    public boolean buyUpgrade1(){
        int cost = getUpgrade1Cost();
        if(cookieCount>=cost){
            cookieCount-=cost;
            Upgrade1Purchases++;
            AmountPerClick++;
            return true;
        }
        return false;
    }
    public boolean buyUpgrade2(){
        int cost = getUpgrade2Cost();
        if(cookieCount>=cost){
            cookieCount-=cost;
            Upgrade2Purchases++;
            AmountPerClick2++;
            return true;
        }
        return false;
    }
}
