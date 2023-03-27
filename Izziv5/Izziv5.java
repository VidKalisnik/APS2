import java.util.ArrayList;
import java.util.Scanner;

class Izziv5 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // Naredimo array
    ArrayList<Integer> ar = new ArrayList<>();

    // Dodamo števila
    while (sc.hasNextInt()) {
      int num = sc.nextInt();
      ar.add(num);
    }
    //

    // Naredimo navadn array
    int[] arr = new int[ar.size()];
    for (int i = 0; i < ar.size(); i++) {
      arr[i] = ar.get(i);
    }
    //

    //int[] arr = { 2, 3, 1, -1, 4, -5, 3 };

    fun(arr, 0, arr.length - 1);

    sc.close();
  }

  public static int fun(int[] arr, int a, int b) {
    if (a == b) {
      System.out.println("[" + arr[a] + "]: " + arr[a]);
      return arr[a];
    } else {
      //sredina arraya
      int mid = (a + b) / 2;
      //rekurzivno levo in desno stran
      int leva = fun(arr, a, mid);
      int desna = fun(arr, mid + 1, b);

      //leva stran
      int maxtmp = arr[mid] + arr[mid + 1];
      int tmp = maxtmp;
      for (int i = mid - 1; i >= a; i--) {
        tmp += arr[i];
        if (tmp > maxtmp) maxtmp = tmp;
      }

      //desna stran
      tmp = maxtmp;

      for (int i = mid + 2; i <= b; i++) {
        tmp += arr[i];
        if (tmp > maxtmp) maxtmp = tmp;
      }

      System.out.print("[");
      for (int i = a; i < b; i++) {
        System.out.print(arr[i] + ", ");
      }
      System.out.println(arr[b] + "]: " + max(leva, desna, maxtmp));

      return max(leva, desna, maxtmp);
    }
  }

  public static int max(int a, int b, int c) {
    if (a >= b && a >= c) {
      return a;
    } else if (b >= a && b >= c) {
      return b;
    } else if (c >= a && c >= b) {
      return c;
    }

    return -1;
  }
}
