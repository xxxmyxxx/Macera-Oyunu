import java.util.Random;

public class Snake extends Monster{
    public Snake(){
        super(4,"Yılan",(int) (Math.random()*6+3),12,0);
    }
}

