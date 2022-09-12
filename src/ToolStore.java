public class ToolStore extends NormalLoc {
    public ToolStore(Player player){
        super(player,"Mağaza");
    }

    @Override
    public boolean onLocation() {
        System.out.println("-----------------------");
        System.out.println("Mağazaya hoşgeldiniz !");
        System.out.println("-----------------------");
        boolean showMenu=true;
       while(showMenu){
           System.out.println("1-Silahlar");
           System.out.println("2-Zırhlar");
           System.out.println("3.Çıkış yap");
           System.out.println();
           System.out.print("Seçiminiz: ");
           int selectCase=input.nextInt();
           while(selectCase<1 || selectCase>3){
               System.out.println("Geçerli bir değer giriniz: ");
               selectCase=input.nextInt();
           }
           switch (selectCase){
               case 1:
                   printWeapon();
                   buyWeapon();

                   break;
               case 2:
                   printArmor();
                   buyArmor();
                   break;
               case 3:
                   System.out.println("Yine beklerizzzz !!");
                   showMenu=false;
                   break;
           }
       }
       return true;
    }
    public void printWeapon(){
        System.out.println("--------Silahlar-----------");
        System.out.println();
        for(Weapon w:Weapon.weapons()){
            System.out.println(w.getId()+"-"+ w.getName()+" Fiyat: "+w.getPrice()+" Hasar: "+w.getDamage());
        }
        System.out.println("0- Çıkış yap.");
    }
    public void buyWeapon(){
        System.out.print("Bir silah seçin: ");
        int selectWeaponId= input.nextInt();
        while(selectWeaponId<0 || selectWeaponId >Weapon.weapons().length){
            System.out.println("Geçeresiz sayı girdin tekrar gir: ");
            selectWeaponId= input.nextInt();
        }

        if(selectWeaponId!=0){
            Weapon selectedWeapon = Weapon.getWeaponobjById(selectWeaponId);
            if(selectedWeapon!=null){
                if (selectedWeapon.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Yeterli paranız bulunamamktadır !");
                }else{
                    // Satın alma işlemi burada gerçekleşiyor..
                    System.out.println(selectedWeapon.getName()+" silahını satın aldınız.");
                    int balance =this.getPlayer().getMoney()-selectedWeapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan paranız : "+this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                }
            }
        }
    }

    public void printArmor(){
        System.out.println("--------Armorlar----------");
        for(Armor a:Armor.armors()){
            System.out.println(a.getId()+" - "+a.getName()+
                    " Para: "+a.getPrice()+
                    " Zırh: "+a.getBlock());
            System.out.println("--------------------------");
        }
        System.out.println("0- Çıkış yap.");

    }
    public void buyArmor(){
        System.out.println("Bir zırh seçiniz : ");
        int selectArmorID=input.nextInt();
        while(selectArmorID<0 || selectArmorID >Armor.armors().length){
            System.out.println("Geçeresiz sayı girdin tekrar gir: ");
            selectArmorID= input.nextInt();
        }

        if(selectArmorID !=0){
            Armor selectedArmor = Armor.getArmorobjByID(selectArmorID);
            if(selectedArmor !=null){
                if(selectedArmor.getPrice()>this.getPlayer().getMoney()){
                    System.out.println("Yeterli bakiyeniz bulunmamaktadır.");
                }else{
                    System.out.println(selectedArmor.getName()+ " zırhı satın aldınız.");
                    this.getPlayer().setMoney(this.getPlayer().getMoney()-selectedArmor.getPrice());
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                    System.out.println("Kalan Bakiye : "+this.getPlayer().getMoney());
                }
            }
        }

    }
}
