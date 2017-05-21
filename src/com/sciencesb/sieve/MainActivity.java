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

    public static final int minCol = 3;
    public static final int maxCol = 7;

    private ArrayList<String> _nums = new ArrayList<String>();
    private ArrayAdapter<String> _adapter = null;
    private GridView _grid = null;
    private int _col = ( minCol + maxCol ) / 2;

    private Timer _timer = null;
    private int _current = 0;

    public static void log( String msg ) {
        android.util.Log.println( 4, "Sieve", msg );
    }

    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        _adapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, _nums );
        _grid = (GridView)findViewById( R.id.gridView );
        _grid.setAdapter( _adapter );
        _init();
    }

    private void _init() {
        final int row = ( _col * 16 + 8 ) / 9;
        final int max = _col * row;
        _grid.setNumColumns( _col );
        _nums.clear();
        for( int i = 0; i < max; ++i ) {
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
            _grid.post( new Updater() );
        }
    }

    public class Updater implements Runnable {
        public void run() {
            final View item = _grid.getChildAt( 0 );
            if( item == null ) log( "Grid item not found." );
            else item.setBackgroundColor( ++_current % 2 == 0 ? 0xFF00FF00 : 0xFF0000FF );
        }
    }
}
