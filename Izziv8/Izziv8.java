import java.util.Scanner;

class Complex {

  double re;
  double im;

  public Complex(double real, double imag) {
    re = real;
    im = imag;
  }

  public String toString() {
    double tRe = (double) Math.round(re * 100000) / 100000;
    double tIm = (double) Math.round(im * 100000) / 100000;
    if (tIm == 0) return tRe + "";
    if (tRe == 0) return tIm + "i";
    if (tIm < 0) return tRe + "-" + (-tIm) + "i";
    return tRe + "+" + tIm + "i";
  }

  public Complex conj() {
    return new Complex(re, -im);
  }

  // sestevanje
  public Complex plus(Complex b) {
    Complex a = this;
    double real = a.re + b.re;
    double imag = a.im + b.im;
    return new Complex(real, imag);
  }

  // odstevanje
  public Complex minus(Complex b) {
    Complex a = this;
    double real = a.re - b.re;
    double imag = a.im - b.im;
    return new Complex(real, imag);
  }

  // mnozenje z drugim kompleksnim stevilo
  public Complex times(Complex b) {
    Complex a = this;
    double real = a.re * b.re - a.im * b.im;
    double imag = a.re * b.im + a.im * b.re;
    return new Complex(real, imag);
  }

  // mnozenje z realnim stevilom
  public Complex times(double alpha) {
    return new Complex(alpha * re, alpha * im);
  }

  // reciprocna vrednost kompleksnega stevila
  public Complex reciprocal() {
    double scale = re * re + im * im;
    return new Complex(re / scale, -im / scale);
  }

  // deljenje
  public Complex divides(Complex b) {
    Complex a = this;
    return a.times(b.reciprocal());
  }

  // e^this
  public Complex exp() {
    return new Complex(
      Math.exp(re) * Math.cos(im),
      Math.exp(re) * Math.sin(im)
    );
  }

  // w
  public Complex w(double n, double p) {
    return new Complex(
      Math.cos((2 * Math.PI) * (p / n)),
      Math.sin((2 * Math.PI) * (p / n))
    );
  }

  //potenca komplesnega stevila
  public Complex pow(int k) {
    Complex c = new Complex(1, 0);
    for (int i = 0; i < k; i++) {
      c = c.times(this);
    }
    return c;
  }

  public Complex inverse() {
    return new Complex(1, 0).divides(this);
  }
}

public class Izziv8 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();

    int N = 1;
    while (n + n > N) {
      N = N * 2;
    }

    Complex[] p1 = new Complex[N];
    Complex[] p2 = new Complex[N];

    for (int i = 0; i < n; i++) {
      p1[i] = new Complex(0, 0);
      p1[i].re = sc.nextDouble();
    }

    for (int i = n; i < N; i++) {
      p1[i] = new Complex(0, 0);
    }

    for (int i = 0; i < n; i++) {
      p2[i] = new Complex(0, 0);
      p2[i].re = sc.nextDouble();
    }

    for (int i = n; i < N; i++) {
      p2[i] = new Complex(0, 0);
    }

    Complex[] p1k = FFT(N, p1, N, false);
    Complex[] p2k = FFT(N, p2, N, false);

    Complex[] p3k = new Complex[N];

    for (int i = 0; i < N; i++) {
      p3k[i] = p1k[i].times(p2k[i]);
      //System.out.println(p3k[i]);
    }
    Complex[] rez = FFT(N, p3k, N, true);

    for (int i = 0; i < rez.length; i++) {
      System.out.print(rez[i].divides(new Complex(N, 0)).toString() + " ");
    }
  }

  // N se zmanjsuje, n ostane skos isti
  public static Complex[] FFT(int N, Complex[] p, int n, boolean inverse) {
    if (p.length == 1) {
      return p;
    }

    //sodi del
    Complex[] pS = new Complex[N / 2];
    int j = 0;
    for (int i = 0; i < p.length; i += 2) {
      pS[j] = new Complex(0, 0);
      pS[j] = p[i];
      j++;
    }
    Complex[] pcS = FFT(N / 2, pS, n, inverse);

    //lihi del
    Complex[] pL = new Complex[N / 2];
    j = 0;
    for (int i = 1; i < p.length; i += 2) {
      pL[j] = new Complex(0, 0);
      pL[j] = p[i];
      j++;
    }
    Complex[] pcL = FFT(N / 2, pL, n, inverse);

    // Izracun
    Complex[] newpc = new Complex[N];
    Complex w = new Complex(1, 0);

    for (int i = 0; i < N / 2; i++) {
      int iW = i;

      w = w.w(n, iW);
      if (inverse) {
        w = w.inverse();
      }
      newpc[i] = pcS[i].plus(w.times(pcL[i]));

      iW += n / 2;
      w = new Complex(1, 0);
      w = w.w(n, iW);
      if (inverse) {
        w = w.inverse();
      }
      newpc[i + N / 2] = pcS[i].plus(w.times(pcL[i]));
    }

    for (int i = 0; i < newpc.length; i++) {
      System.out.print(newpc[i].toString() + " ");
    }
    System.out.println();

    return newpc;
  }
}
