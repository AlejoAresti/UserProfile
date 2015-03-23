package gt.org.kinal.userprofile.sqlite;

import java.util.Locale;

/**
 * Created by Alejo on 21/03/2015.
 */
public class SQLiteTable {
    public final String TABLE_NAME;
    public final String[] TABLE_COLUMNS;
    public final String[] COLUMN_TYPES;
    private final String[] PRIMARY_KEYS;
    private final String[] FOREIGN_KEYS;
    private final SQLiteTable[] PARENT;

    public SQLiteTable(String table, String[] columns, String[] types, String[] primaryKeys) {
        this(table, columns, types, null, null, primaryKeys);
    }
    public SQLiteTable(String table, String[] columns, String[] types, String[] foreignKeys, SQLiteTable[] parent, String[] primaryKeys) {
        TABLE_NAME = table.toLowerCase(Locale.getDefault());
        TABLE_COLUMNS = columns;
        COLUMN_TYPES = toUpperCase(types);
        FOREIGN_KEYS = foreignKeys;
        PARENT = parent;
        PRIMARY_KEYS = primaryKeys;
    }

    private String[] toUpperCase(String[] array) {
        String[] inUpperCase = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            inUpperCase[i] = array[i].toUpperCase(Locale.getDefault());
        }
        return inUpperCase;
    }

    public String getCreateStatement() {
        String statement = new String("CREATE TABLE IF NOT EXISTS '" + TABLE_NAME + "' (");
        for (int i = 0; i < TABLE_COLUMNS.length; i++) {
            statement += TABLE_COLUMNS[i] + " " + COLUMN_TYPES[i] + ", ";
        }
        if (PARENT != null) {
            int i = 0;
            for (SQLiteTable parent : PARENT) {
                if (FOREIGN_KEYS[i] != null) {
                    statement += "FOREIGN KEY (" + FOREIGN_KEYS[i] + ") REFERENCES " + parent.TABLE_NAME + " (" + FOREIGN_KEYS[i] + ")";
                }
                i++;
                statement += ", ";
            }
        }
        if (PRIMARY_KEYS != null) {
            statement += " PRIMARY KEY (";
            for (int i = 0; i < PRIMARY_KEYS.length; i++) {
                statement += PRIMARY_KEYS[i];
                if (i + 1 < PRIMARY_KEYS.length) {
                    statement += ", ";
                }
            }
            statement += ")";
        }
        statement += ")";
        return statement;
    }
    public String getDeleteStatement() {
        return "DROP TABLE IF EXISTS '" + TABLE_NAME + "'";
    }

}
