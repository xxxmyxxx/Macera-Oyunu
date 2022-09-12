import java.util.Scanner;

public class Game {
    private Scanner input =new Scanner(System.in);

    public void start(){
        System.out.println("Macera Oyununa Hoşgeldiniz");
        System.out.println("Lütfen bir isim giriniz: ");
        boolean win=false;
        //String playerName=input.nextLine();
        Player player=new Player("Mustafa");
        System.out.println("Sayın "+player.getName()+ " Macera oyununa hoşgeldiniz..");
        player.selectChar();


        Location location = null;
        while (true){
            player.printInfo();
            System.out.println();
            System.out.println("----------Bölgeler--------");
            System.out.println();
            System.out.println("1-Güvenli Ev");
            System.out.println("2-Eşya dükkanı");
            System.out.println("3-Mağara");
            System.out.println("4-Orman");
            System.out.println("5-Nehir");
            System.out.println("6-Maden");
            System.out.println("0-Çıkış yap --> oyunu sonlandır.");
            System.out.print("Lütfen gitmek istediğniz bölgeyi seçiniz : ");
            int selectLoc=input.nextInt();

            if (selectLoc == 3 && player.getInventory().getFood() == 1){
                selectLoc = 1;
                System.out.println("   ##### GITMEK ISTEDIGINIZ HARITAKI GOREVI TAMAMLADINIZ ##### ");
                System.out.println();
                System.out.println("          ###Safe House'a yonlendiriliyorsunuz###");
            }else if (selectLoc == 4 && player.getInventory().getWood() == 1){
                selectLoc = 1;
                System.out.println("   ##### GITMEK ISTEDIGINIZ HARITAKI GOREVI TAMAMLADINIZ ##### ");
                System.out.println();
                System.out.println("          ###Safe House'a yonlendiriliyorsunuz###");
            }else if (selectLoc == 5 && player.getInventory().getWater() == 1){
                selectLoc = 1;
                System.out.println("   ##### GITMEK ISTEDIGINIZ HARITAKI GOREVI TAMAMLADINIZ ##### ");
                System.out.println();
                System.out.println("          ### Safe House'a yonlendiriliyorsunuz ###");
            }
            switch (selectLoc){
                case 0:
                    location=null;
                    break;
                case 1:
                    if (player.getInventory().getFood() == 1 && player.getInventory().getWood() == 1 && player.getInventory().getWater() == 1) {
                        win = true;
                        break;
                    }
                    location=new SafeHouse(player);
                    break;
                case 2:
                    location =new ToolStore(player);
                    break;
                case 3:
                    location=new Cave(player);
                    break;
                case 4:
                    location=new Forest(player);
                    break;
                case 5:
                    location=new River(player);
                    break;
                case 6:
                    location=new Coal(player);
                    break;
                default:
                    System.out.println("Lütfen doğru düzgün bir sayı giriniz.");
            }

            if (location== null){
                System.out.println("Korktun demek Hah Hah Haaaaa ");
                break;

            }
            if(win){
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println(   "   ############################## MISSION COMPLETED #########################");
                System.out.println("     ########################## HARITANIN ANASINI AGLATTIN #########################");
                System.out.println("        ################ ONLARI GEBETTIN LANET OLSUN SANA DOSTUM ####################");
                break;
            }
            if(!location.onLocation()){
                System.out.println("GAME OVER !");
                break;
            }
        }


    }
}
