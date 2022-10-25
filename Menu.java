public class Menu {
    private Player p1 =  new Player();
    private Player p2 =  new Player();    
    private String message;


    public Menu(Player p1, Player p2){
        this.p1 = p1;
        this.p2 = p2;
    }
    public String commands(String command){
        switch (command) {
            case "Login":
                
                break;
        
            default:
                break;
        }
        return message;
    }
}
