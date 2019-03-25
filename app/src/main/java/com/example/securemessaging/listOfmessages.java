package com.example.securemessaging;



import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class listOfmessages extends AppCompatActivity {

    ListView searchNamesOfUsers;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_messages);
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        searchNamesOfUsers = (ListView)findViewById(R.id.search_names_of_users);
        ArrayList<String> arraySearchNamesOfUsers = new ArrayList<>();
        arraySearchNamesOfUsers.addAll(Arrays.asList(getResources().getStringArray(R.array.names_of_users)));
        adapter = new ArrayAdapter<String>(listOfmessages.this
                                          ,android.R.layout.simple_list_item_1
                                          ,arraySearchNamesOfUsers);
        searchNamesOfUsers.setAdapter(adapter);

    }

    public boolean onCreateOptionsMenu(Menu menu){//for the menu to work that 3 dot thingy
        getMenuInflater().inflate(R.menu.list_of_messages_menu,menu);
        MenuItem item = menu.findItem(R.id.search);

        SearchView searchView =  (SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }

    @Override//back button to display logout message
    public boolean onSupportNavigateUp(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Logout");
        builder.setMessage("Do you really want to logout?");
        builder.setPositiveButton("yes",new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Toast.makeText(listOfmessages.this, "Logged out", Toast.LENGTH_LONG).show();
                finish();
            }
        });
        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
        return true;
    }


    @Override//back button to display logout message
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Logout");
        builder.setMessage("Do you really want to logout?");
        builder.setPositiveButton("yes",new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Toast.makeText(listOfmessages.this, "Logged out", Toast.LENGTH_LONG).show();
                finish();
            }
        });
        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }



}
