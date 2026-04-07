import java.util.ArrayList;
import java.util.Scanner;

abstract class Vehicle {
    String kode, nama, jenis;
    int harga;
    boolean tersedia;

    public Vehicle(String kode, String nama, int harga) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.tersedia = true;
    }

    public void detail() {
        String statusStr = this.tersedia ? "TERSEDIA" : "DISEWA"; 
        System.out.printf("%s | %s | %s | harga: %d | status: %s\n", this.kode, this.jenis, this.nama, this.harga, statusStr);
    }
    
    void onCreateSuccess() {
        System.out.println(this.jenis + " " + this.kode + " berhasil ditambahkan");
    }

    public abstract int hitungSewa(int hari, boolean isPromo);

    public void rent(int hari, boolean isPromo) {
        if (!this.tersedia) {
            System.out.println("Kendaraan sedang disewa");
        } else {
            this.tersedia = false;
            System.out.printf("Total sewa %s: %.0f\n", this.kode, (double)hitungSewa(hari, isPromo));
        }
    }

    public void doReturn() {
        if (this.tersedia) {
            System.out.println("Kendaraan belum disewa");
        } else {
            this.tersedia = true;
            System.out.println(this.kode + " berhasil dikembalikan");
        }
    }
}


class Car extends Vehicle {
    public Car(String kode, String nama, int harga) {
        super(kode, nama, harga);
        this.jenis = "CAR";
        onCreateSuccess();
    }

    @Override
    public int hitungSewa(int hari, boolean isPromo) {
        int total = this.harga * hari;
        if (isPromo) {
            total -= 20000;
        }
        return Math.max(0, total); 
    }
}

class Bike extends Vehicle {
    public Bike(String kode, String nama, int harga) {
        super(kode, nama, harga);
        this.jenis = "BIKE";
        onCreateSuccess();
    }

    @Override
    public int hitungSewa(int hari, boolean isPromo) {
        int total = this.harga * hari;
        if (isPromo) {
            total -= 10000;
        }
        return Math.max(0, total);
    }
}

public class Siswa {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        if (!input.hasNextLine()) return;
        
        int n = input.nextInt();
        ArrayList<Vehicle> listKendaraan = new ArrayList<>();

        for (int i=0; i<n; i++) {
            String perintah = input.next();

            if (perintah.equals("ADD")) {
                String tipe = input.next();
                String kode = input.next();
                String nama = input.next();
                int harga = input.nextInt();

                Vehicle vDitemukan = null;
                for (Vehicle v : listKendaraan) {
                    if (v.kode.equals(kode)) {
                        vDitemukan = v;
                        break;
                    }
                }

                if (vDitemukan != null) {
                    System.out.println("Kendaraan sudah terdaftar");
                } else {
                    if (tipe.equals("CAR")) {
                        listKendaraan.add(new Car(kode, nama, harga));
                    } else if (tipe.equals("BIKE")) {
                        listKendaraan.add(new Bike(kode, nama, harga));
                    }
                }
                
            } else if (perintah.equals("RENT")) {
                String kode = input.next();
                int hari = input.nextInt();
                boolean isPromo = false;
                
                String sisaPromoSialan = input.nextLine().trim();
                if (sisaPromoSialan.equals("PROMO")) {
                    isPromo = true;
                }

                Vehicle target = null;
                for (Vehicle v : listKendaraan) {
                    if (v.kode.equals(kode)) {
                        target = v;
                        break;
                    }
                }

                if (target == null) {
                    System.out.println("Kendaraan tidak ditemukan");
                } else {
                    target.rent(hari, isPromo);
                }
            } else if (perintah.equals("RETURN")) {
                String kode = input.next();

                Vehicle target = null;
                for (Vehicle v : listKendaraan) {
                    if (v.kode.equals(kode)) {
                        target = v;
                        break;
                    }
                }
                if (target == null) {
                    System.out.println("Kendaraan tidak ditemukan");
                } else {
                    target.doReturn();
                }
            } else if (perintah.equals("DETAIL")) {
                String kode = input.next();

                Vehicle target = null;
                for (Vehicle v : listKendaraan) {
                    if (v.kode.equals(kode)) {
                        target = v;
                        break;
                    }
                }
                if (target == null) {
                    System.out.println("Kendaraan tidak ditemukan");
                } else {
                    target.detail();
                    }
                
            } else if (perintah.equals("COUNT")) {
                System.out.println("Total kendaraan: " + listKendaraan.size());
            }
        }
        input.close();
    }
}