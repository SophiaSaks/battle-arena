package arena;

public class Dragon extends GameCharacter{

    public Dragon (){
        super(80, "Dragon");
    }


    public void useSuperpower(){

    }

    @Override
    public int calculateDamage(){
        return getRandom().nextInt(11)+9 ;

    }
}
