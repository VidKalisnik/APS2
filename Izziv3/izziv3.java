import java.util.Scanner;

class izziv3 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // preberemo velikost tabel
    int n = sc.nextInt();
    int m = sc.nextInt();

    // naredimo tabelo
    int[] a = new int[n];
    int[] b = new int[m];

    // shranimo vrednosti v tabeli
    for (int i = 0; i < n; i++) {
      a[i] = sc.nextInt();
    }
    for (int i = 0; i < m; i++) {
      b[i] = sc.nextInt();
    }

    // nova tabela
    int[] tabela = new int[n + m];

    // zlivanje tabel v novo tabelo

    int ia = 0; // index za tabelo a
    int ib = 0; // index za tabelo b
    for (int i = 0; i < n + m; i++) {
      if (ib >= m) { // ce ni vec el. v tabeli b
        System.out.print("a");
        tabela[i] = a[ia];
        ia++;
      } else if (ia >= n) { // ce ni vec el. v tabeli a
        System.out.print("b");
        tabela[i] = b[ib];
        ib++;
      } else if (a[ia] <= b[ib]) { // ce je el. v a manjsi od b
        System.out.print("a");
        tabela[i] = a[ia];
        ia++;
      } else if (a[ia] > b[ib]) { // ce je el. v b manjsi od a
        System.out.print("b");
        tabela[i] = b[ib];
        ib++;
      }
    }

    System.out.println();

    for (int i = 0; i < n + m; i++) {
      System.out.print(tabela[i] + " ");
    }
  }
}
