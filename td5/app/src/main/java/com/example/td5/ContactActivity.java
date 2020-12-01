package com.example.td5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    List<Contact> contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        RecyclerView rvContact = (RecyclerView) findViewById(R.id.rvContacts);

        contacts.add(new Contact("Jean", "Pierre", "https://en.wikipedia.org/wiki/Clovis_I#/media/File:Saint_Remy_baptise_Clovis_d%C3%A9tail_(cropped).jpg"));
        contacts.add(new Contact("Jeanne", "D'arc", "https://histoire-image.org/sites/default/jeanne-arc-sacre-charlesvii.jpg"));
        contacts.add(new Contact("Pierre","Menez", "https://en.wikipedia.org/wiki/Pierre_M%C3%A9n%C3%A8s#/media/File:Pierre_M%C3%A9n%C3%A8s_par_Claude_Truong-Ngoc_f%C3%A9vrier_2014.jpg"));
        contacts.add(new Contact("Arthur", "Rimbault", "https://en.wikipedia.org/wiki/Arthur_Rimbaud#/media/File:Rimbaud.PNG"));
        contacts.add(new Contact("Richoard", "Coeur", "https://upload.wikimedia.org/wikipedia/commons/1/18/Bundesarchiv_B_145_Bild-F010324-0002%2C_Flughafen_K%C3%B6ln-Bonn%2C_Adenauer%2C_de_Gaulle-cropped.jpg"));
        contacts.add(new Contact("Zinedine","Zidane", "https://upload.wikimedia.org/wikipedia/commons/f/f3/Zinedine_Zidane_by_Tasnim_03.jpg"));
        contacts.add(new Contact("Yannick","Noah","https://en.wikipedia.org/wiki/Noah#/media/File:NoahsSacrifice.JPG"));

        ContactsAdaptater adaptater = new ContactsAdaptater(contacts, this);

        rvContact.setAdapter(adaptater);

        rvContact.setLayoutManager(new LinearLayoutManager(this));


    }
}