package com.example.employeeapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper empDB;
    EditText editID, editName, editAddress, editAge, editPosition;
    SQLiteDatabase database;
    Button submit;
    boolean isInserted;
    Button view;
    Button update;
    Button delete;


    public static final String DATABASE_NAME = "empDB.db";
    public static final String TABLE_NAME = "empTable";
    public static final String COL_1 = "empID";
    public static final String COL_2 = "empName";
    public static final String COL_3 = "empAddress";
    public static final String COL_4 = "empAge";
    public static final String COL_5 = "empPosition";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        empDB = new DatabaseHelper(this);

        editID = (EditText) findViewById(R.id.idEdit);
        editName = (EditText) findViewById(R.id.nameEdit);
        editAddress = (EditText) findViewById(R.id.addressEdit);
        editAge = (EditText) findViewById(R.id.ageEdit);
        editPosition = (EditText) findViewById(R.id.postionEdit);
        submit = (Button) findViewById(R.id.button);
        view =(Button) findViewById(R.id.buttonView);
        update =(Button) findViewById(R.id.buttonUpdate);
        delete =(Button) findViewById(R.id.buttonDelete);

        submitData();
        viewData();
        updateData();
        deleteData();
    }

    public void submitData() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                while (true) {
                    database = empDB.getWritableDatabase();
                    String eID = editID.getText().toString();
                    String eName = editName.getText().toString();
                    String eAddress = editAddress.getText().toString();
                    String eAge = editAge.getText().toString();
                    String ePosition = editPosition.getText().toString();


                    System.out.println("1");


                    if (eID.equals("") || eName.equals("") || eAddress.equals("") || eAge.equals("") || ePosition.equals("") ) {
                        System.out.println("2");
                        Toast.makeText(MainActivity.this, "Please fill all the details", Toast.LENGTH_LONG).show();
                        System.out.println("3");
                        editID.setText("");
                        editName.setText("");
                        editAddress.setText("");
                        editAge.setText("");
                        editPosition.setText("");
                        break;
                    }
                    else {

                        System.out.println("5");
                        isInserted = empDB.insertData(eID, eName, eAddress, eAge, ePosition);

                        if (isInserted == true)
                            Toast.makeText(MainActivity.this, "Data recorded successfully...", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data insertion is unsuccessful...", Toast.LENGTH_LONG).show();
                            Toast.makeText(MainActivity.this,"Probably a duplicate data entry for ID...",Toast.LENGTH_LONG).show();

                        editID.setText("");
                        editName.setText("");
                        editAddress.setText("");
                        editAge.setText("");
                        editPosition.setText("");
                        break;
                    }

                }
            }
        });

    }


    public void viewData(){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = empDB.retrieveData();
                if (cursor.getCount() == 0){
                    showMessage("Error", "nothing found" );
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()) {
                    buffer.append("Id: " + cursor.getString(0) + "\n");
                    buffer.append("Name: " + cursor.getString(1) + "\n");
                    buffer.append("Address: " + cursor.getString(2) + "\n");
                    buffer.append("Age: " + cursor.getString(3) + "\n");
                    buffer.append("Position: " + cursor.getString(4) + "\n\n\n");

                }

                showMessage("Data",buffer.toString());
            }
        });
    }


    public void showMessage( String title, String message ){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }


    public void updateData(){
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String upID = editID.getText().toString();
                String upName = editName.getText().toString();
                String upAddress = editAddress.getText().toString();
                String upAge = editAge.getText().toString();
                String upPosition = editPosition.getText().toString();

                boolean isUpdated = empDB.updateData(upID,upName,upAddress,upAge,upPosition);

                if (isUpdated == true)
                    Toast.makeText(MainActivity.this, "Data updated successfully...", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data is not updated...", Toast.LENGTH_LONG).show();

                editID.setText("");
                editName.setText("");
                editAddress.setText("");
                editAge.setText("");
                editPosition.setText("");
            }
        });
    }


    public void deleteData(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer delRows = empDB.deleteData(editID.getText().toString());

                if (delRows>0){
                    Toast.makeText(MainActivity.this,"Data is deleted",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Data is not deleted",Toast.LENGTH_LONG).show();
                }
                editID.setText("");
                editName.setText("");
                editAddress.setText("");
                editAge.setText("");
                editPosition.setText("");
            }
        });
    }


}


