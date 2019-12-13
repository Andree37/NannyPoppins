package com.example.nannypoppins;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class Profile extends AppCompatActivity {

    private EditText name, age;
    private Button apply;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionRef = db.collection("Child");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = (EditText) findViewById(R.id.baby_name);
        age = (EditText) findViewById(R.id.baby_age);

        //get data from firebase
        collectionRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> doc = queryDocumentSnapshots.getDocuments();
                DocumentSnapshot doc_found = null;
                for (DocumentSnapshot d : doc) {
                    String id = d.getData().get("id").toString();
                    if (Integer.parseInt(id) == MainActivity.baby_id) {
                        doc_found = d;
                    }
                }

                String db_name = doc_found.getData().get("name").toString();
                String db_age = doc_found.getData().get("birthDate").toString();
                name.setText(db_name);
                age.setText(db_age);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

        apply = (Button) findViewById(R.id.apply2);
        apply.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                final Baby new_baby = new Baby();
                new_baby.setBirthDate(Integer.parseInt(age.getText().toString()));
                new_baby.setName(name.getText().toString());
                new_baby.setGender("Rapaz");

                final DocumentReference childRef = db.collection("Child").document();

                db.collection("Child").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        new_baby.setID(queryDocumentSnapshots.getDocuments().size());

                        childRef.set(new_baby).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast savedToast = Toast.makeText(getApplicationContext(), "New baby info Saved!", Toast.LENGTH_SHORT);
                                savedToast.show();;
                            }
                        });
                        MainActivity.baby_id = new_baby.getID();
                    }
                });
            }
        });

    }
}
