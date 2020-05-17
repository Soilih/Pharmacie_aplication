package com.example.wonono;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //creation de la base de donnés
    private  static  final String DATABASE_NAME ="SanteDatabase.db";
    public  static final int DATABASE_VERSION = 1;
     //creation de la table Pharmacie
    private static final String TABLE_NAME ="Pharmacie";

    //creation des attributs(tuples)
    private static final String COL_1 ="id";
    private static  final String COL_2 ="nom";
    private static  final String COL_3 ="telephone";
    private static  final String COL_4 ="email";
    private  static  final String COL_5 ="detail";
    private  static  final String COL_6 ="ville";


    public DatabaseHelper(Context context) {

        super(context , DATABASE_NAME ,null, 1);
    }
    //======================================Creation d'une table pharmacie =====================================================//
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_NAME + "(id  integer primary key autoincrement ,nom TEXT , telephone TEXT , email TEXT , detail TEXT , ville TEXT  )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF  EXISTS  " + TABLE_NAME);
        onCreate(db);
    }

    //======================================Insertion d'une pharmacie =====================================================//

    //Dans cette portion de code permet d'ajouter ne pharmacie dans la base de donnés
    public  boolean insertPharmacie(String nom , String tel , String email  ,  String detail , String ville ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2 , nom);
        values.put(COL_3 , tel);
        values.put(COL_4 , email);
        values.put(COL_5 , detail);
        values.put(COL_6 , ville);
        long result = db.insert(TABLE_NAME , null , values);
        if(result == -1)
            return false; else return true;
    }
    //========================================Liste de pharmacie ===================================================//

    //methode permettant  d'afficher sous forme d'une liste les pharmacies

    public Cursor List_Pharmacie(){
        SQLiteDatabase db = this.getWritableDatabase(); //connexion à la base de donnés et ecriture de la base
        Cursor resultat  = db.rawQuery("SELECT * FROM  "+ TABLE_NAME , null);
        return resultat;
    }

    //============================Supression d'une pharmacie =========================================================//

            //je cree une mehode qui permet de suprimer une pharmacie
            //en saisisant l'id de la pharmacie dans le champs de text
            public Integer delete_Pharmacie(String id){
                SQLiteDatabase db = this.getWritableDatabase();
                return db.delete(TABLE_NAME ,  "id = ?" , new String[]{id});
            }

    //======================================Update pharmacie =====================================================//

    //methode qui permet de modifier une pharmacie selon l4id

    public boolean Update_pharmacie(Long id , String nom , String telephone
            , String email , String detail , String ville )
    {
        SQLiteDatabase  db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_1 , id);
        values.put(COL_2 , nom);
        values.put(COL_3 , telephone);
        values.put(COL_4 , email);
        values.put(COL_5 , detail);
        values.put(COL_6 , ville);
        db.update(TABLE_NAME , values , COL_1 + "= " +id , null);
        db.close();

        return true;
    }

    //====================================Recherche d'une pharmacie à prtir de son id pour pouvoir modifier  =======================================================//
     //je cree des methode qui permet desectionner nom , email , tel , detail dans la base
     public Cursor search_pharmacie(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="SELECT * FROM "+TABLE_NAME +"WHERE id=' "+id + " '";
        Cursor cursor =db.rawQuery(query , null);

        return cursor;
    }

     //Recuperer le nom de à partir du formulaire de modifcation
            public String getNom(long l1)
            {
                SQLiteDatabase  db = this.getReadableDatabase();
                String[] columns = new String[]{COL_1 , COL_2 , COL_3 , COL_4 , COL_5 , COL_6};
                Cursor cursor = db.query(TABLE_NAME , columns , COL_1 + " = " +l1 ,
                        null , null ,null ,null);
                if(cursor != null){
                    cursor.moveToFirst();
                    String nom = cursor.getString(1);
                    return nom;

                }
                return null;
            }

    //Recuperer le mail  de à partir du formulaire de modifcation
    public String getEmail(long l1)
    {
        SQLiteDatabase  db = this.getReadableDatabase();
        String[] columns = new String[]{COL_1 , COL_2 , COL_3 , COL_4 , COL_5 , COL_6};
        Cursor cursor = db.query(TABLE_NAME , columns , COL_1 + " = " +l1 ,null , null ,null ,null);
        if(cursor != null){
            cursor.moveToFirst();
            String email = cursor.getString(3);
            return email;

        }
        return null;
    }
    //Recuperer le telephone de à partir du formulaire de modifcation
    public String getTel(long l1)
    {
        SQLiteDatabase  db = this.getReadableDatabase();
        String[] columns = new String[]{COL_1 , COL_2 , COL_3 , COL_4 , COL_5 , COL_6};
        Cursor cursor = db.query(TABLE_NAME , columns , COL_1 + " = " +l1 ,null , null ,null ,null);
        if(cursor != null){
            cursor.moveToFirst();
            String tel = cursor.getString(2);
            return tel;

        }
        return null;
    }

    //Recuperer le Detail de à partir du formulaire de modifcation
     public String getDetail(long l1)
    {
        SQLiteDatabase  db = this.getReadableDatabase();
        String[] columns = new String[]{COL_1 , COL_2 , COL_3 , COL_4 , COL_5 , COL_6};
        Cursor cursor = db.query(TABLE_NAME , columns , COL_1 + " = " +l1 ,null , null ,null ,null);
        if(cursor != null){
            cursor.moveToFirst();
            String detail = cursor.getString(4);
            return detail;

        }
        return null;
    }


    //Recuperer la ville de à partir du formulaire de modifcation

    public String getville(long l1)
    {
        SQLiteDatabase  db = this.getReadableDatabase();
        String[] columns = new String[]{COL_1 , COL_2 , COL_3 , COL_4 , COL_5 , COL_6};
        Cursor cursor = db.query(TABLE_NAME , columns , COL_1 + " = " +l1 ,null , null ,null ,null);
        if(cursor != null){
            cursor.moveToFirst();
            String ville = cursor.getString(5);
            return ville;

        }
        return null;
    }

    //=============== fonction qui permet d'afficher l'Annuaire des pharmacie  ===================//
        public String  getAnnuaire(){
           SQLiteDatabase db = this.getReadableDatabase();
              String[] columns = new String[]{COL_1 , COL_2 , COL_3 , COL_4 , COL_5 , COL_6};
            Cursor cursor = db.query(TABLE_NAME , columns , null , null , null , null , null);

            int iId = cursor.getColumnIndex(COL_1);
            int nom = cursor.getColumnIndex(COL_2);
            int tel = cursor.getColumnIndex(COL_3);
            int email = cursor.getColumnIndex(COL_4 );

            String result ="";

            for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
                result = result +
                        "ID : " +cursor.getString(iId) + "\n"+
                        "Nom : " +cursor.getString(1) + "\n"+
                        "email : " +cursor.getString(2) + "\n"+
                        "tel : " +cursor.getString(3) + "\n";
            }
            db.close();

            return result;
     }



    //======================================Methode permettant de savoir les horaire de
    // et les disponibilite des pharmacie =====================================================//

    public String  getHoraire(){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[]{COL_1 , COL_2 , COL_3 , COL_4 , COL_5 , COL_6};
        Cursor cursor = db.query(TABLE_NAME , columns , null , null , null , null , null);

        int iId = cursor.getColumnIndex(COL_1);
        int nom = cursor.getColumnIndex(COL_2);
        int ville = cursor.getColumnIndex(COL_6);
        int detail = cursor.getColumnIndex(COL_5);

        String result ="";

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            result = result +
                    "ID : " +cursor.getString(iId) + "\n"+
                    "Nom : " +cursor.getString(1) + "\n"+
                    "ville : " +cursor.getString(5) + "\n"+
                    "adresse : " +cursor.getString(4) + "\n";

        }
        db.close();

        return result;
    }


    //=============== Recherche par VILLE ===================//

    public Cursor Search_pharmacie_Name(String text){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM  "+TABLE_NAME +"  WHERE "+ COL_2 +" like '%"+text+ "%' ";
        Cursor cursor = db.rawQuery(query , null);

        return cursor;



    }




}
