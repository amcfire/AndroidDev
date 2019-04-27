package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.riskteacher.teamcoin.riskteacher.RTOperation;
import com.riskteacher.teamcoin.riskteacher.RTUser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 10;

    // Database Name
    private static final String DATABASE_NAME = "RiskTeacher";

    private static final String USER_TABLE_NAME = "RTUser";

    private static final String COLUMN_ID = "id";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String BALANCE = "balance";
    private static final String EMAIL = "email";
    private static final String RISK = "risk";
    private static final String TIMERCONF = "timer";


    private static final String OP_TABLE_NAME = "OpHistory";

    private static final String OP_ID = "id";
    private static final String OP_USERNAME = "username";
    private static final String OP_TYPE = "opType";
    private static final String OP_BALANCE = "balance";
    private static final String OP_PROFIT = "opProfit";
    private static final String OP_RESULT = "opResult";
    private static final String OP_DATE = "opDate";


    private static final String ACC_TABLE_NAME = "RTAcc";

    private static final String ACC_ID = "accid";
    private static final String ACC_USERNAME = "accuser";
    private static final String ACC_BALANCE = "accbalance";
    private static final String ACC_RISK = "accrisk";
    // CreateQuery String

    private final String CREATE_TABLE = "CREATE TABLE " + USER_TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + USERNAME + " TEXT NOT NULL, "
            + PASSWORD + " TEXT NOT NULL, "
            + BALANCE + " TEXT, "
            + EMAIL + " TEXT, "
            + RISK + " TEXT,"
            + TIMERCONF + " TEXT"
            + ")";

    private final String CREATE_TABLE2 = "CREATE TABLE " + OP_TABLE_NAME + "("
            + OP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + OP_USERNAME + " TEXT,"
            + OP_TYPE + " TEXT,"
            + OP_BALANCE + " TEXT,"
            + OP_PROFIT + " TEXT,"
            + OP_RESULT + " TEXT,"
            + OP_DATE + " TEXT"
            + ")";

  /*  private final String CREATE_TABLE3 = "CREATE TABLE " + ACC_TABLE_NAME + "("
            + ACC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ACC_USERNAME + " TEXT NOT NULL, "
            + ACC_BALANCE + " TEXT NOT NULL, "
            + ACC_RISK + " TEXT NOT NULL"
            + ")";    */

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
        sqLiteDatabase.execSQL(CREATE_TABLE2);
       // sqLiteDatabase.execSQL(CREATE_TABLE3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + OP_TABLE_NAME);
        // Create tables again
        onCreate(sqLiteDatabase);
    }

    public long insertUser(RTUser user) {
        String ut = user.getRtuser();
        long id = -1;
        if(CheckUserExist(ut)){
            return id;
        }else {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(USERNAME, user.getRtuser());
            values.put(PASSWORD, user.getRtpass());
            values.put(EMAIL, user.getRtemail());
            values.put(BALANCE,"1000");
            values.put(RISK,"100");
            values.put(TIMERCONF,"3");
            id = db.insert(USER_TABLE_NAME, null, values);
            /*ContentValues v2 = new ContentValues();
            v2.put(ACC_USERNAME, user.getRtuser());
            v2.put(ACC_BALANCE, "1000");
            v2.put(ACC_RISK, "100");
            id = db.insert(ACC_TABLE_NAME, null, v2);*/
            db.close();
        }
        // return newly inserted row id
        return id;
    }

    public long insertOperation(RTOperation operation) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(OP_USERNAME, operation.getUsername());
        values.put(OP_TYPE, operation.getOpType());
        values.put(OP_BALANCE, operation.getBalance().toString());
        values.put(OP_PROFIT, operation.getOpProfit().toString());
        values.put(OP_RESULT, operation.getOpResult());
        values.put(OP_DATE, operation.getOpDate());
        // insert row
        long id = db.insert(OP_TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public RTUser getUser(String email) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(USER_TABLE_NAME,
                new String[]{USERNAME, PASSWORD, EMAIL},
                EMAIL + "=?",
                new String[]{email}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (cursor.getCount() == 0)
            return null;

        // prepare note object
        RTUser user = new RTUser(cursor.getString(cursor.getColumnIndex(USERNAME)),
                cursor.getString(cursor.getColumnIndex(PASSWORD)));

        // close the db connection
        cursor.close();
        db.close();
        return user;
    }

    public int setEmailUser(String user, String mail){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(USER_TABLE_NAME,
                new String[]{USERNAME, PASSWORD, EMAIL},
                USERNAME + "=?",
                new String[]{user}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        if (cursor.getCount() == 0) {
            return 0;
        }
        ContentValues values=new ContentValues();
        values.put(EMAIL,mail);
        int count=db.update(USER_TABLE_NAME,values,USERNAME+"=?",new String[]{user});
        cursor.close();
        db.close();
        return count;
    }

    public int setPassUser(String user, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(USER_TABLE_NAME,
                new String[]{USERNAME, PASSWORD, EMAIL},
                USERNAME + "=?",
                new String[]{user}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        if (cursor.getCount() == 0) {
            return 0;
        }
        ContentValues values=new ContentValues();
        values.put(PASSWORD,pass);
        int count=db.update(USER_TABLE_NAME,values,USERNAME+"=?",new String[]{user});
        // close the db connection
        cursor.close();
        db.close();
        return count;
    }

    public int setBalanceUser(String user, String balance){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(USER_TABLE_NAME,
                new String[]{USERNAME, BALANCE},
                USERNAME + "=?",
                new String[]{user}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        if (cursor.getCount() == 0) {
            return 0;
        }
        ContentValues values=new ContentValues();
        values.put(BALANCE,balance);
        int count=db.update(USER_TABLE_NAME,values,USERNAME+"=?",new String[]{user});
        cursor.close();
        db.close();
        return count;
    }

    public int setRiskUser(String user, String risk){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(USER_TABLE_NAME,
                new String[]{USERNAME, RISK},
                USERNAME + "=?",
                new String[]{user}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        if (cursor.getCount() == 0) {
            return 0;
        }
        ContentValues values=new ContentValues();
        values.put(RISK,risk);
        int count=db.update(USER_TABLE_NAME,values,USERNAME+"=?",new String[]{user});
        cursor.close();
        db.close();
        return count;
    }

    public int setTimerUser(String user, int timer){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(USER_TABLE_NAME,
                new String[]{USERNAME, TIMERCONF},
                USERNAME + "=?",
                new String[]{user}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        if (cursor.getCount() == 0) {
            return 0;
        }
        ContentValues values=new ContentValues();
        values.put(TIMERCONF,timer);
        int count=db.update(USER_TABLE_NAME,values,USERNAME+"=?",new String[]{user});
        cursor.close();
        db.close();
        return count;
    }



    public int resetBalanceUser(String user){
        return setBalanceUser(user, "0");
    }

    public String getBalanceUser(String user){
        String ans="0";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(USER_TABLE_NAME,
                new String[]{USERNAME, BALANCE},
                USERNAME + "=?",
                new String[]{user}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        if (cursor.getCount() == 0) {
            return "0";
        }
        /*int a = cursor.getColumnIndex("balance");
        String s1 = cursor.getString(0);
        String s2 = cursor.getString(1);
        String s3 = cursor.getString(2);
        String[] s4 = cursor.getColumnNames();
        int i1=cursor.getType(1);
        int i2=cursor.getType(3);
        int i3= cursor.getColumnCount();
        int i4=cursor.getCount();
        long s5 = cursor.getLong(4);
        String s6 = cursor.getString(5);
        String s7 = cursor.getString(6);*/
        if(cursor.getString(cursor.getColumnIndex(DatabaseHelper.BALANCE))!=null) {
            ans = cursor.getString(cursor.getColumnIndex(DatabaseHelper.BALANCE));
        }
        cursor.close();
        db.close();
        return ans;
    }

    public String getRiskUser(String user){
        String ans = "100";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(USER_TABLE_NAME,
                new String[]{USERNAME, RISK},
                USERNAME + "=?",
                new String[]{user}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        if (cursor.getCount() == 0) {
            return "0";
        }
        if(cursor.getString(cursor.getColumnIndex(RISK))!=null) {
            ans = cursor.getString(cursor.getColumnIndex(RISK));
        }
        cursor.close();
        db.close();
        return ans;
    }

    public int getTimerUser(String user){
        int ans = 3;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(USER_TABLE_NAME,
                new String[]{USERNAME, TIMERCONF},
                USERNAME + "=?",
                new String[]{user}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        if (cursor.getCount() == 0) {
            return 3;
        }
        if(cursor.getString(cursor.getColumnIndex(TIMERCONF))!=null) {
            ans = cursor.getInt(cursor.getColumnIndex(TIMERCONF));
        }
        cursor.close();
        db.close();
        return ans;
    }

    public List<RTUser> getAllUsers() {
        List<RTUser> users = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + USER_TABLE_NAME ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                RTUser user = new RTUser(cursor.getString(cursor.getColumnIndex(USERNAME))
                ,cursor.getString(cursor.getColumnIndex(PASSWORD)));
                user.setRtemail(cursor.getString(cursor.getColumnIndex(EMAIL)));
                user.setRtbalance(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex(BALANCE))));
                users.add(user);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return users;
    }

    public List<String> getAllUsenames() {
        List<String> users = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + USER_TABLE_NAME ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                String usertemp = cursor.getString(cursor.getColumnIndex(USERNAME));
                users.add(usertemp);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return users;
    }

    public boolean CheckUserExist(String u){
        boolean ans = false;
        List<String> users = getAllUsenames();
        for (String ut:users) {
            if(ut.equals(u)){
                ans=true;
            }
        }
        return ans;
    }

    public List<RTOperation> getUserOperations(String user) {
        List<RTOperation> operations = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + OP_TABLE_NAME + " WHERE " + OP_USERNAME + " = ?";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[] {user});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                RTOperation operation = new RTOperation(cursor.getString(cursor.getColumnIndex(OP_USERNAME)),
                        cursor.getString(cursor.getColumnIndex(OP_TYPE)),
                        new BigDecimal(cursor.getString(cursor.getColumnIndex(OP_BALANCE))),
                        new BigDecimal(cursor.getString(cursor.getColumnIndex(OP_PROFIT))),
                        cursor.getString(cursor.getColumnIndex(OP_RESULT)),
                        cursor.getString(cursor.getColumnIndex(OP_DATE)));

                operation.setId(cursor.getString(cursor.getColumnIndex(OP_ID)));
                operations.add(operation);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return operations;
    }

    public int delUserOperations(String user){
        SQLiteDatabase db = this.getWritableDatabase();
        int ans = db.delete(OP_TABLE_NAME,OP_USERNAME+"=?",new String[] {user});
        db.close();
        return ans;
    }

    public String genstWinUser(String user){
        String ans= "";
        SQLiteDatabase db = this.getWritableDatabase();

        String selectQuery = "SELECT "+OP_USERNAME+", SUM("+OP_PROFIT+") AS 'WIN' FROM "
                + OP_TABLE_NAME + " WHERE " + OP_USERNAME + " =  '"+user+"' AND "+OP_RESULT
                +" = 'WIN' GROUP BY "+ OP_USERNAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        if (cursor.getCount() == 0) {
            return "No result";
        }
        ans = "Total WIN by "+cursor.getString(0)+": "+cursor.getString(1);
        db.close();
        return ans;
    }

    public String genstLostUser(String user){
        String ans= "";
        SQLiteDatabase db = this.getWritableDatabase();

        String selectQuery = "SELECT "+OP_USERNAME+", SUM("+OP_PROFIT+") AS 'WIN' FROM "
                + OP_TABLE_NAME + " WHERE " + OP_USERNAME + " =  '"+user+"' AND "+OP_RESULT
                +" = 'LOST' GROUP BY "+ OP_USERNAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        if (cursor.getCount() == 0) {
            return "No result";
        }
        ans = "Total LOST by "+cursor.getString(0)+": "+cursor.getString(1);
        db.close();
        return ans;
    }


    public String genstProfitUser(String user){
        String ans= "";
        SQLiteDatabase db = this.getWritableDatabase();

        String selectQueryL = "SELECT "+OP_USERNAME+", SUM("+OP_PROFIT+") AS 'WIN' FROM "
                + OP_TABLE_NAME + " WHERE " + OP_USERNAME + " =  '"+user+"' AND "+OP_RESULT
                +" = 'LOST' GROUP BY "+ OP_USERNAME;
        Cursor cursorL = db.rawQuery(selectQueryL, null);
        if (cursorL != null) {
            cursorL.moveToFirst();
        }
        if (cursorL.getCount() == 0) {
            return "No result";
        }
        BigDecimal lost = new BigDecimal(cursorL.getString(1));

        String selectQueryW = "SELECT "+OP_USERNAME+", SUM("+OP_PROFIT+") AS 'WIN' FROM "
                + OP_TABLE_NAME + " WHERE " + OP_USERNAME + " =  '"+user+"' AND "+OP_RESULT
                +" = 'WIN' GROUP BY "+ OP_USERNAME;
        Cursor cursorW = db.rawQuery(selectQueryW, null);
        if (cursorW != null) {
            cursorW.moveToFirst();
        }
        if (cursorW.getCount() == 0) {
            return "No result";
        }
        BigDecimal win = new BigDecimal(cursorW.getString(1));
        BigDecimal total = win.subtract(lost);
        ans = "Total PROFIT by "+cursorW.getString(0)+": "+total.toString();
        db.close();
        return ans;
    }
}