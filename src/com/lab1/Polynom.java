package com.lab1;

public class Polynom {
    private int[] terms;
    private int degree;

    public Polynom(int i, int j) {
        if(j < 0) {
            throw new IllegalArgumentException("exponent cannot be negative: " + j);
        }
        terms = new int[j + 1];
        terms[j] = i;
        countDegree();
    }

    public void countDegree() {
        for (int i = terms.length - 1; i >= 0; i--) {
            if (terms[i] != 0) {
                degree = i;
                return;
            }
        }
    }

    public int solve(int x) {
        int result = 0;
        for(int i = degree; i >= 0; i--) {
            result = terms[i] + x * result;
        }
        return result;
    }

    public Polynom plus(Polynom a) {
        Polynom c = new Polynom(0, Math.max(this.degree, a.degree));
        for (int i = 0; i <= this.degree; i++) {
            c.terms[i] += this.terms[i];
        }
        for(int i = 0; i <= a.degree; i++) {
            c.terms[i] += a.terms[i];
        }
        c.countDegree();
        return c;
    }

    private Polynom minus(Polynom a) {
        Polynom c = new Polynom(0, Math.max(this.degree, a.degree));
        for (int i = 0; i <= this.degree; i++) {
            c.terms[i] += this.terms[i];
        }
        for(int i = 0; i <= a.degree; i++) {
            c.terms[i] -= a.terms[i];
        }
        c.countDegree();
        return c;
    }

    private Polynom multiply(Polynom a) {
        Polynom c = new Polynom(0, this.degree + a.degree);
        for (int i = 0; i <= this.degree; i++) {
            for(int j = 0; j <= a.degree; j++) {
                c.terms[i+j] += (this.terms[i] * a.terms[j]);
            }
        }
        c.countDegree();
        return c;
    }

    public Polynom[] divide(Polynom a) {
        Polynom q = new Polynom(0, 0);
        Polynom r = this;
        while(!r.isZero() && r.degree >= a.degree) {
            int term = r.terms[r.degree]/a.terms[a.degree];
            int deg = r.degree - a.degree;
            Polynom t = new Polynom(term, deg);
            q = q.plus(t);
            r = r.minus(t.multiply(a));
        }
        return new Polynom[]{q, r};
    }

    private boolean isZero() {
        for(int i: terms) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public  String toString() {
        if (degree == -1) {
            return "0";
        } else if (degree == 0) {
            return "" + terms[0];
        } else if (degree == 1) return terms[1] + "x" + terms[0];
        StringBuilder polyString = new StringBuilder(terms[degree] + "x^" + degree);
        for(int i = degree - 1; i >= 0; i--) {
            if(terms[i] == 0) {
                continue;
            } else if(terms[i] > 0) {
                polyString.append(" + ").append(terms[i]);
            } else if(terms[i] < 0) {
                polyString.append(" - ").append(-terms[i]);
            }
            if(i == 1) {
                polyString.append("x");
            } else if (i > 1) {
                polyString.append("x^").append(i);
            }
        }
        return polyString.toString();
    }

    @Override
    public boolean equals(Object a) {
        if(a == this) {
            return  true;
        }
        if(a == null) {
            return false;
        }
        if(a.getClass() != this.getClass()) {
            return false;
        }
        Polynom b = (Polynom) a;
        if(this.degree != b.degree) {
            return  false;
        }
        for(int i = this.degree; i >= 0; i--) {
            if(this.terms[i] != b.terms[i]) {
                return  false;
            }
        }
        return  true;
    }

    public  static  void main(String[] args) {
        Polynom p1 = new Polynom(7, 4);
        Polynom p2 = new Polynom(3, 3);
        Polynom p3 = new Polynom(-6, 2);
        Polynom p4 = new Polynom(1, 1);
        Polynom p5 = new Polynom(-8, 0);

        Polynom r1 = new Polynom(1, 1);
        Polynom r2 = new Polynom(-2, 0);
        Polynom q = p1.plus(p2).plus(p3).plus(p4).plus(p5);
        Polynom r = r1.plus(r2);
        System.out.println(q.toString());
        System.out.println(r.toString());
        Polynom[] result = q.divide(r);
        System.out.println(result[0].toString() + " Remainder: " + result[1].toString());
        System.out.println(q.toString() + " Where x = 2: " + q.solve(2));
    }
}
