import java.util.Random;

public class BattleLoc extends Location {
    private Monster monster;
    private String award;
    private int maxMonster;

    public BattleLoc(Player player, String name, Monster monster, String award, int maxMonster) {
        super(player, name);
        this.monster = monster;
        this.award = award;
        this.maxMonster = maxMonster;
    }

    @Override
    public boolean onLocation() {
        int monsterNum = this.randomMonsterNumber();
        System.out.println("Şuan buradasınız : " + this.getName());
        System.out.println("Dikkatli ol " + monsterNum + " tane " + getMonster().getName() + " çıkacak.");
        System.out.print("(S)avaş veya (K)aç: ");
        String selectCase = input.nextLine().toUpperCase();

        if (selectCase.equals("S") && combat(monsterNum)) {
            System.out.println(this.getName() + " tüm düşmanları öldürdünüz.");
            return true;

        }
        if (this.getPlayer().getHealthy() <= 0) {
            System.out.println("Öldünüz...");
            return false;
        }

        return true;
    }

    public boolean combat(int monsterNum) {

        for (int i = 1; i <= monsterNum; i++) {
            this.getMonster().setHealth(this.getMonster().getOrginalHealth());
            playerStats();
            monsterStats(i);
            while (this.getPlayer().getHealthy() > 0 && this.getMonster().getHealth() > 0) {
                System.out.print("(V)ur veya (K)aç: ");
                System.out.println();
                String selectCombat = input.nextLine().toUpperCase();


                if (selectCombat.equals("V")) {
                    if (change() < 50) {
                        System.out.println("Siz vurdunuz.");
                        System.out.println("--------------");
                        this.monster.setHealth(this.monster.getHealth() - this.getPlayer().getDamage());
                        afterHit();

                    } else {
                        if (this.getMonster().getHealth() > 0) {
                            System.out.println();
                            System.out.println(this.getMonster().getName() + " size vurdu !");
                            System.out.println("-----------------------");
                            int monsterDamage = this.getMonster().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (monsterDamage < 0) {
                                monsterDamage = 0;
                            }
                            this.getPlayer().setHealthy(this.getPlayer().getHealthy() - monsterDamage);
                            afterHit();
                        }
                    }
                } else {
                    return false;
                }
            }
            if (this.getMonster().getHealth() < this.getPlayer().getHealthy()) {
                System.out.println("Düşmanı yendiniz !");
                if (true) {
                    System.out.println(this.getMonster().getAward() + " para kazandınız !");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getMonster().getAward());
                    if (this.getMonster().getName().equals("Zombi")) {
                        this.getPlayer().getInventory().setFood(1);
                        System.out.println("--- Yemek Kazandiniz ---");
                    }
                    if (this.getMonster().getName().equals("Vampir")) {
                        this.getPlayer().getInventory().setWood(1);
                        System.out.println("--- Odun Kazandiniz ---");
                    }
                    if (this.getMonster().getName().equals("Ayı")) {
                        this.getPlayer().getInventory().setWater(1);
                        System.out.println("--- Su Kazandiniz ---");
                    }
                    if (this.getMonster().getName().equals("Yılan")) {
                        drop();
                    }

                }
                System.out.println("Güncel Paranız: " + this.getPlayer().getMoney());
            }

        }
        return false;
    }

    public int change() {
        Random r = new Random();
        return r.nextInt() * 100;
    }

    public void afterHit() {
        System.out.println("Canınız: " + this.getPlayer().getHealthy());
        System.out.println(this.getMonster().getName() + " Canı:  " + this.getMonster().getHealth());
        System.out.println();
    }

    public void playerStats() {
        System.out.println("Oyuncu Değerleri");
        System.out.println("-----------------");
        System.out.println("Saglık: " + this.getPlayer().getHealthy());
        System.out.println("Silah:  " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Armor:  " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Hasar:  " + this.getPlayer().getTotalDamage());
        System.out.println("Para:   " + this.getPlayer().getMoney());
        System.out.println();
    }

    public void monsterStats(int i) {
        System.out.println(i + ". " + this.getMonster().getName() + " Değerleri");
        System.out.println("---------------------");
        System.out.println("Sağlık: " + getMonster().getHealth());
        System.out.println("Hasar:  " + getMonster().getDamage());
        System.out.println("Ödül:   " + getMonster().getAward());
        System.out.println();

    }


    public int randomMonsterNumber() {
        Random r = new Random();
        return r.nextInt(this.getMaxMonster()) + 1;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxMonster() {
        return maxMonster;
    }

    public void setMaxMonster(int maxMonster) {
        this.maxMonster = maxMonster;
    }

    public void drop() {
        Random r = new Random();
        int itemchance = r.nextInt() * 100;
        if (itemchance < 15) {
            int weaponC = r.nextInt() * 100;

            if (weaponC < 20) {
                this.getPlayer().getInventory().setWeapon(Weapon.getWeaponobjById(3));
                System.out.println(getPlayer().getInventory().getWeapon().getName() + " <--------- Kazandınız..");
            }
            if (weaponC < 50 && weaponC >= 20) {
                this.getPlayer().getInventory().setWeapon(Weapon.getWeaponobjById(2));
                System.out.println(getPlayer().getInventory().getWeapon().getName() + " <---------- Kazandınız..");
            }
            if (weaponC < 100 && weaponC >= 50) {
                this.getPlayer().getInventory().setWeapon(Weapon.getWeaponobjById(1));
                System.out.println(getPlayer().getInventory().getWeapon().getName() + " <----------- Kazandınız..");
            }
        } else if (itemchance < 30 && itemchance >= 15) {
            int armorC = r.nextInt() * 100;

            if (armorC < 20) {
                this.getPlayer().getInventory().setArmor(Armor.getArmorobjByID(3));
                System.out.println(getPlayer().getInventory().getWeapon().getName());
            }
            if (armorC < 50 && armorC >= 20) {
                this.getPlayer().getInventory().setArmor(Armor.getArmorobjByID(2));
                System.out.println(getPlayer().getInventory().getWeapon().getName());
            }
            if (armorC < 100 && armorC >= 50) {
                this.getPlayer().getInventory().setArmor(Armor.getArmorobjByID(1));
                System.out.println(getPlayer().getInventory().getWeapon().getName());
            }
        } else if (itemchance < 55 && itemchance >= 30) {
            int moneyC = r.nextInt() * 100;

            if (moneyC < 20) {
                this.getPlayer().setMoney(getPlayer().getMoney() + 10);
                System.out.println("10 para kazandiniz");
            }
            if (moneyC < 50 && moneyC >= 20) {
                this.getPlayer().setMoney(getPlayer().getMoney() + 5);
                System.out.println("5 para kazandiniz");
            }
            if (moneyC < 100 && moneyC >= 50) {
                this.getPlayer().setMoney(getPlayer().getMoney() + 1);
                System.out.println("1 para kazandiniz");

            }
        } else {
            System.out.println("Birşey kazanamadın bro malesef");
        }
    }

}
