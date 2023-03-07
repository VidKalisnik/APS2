import java.lang.Math;
import java.util.Scanner;

class izziv2 {

  static void pogrezni(int a[], int i, int dolzKopice) {
    // ce indeksa i sploh ni v tabeli
    if (i >= dolzKopice) {
      return;
    }

    int oce = a[i];

    // ce nima levega sina
    if ((2 * i + 1) >= dolzKopice) {
      return;
    }

    // ce nima desnega sina
    if ((2 * i + 2) >= dolzKopice) {
      //ce je sin vecji od oceta, ju zamenjamo
      if (a[2 * i + 1] > oce) {
        a[i] = a[2 * i + 1];
        a[2 * i + 1] = oce;
      }
    } else {
      // ce ima oba sinova
      // zamenjamo oceta s vecjim sinom

      // levi sin je vecji oz. sta enaka
      if (a[2 * i + 1] >= a[2 * i + 2] && a[2 * i + 1] > oce) {
        a[i] = a[2 * i + 1];
        a[2 * i + 1] = oce;
      } else if (a[2 * i + 2] > oce) {
        // desni sin je vecji
        a[i] = a[2 * i + 2];
        a[2 * i + 2] = oce;
      }
    }
  }

  static void pogrezniRek(int[] a, int i, int dolzKopice) {
    // nima vec sinov
    if ((2 * i + 1) >= dolzKopice) {
      return;
    }
    // pogrezne i-tega
    pogrezni(a, i, dolzKopice);

    // pogrezne levega sina
    pogrezniRek(a, 2 * i + 1, dolzKopice);

    // preveri ce ima desnega sina
    if ((2 * i + 2) >= dolzKopice) {
      return;
    }

    // pogrezne desnega sina
    pogrezniRek(a, 2 * i + 2, dolzKopice);
  }

  static void kop(int[] a, int dolzKopice) {
    // gre cez vse el.
    for (int i = dolzKopice - 1; i >= 0; i--) {
      // ne gre v rek. v listih
      if (dolzKopice < 2 * i + 1) {
        continue;
      }
      pogrezniRek(a, i, dolzKopice);
    }
  }

  static void izpis(int[] a, int dolzKopice) {
    for (int i = 0; i < a.length; i++) {
      // urejenaje kopice
      kop(a, dolzKopice);

      // izpis kopice
      int tmp = 1;
      for (int j = 1; j <= dolzKopice; j++) {
        System.out.print(a[j - 1] + " ");
        if (Math.pow(2, tmp) - 1 == j && j != dolzKopice) {
          System.out.print("| ");
          tmp++;
        }
      }
      System.out.println();

      // zamenjamo najvecjo vrednost s najnizjim levim listom
      int oce = a[0];
      a[0] = a[dolzKopice - 1];
      a[dolzKopice - 1] = oce;

      dolzKopice--;
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // prebere dolzino tabele
    int num = sc.nextInt();

    // naredimo tabelo
    int[] arr = new int[num];

    // gre v novo vrstico
    sc.nextLine();

    // prebere vrednosti in jih sharni v tabelo
    String line = sc.nextLine();
    String[] splitline = line.split(" ");
    for (int i = 0; i < num; i++) {
      arr[i] = Integer.parseInt(splitline[i]);
    }

    izpis(arr, arr.length);
  }
}
