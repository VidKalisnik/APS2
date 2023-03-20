import java.util.Scanner;

class izziv4 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // Prebere stevila
    int n = sc.nextInt();

    int[] tabela = new int[n];

    int maxSt = 0;
    for (int i = 0; i < n; i++) {
      tabela[i] = sc.nextInt();
      if (maxSt < tabela[i]) maxSt = tabela[i];
    }
    //

    // Najvecja dolzina dvojiskega niza
    int k = 1;
    int d = 0;
    while (maxSt > k) {
      d++;
      k = (int) Math.pow(2, d);
    }
    //

    // Tabela pojavitev
    int[] c = new int[d + 1];
    for (int i = 0; i < c.length; i++) {
      c[i] = 0;
    }
    //

    // Zapolnimo tabelo pojavitev
    for (int i = 0; i < n; i++) {
      c[decToNumOfOnes(tabela[i])]++;
    }
    //

    // Posodobimo tabelo pojavitev
    for (int i = 1; i < c.length; i++) {
      c[i] += c[i - 1];
    }
    //

    // Nova urejena tabela
    int[] uTabela = new int[n];

    // Doda el. v urejeno tabelo
    for (int i = n - 1; i >= 0; i--) {
      c[decToNumOfOnes(tabela[i])]--;
      uTabela[c[decToNumOfOnes(tabela[i])]] = tabela[i];
      System.out.println(
        "(" + tabela[i] + "," + c[decToNumOfOnes(tabela[i])] + ")"
      );
    }

    // Sprinta tabelo
    for (int i = 0; i < n; i++) {
      System.out.print(uTabela[i] + " ");
    }
  }

  public static int decToNumOfOnes(int n) {
    int count = 0;
    while (n > 0) {
      // pogleda ce je zadn bit 1 ali 0 in pol pristeje
      count += n & 1;
      // right shift ... npr. 11010101 -> 01101010
      n >>= 1;
    }

    return count;
  }
}
