import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import edu.moh.database_arrayadapter.BookModel

class MydataBase(context: Context):SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0!!.execSQL(DATABASE_CREATE)
    }
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS $DATABASE_TABLE_NAME")
        onCreate(p0)
    }
    companion object {
        private val KEY_ID="_ID"
        private val MEMBERNAME="membername"
        private val BOOKNAME="bookname"
        private val DATE="date"
        private val DATABASE_TABLE_NAME="book"
        private val DATABASE_VERSION=1
        private val DATABASE_NAME="name"
        private val DATABASE_CREATE=
            "CREATE TABLE $DATABASE_TABLE_NAME ($KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$BOOKNAME TEXT NOT NULL, " + "$MEMBERNAME TEXT NOT NULL, " + "$DATE TEXT NOT NULL);"
    }
    fun addProduct(p:BookModel) {
        val db=this.writableDatabase
        val values=ContentValues()
        values.put(MEMBERNAME,p.nameM)
        values.put(BOOKNAME, p.nameB)
        values.put(DATE, p.date)
        values.put(KEY_ID,p.id)
        db.insert(DATABASE_TABLE_NAME, null, values)
        db.close()
    }
    fun deleteProduct(p:Int) {
        val db=writableDatabase
        db.execSQL("delete from $DATABASE_TABLE_NAME where $BOOKNAME=\"${p}\";")
    }
    @SuppressLint("Range", "SuspiciousIndentation")
    fun show(): MutableList<BookModel> {
        var dbString:ArrayList<BookModel>
        val db=writableDatabase
        val query="SELECT * FROM $DATABASE_TABLE_NAME"
        val c=db.rawQuery(query, null)
         dbString=ArrayList(c.count)
            if (c.moveToFirst()) {
                do {
                    var member=c.getString(c.getColumnIndex(MEMBERNAME))
                    var book=c.getString(c.getColumnIndex(BOOKNAME))
                    var date=c.getInt(c.getColumnIndex(DATE))
                    var id2=c.getInt(c.getColumnIndex(KEY_ID))
                    var b=BookModel(member,book,date,id2)
                    dbString.add(b)
                }
                while (c.moveToNext())
            }
        return dbString
        }
    @SuppressLint("Range", "SuspiciousIndentation")
    fun find(P:BookModel): MutableList<BookModel> {
        var dbString:ArrayList<BookModel>
        val db=writableDatabase
        val query="select * from $DATABASE_TABLE_NAME where $BOOKNAME=\"${P.nameB}\";"
        val c=db.rawQuery(query, null)
        dbString=ArrayList(c.count)
        if (c.moveToFirst()) {
            do {
                var MEMBERNAME=c.getString(c.getColumnIndex(MEMBERNAME))
                var BOOKNAME=c.getString(c.getColumnIndex(BOOKNAME))
               var DATE=c.getInt(c.getColumnIndex(DATE))
               var KEY_ID=c.getInt(c.getColumnIndex(KEY_ID))
                var book=BookModel(MEMBERNAME,BOOKNAME,DATE,KEY_ID)
                    dbString.add(book)
            }
        while (c.moveToNext())
        }
        return dbString
    }
}