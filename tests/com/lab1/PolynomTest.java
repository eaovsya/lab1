package com.lab1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolynomTest {
    private Polynom p1 = new Polynom(7, 4);
    private Polynom p2 = new Polynom(3, 3);
    private Polynom p3 = new Polynom(-6, 2);
    private Polynom p4 = new Polynom(1, 1);
    private Polynom p5 = new Polynom(-8, 0);

    private Polynom p = p1.plus(p2).plus(p3).plus(p4).plus(p5);

    private Polynom r1 = new Polynom(1, 1);
    private Polynom r2 = new Polynom(-2, 0);

    private Polynom r = r1.plus(r2);

    private Polynom t1 = new Polynom(7, 3);
    private Polynom t2 = new Polynom(17, 2);
    private Polynom t3 = new Polynom(28, 1);
    private Polynom t4 = new Polynom(57, 0);

    private Polynom t = t1.plus(t2).plus(t3).plus(t4);
    private Polynom remainder = new Polynom(106,0);
    private Polynom[] expectedResult = {t, remainder};
    @Test
    void solve() {
        assertEquals(106, p.solve(2));
    }

    @Test
    void divide() {
        assertArrayEquals(expectedResult, p.divide(r));
    }
}