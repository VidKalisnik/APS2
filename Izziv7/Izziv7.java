import java.util.Scanner;

public class Izziv7 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int p = 4;
    while (true) {
      p = naslednjePrastevilo(p);
      int[] arr = PKE(p, n);
      if (arr == null) {
        continue;
      } else {
        System.out.print(p + ": ");
        for (var i = 0; i < arr.length; i++) {
          if (arr[i] == 0) {
            break;
          }
          System.out.print(arr[i] + " ");
        }
        System.out.println();
        VandermondMatrix(p, n, arr[0]);
        break;
      }
    }
  }

  public static void VandermondMatrix(int p, int n, int pke) {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        int tmp = 1;
        for (int k = 0; k < i * j; k++) {
          tmp = tmp * pke;
          tmp = tmp % p;
        }
        if (tmp > 10) {
          System.out.print(" " + tmp);
        } else {
          if (j == 0) {
            System.out.print(" " + tmp);
          } else {
            System.out.print("  " + tmp);
          }
        }
      }
      System.out.println();
    }
  }

  public static int[] PKE(int p, int n) {
    boolean exists = false;
    int[] arr = new int[p - 2];
    int j = 0;
    for (int i = 2; i < p; i++) {
      long tmp = i;
      int count = 1;
      while (tmp != 1) {
        tmp = (tmp * i) % p;
        count++;
      }
      if (count == n) {
        exists = true;
        arr[j] = i;
        j++;
      }
    }
    if (!exists) {
      return null;
    }
    return arr;
  }

  public static int naslednjePrastevilo(int p) {
    boolean found = false;
    while (!found) {
      p++;
      for (int i = 2; i <= p / 2; i++) {
        if (p % i == 0) {
          break;
        }
        if (i == p / 2) {
          found = true;
        }
      }
    }
    return p;
  }
}
