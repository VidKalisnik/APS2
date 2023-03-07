import java.util.Random;

public class izziv1 {

  public static int[] generateTable(int n) {
    int[] table = new int[n];

    for (int i = 1; i <= n; i++) {
      table[i - 1] = i;
    }

    return table;
  }

  public static int findLinear(int[] a, int v) {
    for (int i = 0; i < a.length; i++) {
      if (a[i] == v) {
        return i;
      }
    }

    return -1;
  }

  public static int findBinary(int[] a, int l, int r, int v) {
    while (l <= r) {
      int mid = l + (r - l) / 2;
      if (a[mid] == v) {
        return mid;
      } else if (a[mid] < v) {
        l = mid + 1;
      } else {
        r = mid - 1;
      }
    }

    return -1;
  }

  public static long timeLinear(int n) {
    Random rand = new Random();
    int[] table = generateTable(n);

    long startTime = System.nanoTime();

    for (int i = 0; i < 1000; i++) {
      int randomNum = rand.nextInt(n);
      findLinear(table, randomNum);
    }

    long executionTime = System.nanoTime() - startTime;

    return executionTime / 1000;
  }

  public static long timeBinary(int n) {
    Random rand = new Random();
    int[] table = generateTable(n);

    long startTime = System.nanoTime();

    for (int i = 0; i < 1000; i++) {
      int randomNum = rand.nextInt(n);
      findBinary(table, 0, n - 1, randomNum);
    }

    long executionTime = System.nanoTime() - startTime;

    return executionTime / 1000;
  }

  public static void main(String[] args) {
    System.out.println("   n     |    linearno  |   dvojisko   |");
    System.out.println("---------+--------------+---------------");

    for (int i = 1000; i <= 100000; i += 1000) {
      long l = timeLinear(i);
      long b = timeBinary(i);
      System.out.printf("%8d | %12d | %13d\n", i, l, b);
    }
  }
}
/*
 * Moje meritve:
 *
   n     |    linearno  |   dvojisko   |
---------+--------------+---------------
    1000 |         6396 |           809
    2000 |         5098 |           286
    3000 |          784 |           465
    4000 |          977 |           174
    5000 |         1333 |           182
    6000 |         1442 |           164
    7000 |         1815 |           315
    8000 |         2409 |           234
    9000 |         2035 |           172
   10000 |         2674 |           174
   11000 |         2683 |           223
   12000 |         4777 |           701
   13000 |         3575 |           241
   14000 |         3469 |           363
   15000 |         5063 |           202
   16000 |         4766 |           247
   17000 |         4200 |           168
   18000 |         6477 |           234
   19000 |         4878 |           168
   20000 |         6273 |           264
   21000 |         5041 |           223
   22000 |         5294 |           264
   23000 |         6798 |           192
   24000 |         5579 |           171
   25000 |         8150 |           302
   26000 |         7272 |           252
   27000 |         7151 |           204
   28000 |         6149 |           177
   29000 |         9911 |           217
   30000 |         7931 |           274
   31000 |         8162 |           274
   32000 |        10417 |           302
   33000 |         8530 |           202
   34000 |        10170 |           189
   35000 |        11843 |           261
   36000 |         9160 |           290
   37000 |        11599 |           215
   38000 |        10800 |           204
   39000 |        33613 |           274
   40000 |        17333 |           275
   41000 |        19804 |           275
   42000 |        29007 |           262
   43000 |        16216 |           200
   44000 |        16837 |           232
   45000 |        16194 |           247
   46000 |        15969 |           215
   47000 |        19508 |           265
   48000 |        14510 |           201
   49000 |        15657 |           283
   50000 |        42474 |           276
   51000 |        19735 |           201
   52000 |        16503 |           218
   53000 |        25290 |           328
   54000 |        14746 |           225
   55000 |        17237 |           318
   56000 |        19825 |           294
   57000 |        19570 |           275
   58000 |        20606 |           220
   59000 |        19922 |           217
   60000 |        24691 |           290
   61000 |        26978 |           380
   62000 |        35000 |           309
   63000 |        21337 |           205
   64000 |        18108 |           141
   65000 |        22588 |           143
   66000 |        18431 |           130
   67000 |        17495 |           157
   68000 |        18861 |           147
   69000 |        17452 |           200
   70000 |        17741 |           157
   71000 |        17661 |           150
   72000 |        19918 |           165
   73000 |        21067 |           238
   74000 |        19311 |           135
   75000 |        27229 |           171
   76000 |        20960 |           137
   77000 |        18744 |           153
   78000 |        21195 |           145
   79000 |        19434 |           137
   80000 |        23078 |           196
   81000 |        19795 |           161
   82000 |        19857 |           158
   83000 |        20449 |           157
   84000 |        23435 |           155
   85000 |        21550 |           153
   86000 |        23718 |           178
   87000 |        25495 |           165
   88000 |        22500 |           155
   89000 |        24020 |           143
   90000 |        20725 |           336
   91000 |        25396 |           164
   92000 |        22023 |           156
   93000 |        24625 |           145
   94000 |        24783 |           161
   95000 |        23450 |           152
   96000 |        26846 |           180
   97000 |        28513 |           192
   98000 |        23973 |           142
   99000 |        25448 |           141
  100000 |        23535 |           157
 */
