public class SafeHouse extends NormalLoc {
    public SafeHouse(Player player){
        super(player,"Güvenli Ev");
    }

    @Override
    public boolean onLocation() {
        System.out.println("------------------------------------");
        System.out.println("Güvenli evdesiniz,Canınınz yenilendi !");
        System.out.println("-------------------------------------");
        this.getPlayer().setHealthy(this.getPlayer().getOrginalhealth());
        return true;
    }
}
