package com.sciencesb.sieve;

public class TestSieve {

    public static final int[] expected = new int[] {
            2, -4, -6, -8, -10, -12, -14, -16, -18, -20, -22, -24, -26, -28, -30,
            3, -9, -15, -21, -27, 5, -25, 7, 11, 13, 17, 19, 23, 29, 0
    };

    public static boolean test() {
        Sieve s = new Sieve( 30 );
        for( int i = 0;; ++i ) {
            final int n = s.advance();
            System.out.print( " " + n );
            if( n != expected[i] ) return false;
            if( n == 0 ) break;
        }
        System.out.println( " Done" );
        return true;
    }

    public static void main( String[] args ) {
        System.out.println( "Testing sieve, max=30" );
        System.out.println( "Result: " + ( test() ? "OK" : "FAIL" ) );
    }
}
