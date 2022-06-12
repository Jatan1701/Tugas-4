import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception{
        ArrayList<Nasabah> nasabah = new ArrayList<Nasabah>();
        ArrayList<String> logMutasi = new ArrayList<String>();


        Scanner input = new Scanner (System.in);
        init(nasabah);
        String yn="y";
        do{

            menu();

            int pilihan =0;
            pilihan = input.nextInt();

            if(pilihan==1){
                String NoRek;
                String Namansb;
                long Saldo=0;
                System.out.print("Nama Nasabah \t:\t");
                Namansb =input.next();
                System.out.print("Nomor Rekening \t:\t");
                NoRek = input.next();
                System.out.print("Saldo Awal \t:\t");
                Saldo = input.nextLong();

                nasabah.add(new Nasabah(NoRek, Namansb, Saldo));

            }
            else if(pilihan==2){
                //setor Uang
                String NoRek;
                long setoran=0;
                System.out.print("Nomor Rekening \t:\t");
                NoRek= input.next();
                System.out.print("Nominal setoran (IDR) \t:\t");
                setoran= input.nextLong();

                int i=0;
                for (Nasabah nasabah2 : nasabah){
                    if(nasabah2.getNoRek().equals(NoRek)){
                        Nasabah tmpnsb = nasabah2;
                        tmpnsb.setSaldo(tmpnsb.getSaldo() + setoran);
                        nasabah.set(i, tmpnsb);

                        System.out.println("Setoran telah berhasil");
                        logMutasi.add("Setor uang" + setoran + "Ke Rekening" + tmpnsb.getNoRek() + " " + tmpnsb.getNamansb());
                    }
                    i++;

                }
            

            }
        
            else if(pilihan==3){
                for (String string : logMutasi) {
                    System.out.println(string);
                }
            }
            else if(pilihan==4){
                String NoRek3;
                String NoRek4;
                long tf=0;
                System.out.print ("Masukkan Nomor Rekening Anda \t:\t");
                NoRek3 = input.next();
                for (Nasabah nasabah3 : nasabah) 
                {
                    if(nasabah3.getNoRek().equals(NoRek3))
                    {
                        System.out.println("Nomor rekening Atas Nama " + nasabah3.getNamansb());
                        System.out.print ("Masukkan Nomor Rekening yang ingin di \t:\t");
                        NoRek4 = input.next();
                        int i=0;
                        for (Nasabah nasabah4 : nasabah) 
                        {
                            if(nasabah4.getNoRek().equals(NoRek4))
                            {
                                System.out.println("Ke Rekening "+ nasabah4.getNamansb());
                                System.out.print ("Jumlah Uang (IDR)\t:\t");
                                tf = input.nextLong();

                                if(tf<nasabah3.getSaldo() & (nasabah3.getSaldo())<=0)
                                {
                                    Nasabah tmpnsb2 = nasabah3;
                                    tmpnsb2.setSaldo(tmpnsb2.getSaldo() - tf);
                                    Nasabah tmpnsb3 = nasabah4;
                                    tmpnsb3.setSaldo(tmpnsb3.getSaldo() + tf);
                                    nasabah.set(i, tmpnsb2);
                                    logMutasi.add("Transfer " + tf + " ke rekening " + tmpnsb3.getNoRek() + " AN " + tmpnsb3.getNamansb());
                                    System.out.println("\n<TRANSAKSI BERHASIL>");
                                    System.out.println("Saldo Tersisah " + tmpnsb2.getSaldo());
                                }
                                else
                                {
                                    System.out.println("\n<TRANSAKSI GAGAL>");
                                    System.out.println("Saldo tidak mencukupi...");  
                                }
                            }
                        }
                        i++;
                    }
                    else { System.out.println("Rekening Belum Terdaftar"); break;}
                }

            }
            else if(pilihan==5){
                cetakNamaNasabah(nasabah);
            }

            else if(pilihan==6){ 
                break;
            }
            else { continue;}

            System.out.print("Apakah anda ingin kembali ke menu utama (y/n) :");
            yn = input.next();
        }while(yn.equalsIgnoreCase("y"));

    }   
    
    public static ArrayList<Nasabah> init(ArrayList<Nasabah> nasabah){
        Nasabah nsb1 = new Nasabah("0876543", "Jian", 5000000);
        nasabah.add(nsb1);
        Nasabah nsb2 = new Nasabah("0876743", "Gilbert", 10000000);
        nasabah.add(nsb2);
        Nasabah nsb3 = new Nasabah("0855543", "Kimberlly", 7000000);
        nasabah.add(nsb3);
        Nasabah nsb4 = new Nasabah("0855599", "Brian", 4000000);
        nasabah.add(nsb4);
        nasabah.add(new Nasabah("0855543", "Willbert", 16000000));
        cetakNamaNasabah(nasabah);
        return (nasabah);
        
    }
    public static void menu(){
        System.out.println("Aplikasi Banking");
        System.out.println("1. Tambah Nasabah Baru");
        System.out.println("2. Setor Uang");
        System.out.println("3. Cetak Mutasi");
        System.out.println("4. Transfer Uang");
        System.out.println("5. Cetak Nama nasabah");
        System.out.println("6. Keluar");
        System.out.print("Masukkan pilihan anda :");
    }

    public static void hapusNasabah(ArrayList<Nasabah> nasabah, int idx){
        nasabah.remove(idx);
    }

    public static void hapusNasabah(ArrayList<Nasabah> nasabah, Nasabah nsb){
        nasabah.remove(nsb);
    }
    
    public static void cetakNamaNasabah(ArrayList<Nasabah> nasabah){
        System.out.println("No.Rekening\tNama\t\tSaldo\tNo.kartu\tPIN\tTgl. Daftar");
        System.out.println("----------------------------------------------------");
        for (Nasabah nsbh : nasabah){
            System.out.println(nsbh);
        }
        System.out.println("------------------------------------------------------------");
    }
}
