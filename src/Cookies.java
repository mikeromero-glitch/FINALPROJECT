import java.util.TimerTask;
import java.util.Timer;

public class Cookies {
    int cookieCount = 0;
    int AmountPerClick = 1;
    int Upgrade1Purchases = 0;
    int Upgrade1BaseCost = 15;
    Timer CPSTimer = new Timer();

    public int getCookieCount(){return cookieCount;}
    public int getAmountPerClick(){return AmountPerClick;}
    public int getUpgrade1Purchases(){return Upgrade1Purchases;}

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
        //15(base cost)*(1.15)^(times purchased)
        return (int) Math.round(Upgrade1BaseCost*Math.pow(1.15,Upgrade1Purchases));
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
}