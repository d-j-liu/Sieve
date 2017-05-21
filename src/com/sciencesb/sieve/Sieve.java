package com.sciencesb.sieve;

/**
 * A prime number generator using the sieve algorithm.
 * The implementation provides a mechanism for stepping through values,
 * and is suitable for animated demo.
 */
public class Sieve {

    public static final int unknown = 0;
    public static final int prime = 1;
    public static final int composite = -1;

    // the table of integers up to the maximum value
    private final int[] _list;
    // the last position reported
    private int _pos = 1;
    // the last prime number found
    private int _prime = 0;

    /**
     * Construct an object for finding prime numbers up to a limit.
     * @param max  the upper limit for the search
     */
    public Sieve( int max ) {
        _list = new int[max + 1];
        for( int i = 0; i <= max; ++i ) {
            _list[i] = unknown;
        }
    }

    /**
     * Advance one step in the iteration.
     * The algorithm evaluates the next unchecked value on the list.
     * If it is a prime number, the value is returned as-is.
     * Otherwise the negative value is returned.
     * Calling this function successively results in the following series:
     * 2, -4, -6, . . . 3, -9, . . . 5, -25, . . . 7, -49, . . .
     * I know, I know, the scholars will demand receiving an object containing
     * the value, a prime number indicator, and some other stuff.
     * We could go one step further to use a call-back interface for this.
     * @return the result as described above, or 0 if search has already ended
     */
    public int advance() {
        if( _prime < 0 ) return 0;
        // the first step
        if( _prime == 0 ) {
            _prime = _pos = 2;
            _list[_prime] = 1;
            return _prime;
        }
        // find multiples of the current prime number
        for( ;; ) {
            _pos += _prime;
            if( _pos >= _list.length ) break;
            if( _list[_pos] != 0 ) continue;
            _list[_pos] = -1;
            return -_pos;
        }
        // done with the current prime number, find next
        for( _pos = _prime + 1; _pos < _list.length; ++_pos ) {
            if( _list[_pos] != 0 ) continue;
            _prime = _pos;
            _list[_prime] = 1;
            return _prime;
        }
        // no more prime to find
        _prime = -1;
        return 0;
    }
}
