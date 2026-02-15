import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

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
    fun addProduct(member: String, bookname: String, date: String) {
        val db=this.writableDatabase
        val values=ContentValues()
        values.put(MEMBERNAME, member)
        values.put(BOOKNAME, bookname)
        values.put(DATE, date)
        db.insert(DATABASE_TABLE_NAME, null, values)
        db.close()
    }
    fun deleteProduct(name: String) {
        val db=writableDatabase
        db.execSQL("delete from $DATABASE_TABLE_NAME where $BOOKNAME=\"${name}\";")
    }
    @SuppressLint("Range", "SuspiciousIndentation")
    fun show(): Array<Array<String>> {
        val db=writableDatabase
        val query="SELECT * FROM $DATABASE_TABLE_NAME WHERE 1"
        val c=db.rawQuery(query, null)
        var dbString=Array(c.count) { Array(4) { "" } }
        var i=0
        if (c.moveToFirst()) {
            while (!c.isAfterLast) {
                dbString[i][0]=c.getString(c.getColumnIndex(MEMBERNAME))
                dbString[i][1]=c.getString(c.getColumnIndex(BOOKNAME))
                dbString[i][2]=c.getString(c.getColumnIndex(DATE))
                dbString[i][3]=c.getString(c.getColumnIndex(KEY_ID))
                i++
                c.moveToNext()
            }
        }
        c.close()
        return dbString
    }
    @SuppressLint("Range")
    fun find(name: String): Array<Array<String>> {
        val db=writableDatabase
        val query="select * from $DATABASE_TABLE_NAME where $BOOKNAME=\"${name}\";"
        val c=db.rawQuery(query, null)
        var dbString=Array(c.count) { Array(4) { "" } }
        var i=0
        if (c.moveToFirst()) {

            while (!c.isAfterLast) {
                dbString[i][0]=c.getString(c.getColumnIndex(MEMBERNAME))
                dbString[i][1]=c.getString(c.getColumnIndex(BOOKNAME))
                dbString[i][2]=c.getString(c.getColumnIndex(DATE))
                dbString[i][3]=c.getString(c.getColumnIndex(KEY_ID))

                i++
                c.moveToNext()
            }
        }
        c.close()
        return dbString
    }
}