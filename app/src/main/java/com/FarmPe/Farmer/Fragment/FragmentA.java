package com.FarmPe.Farmer.Fragment;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import com.FarmPe.Farmer.Adapter.RecyclerViewAdapter;
import com.FarmPe.Farmer.Bean.ContactVO;
import com.FarmPe.Farmer.R;


import java.util.ArrayList;

import static com.android.volley.VolleyLog.TAG;


public class FragmentA extends Fragment {
    public  static final int RequestPermissionCode  = 1 ;
    RecyclerView recyclerView;
    ListView list;
    Button payButton;
   // public static List<ContactVO> newmember_List = new ArrayList<>();
    ArrayList<ContactVO> newmember_List = new ArrayList<>();
    ContactVO contactVO;
    Cursor cursor;
    ProgressDialog progressdialog;
    public static FragmentA newInstance() {
        FragmentA fragment = new FragmentA();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        //String[] items = getResources().getStringArray(R.array.PhonePe);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        payButton = view.findViewById(R.id.button_pay);
        payButton.setVisibility(View.GONE);
        EnableRuntimePermission();
 //GetContactsIntoArrayList();
      getAllContacts();
        return view;
    }
  /*  public void GetContactsIntoArrayList() {
        long startnow;
        long endnow;

        startnow = android.os.SystemClock.uptimeMillis();
        // cursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null, null, null);
        //RowContacts for filter Account Types
        cursor = getActivity().getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Phone._ID,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID},
                ContactsContract.RawContacts.ACCOUNT_TYPE + "= ?",
                new String[]{"com.whatsapp"},
                null);
        //ArrayList for Store Whatsapp Contact
        ArrayList<String> myWhatsappContacts = new ArrayList<>();
        System.out.println("jkffffffffffffffffffffffffff" + myWhatsappContacts.size());
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        //whatsappContactId for get Number,Name,Id ect... from  ContactsContract.CommonDataKinds.Phone
                        String whatsappContactId = cursor.getString(cursor.getColumnIndex(ContactsContract.RawContacts.CONTACT_ID));
                        if (whatsappContactId != null) {
                            //Get Data from ContactsContract.CommonDataKinds.Phone of Specific CONTACT_ID
                            Cursor whatsAppContactCursor = getActivity().getContentResolver().query(
                                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                    new String[]{ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                                            ContactsContract.CommonDataKinds.Phone.NUMBER,
                                            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME},
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                    new String[]{whatsappContactId}, null);
                            if (whatsAppContactCursor != null) {
                                whatsAppContactCursor.moveToFirst();
                                String id = whatsAppContactCursor.getString(whatsAppContactCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                                String name = whatsAppContactCursor.getString(whatsAppContactCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                                String number = whatsAppContactCursor.getString(whatsAppContactCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                whatsAppContactCursor.close();

                                //Add Number to ArrayList
                                //myWhatsappContacts.add(number);
                                contactVO = new ContactVO(name, number);
                                newmember_List.add(contactVO);
                                RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), newmember_List);
                                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                                recyclerView.setLayoutManager(layoutManager);
                                recyclerView.setAdapter(adapter);
                                System.out.println("jkffffffffffffffffffffffffff" + name);
                                //   showLogI(TAG, " WhatsApp contact id  :  " + id);
                                // showLogI(TAG, " WhatsApp contact name :  " + name);
                                // showLogI(TAG, " WhatsApp contact number :  " + number);
                            }
                        }
                    } while (cursor.moveToNext());
                    cursor.close();
                    endnow = android.os.SystemClock.uptimeMillis();
                    Log.d("END", "TimeForContacts " + (endnow - startnow) + " ms");
                }
            }

        }
    }*/
/*
  private void getContactList() {
        ContentResolver cr = getActivity().getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));
                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        Log.i(TAG, "Name: " + name);
                        Log.i(TAG, "Phone Number: " + phoneNo);
                        contactVO = new ContactVO(name,phoneNo);
                        newmember_List.add(contactVO);
                        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(),newmember_List);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(adapter);
                    }
                    pCur.close();
                }

            }

        }
        if(cur!=null){
            cur.close();
        }
    }
    }*/
  void getAllContacts() {
      long startnow;
      long endnow;

      startnow = android.os.SystemClock.uptimeMillis();
      ArrayList arrContacts = new ArrayList();

      Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
      String selection = ContactsContract.Contacts.HAS_PHONE_NUMBER;
      Cursor cursor = getActivity().getContentResolver().query(uri, new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER,   ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone._ID, ContactsContract.Contacts._ID}, selection, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
      cursor.moveToFirst();
      while (cursor.isAfterLast() == false) {
          String contactNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
          String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
          int phoneContactID = cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID));
          int contactID = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts._ID));
          Log.d("con ", "name " + contactName + " " + " PhoeContactID " + phoneContactID + "  ContactID " + contactID);
          contactVO = new ContactVO(contactName,contactNumber);
          newmember_List.add(contactVO);
          RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(),newmember_List);
          LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
          recyclerView.setLayoutManager(layoutManager);
          recyclerView.setAdapter(adapter);
          cursor.moveToNext();
      }
      cursor.close();
      cursor = null;

      endnow = android.os.SystemClock.uptimeMillis();
      Log.d("END", "TimeForContacts " + (endnow - startnow) + " ms");
  }
    public void EnableRuntimePermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                getActivity(),
                Manifest.permission.READ_CONTACTS))
        {
            Toast.makeText(getActivity(),"CONTACTS permission allows us to Access CONTACTS app", Toast.LENGTH_LONG).show();
        } else {

            ActivityCompat.requestPermissions(getActivity(),new String[]{
                    Manifest.permission.READ_CONTACTS}, RequestPermissionCode);
        }
    }
    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {
        switch (RC) {
            case RequestPermissionCode:
                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(),"Permission Granted, Now your application can access CONTACTS.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(),"Permission Canceled, Now your application cannot access CONTACTS.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}

