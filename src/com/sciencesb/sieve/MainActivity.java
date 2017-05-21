package com.sciencesb.sieve;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class MainActivity extends Activity {

    private GridView _grid = null;
    private ArrayList<String> _nums = new ArrayList<String>();
    private ArrayAdapter<String> _adapter = null;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        _grid = (GridView)findViewById( R.id.gridView );
        _adapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, _nums );
        _grid.setAdapter( _adapter );

        _grid.setNumColumns( 5 );
        _nums.clear();
        for( int i = 0; i < 17; ++i ) {
            _nums.add( " " + i );
        }
        _adapter.notifyDataSetChanged();
    }

    public void reduce( View view ) {}

    public void increase( View view ) {}
}
