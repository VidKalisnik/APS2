import java.util.Scanner;

class Matrix {

  private int[][] m;

  public int n; //only square matrices

  public Matrix(int n) {
    this.n = n;

    m = new int[n][n];
  }

  //set value at i,j
  public void setV(int i, int j, int val) {
    m[i][j] = val;
  }

  // get value at index i,j
  public int v(int i, int j) {
    return m[i][j];
  }

  // return a square submatrix from this
  public Matrix getSubmatrix(int startRow, int startCol, int dim) {
    Matrix subM = new Matrix(dim);

    for (int i = 0; i < dim; i++) for (int j = 0; j < dim; j++) subM.setV(
      i,
      j,
      m[startRow + i][startCol + j]
    );

    return subM;
  }

  // write this matrix as a submatrix from b (useful for the result of multiplication)
  public void putSubmatrix(int startRow, int startCol, Matrix b) {
    for (int i = 0; i < b.n; i++) for (int j = 0; j < b.n; j++) setV(
      startRow + i,
      startCol + j,
      b.v(i, j)
    );
  }

  // matrix addition
  public Matrix sum(Matrix b) {
    Matrix c = new Matrix(n);

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        c.setV(i, j, m[i][j] + b.v(i, j));
      }
    }

    return c;
  }

  // matrix subtraction
  public Matrix sub(Matrix b) {
    Matrix c = new Matrix(n);

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        c.setV(i, j, m[i][j] - b.v(i, j));
      }
    }

    return c;
  }

  public int sumM() {
    int sum = 0;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        sum += m[i][j];
      }
    }

    return sum;
  }

  //simple multiplication
  public Matrix mult(Matrix b) {
    Matrix C = new Matrix(n);

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        for (int k = 0; k < n; k++) {
          C.setV(i, j, C.v(i, j) + m[i][k] * b.v(k, j));
        }
      }
    }

    return C;
  }

  // Strassen multiplication
  public Matrix multStrassen(Matrix b, int cutoff) {
    if (cutoff >= n) {
      return this.mult(b);
    }

    int mid = n / 2;

    Matrix a11 = this.getSubmatrix(0, 0, mid);
    Matrix a12 = this.getSubmatrix(0, mid, mid);
    Matrix a21 = this.getSubmatrix(mid, 0, mid);
    Matrix a22 = this.getSubmatrix(mid, mid, mid);

    Matrix b11 = b.getSubmatrix(0, 0, mid);
    Matrix b12 = b.getSubmatrix(0, mid, mid);
    Matrix b21 = b.getSubmatrix(mid, 0, mid);
    Matrix b22 = b.getSubmatrix(mid, mid, mid);

    Matrix m1 = (a11.sum(a22)).multStrassen(b11.sum(b22), cutoff);
    Matrix m2 = (a21.sum(a22)).multStrassen(b11, cutoff);
    Matrix m3 = (a11).multStrassen(b12.sub(b22), cutoff);
    Matrix m4 = (a22).multStrassen(b21.sub(b11), cutoff);
    Matrix m5 = (a11.sum(a12)).multStrassen(b22, cutoff);
    Matrix m6 = (a21.sub(a11)).multStrassen(b11.sum(b12), cutoff);
    Matrix m7 = (a12.sub(a22)).multStrassen(b21.sum(b22), cutoff);

    System.out.println("m1: " + m1.sumM());
    System.out.println("m2: " + m2.sumM());
    System.out.println("m3: " + m3.sumM());
    System.out.println("m4: " + m4.sumM());
    System.out.println("m5: " + m5.sumM());
    System.out.println("m6: " + m6.sumM());
    System.out.println("m7: " + m7.sumM());

    Matrix c11 = ((m1.sum(m4)).sub(m5)).sum(m7);
    Matrix c12 = m3.sum(m5);
    Matrix c21 = m2.sum(m4);
    Matrix c22 = ((m1.sub(m2)).sum(m3)).sum(m6);

    Matrix c = new Matrix(n);

    c.putSubmatrix(0, 0, c11);
    c.putSubmatrix(0, mid, c12);
    c.putSubmatrix(mid, 0, c21);
    c.putSubmatrix(mid, mid, c22);

    return c;
  }

  public void printMatrix() {
    for (int i = 0; i < m.length; i++) {
      for (int j = 0; j < m.length; j++) {
        System.out.print(m[i][j] + " ");
      }
      System.out.println();
    }
  }
}

class Izziv6 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    int cutoff = sc.nextInt();

    Matrix A = new Matrix(n);
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        A.setV(i, j, sc.nextInt());
      }
    }

    Matrix B = new Matrix(n);
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        B.setV(i, j, sc.nextInt());
      }
    }
    A.multStrassen(B, cutoff).printMatrix();
  }
}
