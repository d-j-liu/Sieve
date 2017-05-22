package com.sciencesb.sieve;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class MainActivity extends Activity {

    // size of the grid
    public static final int minCol = 3;
    public static final int maxCol = 7;
    private int _col = ( minCol + maxCol ) / 2;

    // the grid view and its dynamic content
    private ArrayList<String> _nums = new ArrayList<String>();
    private ArrayAdapter<String> _adapter = null;
    private GridView _grid = null;

    // the engine
    private Timer _timer = null;
    private Sieve _sieve = null;

    public static void log( String msg ) {
        android.util.Log.println( 4, "Sieve", msg );
    }

    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        _adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, _nums );
        _grid = (GridView)findViewById( R.id.gridView );
        _grid.setAdapter( _adapter );
        _init();
    }

    private void _init() {
        final int row = ( _col * 16 + 8 ) / 9;
        final int count = _col * row;
        final int max = count + 1;
        _sieve = new Sieve( max );
        _grid.setNumColumns( _col );
        _nums.clear();
        for( int i = 2; i <= max; ++i ) {
            _nums.add( "" + i );
        }
        _adapter.notifyDataSetChanged();

        if( _timer != null ) _timer.cancel();
        _timer = new Timer();
        _timer.schedule( new Advancer(), 250, 250 );
    }

    public void reduce( View view ) {
        if( _col == minCol ) return;
        --_col;
        _init();
    }

    public void increase( View view ) {
        if( _col == maxCol ) return;
        ++_col;
        _init();
    }

    private class Advancer extends TimerTask {
        public void run() {
            // action on UI must be scheduled on its own thread
            _grid.post( new Updater() );
        }
    }

    public class Updater implements Runnable {
        public void run() {
            final int n = _sieve.advance();
            if( n == 0 ) { // finish
                if( _timer != null ) _timer.cancel();
                _timer = null;
                return;
            }
            // change background color based on prime vs. composite
            final int index = n > 0 ? n : -n;
            final View item = _grid.getChildAt( index - 2 );
            if( item == null ) log( "Missing grid item for " + n );
            else item.setBackgroundColor( n > 0 ? 0xFF00FF00 : 0xFFFF0000 );
        }
    }
}
