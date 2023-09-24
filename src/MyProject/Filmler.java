package MyProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Filmler {
    String c_RED    = "\u001B[31m";
    String BOLD     = "\033[0;1m";
    String c_RESET  = "\u001B[0m";
    String c_BLUE   = "\u001B[34m";
    String c_PURPLE = "\u001B[35m";
    String c_GREEN  = "\u001B[32m";

    private static String adminUser = "admin";
    private static String adminPass = "admin01";
    private String userName;
    private String userPass;

    static Scanner scan = new Scanner(System.in);
    static ArrayList<String> filmListe = new ArrayList<>(
            Arrays.asList("JOKER", "INCEPTION", "PIYANIST", "GREENMILE", "LEON", "GODFATHER", "JURASSICPARK", "TITANIC"));

    static ArrayList<String> userList = new ArrayList<>(Arrays.asList("user1","user2","user3","user4"));
    static ArrayList<String> passwordList = new ArrayList<>(Arrays.asList("user01","user02","user03","user04"));

    public void filmTahminEtme() throws InterruptedException {

        System.out.println(BOLD+c_GREEN+"\n *** FİLM TAHMİN OYUNUNA HOŞGELDİNİZ *** \n"+c_RESET);
        try {
            System.out.println("Listemizde bulunan "+filmListe.size()+" filmden birini seçmek için");;
            System.out.print("1 ile " +filmListe.size() + " arasında bir sayı giriniz: ");
            int filmIndex = scan.nextInt();
            scan.nextLine();

            String tahminEdilecekFilm = filmListe.get(filmIndex-1);
            int tahminHakki = tahminEdilecekFilm.length() * 2;
            System.out.println("\n"+BOLD+c_RED+tahminHakki+" defa tahmin hakkınız bulunmaktadır."+c_RESET);
            String ipucuFilm = tahminEdilecekFilm.substring(2);
            ipucuFilm = tahminEdilecekFilm.substring(0,2) + ipucuFilm.replaceAll("\\D","*");
            System.out.println("Filmi tahmin etmen için sana bir ipucu veriyorum -->" + ipucuFilm);
            int sayac =0;
            while (tahminHakki > 0) {
                sayac++;
                System.out.print("\n"+sayac +". tahminini gir: ");
                String tahmin = scan.nextLine().toUpperCase();
                tahmin = tahmin.replaceAll(" ","");

                if (tahmin.equals(tahminEdilecekFilm)) {
                    System.out.println("\nTebrikler! "+ sayac+". tahmin hakkınızda filmi bildiniz. ");
                    break;
                }
                else System.out.println("\nMalasef bilemediniz. "+"\n"+ ((tahminEdilecekFilm.length()*2)-sayac) + " tane hakkınız kaldı. Devamkeee :) ");

                tahminHakki--;

            }
            if (tahminHakki == 0) System.out.println("\nMalasef Kaybettiniz :( ");
            mainMenu();

        }catch (Exception e) {
            System.out.println(BOLD+c_GREEN+"\n *** Hatalı giriş *** \n"+c_RESET);
            scan.nextLine();
            mainMenu();
        }


    }
    public void adminMenu() throws InterruptedException {
        System.out.println(BOLD+c_BLUE+"\n *** ADMİN MENÜSÜ ***\n"+c_RESET);
        try {
            System.out.println("Oyuna geçmek için - 1");
            System.out.println("Film eklemek için - 2");
            System.out.println("Film silmek için - 3");
            System.out.println("Filmleri görüntülemek için - 4");
            System.out.println("Kullanıcıları görüntülemek için - 5");
            System.out.println("Ana menüye dönmek icin - 6");
            System.out.println("Çıkış için - 7");
            System.out.print("Seçiniz : ");

            int secim = scan.nextInt();
            scan.nextLine();

            if ( secim == 1) filmTahminEtme();
            else if ( secim == 2) filmEkleme();
            else if ( secim == 3) filmSilme();
            else if ( secim == 4) filmGoruntule();
            else if ( secim == 5) kullaniciGoruntule();
            else if ( secim == 6) mainMenu();
            else if ( secim == 7) System.out.println(BOLD+"\nÇıkış yaptnızı.");
            else {
                System.out.println(BOLD+c_GREEN+"\nHatalı Giriş. Yeniden Deneyiniz."+c_RESET);
                adminMenu();
            }
        }catch (Exception e) {
            System.out.println(BOLD+c_GREEN+"\n *** Hatalı giriş *** \n"+c_RESET);
            scan.nextLine();
            adminMenu();
        }


    }
    public void filmEkleme() throws InterruptedException {
        System.out.println();
        try {

            System.out.print("Eklemek istediğiniz film adını giriniz : ");
            String ad = scan.nextLine();
            ad = ad.toUpperCase().replaceAll(" ","");
            filmListe.add(ad);
            System.out.println(ad + " filmi başarıyla eklendi.");
            adminMenu();

        }catch (Exception e) {
            System.out.println(BOLD+c_GREEN+"\n *** Hatalı giriş *** \n"+c_RESET);
            scan.nextLine();
            adminMenu();
        }

    }
    public void filmSilme() throws InterruptedException {
        System.out.println();
        try {

            System.out.print("Silmek istediğiniz film adını giriniz : ");
            String ad = scan.nextLine();
            ad = ad.toUpperCase().replaceAll(" ","");
            if (filmListe.contains(ad)) {
                System.out.println(ad + " filmi başarıyla silindi.");
                filmListe.remove(ad);
                adminMenu();
            }
            else {
                System.out.println(ad+" filmi zaten kayıtlı değildir.");
                adminMenu();
            }

        }catch (Exception e) {
            System.out.println(BOLD+c_GREEN+"\n *** Hatalı giriş *** \n"+c_RESET);
            scan.nextLine();
            adminMenu();
        }


    }
    public void filmGoruntule() throws InterruptedException {
        System.out.println("\n Film listesi aşağıdadır.\n");
        for (int i = 0; i < filmListe.size(); i++) {
            System.out.println(filmListe.get(i));
        }
        adminMenu();


    }
    public void kullaniciGoruntule() throws InterruptedException {
        System.out.println("\n Kullanıcı listesi aşağıdadır.\n");
        for (int i = 0; i < userList.size(); i++) {
            System.out.println(userList.get(i));
        }
        adminMenu();

    }
    public void userMenu() throws InterruptedException {
        System.out.println(BOLD+c_BLUE+"\n *** KULLANICI MENÜSÜ ***\n"+c_RESET);
        try {
            System.out.println("Oyuna geçmek için - 1");
            System.out.println("Ana menüye geçmek için - 2");
            System.out.println("Çıkış için - 3");
            System.out.print("Seçiniz : ");
            int secim = scan.nextInt();

            if (secim == 1){
                Thread.sleep(2000);
                filmTahminEtme();
            }
            else if ( secim == 2 ) mainMenu();
            else if (secim == 3)  System.out.println("\nÇıkış Yaptınız.");
            else {
                System.out.println(BOLD+c_GREEN+"\nHatalı Giriş. Tekrar Deneyiniz."+c_RESET);
                userMenu();
            }

        }catch (Exception e) {
            System.out.println(BOLD+c_GREEN+"\n *** Hatalı giriş *** \n"+c_RESET);
            scan.nextLine();
            userMenu();
        }


    }
    public void mainMenu() throws InterruptedException {
        System.out.println(BOLD+c_PURPLE+"\n *** ANA MENÜ *** "+c_RESET);

        try {

            System.out.println("Admin girişi için - 1");
            System.out.println("Kullanıcı girişi için - 2");
            System.out.println("Çıkış için - 3");
            System.out.print("Seçiniz : ");


            int secim = scan.nextInt();
            if ( secim == 1) {
                System.out.print("\nAdmin kullanıcı adını giriniz : ");
                String admin = scan.next();

                System.out.print("Şifreni giriniz : ");
                String sifre = scan.next();
                if (admin.equals(adminUser) && sifre.equals(adminPass)) {
                    System.out.println(c_RED+"\nAdmin Menüsüne Yönlendiriliyorsunuz."+c_RESET);
                    Thread.sleep(2000);
                    adminMenu();
                }
                else {
                    System.out.println("\nŞifreniz ya da kullanıcı adınız hatalı. Tekrar Deneyin ");
                    mainMenu();
                }

            }
            else if ( secim == 2) {
                System.out.print("\nKullanıcı adını giriniz : ");
                String kullanici = scan.next().toLowerCase().trim();

                System.out.print("Şifreni giriniz : ");
                String sifre = scan.next();
                int indexOfSifre = userList.indexOf(kullanici);

                if (userList.contains(kullanici) && sifre.equals(passwordList.get(indexOfSifre))) {
                    System.out.println(c_RED+"\nKullanıcı Menüsüne Yönlendiriliyorsunuz."+c_RESET);
                    Thread.sleep(2000);
                    userMenu();
                }
                else {
                    System.out.println("\nŞifreniz ya da kullanıcı adınız hatalı. Tekrar Deneyin ");
                    mainMenu();
                }

            }
            else if ( secim == 3) {
                System.out.println("\nÇıkış Yaptınız.");
            }
            else {
                System.out.println(BOLD+c_GREEN+"\nHatalı Giriş. Tekrar Deneyiniz."+c_RESET);
                mainMenu();
            }
        }catch (Exception e) {
            System.out.println(BOLD+c_GREEN+"\n *** Hatalı giriş *** "+c_RESET);
            scan.nextLine();
            mainMenu();

        }


    }


    public Filmler(String userName, String userPass) {
        this.userName = userName;
        this.userPass = userPass;
    }

    public Filmler() {
    }

    public String getAdminUser() {
        return adminUser;
    }


    public String getAdminPass() {
        return adminPass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }


}
