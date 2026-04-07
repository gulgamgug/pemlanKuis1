import java.util.Scanner;

abstract class Perawatan {
    String nama, layanan, kelas;
    int berat, hargaPerKg;

    public Perawatan(String nama, int berat, String kelas) {
        this.nama = nama;
        this.berat = berat;
        this.kelas = kelas;
    }

    public abstract void setLayananDanHarga();

    public void prosesNota() {
        setLayananDanHarga();
        double biayaDasar = this.berat * this.hargaPerKg;
        double diskon = (this.berat > 30) ? (biayaDasar * 0.1) : 0;
        double tambahanVIP = this.kelas.equals("vip") ? (biayaDasar * 0.2) : 0;
        double subtotal = biayaDasar - diskon + tambahanVIP;
        double pajak = subtotal * 0.08;
        double totalBiaya = subtotal + pajak;

        boolean sapiSpesial = this.nama.equals("Moo") || this.nama.equals("Mooo") || this.nama.equals("Moooo");
        if (sapiSpesial) {
            totalBiaya = 0;
        }

        System.out.println("============= NOTA KLINIK SAPI =============");
        System.out.println("Nama Sapi: " + this.nama);
        System.out.println("Berat: " + this.berat + " kg");
        System.out.println("Jenis Layanan: " + this.layanan);
        System.out.println("Kelas: " + this.kelas);
        System.out.println("Biaya Dasar: Rp " + biayaDasar);
        System.out.println("Diskon: Rp " + diskon);
        System.out.println("Biaya Tambahan VIP: Rp " + tambahanVIP);
        System.out.println("Subtotal: Rp " + subtotal);
        System.out.println("Pajak: Rp " + pajak);
        System.out.println("Total Biaya: Rp " + totalBiaya);
        System.out.println("============================================");

        if (sapiSpesial) {
            System.out.println("Terima kasih, " + this.nama + " ! Sapi spesial memang beda perlakuan~");
        } else {
            System.out.println("Terima kasih, " + this.nama + " ! Semoga sapinya makin glow up.");
        }
    }
}

class Spa extends Perawatan {
    public Spa(String nama, int berat, String kelas) {
        super(nama, berat, kelas);
    }

    @Override
    public void setLayananDanHarga() {
        this.layanan = "spa";
        this.hargaPerKg = 8000;
    }
}

class PotongKuku extends Perawatan {
    public PotongKuku(String nama, int berat, String kelas) {
        super(nama, berat, kelas);
    }

    @Override
    public void setLayananDanHarga() {
        this.layanan = "potong_kuku";
        this.hargaPerKg = 6000;
    }
}

class Grooming extends Perawatan {
    public Grooming(String nama, int berat, String kelas) {
        super(nama, berat, kelas);
    }

    @Override
    public void setLayananDanHarga() {
        this.layanan = "grooming";
        this.hargaPerKg = 10000;
    }
}

public class Sapi {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String nama, kelas, layanan;
        int berat = 0;

        while (true) {
            nama = input.nextLine().trim();
            if (nama.matches("^[a-zA-Z]+$")) {
                break;
            }
            System.out.println("Mooo! Nama sapi harus pakai huruf, bukan angka atau simbol!");
        }

        while (true) {
            String inputBerat = input.nextLine().trim();
            try {
                berat = Integer.parseInt(inputBerat);
                if (berat >= 1 && berat <= 80) {
                    break;
                }
                System.out.println("Sapi astral? Masukkan berat yang valid dulu, bestie!");
            } catch (Exception e) {
                System.out.println("Sapi astral? Masukkan berat yang valid dulu, bestie!");
            }
        }

        while (true) {
            layanan = input.nextLine().trim();
            if (layanan.equals("spa") || layanan.equals("potong_kuku") || layanan.equals("grooming")) {
                break;
            }
            System.out.println("Pilih spa, potong_kuku, atau grooming! Sapi kamu mau dirawat apa, sih?");
        }

        while (true) {
            kelas = input.nextLine().trim();
            if (kelas.equals("reguler") || kelas.equals("vip")) {
                break;
            }
            System.out.println("Pilih reguler atau vip! Sapi kamu mau treatment sultan atau biasa aja?");
        }

        Perawatan p = null;
        if (layanan.equals("spa")) {
            p = new Spa(nama, berat, kelas);
        } else if (layanan.equals("potong_kuku")) {
            p = new PotongKuku(nama, berat, kelas);
        } else if (layanan.equals("grooming")) {
            p = new Grooming(nama, berat, kelas);
        }

        if (p != null) {
            p.prosesNota();
        }

        input.close();
    }
}