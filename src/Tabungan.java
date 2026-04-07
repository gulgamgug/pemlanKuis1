import java.util.ArrayList;
import java.util.Scanner;

abstract class Student {
    String nama, tipe;
    double saldo;

    public Student(String nama) {
        this.nama = nama;
        this.saldo = 0;
    }

    public void save(int jumlah) {
        this.saldo += jumlah;
        onTransactionSuccess();
    }

    public void check() {
        System.out.printf("%s | %s | saldo: %d", this.nama, this.tipe, this.saldo);
    }

    public void onCreateSuccess() {
        System.out.printf("%s %s berhasil dibuat", this.tipe, this.nama);
    }

    public void onTransactionSuccess() {
        System.out.printf("Saldo %s: %d", this.nama, this.saldo);
    }

    public abstract boolean take(int jumlah);
}

class Reguler extends Student {
    public Reguler(String nama) {
        super(nama);
        this.tipe = "Reguler";
        onCreateSuccess();
    }

    @Override
    public boolean take(int jumlah) {
        if (this.saldo >= jumlah) {
            this.saldo -= jumlah;
            onTransactionSuccess();
            return true;
        } else {
            System.out.printf("Saldo %s tidak cukup", this.nama);
            return false;
        }
    }
}

class Beasiswa extends Student {
    public Beasiswa(String nama) {
        super(nama);
        this.tipe = "Beasiswa";
    }
    
    @Override
    public boolean take(int jumlah) {
        int potongan = 1000;
        int netSaldo = jumlah - potongan;
        if (this.saldo >= netSaldo) {
            this.saldo -= netSaldo;
            onTransactionSuccess();
            return true;
        } else {
            System.out.printf("Saldo %s tidak cukup", this.nama);
            return false;
        }
    }
}

public class Tabungan {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        if (!input.hasNextInt()) return;

        int n = input.nextInt();
        ArrayList<Student> listSiswa = new ArrayList<>();

        for (int i=0; i<n, i++) {
            String perintah = input.next();
            if (perintah.equals("CREATE")) {
                String tipe = input.next();
                String nama = input.next();

                Student studentSdhDibuat = null;
                for (Student s : listSiswa) {
                    if (s.nama.equals(nama)) {
                        studentSdhDibuat = s;
                        break;
                    }
                }

                if (studentSdhDibuat != null) {
                    System.out.println("Akun sudah terdaftar");
                } else {
                    if (tipe.equals("REGULER")) {
                        listSiswa.add(new Reguler(nama));
                    } else if (tipe.equals("BEASISWA")) {
                        listSiswa.add(new Beasiswa(nama));
                    }
                    System.out.printf("%s %s berhasil dibuat", tipe, nama);
                }
            } else if (perintah.equals("SAVE")) {
                String nama = input.next();
                int jumlah = input.nextInt();

                Student target = null;
                for (Student s : listSiswa) {
                    if (s.nama.equals(nama)) {
                        target = s;
                        break;
                    }
                }

                if (target == null) {
                    System.out.println("Akun tidak ditemukan");
                } else {
                    target.save(jumlah);
                }
            } else if (perintah.equals("TAKE")) {
                String nama = input.next();
                int jumlah = input.nextInt();
                
                Student target = null;
                for (Student s : listSiswa) {
                    if (s.nama.equals(nama)) {
                        target = s;
                        break;
                    }
                }

                if (target == null) {
                    System.out.println("Akun tidak ditemukan");
                } else {
                    if (target.take(jumlah)) {
                        System.out.println("Saldo " + nama + ": " + target.saldo);
                    } else {
                        System.out.println("Saldo " + nama + " tidak cukup");
                    }
                }
            } else if (perintah.equals("CHECK")) {
                String nama = input.next();
                Student target = null;

                for (Student s : listSiswa) {
                    if (s.nama.equals(nama)) {
                        target = s;
                        break;
                    }
                }

                if (target != null) {
                    target.check();
                }
            }
        }
    }
}
